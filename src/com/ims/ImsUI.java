package com.ims;



import javax.servlet.annotation.WebServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.ims.mainviews.CompanyRegisterView;
import com.ims.mainviews.LoginView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@Theme("ims")
public class ImsUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = ImsUI.class)
	public static class Servlet extends VaadinServlet {
	}
	
	public static Navigator navigator;
	public static ApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");
	

	
	
	@Override
	protected void init(VaadinRequest request) {
		
		navigator = new Navigator(this, this);    
		navigator.addView("", LoginView.class);
        navigator.addView("/new_company", CompanyRegisterView.class);
       
        

	}
	
	
	
	

}