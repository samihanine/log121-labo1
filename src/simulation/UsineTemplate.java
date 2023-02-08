package simulation;

import java.util.ArrayList;
import java.util.List;

public class UsineTemplate {
    protected String type;
    protected String iconeVide;
    protected String inconeUnTiers;
    protected String inconDeuxTiers;
    protected String iconPlein;
    protected int intervalProduction;
    protected List<Composant> sorties;
    protected List<Composant> entrees = new ArrayList<>();
    public static List<UsineTemplate> data = new ArrayList<>();

    public UsineTemplate(String type, String iconeVide, String inconeUnTiers, String inconDeuxTiers,
            String iconPlein, List<Composant> entrees, int intervalProduction, List<Composant> sorties) {
        this.type = type;
        this.iconeVide = iconeVide;
        this.inconeUnTiers = inconeUnTiers;
        this.inconDeuxTiers = inconDeuxTiers;
        this.iconPlein = iconPlein;
        this.entrees = entrees;
        this.intervalProduction = intervalProduction;
        this.sorties = sorties;
        data.add(this);
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

    public List<Composant> getEntrees() {
        return entrees;
    }

    public void setEntrees(List<Composant> entrees) {
        this.entrees = entrees;
    }

    public int getIntervalProduction() {
        return intervalProduction;
    }

    public void setIntervalProduction(int intervalProduction) {
        this.intervalProduction = intervalProduction;
    }

    public List<Composant> getSorties() {
        return sorties;
    }

    public void setSorties(List<Composant> sorties) {
        this.sorties = sorties;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static UsineTemplate getUsineTemplateByType(String type) {
        for (UsineTemplate usine : data) {
            if (usine.getType().equals(type)) {
                return usine;
            }
        }
        return null;
    }
}
