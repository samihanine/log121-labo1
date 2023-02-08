package simulation;

import java.util.ArrayList;
import java.util.List;

public class Chemin {
    private int departId;
    private int arriveeId;
    public static List<Chemin> data = new ArrayList<>();

    public Chemin(int departId, int arriveeId) {
        this.departId = departId;
        this.arriveeId = arriveeId;

        data.add(this);
    }

    public int getDepartId() {
        return departId;
    }

    public int getArriveeId() {
        return arriveeId;
    }

    public void setDepartId(int departId) {
        this.departId = departId;
    }

    public void setArriveeId(int arriveeId) {
        this.arriveeId = arriveeId;
    }

}
