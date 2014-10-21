package com.ims.components;

import com.ims.ImsUI;
import com.ims.business.StudentAppliedCompanyDAO;
import com.ims.data.StudentAppliedCompany;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;

import java.util.List;

/**
 * Created by Damitha on 10/12/14.
 */
public class StudentAppliedCompayStateViewComp extends CustomComponent {

    VerticalLayout mainLayout;
    List<StudentAppliedCompany> studentAppliedCompanyList;


    public StudentAppliedCompayStateViewComp() {

        setSizeFull();
        buildStudentAppliedCompanyStateView();
        setCompositionRoot(mainLayout);

    }


    private void buildStudentAppliedCompanyStateView()
    {
        mainLayout= new VerticalLayout();
        mainLayout.setSizeFull();


        StudentAppliedCompanyDAO studentAppliedCompanyDAO=(StudentAppliedCompanyDAO) ImsUI.context.getBean("AccessStudentAppliedCompany");
        studentAppliedCompanyList=studentAppliedCompanyDAO.getStudentAppliedCompanyList(VaadinSession.getCurrent().getAttribute("UserName").toString());

        Table table = new Table("");
        table.setSizeFull();


        table.addContainerProperty("Index", Integer.class, null);
        table.addContainerProperty("Company Name", String.class, null);
        table.addContainerProperty("State Of Company",  String.class, null);
        table.addContainerProperty("View Company",  Button.class, null);

        for (int x=0;x<studentAppliedCompanyList.size();x++)
        {
            int index=x+1;

            Button viewCompnayProfile= new Button("view Company");

            viewCompnayProfile.setData(studentAppliedCompanyList.get(x).getCompany().getCompanyName());

            viewCompnayProfile.addClickListener(new Button.ClickListener() {
                public void buttonClick(Button.ClickEvent event) {

                    //companyDAO.unAllowedCompany((String)event.getButton().getData());
                    //getUI().getNavigator().navigateTo("/student/allowed_companies");
                    getUI().getNavigator().navigateTo("/company/"+(String)event.getButton().getData());
                }
            });


            table.addItem(new Object[] {index,studentAppliedCompanyList.get(x).getCompany().getCompanyName(),studentAppliedCompanyList.get(x).getState(),viewCompnayProfile},index);


        }

        mainLayout.addComponent(table);




    }
}
