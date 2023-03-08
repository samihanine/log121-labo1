package stategie;

public class ContexteVente {
    private static Vente strategie = new VenteFixe();

    public static void setStrategie(Vente newStrategie) {
        strategie = newStrategie;
    }

    public static Vente getStrategie() {
        return strategie;
    }

    public static void vendre() {
        strategie.vendre();
    }

}
