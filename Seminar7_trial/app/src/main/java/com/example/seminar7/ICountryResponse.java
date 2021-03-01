package com.example.seminar7;

import java.util.ArrayList;

public interface ICountryResponse {

    void onSuccess(ArrayList<Country> countries);
    void onFailure(Throwable error);
}
