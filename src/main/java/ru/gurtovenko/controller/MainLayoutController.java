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
    private TableView<Department> departmentTableView;
    @FXML
    private TableView<EntityJavaFX> employeeTableViewTab1;
    @FXML
    private TableView<EntityJavaFX> employeeTableViewTab2;
    @FXML
    private TableView<EntityJavaFX> employeeTableViewTab3;
    @FXML
    private TableView<EntityJavaFX> employeeTableViewTab4;
    @FXML
    private TableView<EntityJavaFX> employeeTableViewTab5;
    @FXML
    private TableView<EntityJavaFX> employeeTableViewTab6;
    @FXML
    private TableView<EntityJavaFX> employeeTableViewTab7;
    @FXML
    private TableView<EntityJavaFX> employeeTableViewTab8;
    @FXML
    private TableView<EntityJavaFX> employeeTableViewTab9;
    @FXML
    private TableView<EntityJavaFX> employeeTableViewTab10;
    @FXML
    private TableView<EntityJavaFX> employeeTableViewTab11;
    @FXML
    private TableView<EntityJavaFX> employeeTableViewTab12;

    private Map<Integer, TableView<EntityJavaFX>> tableMap = new HashMap<>();


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
        ObservableList<Department> departments = FXCollections.observableList(getDepartmentService().getAll());
        departmentTableView.setItems(departments);
        TableColumn<Department, String> departmentCol = new TableColumn<>("Департаменты");
        departmentTableView.getColumns().add(departmentCol);
        departmentCol.setCellValueFactory(cell -> cell.getValue().departmentProperty());

        for(Department department: departments){
            department.setEntityJavaFX(new HashMap<>());
            for(int i = 0; i < 12; i++){
                department.getEntityJavaFX().put(i, new HashMap<>());
                int index = 0;
                for(EntityJavaFX entityJavaFX: EntityJavaFXFactory.getEntityJavaFX(i+1, department)){
                    department.getEntityJavaFX().get(i).put(index, entityJavaFX);
                    index++;
                }
            }
        }

        for(int i = 0; i < 12; i++){
            GregorianCalendar gregorianCalendar = new GregorianCalendar(2020, i, 1);
            TableView<EntityJavaFX> tableEmployee = tableMap.get(i);
            //Основные три колонки
            TableColumn<EntityJavaFX, String> fullNameCol = new TableColumn<>("ФИО");
            TableColumn<EntityJavaFX, String> positionCol = new TableColumn<>("Должность");
            TableColumn<EntityJavaFX, Number> tabelCol = new TableColumn<>("Табельный №");
            tableEmployee.getColumns().addAll(fullNameCol, positionCol, tabelCol);

            for(int z = 0; z < gregorianCalendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH); z++){
                TableColumn<EntityJavaFX, String> markColumn = new TableColumn<>(z+1+"");
                tableEmployee.getColumns().add(markColumn);
            }

            TableColumn<EntityJavaFX, String> totalCol = new TableColumn<>("Итого");
            tableEmployee.getColumns().add(totalCol);
        }

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
