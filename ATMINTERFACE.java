import java.util.Scanner;

class Bank_Account {
	
	String NAME;
	String USER_NAME;
	String PASSWORD;
	String ACCOUNT_NUMBER;
	float BALANCE = 200000f;
	int TRANSACTIONS= 0;
	String TRANSACTION_HISTORY = "";
	
	
	public void register() {
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter Your NAME:-\n");
		this.NAME = sc.nextLine();

		System.out.print("Enter Your USER_NAME:-\n");
		this.USER_NAME = sc.nextLine();

		System.out.print("Enter Your PASSWORD:-\n");
		this.PASSWORD = sc.nextLine();

		System.out.print("Enter Your Account_Numbers:-\n ");
		this.ACCOUNT_NUMBER = sc.nextLine();

		System.out.println("CONGRATULATIONS.! Registration is Completed \n");
		System.out.println("***************************************************\n");
	}
	
	public boolean login() {
		boolean isLogin = false;
		Scanner sc = new Scanner(System.in);
		while ( !isLogin ) {
			System.out.print("Enter Your USER_NAME:-\n");
			String Username = sc.nextLine();
			if ( Username.equals(USER_NAME) ) {
				while ( !isLogin ) {
					System.out.print("Enter Your PASSWORD:-\n");
					String Password = sc.nextLine();
					if ( Password.equals(PASSWORD) ) {
						System.out.print("Login Successful.!\n");
						System.out.println("***************************\n");
						isLogin = true;
					}
					else {
						System.out.println("Incorrect PASSWORD\n");
					}
				}
			}
			else {
				System.out.println("\nUSER_NAME not Found");
			}
		}
		return isLogin;
	}
	
	public void withdraw() {
		
		System.out.print("Enter Amount to withdrawl:- ");
		Scanner sc = new Scanner(System.in);
		float amount = sc.nextFloat();
		try {
			
			if ( BALANCE >= amount ) {
				TRANSACTIONS++;
				BALANCE-= amount;
				System.out.println("Withdrawl Successfully:\n");
				System.out.println("************************************\n");
				String str = amount + " Rs Withdrawed\n";
				TRANSACTION_HISTORY = TRANSACTION_HISTORY.concat(str);
				
			}
			else {
				System.out.println("\nInsufficient BALANCE:");
			}
			
		}
		catch ( Exception e) {
		}
	}
	
	public void deposit() {
		
		System.out.print("\nEnter amount to deposit - ");
		Scanner sc = new Scanner(System.in);
		float amount = sc.nextFloat();
		
		try {
			if ( amount <= 200000f ) {
				TRANSACTIONS++;
				BALANCE += amount;
				System.out.println("Successfully Deposited\n");
				String str = amount + " Rs deposited\n";
				TRANSACTION_HISTORY= TRANSACTION_HISTORY.concat(str);
			}
			else {
				System.out.println("Sorry!! Limit is 200000.00\n");
			}
			
		}
		catch ( Exception e) {
		}
	}
	
	public void transfer() {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Receipent's NAME:-\n");
		String receipent = sc.nextLine();
		System.out.print("\nEnter amount to TRANSFER:- ");
		float amount = sc.nextFloat();
		
		try {
			if (BALANCE >= amount ) {
				if ( amount <= 60000f ) {
					TRANSACTIONS++;
					BALANCE -= amount;
					System.out.println("Successfully Transfered to\n" + receipent);
					String str = amount + " Rs Transfered to " + receipent + "\n";
					TRANSACTION_HISTORY = TRANSACTION_HISTORY.concat(str);
				}
				else {
					System.out.println("Sorry!! Limit is 60000.00\n");
				}
			}
			else {
				System.out.println("Insufficient Balance\n");
			}
		}
		catch ( Exception e) {
		}
	}
	
	public void checkBalance() {
		System.out.println("\n" + BALANCE + " Rs");
	}
	
	public void transHistory() {
		if ( TRANSACTIONS == 0 ) {
			System.out.println("Empty\n");
		}
		else {
			System.out.println("\n" +TRANSACTION_HISTORY);
		}
	}
}


public class ATMINTERFACE {
	
	public static int takeIntegerInput(int limit) {
		int input = 0;
		boolean flag = false;
		
		while ( !flag ) {
			try {
				Scanner sc = new Scanner(System.in);
				input = sc.nextInt();
				flag = true;
				
				if ( flag && input > limit || input < 1 ) {
					System.out.println("Choose the number between 1 to " + limit);
					flag = false;
				}
			}
			catch ( Exception e ) {
				System.out.println("you will take  only integer value:");
				flag = false;
			}
		};
		return input;
	}
	
	
	public static void main(String[] args) {
		
		System.out.println("\nWELCOME TO PNB ATM SYSTEM\n");
		System.out.println("*******************************\n");
		System.out.println("1.Register \n2.Exit");
		System.out.print("Enter Your Choice - ");
		int choice = takeIntegerInput(2);
		
		if ( choice == 1 ) {
			Bank_Account b = new Bank_Account();
			b.register();
			while(true) {
				System.out.println("\n1.Login \n2.Exit");
				System.out.print("Enter Your Choice - ");
				int ch = takeIntegerInput(2);
				if ( ch == 1 ) {
					if (b.login()) {
						System.out.println("\n\n*****WELCOME BACK " + b.NAME + " *****\n");
						boolean isFinished = false;
						while (!isFinished) {
							System.out.println("\n1.Withdraw \n2.Deposit \n3.Transfer \n4.Check Balance \n5.Transaction History \n6.Exit");
							System.out.print("\nEnter Your Choice - ");
							int c = takeIntegerInput(6);
							switch(c) {
								case 1:
								b.withdraw();
								break;
								case 2:
								b.deposit();
								break;
								case 3:
								b.transfer();
								break;
								case 4:
								b.checkBalance();
								break;
								case 5:
								b.transHistory();
								break;
								case 6:
								isFinished = true;
								break;
							}
						}
					}
				}
				else {
					System.exit(0);
				}
			}
		}
		else {
			System.exit(0);
		}
		
		
		
	}
}