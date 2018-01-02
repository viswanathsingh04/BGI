package bgi.com.bgi.bean;

import java.util.List;

/**
 * Awesome Pojo Generator
 */
public class Storelist {
    private List<Store_list> store_list;
    private String status;

    public Storelist() {
    }

    public Storelist(List<Store_list> store_list, String status) {
        this.store_list = store_list;
        this.status = status;
    }

    public void setStore_list(List<Store_list> store_list) {
        this.store_list = store_list;
    }

    public List<Store_list> getStore_list() {
        return store_list;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}