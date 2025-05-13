package org.fixedasset.model;

import java.io.File;
import java.math.BigDecimal;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.process.DocAction;
import org.compiere.process.DocOptions;
import org.compiere.process.DocumentEngine;

public class MAssetStock extends X_A_AssetStock implements DocAction,DocOptions{

	public MAssetStock(Properties ctx, int A_AssetStock_ID, String trxName) {
		super(ctx, A_AssetStock_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	@Override
	public int customizeValidActions(String docStatus, Object processing, String orderType, String isSOTrx,
			int AD_Table_ID, String[] docAction, String[] options, int index) {
		// TODO Auto-generated method stub
		for(int i=0;i<options.length;i++){
			options[i]=null;
		}		
		index = 0;
		if(docStatus.equals(DocAction.STATUS_Drafted)){
			options[index++] = DocAction.ACTION_Complete;
			options[index++] = DocAction.ACTION_Void;
		}else if(docStatus.equals(DocAction.STATUS_Invalid)){
			options[index++] = DocAction.ACTION_Complete;
			options[index++] = DocAction.ACTION_Void;
		}else if(docStatus.equals(DocAction.STATUS_NotApproved)){
			options[index++] = DocAction.ACTION_Complete;
			options[index++] = DocAction.ACTION_Void;
		}else if(docStatus.equals(DocAction.STATUS_InProgress)){
			options[index++] = DocAction.ACTION_Complete;
			options[index++] = DocAction.ACTION_Void;
			
		}else if(docStatus.equals(DocAction.STATUS_Completed)){
			options[index++] = DocAction.ACTION_Close;
						
		}		
		return index;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8772179401187791326L;
	/**	Process Message 			*/
	private String		m_processMsg = null;
	/**	Just Prepared Flag			*/
	private boolean		m_justPrepared = false;
	
	@Override
	public boolean processIt (String processAction)
	{
		m_processMsg = null;
		DocumentEngine engine = new DocumentEngine (this, getDocStatus());
		return engine.processIt (processAction, getDocAction());
	}	//	processIt
	
	@Override
	public boolean unlockIt() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean invalidateIt() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public String prepareIt()
	{
		if (log.isLoggable(Level.INFO)) log.info(toString());
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
		
		
		
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
		
		m_justPrepared = true;
	//	if (!DOCACTION_Complete.equals(getDocAction()))		don't set for just prepare 
	//		setDocAction(DOCACTION_Complete);
		return DocAction.STATUS_InProgress;
	}	//	prepareIt
	@Override
	public boolean approveIt() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean rejectIt() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public String completeIt()
	{
		if (log.isLoggable(Level.INFO)) log.info(toString());
		StringBuilder info = new StringBuilder();
		
		//	Just prepare
		if (DOCACTION_Prepare.equals(getDocAction()))
		{
//			setProcessed(false);
			return DocAction.STATUS_InProgress;
		}

		// Set the definite document number after completed (if needed)
		
		
		//	Re-Check
		if (!m_justPrepared)
		{
			String status = prepareIt();
			m_justPrepared = false;
			if (!DocAction.STATUS_InProgress.equals(status))
				return status;
		}
		
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_COMPLETE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
		
		
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
		

//		setProcessed(true);	
		m_processMsg = info.toString();
		//
		setDocAction(DOCACTION_Close);
		return DocAction.STATUS_Completed;
	}	//	completeIt
	@Override
	public boolean voidIt()
	{
		if (log.isLoggable(Level.INFO)) log.info(toString());
		// Before Void
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_VOID);
		if (m_processMsg != null)
			return false;
		
		// After Void
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_VOID);
		if (m_processMsg != null)
			return false;
		setDocStatus(DOCSTATUS_Voided);
//		setProcessed(true);
		setDocAction(DOCACTION_Void);
		return true;
	}
	@Override
	public boolean closeIt()
	{
		if (log.isLoggable(Level.INFO)) log.info(toString());
		// Before Close
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_CLOSE);
		if (m_processMsg != null)
			return false;
		
		setDocAction(DOCACTION_None);
		
		// After Close
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_CLOSE);
		if (m_processMsg != null)
			return false;		
		return true;
	}	//	closeIt
	@Override
	public boolean reverseCorrectIt() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean reverseAccrualIt() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean reActivateIt() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public String getSummary() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getDocumentInfo() {
		// TODO Auto-generated method stub
		return getDocumentNo();
	}
	@Override
	public File createPDF() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getProcessMsg() {
		// TODO Auto-generated method stub
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
