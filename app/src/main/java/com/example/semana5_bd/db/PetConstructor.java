package com.example.semana5_bd.db;

import android.content.ContentValues;
import android.content.Context;

import com.example.semana5_bd.R;
import com.example.semana5_bd.model.Pet;

import java.util.ArrayList;

public class PetConstructor {
    private static final Integer LIKE = 1;
    private Context context;

    public PetConstructor(Context context) {
        this.context = context;
    }

    public ArrayList<Pet> obtainData() {
        DataBase dataBase = new DataBase(context);
        insertPetsDummy(dataBase);
        return dataBase.obtainAllPets();
    }

    public void insertPetsDummy(DataBase dataBase) {
        ContentValues contentValues;

        contentValues = new ContentValues();
        contentValues.put(ConstantsBD.TABLE_PETS_NAME, "Schrödinger");
        contentValues.put(ConstantsBD.TABLE_PETS_IMAGE, R.drawable.icons8_black_cat);
        dataBase.insertPetDB(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantsBD.TABLE_PETS_NAME, "Shaggy");
        contentValues.put(ConstantsBD.TABLE_PETS_IMAGE, R.drawable.icons8_dog);
        dataBase.insertPetDB(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantsBD.TABLE_PETS_NAME, "Inu");
        contentValues.put(ConstantsBD.TABLE_PETS_IMAGE, R.drawable.icons8_dog_);
        dataBase.insertPetDB(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantsBD.TABLE_PETS_NAME, "Max");
        contentValues.put(ConstantsBD.TABLE_PETS_IMAGE, R.drawable.icons8_corgi_96);
        dataBase.insertPetDB(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantsBD.TABLE_PETS_NAME, "Kyu");
        contentValues.put(ConstantsBD.TABLE_PETS_IMAGE, R.drawable.icons8_pug_96);
        dataBase.insertPetDB(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantsBD.TABLE_PETS_NAME, "Riko");
        contentValues.put(ConstantsBD.TABLE_PETS_IMAGE, R.drawable.icons8_running_rabbit_96);
        dataBase.insertPetDB(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantsBD.TABLE_PETS_NAME, "Mashu");
        contentValues.put(ConstantsBD.TABLE_PETS_IMAGE, R.drawable.icons8_yorkshire_terrier_96);
        dataBase.insertPetDB(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantsBD.TABLE_PETS_NAME, "Pushi");
        contentValues.put(ConstantsBD.TABLE_PETS_IMAGE, R.drawable.icons8_cute_hamster_96);
        dataBase.insertPetDB(contentValues);
    }

    public void givePoint(Pet pet) {
        DataBase dataBase = new DataBase(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantsBD.TABLE_POINTS_ID_PET, pet.getPetId());
        contentValues.put(ConstantsBD.TABLE_POINTS_NUMBER_POINTS, LIKE);
        dataBase.insertPointPet(contentValues);
    }

    public int getPointsPetDB(Pet pet) {
        DataBase dataBase = new DataBase(context);
        return dataBase.obtainPointsPet(pet);
    }
}
