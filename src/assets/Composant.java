package assets;

import java.util.ArrayList;
import java.util.List;

public class Composant implements Asset {
    private int x;
    private int y;
    private String type;
    private int usineDestinationId;
    public static List<Composant> data = new ArrayList<>();
    private boolean arrivee = false;

    public static Composant getComposantByType(String type) {
        for (Composant item : data) {
            if (item.getType().equals(type)) {
                return item;
            }
        }
        return null;
    }

    public Composant(String type, int x, int y, int usineDestinationId) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.usineDestinationId = usineDestinationId;

        data.add(this);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUsineDestinationId() {
        return usineDestinationId;
    }

    public void setUsineDestinationId(int usineDestinationId) {
        this.usineDestinationId = usineDestinationId;
    }

    public boolean getArrivee() {
        return arrivee;
    }

    public void setArrivee(boolean arrivee) {
        this.arrivee = arrivee;
    }

    public String getIcon() {
        return "src/ressources/" + type + ".png";
    }

    public void update() {
        Usine usine = Usine.getUsineById(usineDestinationId);
        int destinationX = usine.getX();
        int destinationY = usine.getY();
        int vx = 0;
        int vy = 0;

        if (destinationX > x) {
            vx = 1;
        }
        if (destinationX < x) {
            vx = -1;
        }
        if (destinationY > y) {
            vy = 1;
        }
        if (destinationY < y) {
            vy = -1;
        }

        x += vx;
        y += vy;

        if (x == destinationX && y == destinationY) {
            arrivee = true;
        }
    }

    public static void updateAll() {
        for (Composant composant : data) {
            composant.update();

            if (composant.getArrivee()) {
                Usine usine = Usine.getUsineById(composant.getUsineDestinationId());
                usine.updateInventaire(composant.getType());
                data.remove(composant);
                break;
            }
        }
    }

}
