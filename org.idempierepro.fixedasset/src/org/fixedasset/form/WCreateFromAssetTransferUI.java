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

import static org.compiere.model.SystemIDs.COLUMN_C_INVOICE_C_BPARTNER_ID;

import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.apps.form.WCreateFromWindow;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListItem;
import org.adempiere.webui.component.ListModelTable;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.adempiere.webui.util.ZKUpdateUtil;
import org.compiere.model.GridTab;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Center;

public class WCreateFromAssetTransferUI extends CreateFromAssetTransfer implements EventListener<Event>, ValueChangeListener
{
	private WCreateFromWindow window;
	
	public WCreateFromAssetTransferUI(GridTab tab) 
	{
		super(tab);
		log.info(getGridTab().toString());
		
		window = new WCreateFromWindow(this, getGridTab().getWindowNo());
		
		p_WindowNo = getGridTab().getWindowNo();

		try
		{
			if (!dynInit())
				return;
			zkInit();
			setInitOK(true);
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "", e);
			setInitOK(false);
		}
		AEnv.showWindow(window);
	}
	
	/** Window No               */
	private int p_WindowNo;

	/**	Logger			*/
	private CLogger log = CLogger.getCLogger(getClass());
		
	protected Label bPartnerLabel = new Label();
	protected WEditor bPartnerField;
	protected Label branchLabel = new Label();
	protected Listbox branchField = ListboxFactory.newDropdownListbox();
	protected Label costcenterLabel = new Label();
	protected Listbox costcenterField = ListboxFactory.newDropdownListbox();
	protected Label departmentLabel = new Label();
	protected Listbox departmentField = ListboxFactory.newDropdownListbox();
	protected Label AssetLabel = new Label();
	protected WEditor AssetField;
	/**
	 *  Dynamic Init
	 *  @throws Exception if Lookups cannot be initialized
	 *  @return true if initialized
	 */
	public boolean dynInit() throws Exception
	{
		log.config("");
		
		super.dynInit();
		
		window.setTitle(getTitle());
		
		initBPartner(true);
		bPartnerField.addValueChangeListener(this);
		
		ArrayList<KeyNamePair> list = loadData("C_SalesRegion");
		branchField.addItem(new KeyNamePair(0, ""));
		for(KeyNamePair knp : list)
			branchField.addItem(knp);

		list = loadData("C_Project");
		costcenterField.addItem(new KeyNamePair(0, ""));
		for(KeyNamePair knp : list)
			costcenterField.addItem(knp);

		list = loadData("C_Activity");
		departmentField.addItem(new KeyNamePair(0, ""));
		for(KeyNamePair knp : list){
			departmentField.addItem(knp);
		}			
		initAsset(true);
		AssetField.addValueChangeListener(this);
		
		branchField.setSelectedIndex(-1);
		costcenterField.setSelectedIndex(-1);
		departmentField.setSelectedIndex(-1);
		
		branchField.addActionListener(this);
		costcenterField.addActionListener(this);
		departmentField.addActionListener(this);
		
		return true;
	}   //  dynInit
	
	protected void zkInit() throws Exception
	{
		bPartnerLabel.setText(Msg.getElement(Env.getCtx(), "C_BPartner_ID"));
		branchLabel.setText("Branch");
		costcenterLabel.setText("Cost Center");
		departmentLabel.setText("Department");
		AssetLabel.setText(Msg.getElement(Env.getCtx(), "A_Asset_ID"));
		
		Borderlayout parameterLayout = new Borderlayout();
		ZKUpdateUtil.setHeight(parameterLayout, "110px");
		ZKUpdateUtil.setWidth(parameterLayout, "100%");
    	Panel parameterPanel = window.getParameterPanel();
		parameterPanel.appendChild(parameterLayout);
		
		Grid parameterStdLayout = GridFactory.newGridLayout();
    	Panel parameterStdPanel = new Panel();
		parameterStdPanel.appendChild(parameterStdLayout);

		Center center = new Center();
		parameterLayout.appendChild(center);
		center.appendChild(parameterStdPanel);
		
		Rows rows = (Rows) parameterStdLayout.newRows();
		Row row = rows.newRow();
		row.appendChild(bPartnerLabel.rightAlign());
		row.appendChild(bPartnerField.getComponent());
		
		row.appendChild(costcenterLabel.rightAlign());
		ZKUpdateUtil.setHflex(costcenterField, "1");
		row.appendChild(costcenterField);
		
		
		row = rows.newRow();
		row.appendChild(branchLabel.rightAlign());
		ZKUpdateUtil.setHflex(branchField, "1");
		row.appendChild(branchField);		
		
		row.appendChild(departmentLabel.rightAlign());
		ZKUpdateUtil.setHflex(departmentField, "1");
		row.appendChild(departmentField);
		
		row = rows.newRow();
		row.appendChild(AssetLabel.rightAlign());
		row.appendChild(AssetField.getComponent());
		
	}

	private boolean 	m_actionActive = false;
	
	/**
	 *  Action Listener
	 *  @param e event
	 * @throws Exception 
	 */
	public void onEvent(Event e) throws Exception
	{
		if (m_actionActive)
			return;
		m_actionActive = true;
		
		if (e.getTarget().equals(branchField))
		{
			ListItem li = branchField.getSelectedItem();
			C_SalesRegion_ID = 0;
			if (li != null && li.getValue() != null)
				C_SalesRegion_ID = ((Integer) li.getValue()).intValue();
			loadAsset();
		}
		else if (e.getTarget().equals(costcenterField))
		{
			ListItem li = costcenterField.getSelectedItem();
			C_Project_ID = 0;
			if (li != null && li.getValue() != null)
				C_Project_ID = ((Integer) li.getValue()).intValue();
			loadAsset();
		}
		else if (e.getTarget().equals(departmentField))
		{
			ListItem li = departmentField.getSelectedItem();
			C_Activity_ID = 0;
			if (li != null && li.getValue() != null)
				C_Activity_ID = ((Integer) li.getValue()).intValue();
			loadAsset();
		}

		m_actionActive = false;
	}
	
	/**
	 *  Change Listener
	 *  @param e event
	 */
	public void valueChange (ValueChangeEvent e)
	{
		if (log.isLoggable(Level.CONFIG)) log.config(e.getPropertyName() + "=" + e.getNewValue());

		//  BPartner - load Order/Invoice/Shipment
		if (e.getPropertyName().equals("C_BPartner_ID"))
		{
			C_BPartner_ID = 0;
			if(e.getNewValue()!=null)
				C_BPartner_ID = ((Integer)e.getNewValue()).intValue();
			
			loadAsset();
		}
		if (e.getPropertyName().equals("A_Asset_ID"))
		{
			A_Asset_ID = 0;
			if(e.getNewValue()!=null)
				A_Asset_ID = ((Integer)e.getNewValue()).intValue();
			
			loadAsset();
		}
		window.tableChanged(null);
	}   //  vetoableChange
	
	/**************************************************************************
	 *  Load BPartner Field
	 *  @param forInvoice true if Invoices are to be created, false receipts
	 *  @throws Exception if Lookups cannot be initialized
	 */
	protected void initBPartner (boolean forInvoice) throws Exception
	{
		//  load BPartner
		int AD_Column_ID = COLUMN_C_INVOICE_C_BPARTNER_ID;        //  C_Invoice.C_BPartner_ID
		MLookup lookup = MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, AD_Column_ID, DisplayType.Search);
		bPartnerField = new WSearchEditor ("C_BPartner_ID", true, false, true, lookup);
		//
		C_BPartner_ID = Env.getContextAsInt(Env.getCtx(), p_WindowNo, "C_BPartner_ID");
		if(C_BPartner_ID>0){
			bPartnerField.setValue(new Integer(C_BPartner_ID));
			loadAsset();
		}

	}   //  initBPartner
	protected void initAsset (boolean forInvoice) throws Exception
	{
		//  load BPartner
		int AD_Column_ID = 8070;        //  C_Invoice.C_BPartner_ID
		MLookup lookup = MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, AD_Column_ID, DisplayType.Search);
		AssetField = new WSearchEditor ("A_Asset_ID", true, false, true, lookup);
		//
		A_Asset_ID = Env.getContextAsInt(Env.getCtx(), p_WindowNo, "A_Asset_ID");
		if(A_Asset_ID>0){
			AssetField.setValue(new Integer(A_Asset_ID));
			loadAsset();
		}

	}   //  initBPartner
	
	/**
	 *  Load Data - Asset
	 *  @param C_BPartner_ID BPartner
	 */
	protected void loadAsset ()
	{
		loadTableOIS(getAssetData());
	}   //  LoadOrder
	
	/**
	 *  Load Order/Invoice/Shipment data into Table
	 *  @param data data
	 */
	protected void loadTableOIS (Vector<?> data)
	{
		window.getWListbox().clear();
		
		//  Remove previous listeners
		window.getWListbox().getModel().removeTableModelListener(window);
		//  Set Model
		ListModelTable model = new ListModelTable(data);
		model.addTableModelListener(window);
		window.getWListbox().setData(model, getOISColumnNames());
		//
		
		configureMiniTable(window.getWListbox());
	}   //  loadOrder
	
	public void showWindow()
	{
		window.setVisible(true);
	}
	
	public void closeWindow()
	{
		window.dispose();
	}

	@Override
	public Object getWindow() {
		return window;
	}
}
