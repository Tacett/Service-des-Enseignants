package champollion;

import java.util.ArrayList;

public class Enseignant extends Personne {

    public final ArrayList<ServicePrevu> listeServicePrevus = new ArrayList<>();
    public final ArrayList<Intervention> listeInterventions = new ArrayList<>();

    public Enseignant(String nom, String email) {
        super(nom, email);
    }

    public int heuresPrevues() {
        int somme = 0;
        for (ServicePrevu s : listeServicePrevus) {
            somme = (int) Math.round(somme + (s.getVolumeTP() * 0.75) + (s.getVolumeCM() * 1.5) + s.getVolumeTD());
        }
        return somme;
    }

    public int heuresPrevuesPourUE(UE ue) {

        int somme = 0;
        for (ServicePrevu s : listeServicePrevus) {
            if (s.getUe() == ue) {
                somme = (int) Math.round(somme + (s.getVolumeTP() * 0.75) + (s.getVolumeCM() * 1.5) + s.getVolumeTD());
            }
        }
        return somme;

    }

    public void ajouteEnseignement(UE ue, int volumeCM, int volumeTD, int volumeTP) {
        listeServicePrevus.add(new ServicePrevu(volumeCM, volumeTD, volumeTP, ue, this));
    }

    public void ajouteIntervention(Intervention inter) throws Exception {
        if (inter.getIntervenant() == this) {
            listeInterventions.add(inter);
        } else {
            throw new Exception("Cette intervention ne correspond pas à cet enseignant");
        }
    }

    public boolean enSousService() throws Exception {
        int nbHeuresPrevues = heuresPrevues();
        double sumNbHeuresRealisees = sommeInterventionsPlanifiees();

        return (nbHeuresPrevues - sumNbHeuresRealisees) > 0;
    }

    public double sommeInterventionsPlanifiees() throws Exception {
        double sumNbHeuresRealisees = 0;

        for (Intervention inter : listeInterventions) {
            if (!inter.isAnnulee()) {
                sumNbHeuresRealisees = sumNbHeuresRealisees + inter.dureeEquivalentTD();
            }
        }
        return Math.round(sumNbHeuresRealisees);
    }

    public int resteAPlanifier(UE ue, TypeIntervention type) throws Exception {
        double sommeInter = 0;
        if (enSousService()) {

            for (Intervention inter : listeInterventions) {
                if (inter.getType().equals(type) && inter.getMatiere().equals(ue)) {
                    sommeInter = sommeInter + inter.dureeEquivalentTD();
                }
            }

            double sommeServ = 0;

            for (ServicePrevu servicePrevu : listeServicePrevus) {
                if (servicePrevu.getUe().equals(ue)) {
                    switch (type) {
                        case CM:
                            sommeServ += servicePrevu.getVolumeCM() * 1.5;
                            break;

                        case TP:
                            sommeServ += servicePrevu.getVolumeTP() * 0.75;
                            break;

                        case TD:
                            sommeServ += servicePrevu.getVolumeTD();
                            break;

                    }
                }

                sommeInter -= Math.round(sommeServ);
            }
            }
            return (int) Math.abs(Math.round(sommeInter));   
    }
    


    public ArrayList<ServicePrevu> getListeServicePrevus() {
        return listeServicePrevus;
    }

    public ArrayList<Intervention> getlisteInterventions() {
        return listeInterventions;
    }
}
