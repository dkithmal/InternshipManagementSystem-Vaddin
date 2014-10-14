package com.ims.mainviews;

import com.ims.ImsUI;
import com.ims.business.CompanyDAO;
import com.ims.components.AllowedCompanyViewComp;
import com.ims.components.CompanyProfileViewComp;
import com.ims.components.StudentAppliedCompayStateViewComp;
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

public class StudentCompanyView extends CssLayout implements View{
	
	CssLayout content = new CssLayout();
	CompanyDAO companyDAO;

	@Override
	public void enter(ViewChangeEvent event) {
		
        setSizeFull();
		
        buildCompnaniesView();
		
		if (event.getParameters().equals("all_companies")) {
			
			buildAllCompaniesView();

			
		}
		
		else if (event.getParameters().equals("appplied_companies"))
		{
			buildAppliedComapnyView();
			
		}
		else if (event.getParameters().equals("state_of_of_applied_companies"))
		{
			buildStateOfCompanyView();
			
		}
		
		
		//build company profile view with getting event data
		else
		{
			buildCompanyProfileView(event.getParameters().replace('_', ' '));
			
		}
		
		
	}
	
	
    private void buildCompnaniesView()
    {
    	
    	
    	addComponent(new HorizontalLayout(){
    		{
    			 setSizeFull();
    			addComponent(new VerticalLayout(){
    				{
    					
    					setHeight("100%");
    					setWidth(null);
    					addStyleName("submenu");

    	                final Button allCompanies = new Button("All Companies");
    	                allCompanies.setStyleName(BaseTheme.BUTTON_LINK);
    	                addComponent(allCompanies);
    	                
    	                allCompanies.addClickListener(new ClickListener() {
    	                    @Override
    	                    public void buttonClick(ClickEvent event) {
    	                    	
    	                    	getUI().getNavigator().navigateTo("/company/all_companies");


    	                    }
    	                    
    	                
    	                   });

    	                
    	                final Button appliedCompanies = new Button("Applied Compnanies");
    	                appliedCompanies.setStyleName(BaseTheme.BUTTON_LINK);
    	                addComponent(appliedCompanies);
    	                
    	                appliedCompanies.addClickListener(new ClickListener() {
    	                    @Override
    	                    public void buttonClick(ClickEvent event) {
    	                    	
    	                    	getUI().getNavigator().navigateTo("/company/appplied_companies");


    	                    }
    	                    
    	                
    	                   });

    	                
    	                
    	                final Button stateOfCompany = new Button("State of Applied Companies"); 
    	                stateOfCompany.setStyleName(BaseTheme.BUTTON_LINK);
    	                addComponent(stateOfCompany);
    	                
    	                stateOfCompany.addClickListener(new ClickListener() {
    	                    @Override
    	                    public void buttonClick(ClickEvent event) {
    	                    	
    	                    	
    	                    	getUI().getNavigator().navigateTo("/company/state_of_of_applied_companies");

    	                    }
    	                    
    	                
    	                   });
    	                
    	                setExpandRatio(stateOfCompany, 1);
    	                

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
    	content.addComponent(new Label("this is company view "));
    			
    }
    
    private void buildAllCompaniesView()
    {
    	content.removeAllComponents();
    	content.addComponent(new AllowedCompanyViewComp());
    	
    }
    
    private void buildAppliedComapnyView()
    {
    	content.removeAllComponents();
    	content.addComponent(new Label("this is applied company"));
    	


    }
    
    private void buildStateOfCompanyView()
    {
    	content.removeAllComponents();
    	//content.addComponent(new Label("this is state of the company view"));
        content.addComponent(new StudentAppliedCompayStateViewComp());
    	
    }
    
    
    
    private void buildCompanyProfileView(String comapnyName)
    {
    	System.out.println("its in build company profile view");
    	content.removeAllComponents();
    	companyDAO=(CompanyDAO)ImsUI.context.getBean("AccessCompany");
    	if(companyDAO.getCompanyState(comapnyName))
    		content.addComponent(new CompanyProfileViewComp(comapnyName,"name"));
    	else if(!comapnyName.equals(""))
    		getUI().getNavigator().navigateTo("/home");

    }

}
