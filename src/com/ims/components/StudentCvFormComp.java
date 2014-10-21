package com.ims.components;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.ims.ImsUI;
import com.ims.business.RegisterStudentDAO;
import com.ims.business.StudentDAO;
import com.ims.data.Student;
import com.ims.data.StudentComplitedProjects;
import com.ims.data.StudentOtherQulification;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.PropertyId;
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

public class StudentCvFormComp extends CustomComponent {
	
	VerticalLayout mainLayout;
    Panel mainLayoutPanel= new Panel();
	
	final BeanFieldGroup<Student> binder = new BeanFieldGroup<Student>(Student.class);
	ImageUploader receiver = new ImageUploader(); 
	boolean profilePicterSelected=false;
	
	RegisterStudentDAO registerStudentDAO;
    StudentDAO studentDAO;
    Student student;
    boolean updateStudent;
	
	int projectCount=1;
	int extraCurricularActivityCount=1;
	
	//personal information
	@PropertyId("nameInFull")
	final TextField nameInFull = new TextField("Name In Full","");
	@PropertyId("nameWithInitials")
	final TextField nameWithInitials = new TextField("Name Wish Initials","");
	@PropertyId("objectives")
	final TextArea objective= new TextArea("Objective");
	@PropertyId("profile")
	final TextArea profile= new TextArea("Profile");
	@PropertyId("dateOfBirth")
	final TextField dateofBirth = new TextField("Date of birth");
	@PropertyId("gender")
	final ComboBox  gender = new ComboBox("Gender");
	@PropertyId("maritalStatus")
	final ComboBox maritalStatus = new ComboBox("Marital Status");	
	@PropertyId("permanentAddress")
	final TextField address = new TextField("Address");	
	@PropertyId("mobile")
	final TextField contactNo = new TextField("Contact No");	
	@PropertyId("email")
	final TextField emailAddress = new TextField("Email Address");
	
	
	//Education Qualification
	@PropertyId("digreeTitle")
	final ComboBox digree = new ComboBox("Digree");
	@PropertyId("yearOfAdmission")
	final ComboBox batch = new ComboBox("Batch");	
	@PropertyId("gpaSemester1")
	final TextField semester1Gpa= new TextField("Semester 1 Gpa");
	@PropertyId("gpaSemester2")
	final TextField semester2Gpa= new TextField("Semester 2 Gpa");
	@PropertyId("gpaSemester3")
	final TextField semester3Gpa= new TextField("Semester 3 Gpa");	
	@PropertyId("school")
	final TextField school = new TextField("School");	
	@PropertyId("alSubject1")
	final TextField alResultSubject1 = new TextField("Al result subject 1");
	@PropertyId("alResult1")
	final ComboBox alresult1= new ComboBox("Al result");
	@PropertyId("alSubject2")
	final TextField alResultSubject2 = new TextField("Al result subject 2");	
	@PropertyId("alResult2")
	final ComboBox alresult2= new ComboBox("Al result2");	
	@PropertyId("alSubject3")
	final TextField alResultSubject3 = new TextField("Al result subject 3");
	@PropertyId("alResult3")
	final ComboBox alresult3= new ComboBox("Al result3");
	
	//projects
	final TextField projectTitle1= new TextField("Complited project title 1");	
	final TextArea  projectDescription1 = new TextArea("Skils and Discription 1");
	final TextField projectTitle2= new TextField("Complited project title  2");	
	final TextArea  projectDescription2 = new TextArea("Skils and Discription 2");
	final TextField projectTitle3= new TextField("Complited project title 3");	
	final TextArea  projectDescription3 = new TextArea("Skils and Discription 3");
	final TextField projectTitle4= new TextField("Complited project title 4");	
	final TextArea  projectDescription4 = new TextArea("Skils and Discription 4");
	final TextField projectTitle5= new TextField("Complited project title 5");	
	final TextArea  projectDescription5 = new TextArea("Skils and Discription 5");
	final TextField projectTitle6= new TextField("Complited project title 6");	
	final TextArea  projectDescription6 = new TextArea("Skils and Discription 6");
	final TextField projectTitle7= new TextField("Complited project title 7");	
	final TextArea  projectDescription7 = new TextArea("Skils and Discription 7");
	final TextField projectTitle8= new TextField("Complited project title 8");	
	final TextArea  projectDescription8 = new TextArea("Skils and Discription 8");
	final TextField projectTitle9= new TextField("Complited project title 9");	
	final TextArea  projectDescription9 = new TextArea("Skils and Discription 9");
	final TextField projectTitle10= new TextField("Complited project title 10");	
	final TextArea  projectDescription10 = new TextArea("Skils and Discription 10");
	
	
	
	//Skills
	final TextArea  skillsAndExperties = new TextArea("Skils and Experties");
	
	//Extra Curricular activities
	final TextField exterCurricularActivityTital1= new TextField("Extra Curriculaer activities tital 1");	
	final TextArea extraCurricularActivityDiscption1 = new TextArea("Extra curriculaer activity discription 1");
	final TextField exterCurricularActivityTital2= new TextField("Extra Curriculaer activities tital 2");	
	final TextArea extraCurricularActivityDiscption2 = new TextArea("Extra curriculaer activity discription 2");
	final TextField exterCurricularActivityTital3= new TextField("Extra Curriculaer activities tital 3");	
	final TextArea extraCurricularActivityDiscption3= new TextArea("Extra curriculaer activity discription 3");
	final TextField exterCurricularActivityTital4= new TextField("Extra Curriculaer activities tital 4");	
	final TextArea extraCurricularActivityDiscption4 = new TextArea("Extra curriculaer activity discription 4");
	final TextField exterCurricularActivityTital5= new TextField("Extra Curriculaer activities tital 5");	
	final TextArea extraCurricularActivityDiscption5 = new TextArea("Extra curriculaer activity discription 5");



    public StudentCvFormComp(boolean updateStudent)
	{
		setSizeFull();
		registerStudentDAO=(RegisterStudentDAO)ImsUI.context.getBean("RegisterStudent");
        studentDAO = (StudentDAO)ImsUI.context.getBean("AccessStudent");

        this.updateStudent=updateStudent;

        if(updateStudent)
        {
            student=studentDAO.getStudent(VaadinSession.getCurrent().getAttribute("UserName").toString());

        }
        else
        {
            student= new Student();

        }

		binder.setItemDataSource(student);
		
		
		buildStudentCVForm();
        mainLayoutPanel.setContent(mainLayout);
        mainLayoutPanel.setSizeFull();
        mainLayoutPanel.addStyleName("mainLayoutPanel");
        setCompositionRoot(mainLayoutPanel);
		
		
	}
	
	private void buildStudentCVForm()
	{
		mainLayout= new VerticalLayout();
		mainLayout.setSizeFull();
		
		binder.bindMemberFields(this);
		

		

		Label studentRegiLable= new Label("Student CV Creation Form");
		studentRegiLable.addStyleName("comRegiLable");
        studentRegiLable.setSizeUndefined();
		mainLayout.addComponent(studentRegiLable);
        mainLayout.setComponentAlignment(studentRegiLable,Alignment.TOP_CENTER);
		
		
		
	

		//VerticalLayout layout = new VerticalLayout();
		//layout.setSizeFull();
		
		FormLayout registrationForm = new FormLayout();


		gender.addItem("Male");
		gender.addItem("Female");
		
		maritalStatus.addItem("Single");
		maritalStatus.addItem("Married");
		
		digree.addItem("BSc(Hons)Information Technology");
		digree.addItem("BSc(Hons)Information Technology & Management");
		
		
		batch.addItem(2010);
		batch.addItem(2011);
		batch.addItem(2012);
		batch.addItem(2013);
		batch.addItem(2014);
		batch.addItem(2015);

		alresult1.addItem("A");
		alresult1.addItem("B");
		alresult1.addItem("C");
		alresult1.addItem("S");

		alresult2.addItem("A");
		alresult2.addItem("B");
		alresult2.addItem("C");
		alresult2.addItem("S");
		

		alresult3.addItem("A");
		alresult3.addItem("B");
		alresult3.addItem("C");
		alresult3.addItem("S");
		
	    projectTitle2.setVisible(false);
		projectDescription2.setVisible(false);
	    projectTitle3.setVisible(false);
		projectDescription3.setVisible(false);
	    projectTitle4.setVisible(false);
		projectDescription4.setVisible(false);
	    projectTitle5.setVisible(false);
		projectDescription5.setVisible(false);
	    projectTitle6.setVisible(false);
		projectDescription6.setVisible(false);
	    projectTitle7.setVisible(false);
		projectDescription7.setVisible(false);
	    projectTitle8.setVisible(false);
		projectDescription8.setVisible(false);
	    projectTitle9.setVisible(false);
		projectDescription9.setVisible(false);
	    projectTitle10.setVisible(false);
		projectDescription10.setVisible(false);
		
		//exterCurricularActivityTital1.setVisible(false);	
		//extraCurricularActivityDiscption1.setVisible(false);	
		exterCurricularActivityTital2.setVisible(false);	
	    extraCurricularActivityDiscption2.setVisible(false);	
		exterCurricularActivityTital3.setVisible(false);	
		extraCurricularActivityDiscption3.setVisible(false);	
		exterCurricularActivityTital4.setVisible(false);	
		extraCurricularActivityDiscption4.setVisible(false);	 
		exterCurricularActivityTital5.setVisible(false);	
		extraCurricularActivityDiscption5.setVisible(false);	


		
		

		HorizontalLayout personalInfoLayout= new HorizontalLayout();
		Label personalInformation= new Label("Personal Information");
		personalInformation.addStyleName("h2");
		personalInfoLayout.addComponent(personalInformation);
		personalInfoLayout.setWidth("100%");
		personalInfoLayout.setComponentAlignment(personalInformation, Alignment.TOP_LEFT);
		
		
		registrationForm.addComponent(personalInfoLayout);
		registrationForm.setComponentAlignment(personalInfoLayout, Alignment.TOP_LEFT);
		registrationForm.addComponent(nameInFull);
		registrationForm.addComponent(nameWithInitials);

        registrationForm.addComponent(objective);
        registrationForm.addComponent(profile);
		
		final Upload upload = new Upload("Profile picture", receiver);
		upload.setButtonCaption(null);
		upload.addSucceededListener(receiver);
		registrationForm.addComponent(upload);
		
		registrationForm.addComponent(gender);
		registrationForm.addComponent(maritalStatus);
		registrationForm.addComponent(dateofBirth);
		registrationForm.addComponent(address);
		registrationForm.addComponent(contactNo);
		registrationForm.addComponent(emailAddress);	
		
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
		
		Label educationQualification= new Label("Education Qualification");
		educationQualification.addStyleName("h2");
		
		registrationForm.addComponent(educationQualification);
		
		registrationForm.addComponent(digree);
		registrationForm.addComponent(batch);
		registrationForm.addComponent(semester1Gpa);
		registrationForm.addComponent(semester2Gpa);
		registrationForm.addComponent(semester3Gpa);
		registrationForm.addComponent(school);
		registrationForm.addComponent(alResultSubject1);
		registrationForm.addComponent(alresult1);
		registrationForm.addComponent(alResultSubject2);
		registrationForm.addComponent(alresult2);
		registrationForm.addComponent(alResultSubject3);
		registrationForm.addComponent(alresult3);
		
		Label projects= new Label("Projects");
		projects.addStyleName("h2");
		Button addProject= new Button("Add");
		Button removeProject= new Button("Remove");
		
		HorizontalLayout projectsLayout= new HorizontalLayout();
		projectsLayout.addComponent(projects);
		projectsLayout.addComponent(addProject);
		projectsLayout.addComponent(removeProject);
		projectsLayout.setSpacing(true);
		projectsLayout.setExpandRatio(removeProject, 1);		
		registrationForm.addComponent(projectsLayout);
		
		addProject.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				if(projectCount==1)
				{
				    projectTitle2.setVisible(true);
					projectDescription2.setVisible(true);
					projectCount++;
					
				}
				else if(projectCount==2)
				{
				    projectTitle3.setVisible(true);
					projectDescription3.setVisible(true);
					projectCount++;
					
				}
				else if(projectCount==3)
				{
				    projectTitle4.setVisible(true);
					projectDescription4.setVisible(true);
					projectCount++;
					
				}
				else if(projectCount==4)
				{
				    projectTitle5.setVisible(true);
					projectDescription5.setVisible(true);
					projectCount++;
					
				}
				else if(projectCount==5)
				{
				    projectTitle6.setVisible(true);
					projectDescription6.setVisible(true);
					projectCount++;
					
				}
				else if(projectCount==6)
				{
				    projectTitle7.setVisible(true);
					projectDescription7.setVisible(true);
					projectCount++;
					
				}
				else if(projectCount==7)
				{
				    projectTitle8.setVisible(true);
					projectDescription8.setVisible(true);
					projectCount++;
					
				}
				else if(projectCount==8)
				{
				    projectTitle9.setVisible(true);
					projectDescription9.setVisible(true);
					projectCount++;
					
				}
				else if(projectCount==9)
				{
				    projectTitle10.setVisible(true);
					projectDescription10.setVisible(true);
					projectCount++;
					
				}
				else if(projectCount==10)
				{
				    Notification.show("Only 10 Projects Can add");
					
				}
				
			}
		});
		
		removeProject.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				if(projectCount==2)
				{
				    projectTitle2.setVisible(false);
					projectDescription2.setVisible(false);
					projectTitle2.setValue("");
					projectDescription2.setValue("");
					projectCount--;
					
				}
				else if(projectCount==3)
				{
				    projectTitle3.setVisible(false);
					projectDescription3.setVisible(false);
					projectTitle3.setValue("");
					projectDescription3.setValue("");
					projectCount--;
					
				}
				else if(projectCount==4)
				{
				    projectTitle4.setVisible(false);
					projectDescription4.setVisible(false);
					projectTitle4.setValue("");
					projectDescription4.setValue("");
					projectCount--;
					
				}
				else if(projectCount==5)
				{
				    projectTitle5.setVisible(false);
					projectDescription5.setVisible(false);
					projectTitle5.setValue("");
					projectDescription5.setValue("");
					projectCount--;
					
				}
				else if(projectCount==6)
				{
				    projectTitle6.setVisible(false);
					projectDescription6.setVisible(false);
					projectTitle6.setValue("");
					projectDescription6.setValue("");
					projectCount--;
					
				}
				else if(projectCount==7)
				{
				    projectTitle7.setVisible(false);
					projectDescription7.setVisible(false);
					projectTitle7.setValue("");
					projectDescription7.setValue("");
					projectCount--;
					
				}
				else if(projectCount==8)
				{
				    projectTitle8.setVisible(false);
					projectDescription8.setVisible(false);
					projectTitle8.setValue("");
					projectDescription8.setValue("");
					projectCount--;
					
				}
				else if(projectCount==9)
				{
				    projectTitle9.setVisible(false);
					projectDescription9.setVisible(false);
					projectTitle9.setValue("");
					projectDescription9.setValue("");
					projectCount--;
					
				}
				else if(projectCount==10)
				{
				    projectTitle10.setVisible(false);
					projectDescription10.setVisible(false);
					projectTitle10.setValue("");
					projectDescription10.setValue("");
					projectCount--;
					
				}

				
			}
		});
		

		
		
		registrationForm.addComponent(projectTitle10);
		registrationForm.addComponent(projectDescription10);
		registrationForm.addComponent(projectTitle9);
		registrationForm.addComponent(projectDescription9);
		registrationForm.addComponent(projectTitle8);
		registrationForm.addComponent(projectDescription8);
		registrationForm.addComponent(projectTitle7);
		registrationForm.addComponent(projectDescription7);
		registrationForm.addComponent(projectTitle6);
		registrationForm.addComponent(projectDescription6);
		registrationForm.addComponent(projectTitle5);
		registrationForm.addComponent(projectDescription5);
		registrationForm.addComponent(projectTitle4);
		registrationForm.addComponent(projectDescription4);
		registrationForm.addComponent(projectTitle3);
		registrationForm.addComponent(projectDescription3);
		registrationForm.addComponent(projectTitle2);
		registrationForm.addComponent(projectDescription2);
		registrationForm.addComponent(projectTitle1);
		registrationForm.addComponent(projectDescription1);
		
		
		
		
		Label exterCurricularLable= new Label("Extra Curricular Activity");
		exterCurricularLable.addStyleName("h2");
		Button addExterCurricular= new Button("Add");
		Button removeExterCurricular= new Button("Remove");
		
		HorizontalLayout exterCurricularLayout= new HorizontalLayout();
		exterCurricularLayout.addComponent(exterCurricularLable);
		exterCurricularLayout.addComponent(addExterCurricular);
		exterCurricularLayout.addComponent(removeExterCurricular);
		exterCurricularLayout.setSpacing(true);
		exterCurricularLayout.setExpandRatio(removeExterCurricular, 1);
		registrationForm.addComponent(exterCurricularLayout);
		
		
		addExterCurricular.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				if(extraCurricularActivityCount==1)
				{
					exterCurricularActivityTital2.setVisible(true);
					extraCurricularActivityDiscption2.setVisible(true);
					extraCurricularActivityCount++;
					
				}
				else if(extraCurricularActivityCount==2)
				{
					exterCurricularActivityTital3.setVisible(true);
					extraCurricularActivityDiscption3.setVisible(true);
					extraCurricularActivityCount++;
					
				}
				else if(extraCurricularActivityCount==3)
				{
					exterCurricularActivityTital4.setVisible(true);
					extraCurricularActivityDiscption4.setVisible(true);
					extraCurricularActivityCount++;
					
				}
				else if(extraCurricularActivityCount==4)
				{
					exterCurricularActivityTital5.setVisible(true);
					extraCurricularActivityDiscption5.setVisible(true);
					extraCurricularActivityCount++;
					
				}
				else if(extraCurricularActivityCount==5)
				{
				    Notification.show("Only 5 Extra Curriculer activity Can add");
					
				}
				
			}
		});
		
		removeExterCurricular.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				if(extraCurricularActivityCount==2)
				{
					exterCurricularActivityTital2.setVisible(false);
					extraCurricularActivityDiscption2.setVisible(false);
					exterCurricularActivityTital2.setValue("");
					extraCurricularActivityDiscption2.setValue("");
					extraCurricularActivityCount--;
					
				}
				else if(extraCurricularActivityCount==3)
				{
					exterCurricularActivityTital3.setVisible(false);
					extraCurricularActivityDiscption3.setVisible(false);
					exterCurricularActivityTital3.setValue("");
					extraCurricularActivityDiscption3.setValue("");
					extraCurricularActivityCount--;
					
				}
				else if(extraCurricularActivityCount==4)
				{
					exterCurricularActivityTital4.setVisible(false);
					extraCurricularActivityDiscption4.setVisible(false);
					exterCurricularActivityTital4.setValue("");
					extraCurricularActivityDiscption4.setValue("");
					extraCurricularActivityCount--;
					
				}
				else if(extraCurricularActivityCount==5)
				{
					exterCurricularActivityTital5.setVisible(false);
					extraCurricularActivityDiscption5.setVisible(false);
					exterCurricularActivityTital5.setValue("");
					extraCurricularActivityDiscption5.setValue("");
					extraCurricularActivityCount--;
					
				}


			}
		});
		
		
		
		
		
		registrationForm.addComponent(exterCurricularActivityTital5);
		registrationForm.addComponent(extraCurricularActivityDiscption5);
		registrationForm.addComponent(exterCurricularActivityTital4);
		registrationForm.addComponent(extraCurricularActivityDiscption4);
		registrationForm.addComponent(exterCurricularActivityTital3);
		registrationForm.addComponent(extraCurricularActivityDiscption3);
		registrationForm.addComponent(exterCurricularActivityTital2);
		registrationForm.addComponent(extraCurricularActivityDiscption2);
		registrationForm.addComponent(exterCurricularActivityTital1);
		registrationForm.addComponent(extraCurricularActivityDiscption1);

        if(updateStudent)
        {
            StudentComplitedProjects project;
            StudentOtherQulification otherQulification;

            if(student.getStudentComplitedProjects().size()!=0)
            projectCount=student.getStudentComplitedProjects().size();
            if(student.getStudentOtherQulification().size()!=0)
            extraCurricularActivityCount=student.getStudentOtherQulification().size();
            switch (student.getStudentComplitedProjects().size())
            {
                case 10:
                    project=student.getStudentComplitedProjects().iterator().next();
                    projectTitle10.setValue(project.getProjectTitle());
                    projectDescription10.setValue(project.getProjectDescription());
                    projectTitle10.setVisible(true);
                    projectDescription10.setVisible(true);
                case 9:
                    project=student.getStudentComplitedProjects().iterator().next();
                    projectTitle9.setValue(project.getProjectTitle());
                    projectDescription9.setValue(project.getProjectDescription());
                    projectTitle9.setVisible(true);
                    projectDescription9.setVisible(true);
                case 8:
                    project=student.getStudentComplitedProjects().iterator().next();
                    projectTitle8.setValue(project.getProjectTitle());
                    projectDescription8.setValue(project.getProjectDescription());
                    projectTitle8.setVisible(true);
                    projectDescription8.setVisible(true);
                case 7:
                    project=student.getStudentComplitedProjects().iterator().next();
                    projectTitle7.setValue(project.getProjectTitle());
                    projectDescription7.setValue(project.getProjectDescription());
                    projectTitle7.setVisible(true);
                    projectDescription7.setVisible(true);
                case 6:
                    project=student.getStudentComplitedProjects().iterator().next();
                    projectTitle6.setValue(project.getProjectTitle());
                    projectDescription6.setValue(project.getProjectDescription());
                    projectTitle6.setVisible(true);
                    projectDescription6.setVisible(true);
                case 5:
                    project=student.getStudentComplitedProjects().iterator().next();
                    projectTitle5.setValue(project.getProjectTitle());
                    projectDescription5.setValue(project.getProjectDescription());
                    projectTitle5.setVisible(true);
                    projectDescription5.setVisible(true);
                case 4:
                    project=student.getStudentComplitedProjects().iterator().next();
                    projectTitle4.setValue(project.getProjectTitle());
                    projectDescription4.setValue(project.getProjectDescription());
                    projectTitle4.setVisible(true);
                    projectDescription4.setVisible(true);
                case 3:
                    project=student.getStudentComplitedProjects().iterator().next();
                    projectTitle3.setValue(project.getProjectTitle());
                    projectDescription3.setValue(project.getProjectDescription());
                    projectTitle3.setVisible(true);
                    projectDescription3.setVisible(true);
                case 2:
                    project=student.getStudentComplitedProjects().iterator().next();
                    projectTitle2.setValue(project.getProjectTitle());
                    projectDescription2.setValue(project.getProjectDescription());
                    projectTitle2.setVisible(true);
                    projectDescription2.setVisible(true);
                case 1:
                    project=student.getStudentComplitedProjects().iterator().next();
                    projectTitle1.setValue(project.getProjectTitle());
                    projectDescription1.setValue(project.getProjectDescription());
                    projectTitle1.setVisible(true);
                    projectDescription1.setVisible(true);

            }

            switch (student.getStudentOtherQulification().size())
            {
                case 5:
                    otherQulification=student.getStudentOtherQulification().iterator().next();
                    exterCurricularActivityTital5.setValue(otherQulification.getDescription());
                    exterCurricularActivityTital5.setVisible(true);
                case 4:
                    otherQulification=student.getStudentOtherQulification().iterator().next();
                    exterCurricularActivityTital4.setValue(otherQulification.getDescription());
                    exterCurricularActivityTital4.setVisible(true);
                case 3:
                    otherQulification=student.getStudentOtherQulification().iterator().next();
                    exterCurricularActivityTital3.setValue(otherQulification.getDescription());
                    exterCurricularActivityTital3.setVisible(true);
                case 2:
                    otherQulification=student.getStudentOtherQulification().iterator().next();
                    exterCurricularActivityTital2.setValue(otherQulification.getDescription());
                    exterCurricularActivityTital2.setVisible(true);
                case 1:
                    otherQulification=student.getStudentOtherQulification().iterator().next();
                    exterCurricularActivityTital1.setValue(otherQulification.getDescription());
                    exterCurricularActivityTital1.setVisible(true);
            }
        }



















		        
		registrationForm.addComponent(new Button("Commit",
		    new Button.ClickListener() {
		    @Override
		    public void buttonClick(ClickEvent event) {
	   
		        try {

		        	binder.commit();
		        	Student newStudent= binder.getItemDataSource().getBean();
		        	newStudent.setStudentUserName(VaadinSession.getCurrent().getAttribute("UserName").toString());
                    newStudent.getStudentComplitedProjects().removeAll(newStudent.getStudentComplitedProjects());
                    newStudent.getStudentOtherQulification().removeAll(newStudent.getStudentOtherQulification());


                    System.out.println("project cont existing"+ newStudent.getStudentComplitedProjects().size()+  newStudent.getStudentOtherQulification().size());

		        	System.out.println("project coutnt and extra coutnt"+projectCount+extraCurricularActivityCount);


                    switch (projectCount)
                    {
                        case 10:
                            StudentComplitedProjects studentComplitedProjects10=new StudentComplitedProjects();
                            studentComplitedProjects10.setProjectTitle(projectTitle10.getValue());
                            studentComplitedProjects10.setProjectDescription(projectDescription10.getValue());
                            studentComplitedProjects10.setStudent(newStudent);
                            newStudent.getStudentComplitedProjects().add(studentComplitedProjects10);

                        case 9:
                            StudentComplitedProjects studentComplitedProjects9=new StudentComplitedProjects();
                            studentComplitedProjects9.setProjectTitle(projectTitle9.getValue());
                            studentComplitedProjects9.setProjectDescription(projectDescription9.getValue());
                            studentComplitedProjects9.setStudent(newStudent);
                            newStudent.getStudentComplitedProjects().add(studentComplitedProjects9);
                        case 8:
                            StudentComplitedProjects studentComplitedProjects8=new StudentComplitedProjects();
                            studentComplitedProjects8.setProjectTitle(projectTitle8.getValue());
                            studentComplitedProjects8.setProjectDescription(projectDescription8.getValue());
                            studentComplitedProjects8.setStudent(newStudent);
                            newStudent.getStudentComplitedProjects().add(studentComplitedProjects8);
                        case 7:
                            StudentComplitedProjects studentComplitedProjects7=new StudentComplitedProjects();
                            studentComplitedProjects7.setProjectTitle(projectTitle7.getValue());
                            studentComplitedProjects7.setProjectDescription(projectDescription7.getValue());
                            studentComplitedProjects7.setStudent(newStudent);
                            newStudent.getStudentComplitedProjects().add(studentComplitedProjects7);
                        case 6:
                            StudentComplitedProjects studentComplitedProjects6=new StudentComplitedProjects();
                            studentComplitedProjects6.setProjectTitle(projectTitle6.getValue());
                            studentComplitedProjects6.setProjectDescription(projectDescription6.getValue());
                            studentComplitedProjects6.setStudent(newStudent);
                            newStudent.getStudentComplitedProjects().add(studentComplitedProjects6);
                        case 5:
                            StudentComplitedProjects studentComplitedProjects5=new StudentComplitedProjects();
                            studentComplitedProjects5.setProjectTitle(projectTitle5.getValue());
                            studentComplitedProjects5.setProjectDescription(projectDescription5.getValue());
                            studentComplitedProjects5.setStudent(newStudent);
                            newStudent.getStudentComplitedProjects().add(studentComplitedProjects5);
                        case 4:
                            StudentComplitedProjects studentComplitedProjects4=new StudentComplitedProjects();
                            studentComplitedProjects4.setProjectTitle(projectTitle4.getValue());
                            studentComplitedProjects4.setProjectDescription(projectDescription4.getValue());
                            studentComplitedProjects4.setStudent(newStudent);
                            newStudent.getStudentComplitedProjects().add(studentComplitedProjects4);
                        case 3:
                            StudentComplitedProjects studentComplitedProjects3=new StudentComplitedProjects();
                            studentComplitedProjects3.setProjectTitle(projectTitle3.getValue());
                            studentComplitedProjects3.setProjectDescription(projectDescription3.getValue());
                            studentComplitedProjects3.setStudent(newStudent);
                            newStudent.getStudentComplitedProjects().add(studentComplitedProjects3);
                        case 2:
                            StudentComplitedProjects studentComplitedProjects2=new StudentComplitedProjects();
                            studentComplitedProjects2.setProjectTitle(projectTitle2.getValue());
                            studentComplitedProjects2.setProjectDescription(projectDescription2.getValue());
                            studentComplitedProjects2.setStudent(newStudent);
                            newStudent.getStudentComplitedProjects().add(studentComplitedProjects2);
                        case 1:
                            StudentComplitedProjects studentComplitedProjects1=new StudentComplitedProjects();
                            studentComplitedProjects1.setProjectTitle(projectTitle1.getValue());
                            studentComplitedProjects1.setProjectDescription(projectDescription1.getValue());
                            studentComplitedProjects1.setStudent(newStudent);
                            newStudent.getStudentComplitedProjects().add(studentComplitedProjects1);

                        default:
                            break;




                    }



                    switch (extraCurricularActivityCount)
                    {
                        case 5:
                            StudentOtherQulification studentOtherQulification5 = new StudentOtherQulification();
                            studentOtherQulification5.setDescription(extraCurricularActivityDiscption5.getValue());
                            studentOtherQulification5.setStudent(newStudent);
                            newStudent.getStudentOtherQulification().add(studentOtherQulification5);
                        case 4:
                            StudentOtherQulification studentOtherQulification4 = new StudentOtherQulification();
                            studentOtherQulification4.setDescription(extraCurricularActivityDiscption4.getValue());
                            studentOtherQulification4.setStudent(newStudent);
                            newStudent.getStudentOtherQulification().add(studentOtherQulification4);
                        case 3:
                            StudentOtherQulification studentOtherQulification3 = new StudentOtherQulification();
                            studentOtherQulification3.setDescription(extraCurricularActivityDiscption3.getValue());
                            studentOtherQulification3.setStudent(newStudent);
                            newStudent.getStudentOtherQulification().add(studentOtherQulification3);
                        case 2:
                            StudentOtherQulification studentOtherQulification2 = new StudentOtherQulification();
                            studentOtherQulification2.setDescription(extraCurricularActivityDiscption2.getValue());
                            studentOtherQulification2.setStudent(newStudent);
                            newStudent.getStudentOtherQulification().add(studentOtherQulification2);
                        case 1:
                            StudentOtherQulification studentOtherQulification1 = new StudentOtherQulification();
                            studentOtherQulification1.setDescription(extraCurricularActivityDiscption1.getValue());
                            studentOtherQulification1.setStudent(newStudent);
                            newStudent.getStudentOtherQulification().add(studentOtherQulification1);

                        default:
                            break;


                    }


                    if(updateStudent)
                    {
                        studentDAO.updateStudent(newStudent);

                        if(profilePicterSelected)
                        {
                            Notification.show("Updated Your Profile ");
                            upload.submitUpload();


                        }
                        else
                        {
                            Notification.show("Updated Your Profile");
                            getUI().getNavigator().navigateTo("/home");
                        }


                    }
                    else
                    {
                        registerStudentDAO.registerNewStudent(newStudent);
                        //System.out.println("Student data "+newStudent.getGender()+newStudent.getMaritalStatus()+newStudent.getDigreeTitle());




                        if(profilePicterSelected)
                        {
                            Notification.show("Your Cv Created");
                            upload.submitUpload();


                        }
                        else
                        {
                            Notification.show("Your Cv Created");
                            getUI().getNavigator().navigateTo("/home");
                        }

                    }





		        
		        	
		        } catch (InvalidValueException e) {
		        	System.out.println("after validatin##########################################################");
		            Notification.show(e.getMessage());
		        } catch (CommitException e) {
					// TODO Auto-generated catch block
		        	
		        	System.out.println("comman execption##########################################################");
					e.printStackTrace();
				}
		    }
		}));

		registrationForm.addComponent(new Button("Discard",
		    new Button.ClickListener() {
		    @Override
		    public void buttonClick(ClickEvent event) {
		       // editor.discard();
		    }
		}));


		mainLayout.addComponent(registrationForm);
        mainLayout.setExpandRatio(registrationForm,1);

		//mainLayout.addComponent(layout);
		
		
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
	        	
	            file = new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath()+"/WEB-INF/images/" + VaadinSession.getCurrent().getAttribute("UserName")+".png");
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













    //getters and setters
    public Student getStudnet() {
        return student;
    }

    public void setStudnet(Student studnet) {
        this.student = studnet;
    }



}
