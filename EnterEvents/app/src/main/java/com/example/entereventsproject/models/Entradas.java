package com.example.entereventsproject.models;

import com.google.firebase.auth.FirebaseAuth;

import static com.example.entereventsproject.ListaEntradas.selected_day;

/**
 * Created by tarda on 08/05/17.
 */

public class Entradas {
private static Entrada[] entradas = {
        new Entrada("8€", FirebaseAuth.getInstance().getCurrentUser().getUid(), selected_day, "10-11"),
        new Entrada("8€", FirebaseAuth.getInstance().getCurrentUser().getUid(), selected_day, "11-12"),
        new Entrada("8€", FirebaseAuth.getInstance().getCurrentUser().getUid(), selected_day, "12-13"),
        new Entrada("8€", FirebaseAuth.getInstance().getCurrentUser().getUid(), selected_day, "13-14"),
        new Entrada("8€", FirebaseAuth.getInstance().getCurrentUser().getUid(), selected_day, "16-17"),
        new Entrada("8€", FirebaseAuth.getInstance().getCurrentUser().getUid(), selected_day, "17-18"),
        new Entrada("8€", FirebaseAuth.getInstance().getCurrentUser().getUid(), selected_day, "18-19"),
        new Entrada("8€", FirebaseAuth.getInstance().getCurrentUser().getUid(), selected_day, "19-20"),
        new Entrada("8€", FirebaseAuth.getInstance().getCurrentUser().getUid(), selected_day, "20-21")
    };
    public static Entrada[] getEntradas() {
        return entradas;
    }

}
