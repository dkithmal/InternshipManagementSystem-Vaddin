package com.ims.mainviews;

import com.ims.components.AllowedCompanyViewComp;
import com.ims.components.CompanyProfileViewComp;
import com.ims.components.ManageCompanyCvViewComp;
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

        else if (event.getParameters().equals("cv_allowed_companies"))
        {
            buildCvAllowedCompanyView();

        }
        else if (event.getParameters().equals("cv_not_allowed_companies"))
        {
            buildCvNotAllowedCompanyView();

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

                        final Button cvAllowedCompanies = new Button("CV  allowed Companies");
                        cvAllowedCompanies.setStyleName(BaseTheme.BUTTON_LINK);
                        addComponent(cvAllowedCompanies);

                        cvAllowedCompanies.addClickListener(new ClickListener() {
                            @Override
                            public void buttonClick(ClickEvent event) {

                                getUI().getNavigator().navigateTo("/company/cv_allowed_companies");


                            }


                        });

                        final Button cvNotAllowedCompanies = new Button("CV Not allowed Companies");
                        cvNotAllowedCompanies.setStyleName(BaseTheme.BUTTON_LINK);
                        addComponent(cvNotAllowedCompanies);

                        cvNotAllowedCompanies.addClickListener(new ClickListener() {
                            @Override
                            public void buttonClick(ClickEvent event) {

                                getUI().getNavigator().navigateTo("/company/cv_not_allowed_companies");


                            }


                        });


    	                
    	                setExpandRatio(cvNotAllowedCompanies, 1);
    	                

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


    private void buildCvAllowedCompanyView()

    {

        content.removeAllComponents();
        content.addComponent(new ManageCompanyCvViewComp("CvAllowedCompany"));

    }


    private void buildCvNotAllowedCompanyView()
    {
        content.removeAllComponents();
        content.addComponent(new ManageCompanyCvViewComp("CvNotAllowedCompany"));

    }
}
