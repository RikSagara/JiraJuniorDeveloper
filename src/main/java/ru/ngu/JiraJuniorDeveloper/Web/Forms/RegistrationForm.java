package ru.ngu.JiraJuniorDeveloper.Web.Forms;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

public class RegistrationForm {
    @Size(min = 3,max=20, message = "Name must be at least 3 characters long")
    @Pattern(regexp = "[a-zA-Z-_.0-9]*",
            message = "Only letters, digits, underscore, minus sign and " +
                    " dots are allowed in login.")
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegistrationForm{" +
                "name='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationForm that = (RegistrationForm) o;
        return Objects.equals(userName, that.userName) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password);
    }
}
