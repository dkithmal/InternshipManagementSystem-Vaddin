package com.ims.mainviews;


import com.ims.ImsUI;
import com.ims.components.AboutUsViewComp;
import com.ims.components.CompanyRegistrationFormComp;
import com.ims.components.ContactUsViewComp;
import com.ims.components.SupportViewComp;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class CompanyRegisterView extends VerticalLayout implements View{
	
	Navigator navigator;
	
	
	CssLayout root = new CssLayout();
	CssLayout mainMenu = new CssLayout();
	CssLayout content = new CssLayout();
	
	VerticalLayout loginLayout;

	@Override
	public void enter(ViewChangeEvent event) {

		setSizeFull();
		addComponent(root);
		root.setSizeFull();
		root.addStyleName("root");
		buildMainView();
		
        String f = Page.getCurrent().getUriFragment();
        if (f != null && f.startsWith("!")) {
            f = f.substring(1);
        }
        if (f == null || f.equals("") || f.equals("/")) {
            //getUI().getNavigator().navigateTo("/home");
       
        } else {

        	if(f.equals("/newcompany/aboutus"))
        	{        		
        		 buildAboutUs();
        		
        	}
        		       		
        	if(f.equals("/newcompany/contactus"))
            {
        		buildContactUs();

        	}
        	if(f.equals("/newcompany/support"))
            {
        		buildSupport();
        		

        	}


        	
    
        }
		
	}
	
	
	
	private void buildMainView()
	{
   			
	        root.addComponent(new AbsoluteLayout() {
	            {
	            	
	                setWidth("100%");
	                setHeight("34px");
	                
	                ThemeResource resource = new ThemeResource("img/university_logo.png");
	                Image logo = new Image(null,resource);
	                addComponent(logo,"top:5px;left:0px;");
	                logo.setStyleName("university-logo");
	                

	                Label info = new Label("Faculty of Information Technology - University of Moratuwa");
	                setStyleName("info");
	                addComponent(info,"top:10px;left:50px;");



	            }
	            });
	        
	        
	        
	        buildComapnyRegistrationMainView();     
	        
	      
		
	}
	
	private void buildComapnyRegistrationMainView()
	{
		
		//navigator= new Navigator(getUI(),content);
		//navigator.addView("/registration_form", CompanyRegistrationFormView.class);
		//navigator.addView("/aboutus", AboutUsView.class);
		//navigator.addView("/contactus",ContactUsView.class);
		//navigator.addView("/support",SupportView.class);
		
        root.addComponent(new HorizontalLayout() {
            {
            	
                setWidth("100%");
                setHeight("40px");
                
                setStyleName("menu");
                
                final Button home = new Button("REGISTER");            
                addComponent(home);
                setComponentAlignment(home, Alignment.BOTTOM_LEFT);
                home.addClickListener(new ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                    	

                    	getUI().getNavigator().navigateTo("/newcompany");


                    }
                    
                
                   });

                
                final Button summary = new Button("ABOUT US");                
                addComponent(summary);
                setComponentAlignment(summary, Alignment.BOTTOM_LEFT);
                summary.addClickListener(new ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                    	                
                    	getUI().getNavigator().navigateTo("/newcompany/aboutus");


                    }
                    
                
                   });

                
                final Button messages = new Button("CONTACT US");                
                addComponent(messages);
                setComponentAlignment(messages, Alignment.BOTTOM_LEFT);
                messages.addClickListener(new ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {

                    	getUI().getNavigator().navigateTo("/newcompany/contactus");


                    }
                    
                
                   });

                
                final Button suppoort = new Button("SUPPORT");                
                addComponent(suppoort);
                setComponentAlignment(suppoort, Alignment.BOTTOM_LEFT);
                suppoort.addClickListener(new ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                    	
                    	getUI().getNavigator().navigateTo("/newcompany/support");


                    }
                    
                
                   });
                
                final Button login = new Button("LOGIN");                
                addComponent(login);
                setComponentAlignment(login, Alignment.BOTTOM_LEFT);
                login.addClickListener(new ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                   	
                    	
                    	//set fundamaintal navigater by using static navigater diclard in Main UI
                    	getUI().setNavigator(ImsUI.navigator);                    	
                    	getUI().getNavigator().navigateTo("");


                    }
                    
                
                   });

                setExpandRatio(login, 1);

            }
            });
        
        root.addComponent(content);
        content.setSizeFull();
        content.addStyleName("content");
        
        
        
        //setting foter in mainview
/*        HorizontalLayout footer = new HorizontalLayout();       
        footer.setWidth("100%");
        footer.setHeight("10px");
        footer.setStyleName("footer");
        
        addComponent(footer);*/
        setExpandRatio(root, 1);
        
        
        String f = Page.getCurrent().getUriFragment();
        if (f != null && f.startsWith("!")) {
            f = f.substring(1);
        }
/*        if (f == null || f.equals("") || f.equals("/")|| f.equals("/newcompany")) {
            getUI().getNavigator().navigateTo("/registration_form");
       
        } else {
        	//getUI().getNavigator().navigateTo("/registration_form");
        	//getUI().getNavigator().navigateTo(f);
    
        }*/
        

        if(f.equals("/newcompany"))
        {
        	buildRegistrationForm();
        	
        }

		
	}
	
	
	private void buildRegistrationForm()
	{

		content.removeAllComponents();
		content.addComponent(new CompanyRegistrationFormComp());
	}
	
	
	private void buildAboutUs()
	{
		content.removeAllComponents();
		content.addComponent(new AboutUsViewComp());
		
	}
	
	
	private void buildContactUs()
	{
		content.removeAllComponents();
		content.addComponent(new ContactUsViewComp());
		
	}
	
	private void buildSupport()
	{
		content.removeAllComponents();
		content.addComponent(new SupportViewComp());
		
	}
	
	
	
	

}




















