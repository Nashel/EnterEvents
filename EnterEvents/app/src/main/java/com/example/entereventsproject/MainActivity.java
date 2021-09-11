package com.example.entereventsproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

/**
 * Created by tarda on 04/05/17.
 */

public class MainActivity extends AppCompatActivity implements
            View.OnClickListener {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sign_in);


            findViewById(R.id.sign_in_button).setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.sign_in_button:
                    startActivity(new Intent(this, SignInGoogleActivity.class));
                    break;
            }

        }

}
