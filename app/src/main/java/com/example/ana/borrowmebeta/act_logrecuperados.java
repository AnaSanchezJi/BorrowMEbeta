
package com.example.ana.borrowmebeta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class act_logrecuperados extends AppCompatActivity {
    ImageView regresa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_logrecuperados);
        //Regresar Botton
        regresa= (ImageView) findViewById(R.id.imageViewlucero3);
        regresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }//oncreate
}
