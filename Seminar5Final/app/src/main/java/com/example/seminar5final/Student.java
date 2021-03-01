package com.example.seminar5final;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {
    private String nume;
    private String prenume;
    private String email;
    private String facultate;
    private String formInv;

    protected Student(Parcel in) {
        nume = in.readString();
        prenume = in.readString();
        email = in.readString();
        facultate = in.readString();
        formInv = in.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public Student() {

    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacultate() {
        return facultate;
    }

    public void setFacultate(String facultate) {
        this.facultate = facultate;
    }

    public String getFormInv() {
        return formInv;
    }

    public void setFormInv(String formInv) {
        this.formInv = formInv;
    }

    @Override
    public String toString() {
        return "Student{" +
                "nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", email='" + email + '\'' +
                ", facultate='" + facultate + '\'' +
                ", formInv='" + formInv + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nume);
        dest.writeString(prenume);
        dest.writeString(email);
        dest.writeString(facultate);
        dest.writeString(formInv);
    }
}
