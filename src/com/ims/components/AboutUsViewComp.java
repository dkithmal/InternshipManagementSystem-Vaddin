package com.ims.components;



import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class AboutUsViewComp extends CustomComponent{

	HorizontalLayout mainLayout = new HorizontalLayout();
	
	
	public AboutUsViewComp()
	{
		setSizeFull();
		buildAboutUs();
		setCompositionRoot(mainLayout);
		
	}
	
	
	
	private void buildAboutUs()
	{
		mainLayout.addComponent(new Label("This is about us"));
		
	}
	

}
