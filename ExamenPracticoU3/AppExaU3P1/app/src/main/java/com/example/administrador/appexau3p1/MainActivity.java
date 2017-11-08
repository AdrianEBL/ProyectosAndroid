package com.example.administrador.appexau3p1;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtxt1, edtxt2, edtxt3, edtxt4, edtxt5;
    Button btn1;
    ArrayList<Contacto> lista = new ArrayList<>();
    String cad = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtxt1 = (EditText)findViewById(R.id.editText);
        edtxt2 = (EditText)findViewById(R.id.editText2);
        edtxt3 = (EditText)findViewById(R.id.editText3);
        edtxt4 = (EditText)findViewById(R.id.editText4);
        edtxt5 = (EditText)findViewById(R.id.editText5);
        btn1 = (Button)findViewById(R.id.button);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarDatos();
            }
        });
    }

    public void guardarDatos() {
        leerDatos();
        cad = cad + "Cantidad: " + edtxt1.getText().toString() + " Nombre: " + edtxt2.getText().toString()
                + " Proveedor: " + edtxt3.getText().toString() + " Fecha: " + edtxt4.getText().toString()
                + " Descripcion: " + edtxt5.getText().toString() + "\n";
        Contacto contacto = new Contacto(edtxt1.getText().toString(), edtxt2.getText().toString(),
                edtxt3.getText().toString(), edtxt4.getText().toString(), edtxt5.getText().toString());
        lista.add(contacto);
        guardarArchivo();
        Toast.makeText(MainActivity.this, "Elemento Guardado", Toast.LENGTH_SHORT).show();
    }

    public boolean hasExternalStorage() {
        String estado = Environment.getExternalStorageState();
        if (estado.equals(Environment.MEDIA_MOUNTED)){
            return true;
        } else {
            return false;
        }
    }

    public void guardarArchivo() {
        try {
            if (hasExternalStorage()){
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "dataShared.txt");
                ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file, false));
                stream.writeObject(cad);
                stream.close();
                File f = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "listShared.txt");
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f, false));
                oos.writeObject(lista); //Objeto solo accesible por la aplicacion de origen
                oos.close();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show();
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
            File f = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "listShared.txt");
            //Para uso unicamente de esta aplicacion
            if (hasExternalStorage() && f.exists()){
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
                lista = (ArrayList<Contacto>) ois.readObject();
                ois.close();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }
}
