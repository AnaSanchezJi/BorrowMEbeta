


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

public class act_recuperar extends AppCompatActivity {
    ImageView regresar,recu2;
    String[][] objre2;
    ListView lisobjre2;
    ConexionBD bdre2;
    int selre2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_recuperar);
        //Regresar Botton
        regresar= (ImageView) findViewById(R.id.imageViewlucero1);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recu2 = (ImageView) findViewById(R.id.imageViewlucero2);
        lisobjre2 = (ListView) findViewById(R.id.listViewlucero2);
        bdre2 = new ConexionBD(this, "basedatos", null, 1);
        selre2 = 0;
        objre2 = null;

        actualizarListView();
        lisobjre2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (selre2 == -1) {
                    return;
                }
                selre2 = position;
            }
        });

        //eliminar
        recu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (selre2 == -1) {
                        return;
                    }
                    SQLiteDatabase db = bdre2.getWritableDatabase();
                    String SQL = "UPDATE Prestamos SET ESTATUS=1 WHERE IDprestamo=" + objre2[selre2][0];
                    db.execSQL(SQL);
                    new AlertDialog.Builder(act_recuperar.this).setTitle("RECUPERACIÓN CORRECTA").setMessage("SE ACTUALIZO CORRECTAMENTE").show();
                    actualizarListView();
                } catch (SQLiteException e) {
                    new AlertDialog.Builder(act_recuperar.this).setTitle("ERROR").setMessage("NO SE PUDO RECUPERAR" + e.getMessage()).show();
                }
            }

        });

    }//oncreate
    public void actualizarListView(){
        try{
            SQLiteDatabase db = bdre2.getReadableDatabase();
            String sql = "SELECT * FROM Prestamos WHERE ESTATUS=0";
            Cursor c = db.rawQuery(sql,null);
            int i=0;
            if(c.moveToFirst()){
                objre2 = new String[c.getCount()][5];
                do{
                    objre2[i][0]=c.getString(0);
                    objre2[i][1]=c.getString(2);
                    objre2[i][2]=c.getString(3);
                    objre2[i][3]=c.getString(4);
                    objre2[i][4]=c.getString(5);
                    i++;
                }while(c.moveToNext());
            }
            else{
                objre2= new String[1][3];
                objre2[0][1]="No hay objetos para recuperar";
                objre2[0][0]="";
                objre2[0][2]="";
                selre2=-1;
            }
            db.close();
        }catch(SQLiteException sqle){
            new AlertDialog.Builder(this).setTitle("ERROR").setMessage("ERROR"+sqle.getMessage()).show();
        }
        String[] vector = new String[objre2.length];
        for(int i=0;i<vector.length;i++)
            vector[i]=objre2[i][1]+" a "+objre2[i][2];
        ArrayAdapter<String> adaptador=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,vector);
        lisobjre2.setAdapter(adaptador);
    }
}
