package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;

public class Client extends Application
{
    public static ArrayList<BankAccount> userList;

    public Client()
    {
    	userList = new ArrayList<BankAccount>();

        try
        {
            Scanner scanner = new Scanner(new File("src\\application\\data.csv"));

            while(scanner.hasNextLine())
            {
                String[] data = scanner.nextLine().split(",");
                userList.add(new BankAccount(new String(Crypto.decrypt(data[0].getBytes())), new String(Crypto.decrypt(data[1].getBytes())), Double.valueOf(new String(Crypto.decrypt(data[2].getBytes())))));
            }

            scanner.close();
        }
        catch(IOException e)
        {
            System.out.println("File not found!");
        }

        System.out.println(userList.size());
    }

    public static void saveAccount(BankAccount account, File newFile)
    {
        try
        {
            PrintWriter data = new PrintWriter(new BufferedWriter(new FileWriter(newFile, true)));
            data.println(new String(Crypto.encrypt(account.getUsername().getBytes())) + "," + new String(Crypto.encrypt(account.getPassword().getBytes())) + "," + new String(Crypto.encrypt(String.valueOf(account.getBalance()).getBytes())) + ",");

            data.flush();
            data.close();
        }
        catch (IOException e)
        {
            System.out.println("File not found!");
        }
    }

	@Override
	public void start(Stage primaryStage)
	{
		primaryStage.show();
		primaryStage.setOnCloseRequest(e ->
		{
		    System.out.println("Storing...");

		    File dataFile = new File("src\\application\\data.csv");

		    try
            {
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(dataFile, false)));
                pw.print("");
                pw.flush();
                pw.close();
            }
		    catch (IOException e1)
		    {
		        System.out.println("File not found!");
            }

		    for(BankAccount account : userList)
		        saveAccount(account, dataFile);

		    System.out.println("Storing complete");
		});

		LogInController logIn = new LogInController(primaryStage);
		logIn.show();
	}

	public static void main(String[] args) throws IOException
	{
		launch(args);
	}
}
