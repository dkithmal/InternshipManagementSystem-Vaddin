package com.ims.components;

import com.ims.ImsUI;
import com.ims.business.StudentDAO;
import com.ims.data.Student;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Table;

import java.util.List;

/**
 * Created by Damitha on 10/10/14.
 */
public class ViewStudentStateComp extends CustomComponent {


    AbsoluteLayout mainLayout = new AbsoluteLayout();
    String studentsType;
    List<Student> studentList;



    public ViewStudentStateComp(String type)
    {
        studentsType=type;

        setSizeFull();
        buildStudentStateView();
        setCompositionRoot(mainLayout);

    }



    private void buildStudentStateView()
    {


        final StudentDAO studentDAO= (StudentDAO) ImsUI.context.getBean("AccessStudent");
        if(studentsType.equals("selected"))
        {
            studentList=studentDAO.getStudentsSelectedForInternship();

        }
        else
        {
            studentList= studentDAO.getStudentsNotSelectedForInternship();

        }



        Table table = new Table("");
        table.setSizeFull();



        table.addContainerProperty("Index", Integer.class, null);
        table.addContainerProperty("Student Index no", String.class, null);
        table.addContainerProperty("Student Name", String.class, null);
        table.addContainerProperty("Student Mobile", String.class, null);
        table.addContainerProperty("Student Email", String.class, null);

        if(studentsType.equals("selected"))
        {
            table.addContainerProperty("Selected Company", String.class, null);

        }



        for(int x=0;x<studentList.size();x++)
        {
            int index=x+1;

            if(studentsType.equals("selected"))
            {

                table.addItem(new Object[] {index,studentList.get(x).getStudentUserName(),studentList.get(x).getNameInFull(),studentList.get(x).getMobile(),studentList.get(x).getEmail(),studentList.get(x).getSelectedCompanyName()},index);

            }

            else
            {
                table.addItem(new Object[] {index,studentList.get(x).getStudentUserName(),studentList.get(x).getNameInFull(),studentList.get(x).getMobile(),studentList.get(x).getEmail()},index);


            }




        }
        mainLayout.addComponent(table,"bottom:75px;left: 0px; top: 0px;right:0px");




    }

}
