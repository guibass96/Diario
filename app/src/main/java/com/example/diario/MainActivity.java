package com.example.diario;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    TextView txtDigitado;
    Button btnSalvarNota;
    Button btnNota;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        txtDigitado = findViewById(R.id.txtNota);
        btnSalvarNota = findViewById(R.id.btnSalvarNota);
        btnNota = findViewById(R.id.btnNotas);

        setSupportActionBar(toolbar);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        FloatingActionButton fab = findViewById(R.id.fab);

       // myRef.child("saldo").setValue(editSaldo.getText().toString());
      //  myRef.child("cpf").setValue(editCpf.getText().toString());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        btnSalvarNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference myRef = database.getReference(new Date().toString());
                myRef.child("Nota").setValue(txtDigitado.getText().toString());
                Toast.makeText(MainActivity.this, "Nota Adicionada", Toast.LENGTH_SHORT).show();
            }
        });
        btnNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultarDados(view);
            }
        });
    }
    public void consultarDados(View v){
        Intent tela02 = new Intent(this, Consulta.class);
        startActivity(tela02);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}