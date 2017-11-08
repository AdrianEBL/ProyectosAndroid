package com.example.administrador.appexau3p2;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class MainActivity extends AppCompatActivity {

    TextView txtvw;
    String cad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtvw = (TextView)findViewById(R.id.textView);

        leerDatos();
        actualizarDatos();
    }

    public void onResume() {
        super.onResume();
        leerDatos();
        actualizarDatos();
    }

    public Boolean hasExternalStorage() {
        String estado = Environment.getExternalStorageState();
        if (estado.equals(Environment.MEDIA_MOUNTED)){
            return true;
        } else {
            return false;
        }
    }

    public void leerDatos() {
        try {
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "dataShared.txt");
            if (hasExternalStorage() && file.exists()){
                ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));
                cad = (String) stream.readObject();
                stream.close();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Excepcion", Toast.LENGTH_SHORT).show();
        }
    }

    public void actualizarDatos(){
        /*String cadenaD = "";
        Toast.makeText(this, "Tamaño " + lista.size(), Toast.LENGTH_SHORT).show();
        for (Contacto c : lista){
            cadenaD = cadenaD + c.cantidad + c.nombre + c.proveedor + " ";
        }*/
        txtvw.setText(cad);
    }
}
