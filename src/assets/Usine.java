package assets;

import java.util.ArrayList;
import java.util.List;

import observateur.ObservateurEntrepot;

public class Usine extends UsineType implements Asset {

    protected int id;
    protected int x;
    protected int y;
    public static List<Usine> data = new ArrayList<>();
    private ObservateurEntrepot observateur;

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

    public void setObservateur(ObservateurEntrepot observateur) {
        this.observateur = observateur;
    }

    public ObservateurEntrepot getObservateur() {
        return observateur;
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

        if (observateur != null) {
            if (observateur.getEtat() == 3) {
                time = 0;
                return;
            }

        }

        int max = intervalProduction * ((observateur.getEtat() + 1));

        time++;

        if (time >= max) {
            time = 0;
            produireComposant();
        }

        if (time >= max * 3 / 4) {
            etat = 3;
        } else if (time >= max * 2 / 4) {
            etat = 2;
        } else if (time >= max * 1 / 4) {
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

        for (Inventaire inventaire : inventaire) {
            if (inventaire.getComposantType().equalsIgnoreCase(composantSortie)) {
                inventaire.setQuantite(inventaire.getQuantite() - inventaire.getQuantiteRequise());
                break;
            }
        }

        new Composant(composantSortie, x, y, usineDestinationId);
    }

    private boolean inventaireRempli() {
        for (Inventaire inventaire : inventaire) {
            if (inventaire.getQuantite() < inventaire.getQuantiteRequise())
                return false;
        }
        return true;
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

    public static List<Entrepot> getEntrepots() {
        List<Entrepot> entrepots = new ArrayList<>();
        for (Usine usine : data) {
            if (usine instanceof Entrepot) {
                entrepots.add((Entrepot) usine);
            }
        }
        return entrepots;
    }

}
