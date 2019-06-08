package application;

import java.io.FileWriter;
import java.io.IOException;

public class BankAccount
{
	private String username, password;
	private double balance;

	public BankAccount(String username, String password)
	{
		this.username = username;
		this.password = password;
		this.balance = 0.00;

		saveAccount();
	}

	private void saveAccount()
	{
	    try
        {
            FileWriter data = new FileWriter("src\\application\\data.csv", true);
            data.write(username + "," + password + "," + balance + ",\n");

            data.close();
        }
	    catch (IOException e)
        {
            System.out.println("File not found!");
        }
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

		return balance - amount >= 0;
	}

	public void deposit(double amount)
	{
		this.balance += Math.round(amount * 100)/100.0;
	}
}
