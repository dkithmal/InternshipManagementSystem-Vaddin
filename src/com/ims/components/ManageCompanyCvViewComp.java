package com.ims.components;

import com.ims.ImsUI;
import com.ims.business.CompanyDAO;
import com.ims.data.Company;
import com.vaadin.ui.*;

import java.util.List;

/**
 * Created by Damitha on 10/10/14.
 */
public class ManageCompanyCvViewComp extends CustomComponent{
    VerticalLayout mainLayout = new VerticalLayout();
    String companyCvAllowedState;
    List<Company> companyList;


    public ManageCompanyCvViewComp(String state)
    {
        companyCvAllowedState=state;
        setSizeFull();
        buildManageCompanyCvView();
        setCompositionRoot(mainLayout);
    }



    private void buildManageCompanyCvView()
    {
        mainLayout.setSizeFull();

        final CompanyDAO companyDAO=  (CompanyDAO) ImsUI.context.getBean("AccessCompany");

        if (companyCvAllowedState.equals("CvAllowedCompany"))
            companyList=companyDAO.getCvAllowedCompanyList();
        else
            companyList=companyDAO.getCvNotAllowedCompanyList();

        Table table = new Table("");
        table.setSizeFull();



        table.addContainerProperty("Index", Integer.class, null);
        table.addContainerProperty("Company Name", String.class, null);
        if(companyCvAllowedState.equals("CvAllowedCompany"))
            table.addContainerProperty("Un Allow Cv ",  Button.class, null);
        else
            table.addContainerProperty("Allow Cv view",  Button.class, null);



        for(int x=0;x<companyList.size();x++)
        {
            int index= x+1;


            if(companyCvAllowedState.equals("CvAllowedCompany"))
            {
                Button unAlowCvToCompany=new Button("Un Allow Cv view");
                unAlowCvToCompany.setData(companyList.get(x).getCompanyUserName());

                unAlowCvToCompany.addClickListener(new Button.ClickListener() {
                    public void buttonClick(Button.ClickEvent event) {

                        companyDAO.setRemoveCvFromCompany((String)event.getButton().getData());
                        getUI().getNavigator().navigateTo("/company/cv_allowed_companies");
                    }
                });


                table.addItem(new Object[] {index,companyList.get(x).getCompanyName(),unAlowCvToCompany},index);


            }
            else
            {
                Button alloweCvToCompany=new Button("Allow Cv view");
                alloweCvToCompany.setData(companyList.get(x).getCompanyUserName());

                alloweCvToCompany.addClickListener(new Button.ClickListener() {
                    public void buttonClick(Button.ClickEvent event) {
                        companyDAO.setAllowCvToCompany((String)event.getButton().getData());
                        getUI().getNavigator().navigateTo("/company/cv_not_allowed_companies");
                    }
                });


                table.addItem(new Object[] {index,companyList.get(x).getCompanyName(),alloweCvToCompany},index);

            }




        }

        mainLayout.addComponent(table);



    }
}
