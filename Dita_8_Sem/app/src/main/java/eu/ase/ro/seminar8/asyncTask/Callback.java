package eu.ase.ro.seminar8.asyncTask;

public interface Callback<R> {

    void runResultOnUiThread(R result);
}
