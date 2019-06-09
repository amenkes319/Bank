package application;

import java.io.IOException;
import java.text.DecimalFormat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HomeController
{
    BankAccount account;

    private Stage primaryStage;

    @FXML private Label welcomeLbl, balanceLbl;
    @FXML private Button signOutBtn, depositBtn, withdrawBtn;

    public HomeController(Stage primaryStage, BankAccount account)
    {
        this.primaryStage = primaryStage;
        this.account = account;
    }

    public void initialize()
    {
        DecimalFormat moneyFormat = new DecimalFormat("$0.00");
        
        this.welcomeLbl.setText("Welcome " + this.account.getUsername() + "!");
        this.balanceLbl.setText("Your balance is " + moneyFormat.format(this.account.getBalance()));

        this.signOutBtn.setOnAction(e ->
        {
            LogInController logInController = new LogInController(this.primaryStage);
            logInController.show();
        });

        this.depositBtn.setOnAction(e ->
        {
            account.deposit(13.15);
            this.balanceLbl.setText("Your balance is " + moneyFormat.format(this.account.getBalance()));
            System.out.println(account);
        });
        
        this.withdrawBtn.setOnAction(e ->
        {
            
        });
    }

    public void show(Stage primaryStage)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));

            loader.setController(this);
            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.centerOnScreen();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
