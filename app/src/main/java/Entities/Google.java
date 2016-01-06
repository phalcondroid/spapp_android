package Entities;

import com.orm.SugarRecord;

/**
 * Created by julianmolina on 6/01/16.
 */
public class Google extends SugarRecord {

    public Long id;
    private String uuid;

    public Google() {

    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
