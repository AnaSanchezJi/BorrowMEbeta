package com.example.ana.borrowmebeta;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class act_prestamoapi21 extends AppCompatActivity {
    ImageView regresar,prestar;
    Spinner categorias;
    EditText objeto,prestatario,fepres,fedev;

    String  fechapres,fechadev;
    ConexionBD conexionBD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_prestamoapi21);
        categorias=(Spinner) findViewById(R.id.spinnerAl2);
        regresar= (ImageView) findViewById(R.id.imageViewAL3);
        prestar= (ImageView) findViewById(R.id.imageViewAL4);
        objeto= (EditText)  findViewById(R.id.editTextAl3);
        prestatario=  (EditText)  findViewById(R.id.editTextAL4);
        fepres=(EditText)findViewById(R.id.editTextDate1);
        fedev=(EditText)findViewById(R.id.editTextDate2);
        conexionBD=new ConexionBD(this,"basedatos",null,1);
        fechapres=fechaActual();
        fechadev=fechaActual();

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //--------------------regresar
        prestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String objeto1,prestatario1,fechaPRE,fechaDEV;
                objeto1=objeto.getText().toString();
                prestatario1=prestatario.getText().toString();
                fechaPRE=fepres.getText().toString();
                fechaDEV=fedev.getText().toString();
                try{
                    SQLiteDatabase db=conexionBD.getWritableDatabase();
                    String SQL="INSERT INTO Prestamos(Categoria,ObjetoPres,Prestatario,FechPrest,FechRec,Estatus) VALUES ('"+categorias.getSelectedItem().toString()+"','"+objeto1+"','"+prestatario1+"','"+fechapres+"','"+fechadev+"',0)";
                    //Categoria VARCHAR(50),ObjetoPres VARCHAR(100),Prestatario varchar(100),FechPrest DATE,FechRec DATE),Estatus INTEGER
                    db.execSQL(SQL);
                    new AlertDialog.Builder(act_prestamoapi21.this).setTitle("INSERCION CORRECTA").setMessage("SE REGISTRO CORRECTAMENTE").show();

                }
                catch(SQLiteException e){

                    new AlertDialog.Builder(act_prestamoapi21.this).setTitle("ERROR").setMessage("NO SE PUDO REGISTRAR"+e.getMessage()).show();

                }

            }
        });

    }//oncreate


    private String fechaActual(){
        String fechaActual="";
        Calendar c = GregorianCalendar.getInstance();
        fechaActual+=c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        if(month>=10){
            fechaActual+="/"+month;
        }else{
            fechaActual+="/0"+month;
        }
        if(dayOfMonth>=10){
            fechaActual+="/"+dayOfMonth;
        }else{
            fechaActual+="/0"+dayOfMonth;
        }
        fechaActual+=c.get(Calendar.MONTH);
        fechaActual+=c.get(Calendar.DAY_OF_MONTH);
        return fechaActual;
    }
}
