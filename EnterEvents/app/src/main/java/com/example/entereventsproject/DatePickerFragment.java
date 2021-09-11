package com.example.entereventsproject;

/**
 * Created by tarda on 09/05/17.
 */
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

import static com.example.entereventsproject.ListaEntradas.selected_day;
import static com.example.entereventsproject.TabFragment.tad;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        selected_day=view.getDayOfMonth()+"-"+(view.getMonth()+1)+"-"+view.getYear();
        tad.notifyDataSetChanged();
    }
}

