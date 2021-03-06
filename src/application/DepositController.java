package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DepositController
{
    private BankAccount account;

    @FXML private ArrayList<Button> buttonList;
    @FXML private TextField customField;

    private Stage primaryStage;

    public DepositController(Stage primaryStage, BankAccount account)
    {
        this.primaryStage = primaryStage;
        this.account = account;
    }

    public void initialize()
    {
        for(int i = 0; i < this.buttonList.size(); i++)
            this.buttonList.get(i).setOnAction(e ->
            {
                PrintWriter logWriter = null;
                try
                {
                    logWriter = new PrintWriter(new BufferedWriter(new FileWriter(new File("src\\logs\\" + this.account.getUsername() + "Log.txt"), true)));
                }
                catch(IOException e1)
                {
                    e1.printStackTrace();
                }

                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                Date date = new Date();

                if(!((Button) e.getSource()).getText().equals("Custom"))
                {
                    this.account.deposit(Double.valueOf(((Button) e.getSource()).getText().substring(1)));
                    logWriter.println(dateFormat.format(date) + " Deposit " + ((Button) e.getSource()).getText().substring(1));
                }
                else
                {
                    this.account.deposit(Double.valueOf(this.customField.getText()));
                    logWriter.println(dateFormat.format(date) + " Deposit " + this.customField.getText());
                }

                logWriter.flush();
                logWriter.close();

                HomeController homeController = new HomeController(primaryStage, account);
                homeController.show();
                homeController.previousIsDeposit();
            });
    }

    public void show()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Deposit.fxml"));

            loader.setController(this);
            this.primaryStage.setScene(new Scene(loader.load()));
            this.primaryStage.centerOnScreen();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
