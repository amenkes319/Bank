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
		this.balance = 0.00;
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
		return this.balance;
	}

	public boolean withdraw(double amount)
	{
		if(balance - amount >= 0)
			this.balance -= amount;

		return this.balance - amount >= 0;
	}

	public void deposit(double amount)
	{
		this.balance += Math.round(amount * 100)/100.0;
	}

	public String toString()
	{
	    return getUsername() + ", " + getPassword() + ", " + Math.round(getBalance() * 100)/100.0;
	}
}
