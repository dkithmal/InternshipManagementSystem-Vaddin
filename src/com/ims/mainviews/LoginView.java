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
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.BaseTheme;

public class LoginView extends VerticalLayout implements View{

    Navigator navigator;


    CssLayout root = new CssLayout();
    CssLayout content = new CssLayout();

    VerticalLayout loginLayout;

    final Button logout = new Button("Logout");


    @Override
    public void enter(ViewChangeEvent event) {


        setSizeFull();
        addComponent(root);
        root.setSizeFull();





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
                    String userRole=null;



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
                        userRole= loginDAO.findUser(username.getValue(), sb.toString());
                    } catch (NoSuchAlgorithmException e) {

                        e.printStackTrace();
                    }



                    if(userRole.equals("error"))
                    {
                        getUI().getNavigator().navigateTo("");
                        //Page.getCurrent().setLocation("");
                        return;

                    }
                    else
                    {
                        getSession().setAttribute("UserName",username.getValue().toString());

                        if(userRole.equals("Admin"))
                        {

                            getSession().setAttribute("Type", "Admin");
                            getSession().setAttribute("Privilege", "All");


                        }
                        else if(userRole.equals("Student-All"))
                        {
                            getSession().setAttribute("Type", "Student");
                            getSession().setAttribute("Privilege", "All");


                        }
                        else if(userRole.equals("Student-ViewOnly"))
                        {
                            getSession().setAttribute("Type", "Student");
                            getSession().setAttribute("Privilege", "View Only");


                        }
                        else if(userRole.equals("Company-All"))
                        {
                            getSession().setAttribute("Type", "Company");
                            getSession().setAttribute("Privilege", "All");

                        }
                        else if(userRole.equals("Company-ViewOnly"))
                        {
                            getSession().setAttribute("Type", "Company");
                            getSession().setAttribute("Privilege", "ViewOnly");

                        }
                        else if(userRole.equals("Company-SelectOnly"))
                        {
                            getSession().setAttribute("Type", "Company");
                            getSession().setAttribute("Privilege", "SelectOnly");

                        }
                        else if(userRole.equals("Company-NotAllowed"))
                        {
                            getSession().setAttribute("Type", "Company");
                            getSession().setAttribute("Privilege", "NotAllowed");

                        }

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

                getUI().getNavigator().navigateTo("/new_company");


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

        root.setSizeFull();
        root.setSizeUndefined();
        addComponent(content);
        content.setSizeFull();
        content.addStyleName("content");
        setExpandRatio(content, 1);

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
                        VaadinSession.getCurrent().setAttribute("Type", null);
                        VaadinSession.getCurrent().setAttribute("Privilege", null);
                        getUI().setNavigator(ImsUI.navigator);
                        getUI().getNavigator().navigateTo("");



                    }


                });


            }
        });




        if(VaadinSession.getCurrent().getAttribute("Type").equals("Student"))
        {
            buildStudentMainView();


        }
        if(VaadinSession.getCurrent().getAttribute("Type").equals("Company"))
        {
            buildCompanyMainView();


        }
        if(VaadinSession.getCurrent().getAttribute("Type").equals("Admin"))
        {
            buildAdminMainView();


        }




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

















    private void buildCompanyMainView()
    {

        navigator= new Navigator(getUI(),content);
        navigator.addView("/home", CompanyHomeView.class);


        if(!(VaadinSession.getCurrent().getAttribute("Privilege").equals("NotAllowed")))
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



                if(!(VaadinSession.getCurrent().getAttribute("Privilege").equals("NotAllowed")))
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

                            getUI().getNavigator().navigateTo("/home");


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

                        getUI().getNavigator().navigateTo("/home");


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


    }









}
