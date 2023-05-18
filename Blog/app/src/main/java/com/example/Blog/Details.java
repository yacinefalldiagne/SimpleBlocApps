package com.example.myapplicationprojet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Details extends AppCompatActivity {

    private TextView titreTextView;
    private TextView contenuTextView;
    private TextView dateTextView;
    private Button retourButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        titreTextView = findViewById(R.id.titreTextView);
        contenuTextView = findViewById(R.id.contenuTextView);
        dateTextView = findViewById(R.id.dateTextView);
        retourButton = findViewById(R.id.retourButton);

        Intent intent = getIntent();
        String titre = intent.getStringExtra("titre");
        String contenu = intent.getStringExtra("contenu");
        String date = intent.getStringExtra("date");

        titreTextView.setText(titre);
        contenuTextView.setText(contenu);
        dateTextView.setText(date);

        retourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
