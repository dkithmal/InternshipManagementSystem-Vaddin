package com.ims.components;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.ims.ImsUI;
import com.ims.business.RegisterCompanyDAO;
import com.ims.data.Company;
import com.ims.data.User;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Upload.ChangeEvent;
import com.vaadin.ui.Upload.FinishedEvent;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

public class UpdateCompanyProfileComp extends CustomComponent{
	
	VerticalLayout mainLayout;
    Panel mainLayoutPanel= new Panel();
	final Embedded image = new Embedded("Uploaded Profile Picture");
	
	final BeanFieldGroup<Company> binder = new BeanFieldGroup<Company>(Company.class);
	RegisterCompanyDAO  registerCompanyDAO;
	
	Company company;
	User user;
	boolean profilePicterSelected=false;
	
	
	public UpdateCompanyProfileComp()
	{
		
		setSizeFull();

        buildUpdateCompanyProfile();



        mainLayoutPanel.setContent(mainLayout);
        mainLayoutPanel.setSizeFull();
        mainLayoutPanel.addStyleName("mainLayoutPanel");
        setCompositionRoot(mainLayoutPanel);
		
	}
	
	private void buildUpdateCompanyProfile()
	{
		mainLayout= new VerticalLayout();
		mainLayout.setSizeFull();
		
		registerCompanyDAO= (RegisterCompanyDAO)ImsUI.context.getBean("RegisterComapny");
		
		
        String f = Page.getCurrent().getUriFragment();
        if (f != null && f.startsWith("!")) {
            f = f.substring(1);
        }
        if (f == null || f.equals("") || f.equals("/")) {
            //getUI().getNavigator().navigateTo("/home");
       
        } else {
        	

        	
        	if(f.equals("/newcompany"))
        	{
        		
        		 company = new Company();	        
        	     binder.setItemDataSource(company);
        		
        	}
        		
        		
        	if(f.equals("/home/update_profile"))
            {
        		company = registerCompanyDAO.getExistingCompany(VaadinSession.getCurrent().getAttribute("UserName").toString());
        		user= registerCompanyDAO.getExistingUser(VaadinSession.getCurrent().getAttribute("UserName").toString());
        		binder.setItemDataSource(company);


        	}
        	
    
        }
        
        
		buildRegistrationFomView();
		
	}

	
	@PropertyId("companyName")
	final TextField companyName =  new TextField("Name of the Company");
	@PropertyId("aboutCompany")
	final TextArea aboutCompany =new TextArea("About the Company");
	@PropertyId("companyUserName")
	final TextField companyUserName = new TextField("UserName");
	@PropertyId("companyPassword")
	final PasswordField companyPassword= new PasswordField("New Password");
	@PropertyId("companyConformPassword")
	final PasswordField companyConformPassword= new PasswordField("Conform Password");
	@PropertyId("technologies")
	final TextArea companyTechnologies = new TextArea("Technologies");
	@PropertyId("companyTelephone")
	final TextField companyTelephone = new TextField("Telephone");
	@PropertyId("companyAddress")
	final TextField companyAddress = new TextField("Address");
	@PropertyId("companyWeb")
	final TextField companyUrl = new TextField("URL");
	@PropertyId("companyEmail")
	final TextField companyEmail = new TextField("Email");
	@PropertyId("contactPerson")
	final TextField contactPersionName = new TextField("Contact person Name");
	@PropertyId("contactPersonTelNo")
	final TextField contactPersonNumer = new TextField("Contact persion Number");
	@PropertyId("contactPersonEmail")
	final TextField contactPersionEmail = new TextField("Contact persion Email");
	@PropertyId("alContactPerson")
	final TextField altContactPersionName = new TextField("Alternative Contact Persion Name");
	@PropertyId("alContactPersonTelNo")
	final TextField altContactPersionNumber = new TextField("Alternative Contact Persion Number");	
	@PropertyId("alContactPersonEmail")
	final TextField altContactPersionEmail = new TextField("Alternative Contact Persion Email");
	

	
	
	
	public void buildRegistrationFomView()
	{
		FormLayout registrationForm = new FormLayout();
		        
		

		companyName.setRequired(true);
		image.setVisible(false);
		ImageUploader receiver = new ImageUploader(); 
		final Upload upload = new Upload("Profile picture", receiver);
		upload.setButtonCaption(null);
		upload.addSucceededListener(receiver);
		companyUserName.setRequired(true);
		companyUserName.setReadOnly(true);
		companyUserName.setEnabled(false);
		companyTechnologies.setRequired(true);
		companyTelephone.setRequired(true);
		companyAddress.setRequired(true);
		companyEmail.setRequired(true);
		companyEmail.addValidator(new EmailValidator("Enter correct email address"));
		companyEmail.setImmediate(true);
		contactPersionName.setRequired(true);
		contactPersonNumer.setRequired(true);
		contactPersionEmail.setRequired(true);
		
		
		binder.bindMemberFields(this);
		
		//set lisners 
		companyConformPassword.addValueChangeListener(new Property.ValueChangeListener() {

			public void valueChange(ValueChangeEvent event) {

				if(!companyPassword.getValue().equals(companyConformPassword.getValue()))
				{
					Notification.show("password and confrm passwod not match");
					
				}

		        
		    }


		});
		
		
		 //registerCompanyDAO= (RegisterCompanyDAO)ImsUI.context.getBean("RegisterComapny");
/*		 
		companyUserName.addValueChangeListener(new Property.ValueChangeListener() {

			public void valueChange(ValueChangeEvent event) {
		
			if(!companyUserName.getValue().isEmpty())
				{
					if(registerCompanyDAO.cheackCompanuyUserNameAlerayAvailable(companyUserName.getValue()))
					{
						Notification.show("This userName is already available Please Use another UserName");
						
					}
					
				}

		    }


		});*/
		
		Label comRegiLable= new Label("Company Profile Update Form");
		comRegiLable.addStyleName("comRegiLable");
        comRegiLable.setSizeUndefined();
		mainLayout.addComponent(comRegiLable);
        mainLayout.setComponentAlignment(comRegiLable,Alignment.TOP_CENTER);



		
		
		registrationForm.addComponent(companyName);
		registrationForm.addComponent(image);
		registrationForm.addComponent(upload);
		registrationForm.addComponent(aboutCompany);
		registrationForm.addComponent(companyUserName);
		registrationForm.addComponent(companyPassword);
		registrationForm.addComponent(companyConformPassword);
		registrationForm.addComponent(companyTechnologies);
		registrationForm.addComponent(companyTelephone);
		registrationForm.addComponent(companyAddress);
		registrationForm.addComponent(companyUrl);
		registrationForm.addComponent(companyEmail);
		registrationForm.addComponent(contactPersionName);
		registrationForm.addComponent(contactPersonNumer);
		registrationForm.addComponent(contactPersionEmail);
		registrationForm.addComponent(altContactPersionName);
		registrationForm.addComponent(altContactPersionNumber);
		registrationForm.addComponent(altContactPersionEmail);
		
		
		
		upload.addChangeListener(new Upload.ChangeListener() {
			
			@Override
			public void filenameChanged(ChangeEvent event) {
				
				if(!(event.getFilename()==null))
				{
					profilePicterSelected=true;
					
				}
	
			}
		});
		
		upload.addFinishedListener(new Upload.FinishedListener() {
			
			@Override
			public void uploadFinished(FinishedEvent event) {
				// TODO Auto-generated method stub
				System.out.println("upload finished");
				getUI().getNavigator().navigateTo("/home");
			}
		});

		

		
		
		
		
		
		registrationForm.setSizeUndefined();
		registrationForm.setMargin(true);

		        
		registrationForm.addComponent(new Button("Commit",
		    new Button.ClickListener() {
		    @Override
		    public void buttonClick(ClickEvent event) {
	   
		        try {
		        	//binder.commit();
		        	
					if(!companyPassword.getValue().equals(companyConformPassword.getValue()))
					{
						Notification.show("password and confrm passwod not match");
						return;
						
					}
/*					if(!companyUserName.getValue().isEmpty())
					{
						if(registerCompanyDAO.cheackCompanuyUserNameAlerayAvailable(companyUserName.getValue()))
						{
							Notification.show("This userName is already available Please Use another UserName");
							return;
							
						}
						
						
					}*/
					
					{
						upload.submitUpload();
			        	binder.commit();
		        	
			        	Company newComapny = new Company();
			        	newComapny=binder.getItemDataSource().getBean();
			        	newComapny.setAllowed(false);
			        	newComapny.setReceiveCv(false);
			        	
			        	String encriptedPassword=null;			        
			        	User newUser= new User();
			        	
			        	if(!companyPassword.getValue().isEmpty())
			        	{
		            		//enctrpt password and check user in database
							try {
								
								MessageDigest md;
								md = MessageDigest.getInstance("MD5");
								
			            		md.update(companyPassword.getValue().getBytes());
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
				        	
				        	
				        	newUser.setUserName(companyUserName.getValue());
				        	newUser.setPassword(encriptedPassword);
				        	newUser.setType('c');
				        	newUser.setPrivilage('A');			        	
				        	newUser.setBatch(0);
			        		
			        	}
			        	else
			        	{
			        		newUser=user;
			        	}

			        	
			        	registerCompanyDAO.updateCompany(newComapny,newUser);
			        	
			        	
			        	if(profilePicterSelected)
			        	{
			        		Notification.show("Updated Company Profile");
			        		upload.submitUpload();
			        		

			        		
			        	}
			        	else
			        	{
			        		Notification.show("Updated Company Profile");
			        		getUI().getNavigator().navigateTo("/home");
			        	}

			        	
			        	//Notification.show("Updated Company Profile");
			        	//getUI().getNavigator().navigateTo("/home");
						
					}


		        	
		        } catch (InvalidValueException e) {
		            Notification.show(e.getMessage());
		        } catch (CommitException e) {
							        	
		        	System.out.println("comman execption##########################################################");
					//e.printStackTrace();
				}
		    }
		}));

		registrationForm.addComponent(new Button("Discard",
		    new Button.ClickListener() {
		    @Override
		    public void buttonClick(ClickEvent event) {
		        //editor.discard();
		    }
		}));


       //this layout created bacause of need to set allinment millde of the registration form
		HorizontalLayout layout= new HorizontalLayout();
		layout.addComponent(registrationForm);
		layout.setComponentAlignment(registrationForm, Alignment.MIDDLE_CENTER);
		layout.setSizeFull();


		mainLayout.addComponent(layout);
        mainLayout.setExpandRatio(layout,1);









    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	// Implement both receiver that saves upload in a file and
	// listener for successful upload
	class ImageUploader implements Receiver, SucceededListener {
	    public File file;
	    
	    public OutputStream receiveUpload(String filename,
	                                      String mimeType) {
	        // Create upload stream
	        FileOutputStream fos = null; // Stream to write to
	        try {
	            // Open the file for writing.
	        	
	            file = new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath()+"/WEB-INF/images/" + companyUserName.getValue()+".png");
	            fos = new FileOutputStream(file);
	        } catch (final java.io.FileNotFoundException e) {
	            new Notification("Could not open file<br/>",
	                             e.getMessage(),
	                             Notification.Type.ERROR_MESSAGE)
	                .show(Page.getCurrent());
	            return null;
	        }
	        return fos; // Return the output stream to write to

	    }

	    public void uploadSucceeded(SucceededEvent event) {
	        // Show the uploaded file in the image viewer
	       // image.setVisible(true);
	       // image.setSource(new FileResource(file));
	    }
	};	
	
	
	

}
