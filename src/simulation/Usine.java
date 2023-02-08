package simulation;

import java.util.ArrayList;
import java.util.List;

public class Usine extends UsineTemplate {

    protected int id;
    protected int x;
    protected int y;

    public static List<Usine> data = new ArrayList<>();

    public Usine(int id, int x, int y, String iconeVide, String inconeUnTiers, String inconDeuxTiers, String iconPlein,
            List<Composant> entrees, int intervalProduction, List<Composant> sorties) {
        super("Usine", iconeVide, inconeUnTiers, inconDeuxTiers, iconPlein, entrees, intervalProduction, sorties);
        this.id = id;
        this.x = x;
        this.y = y;
        data.add(this);
    }

    public Usine(UsineTemplate template, int id, int x, int y) {
        super(template.type, template.iconeVide, template.inconeUnTiers, template.inconDeuxTiers, template.iconPlein,
                template.entrees, template.intervalProduction, template.sorties);
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

}
