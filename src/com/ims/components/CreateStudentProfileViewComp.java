package com.ims.components;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.ims.ImsUI;
import com.ims.business.StudentDAO;
import com.ims.data.User;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Upload.ChangeEvent;
import com.vaadin.ui.Upload.FinishedEvent;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

public class CreateStudentProfileViewComp extends CustomComponent{
	
	AbsoluteLayout mainLayout = new AbsoluteLayout();
	ProfilesUploader  receiver = new ProfilesUploader();
    public File file;
    StudentDAO studentDAO;
    
    boolean studentProfilesFileSelected=false;
    
	final TextField password= new TextField("Students Defult password");
	final TextField batch= new TextField("Student Batch");
	
	public CreateStudentProfileViewComp()
	{
		setSizeFull();
		buildCreateStudentProfileView();
		setCompositionRoot(mainLayout);
		
	}
	
	
	
	private void buildCreateStudentProfileView()
	{
		mainLayout.setSizeFull();
		
		mainLayout.addComponent(new Label("Create Student profiles"),"top:10px;left:40%");
		
		
		FormLayout studentProfilesCreateLayout= new FormLayout();
			
		final Upload upload = new Upload("Student profiles Doc", receiver);
		
		upload.setButtonCaption(null);
		upload.addSucceededListener(receiver);
		
		upload.addChangeListener(new Upload.ChangeListener() {
			
			@Override
			public void filenameChanged(ChangeEvent event) {
				
				if(!(event.getFilename()==null))
				{
					studentProfilesFileSelected=true;
					
				}
	
			}
		});
		
		
		upload.addFinishedListener(new Upload.FinishedListener() {
			
			@Override
			public void uploadFinished(FinishedEvent event) {
				System.out.println("upload finished");
				//getUI().getNavigator().navigateTo("");
			}
		});
		

		Button createProfiles= new Button("Create Profiles");
		
		createProfiles.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				if(!studentProfilesFileSelected)
				{
					Notification.show("Please Select Student Profiles Doc");
					return;
					
				}
				
				if(password.getValue().isEmpty())
				{					
					Notification.show("Please Enter Studnet Default Password");
					return;
				}
				if(batch.getValue().isEmpty())
				{					
					Notification.show("Please Enter Student batch");
					return;
				}
				
				upload.submitUpload();
				

			}
		});
		

		
		studentProfilesCreateLayout.addComponent(upload);
		studentProfilesCreateLayout.addComponent(password);
		studentProfilesCreateLayout.addComponent(batch);
		studentProfilesCreateLayout.addComponent(createProfiles);
		
		
		FormLayout singleStudentProfileLayout= new FormLayout();
		
		mainLayout.addComponent(new Label("Create Single Studnet Profile"),"top:200px;left:40%");

		
		final TextField studnetUserName= new TextField("Student index No");
		final TextField studentPassword= new TextField("Student Passowrd");
		final TextField batch2= new TextField("Student Batch");
		
		Button createSingleProfile= new Button("Create Single Student Profile");
		
		createSingleProfile.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				if(studnetUserName.getValue().isEmpty())
				{
					Notification.show("Please Enter Studnet IndexNo");
					return;
				}
				
				if(studentPassword.getValue().isEmpty())
				{					
					Notification.show("Please Enter Studnet Default Password");
					return;
				}
				
				if(batch2.getValue().isEmpty())
				{					
					Notification.show("Please Enter Student batch");
					return;
				}
				
				
				
				
				
	        	String encriptedPassword=null;
        		//enctrpt password 
				try {
					
					MessageDigest md;
					md = MessageDigest.getInstance("MD5");
					
            		md.update(studentPassword.getValue().getBytes());
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
				
				
				User newStudent= new User();
				newStudent.setUserName(studnetUserName.getValue());
				newStudent.setPassword(encriptedPassword);
				newStudent.setBatch(Integer.parseInt(batch2.getValue()));
				newStudent.setType('s');
				newStudent.setPrivilage('V');
				
				studentDAO= (StudentDAO)ImsUI.context.getBean("AccessStudent");
				studentDAO.createSingleStudentProfile(newStudent);
				
				Notification.show("New Student Profile Created Successfully");
				getUI().getNavigator().navigateTo("/administration/create_student_profiles");
				
				
				
			}
		});
		
		singleStudentProfileLayout.addComponent(studnetUserName);
		singleStudentProfileLayout.addComponent(studentPassword);
		singleStudentProfileLayout.addComponent(batch2);
		singleStudentProfileLayout.addComponent(createSingleProfile);
		
		
		
		mainLayout.addComponent(studentProfilesCreateLayout,"top:50px; left:10px");
		mainLayout.addComponent(singleStudentProfileLayout,"top:250px;left:10px");

		
		
		
	}
	
	
	
	public void createStudentProfiles()
	{
		studentDAO= (StudentDAO)ImsUI.context.getBean("AccessStudent");
		
		
    	String encriptedPassword=null;
		//enctrpt password 
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
		
		
		
		
		
		
		
		
	    try{

	        //Create object of FileReader
	        FileReader inputFile = new FileReader(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath()+"/WEB-INF/profiles/profiles.txt");

	        //Instantiate the BufferedReader Class
	        BufferedReader bufferReader = new BufferedReader(inputFile);

	        //Variable to hold the one line data
	        String line;

	        // Read file line by line and print on the console
	        while ((line = bufferReader.readLine()) != null)   {
	                //System.out.println(line);
				User newStudent= new User();
				newStudent.setUserName(line);
				newStudent.setPassword(encriptedPassword);
				newStudent.setBatch(Integer.parseInt(batch.getValue()));
				newStudent.setType('s');
				newStudent.setPrivilage('V');
				
				
				studentDAO.createSingleStudentProfile(newStudent);
	        	
	        	
	        }
	        //Close the buffer reader
	        bufferReader.close();
	        Notification.show("Studnets Profiles Created Successfully");
	        getUI().getNavigator().navigateTo("/administration/create_student_profiles");
	        
	        }catch(Exception e){
	        	
	        	Notification.show("Problem with Uploaded profiles file");
	               // System.out.println("Error while reading file line by line:" 
	               // + e.getMessage());                      
	        }
		
	}
	
	
	
	
	
	
	
	// Implement both receiver that saves upload in a file and
	// listener for successful upload
	class ProfilesUploader implements Receiver, SucceededListener {

	    
	    public OutputStream receiveUpload(String filename,
	                                      String mimeType) {
	    	

	        // Create upload stream
	        FileOutputStream fos = null; // Stream to write to
	        try {
	            // Open the file for writing.
	        	
	            file = new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath()+"/WEB-INF/profiles/profiles.txt");
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
	    	System.out.println("student profiles file uplaod successfully");
	    	createStudentProfiles();
	    
	    }
	};	

}
