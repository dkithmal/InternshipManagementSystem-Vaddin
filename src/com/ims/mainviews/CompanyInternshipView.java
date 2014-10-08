package com.ims.mainviews;

import com.ims.ImsUI;
import com.ims.business.StudentAppliedCompanyDAO;
import com.ims.components.StudentDetailsViewComp;
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

public class CompanyInternshipView extends CssLayout implements View{
	
	CssLayout content = new CssLayout();

	@Override
	public void enter(ViewChangeEvent event) {
		setSizeFull();
		
		buildHomeMainView();
		
		if (event.getParameters().equals("all_applied_students")) {
			
			buildAllAppliedStudentView();

			
		}
		else if (event.getParameters().equals("not_selected_students_for_inverivew"))
		{
			buildNotSelctedStudentsForInterview();
			
		}
		else if (event.getParameters().equals("selected_students_for_inverivew"))
		{
			buildSelctedStudentsForInterview();
			
		}
		else if (event.getParameters().equals("selected_students_for_internship"))
		{
			buildSelectedStudentsForInternship();
			
		}
		
		else if (event.getParameters().equals("update_vacancies"))
		{
			buildUpdateVacanciesView();
			
		}
		
		else if(event.getParameters().startsWith("student"))
		{

			
			buildStudentProfile(event.getParameters().substring(event.getParameters().lastIndexOf('/')+1));
		}

	}
	
	
	
    private void buildHomeMainView()
    { 
    	
    	addComponent(new HorizontalLayout(){
    		{
    			 setSizeFull();
    			addComponent(new VerticalLayout(){
    				{
    					
    					setHeight("100%");
    					setWidth(null);
    					addStyleName("submenu");

    	                final Button allAppliedStudents = new Button("All Applied Students");
    	                allAppliedStudents.setStyleName(BaseTheme.BUTTON_LINK);
    	                addComponent(allAppliedStudents);
    	                
    	                allAppliedStudents.addClickListener(new ClickListener() {
    	                    @Override
    	                    public void buttonClick(ClickEvent event) {
    	                    	
    	                    	getUI().getNavigator().navigateTo("/internship/all_applied_students");


    	                    }
    	                    
    	                
    	                   });
    	                
    	                final Button notSelectedForInterview = new Button("Not Selected Students For Interview"); 
    	                notSelectedForInterview.setStyleName(BaseTheme.BUTTON_LINK);
    	                addComponent(notSelectedForInterview);
    	                
    	                notSelectedForInterview.addClickListener(new ClickListener() {
    	                    @Override
    	                    public void buttonClick(ClickEvent event) {
    	                    	
    	                    	
    	                    	getUI().getNavigator().navigateTo("/internship/not_selected_students_for_inverivew");

    	                    }
    	                    
    	                
    	                   });

	                
    	                
    	                final Button selectedForInterview = new Button("Selected Students For Interview"); 
    	                selectedForInterview.setStyleName(BaseTheme.BUTTON_LINK);
    	                addComponent(selectedForInterview);
    	                
    	                selectedForInterview.addClickListener(new ClickListener() {
    	                    @Override
    	                    public void buttonClick(ClickEvent event) {
    	                    	
    	                    	
    	                    	getUI().getNavigator().navigateTo("/internship/selected_students_for_inverivew");

    	                    }
    	                    
    	                
    	                   });
    	                
    	                final Button selectedForInternship = new Button("Selected Students For Internship"); 
    	                selectedForInternship.setStyleName(BaseTheme.BUTTON_LINK);
    	                addComponent(selectedForInternship);
    	                
    	                selectedForInternship.addClickListener(new ClickListener() {
    	                    @Override
    	                    public void buttonClick(ClickEvent event) {
    	                    	
    	                    	
    	                    	getUI().getNavigator().navigateTo("/internship/selected_students_for_internship");

    	                    }
    	                    
    	                
    	                   });
    	                
    	                final Button updateVacancy = new Button("Update Vacancy"); 
    	                updateVacancy.setStyleName(BaseTheme.BUTTON_LINK);
    	                addComponent(updateVacancy);
    	                
    	                updateVacancy.addClickListener(new ClickListener() {
    	                    @Override
    	                    public void buttonClick(ClickEvent event) {
    	                    	
    	                    	
    	                    	getUI().getNavigator().navigateTo("/internship/update_vacancies");

    	                    }
    	                    
    	                
    	                   });
    	                
    	                setExpandRatio(updateVacancy, 1);
    	                

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
    	content.addComponent(new Label("this is home content"));
    			
    }

    private void buildAllAppliedStudentView()
    {
    	content.removeAllComponents();
    	content.addComponent(new StudentDetailsViewComp("All"));
    	
    }
    
    private void buildNotSelctedStudentsForInterview()
    {
    	content.removeAllComponents();
    	content.addComponent(new StudentDetailsViewComp("NotSelectedForInterview"));
    	
    }
    
    
    private void buildSelctedStudentsForInterview()
    {
    	content.removeAllComponents();
    	content.addComponent(new StudentDetailsViewComp("SelectedForInterview"));
    	
    }
    
    private void buildSelectedStudentsForInternship()
    {
    	content.removeAllComponents();
    	content.addComponent(new StudentDetailsViewComp("SelectedForInternship"));
    	
    }
    
    private void buildUpdateVacanciesView()
    {
    	content.removeAllComponents();
    	content.addComponent(new Label("this is update vacany"));
    	
    }
    
    
    private void buildStudentProfile(String studentUserName)
    {
    	final StudentAppliedCompanyDAO studentAppliedCompanyDAO=(StudentAppliedCompanyDAO)ImsUI.context.getBean("AccessStudentAppliedCompany");
    	if(studentAppliedCompanyDAO.checkStudentAppliedComapany(VaadinSession.getCurrent().getAttribute("UserName").toString(), studentUserName))
    	{
    		
    	}
    	else
    	{
    		getUI().getNavigator().navigateTo("/home");
    	}

    	
    }


}
