package observateur;

import java.util.LinkedList;
import java.util.List;

public class Sujet {
    private List<ObservateurEntrepot> listeObservateur = new LinkedList<ObservateurEntrepot>();
    private int etat = 1;

    public void attache(ObservateurEntrepot o) {
        listeObservateur.add(o);
    }

    public void detache(ObservateurEntrepot o) {
        listeObservateur.remove(o);
    }

    public void notifierObservateurs() {
        for (ObservateurEntrepot obv : listeObservateur) {
            obv.update();
        }
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
        notifierObservateurs();
    }

}
