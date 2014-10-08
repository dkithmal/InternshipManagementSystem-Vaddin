package com.ims.mainviews;

import com.ims.components.StudentCvFormComp;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.BaseTheme;

public class StudentHomeView  extends CssLayout implements View{
	
	CssLayout content = new CssLayout();

	@Override
	public void enter(ViewChangeEvent event) {

		
        setSizeFull();
		
		buildHomeMainView();
		
		if (event.getParameters().equals("view_profile")) {
			
			buildViewProfileView();

			
		}
		
		else if (event.getParameters().equals("compose_cv"))
		{
			buildComposeCVView();
			
		}
		else if (event.getParameters().equals("applied_companies"))
		{
			buildAppliedCompanyView();
			
		}
		
	}
	
	
    private void buildHomeMainView()
    {
    	
    	addComponent(new HorizontalLayout(){
    		{
    			 setSizeFull();
    			addComponent(new VerticalLayout(){
    				{
    					
    					setHeight("100%");
    					setWidth(null);
    					addStyleName("submenu");

    	                final Button home = new Button("View Profile");
    	                home.setStyleName(BaseTheme.BUTTON_LINK);
    	                addComponent(home);
    	                
    	                home.addClickListener(new ClickListener() {
    	                    @Override
    	                    public void buttonClick(ClickEvent event) {
    	                    	
    	                    	getUI().getNavigator().navigateTo("/home/view_profile");


    	                    }
    	                    
    	                
    	                   });

    	                
    	                final Button composeCV = new Button("Compose CV");
    	                composeCV.setStyleName(BaseTheme.BUTTON_LINK);
    	                addComponent(composeCV);
    	                
    	                composeCV.addClickListener(new ClickListener() {
    	                    @Override
    	                    public void buttonClick(ClickEvent event) {
    	                    	
    	                    	getUI().getNavigator().navigateTo("/home/compose_cv");


    	                    }
    	                    
    	                
    	                   });

    	                
    	                
    	                final Button appliedCompanies = new Button("Applied Companies"); 
    	                appliedCompanies.setStyleName(BaseTheme.BUTTON_LINK);
    	                addComponent(appliedCompanies);
    	                
    	                appliedCompanies.addClickListener(new ClickListener() {
    	                    @Override
    	                    public void buttonClick(ClickEvent event) {
    	                    	
    	                    	
    	                    	getUI().getNavigator().navigateTo("/home/applied_companies");

    	                    }
    	                    
    	                
    	                   });
    	                
    	                setExpandRatio(appliedCompanies, 1);
    	                

    				}
    				
    			});
    			
    			
    			addComponent(content);
    			content.setSizeFull();
    			content.addStyleName("subcontent");
    			setExpandRatio(content, 1);
    			
    			buildMainView();
    			
    		}
    		
    		
    		
    	});


    }
    
    private void buildMainView()
    {
    	content.removeAllComponents();
    	content.addComponent(new Label("this is home content"));
    			
    }
    
    private void buildViewProfileView()
    {
    	content.removeAllComponents();
    	content.addComponent(new Label("this is view profile"));
    	
    }
    
    private void buildComposeCVView()
    {
    	content.removeAllComponents();    	
    	content.addComponent(new StudentCvFormComp());

    }
    
    private void buildAppliedCompanyView()
    {
    	content.removeAllComponents();
    	content.addComponent(new Label("this is applied companies view"));
    	
    }

}
