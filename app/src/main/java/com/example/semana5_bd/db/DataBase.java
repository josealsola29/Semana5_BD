package com.example.semana5_bd.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.semana5_bd.model.Pet;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {
    private Context context;

    public DataBase(@Nullable Context context) {
        super(context, ConstantsBD.DATABASE_NAME, null, ConstantsBD.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryCreateTablePet = "CREATE TABLE " + ConstantsBD.TABLE_PETS + "("
                + ConstantsBD.TABLE_PETS_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT, "
                + ConstantsBD.TABLE_PETS_NAME + " TEXT ,"
                + ConstantsBD.TABLE_PETS_POINTS + " TEXT ,"
                + ConstantsBD.TABLE_PETS_IMAGE + " INTEGER )";
        sqLiteDatabase.execSQL(queryCreateTablePet);

        String queryCreateTablePetPoints = "CREATE TABLE " + ConstantsBD.TABLE_POINTS + "("
                + ConstantsBD.TABLE_POINTS_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT, "
                + ConstantsBD.TABLE_POINTS_ID_PET + " INTEGER ,"
                + ConstantsBD.TABLE_POINTS_NUMBER_POINTS + " INTEGER, "
                + "FOREIGN KEY (" + ConstantsBD.TABLE_POINTS_ID_PET + ") "
                + "REFERENCES " + ConstantsBD.TABLE_PETS + "(" + ConstantsBD.TABLE_PETS_ID + ")"
                + ")";
        sqLiteDatabase.execSQL(queryCreateTablePetPoints);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ConstantsBD.TABLE_PETS);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<Pet> obtainAllPets() {
        ArrayList<Pet> pets = new ArrayList<>();
        String query = "SELECT * FROM " + ConstantsBD.TABLE_PETS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()) {
            Pet petBD = new Pet();
            petBD.setPetId(registros.getInt(0));
            petBD.setPetName(registros.getString(1));
            petBD.setPetPoints(registros.getInt(2));
            petBD.setPetImage(registros.getInt(3));


            String queryLikes = "SELECT COUNT(" + ConstantsBD.TABLE_POINTS_NUMBER_POINTS + ") as likes "
                    + " FROM " + ConstantsBD.TABLE_POINTS
                    + " WHERE " + ConstantsBD.TABLE_POINTS_ID_PET + "=" + petBD.getPetId();
            Cursor registrosLikes = db.rawQuery(queryLikes, null);
            if (registrosLikes.moveToNext()) {
                petBD.setPetPoints(registrosLikes.getInt(0));
            } else
                petBD.setPetPoints(0);
            registrosLikes.close();
            pets.add(petBD);
        }
        registros.close();
        db.close();
        return pets;
    }

    public void insertPetDB(ContentValues contentValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.insert(ConstantsBD.TABLE_PETS, null, contentValues);
        database.close();
    }

    public void insertPointPet(ContentValues contentValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.insert(ConstantsBD.TABLE_POINTS, null, contentValues);
        database.close();
    }

    public int obtainPointsPet(Pet pet) {
        int points = 0;

        String query = "SELECT COUNT (" + ConstantsBD.TABLE_POINTS_NUMBER_POINTS + ")"
                + " FROM " + ConstantsBD.TABLE_POINTS
                + " WHERE " + ConstantsBD.TABLE_POINTS_ID_PET + " = " + pet.getPetId();

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor registros = sqLiteDatabase.rawQuery(query, null);
        if (registros.moveToNext()) {
            points = registros.getInt(0);
        }
        registros.close();
        sqLiteDatabase.close();
        return points;
    }
}