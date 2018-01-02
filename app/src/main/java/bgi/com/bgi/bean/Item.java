package bgi.com.bgi.bean;

import java.util.List;

/**
 * Awesome Pojo Generator
 */
public class Item {
    private List<Content> Content;

    public Item() {
    }

    public Item(List<Content> Content) {
        this.Content = Content;
    }

    public void setContent(List<Content> Content) {
        this.Content = Content;
    }

    public List<Content> getContent() {
        return Content;
    }
}