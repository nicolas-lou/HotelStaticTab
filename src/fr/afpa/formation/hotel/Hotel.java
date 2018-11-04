package fr.afpa.formation.hotel;
import java.util.Scanner;
import java.util.Arrays;
/**
 * <b>Hotel est la classe qui repr�sente l'hotel et ses fonctionnalit�s.</b>
 * <p>
 * Un hotel est caract�ris� par les attributs suivants :
 * <ul>
 * <li>Un tableau d'objet Chambre conceptualisant les chambres de l'hotel .</li>
 * <li>Un �tat, complet ou non, repr�sent� par un boolean</li>
 * <li>Un nombre total de chambres de l'hotel, un integer </li>
 * <li>L'indice de l'entr�e du tableau contenant la chambre disponnible la plus basse </li>
 * <li>Une adresse repr�sent�e par un objet de type Adresse</li>
 * </ul>
 * </p>
 * <p>
 * De plus, un Z�ro a une liste d'amis Z�ro. Le membre pourra ajouter ou enlever
 * des amis � cette liste.
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
	 * Simule une base de donn�es de chambres, objets de type Chambre
	 * @see Chambre
	 */
	private Chambre [] reservations ;
	/**
	 * Indique l'�tait complet (true) ou non (false)
	 */
	private boolean complet;
	private int nbChambresHotel ;
	private int indiceChambreVide;
	private Adresse adresseHotel;

	/**
	 * Constructeur de la classe. 
	 * Initialise:  le talbeau de chambre � 10 et instancie 10 objets Chambre, l'�tat complet � false, le nombre de chambre � 10 et instancie 
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
	 * Methode de v�rification du login et mot de passe entr�s par l'utilisateur
	 * @param login
	 * @param mdp
	 * @return un boolean renvoyant condition login ET mdp identiques � l'acc�s
	 * */
	public boolean loginMdp(String login, String mdp) {		
		final String LOGIN= "admin";
		final String MDP ="root";		
		return ((LOGIN.equals(login))&&(MDP.equals(mdp)));
	}

	/**
	 * V�rifie si le client est d�j� pr�sent dans un des attributs "nom" des occupants des chambres.	 
	 * @param str nom du client tap� au clavier par l'utilisateur
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
	 * Met � jour l'indice du tableau de la premiere chambre vide de l'attribut de l'hotel.
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
	 * Mise � jour de l'�tat de l'hotel. Complet ou non
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
	 * v�rifie si la chambre consid�r�e est disponnible. C'est � dire vide
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
	 * D�clenche une lecture clavier et v�rifie que l'entr�e est une cha�ne de char compos�e de lettre a/z
	 * Boucle tant que la condition n'est pas remplie
	 * Auteur de la m�thode: Marilena 
	 * @return temp la cha�ne v�rifi�e
	 * */
	public String verifEntreeClavier() {
		String temp="";
		Scanner in = new Scanner(System.in);
		temp = in.nextLine();
		do {
			if(!temp.matches("[a-zA-Z]+")){
				System.out.println("Entr�e invalide. Verifiez la casse de votre entr�e et recommencez: ");
				temp = in.nextLine();
			}
		}while(!temp.matches("[a-zA-Z]+"));		
		return temp;
		

	}
	/**
	 * D�clenche une lecture clavier cha�ne de char, v�rifie qu'elle ne contient que des entiers 
	 * et la transforme en integer. Boucle tant que la condition n'est pas remplie
	 * @return reponse l'integer */
	public static int verifClavierInt() {		
		Scanner in = new Scanner(System.in);
		String temp = in.nextLine();
		do {
			if(!temp.matches("0|([1-9]?[0-9])")){
				System.out.println("Entr�e invalide. La valeur ne peut �tre qu'un entier: ");
				temp = in.nextLine();
			}
		}while(!temp.matches("0|([1-9]?[0-9])"));
		int reponse = Integer.parseInt(temp);		
		return reponse;

	}

	// --- fin des methodes utilitaires ---

	// d�buts des methodes d'interface
	
	/**
	 * Lance l'affichage du menu, un switch de choix d'affichage qui boucle en reproposant l'affiche du menu apr�s chaque choix
	 * jusqu'� ce que l'utilisateur d�cide de quitter l'interface
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
				int nbChambresLibres = nombreChambres(getReservations(),null);//appel de la m�thode qui compte le nombre de chambres
				System.out.println("Il y a "+nbChambresLibres+" chambre(s) libre(s)" );}break;

			case "C":{
				int nbChambresReservees = nombreChambres(getReservations(),"res");
				System.out.println("Il y a "+nbChambresReservees+" chambre(s) r�serv�e(s)" );}break;

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
				System.out.println("A bient�t!");}break;

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
			System.out.println("A ~ Afficher l'�tat des reservations");
			System.out.println("B ~ Connaitre le nombre de chambres disponibles");
			System.out.println("C ~ Connaitre le nombre de chambres r�serv�es");		
			System.out.println("D ~ Attribuer une chambre � un nouveau client [Autorisation requise]");
			System.out.println("E ~ Enregistrer la sortie d'un client [Autorisation requise]");
			System.out.println("F ~ Connaitre le montant d'encaissement TTC du jour");
			System.out.println("Q ~ Quitter");
			
		}

	/**
	 * Affiche l'occupation des chambres de l'hotel.
	 * Indique le nom du client si occup� ou la mention "vide" si chambre dispo
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
	 * @return compteurRes nombres de chambres occup�es
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
		//en partant de la premi�re chambre
		if (nb==0){
			for (i=0;i<tailleTab;i++){
				if (tab[i].getOccupant().getNom()==null) {
					indice = i;
					break;
				}
			}
		}
		//en partant de la derni�re chambre
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
		System.out.println("Le PTTC des chambres occup�es est de: " + sommePTTC + "euros");


	}	

	/**
	 * Fonction d'attribution d'une chambre dispo � un client entrant.
	 * Demande acc�s par login, le num�ro de la chambre � attribuer, le nom du client,
	 * v�rifie qu'il n'est pas present dans une chambre et que la chambre est dispo
	 * Si les conditions sont remplies la chambre est reserv�e � son nom, l'�tat de l'hotel est mis � jour et l'indice de la
	 * premi�re chambre dispo aussi.
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
			System.out.println("Quel est le numero de la chambre � reserver");
			int numeroChambre= verifClavierInt()-1;				
			if(chambreDispo(numeroChambre)) {
				System.out.println("Quel est le nom du client");
				String nom = verifEntreeClavier();
				if(!clientExisteDeja(nom)){				
					reservations[numeroChambre].setOccupation(true);
					reservations[numeroChambre].getOccupant().setNom(nom);
					reservations[numeroChambre].getOccupant().enregistrerClient(numeroChambre);			
					System.out.println("La chambre est reserv�es !");
					updateEtatHotel();
				}else {System.out.println("Le client est d�j� present dans l'Hotel");}
			}else { System.out.println("Cette chambre est deja occup�e");}


		}else {System.out.println("Autorisation refus�e ! ");}
		updateIndiceChambreVide();
		
	}




	/**
	 * Fonction de liberation d'une chambre lors du d�part d'un client.
	 * Demande acc�s par login, le num�ro de la chambre occup�e par le client et instancie un nouvel objet chambre vide dans le tableau.
	 * Mise � jour de l'�tat de l'hotel et l'indice de la premi�re chambre vide de l'hotel
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
		}else {System.out.println("Autorisation refus�e !");}
		updateIndiceChambreVide();
		
	}
}




































