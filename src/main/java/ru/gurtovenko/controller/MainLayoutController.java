package ru.gurtovenko.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ru.gurtovenko.model.Department;
import ru.gurtovenko.model.Employee;
import ru.gurtovenko.util.EntityJavaFX;
import ru.gurtovenko.util.EntityJavaFXFactory;

import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static ru.gurtovenko.MainApp.*;

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

    }

    private void tab1ShowEmployee(Department department) throws SQLException {

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
        TableColumn<Department, String> departmentCol = new TableColumn<>("Департаменты");
        departmentTableView.getColumns().add(departmentCol);

        for(int i = 0; i < 12; i++){
            GregorianCalendar gregorianCalendar = new GregorianCalendar(2020, i, 1);
            TableView<EntityJavaFX> tableEmployee = tableMap.get(i);
            ObservableList<EntityJavaFX> entityJavaFXObservableList =
                    FXCollections.observableList(EntityJavaFXFactory.getEntityJavaFX(i+1));
            tableEmployee.setItems(entityJavaFXObservableList);

            //Основные три колонки
            TableColumn<EntityJavaFX, String> fullNameCol = new TableColumn<>("ФИО");
            fullNameCol.setCellValueFactory(cell -> cell.getValue().fullNameProperty());
            TableColumn<EntityJavaFX, String> positionCol = new TableColumn<>("Должность");
            positionCol.setCellValueFactory(cell -> cell.getValue().positionProperty());
            TableColumn<EntityJavaFX, Number> tabelCol = new TableColumn<>("Табельный №");
            tabelCol.setCellValueFactory(cell -> cell.getValue().tabelProperty());
            tableEmployee.getColumns().addAll(fullNameCol, positionCol, tabelCol);

            for(int z = 0; z < gregorianCalendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH); z++){
                TableColumn<EntityJavaFX, String> markColumn = new TableColumn<>(z+1+"");
//                int finalZ = z;
//                markColumn.setCellValueFactory(cell -> cell.getValue().propertyMark(finalZ));
                tableEmployee.getColumns().add(markColumn);
            }

            TableColumn<EntityJavaFX, String> totalCol = new TableColumn<>("Итого");
            totalCol.setCellValueFactory(cell -> cell.getValue().totalProperty());
            tableEmployee.getColumns().add(totalCol);
        }

    }
}
