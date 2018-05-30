/*
A customer opens an account in a bank,the customer must
have an initial balancen of 100$.while opening the account,the
customer can withdraw,deposit,and check his balance any time he wants,
the bank does not charge any fees for the 1st withdrawal butfor all
subsequent withdrawals,the bank charges some transaction fees.
the bank also calculate a certain amount of interest on the amount 
deposited by the customer as per its interested rate.create an object oriented
 program to automate this problem. 

*/
package bank_management_project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PrimitiveIterator.OfDouble;

public class BankProgram {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int numberOfCustomers = 0;
		Bank bank = new Bank();
		Customer[] c = bank.getCustomer();

		while (true) {

			System.out.println("Please Enter your choise");
			System.out.println("1: Add Customer");
			System.out.println("2: Deposit Money");
			System.out.println("3: Withdraw Money");
			System.out.println("4: Check Balance");
			System.out.println("5: Calculate Interest");
			System.out.println("6: Exit");
			int choice = Integer.parseInt(bufferedReader.readLine());

			switch (choice) {
			case 1:

				System.out.println("Creating an account for new customer");
				System.out.println("Plz enter the initial amount in your account: ");
				double bal = Double.parseDouble(bufferedReader.readLine());
				System.out.println("Plz enter your account number: ");
				String acc = bufferedReader.readLine();

				Account account = new Account(bal, acc);

				System.out.println("Plz enter your name: ");
				String name = bufferedReader.readLine();

				Customer customer = new Customer(name, account);
				c[numberOfCustomers] = customer;
				numberOfCustomers++;
				System.err.println("Number of customers: " + numberOfCustomers);

				for (int i = 0; i < numberOfCustomers; i++) {

					System.err.println(c[i].getName() + " NAME");

				}
				break;

			case 2:

				System.out.println("Enter account number: ");
				acc = bufferedReader.readLine();
				if (numberOfCustomers == 0) {

					System.out.println("Account number not Found");
				} else {

					boolean found = false;
					for (int i = 0; i < numberOfCustomers; i++) {

						Account temp = c[i].getAccount();
						String accTemp = temp.getAccountNumber();
						if (accTemp.equals(acc)) {

							System.out.println("Plz enter the amount to deposit: ");
							double money = Double.parseDouble(bufferedReader.readLine());
							temp.deposit(money);
							found = true;
						}
					}
					if (found == false) {

						System.err.println("Account number not found");
					}
				}

				break;
			case 3:

				System.out.println("Enter account number: ");
				acc = bufferedReader.readLine();
				if (numberOfCustomers == 0) {

					System.out.println("Account number not Found");
				} else {

					boolean found = false;
					for (int i = 0; i < numberOfCustomers; i++) {

						Account temp = c[i].getAccount();
						String accTemp = temp.getAccountNumber();
						if (accTemp.equals(acc)) {

							System.out.println("Plz enter the amount to withdrow: ");
							double money = Double.parseDouble(bufferedReader.readLine());
							temp.withdraw(money);
							found = true;
						}
					}
					if (found == false) {

						System.err.println("Account number not found");
					}
				}

				break;
			case 4:

				System.out.println("Enter account number: ");
				acc = bufferedReader.readLine();
				if (numberOfCustomers == 0) {

					System.out.println("Account number not Found");
				} else {

					boolean found = false;
					for (int i = 0; i < numberOfCustomers; i++) {

						Account temp = c[i].getAccount();
						String accTemp = temp.getAccountNumber();
						if (accTemp.equals(acc)) {

							System.out.println("Balance is: " + temp.getBalance());
							found = true;
						}
					}
					if (found == false) {

						System.err.println("Account number not found");
					}
				}

				break;
			case 5:

				System.out.println("Enter account number: ");
				acc = bufferedReader.readLine();
				if (numberOfCustomers == 0) {

					System.out.println("Account number not Found");
				} else {

					boolean found = false;
					for (int i = 0; i < numberOfCustomers; i++) {

						Account temp = c[i].getAccount();
						String accTemp = temp.getAccountNumber();
						if (accTemp.equals(acc)) {

							bank.calculateInterest(c[i]);
							found = true;
						}
					}
					if (found == false) {

						System.err.println("Account number not found");
					}
				}

				break;
			case 6:

				System.exit(0);
				break;
			default:
				break;
			}
		}

	}

}

class Bank {

	private double interesteRate = 8.5;
	private double transactionFees = 10;
	private Customer[] customers = new Customer[1000];

	public void calculateInterest(Customer customer) {

		Account a = customer.getAccount();
		double bal = a.getBalance();
		double interestAmount = bal * interesteRate / 100;
		double totalBalance = bal + interestAmount;
		System.out.println("Interest amount: " + interestAmount);
		System.out.println("Total money after adding interest: " + totalBalance);
	}

	public double getInterestRate() {

		return interesteRate;
	}

	public double getTransactionFees() {

		return transactionFees;
	}

	public Customer[] getCustomer() {

		return customers;
	}

}

class Account {

	private double balance = 100;
	private String accountNumber;
	private boolean firstTime = true;

	public Account(String acc) {

		accountNumber = acc;

	}

	public Account(double bal, String acc) {

		if (bal > 100) {

			balance = bal;
		}

		else {

			balance = 100;
		}
		accountNumber = acc;
	}

	public void deposit(double howMuch) {

		if (howMuch > 0) {

			balance = balance + howMuch;
			System.out.println(howMuch + " was successfully deposited in your account."
					+ " The new balance on your account is " + balance);
		} else {

			System.err.println("Invalid money to deposit");
		}
	}

	public void withdraw(double howMuch) {

		if (howMuch >= 0) {

			if (firstTime == true) {

				double tempBalance = balance;
				tempBalance = tempBalance - howMuch;

				if (tempBalance >= 100) {

					balance = balance - howMuch;
					System.out.println(howMuch + " was successfully withdraw from your account."
							+ " The new balance on your account is " + balance);
				}

				else {

					System.err.println("Insufficient balance to remove" + howMuch);
				}

				firstTime = false;

			} else {

				Bank bank = new Bank();
				double tempBalance = balance;
				tempBalance = tempBalance - howMuch - bank.getTransactionFees();
				if (tempBalance >= 100) {

					balance = balance - howMuch - bank.getTransactionFees();
				}

				else {

					System.err.println("Insufficient balance to remove" + howMuch);
				}
			}
		} else {

			System.err.println("Plz ensure the amount to be withdrawn is not negative");
		}
	}

	public double getBalance() {

		return balance;

	}

	public String getAccountNumber() {

		return accountNumber;
	}

}

class Customer {

	private String name;
	private Account account;

	public Customer(String n, Account a) {

		name = n;
		account = a;

	}

	public void display() {

		System.out.println("Name: " + name);
		System.out.println("Account Number: " + account.getAccountNumber());
		System.out.println("Balance: " + account.getBalance());
	}

	public String getName() {

		return name;
	}

	public Account getAccount() {

		return account;
	}
}