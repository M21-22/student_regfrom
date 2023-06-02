package domain;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

public class CourseInfo implements Serializable, ObjectInfo {
    @JsonProperty("ID")
    private long id;
    @JsonProperty("CRSNAME")
    private String crsname;
    @JsonProperty("CRSCODE")
    private String crscode;
    @JsonProperty("LECTURER")
    private long lecturer_id;

    public long getId() {
        return id;
    }
    public String getCrsname() {
        return crsname;
    }
    public void setCrsname(String crsname) {
        this.crsname = crsname;
    }
    public String getCrscode() {
        return crscode;
    }
    public void setCrscode(String crscode) {
        this.crscode = crscode;
    }
    public long getLecturer_id() {
        return lecturer_id;
    }
    public void setLecturer_id(long lecturer_id) {
        this.lecturer_id = lecturer_id;
    }
}
