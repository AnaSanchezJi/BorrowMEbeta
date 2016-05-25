package com.example.ana.borrowmebeta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView prestar,recuperar,verPS,verRec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //------------------------------
        prestar= (ImageView) findViewById(R.id.imageView);
        recuperar=(ImageView) findViewById(R.id.imageView3);
        verPS=(ImageView) findViewById(R.id.imageView2);
        verRec=(ImageView) findViewById(R.id.imageView4);
        //--------------------------------

        prestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //------------------------btnprestar
        recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //-------------------------btn recuperar

        verPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //-------------------------- log prestados
        verRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //--------------------log recuperados

    }// cierra on create
}
