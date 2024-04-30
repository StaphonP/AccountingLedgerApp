import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
        String command1 = input.next();
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

    }
    //---------------EXIT-------------------
    public static void exit(){

    }
    }