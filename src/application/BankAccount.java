package application;

public class BankAccount
{
	private String username, password;
	private double balance;

	public BankAccount(String username, String password, double balance)
	{
		this.username = username;
		this.password = password;
		this.balance = balance;
	}

	public BankAccount(String username, String password)
	{
		this.username = username;
		this.password = password;
		this.balance = 0.0;
	}

	public String getUsername()
	{
		return this.username;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getPassword()
	{
		return this.password;
	}

	public double getBalance()
	{
		return balance;
	}

	public boolean withdraw(double amount)
	{
		if(balance - amount >= 0)
			this.balance -= amount;

		return balance >= 0;
	}

	public void deposit(double amount)
	{
		this.balance += amount;
	}
}
