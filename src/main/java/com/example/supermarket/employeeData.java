package com.example.supermarket;

public class employeeData {
    private final String employeeID;
    private final String password;
    private final String firstName;
    private final String lastName;

    private final String email;

    private final String jobTitle;

    public employeeData(String employeeID, String password, String firstName, String lastName, String email, String jobTitle) {
        this.employeeID = employeeID;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.jobTitle = jobTitle;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getJobTitle() {
        return jobTitle;
    }
}
