package Projpack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
public class Faculty{
	public static void addDetails(String teamID,String projectname,String leadername,String teammember1,String teammember2,String teammember3,String year,String rpstatus)
	{
		try {
			Connection conn = FYproject.DBconnect();
			System.out.println("Connected Successfully");
			// Check if the project already exists
			if(FYproject.authenticateproject(teamID)) {
				System.out.println("Project with this ID already exists. Cannot add again.");
				conn.close();
				return;
			}
			// Add the project
			String addProjectQuery = "INSERT INTO ProjectDetails(teamID,projectname,leadername,teammember1,teammember2,teammember3,year,researchpaperstatus) VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement addProjectStmt = conn.prepareStatement(addProjectQuery);
			addProjectStmt.setString(1,teamID);
			addProjectStmt.setString(2,projectname);
			addProjectStmt.setString(3,leadername);
			addProjectStmt.setString(4,teammember1);
			addProjectStmt.setString(5,teammember2);
			addProjectStmt.setString(6,teammember3);
			addProjectStmt.setString(7,year);
			addProjectStmt.setString(8,rpstatus);
			addProjectStmt.executeUpdate();
			System.out.println("Project added successfully");
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteProject(String teamID) {
		try {
			Connection conn = FYproject.DBconnect();
			System.out.println("Connected Successfully");
			// Check if the project already exists
			if(FYproject.authenticateproject(teamID)) {
				System.out.println("Project with this ID  exists. Can be deleted.");
			    // Delete a project
				String deleteProjectQuery = "DELETE FROM projectDetails WHERE teamID = ?";
				PreparedStatement deleteProjectStmt = conn.prepareStatement(deleteProjectQuery);
				deleteProjectStmt.setString(1,teamID);
				deleteProjectStmt.executeUpdate();
				System.out.println("Project deleted Successfully.");
				conn.close();
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void update(String researchpaperstatus,String teamID) {
		try {
			Connection conn = FYproject.DBconnect();
			System.out.println("Connected Successfully");
			
			// Check if the project already exists
			if(FYproject.authenticateproject(teamID)) {
				System.out.println("project with this ID exists. Can be updated.");
				
			    // Update the research paper status of the project
		        String updateProjectQuery = "UPDATE projectDetails SET researchpaperstatus = ? WHERE teamID = ?";
		        PreparedStatement updateProjectStmt = conn.prepareStatement(updateProjectQuery);
		        updateProjectStmt.setString(1,researchpaperstatus);
		        updateProjectStmt.setString(2,teamID);
		        updateProjectStmt.executeUpdate();
		        System.out.println("research paper status of the Project updated successfully");
		        conn.close();
			}
		}catch(Exception e) {
			    e.printStackTrace();
		}
	}
}
