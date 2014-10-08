package com.ims.mainviews;

import com.ims.components.AllowedCompanyViewComp;
import com.ims.components.CompanyProfileViewComp;
import com.ims.components.NotAllowedCompanyViewComp;
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

public class AdminCompanyView  extends CssLayout implements View{
	
	

	CssLayout content = new CssLayout();

	@Override
	public void enter(ViewChangeEvent event) {
		
		
        setSizeFull();
		
        buildCompanyMainView();
		
		if (event.getParameters().equals("allowed_companies")) {
			
			 buildAllowedCompanyView();

			
		}
		
		else if (event.getParameters().equals("not_allowed_companies"))
		{
			buildNotAllowedCompnayView();
			
		}
		
		else
		{
			buildCompanyProfileView(event.getParameters().replace('_', ' '));
			
		}

		
	}
	
	
	
	
	
	
	
	
    private void buildCompanyMainView()
    {
    	
    	addComponent(new HorizontalLayout(){
    		{
    			 setSizeFull();
    			addComponent(new VerticalLayout(){
    				{
    					
    					setHeight("100%");
    					setWidth(null);
    					addStyleName("submenu");

    	                final Button allowedCompanies = new Button("Allowed Companies");
    	                allowedCompanies.setStyleName(BaseTheme.BUTTON_LINK);
    	                addComponent(allowedCompanies);
    	                
    	                allowedCompanies.addClickListener(new ClickListener() {
    	                    @Override
    	                    public void buttonClick(ClickEvent event) {
    	                    	
    	                    	getUI().getNavigator().navigateTo("/company/allowed_companies");


    	                    }
    	                    
    	                
    	                   });

    	                
    	                final Button notAllowedCompanies = new Button("Not allowed Companies");
    	                notAllowedCompanies.setStyleName(BaseTheme.BUTTON_LINK);
    	                addComponent(notAllowedCompanies);
    	                
    	                notAllowedCompanies.addClickListener(new ClickListener() {
    	                    @Override
    	                    public void buttonClick(ClickEvent event) {
    	                    	
    	                    	getUI().getNavigator().navigateTo("/company/not_allowed_companies");


    	                    }
    	                    
    	                
    	                   });


    	                
    	                setExpandRatio(notAllowedCompanies, 1);
    	                

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
    
    private void buildAllowedCompanyView()
    {
    	content.removeAllComponents();
    	content.addComponent(new AllowedCompanyViewComp());
    	
    }
    
    private void buildNotAllowedCompnayView()
    {
    	content.removeAllComponents();
    	content.addComponent(new NotAllowedCompanyViewComp());

    }
    
    
    private void buildCompanyProfileView(String comapnyName)
    {
    	content.removeAllComponents();
    	content.addComponent(new CompanyProfileViewComp(comapnyName,"name"));
    }


}
