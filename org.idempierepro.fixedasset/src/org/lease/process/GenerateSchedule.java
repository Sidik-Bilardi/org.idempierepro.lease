package org.lease.process;

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

public class GenerateSchedule extends SvrProcess{
	int p_C_OrderLine_ID = 0;
	
	@Override
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("C_OrderLine_ID"))
				p_C_OrderLine_ID = (int)para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		
	}	//	prepare
	@Override
	protected String doIt() throws Exception {
		
		return null;
	}
}
