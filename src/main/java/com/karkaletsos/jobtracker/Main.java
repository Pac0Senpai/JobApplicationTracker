package com.karkaletsos.jobtracker;

import java.time.LocalDate;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {

    public static void main(String[] args) {

        try(Connection connection = DatabaseConnection.getConnection()) {
            System.out.println("Database connection successful");
        }
        catch (SQLException e) {
            System.out.println("Database connection failed");
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Job Application Tracker ===");
            System.out.println("1. Add application");
            System.out.println("2. View applications");
            System.out.println("3. Update status");
            System.out.println("4. Search by company");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Company: ");
                    String company = scanner.nextLine();

                    System.out.print("Position: ");
                    String position = scanner.nextLine();

                    System.out.print("Notes: ");
                    String notes = scanner.nextLine();

                        String sql = """
                                 INSERT INTO job_applications
                                 (company, position, application_date, status, notes)
                                 VALUES (?, ?, ?, ?, ?)
                                 """;

                         try (Connection connection = DatabaseConnection.getConnection();
                              PreparedStatement statement = connection.prepareStatement(sql)) {
                             statement.setString(1, company);
                             statement.setString(2, position);
                             statement.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
                             statement.setString(4, "Applied");
                             statement.setString(5, notes);

                             int rowsInserted = statement.executeUpdate();
                             if(rowsInserted > 0) {
                                 System.out.println("Application saved to database successfully!");
                             }

                         }
                         catch (SQLException e) {
                             System.out.println("Failed to save application to database.");
                             e.printStackTrace();
                         }
                    break;

                case 2:

                    String selectSql = """
                         SELECT * FROM job_applications
                         ORDER BY id
                    """;

                    try (Connection connection = DatabaseConnection.getConnection();
                         PreparedStatement statement = connection.prepareStatement(selectSql);
                         ResultSet resultSet = statement.executeQuery()) {
                        boolean hasApplications = false;
                        while (resultSet.next()) {
                            hasApplications = true;

                            System.out.println(
                                    resultSet.getInt("id") + " | " +
                                            resultSet.getString("company") + " | " +
                                            resultSet.getString("position") + " | " +
                                            resultSet.getDate("application_date") + " | " +
                                            resultSet.getString("status") + " | " +
                                            resultSet.getString("notes")
                            );
                        }
                        if(!hasApplications) {
                            System.out.println("No Applications found.");
                        }
                    }
                    catch (SQLException e) {
                        System.out.println("Failed to load applications from database.");
                        e.printStackTrace();
                    }
                    break;

                case 3:
                    System.out.print("Enter application ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    String findSql = """
                        SELECT * FROM job_applications
                        WHERE id = ?
                    """;

                    try (Connection connection = DatabaseConnection.getConnection();
                         PreparedStatement statement = connection.prepareStatement(findSql)) {

                        statement.setInt(1, id);

                        ResultSet resultSet = statement.executeQuery();

                        if (!resultSet.next()) {
                            System.out.println("Application not found.");
                            break;
                        }

                        String currentStatus = resultSet.getString("status");
                        System.out.println("Current status: " + currentStatus);

                    }
                    catch (SQLException e) {
                        System.out.println("Failed to find application.");
                        e.printStackTrace();
                    }



                    System.out.println("Do you want to update the status? (y/n): ");
                    String answer = scanner.nextLine();
                    if (!answer.equalsIgnoreCase("y")) {
                        break;
                    }
                    System.out.print("Enter new status: ");
                    String newStatus = scanner.nextLine();

                    String updateSql = """
                        UPDATE job_applications
                        SET status = ?
                        WHERE id = ?
                    """;

                    try (Connection connection = DatabaseConnection.getConnection();
                         PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {

                        updateStatement.setString(1, newStatus);
                        updateStatement.setInt(2, id);

                        int rowsUpdated = updateStatement.executeUpdate();

                        if (rowsUpdated > 0) {
                            System.out.println("Status updated successfully.");
                        }
                    }
                    catch (SQLException e) {
                        System.out.println("Failed to update status.");
                        e.printStackTrace();
                    }
                    break;

                case 4:
                    System.out.println("Enter Company name:");
                    String searchCompany = scanner.nextLine();

                    String searchSql = """
                    SELECT * FROM job_applications
                    WHERE company = ?
                    """;

                    try (Connection connection = DatabaseConnection.getConnection();
                         PreparedStatement statement = connection.prepareStatement(searchSql)) {

                        statement.setString(1, searchCompany);

                        ResultSet resultSet = statement.executeQuery();

                        boolean found = false;

                        while (resultSet.next()) {
                            if (!found) {
                                System.out.println("No applications found for this company.");
                            }
                            found = true;

                            System.out.println(
                                    resultSet.getInt("id") + " | " +
                                            resultSet.getString("company") + " | " +
                                            resultSet.getString("position") + " | " +
                                            resultSet.getDate("application_date") + " | " +
                                            resultSet.getString("status") + " | " +
                                            resultSet.getString("notes")
                            );
                        }

                    }
                    catch (SQLException e) {
                        System.out.println("Failed to search applications.");
                        e.printStackTrace();
                    }
                    break;

                case 0:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }

        } while (choice != 0);

        System.out.println("Goodbye!");
        scanner.close();
    }
}