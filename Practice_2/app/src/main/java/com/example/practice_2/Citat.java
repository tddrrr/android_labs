package com.example.practice_2;

import android.os.Parcel;
import android.os.Parcelable;

public class Citat implements Parcelable {
    private String autor;
    private String text;
    private int nrAprecieri;
    private String categorie;

    public Citat(String autor, String text, int nrAprecieri, String categorie) {
        this.autor = autor;
        this.text = text;
        this.nrAprecieri = nrAprecieri;
        this.categorie = categorie;
    }

    protected Citat(Parcel in) {
        autor = in.readString();
        text = in.readString();
        nrAprecieri = in.readInt();
        categorie = in.readString();
    }

    public static final Creator<Citat> CREATOR = new Creator<Citat>() {
        @Override
        public Citat createFromParcel(Parcel in) {
            return new Citat(in);
        }

        @Override
        public Citat[] newArray(int size) {
            return new Citat[size];
        }
    };

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNrAprecieri() {
        return nrAprecieri;
    }

    public void setNrAprecieri(int nrAprecieri) {
        this.nrAprecieri = nrAprecieri;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Citat{" +
                "autor='" + autor + '\'' +
                ", text='" + text + '\'' +
                ", nrAprecieri=" + nrAprecieri +
                ", categorie='" + categorie + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(autor);
        dest.writeString(text);
        dest.writeInt(nrAprecieri);
        dest.writeString(categorie);
    }
}
