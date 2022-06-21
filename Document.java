package pack;

import java.sql.*;
import java.util.Scanner;

public class Document {
    /*private String documentname;
    private String date;
    private String storage;
    
    
    public Document(String documentname, String date, String storage){
        this.documentname = documentname;
        this.date = date;
        this.storage = storage;
    }*/
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        while(true) {
        	displayMenu();
        	int userChoice = sc.nextInt();
        	sc.nextLine();
        	while(userChoice > 9 || userChoice < 1) {
        		System.out.println("Choix Incorrect, choissisez entre 1 et 9. ");
        		System.out.println("\nChoix : ");
        		userChoice = sc.nextInt();
            	sc.nextLine();
        	}
        	switch(userChoice) {
        	case 1 : 
        		System.out.println("Nom du document : ");
        		String name = sc.nextLine();
        		System.out.println("Chemin du document : ");
        		String path = sc.nextLine();
        		displaySujet();
        		int topic = sc.nextInt();
        		sc.nextLine();
        		displayCategory();
        		int category = sc.nextInt();
        		sc.nextLine();
        		System.out.println("Tag du document : ");
        		String tag = sc.nextLine();
        		System.out.println("Voulez vous enregistrer la date du document ? ");
        		System.out.println("0. Non\n1. Oui");
        		int date = sc.nextInt();
        		sc.nextLine();
        		savedocument(name, path, category, topic, tag, date);
        		break;
        		
        	case 2 : 
        		ShowData.show(1);
        		break;
        		
        	case 3 : 
        		ShowData.show(2);
        		break;
        		
        	case 4 : 
        		ShowData.show(3);
        		break;
        		
        	case 5 :
        		ShowData.show(4);
        		break;
        		
        	case 6 : 
        		ShowData.show(5);
        		break;
        		
        	case 7 : 
        		ShowData.show(1);
        		System.out.println("\nQuel est le nom du doc que vous voulez modifier ? ");
        		System.out.println("Choix : ");
                String docname = sc.nextLine();
                System.out.println("Tapez la date et l'heure sous cette forme : YYYY-MM-DD hh-mm-ss  (2022-06-15 10:25:00)");
                String newDate = sc.nextLine();
                updateDate(docname, newDate);
                break;
        		
        	case 8 : 
        		ShowData.show(1);
        		System.out.println("\nQuel est le nom du doc que vous voulez modifier ? ");
        		System.out.println("Choix : ");
                String documentName = sc.nextLine();
                System.out.println("\nNom du tag que vous voulez ajouter :  ");
        		System.out.println("Choix : ");
                String tagName = sc.nextLine();
                addTag(documentName, tagName);
                break;
                
        	case 9 : 
        		sc.close();
        		System.exit(0);
        	}
        }
    }
    
    public static void savedocument(String documentname, String storage, int category, int topic, String tag, int date){
        String url = "jdbc:mysql://localhost:3306/gestiondocument";
        String login = "root";
        String password = "Math0623736244";
        Connection cn = null;
        
        java.util.Date date1 = null;
    	java.sql.Date sqlDate = null;
        java.sql.Timestamp sqlTime = null;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(url, login, password);
            if(date ==1 ) {
	            date1 = new java.util.Date();
	            sqlDate = new java.sql.Date(date1.getTime());
	            sqlTime = new java.sql.Timestamp(date1.getTime());
            }
            
            PreparedStatement ps;
            
            // Insertion nouveau document
            ps =cn.prepareStatement("INSERT INTO document (DocumentName, DocumentDate, StorageAddress) VALUES (?, ?, ?)");
            ps.setString(1, documentname);
            ps.setDate(2, sqlDate);
            ps.setTimestamp(2, sqlTime);
            ps.setString(3, storage);
            ps.executeUpdate();
            ps.close();
            
            //Affectation de la cat�gorie
            ps = cn.prepareStatement("INSERT INTO belongs_to (CategoryID) VALUES (?)");
            ps.setInt(1, category);
            ps.executeUpdate();
            ps.close();
            
            //Affectation du sujet
            ps = cn.prepareStatement("INSERT INTO is_about (TopicID) VALUES (?)");
            ps.setInt(1, topic);
            ps.executeUpdate();
            ps.close();
            
            //Recherche du tag dans la bdd
            int TagId = isTagExisting(tag, cn);
            if(TagId == -1) { // le Tag n'existe pas il faut le cr�er dans la bdd pour obtenir son Id 
            	//Insertion du Tag
            	ps = cn.prepareStatement("INSERT INTO tag (TagName) VALUES (?)");
                ps.setString(1, tag);
                ps.executeUpdate();
                ps.close();
            
                TagId = isTagExisting(tag, cn); //retourne le nouveau TagId cr�er
            }
            
            //Affectation du tag
            ps = cn.prepareStatement("INSERT INTO has (TagID) VALUES (?)");
            ps.setInt(1, TagId);
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    
    public static void displayMenu() {
    	System.out.println("\n\t\t--- MENU ---\n");
    	System.out.println("\t1. Ajouter un document");
    	System.out.println("\t2. Récuperer la liste des documents par catégorie");
    	System.out.println("\t3. Récupérer la liste des documents par sujet" );
    	System.out.println("\t4. Récupérer la liste des documennts par tag");
    	System.out.println("\t5. Afficher le sujet le plus fréquent");
    	System.out.println("\t6. Afficher le nombre d'occurences de chaque tag");
    	System.out.println("\t7. Modifier la date d'un document");
    	System.out.println("\t8. Ajouter un tag à un document");
    	System.out.println("\t9. Quitter\n");
    	System.out.println("Choix : ");
    }
    
    public static void displaySujet() {
    	
    	System.out.println("\n\t--- Sujet ---\n");
    	System.out.println("\t1. Base de donnees");
    	System.out.println("\t2. Anglais");
    	System.out.println("\t3. Communication");
    	System.out.println("\t4. Theorie des graphes");
    	System.out.println("\t5. Cybersecurite");
    	System.out.println("\t6. JavaScript");
    	System.out.println("\t7. HTML");
    	System.out.println("\t8. CV");
    	System.out.println("\t9. Application React Native\n");
    	System.out.println("Choix : ");
    	
    }
    
    public static void displayCategory() {
    	System.out.println("\n\t--- Catégorie --- \n");
    	System.out.println("\t1. Dev Web");
    	System.out.println("\t2. Projets EFREI");
    	System.out.println("\t3. Projets Perso");
    	System.out.println("\t4. Cours EFREI");
    	System.out.println("\t5. Documents Perso\n");
    	System.out.println("Choix : ");
    	
    }
    
    public static int isTagExisting(String tag, Connection cn) {
    	PreparedStatement ps;
        try {
			ps = cn.prepareStatement("Select * from tag");
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				String TagName = res.getString("TagName");
				int TagID = res.getInt("TagID");
				if (TagName.equals(tag)) {
					return TagID;
				}
			}
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
    }
    
    public static void updateDate(String docname, String newDate){
        String url = "jdbc:mysql://localhost:3306/gestiondocument";
        String login = "root";
        String password = "Math0623736244";
        Connection cn = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(url, login, password);
            PreparedStatement st =cn.prepareStatement("Update Document SET DocumentDate = (?) WHERE documentName = (?) ;");
            st.setString(1, newDate);
            st.setString(2, docname);
            st.executeUpdate();
            st.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static void addTag(String documentName, String tagName) {
    	String url = "jdbc:mysql://localhost:3306/gestiondocument";
        String login = "root";
        String password = "Math0623736244";
        Connection cn = null;
        
        PreparedStatement ps;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(url, login, password);
            
            // Obtention de l'ID du doc pour lequel on veut ajouter un tag
            int docID = getDocumentIdByName(documentName, cn);
            
            
          //Recherche du tag dans la bdd
            int TagId = isTagExisting(tagName, cn);
            if(TagId == -1) { // le Tag n'existe pas il faut le cr�er dans la bdd pour obtenir son Id 
            	//Insertion du Tag
            	ps = cn.prepareStatement("INSERT INTO tag (TagName) VALUES (?)");
                ps.setString(1, tagName);
                ps.executeUpdate();
                ps.close();
            
                TagId = isTagExisting(tagName, cn); //retourne le nouveau TagId cr�er
            }
            
            //Affectation du tag
            ps = cn.prepareStatement("INSERT INTO has (TagID, DocumentID) VALUES (?, ?)");
            ps.setInt(1, TagId);
            ps.setInt(2, docID);
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    
    public static int getDocumentIdByName(String docName, Connection cn) {
    	try {
            PreparedStatement st =cn.prepareStatement("SELECT DocumentID, DocumentName FROM document where documentName=(?)");
            
            st.setString(1, docName);
            ResultSet res = st.executeQuery();
            int DocumentID = -1;
            while (res.next()) {
                String DocumentName = res.getString("DocumentName");
                DocumentID = res.getInt("DocumentID");

                System.out.print("\tNom du document: " + DocumentName+ "\n");
                System.out.print("\tID Document " + DocumentID+ "\n");

            }
            return DocumentID;
            
	    }catch(Exception e) {
	    	System.out.println(e);
	    }
    	return -1;
    }
}

