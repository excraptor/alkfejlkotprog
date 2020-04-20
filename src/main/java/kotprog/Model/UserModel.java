package kotprog.Model;
import java.io.Serializable;

import javafx.beans.property.*;
/*
 * this class represents a user row in the database
 */
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private StringProperty nick = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();
    private StringProperty gender = new SimpleStringProperty();
    private IntegerProperty age = new SimpleIntegerProperty();
    private StringProperty interest1 = new SimpleStringProperty();
    private StringProperty interest2 = new SimpleStringProperty();

    public final void setNick(String nick) {
        this.nick.set(nick);
    }
    public final void setPassword(String password) {
        this.password.set(password);
    }
    public final void setGender(String gender) {
        this.gender.set(gender);
    }
    public final void setAge(Integer age) {
        this.age.set(age);
    }
    public final void setInterest1(String interest1) {
        this.interest1.set(interest1);
    }
    public final void setInterest2(String interest2) {
        this.interest2.set(interest2);
    }

    public final String getNick() {
        return this.nick.get();
    }
    public final String getPassword() {
        return this.password.get();
    }
    public final Integer getAge() {
        return this.age.get();
    }
    public final String getGender() {
        return this.gender.get();
    }
    public final String getInterest1() {
        return this.interest1.get();
    }
    public final String getInterest2() {
        return this.interest2.get();
    }
    
    public String toString() {
        return  "Nick: " + getNick() + "\n" +
                "Password: haha gotcha it's hashed i swear\n" +
                "Gender: " + getGender() + "\n" +
                "Interests: " + getInterest1() + ", " + getInterest2() + "\n"; 
    }

}