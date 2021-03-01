package com.example.practice_1.asyncTask;

public interface Callback<R> {
    void runResultOnUiTread(R result);
}
