package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TransferController
{
    private Stage primaryStage;
    private BankAccount account;

    @FXML private Button backBtn, transferBtn;
    @FXML private TextField usernameField, amountField;
    @FXML private Label invalidUsernameLbl, insufficientFundsLbl;

    public TransferController(Stage primaryStage, BankAccount account)
    {
        this.primaryStage = primaryStage;
        this.account = account;
    }

    public void initialize()
    {
        this.backBtn.setOnAction(e ->
        {
            HomeController homeController = new HomeController(this.primaryStage, this.account);
            homeController.show();
        });

        this.transferBtn.setOnAction(e ->
        {
            this.invalidUsernameLbl.setOpacity(0);
            this.invalidUsernameLbl.setOpacity(0);

            PrintWriter logToWriter = null;
            PrintWriter logFromWriter = null;

            try
            {
                logToWriter = new PrintWriter(new BufferedWriter(new FileWriter(new File("src\\logs\\" + this.account.getUsername() + "Log.txt"), true)));
                logFromWriter = new PrintWriter(new BufferedWriter(new FileWriter(new File("src\\logs\\" + this.usernameField.getText() + "Log.txt"), true)));
            }
            catch(IOException e1)
            {
                e1.printStackTrace();
            }

            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date date = new Date();

            if(this.account.withdraw(Double.valueOf(this.amountField.getText())))
            {
                boolean bValidUsername = false;

                for(BankAccount account : Client.userList)
                {
                    if(this.usernameField.getText().equalsIgnoreCase(account.getUsername()))
                    {
                        account.deposit(Double.valueOf(this.amountField.getText()));
                        bValidUsername = true;

                        logToWriter.println(dateFormat.format(date) + " Transfer TO " + this.usernameField.getText() + " " + this.amountField.getText());
                        logFromWriter.println(dateFormat.format(date) + " Transfer FROM " + this.account.getUsername() + " " + this.amountField.getText());

                        logToWriter.flush();
                        logToWriter.close();
                        logFromWriter.flush();
                        logFromWriter.close();

                        HomeController homeController = new HomeController(this.primaryStage, this.account);
                        homeController.show();
                        homeController.previousIsSend();
                    }
                }

                if(!bValidUsername)
                    this.invalidUsernameLbl.setOpacity(1);
            }
            else
                this.insufficientFundsLbl.setOpacity(1);
        });
    }

    public void show()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Transfer.fxml"));

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
