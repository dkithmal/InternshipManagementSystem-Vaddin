package com.ims.mainviews;

import com.ims.components.ManageStudentComp;
import com.ims.components.UpdateAdminProfileComp;
import com.ims.components.CreateStudentProfileViewComp;
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

public class AdminAdministrationView extends CssLayout implements View{
	
	CssLayout content = new CssLayout();
	
	@Override
	public void enter(ViewChangeEvent event) {
        setSizeFull();
		
		buildHomeMainView();
		
		if (event.getParameters().equals("create_student_profiles")) {
			
			buildCreateStudentProfileView();

			
		}
		
		else if (event.getParameters().equals("remove_student_form_system"))
		{
			buildRemoveStudentFormSysetmView();
			
		}
		else if (event.getParameters().equals("manage_student"))
		{
			buildManageStudentLoginView();
			
		}
		else if (event.getParameters().equals("change_username_password"))
		{
			buildChangeusernamePassowrdnView();
			
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

    	                final Button createStudentProfiles = new Button("Create Student Profiles");
    	                createStudentProfiles.setStyleName(BaseTheme.BUTTON_LINK);
    	                addComponent(createStudentProfiles);
    	                
    	                createStudentProfiles.addClickListener(new ClickListener() {
    	                    @Override
    	                    public void buttonClick(ClickEvent event) {
    	                    	
    	                    	getUI().getNavigator().navigateTo("/administration/create_student_profiles");


    	                    }
    	                    
    	                
    	                   });

    	                
    	                final Button removeStudentFormSystem = new Button("Remove Student Form System");
    	                removeStudentFormSystem.setStyleName(BaseTheme.BUTTON_LINK);
    	                addComponent(removeStudentFormSystem);
    	                
    	                removeStudentFormSystem.addClickListener(new ClickListener() {
    	                    @Override
    	                    public void buttonClick(ClickEvent event) {
    	                    	
    	                    	getUI().getNavigator().navigateTo("/administration/remove_student_form_system");


    	                    }
    	                    
    	                
    	                   });

    	                
    	                
    	                final Button manageStudentLogin = new Button("Manage Student Login"); 
    	                manageStudentLogin.setStyleName(BaseTheme.BUTTON_LINK);
    	                addComponent(manageStudentLogin);
    	                
    	                manageStudentLogin.addClickListener(new ClickListener() {
    	                    @Override
    	                    public void buttonClick(ClickEvent event) {
    	                    	
    	                    	
    	                    	getUI().getNavigator().navigateTo("/administration/manage_student");

    	                    }
    	                    
    	                
    	                   });
    	                
    	                
    	                final Button changeUserName = new Button("Change UserName Password"); 
    	                changeUserName.setStyleName(BaseTheme.BUTTON_LINK);
    	                addComponent(changeUserName);
    	                
    	                changeUserName.addClickListener(new ClickListener() {
    	                    @Override
    	                    public void buttonClick(ClickEvent event) {
    	                    	
    	                    	
    	                    	getUI().getNavigator().navigateTo("/administration/change_username_password");

    	                    }
    	                    
    	                
    	                   });
    	                
    	                setExpandRatio(changeUserName, 1);
    	                

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
    
    private void buildCreateStudentProfileView()
    {
    	content.removeAllComponents();
    	content.addComponent(new CreateStudentProfileViewComp());
    	
    }
    
    private void buildRemoveStudentFormSysetmView()
    {
    	content.removeAllComponents();
    	content.addComponent(new Label("this is compose cv view"));
    	

    }
    
    private void buildManageStudentLoginView()
    {
    	content.removeAllComponents();
    	content.addComponent(new ManageStudentComp());
    	
    }
    
    private void buildChangeusernamePassowrdnView()
    {
    	content.removeAllComponents();
    	content.addComponent(new UpdateAdminProfileComp());
    	
    }



}
