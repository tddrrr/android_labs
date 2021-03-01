package com.example.practice_1.asyncTask;


import android.os.Handler;
import android.util.Log;

import java.util.concurrent.Callable;

public class RunnableTask<R> implements Runnable{//tot template R
    // inst callable care la executie poate fi o clasa in java care implementeaza Callable;
    // pe metoda call face ceva
    // referinta catre bucata de cod asincrona; callable se proceseaza, imi da rezultatul, se trimite la handler message
    private final Handler handler; //handler de android os
    private final Callable<R> asyncOperation;
    private final Callback<R> mainThreadOperation;

    public RunnableTask(Handler handler, Callable<R> asyncOperation, Callback<R> mainThreadOperation) {
        this.handler = handler;
        this.asyncOperation = asyncOperation;
        this.mainThreadOperation = mainThreadOperation;
    }

    @Override
    public void run() { //apelam metoda async operation si luam rezultatul
        try { //Obiectivul lui Runnable Task: primeste handler, referinta catre metoda paralela, sa primesc referinta catre zona dina ctivitate
            //unde sa pun rezultatul primit dupa apel
            final R result = asyncOperation.call();
            handler.post(new HandlerMessage<>(result, mainThreadOperation));
        } catch (Exception e) {
            Log.i("RunnableTask", "failed call runnableTask"+e.getMessage());
        }
    }

}
