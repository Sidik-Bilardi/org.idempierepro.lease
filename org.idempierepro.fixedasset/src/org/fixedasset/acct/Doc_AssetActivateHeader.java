package org.fixedasset.acct;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.compiere.acct.Doc;
import org.compiere.acct.Fact;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MDocType;
import org.compiere.util.Env;
import org.fixedasset.model.MKstAssetActivateHeader;

public class Doc_AssetActivateHeader extends Doc {

//	public Doc_AssetActivateHeader(MAcctSchema as, Class<?> clazz, ResultSet rs, String defaultDocumentType,
//			String trxName) {
//		super(as, clazz, rs, defaultDocumentType, trxName);
//		// TODO Auto-generated constructor stub
//	}
	
	public Doc_AssetActivateHeader (MAcctSchema as, ResultSet rs, String trxName)
	{
		super(as, MKstAssetActivateHeader.class, rs, MDocType.DOCBASETYPE_GLDocument, trxName);
	}

	@Override
	protected String loadDocumentDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getBalance() {
		// TODO Auto-generated method stub
		return Env.ZERO;
	}

	@Override
	public ArrayList<Fact> createFacts(MAcctSchema as) {
		MKstAssetActivateHeader assetAdd = getAssetActivateHeader();
		ArrayList<Fact> facts = new ArrayList<Fact>();
		Fact fact = new Fact(this, as, assetAdd.getPostingType());
		facts.add(fact);
		return facts;
	}
	private MKstAssetActivateHeader getAssetActivateHeader()
	{
		return (MKstAssetActivateHeader)getPO();
	}

}
