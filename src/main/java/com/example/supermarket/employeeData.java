package com.example.supermarket;

public class employeeData {
    private String employeeID;
    private String password;
    private String firstName;
    private String lastName;

    private String email;

    private String jobTitle;

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

    public employeeData(String employeeID, String password, String firstName, String lastName, String email, String jobTitle) {
        this.employeeID = employeeID;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.jobTitle = jobTitle;
    }
}
