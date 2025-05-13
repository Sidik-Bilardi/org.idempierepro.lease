/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.fixedasset.process;


import java.util.List;

import org.compiere.model.MAssetAddition;
import org.compiere.model.Query;
import org.compiere.process.SvrProcess;

/**
 *	Complete Asset Addition 
 *
 * 	@author 	Aries Chandra
 * 	@version 	$Id: CompleteAssetAddition.java,v 1.8 2019/06/24 00:51:02 
 */
public class CompleteAssetAddition extends SvrProcess
{

	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		
	}	//	prepare


	/**
	 *  Perform process.
	 *  @return Message (clear text)
	 *  @throws Exception if not successful
	 */
	protected String doIt() throws java.lang.Exception
	{
		String sqlWhere = " DocStatus = 'DR' AND I_FixedAsset_ID > 0  ";
		List<MAssetAddition> assetAddition = new Query(getCtx(), MAssetAddition.Table_Name, sqlWhere, get_TrxName())
									.setOnlyActiveRecords(true)
									.list();
		for (MAssetAddition mAssetAddition : assetAddition) {
			try {
				mAssetAddition.completeIt();
				mAssetAddition.saveEx();
			} catch (Exception e) {
				log.info("Document No #"+mAssetAddition.getDocumentNo());
				log.info(e.getMessage());
				continue;
			}			
		}
		
		return "";
	}	//	doIt


}	//	ExpenseAPInvoice
