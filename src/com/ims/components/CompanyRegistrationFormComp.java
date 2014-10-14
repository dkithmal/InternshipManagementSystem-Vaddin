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
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.ChangeEvent;
import com.vaadin.ui.Upload.FinishedEvent;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

public class CompanyRegistrationFormComp extends CustomComponent{
	
	AbsoluteLayout mainLayout;
	final Embedded image = new Embedded("Uploaded Profile Picture");
	
	final BeanFieldGroup<Company> binder = new BeanFieldGroup<Company>(Company.class);
	RegisterCompanyDAO  registerCompanyDAO;
	ImageUploader receiver = new ImageUploader(); 

	boolean profilePicterSelected=false;
	
	public CompanyRegistrationFormComp()
	{
		
		setSizeFull();
		
		build();
		setCompositionRoot(mainLayout);
		
	}
	
	private void build()
	{
		mainLayout= new AbsoluteLayout();
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
        		
        		 Company company = new Company();	        
        	     binder.setItemDataSource(company);
        		
        	}
        		
        		
        	if(f.equals("/home/update_profile"))
            {
        		Company company = registerCompanyDAO.getExistingCompany(VaadinSession.getCurrent().getAttribute("UserName").toString());
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
	final PasswordField companyPassword= new PasswordField("Password");
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
		

		Panel companyRegisterFormPanel= new Panel();
		//set height to 90% other wise not visible last content of the panal
		companyRegisterFormPanel.setHeight("100%");

		//VerticalLayout layout = new VerticalLayout();
		//layout.setSizeFull();
		
		FormLayout registrationForm = new FormLayout();



		companyName.setRequired(true);
		image.setVisible(false);
		
		final Upload upload = new Upload("Profile picture", receiver);
		upload.setButtonCaption(null);
		upload.addSucceededListener(receiver);
		companyUserName.setRequired(true);
		companyPassword.setRequired(true);	
		companyConformPassword.setRequired(true);
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


		});
		
		Label comRegiLable= new Label("Company Registration Form");
		comRegiLable.addStyleName("comRegiLable");
		mainLayout.addComponent(comRegiLable,"left:40%; top: 5px");


		
		
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
				System.out.println("upload finished");
				getUI().getNavigator().navigateTo("");
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
					if(!(companyUserName.getValue()==null))
					{
						if(registerCompanyDAO.cheackCompanuyUserNameAlerayAvailable(companyUserName.getValue()))
						{
							Notification.show("This userName is already available Please Use another UserName");
							return;
							
						}
						
						
					}
					
					{

		        	binder.commit();
		        	
			        	Company newComapny = new Company();
			        	newComapny=binder.getItemDataSource().getBean();
			        	newComapny.setAllowed(false);
			        	newComapny.setReceiveCv(false);
			        	
			        	String encriptedPassword=null;
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
			        	
			        	User newUser= new User();
			        	newUser.setUserName(companyUserName.getValue());
			        	newUser.setPassword(encriptedPassword);
			        	newUser.setType('c');
			        	newUser.setPrivilage('A');			        	
			        	newUser.setBatch(0);
			        	
			        	registerCompanyDAO.saveNewComapny(newComapny,newUser);
			        	
			        	if(profilePicterSelected)
			        	{
			        		Notification.show("Thank You For Register In System");
			        		upload.submitUpload();
			        		

			        		
			        	}
			        	else
			        	{
			        		Notification.show("Thank You For Register In System");
			        		getUI().getNavigator().navigateTo("");
			        	}


			        	
			        	
			        	//this navigeter set when upload finish in upload fihish listneer
			        	//getUI().getNavigator().navigateTo("");
						
					}


		        	
		        } catch (InvalidValueException e) {
		            Notification.show(e.getMessage());
		        } catch (CommitException e) {
							        	
		        	System.out.println("comman execption occured");
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

		//companyRegisterFormPanel.setContent(registrationForm);
		companyRegisterFormPanel.setContent(layout);


		//mainLayout.addComponent(companyRegisterFormPanel,"bottom:75px;left: 0px; top: 40px;right:0px");
		mainLayout.addComponent(companyRegisterFormPanel,"bottom:75px;left: 0px; top: 40px;right:0px");


		  
		  
		
		
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
