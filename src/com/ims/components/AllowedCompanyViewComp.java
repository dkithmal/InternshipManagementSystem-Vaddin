package com.ims.components;

import java.util.List;

import com.ims.ImsUI;
import com.ims.business.CompanyDAO;
import com.ims.data.Company;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Table;

public class AllowedCompanyViewComp extends CustomComponent{
	
	
	AbsoluteLayout mainLayout;
	List<Company> companyList;
	
	public AllowedCompanyViewComp()
	{
		setSizeFull();
		
		buildAllowedComapnyView();
		setCompositionRoot(mainLayout);
				
	}
	
	
	
	
	private void buildAllowedComapnyView()
	{
		mainLayout= new AbsoluteLayout();
		mainLayout.setSizeFull();
		
		
		final CompanyDAO companyDAO=  (CompanyDAO)ImsUI.context.getBean("AccessCompany");
		
		companyList=companyDAO.getAllowedComapnies();
	
		Table table = new Table("");
		table.addContainerProperty("Index", Integer.class, null);
		table.addContainerProperty("Comapny Name", String.class, null);
		table.addContainerProperty("Company Email",  String.class, null);
		table.addContainerProperty("Company Contact No",  String.class, null);
		table.addContainerProperty("No Of Vacanies",  String.class, null);
		table.addContainerProperty("View Profile",  Button.class, null);
		
		if(VaadinSession.getCurrent().getAttribute("Type").equals("Admin"))
		{
			table.addContainerProperty("Un Allow",  Button.class, null);
		}
		table.setSizeFull();
		
	
		for(int x=0;x<companyList.size();x++)
		{
			int index=x+1;
			
			Button viewCompany=new Button("All Details");
			Button unAllowCompany=new Button("Un Allow");
			
			
			//if admin add un allow button 
			if(VaadinSession.getCurrent().getAttribute("Type").equals("Admin"))
			{
				 
				
				unAllowCompany.setData(companyList.get(x).getCompanyUserName());
				
				unAllowCompany.addClickListener(new Button.ClickListener() {
			        public void buttonClick(ClickEvent event) {
			        	
			        	companyDAO.unAllowedCompany((String)event.getButton().getData());
			        	getUI().getNavigator().navigateTo("/company/allowed_companies");
			        } 
			    });
				
				table.addItem(new Object[] {index,companyList.get(x).getCompanyName(),companyList.get(x).getCompanyEmail(),companyList.get(x).getCompanyTelephone(),companyList.get(x).getNoOfVacancies(),viewCompany,unAllowCompany},index);
				
			}
			else
			{
				table.addItem(new Object[] {index,companyList.get(x).getCompanyName(),companyList.get(x).getCompanyEmail(),companyList.get(x).getCompanyTelephone(),companyList.get(x).getNoOfVacancies(),viewCompany},index);
				
			}
			
		
			
			
			viewCompany.setData(companyList.get(x).getCompanyName().replace(' ', '_'));
			
			viewCompany.addClickListener(new Button.ClickListener() {
		        public void buttonClick(ClickEvent event) {

		        	getUI().getNavigator().navigateTo("/company/"+(String)event.getButton().getData());
		        } 
		    });
			
		}

		
		mainLayout.addComponent(table,"bottom:75px;left: 0px; top: 0px;right:0px");
		
	}
	
	
	

	
	

}













