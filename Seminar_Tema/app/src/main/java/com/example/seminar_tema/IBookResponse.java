package com.example.seminar_tema;

import java.util.ArrayList;

public interface IBookResponse {
    void onSuccess(ArrayList<Book> books);
    void onFailure(Throwable error);
}
