package com.ims.components;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class ContactUsViewComp extends CustomComponent{

	HorizontalLayout mainLayout = new HorizontalLayout();
	
	public ContactUsViewComp()
	{
		buildContactUsView();
		setCompositionRoot(mainLayout);
		
	}
	
	
	private void buildContactUsView()
	{
		mainLayout.addComponent(new Label("This is contact us"));
	}
}
