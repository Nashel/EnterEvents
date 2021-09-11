package com.example.entereventsproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.entereventsproject.models.Entrada;
import com.example.entereventsproject.models.Entradas;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;


/**
 * Created by tarda on 05/05/17.
 */

public class TabFragment extends Fragment  {

    private static final String ARG_SECTION_NUMBER = "section_number";
    static TicketAdapter tad;


    public static TabFragment newInstance(int sectionNumber) {
        TabFragment fragment = new TabFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public TabFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FloatingActionButton  fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        int section_number = getArguments().getInt(ARG_SECTION_NUMBER);
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ListView list = (ListView) rootView.findViewById(R.id.list);

        switch (section_number) {
            case 1:
                // Obtención del grid view
                tad=new TicketAdapter(getActivity(), Entradas.getEntradas());
                list.setAdapter(tad);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3)
                    {
                        Entrada ent = (Entrada)adapter.getItemAtPosition(position);
                        Intent payp = new Intent(v.getContext(), PaypalActivity.class);
                        payp.putExtra("fecha", (Entrada) adapter.getItemAtPosition(position));
                        startActivity(payp);

                    }
                });
                break;
            case 2:
                //list.setAdapter(new TicketAdapter(getActivity(), Entradas.getEntradas()));
                // Obtención del grid view

                break;
            case 3:
                //Información
                rootView=inflater.inflate(R.layout.information, container, false);

                break;
        }
            return rootView;
    }

    public void compra_entrada(Entrada ent) {
        int n=(int) (Math.random() * 1000 * 1000 * 1000) + 1;

        FirebaseDatabase.getInstance().getReference("tickets")
                .child(String.valueOf(n))
                .setValue(ent);
    }
    public void query() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query query = reference.child("tickets").orderByChild("date").equalTo("");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
