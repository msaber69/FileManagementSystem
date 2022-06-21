package pack;

import com.sun.jdi.IntegerValue;

import java.sql.*;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class ShowData {
    public static void show(int userChoice){
        Scanner sc = new Scanner(System.in);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestiondocument", "root", "Math0623736244");
            Statement stmt = conn.createStatement();
            
            String sql = "";
            ResultSet res;
            int n = 0;
            int y = 0;
            
            switch(userChoice) {
            	case 1 : 
            		sql = "SELECT COUNT(*) FROM category";
                    res = stmt.executeQuery(sql);
                    while(res.next()) {
                    	n = res.getInt(1);}
        			for(int i = 1; i<= n; i++) {
                        sql = "SELECT DocumentName, DocumentDate, StorageAddress, CategoryName FROM Document INNER JOIN belongs_to ON document.DocumentID = belongs_to.DocumentID inner join category on category.categoryID = belongs_to.categoryID WHERE category.CategoryID = " + i;
                    	res = stmt.executeQuery(sql);
                    	y = 0;
                        while (res.next()) {
        	                String DocumentName = res.getString("DocumentName");
        	                String DocumentDate = res.getString("DocumentDate");
        	                String StorageAddress = res.getString("StorageAddress");
        	                if(y++==0) {
        	                String CategoryName = res.getString("CategoryName");
        	                System.out.println("\n\t--- " + "Catégorie : " + CategoryName + " ---");
        	                }
        	                System.out.print("\n\tNom du document: " + DocumentName+ "\n");
        	                System.out.print("\tDate de création: " + DocumentDate+ "\n");
        	                System.out.print("\tAdresse de stockage: " + StorageAddress+ "\n");
        	                
                        }
                    }
        			break;
            	case 2 : 
            		sql = "SELECT COUNT(*) FROM topic";
                     res = stmt.executeQuery(sql);
                    while(res.next()) {
                    	n = res.getInt(1);}
        			for(int i = 1; i<= n; i++) {
                        sql = "SELECT * FROM Document inner join is_about on is_about.documentID = document.documentID inner join topic on topic.topicID = is_about.topicID where topic.topicID =" + i;
                    	res = stmt.executeQuery(sql);
                    	y = 0;
                        while (res.next()) {
        	                String DocumentName = res.getString("DocumentName");
        	                String DocumentDate = res.getString("DocumentDate");
        	                String StorageAddress = res.getString("StorageAddress");
        	                if(y++==0) {
        	                	String TopicName = res.getString("TopicName");
        	                	System.out.println("\n\t--- " + "Sujet : " + TopicName + " ---");
        	                }
        	                System.out.print("\n\tNom du document: " + DocumentName+ "\n");
        	                System.out.print("\tDate de cr�ation: " + DocumentDate+ "\n");
        	                System.out.print("\tAdresse de stockage: " + StorageAddress+ "\n");
                        }
                    }
        			break;
            	case 3 : 
            		sql = "SELECT COUNT(*) FROM tag";
                    res = stmt.executeQuery(sql);
                    
                   while(res.next()) {
                   	n = res.getInt(1);
	       			for(int i = 1; i<= n; i++) {
	                       sql = "SELECT * FROM Document \r\n"
	                       		+ "inner join has on has.documentID = document.documentID\r\n"
	                       		+ "inner join tag on tag.tagID = has.tagID where tag.tagID  =" + i;
	                       		
	                   	res = stmt.executeQuery(sql);
	                   	y = 0;
	                       while (res.next()) {
	       	                String DocumentName = res.getString("DocumentName");
	       	                String DocumentDate = res.getString("DocumentDate");
	       	                String StorageAddress = res.getString("StorageAddress");
	       	                if(y++==0) {
	       	                	String TagName = res.getString("TagName");
	       	                	System.out.println("\n\t--- " + "Tag : " + TagName + " ---");
	       	                }
	       	                System.out.print("\n\tNom du document: " + DocumentName+ "\n");
	       	                System.out.print("\tDate de création: " + DocumentDate+ "\n");
	       	                System.out.print("\tAdresse de stockage: " + StorageAddress+ "\n");
	                       }
	                   }
       			break;
                   }
                   break;
                   
            	case 4: 
            		sql = " select count(*) as count, topic.topicID, topicName from is_about" +
            		" inner join topic on topic.topicID = is_about.topicID" +
            		" group by topicID;";
            		res = stmt.executeQuery(sql);
            		while (res.next()) {
    	                String TopicName = res.getString("TopicName");
    	                String Count = res.getString("count");
    	                System.out.print("\n\tNom du sujet: " + TopicName+ "\n");
    	                System.out.print("\n\tNombre de document: " + Count+ "\n");
    	                break;
                    }
            	case 5 : 
            		sql = "select count(*) as count, tag.tagName from has" +
            		" inner join tag on tag.tagID = has.tagID" +
            		" group by tag.tagName" +
            		" order by count DESC";
            		res = stmt.executeQuery(sql);
            		while (res.next()) {
    	                String TagName = res.getString("TagName");
    	                String Count = res.getString("count");
    	                System.out.print("\n\tTag: " + TagName+ "");
    	                System.out.print("\n\tNombre de document: " + Count+ "\n");
    	                
                    }
            		
            }
            
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}