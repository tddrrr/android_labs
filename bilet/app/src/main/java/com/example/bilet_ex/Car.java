package com.example.bilet_ex;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Car implements Parcelable {
    private String marca;
    private String model;
    private String carburant;
    private long dataInchiriere;
    private int nrZile;

    public Car(String marca, String model, String carburant, long dataInchiriere, int nrZile) {
        this.marca = marca;
        this.model = model;
        this.carburant = carburant;
        this.dataInchiriere = dataInchiriere;
        this.nrZile = nrZile;
    }

    protected Car(Parcel in) {
        marca = in.readString();
        model = in.readString();
        carburant = in.readString();
        nrZile = in.readInt();
    }

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };

    @Override
    public String toString() {
        return "Car{" +
                "marca='" + marca + '\'' +
                ", model='" + model + '\'' +
                ", carburant='" + carburant + '\'' +
                ", dataInchiriere=" + dataInchiriere +
                ", nrZile=" + nrZile +
                '}';
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCarburant() {
        return carburant;
    }

    public void setCarburant(String carburant) {
        this.carburant = carburant;
    }

    public long getDataInchiriere() {
        return dataInchiriere;
    }

    public void setDataInchiriere(long dataInchiriere) {
        this.dataInchiriere = dataInchiriere;
    }

    public int getNrZile() {
        return nrZile;
    }

    public void setNrZile(int nrZile) {
        this.nrZile = nrZile;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(marca);
        dest.writeString(model);
        dest.writeString(carburant);
        dest.writeInt(nrZile);
    }
}
