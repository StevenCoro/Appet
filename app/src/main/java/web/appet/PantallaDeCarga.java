package web.appet;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
public class PantallaDeCarga extends AppCompatActivity{

    private static int pantallaEspera = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_de_carga);
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent siguientePantalla = new Intent(PantallaDeCarga.this, InicioSesion.class);
                startActivity(siguientePantalla);
                finish();
            }
        }, pantallaEspera);



    }


    }







