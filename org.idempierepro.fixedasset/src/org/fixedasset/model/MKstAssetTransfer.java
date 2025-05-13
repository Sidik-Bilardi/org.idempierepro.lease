package org.fixedasset.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MAsset;
import org.compiere.model.Query;
import org.compiere.print.ReportEngine;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.Env;
import org.compiere.util.Msg;

public class MKstAssetTransfer extends X_A_AssetTransfer implements DocAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1330458930636531381L;
	/**	Process Message 			*/
	protected String		m_processMsg = null;
	/**	Just Prepared Flag			*/
	protected boolean		m_justPrepared = false;
	protected MKstAssetTransferLine[] 	m_lines = null;
	
	public MKstAssetTransfer(Properties ctx, int A_AssetTransfer_Line_ID, String trxName) {
		super(ctx, A_AssetTransfer_Line_ID, trxName);
	}

	public MKstAssetTransfer(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	

	@Override
	public boolean processIt(String action) throws Exception {
		m_processMsg = null;
		DocumentEngine engine = new DocumentEngine (this, getDocStatus());
		return engine.processIt (action, getDocAction());
	}

	@Override
	public boolean unlockIt() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean invalidateIt() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String prepareIt() {
		m_justPrepared = true;
		if (!DOCACTION_Complete.equals(getDocAction()))
			setDocAction(DOCACTION_Complete);
		return DocAction.STATUS_InProgress;
	}

	@Override
	public boolean approveIt() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean rejectIt() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String completeIt() {
		for (MKstAssetTransferLine line:getLines()){
			MAsset asset = new MAsset(line.getCtx(), line.getA_Asset_ID(), line.get_TrxName());
			asset.setM_Locator_ID(line.getM_LocatorTo_ID());
			asset.setC_BPartner_ID(line.getC_BPartner_ID());
			asset.set_ValueOfColumn("C_Activity_ID", line.get_Value("C_ActivityTo_ID"));
			asset.set_ValueOfColumn("C_SalesRegion_ID", line.get_Value("C_SaleRregionto_ID"));
			asset.setProcessed(true);
//			String value = DB.getDocumentNo(asset.getAD_Client_ID(), MAsset.Table_Name, asset.get_TrxName(), asset);
//			asset.setValue(value);
			
			asset.saveEx();
			
			line.set_ValueOfColumn("Processed", true);
			line.saveEx();
		}
		setProcessed(true);
		return DocAction.STATUS_Completed;
	}

	@Override
	public boolean voidIt() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean closeIt() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean reverseCorrectIt() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean reverseAccrualIt() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean reActivateIt() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getSummary() {
		StringBuilder sb = new StringBuilder();
		sb.append(getDocumentNo());
		
		return sb.toString();
	}


	@Override
	public String getDocumentInfo() {
		return Msg.getElement(getCtx(), "A_AssetTransfer") + " " + getDocumentNo();
	}

	@Override
	public File createPDF() {
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
	}
	
	public static final int		ASSETTRANSFER = 15;
	
	/**
	 * 	Create PDF file
	 *	@param file output file
	 *	@return file if success
	 */
	public File createPDF (File file)
	{
		ReportEngine re = ReportEngine.get (getCtx(), ASSETTRANSFER, getA_AssetTransfer_ID(),get_TrxName());
		if (re == null)
			return null;
		return re.getPDF(file);
	}	//	createPDF

	@Override
	public String getProcessMsg() {
		// TODO Auto-generated method stub
		return m_processMsg;
	}

	@Override
	public int getDoc_User_ID() {
		return getCreatedBy();
	}

	@Override
	public int getC_Currency_ID() {
		// TODO Auto-generated method stub
		return 303;
	}

	@Override
	public BigDecimal getApprovalAmt() {
		// TODO Auto-generated method stub
		return Env.ZERO;
	}

	public MKstAssetTransferLine[] getLines(){
		List<MKstAssetTransferLine> list = new Query(getCtx(), MKstAssetTransferLine.Table_Name, 
				MKstAssetTransferLine.COLUMNNAME_A_AssetTransfer_ID + "=? ", get_TrxName())
				.setParameters(get_ID())
				.list();
		return list.toArray(new MKstAssetTransferLine[list.size()]);		
	}
}
