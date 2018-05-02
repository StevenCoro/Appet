package web.appet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PantallaRegistro extends AppCompatActivity {
    private EditText etCorreo, etNombre, etContraseña, etContraseña2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_registro);
        etCorreo = (EditText)findViewById(R.id.correoRegistro);
        etNombre = (EditText)findViewById(R.id.nombreRegistro);
        etContraseña= (EditText)findViewById(R.id.contraseñaRegistro);
        etContraseña2 = (EditText)findViewById(R.id.contraseñaRegistro2);




    }

    public void Registrar (View view) {
        String correo = etCorreo.getText().toString();
        String nombre = etNombre.getText().toString();
        String contraseña = etContraseña.getText().toString();
        String contraseña2 = etContraseña2.getText().toString();

        if (correo.length()==0){
            Toast.makeText(this, "Debes ingresar un correo", Toast.LENGTH_LONG);
        }

        if (nombre.length() == 0) {
            Toast.makeText(this, "Debes ingresar un nombre", Toast.LENGTH_LONG).show();
        }
        if (contraseña.length() == 0) {
            Toast.makeText(this, "Ingresa una contraseña", Toast.LENGTH_LONG).show();
        }
        if (contraseña2.length()==0){Toast.makeText(this, "Confirma tu contraseña", Toast.LENGTH_LONG);}
        if (nombre.length() != 0 && contraseña.length() != 0) {
            if(contraseña.equals(contraseña2)){
                Toast.makeText(this, "Se ha registrado", Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, Menu.class);startActivity(i);finish();}
            else if (contraseña!=contraseña2){
                Toast.makeText(this, "Las contraseñas deben coincidir", Toast.LENGTH_LONG);}




        }
    }
}
