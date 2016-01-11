package Entities;

import com.orm.SugarRecord;

/**
 * Created by julianmolina on 10/01/16.
 */
public class ServiceCategory extends SugarRecord {

    private Long id;
    private int idServiceCategory;
    private int idService;
    private String name;
    private String description;
    private String image;
    private String video;

    public ServiceCategory() {

    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
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

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public int getIdServiceCategory() {
        return idServiceCategory;
    }

    public void setIdServiceCategory(int idServiceCategory) {
        this.idServiceCategory = idServiceCategory;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }
}
