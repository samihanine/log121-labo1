package assets;

import java.util.ArrayList;
import java.util.List;

public class Usine extends UsineType implements Asset {

    protected int id;
    protected int x;
    protected int y;
    public static List<Usine> data = new ArrayList<>();

    public Usine(UsineType template, int id, int x, int y) {
        super(template);
        this.id = id;
        this.x = x;
        this.y = y;
        data.add(this);
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static Usine getUsineById(int id) {
        for (Usine usine : data) {
            if (usine.getId() == id) {
                return usine;
            }
        }
        return null;
    }

    public void update() {
        if (!inventaireRempli())
            return;

        time++;

        if (time >= intervalProduction) {
            time = 0;
            produireComposant();
        }

        if (time >= intervalProduction * 3 / 4) {
            etat = 3;
        } else if (time >= intervalProduction * 2 / 4) {
            etat = 2;
        } else if (time >= intervalProduction * 1 / 4) {
            etat = 1;
        } else {
            etat = 0;
        }
    }

    public static void updateAll() {
        for (Usine usine : data) {
            usine.update();
        }
    }

    private void produireComposant() {
        if (composantSortie == null || composantSortie.isEmpty())
            return;

        int usineDestinationId = -1;

        for (Chemin chemin : Chemin.data) {
            if (chemin.getDepartId() == id) {
                Usine usine = Usine.getUsineById(chemin.getArriveeId());
                if (usine != null) {
                    usineDestinationId = usine.getId();
                }
            }
        }

        if (usineDestinationId == -1)
            return;

        new Composant(composantSortie, x, y, usineDestinationId);
    }

    private boolean inventaireRempli() {
        for (Inventaire inventaire : inventaire) {
            if (inventaire.getQuantite() < inventaire.getQuantiteRequise())
                return false;
        }
        return true;
    }

    public void updateInventaire(String composantType) {
        for (Inventaire inventaire : inventaire) {
            if (inventaire.getComposantType().equals(composantType)) {
                inventaire.setQuantite(inventaire.getQuantite() + 1);
            }
        }
    }

    public String getIcon() {
        if (etat == 0)
            return iconeVide;
        else if (etat == 1)
            return inconeUnTiers;
        else if (etat == 2)
            return inconDeuxTiers;

        return iconPlein;
    }

}
