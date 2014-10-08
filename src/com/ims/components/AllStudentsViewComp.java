package com.ims.components;

import java.util.List;

import com.ims.ImsUI;
import com.ims.business.StudentDAO;
import com.ims.data.Student;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Table;
import com.vaadin.ui.Button.ClickEvent;

public class AllStudentsViewComp extends CustomComponent{
	
	AbsoluteLayout mainLayout;
	
	
	public AllStudentsViewComp()
	{
		setSizeFull();
		buildAllStudentView();
		setCompositionRoot(mainLayout);
	}
	
	private void buildAllStudentView()
	{
		
		mainLayout= new AbsoluteLayout();
		mainLayout.setSizeFull();
		
		
		
		StudentDAO sudentDAO=  (StudentDAO)ImsUI.context.getBean("AccessStudent");
		
		List<Student> studentList=sudentDAO.getAllStudents();
	
		Table table = new Table("");
		table.addContainerProperty("Index", Integer.class, null);
		table.addContainerProperty("Student Name", String.class, null);
		table.addContainerProperty("Student Email",  String.class, null);
		table.addContainerProperty("Student Contact No",  String.class, null);
		table.addContainerProperty("State",  String.class, null);
		table.addContainerProperty("View Profile",  Button.class, null);
		table.setSizeFull();
		
	
		for(int x=0;x<studentList.size();x++)
		{
			int index=x+1;
			
			Button viewStudent=new Button("All Details");
			
			table.addItem(new Object[] {index,studentList.get(x).getNameInFull(),studentList.get(x).getEmail(),studentList.get(x).getMobile1(),"not set yet",viewStudent},index);
			
			
			viewStudent.setData(studentList.get(x).getStudentUserName().replace(' ', '_'));
			
			viewStudent.addClickListener(new Button.ClickListener() {
		        public void buttonClick(ClickEvent event) {

		        	getUI().getNavigator().navigateTo("/student/"+(String)event.getButton().getData());
		        } 
		    });
			
		}

		
		mainLayout.addComponent(table,"bottom:75px;left: 0px; top: 0px;right:0px");
		
	}

}
