package com.example.entereventsproject.models;

import java.io.Serializable;
import java.util.StringTokenizer;

import static com.example.entereventsproject.ListaEntradas.selected_day;

/**
 * Created by tarda on 05/05/17.
 */

public class Entrada implements Serializable {
    private String price;
    private String user;
    private String date;
    private String hora;

    public Entrada(String price, String user, String date, String hora) {
        this.price = price;
        this.user=user;
        this.date = date;
        this.hora=hora;
    }

    public Entrada() {

    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        setDate();
        return date;
    }

    public void setDate() {
        date=selected_day;
    }
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
