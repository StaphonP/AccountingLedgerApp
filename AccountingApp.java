import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AccountingApp {
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
            case 1:
                addDeposit();
                break;
            case 2:
                makePayment();
                break;
            case 3:
                ledgerScreen();
            case 4:
                exit();


        }
    }

    // -----------------Add Deposit Method-------------------
    private static void addDeposit() {

        //Prompt user for Deposit Info
        System.out.println("Enter Deposit information in this format \n Item  Vendor  Amount");

        //Writing the input from user into a file
        try {
            Scanner input = new Scanner(System.in);
            String deposit = input.next();
            FileWriter fw = new FileWriter("transactions.csv");

            //Defining Fields array
            String[] depositFields = {"Item", "Vendor", "Amount"};

            // For loop to write each field to file
            for (int i = 0; i < depositFields.length; i++) {
                fw.write(depositFields[i]);
                depositInfo currentItem = new depositInfo(depositFields[0], depositFields[1], Double.parseDouble(depositFields[2]));
                String time = currentItem.getDateTime();
                if (i < depositFields.length - 1) {
                    fw.write("|");
                }
                fw.write(time + currentItem);
                fw.close();
            }
        } catch (IOException e) {
            System.out.println("Error, Please try again later.");
           e.printStackTrace();
        }
        // Tell the user
        System.out.println("Deposit entered successfully!");
          homeScreen();
    }
   // ---------------------Make Payment Method----------------
    public static void makePayment() {

    }
}
