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
    ImageView regresarlogpres, elilogpres;
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
        elilogpres = (ImageView) findViewById(R.id.imageViewana2);
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
        elilogpres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (sel == -1) {
                        return;
                    }
                    SQLiteDatabase db = bd.getWritableDatabase();
                    String SQL = "UPDATE Prestamos SET ESTATUS=2 WHERE IDprestamo=" + obj[sel][0];
                    //Categoria VARCHAR(50), ObjPres VARCHAR(100), Prestario VARCHAR(100),FechPrest DATE, FechRec DATE), Estatus INTEGER
                    db.execSQL(SQL);
                    new AlertDialog.Builder(act_logprestamos.this).setTitle("ELIMINACIÓN CORRECTA").setMessage("SE ACTUALIZO CORRECTAMENTE").show();
                    actualizarListView();
                } catch (SQLiteException e) {
                    new AlertDialog.Builder(act_logprestamos.this).setTitle("ERROR").setMessage("NO SE PUDO RECUPERAR" + e.getMessage()).show();
                }
            }

        });
    }
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

    }//oncreate

/*package com.example.apc.borrowme;



public class ACT_logrecuperados extends AppCompatActivity {
    ImageView regresar,eliminar;
    String[][] objetos;
    ListView lobjetos;
    ConexionBD bd;
    int seleccionado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_logrecuperados);
        regresar= (ImageView) findViewById(R.id.imageView9);
        eliminar=(ImageView) findViewById(R.id.imageView11);
        lobjetos= (ListView) findViewById(R.id.listView3);
        bd = new ConexionBD(this,"basedatos",null,1);
        seleccionado=0;

        regresar.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { finish();} });//regresar
        objetos=null;
        actualizarListView();
        lobjetos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(seleccionado==-1){return;}
                seleccionado=position;
            }
        });

      eliminar.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              try{
                  if(seleccionado==-1){return;}

                  SQLiteDatabase db=bd.getWritableDatabase();
                  String SQL="UPDATE Prestamos SET Estatus=2 WHERE IDprestamo="+objetos[seleccionado][0];
                  //Categoria VARCHAR(50),ObjetoPres VARCHAR(100),Prestatario varchar(100),FechPrest DATE,FechRec DATE),Estatus INTEGER
                  db.execSQL(SQL);
                  new AlertDialog.Builder(ACT_logrecuperados.this).setTitle("ELIMINACION CORRECTA").setMessage("SE ACTUALIZO CORRECTAMENTE").show();
                  actualizarListView();

              }
              catch(SQLiteException e){

                  new AlertDialog.Builder(ACT_logrecuperados.this).setTitle("ERROR").setMessage("NO SE PUDO RECUPERAR"+e.getMessage()).show();

              }
          }
      });

    }
    public void actualizarListView () {

        try {
            SQLiteDatabase db = bd.getReadableDatabase();
            String sql = "SELECT * FROM Prestamos WHERE ESTATUS=1";
            Cursor c = db.rawQuery(sql, null);

            int i = 0;
            if (c.moveToFirst()) {
                objetos = new String[c.getCount()][5];

                do {
                    objetos[i][0] = c.getString(0);
                    objetos[i][1] = c.getString(2);
                    objetos[i][2] = c.getString(3);
                    objetos[i][3] = c.getString(4);
                    objetos[i][4] = c.getString(5);
                    i++;
                } while (c.moveToNext());

            }
            else {
                objetos = new String[1][3];
                objetos[0][1]="no hay objetos prestados";
                objetos[0][0]="";
                objetos[0][2]="";
                seleccionado=-1;
            }
            db.close();

        } catch (SQLiteException sqle) {
            new AlertDialog.Builder(this).setTitle("Error").setMessage("Error: " + sqle.getMessage()).show();
        }

        String[] vector = new String[objetos.length];
        for (int i = 0; i < vector.length; i++)
            vector[i] = objetos[i][1] + " a " + objetos[i][2];
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, vector);
        lobjetos.setAdapter(adaptador);

    }
}
*
*
*
* */