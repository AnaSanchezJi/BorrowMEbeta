package com.example.ana.borrowmebeta;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class act_prestamo extends AppCompatActivity {

    ImageView regresar,prestar;
    Spinner categorias;
    EditText objeto,prestatario;
    DatePicker fepres,fedev;
    String  fechapres,fechadev;

    ConexionBD conexionBD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_prestamo);
        categorias=(Spinner) findViewById(R.id.spinnerAl1);
        regresar= (ImageView) findViewById(R.id.imageViewAL1);
        prestar= (ImageView) findViewById(R.id.imageViewAL2);
        objeto=  (EditText)  findViewById(R.id.editTextAl1);
        prestatario=  (EditText)  findViewById(R.id.editTextAL2);
        fepres=  (DatePicker)  findViewById(R.id.datePickerAL1);
        fedev=  (DatePicker)  findViewById(R.id.datePickerAl2);
        conexionBD=new ConexionBD(this,"basedatos",null,1);
        fechapres=fechaActual();
        fechadev=fechaActual();

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
            //------------regresar


        });
        prestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String objeto1,prestatario1;
                objeto1=objeto.getText().toString();
                prestatario1=prestatario.getText().toString();

                try{
                    SQLiteDatabase db=conexionBD.getWritableDatabase();
                    String SQL="INSERT INTO Prestamos(Categoria,ObjetoPres,Prestatario,FechPrest,FechRec,Estatus) VALUES ('"+categorias.getSelectedItem().toString()+"','"+objeto1+"','"+prestatario1+"','"+fechapres+"','"+fechadev+"',0)";
                    //Categoria VARCHAR(50),ObjetoPres VARCHAR(100),Prestatario varchar(100),FechPrest DATE,FechRec DATE),Estatus INTEGER
                    db.execSQL(SQL);
                    new AlertDialog.Builder(act_prestamo.this).setTitle("INSERCION CORRECTA").setMessage("SE REGISTRO CORRECTAMENTE").show();

                }
                catch(SQLiteException e){

                    new AlertDialog.Builder(act_prestamo.this).setTitle("ERROR").setMessage("NO SE PUDO REGISTRAR"+e.getMessage()).show();

                }

            }
        });

        fepres.getCalendarView().setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                fechapres=""+year;
                month++;
                if(month>=10){
                    fechapres+="/"+month;
                }else{
                    fechapres+="/0"+month;
                }
                if(dayOfMonth>=10){
                    fechapres+="/"+dayOfMonth;
                }else{
                    fechapres+="/0"+dayOfMonth;
                }

            }


        });


        fedev.getCalendarView().setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                fechadev=""+year;
                month++;
                if(month>=10){
                    fechadev+="/"+month;
                }else{
                    fechadev+="/0"+month;
                }
                if(dayOfMonth>=10){
                    fechadev+="/"+dayOfMonth;
                }else{
                    fechadev+="/0"+dayOfMonth;
                }

            }


        });
    }






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
