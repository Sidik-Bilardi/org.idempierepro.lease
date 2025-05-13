/******************************************************************************
 * Copyright (C) 2009 Low Heng Sin                                            *
 * Copyright (C) 2009 Idalica Corporation                                     *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package org.fixedasset.form;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;

import org.compiere.apps.IStatusBar;
import org.compiere.grid.CreateFrom;
import org.compiere.minigrid.IMiniTable;
import org.compiere.model.GridTab;
import org.compiere.model.MAsset;
import org.compiere.model.MAssetAddition;
import org.compiere.model.MAssetDisposed;
import org.compiere.model.MTax;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.fixedasset.model.MKstAssetDisposedHeader;
import org.fixedasset.model.X_A_Asset_Activate_Header;

/**
 *  Create Asset Disposal from Asset
 *
 *  @author Zuhri Utama
 */
public abstract class CreateFromAssetActive extends CreateFrom
{
	protected int C_BPartner_ID = 0;
	protected int C_SalesRegion_ID = 0;
	protected int C_Project_ID = 0;
	protected int C_Activity_ID = 0;
	protected String Barcode = "";
	
	/**
	 *  Protected Constructor
	 *  @param mTab MTab
	 */
	public CreateFromAssetActive(GridTab mTab)
	{
		super(mTab);
		if (log.isLoggable(Level.INFO)) log.info(mTab.toString());
	}   //  VCreateFromInvoice

	/**
	 *  Dynamic Init
	 *  @return true if initialized
	 */
	public boolean dynInit() throws Exception
	{
		log.config("");
		setTitle(Msg.getElement(Env.getCtx(), "A_Asset_Disposed_Header", false) + " .. " + Msg.translate(Env.getCtx(), "CreateFrom"));

		return true;
	}   //  dynInit	
	
	protected ArrayList<KeyNamePair> loadData (String table)
	{
		ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();

		StringBuffer sql = new StringBuffer("SELECT s."+table+"_ID,s.Name")
			.append(" FROM "+table+" s ")
			.append(" WHERE s.AD_Client_ID=? ");
			sql.append(" ORDER BY s.Name");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, Env.getAD_Client_ID(Env.getCtx()));
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				list.add(new KeyNamePair(rs.getInt(1), rs.getString(2)));
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return list;
	}

	/**
	 * Load RMA details
	 * @param M_RMA_ID RMA
	 */
	protected Vector<Vector<Object>> getAssetData()
	{
	    p_order = null;

	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    StringBuilder sqlStmt = new StringBuilder();
	    sqlStmt.append("SELECT a.A_Asset_ID,a.value,a.name, bp.name, sr.name, pro.name,act.name ");
	    sqlStmt.append("FROM A_Asset a ");
	    sqlStmt.append(" join A_Asset_Addition addt on addt.A_Asset_id=a.A_Asset_id ");
	    sqlStmt.append("LEFT JOIN C_BPartner bp ON a.C_BPartner_ID=bp.C_BPartner_ID ");
	    sqlStmt.append("LEFT JOIN C_SalesRegion sr ON a.C_SalesRegion_ID=sr.C_SalesRegion_ID ");
	    sqlStmt.append("LEFT JOIN C_Project pro ON a.C_Project_ID=pro.C_Project_ID ");
	    sqlStmt.append("LEFT JOIN C_Activity act ON a.C_Activity_ID=act.C_Activity_ID ");
	    sqlStmt.append("WHERE a.isactive='Y' and a_asset_status='NW'");
	    sqlStmt.append("AND a.A_Asset_ID NOT IN (SELECT A_Asset_ID FROM A_Asset_Disposed) ");
	    sqlStmt.append("AND a.AD_Client_ID=? ");
	    
	    if(C_BPartner_ID>0)
	    	sqlStmt.append(" AND a.C_BPartnerSR_ID=? ");
	    if(C_SalesRegion_ID>0)
	    	sqlStmt.append(" AND a.C_SalesRegion_ID=? ");
	    if(C_Project_ID>0)
	    	sqlStmt.append(" AND a.C_Project_ID=? ");
	    if(C_Activity_ID>0)
	    	sqlStmt.append(" AND a.C_Activity_ID=? ");
	    if(Barcode.length()>0)
	    	sqlStmt.append(" AND a.value like ? ");
	    
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try
	    {
	        pstmt = DB.prepareStatement(sqlStmt.toString(), null);
		    int no_param = 1;
	        pstmt.setInt(no_param++, Env.getAD_Client_ID(Env.getCtx()));
	        if(C_BPartner_ID>0)
	        	pstmt.setInt(no_param++, C_BPartner_ID);
	        if(C_SalesRegion_ID>0)
	        	pstmt.setInt(no_param++, C_SalesRegion_ID);
	        if(C_Project_ID>0)
	        	pstmt.setInt(no_param++, C_Project_ID);
	        if(C_Activity_ID>0)
	        	pstmt.setInt(no_param++, C_Activity_ID);
	        if(Barcode.length()>0)
	        	pstmt.setString(no_param++, Barcode);
	        rs = pstmt.executeQuery();

	        while (rs.next())
            {
	            Vector<Object> line = new Vector<Object>(7);
	            line.add(new Boolean(false));   // 0-Selection
	            KeyNamePair pp = new KeyNamePair(rs.getInt(1), rs.getString(2)); // 1-2 barcode
	            line.add(pp);
	            line.add(rs.getString(3)); // 3-name
	            line.add(rs.getString(4)); // 4-bp
	            line.add(rs.getString(5)); // 5-branch
	            line.add(rs.getString(6)); // 6-cost center
	            line.add(rs.getString(7)); // 7-department
	            data.add(line);
            }
	    }
	    catch (Exception ex)
	    {
	        log.log(Level.SEVERE, sqlStmt.toString(), ex);
	    } 
	    finally
	    {
	    	DB.close(rs, pstmt);
	    	rs = null; pstmt = null;
	    }

	    return data;
	}

	/**
	 *  List number of rows selected
	 */
	public void info(IMiniTable miniTable, IStatusBar statusBar)
	{

	}   //  infoInvoice

	protected void configureMiniTable (IMiniTable miniTable)
	{
		miniTable.setColumnClass(0, Boolean.class, false);      //  0-Selection
		miniTable.setColumnClass(1, String.class, true);        //  1-Barcode
		miniTable.setColumnClass(2, String.class, true);        //  2-Asset
		miniTable.setColumnClass(3, String.class, true);        //  3-BPartner
		miniTable.setColumnClass(4, String.class, true);        //  4-Branch
		miniTable.setColumnClass(5, String.class, true);        //  5-Cost Center
		miniTable.setColumnClass(6, String.class, true);        //  6-Department
		//  Table UI
		miniTable.autoSize();
	}

	/**
	 *  Save - Create Invoice Lines
	 *  @return true if saved
	 */
	public boolean save(IMiniTable miniTable, String trxName)
	{
		//  Invoice
		int A_Asset_Activate_Header_ID = ((Integer)getGridTab().getValue("A_Asset_Activate_Header_ID")).intValue();
		X_A_Asset_Activate_Header header = new X_A_Asset_Activate_Header (Env.getCtx(), A_Asset_Activate_Header_ID, trxName);
		if (log.isLoggable(Level.CONFIG)) log.config(header.toString());

		//  Lines
		for (int i = 0; i < miniTable.getRowCount(); i++)
		{
			if (((Boolean)miniTable.getValueAt(i, 0)).booleanValue())
			{
				MAsset asset = null;
				
				KeyNamePair pp = (KeyNamePair)miniTable.getValueAt(i, 1);               //  1-Asset
				int A_Asset_ID = 0;
				if (pp != null)
					A_Asset_ID = pp.getKey();
				
				if (A_Asset_ID != 0)
				{
					asset = MAsset.get(Env.getCtx(), A_Asset_ID, trxName);
				}
				
				MAssetAddition assetAddt = new Query(Env.getCtx(), MAssetAddition.Table_Name, 
						"A_Asset_ID=?", trxName)
						.setClient_ID()
						.setOnlyActiveRecords(true)
						.setParameters(A_Asset_ID).setOrderBy("created")
						.first();
				if(assetAddt == null || assetAddt.get_ID() <= 0) {
//					assetAddt = new MAssetAddition(Env.getCtx(), 0, trxName);
					continue;
				} else if(assetAddt.get_ID() > 0) {
//					assetAddt.setDateAcct(header.getDateAcct());
//					assetAddt.setDateDoc(header.getDateDoc());
//					assetAddt.setC_DocType_ID(1000140);
//					assetAddt.setA_Asset_ID(asset.get_ID());
//					assetAddt.setPostingType("A");
//					assetAddt.setA_SourceType("MAN");
//					assetAddt.setM_Product_ID(asset.getM_Product_ID());
//					assetAddt.setAssetAmtEntered(asset.getA_Asset_Cost());
//					assetAddt.setAssetValueAmt(asset.getA_Asset_Cost());
//					assetAddt.setAssetSourceAmt(asset.getA_Asset_Cost());
//					assetAddt.setA_QTY_Current(asset.getA_QTY_Current());
//					assetAddt.setC_ConversionType_ID(1000000);
//					assetAddt.setC_Currency_ID(303);
//					assetAddt.setDeltaUseLifeYears(1);
//					assetAddt.setDeltaUseLifeYears_F(1);
//					assetAddt.setDocStatus("DR");
					assetAddt.set_ValueNoCheck("A_Asset_Activate_Header_ID", header.getA_Asset_Activate_Header_ID());
					assetAddt.saveEx();
				}
				
			}   //   if selected
		}

		return true;
	}   //  saveInvoice

	protected Vector<String> getOISColumnNames()
	{
		//  Header Info
	    Vector<String> columnNames = new Vector<String>(7);
	    columnNames.add(Msg.getMsg(Env.getCtx(), "Select"));
	    columnNames.add("Barcode");
	    columnNames.add(Msg.translate(Env.getCtx(), "A_Asset_ID"));
	    columnNames.add(Msg.translate(Env.getCtx(), "C_BPartner_ID"));
	    columnNames.add("Branch");
	    columnNames.add("Cost Center");
	    columnNames.add("Department");

	    return columnNames;
	}

}
