package com.example.ana.borrowmebeta;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class act_logprestamos extends AppCompatActivity {
    ImageView regresarlogpres;
    String[][] obj;
    ListView lisobj;
    ConexionBD bd;
    int sel;
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
        //fin Regresar botton


        lisobj = (ListView) findViewById(R.id.listViewana1);
        bd = new ConexionBD(this, "basedatos", null, 1);
        sel = 0;
        obj = null;

        //list
        actualizarListView();
        lisobj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (sel == -1) {
                    return;
                }
                sel = position;
            }
        });
        //fin list


    }//finOncreate
       //metodo actualizar list
        public void actualizarListView(){
            try{
                SQLiteDatabase db = bd.getReadableDatabase();
                String sql = "SELECT * FROM Prestamos WHERE ESTATUS=0";
                Cursor c = db.rawQuery(sql,null);
                int i=0;
                if(c.moveToFirst()){
                    obj = new String[c.getCount()][5];
                    do{
                        obj[i][0]=c.getString(0);
                        obj[i][1]=c.getString(2);
                        obj[i][2]=c.getString(3);
                        obj[i][3]=c.getString(4);
                        obj[i][4]=c.getString(5);
                        i++;
                    }while(c.moveToNext());
                }
                else{
                    obj= new String[1][3];
                    obj[0][1]="No hay objetos prestados";
                    obj[0][0]="";
                    obj[0][2]="";
                    sel=-1;
                }
                db.close();
            }catch(SQLiteException sqle){
                new AlertDialog.Builder(this).setTitle("ERROR").setMessage("ERROR"+sqle.getMessage()).show();
            }
            String[] vector = new String[obj.length];
            for(int i=0;i<vector.length;i++)
                vector[i]=obj[i][1]+" a "+obj[i][2];
            ArrayAdapter<String>adaptador=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,vector);
            lisobj.setAdapter(adaptador);
        }

    }

