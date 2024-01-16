
//Final year project management system
package Projpack;

import java.util.Scanner;

public class Main {

 public static void main(String[] args) {
 	FYproject.createDatabase();
		FYproject.createTables();
     Scanner scanner = new Scanner(System.in);

     System.out.println("Welcome to our final year project management system");

     while (true) {
         System.out.println("Select an option:");
         System.out.println("1. User");
         System.out.println("2. Professor");
         System.out.println("3. Exit");

         int option = scanner.nextInt();
         scanner.nextLine(); // Consume the newline character

         switch (option) {
             case 1:
                 userMenu();
                 break;
             case 2:
                 FacultyMenu();
                 break;
             case 3:
                 System.out.println("Exiting. Thank You");
                 System.exit(0);
                 break;
             default:
                 System.out.println("Invalid option. Please try again.");
         }
      }
 }

 private static void userMenu() {
     Scanner scanner = new Scanner(System.in);

             while (true) {
                 System.out.println("Select any option:");
                 System.out.println("1. View all projects");
                 System.out.println("2. Search by project name");
                 System.out.println("3. Search by student name");
                 System.out.println("4. Exit");
                 

                 int userOption = scanner.nextInt();
                 scanner.nextLine(); // Consume the newline character

                 switch (userOption) {
                     case 1:
                         FYproject.viewallprojects();

                         break;
                     case 2:
                         System.out.print("Enter project name: ");
                         String projectName = scanner.nextLine();
                         
                         FYproject.searchbyprojectname(projectName);

                         break;
                     case 3:
                         System.out.print("Enter student name: ");
                         String studentName = scanner.nextLine();
                         FYproject.searchbystudentname(studentName);
                        
                         break;

                     case 4:
                         System.out.println("Exiting user menu.");
                         return;
                     
                     default:
                         System.out.println("Invalid option. Please try again.");
                 }
             }
   }
         
 private static void FacultyMenu() {
     Scanner scanner = new Scanner(System.in);

     System.out.print("Enter your faculty ID: ");
     String facultyId = scanner.nextLine();

     if (facultyId.equals("admin")) {
         System.out.println("Authentication successful!");

         while (true) {
             System.out.println("Select an option:");
             System.out.println("1. Add Details");
             System.out.println("2. Delete");
             System.out.println("3. Search by project name");
             System.out.println("4. Search by student name");
             System.out.println("5. Update research paper status of the project");
             System.out.println("6. Exit");

             int facultyOption = scanner.nextInt();
             scanner.nextLine(); // Consume the newline character
             
             switch (facultyOption) {
                 case 1:
                     System.out.print("Enter team ID:");
                     String teamID = scanner.nextLine();;
                     
                     System.out.print("Enter project name:");
                     String projectname = scanner.nextLine();
                     
                     System.out.print("Enter team leader name:");
                     String leadername = scanner.nextLine();

                     System.out.print("Enter team member1 name:");
                     String teammember1 = scanner.nextLine();
                     System.out.print("Enter team member2 name:");
                     String teammember2 = scanner.nextLine();
                     System.out.print("Enter team member3 name:");
                     String teammember3 = scanner.nextLine();
    
                     System.out.print("Enter year:");
                     String year= scanner.nextLine();

                     System.out.print("Enter research paper status(Published/Not Published):");
                     String rpstatus = scanner.nextLine();

                     Faculty.addDetails(teamID,projectname,leadername,teammember1, teammember2, teammember3,year,rpstatus);

                     break;
                 case 2:
                     System.out.print("Enter team ID to delete:");
                     String team_ID = scanner.nextLine();
                     Faculty.deleteProject(team_ID);
                     break;
                 case 3:
                     System.out.print("Enter project name: ");
                     String projectName = scanner.nextLine();
                     FYproject.searchbyprojectname(projectName);
                     break;
                 case 4:
                     System.out.print("Enter student name: ");
                     String studentName = scanner.nextLine();
                     FYproject.searchbystudentname(studentName);
                     break;

                 case 5: 
                     System.out.print("Enter team ID to update:");
                     String teamid = scanner.nextLine();
                     System.out.print("Enter research paper status (PUBLISHED/NOT PUBLISHED): ");
                     String researchpaperstatus = scanner.nextLine();

                     Faculty.update(researchpaperstatus,teamid);
                     break;

                 case 6:
                     System.out.println("Exiting faculty menu.");
                     System.exit(0);
                 default:
                     System.out.println("Invalid option. Please try again.");
             }
         }
     } else {
         System.out.println("Authentication failed. Only college faculty can access this feature.");
     }
 }
}





