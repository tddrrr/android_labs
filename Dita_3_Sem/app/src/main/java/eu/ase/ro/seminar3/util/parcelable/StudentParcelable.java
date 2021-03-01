package eu.ase.ro.seminar3.util.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

import eu.ase.ro.seminar3.util.DateConverter;
import eu.ase.ro.seminar3.util.StudyType;

import static eu.ase.ro.seminar3.util.StudyType.FULL_TIME;

public class StudentParcelable implements Parcelable {
    private String name;
    private int age;
    private Date enrollmentDate;
    private StudyType studyType;
    private String faculty;

    public StudentParcelable(String name, int age, Date enrollmentDate, StudyType studyType, String faculty) {
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

    private StudentParcelable(Parcel source) {
        //ordinea de scriere in fisier trebuie respectata si la citire
        name = source.readString();
        age = source.readInt();
        enrollmentDate = new DateConverter().fromString(source.readString());
        studyType = FULL_TIME.name().equals(source.readString()) ? FULL_TIME : StudyType.DISTANCE;
        faculty = source.readString();
    }

    //Creatorul este public deoarece acesta trebuie sa fie invocat de pachetul
    // android care se afla in exteriorul clasei noastre
    //Este static deoarece dorim sa obtinem o instanta Java pe baza fisierului parcel,
    // ceea ce inseamna ca nu poate depinde de vreo instanta ci de clasa
    // (cititi ce inseamna o variabila static - POO si Java)
    public static Creator<StudentParcelable> CREATOR = new Creator<StudentParcelable>() {
        @Override
        public StudentParcelable createFromParcel(Parcel source) {
            return new StudentParcelable(source);
        }

        @Override
        public StudentParcelable[] newArray(int size) {
            return new StudentParcelable[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //ordinea de scriere in fisier trebuie respectata si la citire
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeString(new DateConverter().toString(enrollmentDate));
        dest.writeString(studyType.name());
        dest.writeString(faculty);
    }
}
