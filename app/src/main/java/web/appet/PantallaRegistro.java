package web.appet;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.SQLData;

public class PantallaRegistro extends AppCompatActivity {
    public EditText etCorreo, etNombre, etContraseña, etContraseña2, correoInicioSesion, correoIS;
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

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();



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
              else if (contraseña2.length() != 0 && contraseña!=contraseña2){
                Toast.makeText(this, "Las contraseñas deben coincidir", Toast.LENGTH_SHORT).show();
            }
             if (nombre.length() != 0 && contraseña.length() != 0 && contraseña.equals(contraseña2) && (correo.length() != 0)) {
                Toast.makeText(this, "Se ha registrado", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, InicioSesion.class);

                 ContentValues registro = new ContentValues();

                 registro.put("correo", correo);
                 registro.put("nombre", nombre);
                 registro.put("contraseña", contraseña2);

                 BaseDeDatos.insert("registroUsuarios", null, registro);

                 BaseDeDatos.close();
                 etNombre.setText("");
                 etCorreo.setText("");
                 etContraseña.setText("");
                 etContraseña2.setText("");

                startActivity(i);






        }
    }else{
        Toast.makeText(this, "Debes aceptar términos y condiciones", Toast.LENGTH_SHORT).show();}

}}
