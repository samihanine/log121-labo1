package simulation;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MenuFenetre extends JMenuBar {

	private static final long serialVersionUID = 1L;
	private static final String MENU_FICHIER_TITRE = "Fichier";
	private static final String MENU_FICHIER_CHARGER = "Charger";
	private static final String MENU_FICHIER_QUITTER = "Quitter";
	private static final String MENU_SIMULATION_TITRE = "Simulation";
	private static final String MENU_SIMULATION_CHOISIR = "Choisir";
	private static final String MENU_AIDE_TITRE = "Aide";
	private static final String MENU_AIDE_PROPOS = "� propos de...";

	public MenuFenetre() {
		ajouterMenuFichier();
		ajouterMenuSimulation();
		ajouterMenuAide();
	}

	/**
	 * Cr�er le menu de Fichier
	 */
	private void ajouterMenuFichier() {
		JMenu menuFichier = new JMenu(MENU_FICHIER_TITRE);
		JMenuItem menuCharger = new JMenuItem(MENU_FICHIER_CHARGER);
		JMenuItem menuQuitter = new JMenuItem(MENU_FICHIER_QUITTER);

		menuCharger.addActionListener((ActionEvent e) -> {
			JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			fileChooser.setDialogTitle("S�lectionnez un fichier de configuration");
			fileChooser.setAcceptAllFileFilterUsed(false);
			// Cr�er un filtre
			FileNameExtensionFilter filtre = new FileNameExtensionFilter(".xml", "xml");
			fileChooser.addChoosableFileFilter(filtre);

			int returnValue = fileChooser.showOpenDialog(null);

			if (returnValue == JFileChooser.APPROVE_OPTION) {

				File selectedFile = fileChooser.getSelectedFile();
				System.out.println(selectedFile.getAbsolutePath());

				try {
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					Document doc = dBuilder.parse(selectedFile);
					doc.getDocumentElement().normalize();

					NodeList metadonnesBalises = doc.getElementsByTagName("metadonnees");

					for (int i = 0; i < metadonnesBalises.getLength(); i++) {
						Node metadonnesBalise = metadonnesBalises.item(i);
						createTemplates(metadonnesBalise);
					}

					NodeList simulationBalises = doc.getElementsByTagName("simulation");

					for (int i = 0; i < simulationBalises.getLength(); i++) {
						Node simulationBalise = simulationBalises.item(i);
						createSimulation(simulationBalise);
					}

					for (Usine usine : Usine.data) {
						System.out.println(usine.type + ", x: " + usine.x + ", y: " + usine.y + ", id: " + usine.id);
					}

				} catch (Exception exception) {
					exception.printStackTrace();
				}

			}
		});

		menuQuitter.addActionListener((ActionEvent e) -> {
			System.exit(0);
		});

		menuFichier.add(menuCharger);
		menuFichier.add(menuQuitter);

		add(menuFichier);
	}

	public void createSimulation(Node simulationBalise) {
		NodeList usines = simulationBalise.getChildNodes();

		for (int j = 0; j < usines.getLength(); j++) {
			Node node = usines.item(j);

			if (node.getNodeType() != Node.ELEMENT_NODE)
				continue;

			if (node.getNodeName().contains("usine")) {
				String type = ((Element) node).getAttribute("type");
				int x = Integer.parseInt(((Element) node).getAttribute("x"));
				int y = Integer.parseInt(((Element) node).getAttribute("y"));
				int id = Integer.parseInt(((Element) node).getAttribute("id"));

				UsineTemplate usine = UsineTemplate.getUsineTemplateByType(type);

				new Usine(usine, id, x, y);
			}

			if (node.getNodeName().contains("chemins")) {
				NodeList chemins = node.getChildNodes();

				for (int k = 0; k < chemins.getLength(); k++) {
					Node chemin = chemins.item(k);

					if (chemin.getNodeType() != Node.ELEMENT_NODE)
						continue;

					int de = Integer.parseInt(((Element) chemin).getAttribute("de"));
					int vers = Integer.parseInt(((Element) chemin).getAttribute("vers"));

					new Chemin(de, vers);
				}
			}

		}
	}

	public void createTemplates(Node metadonnesBalise) {
		NodeList usines = metadonnesBalise.getChildNodes();

		for (int j = 0; j < usines.getLength(); j++) {
			Node usine = usines.item(j);

			if (usine.getNodeType() != Node.ELEMENT_NODE)
				continue;

			String type = ((Element) usine).getAttribute("type");
			boolean entrepot = false;
			int interval = 0;
			String iconeVide = "";
			String iconePlein = "";
			String iconeUnTiers = "";
			String iconeDeuxTiers = "";
			List<Composant> entree = new ArrayList<Composant>();
			List<Composant> sortie = new ArrayList<Composant>();

			NodeList usineChildren = usine.getChildNodes();

			for (int k = 0; k < usineChildren.getLength(); k++) {
				Node node = usineChildren.item(k);

				if (node.getNodeType() != Node.ELEMENT_NODE)
					continue;

				if (node.getNodeName().contains("interval-production")) {
					interval = Integer.parseInt(node.getTextContent());
				}

				if (node.getNodeName().contains("icones")) {
					NodeList icones = node.getChildNodes();

					for (int l = 0; l < icones.getLength(); l++) {
						Node icone = icones.item(l);

						if (icone.getNodeType() != Node.ELEMENT_NODE)
							continue;

						if (icone.getNodeName().contains("icone")) {
							String iconeType = ((Element) icone).getAttribute("type");
							String iconePath = ((Element) icone).getAttribute("path");

							if (iconeType.contains("vide")) {
								iconeVide = iconePath;
							} else if (iconeType.contains("plein")) {
								iconePlein = iconePath;
							} else if (iconeType.contains("un-tiers")) {
								iconeUnTiers = iconePath;
							} else if (iconeType.contains("deux-tiers")) {
								iconeDeuxTiers = iconePath;
							}
						}
					}
				}

				if (node.getNodeName().contains("entree")) {
					// String composantType = ((Element) node).getAttribute("type");
					// int composantCapacite = Integer.parseInt(((Element)
					// node).getAttribute("capacite"));

					// Composant composant = new Composant(composantType, composantCapacite);
					// entree.add(composant);
				}
			}

			if (type.contains("entrepot")) {
				entrepot = true;
			}

			new UsineTemplate(type, iconeVide, iconeUnTiers, iconeDeuxTiers, iconePlein,
					entree, interval, sortie);
		}
	}

	/**
	 * Cr�er le menu de Simulation
	 */
	private void ajouterMenuSimulation() {
		JMenu menuSimulation = new JMenu(MENU_SIMULATION_TITRE);
		JMenuItem menuChoisir = new JMenuItem(MENU_SIMULATION_CHOISIR);
		menuSimulation.add(menuChoisir);

		menuChoisir.addActionListener((ActionEvent e) -> {
			// Ouvrir la fen�tre de s�lection
			// TODO - R�cup�rer la bonne strat�gie de vente
			new FenetreStrategie();
		});
		add(menuSimulation);

	}

	/**
	 * Cr�er le menu Aide
	 */
	private void ajouterMenuAide() {
		JMenu menuAide = new JMenu(MENU_AIDE_TITRE);
		JMenuItem menuPropos = new JMenuItem(MENU_AIDE_PROPOS);
		menuAide.add(menuPropos);

		menuPropos.addActionListener((ActionEvent e) -> {
			JOptionPane.showMessageDialog(null,
					"<html><p>Application simulant une chaine de production d'avions.</p>" + "<br>"
							+ "<p>&copy; &nbsp; 2017 &nbsp; Ghizlane El Boussaidi</p>"
							+ "<p>&copy; &nbsp; 2017 &nbsp; Dany Boisvert</p>"
							+ "<p>&copy; &nbsp; 2017 &nbsp; Vincent Mattard</p>" + "<br>"
							+ "<p>&Eacute;cole de technologie sup&eacute;rieure</p></html>");
		});
		add(menuAide);
	}

}
