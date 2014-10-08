package com.ims.components;

import com.ims.ImsUI;
import com.ims.business.AdminDAO;
import com.ims.data.Administration;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

public class ManageStudentLoginComp  extends CustomComponent{
	
	VerticalLayout mainLayout = new VerticalLayout();
	
	public ManageStudentLoginComp()
	{
		setSizeFull();
		buildManageStudentLoginView();
		setCompositionRoot(mainLayout);
		
	}
	
	
	
	private void buildManageStudentLoginView()
	{
		//mainLayout.addComponent(new Label("This is about us"));
		
		final ComboBox selectStudentBatch= new ComboBox("Select Student Batch");
	    selectStudentBatch.addItem("2010");
	    selectStudentBatch.addItem("2011");
	    selectStudentBatch.addItem("2012");
	    selectStudentBatch.addItem("2013");
	    selectStudentBatch.addItem("2014");
	    selectStudentBatch.addItem("2015");
	    selectStudentBatch.addItem("2016");
	    selectStudentBatch.addItem("2017");
	    
	    
	    Button submit= new Button("Submit Studnet Batch");
	    
	    submit.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				if(selectStudentBatch.getValue()==null)
				{
					Notification.show("Plsease select Batch");
					return;
				}
				
				Administration administration= new Administration();
				administration.setBatch(Integer.parseInt(selectStudentBatch.getValue().toString()));
				administration.setAdminId(1234);
				
				AdminDAO adminDAO= (AdminDAO)ImsUI.context.getBean("AccessAdmin");
				adminDAO.updateAdministrationTable(administration);
				
				Notification.show("Successfully change batch into"+selectStudentBatch.getValue().toString());
				
				getUI().getNavigator().navigateTo("/home");
				
			}
		});
	    
	    
	    mainLayout.addComponent(selectStudentBatch);
	    mainLayout.addComponent(submit);
	    
	
		
	}
	
	
	
	
		

}
