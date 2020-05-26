package ru.gurtovenko.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ru.gurtovenko.model.Department;
import ru.gurtovenko.util.EntityJavaFX;
import ru.gurtovenko.util.EntityJavaFXFactory;

import java.sql.SQLException;
import java.util.*;

import static ru.gurtovenko.MainApp.getDepartmentService;

public class MainLayoutController {
    @FXML
    private TableView<DepartmentJavaFX> departmentTableView;
    @FXML
    private TableView<EmployeeJavaFX> employeeTableViewTab1;
    @FXML
    private TableView<EmployeeJavaFX> employeeTableViewTab2;
    @FXML
    private TableView<EmployeeJavaFX> employeeTableViewTab3;
    @FXML
    private TableView<EmployeeJavaFX> employeeTableViewTab4;
    @FXML
    private TableView<EmployeeJavaFX> employeeTableViewTab5;
    @FXML
    private TableView<EmployeeJavaFX> employeeTableViewTab6;
    @FXML
    private TableView<EmployeeJavaFX> employeeTableViewTab7;
    @FXML
    private TableView<EmployeeJavaFX> employeeTableViewTab8;
    @FXML
    private TableView<EmployeeJavaFX> employeeTableViewTab9;
    @FXML
    private TableView<EmployeeJavaFX> employeeTableViewTab10;
    @FXML
    private TableView<EmployeeJavaFX> employeeTableViewTab11;
    @FXML
    private TableView<EmployeeJavaFX> employeeTableViewTab12;

    private Map<Integer, TableView<EmployeeJavaFX>> tableMap = new HashMap<>();
    private ObservableList<DepartmentJavaFX> departmentJavaFXES;


    @FXML
    private void initialize() throws SQLException {
        initMapTable();
        initColumns();
        departmentTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    try {
//                        Date date1 = new Date();
                        departmentToEmployee(newValue);
//                        Date date2 = new Date();
//                        System.out.println(date2.getTime() - date1.getTime());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
        );
    }

    private void initMapTable(){
        tableMap.put(0,employeeTableViewTab1);
        tableMap.put(1,employeeTableViewTab2);
        tableMap.put(2,employeeTableViewTab3);
        tableMap.put(3,employeeTableViewTab4);
        tableMap.put(4,employeeTableViewTab5);
        tableMap.put(5,employeeTableViewTab6);
        tableMap.put(6,employeeTableViewTab7);
        tableMap.put(7,employeeTableViewTab8);
        tableMap.put(8,employeeTableViewTab9);
        tableMap.put(9,employeeTableViewTab10);
        tableMap.put(10,employeeTableViewTab11);
        tableMap.put(11,employeeTableViewTab12);
    }
    private void initColumns() throws SQLException {
        //Department table
        departmentJavaFXES = FXCollections.observableArrayList(DepartmentJavaFXFactory.getEntityJavaFX());
        departmentTableView.setItems(departmentJavaFXES);
        TableColumn<DepartmentJavaFX, String> departmentCol = new TableColumn<>("Департаменты");
        departmentCol.setCellValueFactory(cell -> cell.getValue().departmentProperty());
        departmentTableView.getColumns().add(departmentCol);
        for(int i = 0; i < 12; i++){
            GregorianCalendar gregorianCalendar = new GregorianCalendar(2020, i, 1);
            TableView<EmployeeJavaFX> tableEmployee = tableMap.get(i);

            //Основные три колонки
            TableColumn<EmployeeJavaFX, String> fullNameCol = new TableColumn<>("ФИО");
            TableColumn<EmployeeJavaFX, String> positionCol = new TableColumn<>("Должность");
            TableColumn<EmployeeJavaFX, Number> tabelCol = new TableColumn<>("Табельный №");
            tableEmployee.getColumns().addAll(fullNameCol, positionCol, tabelCol);

            for(int z = 0; z < gregorianCalendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH); z++){
                TableColumn<EmployeeJavaFX, String> markColumn = new TableColumn<>(z+1+"");
                tableEmployee.getColumns().add(markColumn);
            }

            TableColumn<EmployeeJavaFX, String> totalCol = new TableColumn<>("Итого");
            tableEmployee.getColumns().add(totalCol);
        }
    }

    private void departmentToEmployee(DepartmentJavaFX departmentJavaFX) throws SQLException {
        for(int i = 0; i < 12; i++){
            TableView<EmployeeJavaFX> tableEmployee = tableMap.get(i);
            int countColumns = tableEmployee.getColumns().size();
            System.out.println(i);
            tableEmployee.setItems(departmentJavaFX.getEmployeeJavaFX(i));
            System.out.println(departmentJavaFX.getEmployeeJavaFX(i));
            //заполнение основных столбцов
            TableColumn<EmployeeJavaFX, String> column = (TableColumn<EmployeeJavaFX, String>) tableEmployee.getColumns().get(0);
            column.setCellValueFactory(cell -> cell.getValue().fullNameProperty());
            column = (TableColumn<EmployeeJavaFX, String>) tableEmployee.getColumns().get(1);
            column.setCellValueFactory(cell -> cell.getValue().positionProperty());
            TableColumn<EmployeeJavaFX, Number> columnLong = (TableColumn<EmployeeJavaFX, Number>) tableEmployee.getColumns().get(2);
            columnLong.setCellValueFactory(cell -> cell.getValue().tabelProperty());
            //заполнение отметок
            for(int z = 3; z < (countColumns - 1); z++){
                int finalZ = z - 3;
                column = (TableColumn<EmployeeJavaFX, String>) tableEmployee.getColumns().get(z);
                int finalZ1 = z;
                column.setCellValueFactory(cell -> cell.getValue().propertyMark(finalZ1 - 3));
            }
            //заполнение Итого
            column = (TableColumn<EmployeeJavaFX, String>) tableEmployee.getColumns().get(countColumns-1);
            column.setCellValueFactory(cell -> cell.getValue().totalProperty());
        }

//        for(int i = 0; i < 12; i++){
//            GregorianCalendar gregorianCalendar = new GregorianCalendar(2020, i, 1);
//            TableView<EmployeeJavaFX> tableEmployee = tableMap.get(i);
//            ObservableList<EmployeeJavaFX> entityJavaFXObservableList =
//                    FXCollections.observableList(EmployeeJavaFXFactory.getEntityJavaFX(i+1, department));
//            tableEmployee.setItems(entityJavaFXObservableList);
//
//            //Основные три колонки
//            TableColumn<EmployeeJavaFX, String> fullNameCol = new TableColumn<>("ФИО");
//            fullNameCol.setCellValueFactory(cell -> cell.getValue().fullNameProperty());
//            TableColumn<EmployeeJavaFX, String> positionCol = new TableColumn<>("Должность");
//            positionCol.setCellValueFactory(cell -> cell.getValue().positionProperty());
//            TableColumn<EmployeeJavaFX, Number> tabelCol = new TableColumn<>("Табельный №");
//            tabelCol.setCellValueFactory(cell -> cell.getValue().tabelProperty());
//            tableEmployee.getColumns().addAll(fullNameCol, positionCol, tabelCol);
//
//            for(int z = 0; z < gregorianCalendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH); z++){
//                TableColumn<EmployeeJavaFX, String> markColumn = new TableColumn<>(z+1+"");
//                int finalZ = z;
//                markColumn.setCellValueFactory(cell -> cell.getValue().propertyMark(finalZ));
//                tableEmployee.getColumns().add(markColumn);
//            }
//
//            TableColumn<EmployeeJavaFX, String> totalCol = new TableColumn<>("Итого");
//            totalCol.setCellValueFactory(cell -> cell.getValue().totalProperty());
//            tableEmployee.getColumns().add(totalCol);
//        }
    }
    private void departmentToEmployee(Department department) throws SQLException {
        for(int i = 0; i < 12; i++){
            TableView<EntityJavaFX> tableEmployee = tableMap.get(i);
            int countColumns = tableEmployee.getColumns().size();
//            ObservableList<EntityJavaFX> entityJavaFXObservableList =
//                    FXCollections.observableList(EntityJavaFXFactory.getEntityJavaFX(i+1, department));
            ObservableList<EntityJavaFX> entityJavaFXObservableList =
                    FXCollections.observableList(new ArrayList<>(department.getEntityJavaFX().get(i).values()));
            tableEmployee.setItems(entityJavaFXObservableList);

            //Основные три колонки
            TableColumn<EntityJavaFX, String> columnString = (TableColumn<EntityJavaFX, String>) tableEmployee.getColumns().get(0);
            columnString.setCellValueFactory(cell -> cell.getValue().fullNameProperty());
            columnString = (TableColumn<EntityJavaFX, String>) tableEmployee.getColumns().get(1);
            columnString.setCellValueFactory(cell -> cell.getValue().positionProperty());
            TableColumn<EntityJavaFX, Number> columnLong = (TableColumn<EntityJavaFX, Number>) tableEmployee.getColumns().get(2);
            columnLong.setCellValueFactory(cell -> cell.getValue().tabelProperty());

            for(int z = 3; z < (countColumns - 1); z++){
                columnString = (TableColumn<EntityJavaFX, String>) tableEmployee.getColumns().get(z);
                int finalZ = z;
                columnString.setCellValueFactory(cell -> cell.getValue().propertyMark(finalZ - 3));
            }

            columnString = (TableColumn<EntityJavaFX, String>) tableEmployee.getColumns().get(countColumns - 1);
            columnString.setCellValueFactory(cell -> cell.getValue().totalProperty());
        }
    }
}
