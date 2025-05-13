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
package org.fixedasset.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.compiere.model.I_M_Locator;
import org.compiere.model.MTable;
import org.compiere.util.KeyNamePair;

/** Generated Interface for A_AssetTransfer_Line
 *  @author iDempiere (generated) 
 *  @version Release 4.1
 */
@SuppressWarnings("all")
public interface I_A_AssetTransfer_Line 
{

    /** TableName=A_AssetTransfer_Line */
    public static final String Table_Name = "A_AssetTransfer_Line";

    /** AD_Table_ID=1000084 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

    /** Load Meta Data */

    /** Column name A_Asset_ID */
    public static final String COLUMNNAME_A_Asset_ID = "A_Asset_ID";

	/** Set Asset.
	  * Asset used internally or by customers
	  */
	public void setA_Asset_ID (int A_Asset_ID);

	/** Get Asset.
	  * Asset used internally or by customers
	  */
	public int getA_Asset_ID();

	public org.compiere.model.I_A_Asset getA_Asset() throws RuntimeException;

    /** Column name A_AssetTransfer_ID */
    public static final String COLUMNNAME_A_AssetTransfer_ID = "A_AssetTransfer_ID";

	/** Set A_AssetTransfer_ID	  */
	public void setA_AssetTransfer_ID (int A_AssetTransfer_ID);

	/** Get A_AssetTransfer_ID	  */
	public int getA_AssetTransfer_ID();

	public I_A_AssetTransfer getA_AssetTransfer() throws RuntimeException;

    /** Column name A_AssetTransfer_Line_ID */
    public static final String COLUMNNAME_A_AssetTransfer_Line_ID = "A_AssetTransfer_Line_ID";

	/** Set A_AssetTransfer_Line_ID	  */
	public void setA_AssetTransfer_Line_ID (int A_AssetTransfer_Line_ID);

	/** Get A_AssetTransfer_Line_ID	  */
	public int getA_AssetTransfer_Line_ID();

    /** Column name A_AssetTransfer_Line_UU */
    public static final String COLUMNNAME_A_AssetTransfer_Line_UU = "A_AssetTransfer_Line_UU";

	/** Set A_AssetTransfer_Line_UU	  */
	public void setA_AssetTransfer_Line_UU (String A_AssetTransfer_Line_UU);

	/** Get A_AssetTransfer_Line_UU	  */
	public String getA_AssetTransfer_Line_UU();

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Business Unit.
	  * Business Unit entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Business Unit.
	  * Business Unit entity within client
	  */
	public int getAD_Org_ID();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_BPartnerSR_ID */
    public static final String COLUMNNAME_C_BPartnerSR_ID = "C_BPartnerSR_ID";

	/** Set BPartner (Agent).
	  * Business Partner (Agent or Sales Rep)
	  */
	public void setC_BPartnerSR_ID (int C_BPartnerSR_ID);

	/** Get BPartner (Agent).
	  * Business Partner (Agent or Sales Rep)
	  */
	public int getC_BPartnerSR_ID();

	public org.compiere.model.I_C_BPartner getC_BPartnerSR() throws RuntimeException;

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name M_Locator_ID */
    public static final String COLUMNNAME_M_Locator_ID = "M_Locator_ID";

	/** Set Locator.
	  * Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID);

	/** Get Locator.
	  * Warehouse Locator
	  */
	public int getM_Locator_ID();

	public I_M_Locator getM_Locator() throws RuntimeException;

    /** Column name M_LocatorTo_ID */
    public static final String COLUMNNAME_M_LocatorTo_ID = "M_LocatorTo_ID";

	/** Set Locator To.
	  * Location inventory is moved to
	  */
	public void setM_LocatorTo_ID (int M_LocatorTo_ID);

	/** Get Locator To.
	  * Location inventory is moved to
	  */
	public int getM_LocatorTo_ID();

	public I_M_Locator getM_LocatorTo() throws RuntimeException;

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();
}
