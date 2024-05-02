import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class AccountingApp {
    static ArrayList<String> entries = new ArrayList<>();

    //  ----------Main Driver----------
    public static void main(String[] args) {
        homeScreen();

    }

    // ----------Home Screen-----------
    public static void homeScreen() {
        Scanner input = new Scanner(System.in);
        System.out.println("---------------CASHTRACKR---------------\n");

        //Asking the User what they want to do
        System.out.println("Welcome! What would you like to do today? \n (D) Add Deposit \n (P) Make Payment \n (L) Ledger \n (X) Exit");

        String command1 = input.next().toUpperCase();//Used an uppercase method so that even if they put a lowercase letter the app will change it to uppercase.

            switch (command1) { //I used a switch command for the different screens depending on the users input.

                case "D":
                    addDeposit();
                    break;
                case "P":
                    makePayment();
                    break;
                case "L":
                    ledgerScreen();
                case "X":
                    exit();


            }
       }


    // -----------------Add Deposit Method-------------------
    private static void addDeposit() {
        Scanner input = new Scanner(System.in);

      //Tell the User where they are in the app
        System.out.println("----------Add A Deposit----------\n");

        //Prompt user for Deposit Info and assign their input to a variable.
        System.out.println("Enter Item: ");
        String item = input.nextLine();
        System.out.println("Enter Vendor: ");
        String vendor = input.nextLine();
        System.out.println("Enter Amount: ");
        double amount = input.nextDouble();

        //Creates a new deposit object.
       depositInfo deposit1 = new depositInfo(item,vendor,amount);
        deposit1.setItem(item);
        deposit1.setVendor(vendor);
        deposit1.setAmount(amount);

        //Writing the input from user into a file, Separating each input by |
        try {
           FileWriter writer = new FileWriter("transactions.csv",true);
           writer.write("\n" + deposit1.getDateTime()+ "|" + deposit1.getItem()+ "|"+ deposit1.getVendor()+ "|" + " " + "+" + deposit1.getAmount());
            writer.close();


        } catch (IOException e) {
            System.out.println("Error, Please try again later.");
            e.printStackTrace();
        }
        // Tell the user that the App received information correctly
        System.out.println("Deposit entered successfully!\n Thank You!");

        //Back to the home screen
        homeScreen();
    }

    // ---------------------Make Payment Method----------------
    public static void makePayment() {
        Scanner input = new Scanner(System.in);

        //Tell the user where they are in the app
        System.out.println("----------Make A Payment----------\n");

        // Ask the User for the information needed to log the payment and assign the users input to a variable
        System.out.println("What are you paying for?:  ");
        String item = input.nextLine();
        System.out.println("Who are you paying?: ");
        String paidTo = input.nextLine();
        System.out.println("Enter Amount: ");
        double amount = input.nextDouble();

        // Creates a new payment object
        Payments payment1 = new Payments(item,paidTo,amount);
        payment1.setItem(item);
        payment1.setPaidTo(paidTo);
        payment1.setAmount(amount);


                  //Writing the input from user into a file, Separating each input by |
        try {
            FileWriter writer = new FileWriter("transactions.csv",true);
            writer.write("\n"+ payment1.getDateTime()+ "|"+ payment1.getItem()+"|"+ payment1.getPaidTo()+"|"+ " " + "-" + payment1.getAmount());
            writer.close();


        } catch (IOException e) {
            System.out.println("Oops, There was an error, Please try again!");
            e.printStackTrace();
        }
        //Tell the user that the App received the information correctly
        System.out.println("Transaction loaded successfully! \n Thank you!");

        //Back to home screen
            homeScreen();
     }

    //----------------Ledger Screen-------------
    public static void ledgerScreen() {
        Scanner input = new Scanner(System.in);
        //Tell the user where they are in the app and ask them for input on what they want to do.
        System.out.println("----------Ledger Screen----------\n");
        System.out.println("What would you like to see?\n(A) All Transactions\n(D) Deposits\n(P) Payments\n(R) Reports\n(H) Home");
        String command = input.next().toUpperCase();

        switch (command) {
            case "A":
                loadTransactions();
                break;
            case "D":
                loadDeposits();
                break;
            case "P":
                loadPayments();
                break;
            case "R":
                reportScreen();
                break;
            case "H":
                homeScreen();
                break;
        }
        System.out.println("----------Ledger Screen----------\n");
    }
        //  -------------------Load All Transactions---------------------
    public static void loadTransactions() {
        System.out.println("----------All Transactions----------");
        //File Reader reads transactions file and prints it out for the user
        try {
            BufferedReader reader = new BufferedReader(new FileReader("transactions.csv")); // reading the file to the Application
            String line;

            //Read Every Line
            while ((line = reader.readLine()) != null) {
                entries.add(line);
                System.out.println(line);
            } reader.close();
            sortArray(entries);
        } catch (IOException e) {
            System.out.println("Error, Could Not Load.");
        }
        System.out.println("----------All Transactions----------\n");

        //Back to Ledger Screen
        ledgerScreen();
    }
    //-------------------------Load All Deposits-------------------------
    public static void loadDeposits() {
        System.out.println("----------All Deposits----------");
        //Reads from transaction file
        try {
            String line;
            BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"));
            while ((line = reader.readLine()) != null) {
                // Prints out only lines that contain "+"
               if (line.contains("+")){
                   System.out.println(line);
                   entries.add(line);
               }
            } reader.close();
            sortArray(entries);
        } catch (IOException e) {
            System.out.println("Error Loading..");
            e.printStackTrace();
        }
        System.out.println("----------All Deposits----------\n");

        //Back to ledger screen
       ledgerScreen();
    }
    //------------------------Load All Payments--------------------------
    public static void loadPayments(){
        System.out.println("----------All Payments----------\n");

        //Read from transactions file
        try {
            String line;
            BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"));
            while ((line = reader.readLine()) != null) {

                // If the line does not contain "+" then the App will print out that line
                if (!line.contains("+")){
                    entries.add(line);
                    System.out.println(line);
                }
                sortArray(entries);
            } reader.close();
        } catch (IOException e) {
            System.out.println("Error Loading..");
            e.printStackTrace();
        }
        System.out.println("----------All Payments----------\n");
        ledgerScreen();
    }
    //-------------------------Report Screen-----------------------------
    public static void reportScreen(){
        Scanner input = new Scanner(System.in);
        System.out.println("----------Report Screen----------\n");
        System.out.println("How would you like to search reports?\n (1) Month to Date\n(2) Previous Month\n(3) Year to Date\n(4) Previous Year\n(5) Search by Vendor\n(6) Back to Ledger Screen");
        int command = input.nextInt();
        switch(command) {
            case 1:
                monthToDate();
                break;
            case 2:
                previousMonth();
                break;
            case 3:
                yearToDate();
                break;
            case 4:
                previousYear();
                break;
            case 5:
                searchByVendor();
                break;
            case 6:
                ledgerScreen();
                break;
        }
        System.out.println("----------Report Screen----------\n");
    }
    //---------------------Month To Date------------------//
    public static void monthToDate() {
        System.out.println("----------Month to Date----------");
        String month = "2024-05";
        try {
            String line;
            BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"));
            while ((line = reader.readLine()) != null) {
                if(line.contains(month)){
                    sortArray(entries);
                    System.out.println(line);
                }
              }
            reader.close();

        } catch (IOException e) {
            System.out.println("Error!\n");
            e.printStackTrace();
        }   System.out.println("----------Month to Date----------\n");
        reportScreen();
    }
    public static void sortArray(ArrayList<String> entries){
        Collections.reverse(entries);
        for (String entry : entries) {
            System.out.println(entry);
        }
        entries.clear();
    }
    //--------------------Previous Month-------------------//
    public static void previousMonth(){
        System.out.println("----------Last Month Entries-----------");
        try {
            String line;
            String lastMonth= "2024-04";
            BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"));
            while ((line = reader.readLine()) != null) {
                if(line.contains(lastMonth)){
                    sortArray(entries);
                    System.out.println(line);
                }
            } reader.close();

        } catch (IOException e){
            System.out.println("Error");
        }  System.out.println("----------Last Month Entries-----------\n");
        reportScreen();

    }
    //-----------------Year To Date-----------------------//
    public static void yearToDate(){
        System.out.println("----------Current Year Entries-----------");
        try {
            String line;
            String currentYear= "2024";
            BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"));
            while ((line = reader.readLine()) != null) {
                if(line.contains(currentYear)){
                    sortArray(entries);
                    System.out.println(line);
                }
            } reader.close();

        } catch (IOException e){
            System.out.println("Error");
        } System.out.println("----------Current Year Entries-----------\n");
        reportScreen();
    }
    //------------------Previous Year-------------------
    public static void previousYear(){
        System.out.println("----------Previous Year Entries-----------");
        try {
            String line;
            String lastYear= "2023";
            BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"));
            while ((line = reader.readLine()) != null) {
                if(line.contains(lastYear)){
                    sortArray(entries);
                    System.out.println(line);
                }
            } reader.close();

        } catch (IOException e){
            System.out.println("Error");
            e.printStackTrace();
        }  System.out.println("----------Previous Year Entries-----------\n");
        reportScreen();
    }
        // -----------------Search By Vendor--------------------
    public static void searchByVendor(){
        Scanner input = new Scanner(System.in);
        System.out.println("-----------Search By Vendor----------\n");
        System.out.println("Enter Vendor: \n");
        String vendor = input.nextLine();
        System.out.println("----------Entries----------");
        try {
            String line;
            BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"));
            while ((line = reader.readLine()) != null) {
                if(line.contains(vendor)){
                    sortArray(entries);
                    System.out.println(line);
                }
            } reader.close();

        } catch(IOException e){
            System.out.println("Error!");
            e.printStackTrace();
        }  System.out.println("----------Entries----------\n");
        reportScreen();

    }
    //----------------------EXIT--------------------------//
    public static void exit(){

    }


    }