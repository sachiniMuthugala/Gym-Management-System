package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.util.*;

public class MyGymManager extends Application implements GymManager{

    //Can't add more than 100 members
    private final int MAX_MEMBERS = 100;
    private Scanner sc;
    private Scanner sc1;

    public static ArrayList<DefaultMember> memberList = new ArrayList<>();  //Used an array list to get data.

    public void manager() throws Exception{

        memberList=DataHistory.readData();

        sc = new Scanner(System.in);
        sc1 = new Scanner(System.in);

        System.out.println("WELCOME TO GYM MANAGEMENT SYSTEM");

        boolean input = true;

        while (input){      //this process will stop when the input = true
            System.out.println("======== Select the options ========");
            System.out.println("A ---------->  Add a new member");
            System.out.println("D ---------->  Delete a member");
            System.out.println("P ---------->  Print list of members");
            System.out.println("S ---------->  Sort out the list");
            System.out.println("W ---------->  Save files");
            System.out.println("G ---------->  Open graphical user interface");
            System.out.println("Q ---------->  Exit Application");
            System.out.print("Enter your choice : ");
            String userInput = sc.nextLine().toUpperCase();     //user can add either uppercase or a lowercase letters
            System.out.println();

            switch (userInput) {
                //Adding members
                case "A":
                    if(memberList.size() < MAX_MEMBERS){        //user can only add data if number of members are below 100
                        DefaultMember tempMember = GetNewMemberInfo(sc);
                        if(tempMember != null){
                            AddMember(tempMember);
                            System.out.println("Member Added Successfully");

                            //Printing number of available slots
                            int currentCount =(100 - memberList.size()) ;
                            if (currentCount > 100){
                                System.out.println("No more slots available.Achieve the maximum count of 100 members");
                            }
                            else {
                                System.out.println("Slots available :" + currentCount);
                            }

                        }
                    }else{
                        System.out.println("Member list has reached maximum length!");      //If number of count exceed the limit
                    }
                    DataHistory.storeData();
                    break;

                case "D":
                    //Deleting the records

                    System.out.print("Enter Membership ID number of user to be deleted: ");
                    while (!sc1.hasNextInt()) {     //check user enters int value or not
                        System.out.println("membership number must be a Number.Enter a valid id!");
                        sc1.next();
                        System.out.print("Enter Membership ID number of user to be deleted: ");   //give the chance to enter again
                    }
                    int id = sc1.nextInt();
                    DeleteMember(id);

                    //Printing number of available slots
                    int currentCount =(100 - memberList.size()) ;
                    System.out.println("Slots available :" + currentCount);
                    break;

                case "P":
                    //Printing the member details
                    PrintMemberList();
                    break;

                case "S":
                    //Sorted the member list in ascending order according to the name
                    SortListNameAsc();
                    PrintMemberList();
                    break;

                case "W":
                    //Saving the data to text file
                    SaveToFile();
                    break;

                case "G":
                    //show the list of data in graphical user interface
                    Application.launch();
                    break;

                case "Q":
                    //Exit the programme
                    input = false;
                    break;

                default:
                    //if added any other key this message will display
                    System.out.println("Invalid input! Try again");
                    break;
            }
        }
    }

    @Override
    public void AddMember(DefaultMember dm) {
        memberList.add(dm);
    }

    @Override
    public void DeleteMember(int membershipNumber) {
        boolean inTheList=false;     //
        try {
            if (memberList.size() !=0){                             //to run this there must be at least one element in the list
            for(int i=0; i<memberList.size();i++) {                 //running thr for loop in list
                int id=memberList.get(i).getMembershipNumber();     //getting the value from list
                String deletedMemberType =memberList.get(i).getMemberType();    //to print the deleted member type
                if ( id== membershipNumber) {                       //when entered it exist in the list

                    inTheList = true;
                    memberList.remove(memberList.get(i));       //Delete the records by membership number
                    DataHistory.storeData();                    //to update the array
                    System.out.println("Type of the member deleted : " + deletedMemberType);      //Type of the member deleted
                    System.out.println("Member with ID [" + membershipNumber + "] Deleted Successfully");
                }

            }
            }
            if (!inTheList){    //if entered id doesn't exist in the list
                System.out.println("Entered id doesn't exist.");
            }
        }catch (ConcurrentModificationException e){
            //if there is an object not permissible to modify
            e.printStackTrace();
        }
    }

    @Override
    public void PrintMemberList() {
        for(DefaultMember d: memberList){
            System.out.println(d);
        }

        if (memberList.size() == 0){    //if no one in the list there's nothing to print
            System.out.println("No one is added");
        }
    }

    @Override
    public void SortListNameAsc() {
        Collections.sort(memberList, new NameComparator());     //Sort ascending order according to the name
    }

    @Override
    public void SaveToFile() throws Exception {
        FileWriter writer = new FileWriter("details.txt",true);     //append = true to avoid overwriting
        //if user wants to save data all the data will save to details text file
        for (DefaultMember str:memberList) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();
        System.out.println("Details saved successfully");

    }

    @Override
    public void OpenGUI() throws Exception {        //View the GUI
        GUI.View();
    }

    /*
    there are 3 types as default,student,over60
    User can add members one by one according to the instructions.
     */
    private DefaultMember GetNewMemberInfo(Scanner sc){
        System.out.println("======== Select member type ========");
        System.out.println("1 ---------->  Default Member");
        System.out.println("2 ---------->  Student Member");
        System.out.println("3 ---------->  Over 60 Member");
        System.out.println("Anything else to go back");
        System.out.print("Enter your selection : ");

        String memberType = sc.nextLine();

        if(!(memberType.equals("1") || memberType.equals("2") || memberType.equals("3")))return null;

        //Input ID
        boolean exists = true;      //create this exist boolean val to check id is already added or not
        int id = 0;
        while (exists){
            exists = false;
            System.out.print("Enter New Membership Id Number: ");

            while (!sc1.hasNextInt()) {     //check user enters int value or not
                System.out.println("membership number must be a Number.Enter a valid id!");
                sc1.next();
                System.out.print("Enter New Membership Id Number: ");   //give the chance to enter again
            }

            id = sc1.nextInt();

            /*
            check the id is already exist in the list or not
            if the entered id is already taken user have to enter another id
             */
            for (DefaultMember mn : memberList) {
                if (mn.getMembershipNumber() == id) {
                    exists = true;  //if id is same loop again
                    break;
                }
            }
            if (exists){
                System.out.println("This id is already exist. Try again");
                //if id is exist in the list this error message will display
                exists = true;

            }
        }


        //Enter name
        String name;
        do {
            System.out.print("Enter Name: ");
            name = sc.nextLine();
            if (!(name.matches("^[a-zA-Z\\s]*$"))){
                /*check user enters Strings or not
                    a-zA-Z -----> means Strings lowercase a to z and uppercase A to Z
                         ^ -----> indicates starting of the string
                         * -----> indicates ending of the string
                         \\s ---> to allow user to spaces.name with 2 words ex: sachini jahson
                 */
                System.out.println("Name must be a String");
            }
            else if (name.equals("")){      //if user leave the name field blank it display an error message and ask to enter again
                System.out.println("Neme must be filled out");
            }

        }while ((!(name.matches("^[a-zA-Z\\s]*$"))) || (name.equals("")));


        //Membership date
        System.out.print("Enter Membership start date (dd-mm-yyyy): ");
        String startDate = sc.nextLine();

        switch (memberType) {
            //Default member
            case "1":
                return new DefaultMember(id, name, startDate, "Default");

            //School member
            case "2":
                String schoolName;
                do {
                    System.out.print("Enter School Name: ");
                    schoolName = sc.nextLine();
                    if (!(schoolName.matches("^[a-zA-Z\\s]*$"))){
                        /*check user enters Strings or not
                            a-zA-Z -----> means Strings lowercase a to z and uppercase A to Z
                                 ^ -----> indicates starting of the string
                                 * -----> indicates ending of the string
                                 \\s ---> to allow user to spaces.name with more than one word ex: colombo university
                        */
                        System.out.println("School name must be a string!");
                    }
                    else if (schoolName.equals("")){        //if user leave the school name field blank it display an error message and ask to enter again
                        System.out.println("School name must be filled out!");
                    }

                }while ((!(schoolName.matches("^[a-zA-Z\\s]*$"))) || (schoolName.equals("")));


                return new StudentMember(id, name, startDate, schoolName);

            //Over 60 member
            case "3":
                //Validate age
                int age;
                do {
                    System.out.print("Age : ");

                    while (!sc1.hasNextInt()) {
                        System.out.println("Age must be a Number");
                        sc1.next();
                        System.out.print("Age :");
                    }
                    age = sc1.nextInt();

                    if (age < 60){      //Over 60 members must be above 60 years old
                        System.out.println("Age must be over 60");
                    }

                } while (age < 60);

                return new Over60Member(id, name, startDate, age);
            default:
                return null;
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GUI.View();
    }


    //Get the data from the array list and assign that values to GUi table
    public static ObservableList<DefaultMember> getGymMember(){
        ObservableList<DefaultMember> gymMembers = FXCollections.observableArrayList();
        for (int j= 0;j<memberList.size();j++){
            gymMembers.add(memberList.get(j));
        }
        return gymMembers;
    }

    public static ArrayList<DefaultMember> defaultMember(){
        return memberList;
    }




}


