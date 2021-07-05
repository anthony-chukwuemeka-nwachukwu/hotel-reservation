package model;

import java.util.Objects;
import java.util.regex.Pattern;

public class Customer {
    private final String firstName;
    private final String lastName;
    private final String email;
    public static final String emailRegex = "^(.+)@(.+).(.+)$";

    public Customer(String firstName, String lastName, String email){
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
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Customer other = (Customer) obj;
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }

        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }

        return Objects.equals(this.email, other.email);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode (this.firstName);
        hash = 37 * hash + Objects.hashCode (this.lastName);
        hash = 37 * hash + Objects.hashCode (this.email);
        return hash;
    }

    @Override
    public String toString() {
        return "First Name:" + firstName + ", Last Name:" + lastName + ", Email:" + email;
    }
}
