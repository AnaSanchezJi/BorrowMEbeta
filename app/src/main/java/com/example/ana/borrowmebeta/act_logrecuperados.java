
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

public class act_logrecuperados extends AppCompatActivity {
    ImageView regresa, elirecu;
    String[][] objre;
    ListView lisobjre;
    ConexionBD bdre;
    int selre;
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

        elirecu = (ImageView) findViewById(R.id.imageViewlucero4);
        lisobjre = (ListView) findViewById(R.id.listViewlucero1);
        bdre = new ConexionBD(this, "basedatos", null, 1);
        selre = 0;
        objre = null;

        //list
        actualizarListView();
        lisobjre.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (selre == -1) {
                    return;
                }
                selre = position;
            }
        });

        //eliminar
        elirecu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (selre == -1) {
                        return;
                    }
                    SQLiteDatabase db = bdre.getWritableDatabase();
                    String SQL = "UPDATE Prestamos SET ESTATUS=2 WHERE IDprestamo=" + objre[selre][0];
                    //Categoria VARCHAR(50), ObjPres VARCHAR(100), Prestario VARCHAR(100),FechPrest DATE, FechRec DATE), Estatus INTEGER
                    db.execSQL(SQL);
                    new AlertDialog.Builder(act_logrecuperados.this).setTitle("ELIMINACIÓN CORRECTA").setMessage("SE ACTUALIZO CORRECTAMENTE").show();
                    actualizarListView();
                } catch (SQLiteException e) {
                    new AlertDialog.Builder(act_logrecuperados.this).setTitle("ERROR").setMessage("NO SE PUDO RECUPERAR" + e.getMessage()).show();
                }
            }

        });

    }//oncreate

    public void actualizarListView(){
        try{
            SQLiteDatabase db = bdre.getReadableDatabase();
            String sql = "SELECT * FROM Prestamos WHERE ESTATUS=1";
            Cursor c = db.rawQuery(sql,null);
            int i=0;
            if(c.moveToFirst()){
                objre = new String[c.getCount()][5];
                do{
                    objre[i][0]=c.getString(0);
                    objre[i][1]=c.getString(2);
                    objre[i][2]=c.getString(3);
                    objre[i][3]=c.getString(4);
                    objre[i][4]=c.getString(5);
                    i++;
                }while(c.moveToNext());
            }
            else{
                objre= new String[1][3];
                objre[0][1]="No hay objetos prestados";
                objre[0][0]="";
                objre[0][2]="";
                selre=-1;
            }
            db.close();
        }catch(SQLiteException sqle){
            new AlertDialog.Builder(this).setTitle("ERROR").setMessage("ERROR"+sqle.getMessage()).show();
        }
        String[] vector = new String[objre.length];
        for(int i=0;i<vector.length;i++)
            vector[i]=objre[i][1]+" a "+objre[i][2];
        ArrayAdapter<String> adaptador=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,vector);
        lisobjre.setAdapter(adaptador);
    }
}
