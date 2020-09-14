package com.example.semana5_bd.model;

public class Pet {
    private int petId;
    private String petName;
    private int petPoints;
    private int petImage;

    public Pet(int petId, String petName, int petPoints, int petImage) {
        this.petId = petId;
        this.petName = petName;
        this.petPoints = petPoints;
        this.petImage = petImage;
    }

    public Pet() {

    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public int getPetPoints() {
        return petPoints;
    }

    public void setPetPoints(int petPoints) {
        this.petPoints = petPoints;
    }

    public int getPetImage() {
        return petImage;
    }

    public void setPetImage(int petImage) {
        this.petImage = petImage;
    }
}
