package com.ims.components;

import com.ims.ImsUI;
import com.ims.business.CompanyDAO;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;

/**
 * Created by Damitha on 10/20/14.
 */
public class UpdateCompanyVacancyComp extends CustomComponent {

    VerticalLayout mainLayout = new VerticalLayout();

    public UpdateCompanyVacancyComp()
    {
        setSizeFull();
        buildUpadateCompanyView();
        setCompositionRoot(mainLayout);

    }


    private void buildUpadateCompanyView()
    {

        mainLayout.setSizeFull();
        mainLayout.setSpacing(true);

       final  CompanyDAO companyDAO=  (CompanyDAO) ImsUI.context.getBean("AccessCompany");

       final Label upadateCompayVacancyLabel= new Label("Update Student No of Vacancies");


       final TextField enterNumberOfVacancies= new TextField("Enter No of Vacancies");

        Button submitNoOfVacancy= new Button("Submit No of Vacancies");

        submitNoOfVacancy.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {


                companyDAO.updateNoOfVacancies(VaadinSession.getCurrent().getAttribute("UserName").toString(),(Integer.valueOf(enterNumberOfVacancies.getValue().toString())));
                Notification.show("Successfully Updated No of Vacancies");

            }
        });


        mainLayout.addComponent(upadateCompayVacancyLabel);
        mainLayout.addComponent(enterNumberOfVacancies);
        mainLayout.addComponent(submitNoOfVacancy);
        mainLayout.setExpandRatio(submitNoOfVacancy,1);


    }



}
