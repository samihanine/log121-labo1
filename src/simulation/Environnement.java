package simulation;

import javax.swing.SwingWorker;

import assets.Composant;
import assets.Usine;

public class Environnement extends SwingWorker<Object, String> {
	private boolean actif = true;
	private static final int DELAI = 10;

	@Override
	protected Object doInBackground() throws Exception {
		while (actif) {
			Thread.sleep(DELAI);

			Usine.updateAll();

			Composant.updateAll();

			firePropertyChange("TEST", null, null);
		}
		return null;
	}

}