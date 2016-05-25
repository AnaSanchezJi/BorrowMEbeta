package com.example.ana.borrowmebeta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class act_prestamo extends AppCompatActivity {

    ImageView regresar,prestar;
    Spinner categorias;
    EditText objeto,prestatario;
    DatePicker fepres,fedev;
    String  fechapres,fechadev;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_prestamo);

        //----------------------------------------declaracion
        regresar=(ImageView)findViewById(R.id.imageViewAL1);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //----------------------regresar
        prestar= (ImageView) findViewById(R.id.imageViewAL2);
        prestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //-----------------prestar
    }
}
