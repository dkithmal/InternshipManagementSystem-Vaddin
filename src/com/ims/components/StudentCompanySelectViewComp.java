package com.ims.components;

import com.ims.ImsUI;
import com.ims.business.CompanyDAO;
import com.ims.business.StudentDAO;
import com.ims.data.StudentAppliedCompany;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Damitha on 10/16/14.
 */
public class StudentCompanySelectViewComp extends CustomComponent {

    VerticalLayout mainLayout;

    private Collection<StudentAppliedCompany> studentAppliedCompanies;
    private StudentDAO studentDAO;
    private CompanyDAO companyDAO;

    boolean studentSelected=false;

    List<String> companyNameList;
    List<String> studentAppliedCompanyList= new ArrayList<String>();

    public StudentCompanySelectViewComp() {

        setSizeFull();
        buildStudentCompanySelectView();
        setCompositionRoot(mainLayout);
    }


    private void buildStudentCompanySelectView()
    {
        mainLayout= new VerticalLayout();
        mainLayout.setSizeFull();

        Table table = new Table();
        table.setWidth("100%");
        table.setHeightUndefined();
        table.addContainerProperty("Index", Integer.class, null);
        table.addContainerProperty("Selected Company", String.class, null);
        table.addContainerProperty("Company State",  String.class, null);


        studentDAO=  (StudentDAO) ImsUI.context.getBean("AccessStudent");
        companyDAO=  (CompanyDAO)ImsUI.context.getBean("AccessCompany");

        studentAppliedCompanies=studentDAO.getStudent(VaadinSession.getCurrent().getAttribute("UserName").toString()).getStudentCompany();


        int index=1;
        for(StudentAppliedCompany studentAppliedCompany:studentAppliedCompanies)
        {
            table.addItem(new Object[] {index,studentAppliedCompany.getCompany().getCompanyName(),studentAppliedCompany.getState()},index);
            studentAppliedCompanyList.add(studentAppliedCompany.getCompany().getCompanyName());
            if(studentAppliedCompany.getStudent().isSelected())
                studentSelected= true;
            index++;
        }



        mainLayout.addComponent(table);


        Label selectCompanyLabel=new Label("Select Company");
        mainLayout.addComponent(selectCompanyLabel);
        companyNameList=companyDAO.getAllAllowedCompanyNames();


      // if(!studentSelected)
        {
            ComboBox selectCompanySelect= new ComboBox();

            for(String companyName:companyNameList)
            {
                if(!(studentAppliedCompanyList.contains( companyName)))
                {
                    selectCompanySelect.addItem(companyName);

                }

            }

            mainLayout.addComponent(selectCompanySelect);


            Button addSelectedcompany =new Button("Add To Company List");


            addSelectedcompany.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {

                }
            });

            mainLayout.addComponent(addSelectedcompany);




            mainLayout.setExpandRatio(addSelectedcompany,1);

        }



    }
}
