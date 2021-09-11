package com.example.entereventsproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.entereventsproject.models.Entrada;

/**
 * Created by tarda on 05/05/17.
 */

public class TicketAdapter extends BaseAdapter {
    private final Context mContext;
    private final Entrada[] tickets;

    public TicketAdapter(Context mContext, Entrada[] tickets) {
        this.mContext = mContext;
        this.tickets = tickets;
    }

    @Override
    public int getCount() {
        return tickets.length;
    }

    @Override
    public Entrada getItem(int position) {
        return tickets[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.entrada_item, parent, false);
        }

        Entrada item = getItem(position);

        TextView price = (TextView) convertView.findViewById(R.id.price);
        price.setText(item.getPrice());

        TextView fecha = (TextView) convertView.findViewById(R.id.fecha);
        fecha.setText(item.getDate());

        TextView  hora= (TextView) convertView.findViewById(R.id.time);
        hora.setText(item.getHora());

        TextView tickets = (TextView) convertView.findViewById(R.id.tickets);
        tickets.setText("80");

        return convertView;
    }
}
