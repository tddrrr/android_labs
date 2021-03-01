package com.example.practice_1;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Database;

import java.util.List;

public class RezervariService { //operatiile pe BD trebuie realizate pe fire paralele;
    private static RezervariDAO rezervareDAO;

    public static class Insert extends AsyncTask<Rezervare, Void, Rezervare> {
        public Insert(Context context) { //il iau din DatabaseManager prin getInstance
            rezervareDAO = DatabaseManager.getInstance(context).getRezervariDAO(); //am initializat un manipulator de datd
        }

        @Override
        protected Rezervare doInBackground(Rezervare... rezervares) {
            if (rezervares != null && rezervares.length != 1) {
                return null;
            }
            Rezervare rezervareNoua = rezervares[0];
            long id= rezervareDAO.inserareRezervare(rezervareNoua);
            if (id != -1) {
                return rezervareNoua;
            }
            return null;
        }
    }
    public List<Rezervare> getAllRezervari() {
        return rezervareDAO.getAll();
    }
}
