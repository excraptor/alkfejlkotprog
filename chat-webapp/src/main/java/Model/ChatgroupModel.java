package Model;
import java.io.Serializable;
public class ChatgroupModel implements Serializable{
    private static final long serialVersionUID = 1L;

    private String name;
    private String theme;

    public ChatgroupModel (String name, String theme) {
        this.name = name;
        this.theme = theme;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTheme() {
        return this.theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

}