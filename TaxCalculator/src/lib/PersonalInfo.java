package lib;

public class PersonalInfo {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String address;

    public PersonalInfo(String employeeId, String firstName, String lastName, String idNumber, String address) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.address = address;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public String getAddress() {
        return address;
    }
}