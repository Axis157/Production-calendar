package ru.gurtovenko.util;

import ru.gurtovenko.model.Department;
import ru.gurtovenko.model.entityfx.DepartmentJavaFX;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ru.gurtovenko.MainApp.getDepartmentService;

public class DepartmentJavaFXFactory {

    public static List<DepartmentJavaFX> getEntityJavaFX() throws SQLException {
        List<DepartmentJavaFX> departmentJavaFXList = new ArrayList<>();
        for(Department department: getDepartmentService().getAll()){
            DepartmentJavaFX departmentJavaFX = new DepartmentJavaFX(department);
            departmentJavaFXList.add(departmentJavaFX);
        }
        return departmentJavaFXList;
    }
}
