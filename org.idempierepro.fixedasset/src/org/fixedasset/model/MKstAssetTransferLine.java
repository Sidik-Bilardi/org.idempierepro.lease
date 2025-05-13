package org.fixedasset.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MKstAssetTransferLine extends X_A_AssetTransfer_Line{
	/**
	 * 
	 */
	private static final long serialVersionUID = 363425216218354513L;
	
	public MKstAssetTransferLine(Properties ctx, int A_AssetTransfer_Line_ID, String trxName) {
		super(ctx, A_AssetTransfer_Line_ID, trxName);
	}

	public MKstAssetTransferLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	

}
