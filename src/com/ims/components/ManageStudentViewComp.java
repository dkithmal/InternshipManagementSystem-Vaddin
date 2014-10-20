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

				AdminDAO adminDAO= (AdminDAO)ImsUI.context.getBean("AccessAdmin");
				adminDAO.updateCurrentStudentBatch(Integer.parseInt(selectStudentBatch.getValue().toString()));
				
				Notification.show("Successfully change batch into"+selectStudentBatch.getValue().toString());
				
				getUI().getNavigator().navigateTo("/home");
				
			}
		});
	    
	    
	    mainLayout.addComponent(selectStudentBatch);
	    mainLayout.addComponent(submit);
	    
	
		
	}
	
	
	
	
		

}
