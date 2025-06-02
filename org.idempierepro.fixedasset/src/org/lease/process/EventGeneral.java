package org.lease.process;

import org.compiere.model.MAsset;
import org.compiere.model.Query;
import org.lease.model.X_C_OrderComponent;
import org.lease.model.X_C_OrderLineSch;

public class EventGeneral {
	public static String CreateComponentSchedule(X_C_OrderComponent comp){
		String msg = "";
		X_C_OrderLineSch sch = new X_C_OrderLineSch(comp.getCtx(), 0, comp.get_TrxName());
		sch.set_ValueOfColumn("C_OrderComponent_ID", comp.get_ID());
		sch.setDateStart(comp.getDateStart());
		sch.setEndDate(comp.getEndDate());
		sch.set_ValueOfColumn("Rate", comp.get_Value("Rate"));
		sch.saveEx();
		return msg;
	}
}
