package org.fixedasset.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MAsset;



/**
 * @author AH
 *
 */
public class CalloutMAssetTransferLine implements IColumnCallout {
	
	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {
		if(mField.getColumnName().equals("A_Asset_ID"))
			return setAssetInfo(ctx, WindowNo, mTab, mField, value);
		
		return null;
	}
	
	public String setAssetInfo(Properties ctx, int WindowNo, GridTab mTab,GridField mField, Object value)
	{
		if (value == null )
			return "";
		
		MAsset asset = new MAsset(ctx, (int) value, null);
		
		mTab.setValue("C_SalesRegion_ID", asset.get_ValueAsInt("C_SalesRegion_ID"));
		mTab.setValue("C_Activity_ID", asset.get_ValueAsInt("C_Activity_ID"));
		return "";
	}
	
}