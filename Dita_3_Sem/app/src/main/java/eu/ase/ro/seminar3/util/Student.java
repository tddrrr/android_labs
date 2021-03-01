package eu.ase.ro.seminar3.util;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {
    private String name;
    private int age;
    private Date enrollmentDate;
    private StudyType studyType;
    private String faculty;

    public Student(String name, int age, Date enrollmentDate, StudyType studyType, String faculty) {
        this.name = name;
        this.age = age;
        this.enrollmentDate = enrollmentDate;
        this.studyType = studyType;
        this.faculty = faculty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public StudyType getStudyType() {
        return studyType;
    }

    public void setStudyType(StudyType studyType) {
        this.studyType = studyType;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return name + ", " + age + " ani, inscris pe "
                + new DateConverter().toString(enrollmentDate)
                + ", la " + faculty + " - " + studyType;
    }
}
