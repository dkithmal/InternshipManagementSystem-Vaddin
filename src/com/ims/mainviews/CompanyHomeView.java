package com.ims.mainviews;

import com.ims.components.CompanyProfileViewComp;
import com.ims.components.UpdateCompanyProfileComp;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.BaseTheme;

public class CompanyHomeView extends CssLayout implements View{
	CssLayout content = new CssLayout();
    Panel contentPanel= new Panel();

	@Override
	public void enter(ViewChangeEvent event) {
		setSizeFull();
		
		buildHomeMainView();
		
		if (event.getParameters().equals("view_profile")) {
			
			buildViewProfileView();

			
		}
		
/*		else if (event.getParameters().equals("update_vacancies"))
		{
			buildUpdateVacanciesView();
			
		}*/
		else if (event.getParameters().equals("update_profile"))
		{
			buildUpdateProfileView();
			
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

    	                
    	                final Button updateProfile = new Button("Update Profile");
                        updateProfile.setStyleName(BaseTheme.BUTTON_LINK);
    	                addComponent(updateProfile);

                        updateProfile.addClickListener(new ClickListener() {
    	                    @Override
    	                    public void buttonClick(ClickEvent event) {
    	                    	
    	                    	
    	                    	getUI().getNavigator().navigateTo("/home/update_profile");

    	                    }
    	                    
    	                
    	                   });
    	                
    	                setExpandRatio(updateProfile, 1);
    	                

    				}
    				
    			});
    			
    			
    			//addComponent(content);
    			content.setSizeFull();
    			content.addStyleName("subcontent");
               // setExpandRatio(content, 1);
                addComponent(contentPanel);
                contentPanel.setSizeFull();
                setExpandRatio(contentPanel,1);

    			
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
        content.addComponent(new CompanyProfileViewComp(VaadinSession.getCurrent().getAttribute("UserName").toString(), "userName"));
        contentPanel.setContent(content);


    	
    }
    
/*    private void buildUpdateVacanciesView()
    {
    	content.removeAllComponents();
    	content.addComponent(new Label("this is update vacany"));
    	
    }*/
    
    private void buildUpdateProfileView()
    {
    	content.removeAllComponents();
    	content.addComponent(new UpdateCompanyProfileComp());
        contentPanel.setContent(content);
    	
    }
    

}
