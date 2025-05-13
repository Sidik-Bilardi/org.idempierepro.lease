/******************************************************************************
 * Product: iDempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2012 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.lease.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for C_OrderLineSch
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="C_OrderLineSch")
public class X_C_OrderLineSch extends PO implements I_C_OrderLineSch, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250509L;

    /** Standard Constructor */
    public X_C_OrderLineSch (Properties ctx, int C_OrderLineSch_ID, String trxName)
    {
      super (ctx, C_OrderLineSch_ID, trxName);
      /** if (C_OrderLineSch_ID == 0)
        {
			setC_OrderLineSch_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_C_OrderLineSch (Properties ctx, int C_OrderLineSch_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, C_OrderLineSch_ID, trxName, virtualColumns);
      /** if (C_OrderLineSch_ID == 0)
        {
			setC_OrderLineSch_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_C_OrderLineSch (Properties ctx, String C_OrderLineSch_UU, String trxName)
    {
      super (ctx, C_OrderLineSch_UU, trxName);
      /** if (C_OrderLineSch_UU == null)
        {
			setC_OrderLineSch_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_C_OrderLineSch (Properties ctx, String C_OrderLineSch_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, C_OrderLineSch_UU, trxName, virtualColumns);
      /** if (C_OrderLineSch_UU == null)
        {
			setC_OrderLineSch_ID (0);
        } */
    }

    /** Load Constructor */
    public X_C_OrderLineSch (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuilder sb = new StringBuilder ("X_C_OrderLineSch[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Amount.
		@param Amount Amount in a defined currency
	*/
	public void setAmount (BigDecimal Amount)
	{
		set_ValueNoCheck (COLUMNNAME_Amount, Amount);
	}

	/** Get Amount.
		@return Amount in a defined currency
	  */
	public BigDecimal getAmount()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Amount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set C_OrderLineSch.
		@param C_OrderLineSch_ID C_OrderLineSch
	*/
	public void setC_OrderLineSch_ID (int C_OrderLineSch_ID)
	{
		if (C_OrderLineSch_ID < 1)
			set_ValueNoCheck (COLUMNNAME_C_OrderLineSch_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_C_OrderLineSch_ID, Integer.valueOf(C_OrderLineSch_ID));
	}

	/** Get C_OrderLineSch.
		@return C_OrderLineSch	  */
	public int getC_OrderLineSch_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_OrderLineSch_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set C_OrderLineSch_UU.
		@param C_OrderLineSch_UU C_OrderLineSch_UU
	*/
	public void setC_OrderLineSch_UU (String C_OrderLineSch_UU)
	{
		set_Value (COLUMNNAME_C_OrderLineSch_UU, C_OrderLineSch_UU);
	}

	/** Get C_OrderLineSch_UU.
		@return C_OrderLineSch_UU	  */
	public String getC_OrderLineSch_UU()
	{
		return (String)get_Value(COLUMNNAME_C_OrderLineSch_UU);
	}

	public org.compiere.model.I_C_OrderLine getC_OrderLine() throws RuntimeException
	{
		return (org.compiere.model.I_C_OrderLine)MTable.get(getCtx(), org.compiere.model.I_C_OrderLine.Table_ID)
			.getPO(getC_OrderLine_ID(), get_TrxName());
	}

	/** Set Sales Order Line.
		@param C_OrderLine_ID Sales Order Line
	*/
	public void setC_OrderLine_ID (int C_OrderLine_ID)
	{
		if (C_OrderLine_ID < 1)
			set_ValueNoCheck (COLUMNNAME_C_OrderLine_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_C_OrderLine_ID, Integer.valueOf(C_OrderLine_ID));
	}

	/** Get Sales Order Line.
		@return Sales Order Line
	  */
	public int getC_OrderLine_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_OrderLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date Start.
		@param DateStart Date Start for this Order
	*/
	public void setDateStart (Timestamp DateStart)
	{
		set_ValueNoCheck (COLUMNNAME_DateStart, DateStart);
	}

	/** Get Date Start.
		@return Date Start for this Order
	  */
	public Timestamp getDateStart()
	{
		return (Timestamp)get_Value(COLUMNNAME_DateStart);
	}

	/** Set Transaction Date.
		@param DateTrx Transaction Date
	*/
	public void setDateTrx (Timestamp DateTrx)
	{
		set_ValueNoCheck (COLUMNNAME_DateTrx, DateTrx);
	}

	/** Get Transaction Date.
		@return Transaction Date
	  */
	public Timestamp getDateTrx()
	{
		return (Timestamp)get_Value(COLUMNNAME_DateTrx);
	}

	/** Set Description.
		@param Description Optional short description of the record
	*/
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription()
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set End Date.
		@param EndDate Last effective date (inclusive)
	*/
	public void setEndDate (Timestamp EndDate)
	{
		set_Value (COLUMNNAME_EndDate, EndDate);
	}

	/** Get End Date.
		@return Last effective date (inclusive)
	  */
	public Timestamp getEndDate()
	{
		return (Timestamp)get_Value(COLUMNNAME_EndDate);
	}

	/** Set Info.
		@param Info Information
	*/
	public void setInfo (String Info)
	{
		set_Value (COLUMNNAME_Info, Info);
	}

	/** Get Info.
		@return Information
	  */
	public String getInfo()
	{
		return (String)get_Value(COLUMNNAME_Info);
	}

	/** Set Tax Amount.
		@param TaxAmt Tax Amount for a document
	*/
	public void setTaxAmt (BigDecimal TaxAmt)
	{
		set_ValueNoCheck (COLUMNNAME_TaxAmt, TaxAmt);
	}

	/** Get Tax Amount.
		@return Tax Amount for a document
	  */
	public BigDecimal getTaxAmt()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TaxAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Total Amount.
		@param TotalAmt Total Amount
	*/
	public void setTotalAmt (BigDecimal TotalAmt)
	{
		set_ValueNoCheck (COLUMNNAME_TotalAmt, TotalAmt);
	}

	/** Get Total Amount.
		@return Total Amount
	  */
	public BigDecimal getTotalAmt()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}