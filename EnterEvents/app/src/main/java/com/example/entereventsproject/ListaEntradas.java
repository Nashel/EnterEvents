package com.example.entereventsproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

public class ListaEntradas extends GoogleApiActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ViewPager mViewPager;
    public static String selected_day;
    //TODO mirar ejecucion doble

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateAct();
        setContentView(R.layout.activity_lista_entradas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // Setear adaptador al viewpager.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        setupViewPager(mViewPager);

        // Preparar las pestañas
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);

        tabs.setupWithViewPager(mViewPager);

        if (selected_day==null) {
            Calendar now=Calendar.getInstance();
            selected_day=now.get(Calendar.DAY_OF_MONTH)+"-"+(now.get(Calendar.MONTH)+1)+"-"+now.get(Calendar.YEAR);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.show();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnCalendar();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TextView mail = (TextView) navigationView.getHeaderView(0).findViewById(R.id.mail);
        mail.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());

        TextView name = (TextView) navigationView.getHeaderView(0).findViewById(R.id.name);
        name.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());

        ImageView photo = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.user);
        Uri photouser=FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl();
        Picasso.with(this).load(photouser).into(photo);
    }

    public void logout() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                FirebaseAuth.getInstance().signOut();
                updateAct();
            }
        });
    }

    void reservar(){
        int cantidad = 1;
        final int cant = cantidad;
        /*
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("sessions/");
        DatabaseReference upvotesRef = ref.child("server/saving-data/fireblog/posts/-JRHTHaIs-jNPLXOQivY/upvotes");
        upvotesRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                Integer currentValue = mutableData.getValue(Integer.class);
                if (currentValue == null) {
                    mutableData.setValue(1);
                } else {
                    mutableData.setValue(currentValue + 1);
                }

                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean committed, DataSnapshot dataSnapshot) {
                System.out.println("Transaction completed");
            }
        });
        */
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lista_entradas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
        } else if (id == R.id.log_out) {
            logout();
        } else if (id == R.id.nav_manage) {
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void updateAct() {
        if (FirebaseAuth.getInstance().getCurrentUser()==null) {
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(TabFragment.newInstance(1), "Entradas");
        adapter.addFragment(TabFragment.newInstance(2), "Favoritos");
        adapter.addFragment(TabFragment.newInstance(3), "Información");
        viewPager.setAdapter(adapter);
    }

    public void prueba() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public void OnCalendar(){
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}
