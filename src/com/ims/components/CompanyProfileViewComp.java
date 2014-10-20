package com.ims.components;



import java.io.File;
import java.io.FileInputStream;


import com.ims.ImsUI;
import com.ims.business.CompanyDAO;
import com.ims.data.Company;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;

public class CompanyProfileViewComp extends CustomComponent{
	
	VerticalLayout mainLayout= new VerticalLayout();
    Panel mainLayoutPanel= new Panel();

    VerticalLayout companyProfileView= new VerticalLayout();
	
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


        mainLayoutPanel.setContent(mainLayout);
        mainLayoutPanel.setSizeFull();
        mainLayoutPanel.addStyleName("mainLayoutPanel");
		setCompositionRoot(mainLayoutPanel);


		
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

            image.setHeight("75px");
            image.setWidthUndefined();


            companyProfileView.setSizeFull();
            companyProfileView.setSpacing(true);


            Label companyProfileMainHeader= new Label(company.getCompanyName()+" Profile");
            companyProfileMainHeader.setWidthUndefined();
            companyProfileMainHeader.addStyleName("companyProfileViewMainHeader");
            companyProfileView.addComponent(companyProfileMainHeader);
            companyProfileView.setComponentAlignment(companyProfileMainHeader,Alignment.TOP_CENTER);

            companyProfileView.addComponent(image);

            Label genaralInformationLabel= new Label("Genaral Information");
            genaralInformationLabel.addStyleName("companyProfileViewHeader");
            companyProfileView.addComponent(genaralInformationLabel);


            Label companyName= new Label("Company Name : "+company.getCompanyName());
            companyName.addStyleName("companyProfileViewContent");
            Label noOfVacancies= new Label("No of Vacancies  : "+company.getNoOfVacancies());
            noOfVacancies.addStyleName("companyProfileViewContent");

            companyProfileView.addComponent(companyName);
            companyProfileView.addComponent(noOfVacancies);



            Label technologiesLabel= new Label("Technologies");
            technologiesLabel.addStyleName("companyProfileViewHeader");

            companyProfileView.addComponent(technologiesLabel);


            Label companyTechnologies= new Label("C++ java Web"+company.getTechnologies());
            companyTechnologies.setHeightUndefined();
            companyTechnologies.addStyleName("companyProfileViewContent");

            companyProfileView.addComponent(companyTechnologies);


            Label contactInformationLabel= new Label("Contact Informatin");
            contactInformationLabel.addStyleName("companyProfileViewHeader");

            companyProfileView.addComponent(contactInformationLabel);

            Label companyTelephone= new Label("Company Telephone : "+company.getCompanyTelephone());
            companyTelephone.addStyleName("companyProfileViewContent");
            Label companyEmail= new Label("Company Email : "+company.getCompanyEmail());
            companyEmail.addStyleName("companyProfileViewContent");
            Label companyWeb= new Label("Company WebSite : "+company.getCompanyWeb());
            companyWeb.addStyleName("companyProfileViewContent");

            companyProfileView.addComponent(companyTelephone);
            companyProfileView.addComponent(companyEmail);
            companyProfileView.addComponent(companyWeb);

            Label contactPersonInformationLabel= new Label("Contact Information Contact Persons");
            contactPersonInformationLabel.addStyleName("companyProfileViewHeader");

            companyProfileView.addComponent(contactPersonInformationLabel);



            Label contactPersonName= new Label("Contact Person Name :"+company.getContactPerson());
            contactPersonName.addStyleName("companyProfileViewContent");
            Label contactPersonEmail= new Label("Contact Person Email :"+company.getContactPersonEmail());
            contactPersonEmail.addStyleName("companyProfileViewContent");
            Label contactPersonContactNo= new Label("Contact Person Contact No :"+company.getContactPersonTelNo());
            contactPersonContactNo.addStyleName("companyProfileViewContent");
            Label alternativeContactPersonName= new Label("Alternative Contact Person Name :"+company.getAlContactPerson());
            alternativeContactPersonName.addStyleName("companyProfileViewContent");
            Label alternativeContactPersonEmail= new Label("AlternativeContact Person Email :"+company.getAlContactPersonEmail());
            alternativeContactPersonEmail.addStyleName("companyProfileViewContent");
            Label alternativeContactPersonContactNo= new Label("Alternative Contact Person ContactNo :"+company.getAlContactPersonTelNo());
            alternativeContactPersonContactNo.addStyleName("companyProfileViewContent");


            companyProfileView.addComponent(contactPersonName);
            companyProfileView.addComponent(contactPersonEmail);
            companyProfileView.addComponent(contactPersonContactNo);
            companyProfileView.addComponent(alternativeContactPersonName);
            companyProfileView.addComponent(alternativeContactPersonEmail);
            companyProfileView.addComponent(alternativeContactPersonContactNo);

            companyProfileView.setExpandRatio(alternativeContactPersonContactNo,1);

            mainLayout.addComponent(companyProfileView);





		}
		
		
		
	}

}
