package core.Person;

public class Person {
    private String username;
    private String email;
    private String password;

    public Person(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
