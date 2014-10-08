package com.ims.components;



import java.io.File;
import java.io.FileInputStream;


import com.ims.ImsUI;
import com.ims.business.CompanyDAO;
import com.ims.data.Company;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class CompanyProfileViewComp extends CustomComponent{
	
	VerticalLayout mainLayout= new VerticalLayout();
	
	String comapnyName;
	CompanyDAO companyDAO=  (CompanyDAO)ImsUI.context.getBean("AccessCompany");
	Company company;
	public File file;
	FileInputStream fis = null;
	
	final Embedded image = new Embedded("");

	
	
	// if name is companyUserName need to type to be "UserName" if name is companyName need to type is "name"
	public CompanyProfileViewComp(String name,String type)
	{
		this.comapnyName=name;
		setSizeFull();
		
		
		if(type.equals("name"))
		{
			setCompanyFromName();
			
		}
		else
		{
			setComapnyFromUserName();
		}
		buildCompanyProfile();
		setCompositionRoot(mainLayout);
		
	}
	
	
	
	private void setCompanyFromName()
	{
		company=companyDAO.getCompanyFromCompanyName(comapnyName);
		
	}
	
	
	private void setComapnyFromUserName()
	{
		company= companyDAO.getComapny(comapnyName);
		
	}
	
	
	private void buildCompanyProfile()
	{	
		mainLayout.setSizeFull();
		
		
		
		
		
		//Company company= companyDAO.getComapny(comapnyUserName);
		if(company!=null)
		{
			
			
			
			//file = new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath()+"/WEB-INF/images/" + company.getCompanyUserName()+".png");
			
	        try {
	        	
	        	file = new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath()+"/WEB-INF/images/" + company.getCompanyUserName()+".png");
	        	fis = new FileInputStream (file);
	        } catch (final java.io.FileNotFoundException e) {

	            file = new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath()+"/WEB-INF/images/defalutCompany.png");

	        }
	        
			image.setSource(new FileResource(file));
			
			HorizontalLayout imagelayout= new HorizontalLayout();
			imagelayout.setHeight("100px");
			imagelayout.setWidth("200px");
			image.setSizeFull();
			imagelayout.addComponent(image);
			//image.setHeight("80px");
			//image.setWidth("80px");
			
			Label comProfile= new Label("Company Profile");
		    comProfile.addStyleName("comRegiLable");
		    mainLayout.addComponent(comProfile);
		   // mainLayout.addComponent(image);
		    mainLayout.addComponent(imagelayout);
		    
		    
		    GridLayout grid = new GridLayout(4,20);
		    grid.addComponent(new Label("Genaral Information"),1,1);
		    grid.addComponent(new Label("Company Name"),2,2);
		    grid.addComponent(new Label("No of Vacancies"),2,3);
		    grid.addComponent(new Label("Technologies"),1,4);
		    grid.addComponent(new Label("Contact Information"),1,6);
		    grid.addComponent(new Label("Telephone"),2,7);
		    grid.addComponent(new Label("Email"),2,8);
		    grid.addComponent(new Label("Web site"),2,9);
		    grid.addComponent(new Label("Contact information Contact-persions"),1,10);
		    grid.addComponent(new Label("Contact person name"),2,11);
		    grid.addComponent(new Label("Contact person email"),2,12);
		    grid.addComponent(new Label("Contact person ContactNo"),2,13);
		    grid.addComponent(new Label("Al Contact person Name"),2,14);
		    grid.addComponent(new Label("Al Contact person email"),2,15);
		    grid.addComponent(new Label("Al Contact persons ContactNo"),2,16);		   
		    
		    grid.addComponent(new Label(company.getCompanyName()),3,2);
		    grid.addComponent(new Label(company.getNoOfVacancies()),3,3);
		    grid.addComponent(new Label(company.getTechnologies()),3,4);
		    grid.addComponent(new Label(company.getCompanyTelephone()),3,7);
		    grid.addComponent(new Label(company.getCompanyEmail()),3,8);
		    grid.addComponent(new Label(company.getCompanyWeb()),3,9);
		    grid.addComponent(new Label(company.getContactPerson()),3,11);
		    grid.addComponent(new Label(company.getContactPersonEmail()),3,12);
		    grid.addComponent(new Label(company.getContactPersonTelNo()),3,13);
		    grid.addComponent(new Label(company.getAlContactPerson()),3,14);
		    grid.addComponent(new Label(company.getAlContactPersonEmail()),3,15);
		    grid.addComponent(new Label(company.getAlContactPersonTelNo()),3,16);

	/*		mainLayout.addComponent(new Label(company.getCompanyName()));
			mainLayout.addComponent(new Label(company.getAboutCompany()));
			mainLayout.addComponent(new Label(company.getCompanyEmail()));
			mainLayout.addComponent(new Label(company.getCompanyTelephone()));
			mainLayout.addComponent(new Label(company.getCompanyWeb()));
			mainLayout.addComponent(new Label(company.getCompanyTelephone()));
			*/
		   // grid.setSizeFull();
		    grid.addStyleName("comapnyProfileViewGrid");
		    mainLayout.addComponent(grid);
		    mainLayout.setExpandRatio(grid, 1);
		}
		
		
		
	}

}
