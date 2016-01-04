package Models;

import android.graphics.Bitmap;

/**
 * Created by julianmolina on 29/12/15.
 */
public class Service {

    private int serviceId;
    private int serviceCategoryId;
    private String name;
    private String description;
    private Bitmap image;

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getServiceId() {
        return this.serviceId;
    }

    public void setServiceCategoryId(int serviceCategoryId) {
        this.serviceCategoryId = serviceCategoryId;
    }

    public int getServiceCategoryId() {
        return this.serviceCategoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public String getDescription() {
        return this.description;
    }

    public void setImage(Bitmap img) {
        this.image = img;
    }

    public Bitmap getImage() {
        return this.image;
    }

}
