package assets;

public class Inventaire {
    private int quantite = 0;
    private int quantiteRequise = 0;
    private String composantType;

    public Inventaire(String composantType, int quantiteRequise) {
        this.composantType = composantType;
        this.quantiteRequise = quantiteRequise;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getQuantiteRequise() {
        return quantiteRequise;
    }

    public void setQuantiteRequise(int quantiteRequis) {
        this.quantiteRequise = quantiteRequis;
    }

    public String getComposantType() {
        return composantType;
    }

    public void setComposantType(String composantType) {
        this.composantType = composantType;
    }
}
