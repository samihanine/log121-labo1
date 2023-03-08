package assets;

import java.util.ArrayList;
import java.util.List;

public class UsineType {
    protected String type;
    protected String iconeVide;
    protected String inconeUnTiers;
    protected String inconDeuxTiers;
    protected String iconPlein;
    protected int intervalProduction;
    protected List<Inventaire> inventaire = new ArrayList<>();
    protected String composantSortie = null;
    public static List<UsineType> data = new ArrayList<>();
    protected boolean entrepot = false;
    protected int etat = 0;
    protected double time = 0.0;

    public UsineType(String type, String iconeVide, String inconeUnTiers, String inconDeuxTiers,
            String iconPlein, int intervalProduction, List<Inventaire> inventaire, String composantSortie,
            boolean entrepot) {
        this.type = type;
        this.iconeVide = iconeVide;
        this.inconeUnTiers = inconeUnTiers;
        this.inconDeuxTiers = inconDeuxTiers;
        this.iconPlein = iconPlein;
        this.inventaire = inventaire;
        this.intervalProduction = intervalProduction;
        this.composantSortie = composantSortie;
        this.entrepot = entrepot;
        data.add(this);
    }

    public UsineType(UsineType template) {
        this.type = template.getType();
        this.iconeVide = template.getIconeVide();
        this.inconeUnTiers = template.getInconeUnTiers();
        this.inconDeuxTiers = template.getInconDeuxTiers();
        this.iconPlein = template.getIconPlein();
        this.inventaire = template.getInventaire();
        this.intervalProduction = template.getIntervalProduction();
        this.composantSortie = template.getComposantSortie();
        this.entrepot = template.getEntrepot();
    }

    public void setEntrepot(boolean entrepot) {
        this.entrepot = entrepot;
    }

    public boolean getEntrepot() {
        return entrepot;
    }

    public String getIconeVide() {
        return iconeVide;
    }

    public String getInconeUnTiers() {
        return inconeUnTiers;
    }

    public String getInconDeuxTiers() {
        return inconDeuxTiers;
    }

    public String getIconPlein() {
        return iconPlein;
    }

    public void setIconeVide(String iconeVide) {
        this.iconeVide = iconeVide;
    }

    public void setInconeUnTiers(String inconeUnTiers) {
        this.inconeUnTiers = inconeUnTiers;
    }

    public void setInconDeuxTiers(String inconDeuxTiers) {
        this.inconDeuxTiers = inconDeuxTiers;
    }

    public void setIconPlein(String iconPlein) {
        this.iconPlein = iconPlein;
    }

    public int getIntervalProduction() {
        return intervalProduction;
    }

    public void setIntervalProduction(int intervalProduction) {
        this.intervalProduction = intervalProduction;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static UsineType getUsineTypeByType(String type) {
        for (UsineType usine : data) {
            if (usine.getType().equals(type)) {
                return usine;
            }
        }
        return null;
    }

    public List<Inventaire> getInventaire() {
        return inventaire;
    }

    public void setInventaire(List<Inventaire> inventaire) {
        this.inventaire = inventaire;
    }

    public String getComposantSortie() {
        return composantSortie;
    }

    public void setComposantSortie(String composantSortie) {
        this.composantSortie = composantSortie;
    }

    public String toString() {
        return "UsineType [type=" + type + ", iconeVide=" + iconeVide + ", inconeUnTiers=" + inconeUnTiers
                + ", inconDeuxTiers=" + inconDeuxTiers + ", iconPlein=" + iconPlein + ", intervalProduction="
                + intervalProduction + ", inventaire=" + inventaire + ", composantSortie=" + composantSortie + "]";
    }
}
