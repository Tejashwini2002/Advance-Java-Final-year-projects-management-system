package Projpack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class FYproject {
    public static void createDatabase() {
        try {
           String url = "jdbc:mysql://localhost:3306/";
            String username = "root";
            String password = "Tanuvinnu@1234";
           Connection conn = DriverManager.getConnection(url, username, password);
           Statement stm = conn.createStatement();
            System.out.println("Connected Successfully");
		 // Check if the 'finalyeatprojectDB' database exists
	        ResultSet resultSet = stm.executeQuery("SHOW DATABASES LIKE 'finalyearprojectDB' ");
	        if (!resultSet.next()) {
	            // If ' finalyeatprojectDB ' database doesn't exist, create it
	            String createDbQuery = "CREATE DATABASE IF NOT EXISTS finalyearprojectDB ";
	            stm.executeUpdate(createDbQuery);
	            System.out.println("'finalyearprojectDB' database created successfully.");
	        } else {
	            System.out.println("'finalyearprojectDB' database already exists.");
	        }

	        // Use the ' finalyeatprojectDB ' database
	        conn.setCatalog("finalyearprojectDB");           
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    	public static void createTables() {
    	    try {
    	        Connection conn = DBconnect ();
    	        Statement stm = conn.createStatement();
    	        System.out.println("Connected Successfully");

    	            // Check if the ' ProjectDetails ' table exists
    	        ResultSet resultSet = stm.executeQuery("SHOW TABLES LIKE 'ProjectDetails'");
    	        if (!resultSet.next()) {
    	            // If ' ProjectDetails ' table doesn't exist, create it
    	            String createProjectDetailsTableQuery = "CREATE TABLE ProjectDetails (teamId varchar(16) primary key, projectname varchar(55),leadername varchar(15) , teammember1 varchar(25), teammember2 varchar(25) ,teammember3 varchar(25) ,year varchar(20), researchpaperstatus varchar(50) ) ";
    	            stm.executeUpdate(createProjectDetailsTableQuery);
    	            System.out.println("' ProjectDetails ' table created successfully.");
    	        } else {
    	            System.out.println("' ProjectDetails ' table already exists.");
    	        }
    	        conn.close();
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
    	}

    public static boolean authenticateproject(String teamid) {
        try {
            Connection conn = DBconnect();
            System.out.println("Connected Successfully");
            String query = "select teamID from ProjectDetails where teamID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1,teamid);
            ResultSet rs = pstmt.executeQuery();

            String searchedteamid = null;
            while (rs.next()) {
                searchedteamid = rs.getString(1);
            }
            conn.close();
            return searchedteamid != null && searchedteamid.equals(teamid);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
   
    static Connection conn = null;
    public static Connection DBconnect(){
        try{
            String url = "jdbc:mysql://localhost:3306/";
            String db = " finalyearprojectDB "; // database name
            String username = "root";
            String password = "Tanuvinnu@1234";
            conn = DriverManager.getConnection(url+db, username, password);
        }catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }   
    public static void searchbyprojectname(String projectName) {
        try {
            Connection conn = DBconnect();
            System.out.println("Connected Successfully");
            String query = "SELECT * FROM  projectDetails WHERE projectname = ?";
                          
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, projectName);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("No project Details found for project: " + projectName);
            } else {
                    System.out.printf("%-10s | %-50s | %-20s | %-20s |%-20s |%-20s |%-20s | %-20s%n",
                           "teamId","projectname","leadername", "teammember1"," teammember2", "teammember3","year","researchpaperstatus");

                System.out.println("-----------------------------------------------------------------------------");

                while (rs.next()) {
                    String teamId = rs.getString("teamId");
                    String leadername = rs.getString("leadername");
                    String projectname = rs.getString("projectname");
                    String teammember1= rs.getString("teammember1");
                    String teammember2 = rs.getString("teammember2");
                    String teammember3 = rs.getString("teammember3");
                    String year = rs.getString("year");
                    String  researchpaperstatus = rs.getString("researchpaperstatus");
                    System.out.printf("%-10s | %-50s | %-20s | %-20s |%-20s  |%-20s | %-20s | %-20s%n",
                            teamId,projectname,leadername, teammember1, teammember2, teammember3 ,year,researchpaperstatus);
                }
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void searchbystudentname(String studentname) {
        try {
            Connection conn = DBconnect();
            System.out.println("Connected Successfully");
            String query = "SELECT * FROM projectDetails WHERE leadername = ? OR teammember1 = ? OR teammember2 = ? OR teammember3 = ?";
                           
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, studentname);
            pstmt.setString(2, studentname);
            pstmt.setString(3, studentname);
            pstmt.setString(4, studentname);

            ResultSet rs = pstmt.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("No project Details found in which either of the team member is with name: " +studentname);
            } else {
            	System.out.printf("%-10s | %-50s | %-20s | %-20s |%-20s |%-20s |%-20s | %-20s%n",
                        "teamId","projectname","leadername", "teammember1"," teammember2", "teammember3","year","researchpaperstatus");

                System.out.println("-----------------------------------------------------------------------------");

                while (rs.next()) {
                	String teamId = rs.getString("teamId");
                    String leadername = rs.getString("leadername");
                    String projectname = rs.getString("projectname");
                    String teammember1= rs.getString("teammember1");
                    String teammember2 = rs.getString("teammember2");
                    String teammember3 = rs.getString("teammember3");
                    String year = rs.getString("year");
                    String  researchpaperstatus = rs.getString("researchpaperstatus");
                    System.out.printf("%-10s | %-50s | %-20s | %-20s |%-20s  |%-20s | %-20s | %-20s%n",teamId,projectname,leadername, teammember1, teammember2, teammember3 ,year,researchpaperstatus);
                            
                }
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void viewallprojects () {
        try {
            Connection conn = DBconnect();
            System.out.println("Connected Successfully");
            String query = "SELECT * FROM projectDetails ";               
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("No project Details found");
            } else {
            	System.out.printf("%-10s | %-50s | %-20s | %-20s |%-20s |%-20s |%-20s | %-20s%n",
                        "teamId","projectname","leadername", "teammember1"," teammember2", "teammember3","year","researchpaperstatus");

                System.out.println("-----------------------------------------------------------------------------");

                while (rs.next()) {
                	String teamId = rs.getString("teamId");
                    String leadername = rs.getString("leadername");
                    String projectname = rs.getString("projectname");
                    String teammember1= rs.getString("teammember1");
                    String teammember2 = rs.getString("teammember2");
                    String teammember3 = rs.getString("teammember3");
                    String year = rs.getString("year");
                    String  researchpaperstatus = rs.getString("researchpaperstatus");
                    System.out.printf("%-10s | %-50s | %-20s | %-20s |%-20s  |%-20s | %-20s | %-20s%n",teamId,projectname,leadername, teammember1, teammember2, teammember3 ,year,researchpaperstatus);
                }
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}      





