import service.*;
import entity.*;
import enums.*;

public class Main {

    public static void main(String[] args) {
        BankingService bankingService = BankingService.getInstance();
        
        Account a1 = bankingService.createAccount(1000);
        
        Card c1 = bankingService.createCard("1234-1234-1234-1234", "1234");
        
        bankingService.mapCardWithAccount(c1, a1);
        System.out.println("Current balance: rs"+bankingService.getBalance(c1));
        bankingService.depositMoney(c1, 500);
        System.out.println("Current balance: rs"+bankingService.getBalance(c1));

        System.out.println(bankingService.getCard("1234-1234-1234-1234"));
      

        System.out.println();


        ATMService atmService = ATMService.getInstance();
        atmService.insertCard("1234-1234-1234-1234");
        atmService.enterPin("1234");
        atmService.selectOperation(OperationType.CHECK_BALANCE);


        atmService.insertCard("1234-1234-1234-1234");
        atmService.enterPin("1234");
        atmService.selectOperation(OperationType.WITHDRAW_CASH, 700);

        atmService.insertCard("1234-1234-1234-1234");
        atmService.enterPin("1234");
        atmService.selectOperation(OperationType.DEPOSIT_CASH, 200);


        atmService.insertCard("1234-1234-1234-1234");
        atmService.enterPin("1234");
        atmService.selectOperation(OperationType.CHECK_BALANCE);

        atmService.insertCard("1234-1234-1234-1234");
        atmService.enterPin("1234");
        atmService.selectOperation(OperationType.WITHDRAW_CASH, 2000); // withdraw more then limit


        atmService.insertCard("1234-1234-1234-1234");
        atmService.enterPin("4321"); // incorrect pin
    }
}