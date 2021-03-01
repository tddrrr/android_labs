package eu.ase.ro.seminar6.asyncTask;

public interface Callback<R> {
    void runResultOnUiTread(R result);
}
