


package com.example.ana.borrowmebeta;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ImageView;

public class act_recuperar extends AppCompatActivity {
    ImageView regresar;
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

    }//oncreate
}
