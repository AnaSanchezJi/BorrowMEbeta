package com.example.ana.borrowmebeta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView prestar,recuperar,verPS,verRec;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //------------------------------
        prestar= (ImageView) findViewById(R.id.imageView1);
        recuperar=(ImageView) findViewById(R.id.imageView3);
        verPS=(ImageView) findViewById(R.id.imageView2);
        verRec=(ImageView) findViewById(R.id.imageView4);
        //--------------------------------

        prestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentapiVersion = android.os.Build.VERSION.SDK_INT;
                if (currentapiVersion >= android.os.Build.VERSION_CODES.LOLLIPOP){
                    startActivity(new Intent(MainActivity.this, act_prestamoapi21.class));
                } else{
                    startActivity(new Intent(MainActivity.this, act_prestamo.class));
                }



                // startActivity(new Intent(MainActivity.this, act_prestamo.class));
            }
        });
        //------------------------btnprestar
        recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , act_recuperar.class));
            }
        });
        //-------------------------btn recuperar

        verPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , act_logprestamos.class));
            }
        });
        //-------------------------- log prestados
        verRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , act_logrecuperados.class));
            }
        });
        //--------------------log recuperados

    }// cierra on create
}
