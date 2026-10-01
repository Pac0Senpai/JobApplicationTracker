package com.karkaletsos.jobtracker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<JobApplication> applications = new ArrayList<>();

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

                    JobApplication newApplication = new JobApplication(
                            applications.size() + 1,
                            company,
                            position,
                            LocalDate.now(),
                            "Applied",
                            notes
                    );

                    applications.add(newApplication);

                    System.out.println("Application added successfully!");
                    break;

                case 2:
                    if (applications.isEmpty()) {
                        System.out.println("No applications found.");
                    } else {
                        for (JobApplication application : applications) {
                            System.out.println(application);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter application ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    JobApplication selectApplication = null;

                    for (JobApplication application : applications){
                        if (selectApplication == null){
                            System.out.println("Application not found.");
                        }
                        if (application.getId() == id) {
                            selectApplication = application;
                            break;
                        }
                    }

                case 4:
                    System.out.println("Search by company selected.");
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