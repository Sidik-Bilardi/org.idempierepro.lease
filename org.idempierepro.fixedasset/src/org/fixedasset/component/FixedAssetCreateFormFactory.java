package org.fixedasset.component;

import org.compiere.grid.ICreateFrom;
import org.compiere.grid.ICreateFromFactory;
import org.compiere.model.GridTab;
import org.fixedasset.form.WCreateFromAssetActive;
import org.fixedasset.form.WCreateFromAssetTransferUI;
import org.fixedasset.form.WCreateFromAssetUI;
import org.fixedasset.model.MKstAssetDisposedHeader;
import org.fixedasset.model.MKstAssetTransfer;
import org.fixedasset.model.X_A_Asset_Activate_Header;

public class FixedAssetCreateFormFactory implements ICreateFromFactory {

	@Override
	public ICreateFrom create(GridTab mTab) {
		String tableName = mTab.getTableName();
		if(tableName.equals(MKstAssetDisposedHeader.Table_Name))
			return new WCreateFromAssetUI(mTab);
		else if(tableName.equals(MKstAssetTransfer.Table_Name))
			return new WCreateFromAssetTransferUI(mTab);
		else if(tableName.equals(X_A_Asset_Activate_Header.Table_Name))
			return new WCreateFromAssetActive(mTab);
		
		return null;
	}

}
