package com.example.myapplicationprojet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class Afficher extends AppCompatActivity {

        private ListView listView;
        private Button addButton;

        private SQLiteDatabase database;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_afficher);

                listView = findViewById(R.id.list_view);
                addButton = findViewById(R.id.fab_button);

                DatabaseHelper dbHelper = new DatabaseHelper(this);
                database = dbHelper.getReadableDatabase();

                afficherElements();

                addButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Intent intent = new Intent(Afficher.this, MainActivity.class);
                                startActivity(intent);
                        }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Cursor cursor = (Cursor) parent.getItemAtPosition(position);
                                @SuppressLint("Range") int itemId = cursor.getInt(cursor.getColumnIndex("_id"));
                                afficherElement(itemId);
                        }
                });
        }

        private void afficherElements() {
                Cursor cursor = database.query("elements", null, null, null, null, null, null);

                String[] fromColumns = {"_id", "titre", "contenu"};
                int[] toViews = {R.id.item_id, R.id.item_titre, R.id.item_contenu};
                SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                        R.layout.list_item, cursor, fromColumns, toViews, 0);

                listView.setAdapter(adapter);
        }

        private void afficherElement(int itemId) {
                Cursor cursor = database.query("elements", null, "_id=?", new String[]{String.valueOf(itemId)}, null, null, null);

                if (cursor.moveToFirst()) {
                        @SuppressLint("Range") String titre = cursor.getString(cursor.getColumnIndex("titre"));
                        @SuppressLint("Range") String contenu = cursor.getString(cursor.getColumnIndex("contenu"));
                        @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex("date"));

                        Intent intent = new Intent(Afficher.this, Details.class);
                        intent.putExtra("titre", titre);
                        intent.putExtra("contenu", contenu);
                        intent.putExtra("date", date);
                        startActivity(intent);
                }

                cursor.close();
        }

        @Override
        protected void onDestroy() {
                super.onDestroy();
                database.close();
        }
}
