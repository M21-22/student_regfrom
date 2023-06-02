package domain;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

public class StudentInfo implements ObjectInfo, Serializable {
    @JsonProperty("ID")
    private long id;
    @JsonProperty("NAME")
    private String name;
    @JsonProperty("SURNAME")
    private String surname;
    @JsonProperty("COURSE")
    private int course_id;
    @JsonProperty("EMAIL")
    private String email;
    @JsonProperty("MOBILE")
    private String mobile;
    @JsonProperty("BIRTH_DATE")
    private String birth_date;
    @JsonProperty("GENDER_ID")
    private int gender_id;
    @JsonProperty("SCHOOL")
    private String school;
    @JsonProperty("ENTRANCE_SCORE")
    private int entrance_score;
    @JsonProperty("COMPLETION_DATE")
    private String completion_date;
    @JsonProperty("IMAGEENCODED")
    private String image;

    public StudentInfo() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public int getGender_id() {
        return gender_id;
    }

    public void setGender_id(int gender_id) {
        this.gender_id = gender_id;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getEntrance_score() {
        return entrance_score;
    }

    public void setEntrance_score(int entrance_score) {
        this.entrance_score = entrance_score;
    }

    public String getCompletion_date() {
        return completion_date;
    }

    public void setCompletion_date(String completion_date) {
        this.completion_date = completion_date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public int getColumnCount() {
        return getClass().getDeclaredFields().length;
    }

    @Override
    public String toString() {
        return "StudentInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", course_id=" + course_id +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", birth_date='" + birth_date + '\'' +
                ", gender_id=" + gender_id +
                ", school='" + school + '\'' +
                ", entrance_score=" + entrance_score +
                ", completion_date='" + completion_date + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
