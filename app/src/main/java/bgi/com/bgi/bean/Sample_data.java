package bgi.com.bgi.bean;

import java.util.List;

/**
 * Awesome Pojo Generator
 */
public class Sample_data {
    private List<Data> data;
    private String status;

    public void setData(List<Data> data) {
        this.data = data;
    }

    public List<Data> getData() {
        return data;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}