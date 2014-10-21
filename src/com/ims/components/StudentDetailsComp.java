package com.ims.components;


import java.util.List;

import com.ims.ImsUI;
import com.ims.business.CompanyDAO;
import com.ims.business.StudentAppliedCompanyDAO;
import com.ims.data.Student;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

public class StudentDetailsComp extends CustomComponent{

    VerticalLayout mainLayout;
	List<Student> studentList;
	String studentState;
	String stateChangeButtonString="";
	
	
	public StudentDetailsComp(String studentState)
	{
		setSizeFull();
		this.studentState=studentState;
		
		buildStudentDetailsView();
		setCompositionRoot(mainLayout);
		
				
	}
	
	
	
	
	private void buildStudentDetailsView()
	{
		mainLayout= new VerticalLayout();
		mainLayout.setSizeFull();
		
		final CompanyDAO companyDAO=  (CompanyDAO)ImsUI.context.getBean("AccessCompany");
		final StudentAppliedCompanyDAO studentAppliedCompanyDAO=(StudentAppliedCompanyDAO)ImsUI.context.getBean("AccessStudentAppliedCompany");
		
		if(studentState=="All")
		{
			studentList=companyDAO.getAppliedStudentToCompany(VaadinSession.getCurrent().getAttribute("UserName").toString());
			
			
		}
		else if(studentState=="SelectedForInterview")
		{
			studentList=companyDAO.getSelectedStudentForInterview(VaadinSession.getCurrent().getAttribute("UserName").toString());
			
		}
		else if(studentState=="NotSelectedForInterview")
		{
			studentList=companyDAO.getNotSelectStudentForInterview(VaadinSession.getCurrent().getAttribute("UserName").toString());
			
		}
		else if(studentState=="SelectedForInternship")
		{
			studentList=companyDAO.getSelectedStudentForInternship(VaadinSession.getCurrent().getAttribute("UserName").toString());
			
		}
		
		
		
		
		Table table = new Table("");
		table.setSizeFull();
		
		
		table.addContainerProperty("Index", Integer.class, null);
		table.addContainerProperty("Student Name", String.class, null);
		table.addContainerProperty("Student Email",  String.class, null);
		table.addContainerProperty("Student Contact No",  String.class, null);
		//table.addContainerProperty("No Of Vacanies",  String.class, null);
		table.addContainerProperty("View Profile",  Button.class, null);
		
		if(studentState=="All")
		{
			//table.addContainerProperty("View Profile",  Button.class, null);
		}
		else if(studentState=="NotSelectedForInterview")
		{
			table.addContainerProperty("Select To Interview",  Button.class, null);
			stateChangeButtonString="Select To Interview";
		}
		else if(studentState=="SelectedForInterview")
		{
			table.addContainerProperty("Select For Internship",  Button.class, null);
			stateChangeButtonString="Select To Internship";
		}
		else if(studentState=="SelectedForInternhsip")
		{
			//table.addContainerProperty("View Profile",  Button.class, null);
		}
		
		
		for(int x=0;x<studentList.size();x++)
		{
			int index=x+1;
			
			Button viewProfile= new Button("view CV");
			
			viewProfile.setData(studentList.get(x).getStudentUserName());
			
			viewProfile.addClickListener(new Button.ClickListener() {
		        public void buttonClick(ClickEvent event) {
		        	
		        	//companyDAO.unAllowedCompany((String)event.getButton().getData());
		        	//getUI().getNavigator().navigateTo("/student/allowed_companies");
		        	getUI().getNavigator().navigateTo("/internship/student/"+(String)event.getButton().getData());
		        } 
		    });
			
			
			Button changeStudentState=new Button(stateChangeButtonString);
			
			changeStudentState.setData(studentList.get(x).getStudentUserName());

            if(studentList.get(x).isSelected())
            {
                changeStudentState.setEnabled(false);
                changeStudentState.setCaption("Seleced By Other Company");

            }
			
			changeStudentState.addClickListener(new Button.ClickListener() {
                public void buttonClick(ClickEvent event) {

                    if (studentState == "NotSelectedForInterview") {
                        studentAppliedCompanyDAO.updateStudentStateToInterview(VaadinSession.getCurrent().getAttribute("UserName").toString(), (String) event.getButton().getData());
                        getUI().getNavigator().navigateTo("/internship/not_selected_students_for_inverivew");

                    } else if (studentState == "SelectedForInterview") {
                        studentAppliedCompanyDAO.updateStudentStateToSelected(VaadinSession.getCurrent().getAttribute("UserName").toString(), (String) event.getButton().getData());
                        getUI().getNavigator().navigateTo("/internship/selected_students_for_inverivew");

                    }

                    //companyDAO.unAllowedCompany((String)event.getButton().getData());
                    //getUI().getNavigator().navigateTo("/student/allowed_companies");
                }
            });
			
			
			if(stateChangeButtonString.equals(""))
			{
				
				table.addItem(new Object[] {index,studentList.get(x).getNameInFull(),studentList.get(x).getEmail(),studentList.get(x).getMobile(),viewProfile},index);
				
			}
			else
			{
				table.addItem(new Object[] {index,studentList.get(x).getNameInFull(),studentList.get(x).getEmail(),studentList.get(x).getMobile(),viewProfile,changeStudentState},index);
				
			}
			
			
		
			
		
		}
		
		mainLayout.addComponent(table);

	}

}
