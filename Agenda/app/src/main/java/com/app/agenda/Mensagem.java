package com.app.agenda;

import android.app.Activity;

import androidx.appcompat.app.AlertDialog;

public class Mensagem {

    public static void show(String text, Activity act){
        AlertDialog.Builder alert = new AlertDialog.Builder(act);
        alert.setMessage(text);
        alert.setNeutralButton("OK", null);
        alert.show();
    }
}
