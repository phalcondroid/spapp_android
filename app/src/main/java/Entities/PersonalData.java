package Entities;

import com.orm.SugarRecord;

/**
 * Created by julianmolina on 5/01/16.
 */
public class PersonalData extends SugarRecord {

    private Long id;
    private Boolean firstData;

    public PersonalData() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getFirstData() {
        return firstData;
    }

    public void setFirstData(Boolean firstData) {
        this.firstData = firstData;
    }

}
