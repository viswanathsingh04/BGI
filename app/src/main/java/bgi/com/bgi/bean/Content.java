package bgi.com.bgi.bean;

/**
 * Awesome Pojo Generator
 */
public class Content {
    private String URL;
    private String Name;

    public Content() {
    }

    public Content(String URL, String Name) {
        this.URL = URL;
        this.Name = Name;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getURL() {
        return URL;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getName() {
        return Name;
    }
}