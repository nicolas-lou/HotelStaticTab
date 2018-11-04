package fr.afpa.formation.hotel;
import java.util.Scanner;
import java.util.Arrays;
/**
 * <b>Hotel est la classe qui représente l'hotel et ses fonctionnalités.</b>
 * <p>
 * Un hotel est caractérisé par les attributs suivants :
 * <ul>
 * <li>Un tableau d'objet Chambre conceptualisant les chambres de l'hotel .</li>
 * <li>Un état, complet ou non, représenté par un boolean</li>
 * <li>Un nombre total de chambres de l'hotel, un integer </li>
 * <li>L'indice de l'entrée du tableau contenant la chambre disponnible la plus basse </li>
 * <li>Une adresse représentée par un objet de type Adresse</li>
 * </ul>
 * </p>
 * <p>
 * De plus, un Zéro a une liste d'amis Zéro. Le membre pourra ajouter ou enlever
 * des amis à cette liste.
 * </p>
 * 
 * @see Chambre
 * @see Client
 * @see Adresse
 * 
 * @author Nicolas L
 * @version 4.0
 */
public class Hotel {

	/**
	 * Simule une base de données de chambres, objets de type Chambre
	 * @see Chambre
	 */
	private Chambre [] reservations ;
	/**
	 * Indique l'était complet (true) ou non (false)
	 */
	private boolean complet;
	private int nbChambresHotel ;
	private int indiceChambreVide;
	private Adresse adresseHotel;

	/**
	 * Constructeur de la classe. 
	 * Initialise:  le talbeau de chambre à 10 et instancie 10 objets Chambre, l'état complet à false, le nombre de chambre à 10 et instancie 
	 * un nouvel objet de type Adresse pour l'initialisation de l'adresse de l'hotel
	 *  */
	public Hotel() {
		super();
		reservations = new Chambre[10];
		for(int i = 0; i<reservations.length ; ++i) {
			reservations[i] = new Chambre();
		}
		this.complet = false;
		this.nbChambresHotel = 10;
		this.indiceChambreVide = 0;
		this.adresseHotel = new Adresse();
		afficherInterface();
	}

	// debut setters getters 
	public Chambre[] getReservations() {
		return reservations;
	}


	public void setReservations(Chambre[] reservations) {
		this.reservations = reservations;
	}


	public boolean isComplet() {
		return complet;
	}


	public void setComplet(boolean complet) {
		this.complet = complet;
	}
	// fin setters getters

	@Override
	public String toString() {
		return "Hotel [reservations=" + Arrays.toString(reservations) + ", complet=" + complet + "]";
	}


	// Debut des methodes utilitaires de la classe

	/**
	 * Methode de vérification du login et mot de passe entrés par l'utilisateur
	 * @param login
	 * @param mdp
	 * @return un boolean renvoyant condition login ET mdp identiques à l'accès
	 * */
	public boolean loginMdp(String login, String mdp) {		
		final String LOGIN= "admin";
		final String MDP ="root";		
		return ((LOGIN.equals(login))&&(MDP.equals(mdp)));
	}

	/**
	 * Vérifie si le client est déjà présent dans un des attributs "nom" des occupants des chambres.	 
	 * @param str nom du client tapé au clavier par l'utilisateur
	 * @return un boolean true si il y est present.
	 * @see Chambre#getOccupant()
	 * @see Client#getNom()
	 */
	public boolean clientExisteDeja(String str) {
		boolean temp=false;
		if (str!=null) {
			for (int i=0; i<reservations.length;i++) {
				if (str.equalsIgnoreCase(reservations[i].getOccupant().getNom())) { 
					temp = true ; 
				}
			}
		}
		return temp;
	}

	/**
	 * Met à jour l'indice du tableau de la premiere chambre vide de l'attribut de l'hotel.
	 * @see Hotel#indiceChambreVide
	 * @see Hotel#getReservations()
	 * */
	public void updateIndiceChambreVide() {
		if(!complet) {
			for(int i=0; i<reservations.length; ++i) {
				if(reservations[i].getOccupation()==true) {
					indiceChambreVide = i;
				}
			}
		}else {indiceChambreVide=11;}
	}

	/**
	 * Mise à jour de l'état de l'hotel. Complet ou non
	 * @see Hotel#getReservations()
	 * @see Hotel#complet
	 * */
	public void updateEtatHotel() {
		boolean reponse = true;
		for(int i=0;i< reservations.length ;++i) {
			if(reservations[i].getOccupation()==false) {
				reponse = false;
			}
		}
		complet = reponse;
	}

	/**
	 * vérifie si la chambre considérée est disponnible. C'est à dire vide
	 * @param numeroChambre l'indice du tableau de la chambre
	 * @return la reponse sous forme de boolean
	 * */
	public boolean chambreDispo(int numeroChambre) {
		boolean reponse= true;
		if(reservations[numeroChambre].getOccupation()) {
			reponse = false;
		}

		return reponse;
	}

	/**
	 * Déclenche une lecture clavier et vérifie que l'entrée est une chaîne de char composée de lettre a/z
	 * Boucle tant que la condition n'est pas remplie
	 * Auteur de la méthode: Marilena 
	 * @return temp la chaîne vérifiée
	 * */
	public String verifEntreeClavier() {
		String temp="";
		Scanner in = new Scanner(System.in);
		temp = in.nextLine();
		do {
			if(!temp.matches("[a-zA-Z]+")){
				System.out.println("Entrée invalide. Verifiez la casse de votre entrée et recommencez: ");
				temp = in.nextLine();
			}
		}while(!temp.matches("[a-zA-Z]+"));		
		return temp;
		

	}
	/**
	 * Déclenche une lecture clavier chaîne de char, vérifie qu'elle ne contient que des entiers 
	 * et la transforme en integer. Boucle tant que la condition n'est pas remplie
	 * @return reponse l'integer */
	public static int verifClavierInt() {		
		Scanner in = new Scanner(System.in);
		String temp = in.nextLine();
		do {
			if(!temp.matches("0|([1-9]?[0-9])")){
				System.out.println("Entrée invalide. La valeur ne peut être qu'un entier: ");
				temp = in.nextLine();
			}
		}while(!temp.matches("0|([1-9]?[0-9])"));
		int reponse = Integer.parseInt(temp);		
		return reponse;

	}

	// --- fin des methodes utilitaires ---

	// débuts des methodes d'interface
	
	/**
	 * Lance l'affichage du menu, un switch de choix d'affichage qui boucle en reproposant l'affiche du menu après chaque choix
	 * jusqu'à ce que l'utilisateur décide de quitter l'interface
	 * @see Hotel#affichMenu()
	 * @see Hotel#afficheEtat(Chambre[])
	 * @see Hotel#nombreChambres(Chambre[], String)
	 * @see Hotel#attribuerChambre()
	 * @see Hotel#libererChambre()
	 * @see Hotel#afficherPttc()
	 * */
	public void afficherInterface() {
		Scanner in = new Scanner(System.in);
		String choix="";
		do {
			affichMenu();
			choix  = in.nextLine();
			switch (choix)
			{			
			case "A":{
				afficheEtat(getReservations());}break;

			case "B":{
				int nbChambresLibres = nombreChambres(getReservations(),null);//appel de la méthode qui compte le nombre de chambres
				System.out.println("Il y a "+nbChambresLibres+" chambre(s) libre(s)" );}break;

			case "C":{
				int nbChambresReservees = nombreChambres(getReservations(),"res");
				System.out.println("Il y a "+nbChambresReservees+" chambre(s) réservée(s)" );}break;

			case "D":{ 
				if(complet) {
					System.out.println("L'hotel est complet !");
				}else {attribuerChambre();}				

			}break;

			case "E":{ libererChambre();
			}break;

			case "F":{ afficherPttc();
			}break;

			case "Q":{
				System.out.println("A bientôt!");}break;

			default:
			{
				System.out.println("Votre choix n'est pas valide!");
			}
			}  

		} while (!choix.equalsIgnoreCase("Q"));
		
		

	}
	
	
		/**
		 * Affiche le menu sur la console
		 * */
		public void affichMenu() {
			System.out.println();
			System.out.println("Gestion de l'hotel: ");
			System.out.println("--------------------- ");
			System.out.println("Selectionnez une option de menu");
			System.out.println();
			System.out.println("A ~ Afficher l'état des reservations");
			System.out.println("B ~ Connaitre le nombre de chambres disponibles");
			System.out.println("C ~ Connaitre le nombre de chambres réservées");		
			System.out.println("D ~ Attribuer une chambre à un nouveau client [Autorisation requise]");
			System.out.println("E ~ Enregistrer la sortie d'un client [Autorisation requise]");
			System.out.println("F ~ Connaitre le montant d'encaissement TTC du jour");
			System.out.println("Q ~ Quitter");
			
		}

	/**
	 * Affiche l'occupation des chambres de l'hotel.
	 * Indique le nom du client si occupé ou la mention "vide" si chambre dispo
	 * @param tab le tableau des chambres de l'hotel
	 * */
	public void afficheEtat(Chambre tab[]) {

		for (int i=0;i<(tab.length);i++) {
			if (tab[i].getOccupant().getNom()=="") {				
				System.out.print("Room "+ (i+1) + " -> [vide] ");
			}
			else System.out.print("Room"+ (i+1) +" -> [ "+ tab[i].getOccupant().getNom() +" ]  ");

		}
		System.out.println("");
	}

	/**
	 * calcul le nombre de chambres disponnibles et indisponnible
	 * @param tab le talbeau des chambres de l'hotel
	 * @param str si la variable est null: retourne le nombre de chambres dispo
	 * @return compteurLibres nombres de chambres dispos
	 * @return compteurRes nombres de chambres occupées
	 * */
	public int nombreChambres(Chambre[] tab, String str) {

		int compteurLibres=0;
		int compteurRes=0;
		int i=0;
		for (i=0;i<tab.length;i++) {
			if (!tab[i].getOccupation()) {
				compteurLibres=compteurLibres+1;
			}
			else compteurRes=compteurRes+1;
		}
		if (str==null) {
			return compteurLibres;
		}
		else return compteurRes;
	}


	public int premChambreLibre(Chambre[] tab, int nb) {
		int i = 0;
		int indice = 0;
		int tailleTab = tab.length;
		//en partant de la première chambre
		if (nb==0){
			for (i=0;i<tailleTab;i++){
				if (tab[i].getOccupant().getNom()==null) {
					indice = i;
					break;
				}
			}
		}
		//en partant de la dernière chambre
		else {
			for (i=tailleTab-1;i>=0;i--){
				if (tab[i].getOccupant().getNom()==null) {
					indice = i;
					break;
				}
			}
		}

		return indice;
	}

	

	public void afficherPttc() {
		double sommePTTC=0;
		for(int i=0; i<reservations.length; ++i) {
			if(reservations[i].getOccupation())
				sommePTTC = sommePTTC + reservations[i].getPrix();
		}
		System.out.println("Le PTTC des chambres occupées est de: " + sommePTTC + "euros");


	}	

	/**
	 * Fonction d'attribution d'une chambre dispo à un client entrant.
	 * Demande accès par login, le numéro de la chambre à attribuer, le nom du client,
	 * vérifie qu'il n'est pas present dans une chambre et que la chambre est dispo
	 * Si les conditions sont remplies la chambre est reservée à son nom, l'état de l'hotel est mis à jour et l'indice de la
	 * première chambre dispo aussi.
	 * @see Hotel#loginMdp(String, String)
	 * @see Hotel#chambreDispo(int)
	 * @see Hotel#updateEtatHotel()
	 * @see Hotel#updateIndiceChambreVide()
	 * */
	public void attribuerChambre() {
		Scanner in = new Scanner(System.in);		
		System.out.println("--- Autorisation requise ---");
		System.out.println("login:");
		String userLogin = in.next();
		System.out.println("Mot de passe");
		String userMdp= in.next();		
		if (loginMdp(userLogin,userMdp)) {
			System.out.println("Quel est le numero de la chambre à reserver");
			int numeroChambre= verifClavierInt()-1;				
			if(chambreDispo(numeroChambre)) {
				System.out.println("Quel est le nom du client");
				String nom = verifEntreeClavier();
				if(!clientExisteDeja(nom)){				
					reservations[numeroChambre].setOccupation(true);
					reservations[numeroChambre].getOccupant().setNom(nom);
					reservations[numeroChambre].getOccupant().enregistrerClient(numeroChambre);			
					System.out.println("La chambre est reservées !");
					updateEtatHotel();
				}else {System.out.println("Le client est déjà present dans l'Hotel");}
			}else { System.out.println("Cette chambre est deja occupée");}


		}else {System.out.println("Autorisation refusée ! ");}
		updateIndiceChambreVide();
		
	}




	/**
	 * Fonction de liberation d'une chambre lors du départ d'un client.
	 * Demande accès par login, le numéro de la chambre occupée par le client et instancie un nouvel objet chambre vide dans le tableau.
	 * Mise à jour de l'état de l'hotel et l'indice de la première chambre vide de l'hotel
	 * @see Hotel#loginMdp(String, String)
	 * @see Hotel#indiceChambreVide
	 * @see Hotel#complet
	 * @see Hotel#updateEtatHotel()
	 * @see Hotel#updateIndiceChambreVide()
	 * */
	public void libererChambre() {
		Scanner in = new Scanner(System.in);
		System.out.println("--- Autorisation requise ---");
		System.out.println("login:");
		String userLogin = in.next();
		System.out.println("Mot de passe");
		String userMdp= in.next();		
		if (loginMdp(userLogin,userMdp)) {
			System.out.println("Quel est le numero de la chambre du client sortant ?");
			int numeroChambre= verifClavierInt() -1;
			reservations[numeroChambre] = new Chambre();
			System.out.println("La chambre est maintenant libre !");
			updateEtatHotel();
		}else {System.out.println("Autorisation refusée !");}
		updateIndiceChambreVide();
		
	}
}




































