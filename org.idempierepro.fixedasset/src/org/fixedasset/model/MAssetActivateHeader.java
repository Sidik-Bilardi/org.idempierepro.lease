package org.fixedasset.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAssetAddition;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.Env;

public class MAssetActivateHeader extends X_A_Asset_Activate_Header implements DocAction{

	public MAssetActivateHeader(Properties ctx, int A_Asset_Activate_Header_ID, String trxName) {
		super(ctx, A_Asset_Activate_Header_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public MAssetActivateHeader(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
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
		
		List<MAssetAddition> assets = new Query(getCtx(), MAssetAddition.Table_Name, "A_Asset_Activate_Header_ID=?",get_TrxName())
				.setParameters(getA_Asset_Activate_Header_ID())
				.list();
				
		if(assets.isEmpty()){
			m_processMsg = "No Asset for Activated";
			return DocAction.STATUS_Invalid;
		}
		
		for(MAssetAddition asset : assets){
			if(asset.getAssetValueAmt().compareTo(Env.ZERO) == 0){
//				asset.setAssetAmtEntered(new BigDecimal(asset.getDeltaUseLifeYears()));
//				asset.setAssetValueAmt(new BigDecimal(asset.getDeltaUseLifeYears()));
				asset.saveEx(get_TrxName());
			}
			
			if(asset.getAssetValueAmt().compareTo(Env.ZERO) <= 0){
				throw new IllegalStateException("Invalid, Asset Amount < 0");
			}
		}
		
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
			setProcessed(false);
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
		
		List<MAssetAddition> assets = new Query(getCtx(), MAssetAddition.Table_Name, "A_Asset_Activate_Header_ID=?",get_TrxName())
				.setParameters(getA_Asset_Activate_Header_ID())
				.list();
		
		for(MAssetAddition asset : assets){
//			asset.completeIt();
			asset.processIt(DocAction.ACTION_Complete);
			
		}
		
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
		

		setProcessed(true);	
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
		setProcessed(true);
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
