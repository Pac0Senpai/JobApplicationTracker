package com.karkaletsos.jobtracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL =
            "jdbc:mysql://localhost:3306/job_application_tracker";

    private static final String USER = "jobtracker_user";

    private  static final String PASSWORD =
            System.getenv("JOBTRACKER_DB_PASSWORD");

public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
}
}
