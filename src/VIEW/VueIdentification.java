package VIEW;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import JOUEUR.*;


public class VueIdentification extends JPanel implements ActionListener {

	private JFrame fenetre;
	private JLabel login;
	private JLabel mdp;
	private JTextField loginText;
	private JPasswordField mdpText;
	private JButton valider;
	private JButton inscription;
	private boolean ok;

	public VueIdentification(JFrame fenetre) {     

		this.fenetre = fenetre;
		this.setLayout(new BorderLayout(250, 220));
		this.setBackground(new Color(222, 222, 222));

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

		login = new JLabel("LOGIN : ");
		mdp = new JLabel("MOT DE PASSE : ");
		loginText = new JTextField();
		mdpText = new JPasswordField();
		loginText.setColumns(10);
		mdpText.setColumns(10);
		valider = new JButton("VALIDER");
		inscription = new JButton("S'INSCRIRE");

		panelCentre.add(new JLabel("Identification :"));
		panelCentre.add(new JLabel());
		panelCentre.add(login);
		panelCentre.add(loginText);
		panelCentre.add(mdp);
		panelCentre.add(mdpText);
		panelCentre.add(inscription);
		panelCentre.add(valider);

		this.add(panelGauche, BorderLayout.WEST);
		this.add(panelDroite, BorderLayout.EAST);
		this.add(panelHaut, BorderLayout.NORTH);
		this.add(panelBas, BorderLayout.SOUTH);
		this.add(panelCentre, BorderLayout.CENTER);

		valider.addActionListener(this);
		inscription.addActionListener(this);

	}

	public boolean estInscrit(String login, char[] password) {
		ok = false;
		String mdp = "";
		for (int i = 0; i < password.length; i++) {
			mdp += password[i];
		}
		BufferedReader lecteurAvecBuffer = null;
		String ligne;
		try {
			lecteurAvecBuffer = new BufferedReader(new FileReader("file.txt"));
			while ((ligne = lecteurAvecBuffer.readLine()) != null) {
				if (ligne.equals(login + "/" + mdp)) {
					ok = true;
				}
			}
			lecteurAvecBuffer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ok;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// Verif base de donnee LOGIN ET MDP

		// ETUDIANT etudiant = UTILISATEUR(BASE DE DONNEE)....
		if (arg0.getSource() == valider) {
			if(estInscrit(loginText.getText(), mdpText.getPassword())){
				System.out.println("OK");
				JoueurDIXIT j = null;
	// modification du joueur null 
//				while(j == null)
//		    	{
					try {
						j = new JoueurDIXIT(loginText.getText());
						System.out.println("Connexion effectu�e");
						j.initialisation();
					} catch (IOException e) {
						System.out.println("Connexion refus�e : Serveur non ouvert");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//		    	}

//				j.partie();
					fenetre.getContentPane().removeAll();
				VueAttente vueAttente = new VueAttente(fenetre, j);
				fenetre.setContentPane(vueAttente);
			
				fenetre.getContentPane().repaint();
				fenetre.getContentPane().revalidate();
			
			}
		}
		if (arg0.getSource() == inscription) {
				
		}

	}

}
