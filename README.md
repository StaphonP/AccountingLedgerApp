# Accounting Ledger App
By: Staphon Peterson

## Application Details
I have created a CLI Application that:
* Takes deposits and payments.
* Can display either all transactions or just deposits or payments depending on what the user wants
* Displays Home Screen
* Displays Ledger Screen
* Displays Report Screen

## Code Specifics
* All transactions are written and saved to a file 
* Multiple try and catch methods
* Switch command methods
* Buffered Reader 
* Buffered Writer
* File Writer
* File Reader
* Application does not exit until the User says so

## Screens
![Capstone1 Screen1](https://github.com/StaphonP/AccountingLedgerApp/assets/166443449/193311f6-adb8-4ba3-bded-99d40f11a1ff)
![Capstone1 Screen2](https://github.com/StaphonP/AccountingLedgerApp/assets/166443449/98a3fd86-9302-4e1d-93ea-02453cdefe68)

## Interesting Code
The code that I found interesting was when I was making my load deposit and load payments method. I used the "line.contains" method,
to see if the line contains the + symbol or not. For the load payments method, if the + symbol is there then it wont print out that line.
For the load deposits method, if the line contains the + symbol then it WILL print out that line.
Shown here:
![Capstone1 Interesting code1](https://github.com/StaphonP/AccountingLedgerApp/assets/166443449/a5819e40-6edc-4e5e-b413-3bd9d7af8589)
![Capstone1 Interesting code2](https://github.com/StaphonP/AccountingLedgerApp/assets/166443449/6d38d4fc-728d-4a1f-be81-6acf2013710c)


