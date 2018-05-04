package web.appet;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class PantallaRegistro extends AppCompatActivity {
    private EditText etCorreo, etNombre, etContraseña, etContraseña2, correoInicioSesion;
    private RadioButton rbInicioSesion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_registro);
        etCorreo = (EditText)findViewById(R.id.correoRegistro);
        correoInicioSesion =(EditText)findViewById(R.id.etCorreoInicioSesion);
        etNombre = (EditText)findViewById(R.id.nombreRegistro);
        etContraseña= (EditText)findViewById(R.id.contraseñaRegistro);
        etContraseña2 = (EditText)findViewById(R.id.contraseñaRegistro2);
        rbInicioSesion = (RadioButton)findViewById(R.id.radioButton);
        String archivos[] = fileList();
        if (ArchivoExiste(archivos, "datos.txt")){
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("datos.txt"));
                BufferedReader leerArchivo = new BufferedReader(archivo);
                String guardar = leerArchivo.readLine();
                String guardarTodoTexto = "";

                while(guardar!=null){
                    guardarTodoTexto= guardarTodoTexto + guardar + "/n";
                    guardar=leerArchivo.readLine();
                }
                leerArchivo.close();
                archivo.close();


            }catch (IOException e){}
        }



    }private boolean ArchivoExiste(String archivos[], String NombreArchivo){
     for (int i=0; i<archivos.length; i++)
         if(NombreArchivo.equals(archivos[i]))
             return true;
     return false;
    }



    public void Registrar (View view) {
        String correo = etCorreo.getText().toString();
        String nombre = etNombre.getText().toString();
        String contraseña = etContraseña.getText().toString();
        String contraseña2 = etContraseña2.getText().toString();


        if (rbInicioSesion.isChecked() == true) {
            if (correo.length() == 0) {
                Toast.makeText(this, "Debes ingresar un correo", Toast.LENGTH_SHORT).show();
            } else if (nombre.length() == 0) {
                Toast.makeText(this, "Debes ingresar un nombre", Toast.LENGTH_SHORT).show();
            } else if (contraseña.length() == 0) {
                Toast.makeText(this, "Ingresa una contraseña", Toast.LENGTH_SHORT).show();
            } else if (contraseña2.length() == 0 && contraseña.length() != 0) {
                Toast.makeText(this, "Confirma tu contraseña", Toast.LENGTH_SHORT).show();
            }
            if (contraseña.length() != 0 && contraseña2.length() != 0 && contraseña != contraseña2) {
                Toast.makeText(this, "Las contraseñas deben coincidir", Toast.LENGTH_SHORT).show();
            }
            if (nombre.length() != 0 && contraseña.length() != 0 && contraseña.equals(contraseña2) && (correo.length() != 0)) {
                Toast.makeText(this, "Se ha registrado", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, InicioSesion.class);
                startActivity(i);
                try {
                    OutputStreamWriter enviarArchivo = new OutputStreamWriter(openFileOutput("datos.txt", Activity.MODE_PRIVATE));
                    enviarArchivo.write(etCorreo.getText().toString());
                    enviarArchivo.flush();
                    enviarArchivo.close();
                }catch (IOException e){}
                finish();
            }
        } else if (rbInicioSesion.isChecked() == false) {
            Toast.makeText(this, "Debes aceptar términos y condiciones", Toast.LENGTH_SHORT).show();
        }
    }
    public void TerminosCondiciones(View view){
        Intent i = new Intent(this, Menu.class);
        startActivity(i);finish();
    }
}
