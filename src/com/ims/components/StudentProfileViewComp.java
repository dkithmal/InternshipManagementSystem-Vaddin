package com.ims.components;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class StudentProfileViewComp extends CustomComponent{
	
	HorizontalLayout mainLayout = new HorizontalLayout();
	
	
	public StudentProfileViewComp()
	{
		setSizeFull();
		buildAboutUs();
		setCompositionRoot(mainLayout);
		
	}
	
	
	
	private void buildAboutUs()
	{
		mainLayout.addComponent(new Label("This is student profile"));
		
	}
	

}
