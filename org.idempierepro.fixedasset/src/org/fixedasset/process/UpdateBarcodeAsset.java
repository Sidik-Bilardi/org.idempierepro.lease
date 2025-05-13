package org.fixedasset.process;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.logging.Level;

import org.compiere.model.MAsset;
import org.compiere.model.MSequence;
import org.compiere.model.Query;
import org.compiere.model.X_AD_Sequence_No;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.compiere.util.Util;

public class UpdateBarcodeAsset extends SvrProcess{
	int p_A_Asset_ID = 0;
	
	@Override
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("A_Asset_ID"))
				p_A_Asset_ID = (int)para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		
	}	//	prepare
	@Override
	protected String doIt() throws Exception {
		MAsset asset = new MAsset(Env.getCtx(), p_A_Asset_ID, get_TrxName());
		if (asset.get_Value("Value") != null){
			MSequence mSequence = new MSequence(Env.getCtx(), 1001708, get_TrxName());
			String Year = asset.getA_Asset_CreateDate().toString();
			String incPattern = "";
			String[] YearEx = Year.split("-");
			String sqlListSeq = " AD_Sequence_ID = ?  AND CalendarYearMonth like ? ";
			X_AD_Sequence_No listSequence = new Query(Env.getCtx(), X_AD_Sequence_No.Table_Name, sqlListSeq, get_TrxName())
													.setOnlyActiveRecords(true)
													.setParameters(mSequence.get_ID(), YearEx[0] )
													.first();
			if ( listSequence == null){
				X_AD_Sequence_No newSequence = new X_AD_Sequence_No(Env.getCtx(), 0, get_TrxName());
				newSequence.setAD_Sequence_ID(mSequence.get_ID());
				newSequence.setCalendarYearMonth(YearEx[0]);
				newSequence.setCurrentNext(2);
				newSequence.saveEx();	
				try {
					incPattern = new DecimalFormat(mSequence.getDecimalPattern()).format(1);
						
				} catch (Exception e) {
					incPattern = new DecimalFormat(mSequence.getDecimalPattern()).format(1);
				}
				
			}else{
				Integer sequenceNo;
				try {
					sequenceNo = Integer.valueOf(listSequence.getCurrentNext());
					if ( sequenceNo > 0){
						incPattern = new DecimalFormat(mSequence.getDecimalPattern()).format(sequenceNo);
					}
						
				} catch (Exception e) {
					incPattern = new DecimalFormat(mSequence.getDecimalPattern()).format(1);
				}
				StringBuilder doc = new StringBuilder();
				String prefix = mSequence.getPrefix();
				String suffix = mSequence.getSuffix();
				if (prefix != null && prefix.length() > 0) {
					String prefixValue = Env.parseVariable(prefix, asset, get_TrxName(), false);
					if (!Util.isEmpty(prefixValue))
						doc.append(prefixValue);
				}
				if (incPattern != null && incPattern.length() > 0)
					doc.append(incPattern);
				
				if (suffix != null && suffix.length() > 0) {
					String suffixValue = Env.parseVariable(suffix, asset, get_TrxName(), false);
					if (!Util.isEmpty(suffixValue))
						doc.append(suffixValue);
				}

				String documentNo = doc.toString();
				asset.setValue(documentNo);
				asset.saveEx();
				listSequence.setCurrentNext(listSequence.getCurrentNext()+1);
				listSequence.saveEx();
			}
		}
		return null;
	}
}
