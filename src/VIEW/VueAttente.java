package VIEW;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import JOUEUR.JoueurDIXIT;
import JOUEUR.ServeurDIXIT;
import JOUEUR.ThreadPartie;

public class VueAttente extends JPanel  {
	private JLabel labelNombreJoueur;
	private JFrame fenetre;
	public VueAttente(JFrame fenetre, JoueurDIXIT j) {
		
		
		this.fenetre = fenetre;
		this.setLayout(new BorderLayout(250, 220));
		this.setBackground(new Color(222, 222, 222));
		JLabel compteurNombreJoueur = new JLabel("0");
		JPanel panelCentre = new JPanel(new GridLayout(4, 2));
		JPanel panelGauche = new JPanel();
		JPanel panelDroite = new JPanel();
		JPanel panelHaut = new JPanel();
		JPanel panelBas = new JPanel();

		panelGauche.setSize(100, 400);
		panelDroite.setSize(100, 400);
		panelHaut.setSize(600, 200);
		panelBas.setSize(600, 200);

		panelGauche.setBackground(new Color(222, 222, 222));
		panelDroite.setBackground(new Color(222, 222, 222));
		 labelNombreJoueur = new JLabel("attente des joueurs : ");
		 panelCentre.add(compteurNombreJoueur);
		 panelCentre.add(labelNombreJoueur);
		 compteurNombreJoueur.setText( j.getThread2().getMessage());
		this.add(panelCentre, BorderLayout.CENTER);
		this.setVisible(true);
		
		if (j.pretJouer){
			j.partie();
		}
		
		
	}
	
	



	

}
