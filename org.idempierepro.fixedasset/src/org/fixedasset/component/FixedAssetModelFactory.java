/******************************************************************************
 * Product: iDempiere Free ERP Project based on Compiere (2006)               *
 * Copyright (C) 2014 Redhuan D. Oon All Rights Reserved.                     *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *  FOR NON-COMMERCIAL DEVELOPER USE ONLY                                     *
 *  @author Redhuan D. Oon  - red1@red1.org  www.red1.org                     *
 *****************************************************************************/

package org.fixedasset.component;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.fixedasset.model.MAssetActivateHeader;
import org.fixedasset.model.MAssetStock;
import org.fixedasset.model.MKstAssetActivateHeader;
import org.fixedasset.model.MKstAssetDisposedHeader;
import org.fixedasset.model.MKstAssetTransfer;
import org.fixedasset.model.MKstAssetTransferLine;
import org.lease.model.X_C_OrderComponent;
import org.lease.model.X_C_OrderLineSch;


public class FixedAssetModelFactory implements IModelFactory {

	CLogger log = CLogger.getCLogger(FixedAssetModelFactory.class);
	@Override
	public Class<?> getClass(String tableName) {
		 if (tableName.equals(MKstAssetTransfer.Table_Name)){
			 return MKstAssetTransfer.class;
		 }
		 else if (tableName.equals(MKstAssetTransferLine.Table_Name)){
			 return MKstAssetTransferLine.class;
		 } 
		 else if (tableName.equals(MKstAssetDisposedHeader.Table_Name)){
			 return MKstAssetDisposedHeader.class;
		 } 
		 else if (tableName.equals(MAssetActivateHeader.Table_Name)){
			 return MAssetActivateHeader.class;
		 }
 		 else if (tableName.equals(MAssetStock.Table_Name)){
 			 return MAssetStock.class;
 		 }
		 
 		else if (tableName.equals(X_C_OrderComponent.Table_Name)){
			 return X_C_OrderComponent.class;
		 }
		 
		return null;
	}

	@Override
	public PO getPO(String tableName, int Record_ID, String trxName) {

		Class<?> clazz = getClass(tableName);
		if (clazz == null)
			return null;

		PO model = null;
		Constructor<?> constructor = null;

		try {
			constructor = clazz.getDeclaredConstructor(new Class[] { Properties.class, int.class, String.class });
			model = (PO) constructor.newInstance(new Object[] { Env.getCtx(), new Integer(Record_ID), trxName });
		} catch (Exception e) {
			if (log.isLoggable(Level.WARNING))
				log.warning(String.format("Plugin: %s -> Class can not be instantiated for table: %s", "FixedAssetPlugin", tableName));
		}

		return model;
	}

	@Override
	public PO getPO(String tableName, ResultSet rs, String trxName) {

		Class<?> clazz = getClass(tableName);
		if (clazz == null)
			return null;

		PO model = null;
		Constructor<?> constructor = null;

		try {
			constructor = clazz.getDeclaredConstructor(new Class[] { Properties.class, ResultSet.class, String.class });
			model = (PO) constructor.newInstance(new Object[] { Env.getCtx(), rs, trxName });
		} catch (Exception e) {
			if (log.isLoggable(Level.WARNING))
				log.warning(String.format("Plugin: %s -> Class can not be instantiated for table: %s", "FixedAssetPlugin", tableName));
		}

		return model;
	}

}
