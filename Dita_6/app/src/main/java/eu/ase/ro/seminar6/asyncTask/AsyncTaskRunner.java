package eu.ase.ro.seminar6.asyncTask;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AsyncTaskRunner { //executor e necesar sa ne rulam threadurile
    private final Executor executor = Executors.newCachedThreadPool(); //newCachedThreadPool vede resursele si determina cate threaduri putem folosi noi in paralel
    private final Handler handler = new Handler(Looper.getMainLooper());

    public <R> void executeAsync(Callable<R> asyncOperation, Callback<R> mainThreadOperation) {
        try {
            executor.execute(new RunnableTask<>(handler, asyncOperation, mainThreadOperation));
        } catch (Exception ex) {
            Log.i("AsyncTaskRunner", "failed call execute async "+ex.getMessage());
        }
    }
}
