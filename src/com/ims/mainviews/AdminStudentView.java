package com.ims.mainviews;

import com.ims.ImsUI;
import com.ims.business.StudentAppliedCompanyDAO;
import com.ims.components.AllStudentsViewComp;
import com.ims.components.StudentCvViewComp;
import com.ims.components.ViewStudentStateComp;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.BaseTheme;

public class AdminStudentView  extends CssLayout implements View{
	
	CssLayout content = new CssLayout();

	@Override
	public void enter(ViewChangeEvent event) {
		
        setSizeFull();
		
        buildStudentMainView();
		
		if (event.getParameters().equals("all_students")) {
			
			buildAllStudentsView();

			
		}
		
		else if (event.getParameters().equals("not_selected_students"))
		{
			buildNotSelectedStudentsView();
			
		}
		else if (event.getParameters().equals("selected_students"))
		{
			buildSelectedStudentsView();
			
		}
		
		else if (event.getParameters().equals("student_status"))
		{
			buildStudentStatusView();
			
		}

        else
        {
            buildStudentProfile(event.getParameters());
        }
		
		
	}
	
	
	
    private void buildStudentMainView()
    {    	
    	
    	addComponent(new HorizontalLayout(){
    		{
    			 setSizeFull();
    			addComponent(new VerticalLayout(){
    				{
    					
    					setHeight("100%");
    					setWidth(null);
    					addStyleName("submenu");

    	                final Button allStudents = new Button("All Students Profile");
    	                allStudents.setStyleName(BaseTheme.BUTTON_LINK);
    	                addComponent(allStudents);
    	                
    	                allStudents.addClickListener(new ClickListener() {
    	                    @Override
    	                    public void buttonClick(ClickEvent event) {
    	                    	
    	                    	getUI().getNavigator().navigateTo("/student/all_students");


    	                    }
    	                    
    	                
    	                   });

    	                
    	                final Button notSelectedStudents = new Button("Not Selected Students");
    	                notSelectedStudents.setStyleName(BaseTheme.BUTTON_LINK);
    	                addComponent(notSelectedStudents);
    	                
    	                notSelectedStudents.addClickListener(new ClickListener() {
    	                    @Override
    	                    public void buttonClick(ClickEvent event) {
    	                    	
    	                    	getUI().getNavigator().navigateTo("/student/not_selected_students");


    	                    }
    	                    
    	                
    	                   });
    	                
    	                
    	                final Button selectedStudents = new Button("Selected  Students");
    	                selectedStudents.setStyleName(BaseTheme.BUTTON_LINK);
    	                addComponent(selectedStudents);
    	                
    	                selectedStudents.addClickListener(new ClickListener() {
    	                    @Override
    	                    public void buttonClick(ClickEvent event) {
    	                    	
    	                    	getUI().getNavigator().navigateTo("/student/selected_students");


    	                    }
    	                    
    	                
    	                   });


    	                
    	                
    	                final Button studentStatus = new Button("View Student Status"); 
    	                studentStatus.setStyleName(BaseTheme.BUTTON_LINK);
    	                addComponent(studentStatus);
    	                
    	                studentStatus.addClickListener(new ClickListener() {
    	                    @Override
    	                    public void buttonClick(ClickEvent event) {
    	                    	
    	                    	
    	                    	getUI().getNavigator().navigateTo("/student/student_status");

    	                    }
    	                    
    	                
    	                   });
    	                
    	                setExpandRatio(studentStatus, 1);
    	                

    				}
    				
    			});
    			
    			
    			addComponent(content);
    			content.setSizeFull();
    			content.addStyleName("subcontent");
    			setExpandRatio(content, 1);
    			
    			buildMainView();
    			
    		}
    		
    		
    		
    	});


    }
    
    private void buildMainView()
    {
    	content.removeAllComponents();
    	content.addComponent(new Label("this is admin home content"));
    			
    }
    
    private void buildAllStudentsView()
    {
    	content.removeAllComponents();
    	content.addComponent(new AllStudentsViewComp());
    	
    }
    
    private void buildNotSelectedStudentsView()
    {
    	content.removeAllComponents();
    	//.addComponent(new Label("this is not selected student view in admin"));
        content.addComponent(new ViewStudentStateComp("notSelected"));
    	

    }
    
    private void buildSelectedStudentsView()
    {
    	content.removeAllComponents();
    	//content.addComponent(new Label("this is selected students in admin"));
        content.addComponent(new ViewStudentStateComp("selected"));
    	
    }
    
    private void buildStudentStatusView()
    {
    	content.removeAllComponents();
    	content.addComponent(new Label("this is student status in admin"));
    	
    }

    private void buildStudentProfile(String studentUserName)
    {

            content.removeAllComponents();
            content.addComponent(new StudentCvViewComp(studentUserName));




    }

}
