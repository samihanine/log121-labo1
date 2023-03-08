package stategie;

public class ContexteStrategie {
    private static Strategie strategie = new StrategieFixe();

    public static void setStrategie(Strategie newStrategie) {
        strategie = newStrategie;
    }

    public static Strategie getStrategie() {
        return strategie;
    }

    public static void update() {
        strategie.update();
    }

}
