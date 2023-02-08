package simulation;

import java.util.List;

public class Entrepot extends Usine {

    public Entrepot(int id, int x, int y, String iconeVide, String inconeUnTiers, String inconDeuxTiers,
            String iconPlein,
            List<Composant> entrees, int intervalProduction, List<Composant> sorties) {
        super(id, x, y, iconeVide, inconeUnTiers, inconDeuxTiers, iconPlein, entrees, intervalProduction, sorties);
    }

}
