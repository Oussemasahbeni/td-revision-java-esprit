package com.esprit;

import java.util.Objects;

public class Voiture {
    private int  id;
    private String marque;
    private float prixLocation;


    public Voiture(int id, String marque, float prixLocation) {
        this.id = id;
        this.marque = marque;
        this.prixLocation = prixLocation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public float getPrixLocation() {
        return prixLocation;
    }

    public void setPrixLocation(float prixLocation) {
        this.prixLocation = prixLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voiture voiture = (Voiture) o;
        return getId() == voiture.getId() && Float.compare(getPrixLocation(), voiture.getPrixLocation()) == 0 && Objects.equals(getMarque(), voiture.getMarque());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMarque(), getPrixLocation());
    }

    @Override
    public String toString() {
        return "Voiture{" +
                "id=" + id +
                ", marque='" + marque + '\'' +
                ", prixLocation=" + prixLocation +
                '}';
    }
}















