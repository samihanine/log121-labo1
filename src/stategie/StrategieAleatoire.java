package stategie;

import java.util.List;

import assets.Entrepot;
import assets.Usine;

public class StrategieAleatoire implements Strategie {

    public void update() {
        int aleatoire = (int) (Math.random() * 100);

        if (aleatoire < 98)
            return;

        List<Entrepot> entrepots = Usine.getEntrepots();
        for (Entrepot entrepot : entrepots) {
            if (entrepot.vendre())
                break;
        }
    }
}