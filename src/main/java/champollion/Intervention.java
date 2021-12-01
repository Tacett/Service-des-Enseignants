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
import java.util.Date;

public class Intervention {

    private Date debut;
    private int duree;
    private boolean annulation;
    public int heureDebut;
    private TypeIntervention type;
    private final Salle lieu;
    private final UE matiere;
    private final Enseignant intervenant;

    public Intervention(Date debut, int duree, boolean annulee, int heureDebut, TypeIntervention type, Salle lieu, UE matiere, Enseignant intervenant) {
        this.debut = debut;
        this.duree = duree;
        this.annulation = false;
        this.heureDebut = heureDebut;
        this.type = type;
        this.lieu = lieu;
        this.matiere = matiere;
        this.intervenant = intervenant;
    }

    // ----------------- AJOUT DE FONCTIONS -----------------
    public double dureeEquivalentTD() throws Exception {
        switch (type) {
            case CM:
                return this.getDuree() * 1.5;

            case TP:
                return getDuree() * 0.75;

            case TD:
                return this.getDuree();
            default:
                throw new Exception("Type Incorrect");
        }
    }

    //  ----------------- GETTERS -----------------
    public Date getDebut() {
        return debut;
    }

    public int getDuree() {
        return duree;
    }

    public int getHeureDebut() {
        return heureDebut;
    }

    public boolean isAnnulee() {
        return annulation;
    }

    public TypeIntervention getType() {
        return type;
    }

    public Enseignant getIntervenant() {
        return intervenant;
    }

    public Salle getLieu() {
        return lieu;
    }

    public UE getMatiere() {
        return matiere;
    }
    //  ----------------- SETTERS -----------------

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setAnnulee(boolean annulation) {
        this.annulation = annulation;
    }

    public void setType(TypeIntervention type) {
        this.type = type;
    }

}
