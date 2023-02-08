package simulation;

public class Composant {

    private String type;
    private int quantite;

    public Composant(String type, int quantite) {
        this.type = type;
        this.quantite = quantite;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

}
