package assets;

import observateur.Sujet;

public class Entrepot extends Usine {
    private Sujet sujet = new Sujet();

    public Entrepot(UsineType template, int id, int x, int y) {
        super(template, id, x, y);
    }

    public Sujet getSujet() {
        return sujet;
    }

    public boolean vendre() {
        for (Inventaire item : inventaire) {
            if (item.getQuantite() > 0) {
                item.setQuantite(item.getQuantite() - 1);
                update();
                return true;
            }
        }

        return false;
    }

    @Override
    public void update() {
        int quantiteTotal = 0;
        int quantiteRequiseTotal = 0;

        for (Inventaire item : inventaire) {
            quantiteTotal += item.getQuantite();
            quantiteRequiseTotal += item.getQuantiteRequise();
        }

        if (quantiteTotal >= quantiteRequiseTotal * 3 / 3) {
            setEtat(3);
        } else if (quantiteTotal >= quantiteRequiseTotal * 2 / 3) {
            setEtat(2);
        } else if (quantiteTotal >= quantiteRequiseTotal * 1 / 3) {
            setEtat(1);
        } else {
            setEtat(0);
        }
    }

    @Override
    public void setEtat(int etat) {
        this.etat = etat;
        if (sujet != null) {
            sujet.setEtat(etat);
        }
    }
}
