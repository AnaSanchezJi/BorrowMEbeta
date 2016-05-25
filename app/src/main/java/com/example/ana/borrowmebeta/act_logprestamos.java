package com.example.ana.borrowmebeta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class act_logprestamos extends AppCompatActivity {
    ImageView regresarlogpres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_logprestamos);
        //Regresar Botton
        regresarlogpres = (ImageView) findViewById(R.id.imageViewana1);
        regresarlogpres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

    }//oncreate
}
