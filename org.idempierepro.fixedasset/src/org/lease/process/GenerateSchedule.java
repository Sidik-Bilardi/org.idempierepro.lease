package org.lease.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.util.logging.Level;

import org.compiere.model.MAsset;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MSequence;
import org.compiere.model.Query;
import org.compiere.model.X_AD_Sequence_No;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.lease.model.X_C_OrderComponent;

public class GenerateSchedule extends SvrProcess{
	int p_ID = 0;
	
	@Override
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
//			else if (name.equals("C_OrderLine_ID"))
//				p_C_OrderLine_ID = (int)para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		p_ID = getRecord_ID();
		
	}	//	prepare
	@Override
	protected String doIt() throws Exception {
		String text = "";
		int count = 0;
		X_C_OrderComponent comp = new X_C_OrderComponent(getCtx(), p_ID, get_TrxName());
		MOrder order = new MOrder(getCtx(), comp.getC_Order_ID(), get_TrxName());
		BigDecimal AreaSize = (BigDecimal) order.get_Value("AreaSize");
		String prdName = comp.getM_Product().getName();
		Timestamp dateStart = comp.getDateStart();
		Timestamp dateEnd = comp.getEndDate();
		LocalDate Start = dateStart.toLocalDateTime().toLocalDate();
		LocalDate End = dateEnd.toLocalDateTime().toLocalDate();
		LocalDate EndLoop = null;
		for (LocalDate date = Start; date.isBefore(End); date = date.plusMonths(1)) {
			MOrderLine line = new MOrderLine(getCtx(), 0, get_TrxName());
			line.setC_Order_ID(order.get_ID());
	        line.setM_Product_ID(comp.getM_Product_ID());
	        line.setQty(Env.ONE);
	        
	        LocalDate firstDay = date;
	        if(count > 0 ) {
	        	 firstDay= date.withDayOfMonth(1);
	        }
	        LocalDate lastDay= firstDay.withDayOfMonth(
	        		firstDay.getMonth().length(firstDay.isLeapYear()));
	        Period diff = Period.between(firstDay, lastDay);
	        long BillingDays = ChronoUnit.DAYS.between(firstDay, lastDay);   // inclusive
	        if(count > 0 ) {
	        	BillingDays = BillingDays + 1;
	        }
	        YearMonth yearMonthEnd = YearMonth.of(End.getYear(), End.getMonth());
	        
	        YearMonth yearMonthObject = YearMonth.of(lastDay.getYear(), lastDay.getMonth());
	        int daysInMonth = yearMonthObject.lengthOfMonth(); //28 
	       
	        if (yearMonthEnd.equals(yearMonthObject)) {
	        	lastDay = End;
	        	BillingDays = ChronoUnit.DAYS.between(firstDay, lastDay) + 1;
	         }
	        String desc = "Installment "+prdName+" "+firstDay+" s/d "+lastDay+" ("+BillingDays+"/"+daysInMonth+") ";
	        line.setDescription(desc);
	        Timestamp tsLastDay = Timestamp.valueOf(lastDay.atStartOfDay());
	        Timestamp tsfirstDay = Timestamp.valueOf(firstDay.atStartOfDay());
	        BigDecimal rate = (BigDecimal) comp.get_Value("Rate");
	        BigDecimal ActualDays = new BigDecimal(BillingDays);
	        BigDecimal MonthDays = new BigDecimal(daysInMonth);
			BigDecimal priceentered = (rate.multiply(AreaSize)).multiply(ActualDays.divide(MonthDays, 20, RoundingMode.HALF_UP));
			line.setPrice(priceentered);
	        line.set_ValueOfColumn("EndDate", tsLastDay);
	        line.set_ValueOfColumn("DateStart", tsfirstDay);
	        line.set_ValueOfColumn("PlannedQty", new BigDecimal(daysInMonth));
	        line.set_ValueOfColumn("ActualQty", new BigDecimal(BillingDays));
	        if(line.save()) {
	        	count++;
	        }
	        EndLoop = lastDay;
	    }
		
		if(EndLoop.isBefore(End)) {
			MOrderLine line = new MOrderLine(getCtx(), 0, get_TrxName());
	        line.setC_Order_ID(order.get_ID());
	        line.setM_Product_ID(comp.getM_Product_ID());
	        line.setQty(Env.ONE);
	        line.setPrice(Env.ONE);
	        LocalDate firstDay = End.withDayOfMonth(1);
	        YearMonth yearMonthEnd = YearMonth.of(End.getYear(), End.getMonth());
	        int daysInMonth = yearMonthEnd.lengthOfMonth(); //28 
	        BigDecimal rate = (BigDecimal) comp.get_Value("Rate");
	        
	        long BillingDays = ChronoUnit.DAYS.between(EndLoop, End);   // inclusive
	        String desc = "Installment "+prdName+" "+firstDay+" - "+End+" ("+BillingDays+"/"+daysInMonth+") ";
	        line.setDescription(desc);
	        BigDecimal ActualDays = new BigDecimal(BillingDays);
	        BigDecimal MonthDays = new BigDecimal(daysInMonth);
			
	        Timestamp tsfirstDay = Timestamp.valueOf(firstDay.atStartOfDay());
	        Timestamp tsEndDay = Timestamp.valueOf(End.atStartOfDay());
	        BigDecimal priceentered = (rate.multiply(AreaSize)).multiply(ActualDays.divide(MonthDays, 20, RoundingMode.HALF_UP));
	        line.setPrice(priceentered);
	        line.set_ValueOfColumn("EndDate", tsEndDay);
	        line.set_ValueOfColumn("DateStart", tsfirstDay);
	        line.set_ValueOfColumn("PlannedQty", new BigDecimal(daysInMonth));
	        line.set_ValueOfColumn("ActualQty", new BigDecimal(BillingDays));
	        if(line.save()) {
	        	count++;
	        }
		}
		
		return "#"+count+" Generated ";
	}
}
