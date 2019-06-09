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
		return Math.round(this.balance * 100)/100.0; //rounds to hundredth
	}

	public boolean withdraw(double amount)
	{
	    boolean bSuccess = false;

		if(this.balance - amount >= 0)
		{
			this.balance -= amount;
			bSuccess = true;
		}

		return bSuccess;
	}

	public void deposit(double amount)
	{
		this.balance += Math.round(amount * 100)/100.0;
	}

	public String toString()
	{
	    return getUsername() + ", " + getPassword() + ", " + getBalance();
	}
}
