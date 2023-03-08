package simulation;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import assets.Chemin;
import assets.Entrepot;
import assets.Inventaire;
import assets.Usine;
import assets.UsineType;
import observateur.ObservateurEntrepot;
import observateur.Sujet;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.events.EntityReference;

public class LecteurXML {

    public LecteurXML() {
    }

    public void lecture(Document doc) {
        try {

            doc.getDocumentElement().normalize();

            NodeList metadonnesBalises = doc.getElementsByTagName("metadonnees");

            for (int i = 0; i < metadonnesBalises.getLength(); i++) {
                Node metadonnesBalise = metadonnesBalises.item(i);
                templatesCreation(metadonnesBalise);
            }

            NodeList simulationBalises = doc.getElementsByTagName("simulation");

            System.out.println("----------------------------");

            for (int i = 0; i < simulationBalises.getLength(); i++) {
                Node simulationBalise = simulationBalises.item(i);
                simulationCreation(simulationBalise);
            }

            for (Usine usine : Usine.data) {
                System.out.println(usine);
            }

            System.out.println("----------------------------");
            // use SAXException instead of Exception
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void simulationCreation(Node simulationBalise) {
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

                UsineType usine = UsineType.getUsineTypeByType(type);

                if (usine.getType().equalsIgnoreCase("entrepot")) {
                    new Entrepot(usine, id, x, y);
                } else {
                    new Usine(usine, id, x, y);
                }
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

        List<Entrepot> entrepots = Usine.getEntrepots();
        if (entrepots.size() > 0) {
            Entrepot entrepot = entrepots.get(0);
            for (Usine usine : Usine.data) {
                if (usine instanceof Entrepot)
                    continue;

                usine.setObservateur(new ObservateurEntrepot(entrepot.getSujet()));
            }
        }

    }

    private void templatesCreation(Node metadonnesBalise) {
        NodeList usines = metadonnesBalise.getChildNodes();

        for (int j = 0; j < usines.getLength(); j++) {
            Node usine = usines.item(j);

            if (usine.getNodeType() != Node.ELEMENT_NODE)
                continue;

            String type = ((Element) usine).getAttribute("type");

            int interval = 0;
            String iconeVide = "";
            String iconePlein = "";
            String iconeUnTiers = "";
            String iconeDeuxTiers = "";
            List<Inventaire> inventaire = new ArrayList<Inventaire>();
            String sortie = "";

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
                    String composantType = ((Element) node).getAttribute("type");
                    String capacite = ((Element) node).getAttribute("capacite");
                    String quantite = ((Element) node).getAttribute("quantite");
                    String quantiteRequis = capacite == null || capacite.isEmpty() ? quantite : capacite;
                    int composantCapacite = Integer.parseInt((quantiteRequis));
                    Inventaire item = new Inventaire(composantType, composantCapacite);
                    inventaire.add(item);
                }

                if (node.getNodeName().contains("sortie")) {
                    sortie = ((Element) node).getAttribute("type");
                }
            }

            new UsineType(type, iconeVide, iconeUnTiers, iconeDeuxTiers, iconePlein, interval, inventaire, sortie);
        }
    }
}
