package ru.gurtovenko.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import static ru.gurtovenko.MainApp.*;
import ru.gurtovenko.model.Department;
import ru.gurtovenko.model.Employee;
import ru.gurtovenko.service.DepartmentService;
import ru.gurtovenko.util.InitData;

import java.sql.SQLException;

public class MainLayoutController {

    @FXML
    private TableView<Department> departmentTableView;
    @FXML
    private TableView<Employee> employeeTableViewTab1;


    @FXML
    private void initialize() throws SQLException {
        InitData.dataInDB();
        ObservableList<Department> departments = FXCollections.observableArrayList(getDepartmentService().getAll());
        departmentTableView.setItems(departments);
        TableColumn<Department, String> col1 = new TableColumn<>("Департаменты");
        departmentTableView.getColumns().add(col1);
        col1.setCellValueFactory(cell -> cell.getValue().departmentProperty());

        departmentTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    try {
                        tab1ShowEmployee(newValue);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
        );
    }

    private void tab1ShowEmployee(Department department) throws SQLException {
        ObservableList<Employee> employees = FXCollections.observableArrayList(
                getEmployeeService().getAllByDepartment(department));
        employeeTableViewTab1.setItems(employees);
        TableColumn<Employee, String> col1 = new TableColumn<>("ФИО");
        TableColumn<Employee, String> col2 = new TableColumn<>("Должность");
        TableColumn<Employee, Number> col3 = new TableColumn<>("Табельный №");
        if(employeeTableViewTab1.getColumns().size() == 0){
                employeeTableViewTab1.getColumns().add(col1);
                employeeTableViewTab1.getColumns().add(col2);
                employeeTableViewTab1.getColumns().add(col3);
        }
        col1.setCellValueFactory(cell -> cell.getValue().fioProperty());
        col2.setCellValueFactory(cell -> cell.getValue().positionProperty());
        col3.setCellValueFactory(cell -> cell.getValue().tabelProperty());
    }
}
