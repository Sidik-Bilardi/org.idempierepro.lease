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

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for C_OrderComponent
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="C_OrderComponent")
public class X_C_OrderComponent extends PO implements I_C_OrderComponent, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250514L;

    /** Standard Constructor */
    public X_C_OrderComponent (Properties ctx, int C_OrderComponent_ID, String trxName)
    {
      super (ctx, C_OrderComponent_ID, trxName);
      /** if (C_OrderComponent_ID == 0)
        {
			setC_OrderComponent_ID (0);
			setDateTrx (new Timestamp( System.currentTimeMillis() ));
// @#Date@
        } */
    }

    /** Standard Constructor */
    public X_C_OrderComponent (Properties ctx, int C_OrderComponent_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, C_OrderComponent_ID, trxName, virtualColumns);
      /** if (C_OrderComponent_ID == 0)
        {
			setC_OrderComponent_ID (0);
			setDateTrx (new Timestamp( System.currentTimeMillis() ));
// @#Date@
        } */
    }

    /** Standard Constructor */
    public X_C_OrderComponent (Properties ctx, String C_OrderComponent_UU, String trxName)
    {
      super (ctx, C_OrderComponent_UU, trxName);
      /** if (C_OrderComponent_UU == null)
        {
			setC_OrderComponent_ID (0);
			setDateTrx (new Timestamp( System.currentTimeMillis() ));
// @#Date@
        } */
    }

    /** Standard Constructor */
    public X_C_OrderComponent (Properties ctx, String C_OrderComponent_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, C_OrderComponent_UU, trxName, virtualColumns);
      /** if (C_OrderComponent_UU == null)
        {
			setC_OrderComponent_ID (0);
			setDateTrx (new Timestamp( System.currentTimeMillis() ));
// @#Date@
        } */
    }

    /** Load Constructor */
    public X_C_OrderComponent (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_C_OrderComponent[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set C_OrderComponent.
		@param C_OrderComponent_ID C_OrderComponent
	*/
	public void setC_OrderComponent_ID (int C_OrderComponent_ID)
	{
		if (C_OrderComponent_ID < 1)
			set_ValueNoCheck (COLUMNNAME_C_OrderComponent_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_C_OrderComponent_ID, Integer.valueOf(C_OrderComponent_ID));
	}

	/** Get C_OrderComponent.
		@return C_OrderComponent	  */
	public int getC_OrderComponent_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_OrderComponent_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set C_OrderComponent_UU.
		@param C_OrderComponent_UU C_OrderComponent_UU
	*/
	public void setC_OrderComponent_UU (String C_OrderComponent_UU)
	{
		set_Value (COLUMNNAME_C_OrderComponent_UU, C_OrderComponent_UU);
	}

	/** Get C_OrderComponent_UU.
		@return C_OrderComponent_UU	  */
	public String getC_OrderComponent_UU()
	{
		return (String)get_Value(COLUMNNAME_C_OrderComponent_UU);
	}

	public org.compiere.model.I_C_Order getC_Order() throws RuntimeException
	{
		return (org.compiere.model.I_C_Order)MTable.get(getCtx(), org.compiere.model.I_C_Order.Table_ID)
			.getPO(getC_Order_ID(), get_TrxName());
	}

	/** Set Order.
		@param C_Order_ID Order
	*/
	public void setC_Order_ID (int C_Order_ID)
	{
		if (C_Order_ID < 1)
			set_ValueNoCheck (COLUMNNAME_C_Order_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_C_Order_ID, Integer.valueOf(C_Order_ID));
	}

	/** Get Order.
		@return Order
	  */
	public int getC_Order_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Order_ID);
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
		set_Value (COLUMNNAME_DateTrx, DateTrx);
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

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
	{
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_ID)
			.getPO(getM_Product_ID(), get_TrxName());
	}

	/** Set Product.
		@param M_Product_ID Product, Service, Item
	*/
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1)
			set_Value (COLUMNNAME_M_Product_ID, null);
		else
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}