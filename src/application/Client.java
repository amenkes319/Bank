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
    public static ArrayList<BankAccount> list;

    public Client()
    {
        list = new ArrayList<BankAccount>();

        try
        {
            Scanner scanner = new Scanner(new File("src\\application\\data.csv"));

            while(scanner.hasNextLine())
            {
                String[] data = scanner.nextLine().split(",");
                list.add(new BankAccount(data[0], data[1], Double.valueOf(data[2])));
            }

            scanner.close();
        }
        catch(IOException e)
        {
            System.out.println("File not found!");
        }

        System.out.println(list.size());
    }

    public static void saveAccount(BankAccount account, File newFile)
    {
        try
        {
            System.out.println(account.getUsername() + ": " + account.getBalance());
            PrintWriter data = new PrintWriter(new BufferedWriter(new FileWriter(newFile, true)));
            data.println(account.getUsername() + "," + account.getPassword() + "," + account.getBalance() + ",");

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

		    for(BankAccount account : list)
		        saveAccount(account, dataFile);

		    System.out.println("Storing complete");
		});

		LogInController logIn = new LogInController(primaryStage);
		logIn.show();
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
