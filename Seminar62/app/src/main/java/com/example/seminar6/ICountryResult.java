package com.example.seminar6;

public interface ICountryResult {
    void onSuccess(String result);
    void onFailure(Throwable throwable);
}
