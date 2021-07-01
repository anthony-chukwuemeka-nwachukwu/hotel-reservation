package model;

import java.util.regex.Pattern;

public class Customer {
    public String firstName;
    public String lastName;
    public String email;

    public Customer(String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        String emailRegex = "^(.+)@(.+).(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email, must be of this format, name@domain.com");
        }else{
            this.email = email;
        }
    }

    @Override
    public String toString() {
        return "First Name:" + firstName + ", Last Name:" + lastName + ", Email:" + email;
    }
}
