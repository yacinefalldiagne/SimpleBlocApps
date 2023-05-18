package com.example.myapplicationprojet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "blog.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Créer la table "elements"
        String createTableQuery = "CREATE TABLE elements (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "titre TEXT," +
                "contenu TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Mettre à jour la base de données si nécessaire
        if (oldVersion < newVersion) {
            db.execSQL("DROP TABLE IF EXISTS elements");
            onCreate(db);
        }
    }
}

