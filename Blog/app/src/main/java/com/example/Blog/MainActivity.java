package com.example.myapplicationprojet;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText titreEditText;
    private EditText contenuEditText;
    private Button ajouterButton;

    private Button retourButton;

    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titreEditText = findViewById(R.id.titre);
        contenuEditText = findViewById(R.id.contenu);
        ajouterButton = findViewById(R.id.add_button);
        retourButton = findViewById(R.id.back_button);

        // Ouvrir ou créer la base de données
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        retourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Afficher.class);
                startActivity(intent);
            }
        });

        ajouterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ajouterElement();
            }
        });
    }

    private void ajouterElement() {
        String titre = titreEditText.getText().toString().trim();
        String contenu = contenuEditText.getText().toString().trim();

        if (titre.isEmpty() || contenu.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        // Créer un nouvel enregistrement dans la table "elements"
        ContentValues values = new ContentValues();
        values.put("titre", titre);
        values.put("contenu", contenu);

        long newRowId = database.insert("elements", null, values);

        if (newRowId != -1) {
            Toast.makeText(this, "Élément ajouté avec succès", Toast.LENGTH_SHORT).show();
            // Réinitialiser les champs d'entrée
            titreEditText.setText("");
            contenuEditText.setText("");
        } else {
            Toast.makeText(this, "Erreur lors de l'ajout de l'élément", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Fermer la base de données lors de la fermeture de l'activité
        database.close();
    }
}
