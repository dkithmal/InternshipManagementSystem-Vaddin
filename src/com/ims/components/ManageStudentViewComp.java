package com.ims.components;

import com.ims.ImsUI;
import com.ims.business.AdminDAO;
import com.ims.data.Administration;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

public class ManageStudentViewComp extends CustomComponent{
	
	VerticalLayout mainLayout = new VerticalLayout();
	
	public ManageStudentViewComp()
	{
		setSizeFull();
		buildManageStudentLoginView();
		setCompositionRoot(mainLayout);
		
	}
	
	
	
	private void buildManageStudentLoginView()
	{
        mainLayout.setSizeFull();
        mainLayout.setSpacing(true);

        final AdminDAO adminDAO= (AdminDAO)ImsUI.context.getBean("AccessAdmin");
		//mainLayout.addComponent(new Label("This is about us"));
		
		final ComboBox selectStudentBatch= new ComboBox("Select Current Student Batch");
	    selectStudentBatch.addItem("2010");
	    selectStudentBatch.addItem("2011");
	    selectStudentBatch.addItem("2012");
	    selectStudentBatch.addItem("2013");
	    selectStudentBatch.addItem("2014");
	    selectStudentBatch.addItem("2015");
	    selectStudentBatch.addItem("2016");
	    selectStudentBatch.addItem("2017");
	    
	    
	    Button submit= new Button("Submit Current Student Batch");

	    
	    submit.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				if(selectStudentBatch.getValue()==null)
				{
					Notification.show("Plsease select Batch");
					return;
				}


				adminDAO.updateCurrentStudentBatch(Integer.parseInt(selectStudentBatch.getValue().toString()));
				
				Notification.show("Successfully change batch into"+selectStudentBatch.getValue().toString());
				
				getUI().getNavigator().navigateTo("/home");
				
			}
		});

        mainLayout.addComponent(selectStudentBatch);
        mainLayout.addComponent(submit);


       Label enableStudentLoginLabel= new Label("Enable Or Disable Student Login");


        String enableOrDisabeLabel;

        if(adminDAO.getStudentLoginState())
        {
           enableOrDisabeLabel= new String("Disable Student Login");

        }
        else
        {
            enableOrDisabeLabel= new String("Enable Student Login");

        }

        final Button enabelOrDisableStudentLogin= new Button(enableOrDisabeLabel);



        enabelOrDisableStudentLogin.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent clickEvent) {
                if(enabelOrDisableStudentLogin.getCaption().equals("Enable Student Login"))
                {
                    adminDAO.enableStudentLogin();

                    enabelOrDisableStudentLogin.setCaption("Disable Student Login");

                }
                else
                {
                    adminDAO.disableStudentLogin();
                    enabelOrDisableStudentLogin.setCaption("Enable Student Login");
                }
            }
        }

        );
        mainLayout.addComponent(enableStudentLoginLabel);
        mainLayout.addComponent(enabelOrDisableStudentLogin);


        Label studentApplicableComapnyCountLabel= new Label("Student Can Applicable Comapay Count");

        final TextField setStudentApplicableCompanyCount= new TextField("Enter Student Applicable Company Count");

        Button submitStudntApplicableCompanyCount= new Button("Submit Count");

        submitStudntApplicableCompanyCount.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent clickEvent) {

                adminDAO.setStudentApplicableCompanyCount(Integer.valueOf(setStudentApplicableCompanyCount.getValue().toString()));
                Notification.show("Successfully Updated student Applicable Company Count");


            }
        }
        );


        mainLayout.addComponent(studentApplicableComapnyCountLabel);
        mainLayout.addComponent(setStudentApplicableCompanyCount);
        mainLayout.addComponent(submitStudntApplicableCompanyCount);



        mainLayout.setExpandRatio(submitStudntApplicableCompanyCount,1);

	    
	
		
	}
	
	
	
	
		

}
