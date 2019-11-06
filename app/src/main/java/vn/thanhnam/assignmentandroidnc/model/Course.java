package vn.thanhnam.assignmentandroidnc.model;

public class Course {
    private String idCourse;
    private String typeCourse;
    private String nameCourse;
    private String timeCourse;

    public Course() {
    }

    public Course(String idCourse, String nameCourse, String typeCourse, String timeCourse) {
        this.idCourse = idCourse;
        this.typeCourse = typeCourse;
        this.nameCourse = nameCourse;
        this.timeCourse = timeCourse;
    }

    public String getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(String idCourse) {
        this.idCourse = idCourse;
    }

    public String getTypeCourse() {
        return typeCourse;
    }

    public void setTypeCourse(String typeCourse) {
        this.typeCourse = typeCourse;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public String getTimeCourse() {
        return timeCourse;
    }

    public void setTimeCourse(String timeCourse) {
        this.timeCourse = timeCourse;
    }
}
