package simulation;

public class Composant {

    private String type;
    private int capacite;

    public Composant(String type, int capacite) {
        this.type = type;
        this.capacite = capacite;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

}
