import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountingApp {
    static ArrayList<Payments> transactions = new ArrayList<>();
    static ArrayList<depositInfo> deposits = new ArrayList<>();
    
    //  ----------Main Driver----------
    public static void main(String[] args) {
        homeScreen();

    }

    // ----------Home Screen-----------
    public static void homeScreen() {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome! What would you like to do today? \n (D) Add Deposit \n (P) Make Payment \n (L) Ledger \n (X) Exit");
        String command1 = input.next().toUpperCase();
        switch (command1) {
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
        //Prompt user for Deposit Info
        System.out.println("Add a Deposit!\n");
        System.out.println("Enter Item: ");
        String item = input.nextLine();
        System.out.println("Enter Vendor: ");
        String vendor = input.nextLine();
        System.out.println("Enter Amount: ");
        double amount = input.nextDouble();
        depositInfo deposit1 = new depositInfo(item,vendor,amount);
        deposit1.setItem(item);
        deposit1.setVendor(vendor);
        deposit1.setAmount(amount);
        deposits.add(deposit1);

        //Writing the input from user into a file
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
        homeScreen();
    }

    // ---------------------Make Payment Method----------------
    public static void makePayment() {
        Scanner input = new Scanner(System.in);
        System.out.println("Make a Payment!\n");
        System.out.println("What are you paying for?:  ");
        String item = input.nextLine();
        System.out.println("Who are you paying?: ");
        String paidTo = input.nextLine();
        System.out.println("Enter Amount: ");
        double amount = input.nextDouble();
        Payments payment1 = new Payments(item,paidTo,amount);
        payment1.setItem(item);
        payment1.setPaidTo(paidTo);
        payment1.setAmount(amount);
        transactions.add(payment1);

        try {
            FileWriter writer = new FileWriter("transactions.csv",true);
            writer.write("\n"+ payment1.getDateTime()+ "|"+ payment1.getItem()+"|"+ payment1.getPaidTo()+"|"+ " " + "-" + payment1.getAmount());
            writer.close();


        } catch (IOException e) {
            System.out.println("Oops, There was an error, Please try again!");
            e.printStackTrace();
        }
        System.out.println("Transaction loaded successfully! \n Thank you!");
            homeScreen();
     }

    //----------------Ledger Screen-------------
    public static void ledgerScreen() {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the Ledger Screen! What would you like to see?\n(A) All Transactions\n(D) Deposits\n(P) Payments\n(R) Reports\n(H) Home");
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

    }
        //  -------------------Load All Transactions---------------------
    public static void loadTransactions() {
        System.out.println("----------All Transactions----------\n");
        try {
            BufferedReader reader = new BufferedReader(new FileReader("transactions.csv")); // reading the file to the Application
            String line;
            //Read Every Line
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            } reader.close();
        } catch (IOException e) {
            System.out.println("Error, Could Not Load.");
        }
        System.out.println("----------All Transactions----------\n");
        ledgerScreen();
    }
    //-------------------------Load All Deposits-------------------------
    public static void loadDeposits() {
        System.out.println("----------All Deposits----------\n");
        try {
            String line;
            BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"));
            while ((line = reader.readLine()) != null) {
               if (line.contains("+")){
                   System.out.println(line);
               }
            } reader.close();
        } catch (IOException e) {
            System.out.println("Error Loading..");
            e.printStackTrace();
        }
        System.out.println("----------All Deposits----------\n");
       ledgerScreen();
    }
    //------------------------Load All Payments--------------------------
    public static void loadPayments(){
        System.out.println("----------All Payments----------\n");
        try {
            String line;
            BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"));
            while ((line = reader.readLine()) != null) {
                if (!line.contains("+")){
                    System.out.println(line);
                }
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
        System.out.println("How would you like to search reports?\n (1) Month to Date\n(2) Previous Month\n(3) Year to Date\n(4) Previous Year\n(5) Search by Vendor");
        int command = input.nextInt();
        switch(command) {
            case 1:
                monthToDate();
            case 2:
                previousMonth();
            case 3:
                yearToDate();
            case 4:
                previousYear();
            case 5:
                searchByVendor();
        }
    }
    //---------------------Month To Date------------------//
    public static void monthToDate() {

    }
    //--------------------Previous Month-------------------//
    public static void previousMonth(){

    }
    //-----------------Year To Date-----------------------//
    public static void yearToDate(){

    }
    //------------------Previous Year-------------------
    public static void previousYear(){

    }
        // -----------------Search By Vendor--------------------
    public static void searchByVendor(){

    }
    //----------------------EXIT--------------------------//
    public static void exit(){

    }


    }