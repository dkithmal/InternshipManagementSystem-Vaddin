package com.ims.components;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.ims.ImsUI;
import com.ims.business.AdminDAO;
import com.ims.data.User;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;

public class ChangeAdminProfileComp extends CustomComponent{
	
	AbsoluteLayout mainLayout;
	
	public ChangeAdminProfileComp()
	{
		setSizeFull();
		
		buildAdminChangeProfileView();
		setCompositionRoot(mainLayout);
				
	}
	
	
	
	
	private void buildAdminChangeProfileView()
	{
		mainLayout= new AbsoluteLayout();
		mainLayout.setSizeFull();
		
		mainLayout.addComponent(new Label("Change Admin Profile"),"top:10px;left:40%");
	
		FormLayout adminProfileChangeLayout= new FormLayout();
		

		
		final TextField userName= new TextField("New Admin UserName");
		
		AdminDAO adminDAO= (AdminDAO)ImsUI.context.getBean("AccessAdmin");
		userName.setValue(adminDAO.getAdminProfile(VaadinSession.getCurrent().getAttribute("UserName").toString()).getUserName());
		
		final PasswordField password= new PasswordField("New Password");
		final PasswordField conPassword= new PasswordField("Confrm password");
		
		conPassword.addValueChangeListener(new Property.ValueChangeListener(){
			
			
			public void valueChange(ValueChangeEvent event) {
				
				if(!(password.getValue().toString().equals(conPassword.getValue().toString())))
				{
					Notification.show("password and confrm passwod dose not match");
				}
			}
		});
		
		Button submit= new Button("Modify Admin Profile");
		
		submit.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				if(userName.getValue().isEmpty())
				{
					Notification.show("Username Cannot be empty");
					return;
					
				}
				
				if(password.getValue()==null)
				{
					Notification.show("you have to give new password");
					return;
				}
				if(!(password.getValue().toString().equals(conPassword.getValue().toString())))
				{
					Notification.show("password and confrm passwod dose not match");
					return;
				}
				
				
	        	String encriptedPassword=null;
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
            		encriptedPassword=sb.toString();
            		//user= loginDAO.findUser(username.getValue(), sb.toString());
				} catch (NoSuchAlgorithmException e) {
					
					e.printStackTrace();
				}
				
				
				User newAdmin= new User();
				newAdmin.setUserName(userName.getValue());
				newAdmin.setPassword(encriptedPassword);
				newAdmin.setType('a');
				newAdmin.setPrivilage('A');
				newAdmin.setBatch(0);
		
				AdminDAO adminDAO= (AdminDAO)ImsUI.context.getBean("AccessAdmin");
				adminDAO.changeAdminProfile(VaadinSession.getCurrent().getAttribute("UserName").toString(),newAdmin);
				
				Notification.show("Admin Profile Successfully Chainged");
				VaadinSession.getCurrent().setAttribute("UserName", userName.getValue());
				getUI().setNavigator(ImsUI.navigator);
				getUI().getNavigator().navigateTo("");
				
			}
		});
		
		adminProfileChangeLayout.addComponent(userName);
		adminProfileChangeLayout.addComponent(password);
		adminProfileChangeLayout.addComponent(conPassword);
		adminProfileChangeLayout.addComponent(submit);
		
		mainLayout.addComponent(adminProfileChangeLayout,"top:30px;left:10px");
		
		
		
	}

}
