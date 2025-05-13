package org.fixedasset.process;

import org.compiere.model.MAsset;
import org.compiere.model.Query;

public class EventGeneral {
	public static String ValidateAssetCode(MAsset ast){
		String msg = "";
		MAsset exist = new Query(ast.getCtx(), MAsset.Table_Name, " A_Asset_ID != ? AND Value = ?  ", ast.get_TrxName())
				.setOnlyActiveRecords(true)
				.setParameters(ast.get_ID(),ast.getValue())
				.first();
		if(exist != null)
			msg = "Barcode need to be Unique";
		return msg;
	}
}
