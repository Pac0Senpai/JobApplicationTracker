package com.karkaletsos.jobtracker;

import java.time.LocalDate;
import com.karkaletsos.jobtracker.JobApplication;


public class Main {
        public static void main(String[] args) {
            System.out.println("Job Application Tracker");
            JobApplication application = new JobApplication(
                    1,
                    "Deloitte",
                    "Junior SAP ABAP",
                    LocalDate.of(2026, 10, 1),
                    "Applied",
                    "Application submitted"
            );

System.out.println(application);
        }
    }
