package observateur;

public class ObservateurEntrepot extends Observateurs {
	private int etat = 0;

	public ObservateurEntrepot(Sujet sujet) {
		this.sujet = sujet;
		this.sujet.attache(this);
	}

	public void update() {
		etat = sujet.getEtat();
	}

	public int getEtat() {
		return etat;
	}

}
