package com.ims.components;

import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by Damitha on 10/13/14.
 */
public class StudentCvViewComp extends CustomComponent {

    AbsoluteLayout mainLayout;
    VerticalLayout cvViewLayout;
    final Embedded image = new Embedded("");
    public File file;
    FileInputStream fis = null;

    public StudentCvViewComp(String studentUserName) {

        setSizeFull();

        buildStudentCvView();
        setCompositionRoot(mainLayout);

    }



    private void buildStudentCvView()
    {
        mainLayout= new AbsoluteLayout();
        mainLayout.setSizeFull();


        cvViewLayout = new VerticalLayout();
        cvViewLayout.setSizeFull();
        cvViewLayout.setSpacing(true);


        Panel cvViewPanel= new Panel();
        cvViewPanel.setHeight("100%");


        Label studentCvMainHeader=new Label("Damitha Kithmal | Curriculum vite");
        studentCvMainHeader.addStyleName("studentCvMainHeader");
        studentCvMainHeader.setWidthUndefined();
        cvViewLayout.addComponent(studentCvMainHeader);
        cvViewLayout.setComponentAlignment(studentCvMainHeader, Alignment.TOP_CENTER);


        Label studentAddress= new Label("475/1 Samudra Mw, Hokandara North Hokandara.");
        studentAddress.addStyleName("studentCvContent");
        studentAddress.setWidthUndefined();
        cvViewLayout.addComponent(studentAddress);
        cvViewLayout.setComponentAlignment(studentAddress, Alignment.TOP_CENTER);



        HorizontalLayout studentMainInfoLayout= new HorizontalLayout();

        Label studentTelNo= new Label("+94 711086804");
        studentTelNo.addStyleName("studentCvContent");
        Label studentEmail= new Label("dkithmalfit@gmail.com");
        studentEmail.addStyleName("studentCvContent");
        Label studentLinkedIn= new Label("linkedin.com/in/damithakithmal");
        studentLinkedIn.addStyleName("studentCvContent");

        studentMainInfoLayout.setWidth("100%");
        studentMainInfoLayout.addComponent(studentTelNo);
        studentMainInfoLayout.addComponent(studentEmail);
        studentMainInfoLayout.addComponent(studentLinkedIn);

        cvViewLayout.addComponent(studentMainInfoLayout);



        Label objectiveLabel= new Label("Objectives");
        objectiveLabel.addStyleName("studentCvHeader");
        objectiveLabel.setWidthUndefined();

        Label studentObjective= new Label("To join a competitive and challenging IT industry and obtain an internship with the intention of contributing the growth " +
                "of the company with my knowledge while uplifting my professional skills");
        studentObjective.addStyleName("studentCvContent");
        studentObjective.setWidth("100%");
        studentObjective.setHeightUndefined();



        Label profileLabel= new Label("Profile");
        profileLabel.addStyleName("studentCvHeader");


        Label studentProfile= new Label("Energetic individual with the passion for IT who is willing to take challenges and complete assigned tasks to maximum quality possible." +
                " A team player as well as a motivator and ready to pull everyone through in tight situations while enjoying the pressure.");
        studentProfile.addStyleName("studentCvContent");
        studentProfile.setWidth("100%");
        studentProfile.setHeightUndefined();


        try {

            file = new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath()+"/WEB-INF/images/damitha.png");
            fis = new FileInputStream (file);
        } catch (final java.io.FileNotFoundException e) {

            file = new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath()+"/WEB-INF/images/defalutCompany.png");

        }

        image.setSource(new FileResource(file));
        image.setHeight("200px");
        image.setWidth("200px");


        GridLayout profileGrid= new GridLayout(2,4);
        profileGrid.setWidth("100%");
        profileGrid.setHeightUndefined();
        profileGrid.setRowExpandRatio(1,2);
        profileGrid.setRowExpandRatio(3,2);
        profileGrid.setColumnExpandRatio(0,4);
        profileGrid.addComponent(objectiveLabel,0,0);
        profileGrid.addComponent(studentObjective,0,1);
        profileGrid.addComponent(profileLabel,0,2);
        profileGrid.addComponent(studentProfile,0,3);
        profileGrid.addComponent(image,1,0,1,3);
        profileGrid.setComponentAlignment(image,Alignment.TOP_CENTER);
        cvViewLayout.addComponent(profileGrid);


        Label personalInformationLabel= new Label("Personal Information");
        personalInformationLabel.addStyleName("studentCvHeader");
        cvViewLayout.addComponent(personalInformationLabel);


        Label fullName=new Label("Full Name : Hewa Marambage Damitha Kithmal");
        fullName.addStyleName("studentCvContent");
        Label dateOfBirth = new Label("Date of Birth : 16 August 1990");
        dateOfBirth.addStyleName("studentCvContent");
        Label nic = new Label("NIC : 902290419V");
        nic.addStyleName("studentCvContent");
        Label gender = new Label("Gender : Male");
        gender.addStyleName("studentCvContent");
        Label nationality = new Label("Nationality : Sri Lankan");
        nationality.addStyleName("studentCvContent");



        cvViewLayout.addComponent(fullName);
        cvViewLayout.addComponent(dateOfBirth);
        cvViewLayout.addComponent(nic);
        cvViewLayout.addComponent(gender);
        cvViewLayout.addComponent(nationality);


        Label educationQualificationsLabel= new Label("Eduction Qualifications");
        educationQualificationsLabel.addStyleName("studentCvHeader");
        cvViewLayout.addComponent(educationQualificationsLabel);


        Label tertiaryEducation=new Label("Tertiary Education: Currently Following B.Sc. in Information Technology (Hons.)");
        tertiaryEducation.addStyleName("studentCvSubHeader");
        tertiaryEducation.setHeightUndefined();
        Label semester1Gpa=new Label("Level 1 - Semester 1 GPA: 3.46");
        semester1Gpa.addStyleName("studentCvContent");
        Label semester2Gpa=new Label("Level 1 - Semester 2 GPA: 3.35");
        semester2Gpa.addStyleName("studentCvContent");
        Label semester3Gpa=new Label("Level 2 - Semester 1 GPA: 3.33");
        semester3Gpa.addStyleName("studentCvContent");

        cvViewLayout.addComponent(tertiaryEducation);
        cvViewLayout.addComponent(semester1Gpa);
        cvViewLayout.addComponent(semester2Gpa);
        cvViewLayout.addComponent(semester3Gpa);

        Label secondaryEducation= new Label("Secondary Education: (2007-2009) Ananda College, Colombo 10.");
        secondaryEducation.addStyleName("studentCvSubHeader");
        secondaryEducation.setHeightUndefined();

        Label alStream= new Label("G.C.E. (A/L) 2010 Physical Science Stream");
        alStream.addStyleName("studentCvContent");
        Label alSubject1=new Label("Combined Maths A");
        alSubject1.addStyleName("studentCvContent");
        Label alSubject2=new Label("Chemistry B");
        alSubject2.addStyleName("studentCvContent");
        Label alSubject3=new Label("Physics B");
        alSubject3.addStyleName("studentCvContent");
        Label zScore = new Label("Z-score 1.803");
        zScore.addStyleName("studentCvContent");



        cvViewLayout.addComponent(secondaryEducation);
        cvViewLayout.addComponent(alStream);
        cvViewLayout.addComponent(alSubject1);
        cvViewLayout.addComponent(alSubject2);
        cvViewLayout.addComponent(alSubject3);
        cvViewLayout.addComponent(zScore);




        Label projectsLabel= new Label("Projects");
        projectsLabel.addStyleName("studentCvHeader");
        cvViewLayout.addComponent(projectsLabel);

        for (int x=0;x<3;x++)
        {
            Label projectTitel= new Label("Internship Management system developed for faculty of Information Technology (Level 2 project)");
            projectTitel.setHeightUndefined();
            projectTitel.addStyleName("studentCvSubHeader");

            Label projectDiscription = new Label("This system has replaced the manual internship management process of the faculty. " +
                    "It is capable of making interactivity with student and company directly through the administrator.");
            projectDiscription.setHeightUndefined();
            projectDiscription.addStyleName("studentCvContent");


            cvViewLayout.addComponent(projectTitel);
            cvViewLayout.addComponent(projectDiscription);
        }

        Label otherQulification= new Label("Other Qulification");
        otherQulification.addStyleName("studentCvHeader");
        cvViewLayout.addComponent(otherQulification);






        Label fotter=new  Label("");
        cvViewLayout.addComponent(fotter);
        cvViewLayout.setExpandRatio(fotter,1);


        cvViewPanel.setContent(cvViewLayout);
        mainLayout.addComponent(cvViewPanel,"bottom:75px;left: 0px; top: 0px;right:0px");


    }
}
