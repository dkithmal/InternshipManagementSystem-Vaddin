package com.ims.mainviews;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.ims.ImsUI;
import com.ims.business.LoginDAO;
import com.vaadin.event.ShortcutListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.BaseTheme;

public class LoginView extends VerticalLayout implements View{

	Navigator navigator;
	
	
	CssLayout root = new CssLayout();
	CssLayout mainMenu = new CssLayout();
	CssLayout content = new CssLayout();
	
	VerticalLayout loginLayout;
	
	final Button logout = new Button("Logout");  
	
	
	@Override
	public void enter(ViewChangeEvent event) {
		
		
		setSizeFull();
		addComponent(root);
		root.setSizeFull();
		root.addStyleName("root");

		
        String f = Page.getCurrent().getUriFragment();
        if (f != null && f.startsWith("!")) {
            f = f.substring(1);
        }
        if (f == null || f.equals("") || f.equals("/")||f.equals("#")) {
        	
        	try {
        		if(VaadinSession.getCurrent().getAttribute("UserName")==null)
        		{
        			buildLoginView(false);
        			
        		}
        		else
        		{
        			buildDashboardView();
        		}
				
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
        	
        	
        } 
        else {
        	buildDashboardView();
        }
		
	}
	
	
	private void buildLoginView(boolean exit) throws NoSuchAlgorithmException
	{
        if (exit) {
            root.removeAllComponents();
        }
        
        
       
        addStyleName("login");


        loginLayout = new VerticalLayout();
        loginLayout.setSizeFull();
        loginLayout.addStyleName("login-layout");
        root.addComponent(loginLayout);

        final CssLayout loginPanel = new CssLayout();
        loginPanel.addStyleName("login-panel");

        HorizontalLayout labels = new HorizontalLayout();
        labels.setWidth("100%");
        labels.setMargin(true);
        labels.addStyleName("labels");
        loginPanel.addComponent(labels);

        Label welcome = new Label("Welcome");
        welcome.setSizeUndefined();
        welcome.addStyleName("h4");
        labels.addComponent(welcome);
        labels.setComponentAlignment(welcome, Alignment.MIDDLE_LEFT);
              

        Label title = new Label("Internship Management System");
        title.setSizeUndefined();
        title.addStyleName("h2");
        title.addStyleName("light");
        labels.addComponent(title);
        labels.setComponentAlignment(title, Alignment.MIDDLE_CENTER);
        labels.setExpandRatio(title, 1);

        HorizontalLayout fields = new HorizontalLayout();
        fields.setSpacing(true);
        fields.setMargin(true);
        fields.addStyleName("fields");

        final TextField username = new TextField("Username");
        username.focus();
        fields.addComponent(username);

        final PasswordField password = new PasswordField("Password");
        fields.addComponent(password);

        final Button signin = new Button("Sign In");
        signin.addStyleName("default");
        fields.addComponent(signin);
        fields.setComponentAlignment(signin, Alignment.BOTTOM_LEFT);
        

        //creating register new comppny link button with in horizontal layout
        final Button newCompany = new Button("Register New Company");
        newCompany.setStyleName(BaseTheme.BUTTON_LINK);
        
        HorizontalLayout newCompanyLink= new HorizontalLayout();
        newCompanyLink.setWidth("100%");        
        newCompanyLink.addComponent(newCompany);
        newCompanyLink.setComponentAlignment(newCompany, Alignment.BOTTOM_RIGHT);
        
        

        final ShortcutListener enter = new ShortcutListener("Sign In",
                KeyCode.ENTER, null) {
            @Override
            public void handleAction(Object sender, Object target) {
                signin.click();
            }
        };

        signin.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
            	
            	if(username.getValue() != null && password.getValue() != null&& !username.getValue().equals("") && !password.getValue().equals(""))            	
            	{
            		LoginDAO  loginDAO= (LoginDAO)ImsUI.context.getBean("UserLogin");
            		String user=null;
            		
            		
            		
            		//enctrpt password and check user in database
					try {
						
						MessageDigest md;
						md = MessageDigest.getInstance("MD5");
						
	            		md.update(password.getValue().getBytes());
	                    byte byteData[] = md.digest();

	            		
	            		StringBuffer sb = new StringBuffer();
	            		for (int i = 0; i < byteData.length; i++) {
	            			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
	            					.substring(1));	  	            			
	                		
	            		}
	            		user= loginDAO.findUser(username.getValue(), sb.toString());
					} catch (NoSuchAlgorithmException e) {
						
						e.printStackTrace();
					}
					
					
					
					if(user=="error")
					{
						getUI().getNavigator().navigateTo("");
						//Page.getCurrent().setLocation("");
						return;
						
					}

					else if(user=="Admin")
            		{
            			getSession().setAttribute("UserName",username.getValue().toString());
            			getSession().setAttribute("Type", "Admin");
            			getSession().setAttribute("Privilage", "All");
            			
            			
            		}
            		else if(user=="Student-All")
            		{

            			getSession().setAttribute("UserName",username.getValue().toString());
            			getSession().setAttribute("Type", "Student");
            			getSession().setAttribute("Privilage", "All");
            			
            			
            		}
            		else if(user=="Student-ViewOnly")
            		{
            			getSession().setAttribute("UserName",username.getValue().toString());
            			getSession().setAttribute("Type", "Student");
            			getSession().setAttribute("Privilage", "View Only");
            			
            			
            		}
            		else if(user=="Company-All")
            		{
            			getSession().setAttribute("UserName",username.getValue().toString());
            			getSession().setAttribute("Type", "Company");
            			getSession().setAttribute("Privilage", "All");
            			
            		}
            		else if(user=="Company-ViewOnly")
            		{
            			getSession().setAttribute("UserName",username.getValue().toString());
            			getSession().setAttribute("Type", "Company");
            			getSession().setAttribute("Privilage", "ViewOnly");
            			
            		}
            		else if(user=="Company-SelectOnly")
            		{
            			getSession().setAttribute("UserName",username.getValue().toString());
            			getSession().setAttribute("Type", "Company");
            			getSession().setAttribute("Privilage", "SelectOnly");
            			
            		}
            		else if(user=="Comany-NotAllowed")
            		{
            			getSession().setAttribute("UserName",username.getValue().toString());
            			getSession().setAttribute("Type", "Company");
            			getSession().setAttribute("Privilage", "NotAllowed");
            			
            		}
            		

					System.out.println("user logged in"+VaadinSession.getCurrent().getAttribute("UserName").toString());
            		signin.removeShortcutListener(enter);
                    buildDashboardView();
    
            		
            	}
            	else {
                    if (loginPanel.getComponentCount() > 2) {
                        // Remove the previous error message
                        loginPanel.removeComponent(loginPanel.getComponent(2));
                    }
                    // Add new error message
                    Label error = new Label(
                            "Wrong username or password. <span>Hint: try empty values</span>",
                            ContentMode.HTML);
                    error.addStyleName("error");
                    error.setSizeUndefined();
                    error.addStyleName("light");
                    // Add animation
                    error.addStyleName("v-animate-reveal");
                    loginPanel.addComponent(error);
                    username.focus();
                }
            }
        });


        newCompany.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
            	
            	getUI().getNavigator().navigateTo("/newcompany");


            }
            
        
           });
        
        

        signin.addShortcutListener(enter);

        loginPanel.addComponent(fields);
        
        loginPanel.addComponent(newCompanyLink);

        loginLayout.addComponent(loginPanel);
        loginLayout.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
		
	}
	
	
	
	
	
	
	
	
	
	private void buildDashboardView()
	{
		
		 removeStyleName("login");
		 
	     if(!(loginLayout==null))
	        root.removeComponent(loginLayout);
	     
			
	        root.addComponent(new HorizontalLayout() {
	            {
	            	
	                setWidth("100%");
	                setHeight("34px");
	                
	                ThemeResource resource = new ThemeResource("img/university_logo.png");
	                Image logo = new Image(null,resource);
	               //addComponent(logo,"top:5px;left:0px;");
	                addComponent(logo);
	                setComponentAlignment(logo, Alignment.MIDDLE_LEFT);
	                logo.setStyleName("university-logo");
	                

	                Label info = new Label("Faculty of Information Technology - University of Moratuwa");
	                setStyleName("info");
	               // addComponent(info,"top:10px;left:50px;");
	                addComponent(info);
	                setComponentAlignment(info, Alignment.MIDDLE_LEFT);
	                setExpandRatio(info, 1);
	                
	                
	                HorizontalLayout rightPanel= new HorizontalLayout();	                	                
	                rightPanel.setSpacing(true);
	                
	                Label welcome = new Label("Welcome!");
	                welcome.addStyleName("info");


	                Label userName = new Label(VaadinSession.getCurrent().getAttribute("UserName").toString());
	                userName.addStyleName("info");
                
	                ThemeResource logoutRsource = new ThemeResource("img/wheel.png");	                
	                logout.setIcon(logoutRsource,null);	              
	                logout.setStyleName("logout.weel");
	                
	                rightPanel.addComponent(welcome);
	                rightPanel.addComponent(userName);
	                rightPanel.addComponent(logout);	                	                
	                addComponent(rightPanel);
	                setComponentAlignment(rightPanel, Alignment.MIDDLE_RIGHT);
	                
	                
	                //setExpandRatio(logout, 1);
	                logout.addClickListener(new ClickListener() {
	                    @Override
	                    public void buttonClick(ClickEvent event) {
              	
	                    	System.out.println("Logoff from session "+getSession().getAttribute("UserName"));
	                    	VaadinSession.getCurrent().setAttribute("UserName", null);
	                    	getUI().setNavigator(ImsUI.navigator);                    	
	                    	getUI().getNavigator().navigateTo("");



	                    }
	                    
	                
	                   });


	            }
	            });
	        
	        

	        
	       if(VaadinSession.getCurrent().getAttribute("Type").equals("Student")) 
	       {
	    	   buildStudentMainView();
	    	   return;
	    	   
	       }
	       if(VaadinSession.getCurrent().getAttribute("Type").equals("Company")) 
	       {
	    	   buildComapnyMainView();
	    	   return;
	    	   
	       }
	       if(VaadinSession.getCurrent().getAttribute("Type").equals("Admin")) 
	       {
	    	   buildAdminMainView();
	    	   return;
	    	   
	       }

	       
	        
	      
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void buildComapnyMainView()
	{
		
		navigator= new Navigator(getUI(),content);		
		navigator.addView("/home", CompanyHomeView.class);

		
		if(VaadinSession.getCurrent().getAttribute("Privilage").equals("NotAllowed"))
		{	
			
		}
		else
		{
			navigator.addView("/internship", CompanyInternshipView.class);
			
			
		}
		
		
		
		
		
		
        root.addComponent(new HorizontalLayout() {
            {
            	
                setWidth("100%");
                setHeight("40px");
                
                setStyleName("menu");
                
                final Button home = new Button("HOME");            
                addComponent(home);
                setComponentAlignment(home, Alignment.BOTTOM_LEFT);
                home.addClickListener(new ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                    	
                    	
                    	getUI().getNavigator().navigateTo("/home");


                    }
                    
                
                   });

                
             
				if(!(VaadinSession.getCurrent().getAttribute("Privilage").equals("NotAllowed")))
                {
                    
                    final Button internship = new Button("INTERNSHIP");                
                    addComponent(internship);
                    setComponentAlignment(internship, Alignment.BOTTOM_LEFT);
                    internship.addClickListener(new ClickListener() {
                        @Override
                        public void buttonClick(ClickEvent event) {
                        	                
                        	getUI().getNavigator().navigateTo("/internship");


                        }
                        
                    
                       });

                    
                    final Button messages = new Button("MESSAGE");                
                    addComponent(messages);
                    setComponentAlignment(messages, Alignment.BOTTOM_LEFT);
                    messages.addClickListener(new ClickListener() {
                        @Override
                        public void buttonClick(ClickEvent event) {

                        	getUI().getNavigator().navigateTo("/message");


                        }
                        
                    
                       });
                	
                }

                                                

                
                final Button suppoort = new Button("SUPPORT");                
                addComponent(suppoort);
                setComponentAlignment(suppoort, Alignment.BOTTOM_LEFT);
                suppoort.addClickListener(new ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                    	
                    	getUI().getNavigator().navigateTo("/home");


                    }
                    
                
                   });
                
                
                final Button aboutUs = new Button("ABOUTUS");                
                addComponent(aboutUs);
                setComponentAlignment(aboutUs, Alignment.BOTTOM_LEFT);
                aboutUs.addClickListener(new ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                    	
                    	getUI().getNavigator().navigateTo("/home");


                    }
                    
                
                   });


                setExpandRatio(aboutUs, 1);

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
        if (f == null || f.equals("") || f.equals("/")) {
            getUI().getNavigator().navigateTo("/home");
       
        } else {
        	try
        	{
        		getUI().getNavigator().navigateTo(f);
        		
        	}
        	catch(IllegalArgumentException e)
        	{
            	getUI().setNavigator(ImsUI.navigator); 
            	
            	//Page.getCurrent().setLocation("");

        	}
        	
        	
    
        }
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	private void buildStudentMainView()
	{
		navigator= new Navigator(getUI(),content);
		navigator.addView("/home", StudentHomeView.class);
		navigator.addView("/company", StudentCompanyView.class);
		
        root.addComponent(new HorizontalLayout() {
            {
            	
                setWidth("100%");
                setHeight("40px");
                
                setStyleName("menu");
                
                final Button home = new Button("HOME");            
                addComponent(home);
                setComponentAlignment(home, Alignment.BOTTOM_LEFT);
                home.addClickListener(new ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                    	
                    	
                    	getUI().getNavigator().navigateTo("/home");


                    }
                    
                
                   });

                
                final Button internship = new Button("INTERNSHIPS");                
                addComponent(internship);
                setComponentAlignment(internship, Alignment.BOTTOM_LEFT);
                internship.addClickListener(new ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                    	                
                    	getUI().getNavigator().navigateTo("/summary");


                    }
                    
                
                   });

                
                final Button company = new Button("COMPANY");                
                addComponent(company);
                setComponentAlignment(company, Alignment.BOTTOM_LEFT);
                company.addClickListener(new ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {

                    	getUI().getNavigator().navigateTo("/company");


                    }
                    
                
                   });

                
                final Button messages = new Button("MESSAGES");                
                addComponent(messages);
                setComponentAlignment(messages, Alignment.BOTTOM_LEFT);
                messages.addClickListener(new ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                    	
                    	getUI().getNavigator().navigateTo("/home");


                    }
                    
                
                   });
                final Button support = new Button("SUPPORT");                
                addComponent(support);
                setComponentAlignment(support, Alignment.BOTTOM_LEFT);
                support.addClickListener(new ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                    	
                    	getUI().getNavigator().navigateTo("/home");


                    }
                    
                
                   });

                setExpandRatio(support, 1);

            }
            });
        
        root.addComponent(content);
        content.setSizeFull();
        content.addStyleName("content");
        
       
        //setMargin(true);
        
        
       
        

        
        
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
        if (f == null || f.equals("") || f.equals("/")) {
            getUI().getNavigator().navigateTo("/home");
       
        } else {
        	getUI().getNavigator().navigateTo(f);
    
        }
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void buildAdminMainView()
	{
		
		navigator= new Navigator(getUI(),content);
		//navigator.addView("/summary", SummaryView.class);
		navigator.addView("/home", CompanyHomeView.class);
		navigator.addView("/student", AdminStudentView.class);
		navigator.addView("/company", AdminCompanyView.class);
		navigator.addView("/administration", AdminAdministrationView.class);
		
        root.addComponent(new HorizontalLayout() {
            {
            	
                setWidth("100%");
                setHeight("40px");
                
                setStyleName("menu");
                
                final Button home = new Button("HOME");            
                addComponent(home);
                setComponentAlignment(home, Alignment.BOTTOM_LEFT);
                home.addClickListener(new ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                    	
                    	
                    	getUI().getNavigator().navigateTo("/home");


                    }
                    
                
                   });

                
                final Button student = new Button("STUDENT");                
                addComponent(student);
                setComponentAlignment(student, Alignment.BOTTOM_LEFT);
                student.addClickListener(new ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                    	                
                    	getUI().getNavigator().navigateTo("/student");


                    }
                    
                
                   });

                
                final Button company = new Button("COMPANY");                
                addComponent(company);
                setComponentAlignment(company, Alignment.BOTTOM_LEFT);
                company.addClickListener(new ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {

                    	getUI().getNavigator().navigateTo("/company");


                    }
                    
                
                   });

                
                final Button administration = new Button("ADMINISTRATION");                
                addComponent(administration);
                setComponentAlignment(administration, Alignment.BOTTOM_LEFT);
                administration.addClickListener(new ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                    	
                    	getUI().getNavigator().navigateTo("/administration");


                    }
                    
                
                   });

                setExpandRatio(administration, 1);

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
        if (f == null || f.equals("") || f.equals("/")) {
            getUI().getNavigator().navigateTo("/home");
       
        } else {
        	getUI().getNavigator().navigateTo(f);
    
        }
		
	}
	
	
	
}
