/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package champollion;

/**
 *
 * @author paul
 */
import java.util.ArrayList;

public class Salle {
    public String nom;
    public int capacite;
    public ArrayList<Intervention> occupations = new ArrayList<>();

    public Salle(String nom, int capacite) {
        this.nom = nom;
        this.capacite = capacite;
    }

    public String getNom() {
        return nom;
    }

    public int getCapacite() {
        return capacite;
    }

    public ArrayList<Intervention> getOccupations() {
        return occupations;
    }
}

