package com.ims.components;

import java.util.List;

import com.ims.ImsUI;
import com.ims.business.CompanyDAO;
import com.ims.data.Company;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Table;
import com.vaadin.ui.Button.ClickEvent;

public class NotAllowedCompanyViewComp extends CustomComponent{
	
	AbsoluteLayout mainLayout;
	
	public NotAllowedCompanyViewComp()
	{
		setSizeFull();
		
		buildNotAllowedCompanyView();
		setCompositionRoot(mainLayout);
		
	}
	
	
	private void buildNotAllowedCompanyView()
	{
		mainLayout= new AbsoluteLayout();
		mainLayout.setSizeFull();
		
		
		final CompanyDAO companyDAO=  (CompanyDAO)ImsUI.context.getBean("AccessCompany");
		
		List<Company> companyList=companyDAO.getNotAllowedCompanies();
	
		Table table = new Table("");
		table.addContainerProperty("Index", Integer.class, null);
		table.addContainerProperty("Comapny Name", String.class, null);
		table.addContainerProperty("Company Email",  String.class, null);
		table.addContainerProperty("Company Contact No",  String.class, null);
		table.addContainerProperty("No Of Vacanies",  String.class, null);
		table.addContainerProperty("View Profile",  Button.class, null);
		table.addContainerProperty("Accept Company",  Button.class, null);
		table.addContainerProperty("Delete Company",  Button.class, null);
		table.setSizeFull();
		
	
		for(int x=0;x<companyList.size();x++)
		{
			int index=x+1;
			
			Button viewCompany=new Button("All Details");
			Button acceptCompany=new Button("Accept");
			Button deleteCompany=new Button("Delete");
			
			table.addItem(new Object[] {index,companyList.get(x).getCompanyName(),companyList.get(x).getCompanyEmail(),companyList.get(x).getCompanyTelephone(),companyList.get(x).getNoOfVacancies(),viewCompany,acceptCompany,deleteCompany},index);
			
			
			viewCompany.setData(companyList.get(x).getCompanyName().replace(' ', '_'));
			//set user name as buttun data
			acceptCompany.setData(companyList.get(x).getCompanyUserName());
			deleteCompany.setData(companyList.get(x).getCompanyUserName());
			
			
			viewCompany.addClickListener(new Button.ClickListener() {
		        public void buttonClick(ClickEvent event) {

		        	getUI().getNavigator().navigateTo("/company/"+(String)event.getButton().getData());
		        } 
		    });
			
			
			acceptCompany.addClickListener(new Button.ClickListener() {
		        public void buttonClick(ClickEvent event) {

		        	//getUI().getNavigator().navigateTo("/company/"+(String)event.getButton().getData());
		        	companyDAO.acceptCompany((String)event.getButton().getData());
		        	getUI().getNavigator().navigateTo("/company/not_allowed_companies");
		        } 
		    });
			
			deleteCompany.addClickListener(new Button.ClickListener() {
		        public void buttonClick(ClickEvent event) {

		        	//getUI().getNavigator().navigateTo("/company/"+(String)event.getButton().getData());
		        	companyDAO.removeCompany((String)event.getButton().getData());
		        	getUI().getNavigator().navigateTo("/company/not_allowed_companies");
		        } 
		    });
			
		}

		
		mainLayout.addComponent(table,"bottom:75px;left: 0px; top: 0px;right:0px");
		
		
	}

}
