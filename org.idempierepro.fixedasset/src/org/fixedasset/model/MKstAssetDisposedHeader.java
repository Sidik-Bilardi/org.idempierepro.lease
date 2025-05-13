package org.fixedasset.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAssetDisposed;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.Query;
import org.compiere.print.ReportEngine;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.Msg;

public class MKstAssetDisposedHeader extends X_A_Asset_Disposed_Header
		implements DocAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7501397528499847573L;
	
	/**	Process Message 			*/
	protected String		m_processMsg = null;
	/**	Just Prepared Flag			*/
	protected boolean		m_justPrepared = false;

	public MKstAssetDisposedHeader(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public MKstAssetDisposedHeader(Properties ctx, int A_Asset_Disposed_Header_ID, String trxName) {
		super(ctx, A_Asset_Disposed_Header_ID, trxName);
	}

	@Override
	public boolean processIt(String action) throws Exception {
		m_processMsg = null;
		DocumentEngine engine = new DocumentEngine (this, getDocStatus());
		return engine.processIt (action, getDocAction());
	}

	@Override
	public boolean unlockIt() {
		if (log.isLoggable(Level.INFO)) log.info("unlockIt - " + toString());
		setProcessing(false);
		return true;
	}

	@Override
	public boolean invalidateIt() {
		if (log.isLoggable(Level.INFO)) log.info("invalidateIt - " + toString());
		setDocAction(DOCACTION_Prepare);
		return true;
	}

	@Override
	public String prepareIt() {
		if (log.isLoggable(Level.INFO)) log.info(toString());
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
		
		
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;

		setProcessing(true);
		m_justPrepared = true;
		if (!DOCACTION_Complete.equals(getDocAction()))
			setDocAction(DOCACTION_Complete);
		return DocAction.STATUS_InProgress;
	}

	@Override
	public boolean approveIt() {
		if (log.isLoggable(Level.INFO)) log.info("approveIt - " + toString());
		setIsApproved(true);
		return true;
	}

	@Override
	public boolean rejectIt() {
		if (log.isLoggable(Level.INFO)) log.info("rejectIt - " + toString());
		setIsApproved(false);
		return true;
	}

	@Override
	public String completeIt() {
		if (log.isLoggable(Level.INFO)) log.info(toString());
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_COMPLETE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
		
		List<MAssetDisposed> assets = new Query(getCtx(), MAssetDisposed.Table_Name, "A_Asset_Disposed_Header_ID=?",get_TrxName())
		.setParameters(getA_Asset_Disposed_Header_ID())
		.list();
		
		if(assets.isEmpty()){
			m_processMsg = "No Asset for Disposed";
			return DocAction.STATUS_Invalid;
		}
		
		for(MAssetDisposed asset : assets){
			if(asset.processIt(MAssetDisposed.DOCACTION_Complete))
				asset.saveEx();
		}
		
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;

		setProcessed(true);
		
		if (!DOCACTION_Complete.equals(getDocAction()))
			setDocAction(DOCACTION_Close);
		return DocAction.STATUS_Completed;
	}

	@Override
	public boolean voidIt() {
		return false;
	}

	@Override
	public boolean closeIt() {
		if (log.isLoggable(Level.INFO)) log.info("closeIt - " + toString());
		// Before Close
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_CLOSE);
		if (m_processMsg != null)
			return false;
		
		List<MAssetDisposed> assets = new Query(getCtx(), MAssetDisposed.Table_Name, "A_Asset_Disposed_Header_ID=?",get_TrxName())
		.setParameters(getA_Asset_Disposed_Header_ID())
		.list();
		
		for(MAssetDisposed asset : assets){
			if(asset.processIt(MAssetDisposed.DOCACTION_Close))
				asset.saveEx();
		}
		
		// After Close
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_CLOSE);
		if (m_processMsg != null)
			return false;

		setDocAction(DOCACTION_None);
		return true;
	}

	@Override
	public boolean reverseCorrectIt() {
		throw new AdempiereException("not implemented yet");
	}

	@Override
	public boolean reverseAccrualIt() {
		throw new AdempiereException("not implemented yet");
	}

	@Override
	public boolean reActivateIt() {
		throw new AdempiereException("not implemented yet");
	}

	@Override
	public String getSummary() {
		return getDocumentNo()+" Status "+getDocStatus();
	}

	@Override
	public String getDocumentInfo() {
		return Msg.getElement(getCtx(), "A_Asset_Disposed_Header") + " " + getDocumentNo();
	}

	/**
	 * 	Create PDF
	 *	@return File or null
	 */
	public File createPDF ()
	{
		try
		{
			// default name from documentno AH@20180512
			//File temp = File.createTempFile(get_TableName()+get_ID()+"_", ".pdf");
			File temp = File.createTempFile(getDocumentNo().replace("/", "")+"_", ".pdf");
			return createPDF (temp);
		}
		catch (Exception e)
		{
			log.severe("Could not create PDF - " + e.getMessage());
		}
		return null;
	}	//	getPDF
	
	public File createPDF(File file) {
		ReportEngine re = ReportEngine.get (getCtx(), 13, getA_Asset_Disposed_Header_ID(),get_TrxName());
		if (re == null)
			return null;
		return re.getPDF(file);
	}

	@Override
	public String getProcessMsg() {
		return m_processMsg;
	}

	@Override
	public int getDoc_User_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getC_Currency_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BigDecimal getApprovalAmt() {
		// TODO Auto-generated method stub
		return null;
	}

}
