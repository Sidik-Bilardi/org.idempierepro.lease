package org.fixedasset.acct;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.compiere.acct.Doc;
import org.compiere.acct.Fact;
import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MDocType;
import org.compiere.model.ProductCost;
import org.compiere.util.Env;
import org.fixedasset.model.MKstAssetDisposedHeader;


/**
 * @author Kosta
 */
public class Doc_AssetDisposedHeader extends Doc
{
	public Doc_AssetDisposedHeader (MAcctSchema as, ResultSet rs, String trxName)
	{
		super(as, MKstAssetDisposedHeader.class, rs, MDocType.DOCBASETYPE_GLDocument, trxName);
	}

	
	protected String loadDocumentDetails()
	{
		return null;
	}

	
	public BigDecimal getBalance()
	{
		return Env.ZERO;
	}
	
	public ArrayList<Fact> createFacts(MAcctSchema as)
	{
		MKstAssetDisposedHeader assetAdd = getAssetDisposedHeader();
		ArrayList<Fact> facts = new ArrayList<Fact>();
		Fact fact = new Fact(this, as, assetAdd.getPostingType());
		facts.add(fact);
		return facts;
	}
	
	private MKstAssetDisposedHeader getAssetDisposedHeader()
	{
		return (MKstAssetDisposedHeader)getPO();
	}
	
	public MAccount getP_Expense_Acct(int M_Product_ID, MAcctSchema as)
	{
		ProductCost pc = new ProductCost(getCtx(), M_Product_ID, 0, null);
		return pc.getAccount(ProductCost.ACCTTYPE_P_Expense, as);
	}
}
