package com.example.diario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

public class Consulta extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference reference;
    LinearLayout coluna;
    class User {
        private String Value;

    }
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        database = FirebaseDatabase.getInstance();

        reference = database.getReference();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
               // System.out.println( snapshot.get);
                for (DataSnapshot item : snapshot.getChildren()) {
                    coluna = findViewById(R.id.coluna);
                    TextView txt =  new TextView(Consulta.this);

                    JSONObject json = null;
                    try {
                         json = new JSONObject(item.getValue().toString());
                        System.out.println(json.getString("Nota").toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    System.out.println( item.getValue().toString());
                    try {
                        txt.setText(json.getString("Nota").toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //txt.setText(snapshot.getValue().toString());
                  //txt.setGravity(Gravity.CENTER);
                    txt.setLayoutParams(new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                    coluna.addView(txt);
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });


    }
}