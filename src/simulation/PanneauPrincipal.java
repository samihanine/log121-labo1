package simulation;

import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Toolkit;

public class PanneauPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;

	@Override
	public void paint(Graphics g) {
		super.paint(g);

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
			Image image = Toolkit.getDefaultToolkit().getImage(usine.getIconeVide());
			g.drawImage(image, usine.getX() - 10, usine.getY() - 10, this);
		}

	}

}