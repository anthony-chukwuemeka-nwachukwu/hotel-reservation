package model;

import java.util.regex.Pattern;

public class Customer {
    private final String firstName;
    private final String lastName;
    private final String email;

    public Customer(String firstName, String lastName, String email){
        String emailRegex = "^(.+)@(.+).(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (email == null || firstName == null || lastName == null) {
            throw new IllegalArgumentException("All fields are required");
        }else if (!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email, must be of this format, name@domain.com");
        }else{
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {return lastName;}

    public void setFirstName(){}

    public void setLastName(){}

    @Override
    public String toString() {
        return "First Name:" + firstName + ", Last Name:" + lastName + ", Email:" + email;
    }
}
