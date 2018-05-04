package web.appet;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
public class InicioSesion extends AppCompatActivity {
    private EditText etCorreoInicioSesion, etContraseñaInicioSesion;
    private CheckBox checkBoxInicioSesion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        etCorreoInicioSesion = (EditText) findViewById(R.id.etCorreoInicioSesion);
        etContraseñaInicioSesion = (EditText) findViewById(R.id.etContraseñaInicioSesion);
        checkBoxInicioSesion = (CheckBox) findViewById(R.id.checkBoxInicioSesion);
        SharedPreferences verificarPreferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        etContraseñaInicioSesion.setText(verificarPreferences.getString("contraseña", ""));
        etCorreoInicioSesion.setText(verificarPreferences.getString("correo", ""));
    }

    public void Registrarse(View view) {
        Intent i = new Intent(this, PantallaRegistro.class);
        startActivity(i);
        finish();
    }

    public void IniciarSesion(View view) {
        if (checkBoxInicioSesion.isChecked() == true) {
            SharedPreferences crearPreferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
            SharedPreferences.Editor editarPreferences = crearPreferences.edit();
            editarPreferences.putString("correo", etCorreoInicioSesion.getText().toString());
            editarPreferences.putString("contraseña", etContraseñaInicioSesion.getText().toString());
            editarPreferences.commit();
            finish();
        }
    }
}
