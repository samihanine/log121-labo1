package stategie;

import java.util.List;

import assets.Entrepot;
import assets.Usine;

public class VenteFixe implements Vente {
    int time = 0;
    int intervalVente = 200;

    public void vendre() {
        time++;
        if (time < intervalVente)
            return;
        time = 0;
        List<Entrepot> entrepots = Usine.getEntrepots();
        for (Entrepot entrepot : entrepots) {
            if (entrepot.vendre()) {
                System.out.println("Vente Effectuée");
                break;
            }
        }
    }
}
