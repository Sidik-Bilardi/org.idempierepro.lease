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
package org.fixedasset.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.I_M_Locator;
import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;

/** Generated Model for A_AssetTransfer_Line
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_A_AssetTransfer_Line extends PO implements I_A_AssetTransfer_Line, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180724L;

    /** Standard Constructor */
    public X_A_AssetTransfer_Line (Properties ctx, int A_AssetTransfer_Line_ID, String trxName)
    {
      super (ctx, A_AssetTransfer_Line_ID, trxName);
      /** if (A_AssetTransfer_Line_ID == 0)
        {
        } */
    }

    /** Load Constructor */
    public X_A_AssetTransfer_Line (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_A_AssetTransfer_Line[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_A_Asset getA_Asset() throws RuntimeException
    {
		return (org.compiere.model.I_A_Asset)MTable.get(getCtx(), org.compiere.model.I_A_Asset.Table_Name)
			.getPO(getA_Asset_ID(), get_TrxName());	}

	/** Set Asset.
		@param A_Asset_ID 
		Asset used internally or by customers
	  */
	public void setA_Asset_ID (int A_Asset_ID)
	{
		if (A_Asset_ID < 1) 
			set_Value (COLUMNNAME_A_Asset_ID, null);
		else 
			set_Value (COLUMNNAME_A_Asset_ID, Integer.valueOf(A_Asset_ID));
	}

	/** Get Asset.
		@return Asset used internally or by customers
	  */
	public int getA_Asset_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_A_AssetTransfer getA_AssetTransfer() throws RuntimeException
    {
		return (I_A_AssetTransfer)MTable.get(getCtx(), I_A_AssetTransfer.Table_Name)
			.getPO(getA_AssetTransfer_ID(), get_TrxName());	}

	/** Set A_AssetTransfer_ID.
		@param A_AssetTransfer_ID A_AssetTransfer_ID	  */
	public void setA_AssetTransfer_ID (int A_AssetTransfer_ID)
	{
		if (A_AssetTransfer_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_A_AssetTransfer_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_A_AssetTransfer_ID, Integer.valueOf(A_AssetTransfer_ID));
	}

	/** Get A_AssetTransfer_ID.
		@return A_AssetTransfer_ID	  */
	public int getA_AssetTransfer_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_AssetTransfer_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set A_AssetTransfer_Line_ID.
		@param A_AssetTransfer_Line_ID A_AssetTransfer_Line_ID	  */
	public void setA_AssetTransfer_Line_ID (int A_AssetTransfer_Line_ID)
	{
		if (A_AssetTransfer_Line_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_A_AssetTransfer_Line_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_A_AssetTransfer_Line_ID, Integer.valueOf(A_AssetTransfer_Line_ID));
	}

	/** Get A_AssetTransfer_Line_ID.
		@return A_AssetTransfer_Line_ID	  */
	public int getA_AssetTransfer_Line_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_AssetTransfer_Line_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set A_AssetTransfer_Line_UU.
		@param A_AssetTransfer_Line_UU A_AssetTransfer_Line_UU	  */
	public void setA_AssetTransfer_Line_UU (String A_AssetTransfer_Line_UU)
	{
		set_ValueNoCheck (COLUMNNAME_A_AssetTransfer_Line_UU, A_AssetTransfer_Line_UU);
	}

	/** Get A_AssetTransfer_Line_UU.
		@return A_AssetTransfer_Line_UU	  */
	public String getA_AssetTransfer_Line_UU () 
	{
		return (String)get_Value(COLUMNNAME_A_AssetTransfer_Line_UU);
	}

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_BPartner getC_BPartnerSR() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartnerSR_ID(), get_TrxName());	}

	/** Set BPartner (Agent).
		@param C_BPartnerSR_ID 
		Business Partner (Agent or Sales Rep)
	  */
	public void setC_BPartnerSR_ID (int C_BPartnerSR_ID)
	{
		if (C_BPartnerSR_ID < 1) 
			set_Value (COLUMNNAME_C_BPartnerSR_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartnerSR_ID, Integer.valueOf(C_BPartnerSR_ID));
	}

	/** Get BPartner (Agent).
		@return Business Partner (Agent or Sales Rep)
	  */
	public int getC_BPartnerSR_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartnerSR_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Locator getM_Locator() throws RuntimeException
    {
		return (I_M_Locator)MTable.get(getCtx(), I_M_Locator.Table_Name)
			.getPO(getM_Locator_ID(), get_TrxName());	}

	/** Set Locator.
		@param M_Locator_ID 
		Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID)
	{
		if (M_Locator_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Locator_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Locator_ID, Integer.valueOf(M_Locator_ID));
	}

	/** Get Locator.
		@return Warehouse Locator
	  */
	public int getM_Locator_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Locator_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Locator getM_LocatorTo() throws RuntimeException
    {
		return (I_M_Locator)MTable.get(getCtx(), I_M_Locator.Table_Name)
			.getPO(getM_LocatorTo_ID(), get_TrxName());	}

	/** Set Locator To.
		@param M_LocatorTo_ID 
		Location inventory is moved to
	  */
	public void setM_LocatorTo_ID (int M_LocatorTo_ID)
	{
		if (M_LocatorTo_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_LocatorTo_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_LocatorTo_ID, Integer.valueOf(M_LocatorTo_ID));
	}

	/** Get Locator To.
		@return Location inventory is moved to
	  */
	public int getM_LocatorTo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_LocatorTo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}