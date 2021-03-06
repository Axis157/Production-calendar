package ru.gurtovenko;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.gurtovenko.config.SpringConfig;
import ru.gurtovenko.model.Calendar;
import ru.gurtovenko.model.Department;
import ru.gurtovenko.service.CalendarService;
import ru.gurtovenko.service.DepartmentService;
import ru.gurtovenko.service.EmployeeService;
import ru.gurtovenko.service.EventService;
import ru.gurtovenko.util.InitData;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainApp extends Application implements Runnable{

    private static ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    private static DepartmentService departmentService;
    private static EmployeeService employeeService;
    private static CalendarService calendarService;
    private static EventService eventService;
    private Stage primaryStage;
    private BorderPane rootPane;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException, SQLException {
//        File file = new File("./src/main/resources/workornot.txt");
//        System.out.println(file.length() == 0);
        departmentService = context.getBean("departmentService", DepartmentService.class);
        employeeService = context.getBean("employeeService", EmployeeService.class);
        calendarService = context.getBean("calendarService", CalendarService.class);
        eventService = context.getBean("eventService", EventService.class);
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Производственный календарь");
        InitData.dataInDB();
        initRootLayout();
    }

    private void initRootLayout() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainLayout.fxml"));
        rootPane = loader.load();
        Scene scene = new Scene(rootPane);
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(800);
        primaryStage.show();
    }

    public static ApplicationContext getContext(){
        return context;
    }

    public static DepartmentService getDepartmentService() {
        return departmentService;
    }

    public static EmployeeService getEmployeeService() {
        return employeeService;
    }

    public static CalendarService getCalendarService() {
        return calendarService;
    }

    public static EventService getEventService() {
        return eventService;
    }

    @Override
    public void run() {
        try {
            initRootLayout();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
