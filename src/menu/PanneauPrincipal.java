package menu;

import java.awt.Graphics;
import javax.swing.JPanel;

import assets.Chemin;
import assets.Composant;
import assets.Usine;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

public class PanneauPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		try {

			for (Chemin chemin : Chemin.data) {
				Usine usineDepart = Usine.getUsineById(chemin.getDepartId());
				Usine usineArrivee = Usine.getUsineById(chemin.getArriveeId());

				if (usineDepart == null || usineArrivee == null) {
					continue;
				}

				int x1 = usineDepart.getX();
				int y1 = usineDepart.getY();
				int x2 = usineArrivee.getX();
				int y2 = usineArrivee.getY();
				g.drawLine(x1, y1, x2, y2);
			}

			for (Usine usine : Usine.data) {
				Image image = Toolkit.getDefaultToolkit().getImage(usine.getIcon());
				g.drawImage(image, usine.getX() - 10, usine.getY() - 10, this);
			}

			// copie des composants
			List<Composant> composants = new ArrayList<>(Composant.data);
			for (Composant composant : composants) {
				Image image = Toolkit.getDefaultToolkit().getImage(composant.getIcon());
				g.drawImage(image, composant.getX() - 20, composant.getY() - 20, this);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}