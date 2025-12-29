
import enums.*;
import entities.*;
import service.*;

public class Main {
	public static void main(String[] args) {
		AccountService accountService = AccountService.getInstance();
		
		System.out.println("--- Account ---");
		Account account1 = accountService.createAccount(10000);
		Account account2 = accountService.createAccount(10000);


		System.out.println("--- Card ---");
		Card card1 = accountService.addCard(account1, "1234-1234-1234-1234", "7002");
		Card card2 = accountService.addCard(account1, "1234-1234-1234-1235", "7002");


		System.out.println("--- ATM ---");
		AtmService atmService = AtmService.getInstance();
		atmService.insertCard("1234-1234-1234-1235");
		atmService.insertCard("1234-1234-1234-1234");
		atmService.insertPin("1231");
		atmService.insertPin("1231");
		atmService.insertPin("1231");

		System.out.println();
		accountService.activateCard("1234-1234-1234-1234");
	
		System.out.println();
		atmService.insertCard("1234-1234-1234-1234");
		atmService.insertPin("7002");
		atmService.action(AtmOperations.WITHDRAW);
		atmService.enterAmount(12334);
		atmService.enterAmount(1233);
		atmService.enterAmount(2000);

		System.out.println("");
		atmService.action(AtmOperations.WITHDRAW);
		atmService.enterAmount(9000);

		System.out.println();
		atmService.action(AtmOperations.CHECK_BALANCE);
	}
}