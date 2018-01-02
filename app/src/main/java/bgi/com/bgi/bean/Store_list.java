package bgi.com.bgi.bean;

/**
 * Awesome Pojo Generator
 */
public class Store_list {
    private String offer;
    private String location;
    private Integer id;
    private String title;
    private String shop_name;
    private String lat;
    private String longitude;

    public Store_list() {
    }

    public Store_list(String offer, String location, Integer id, String title, String shop_name, String lat, String longitude) {
        this.offer = offer;
        this.location = location;
        this.id = id;
        this.title = title;
        this.shop_name = shop_name;
        this.lat = lat;
        this.longitude=longitude;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getOffer() {
        return offer;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLat() {
        return lat;
    }

    public void setlongitude(String longitude) {
        this.longitude=longitude;
    }

    public String getlongitude() {
        return longitude;
    }
}