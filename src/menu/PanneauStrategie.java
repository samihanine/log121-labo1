package menu;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

import stategie.ContexteStrategie;
import stategie.StrategieAleatoire;
import stategie.StrategieFixe;

public class PanneauStrategie extends JPanel {

	private static final long serialVersionUID = 1L;

	public PanneauStrategie() {

		ButtonGroup groupeBoutons = new ButtonGroup();
		JRadioButton strategie1 = new JRadioButton("Strat�gie 1");
		JRadioButton strategie2 = new JRadioButton("Strat�gie 2");

		JButton boutonConfirmer = new JButton("Confirmer");

		boutonConfirmer.addActionListener((ActionEvent e) -> {
			if (e.getSource() == strategie1) {
				ContexteStrategie.setStrategie(new StrategieAleatoire());
			} else if (e.getSource() == strategie2) {
				ContexteStrategie.setStrategie(new StrategieFixe());
			}

			SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();
		});

		JButton boutonAnnuler = new JButton("Annuler");

		boutonAnnuler.addActionListener((ActionEvent e) -> {
			// Fermer la fen�tre du composant
			SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();
		});

		groupeBoutons.add(strategie1);
		groupeBoutons.add(strategie2);
		add(strategie1);
		add(strategie2);
		add(boutonConfirmer);
		add(boutonAnnuler);

	}

	/**
	 * Retourne le bouton s�lectionn� dans un groupe de boutons.
	 * 
	 * @param groupeBoutons
	 * @return
	 */
	public String getSelectedButtonText(ButtonGroup groupeBoutons) {
		for (Enumeration<AbstractButton> boutons = groupeBoutons.getElements(); boutons.hasMoreElements();) {
			AbstractButton bouton = boutons.nextElement();
			if (bouton.isSelected()) {
				return bouton.getText();
			}
		}

		return null;
	}

}
