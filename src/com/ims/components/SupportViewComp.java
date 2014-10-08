package com.ims.components;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class SupportViewComp extends CustomComponent{

	HorizontalLayout mainLayout = new HorizontalLayout();
	
	public SupportViewComp()
	{
		buildSupportView();
		setCompositionRoot(mainLayout);
		
	}
	
	
	private void buildSupportView()
	{
		mainLayout.addComponent(new Label("This is support view"));
	}

}
