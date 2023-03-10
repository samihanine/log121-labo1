package simulation;

import javax.swing.SwingWorker;

import assets.Composant;
import assets.Usine;
import stategie.ContexteStrategie;

public class Environnement extends SwingWorker<Object, String> {
	private boolean actif = true;
	private static final int DELAI = 20;

	@Override
	protected Object doInBackground() throws Exception {
		while (actif) {
			Thread.sleep(DELAI);

			Usine.updateAll();

			Composant.updateAll();

			ContexteStrategie.update();

			firePropertyChange("TEST", null, null);
		}
		return null;
	}

}