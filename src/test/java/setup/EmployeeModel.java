package setup;

public class EmployeeModel {
    String firstName;
    String middleName;
    String lastName;
    String username;
    String password;
    String employeeId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public EmployeeModel(String firstName,String middleName, String lastName, String username, String password){
         this.firstName=firstName;
         this.middleName=middleName;
         this.lastName=lastName;
         this.username=username;
         this.password=password;

    }
    public EmployeeModel(){

    }
}
