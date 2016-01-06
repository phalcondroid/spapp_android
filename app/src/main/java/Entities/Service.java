package Entities;


import com.orm.SugarRecord;

/**
 * Created by julianmolina on 29/12/15.
 */
public class Service extends SugarRecord {

    private Long id;
    private String name;
    private String description;
    private String image;

    public Service() {

    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
