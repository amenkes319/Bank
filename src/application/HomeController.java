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

    @FXML private Label welcomeLbl, balanceLbl, depositLbl, withdrawSuccessLbl, withdrawErrorLbl;
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
            DepositController depositController = new DepositController(this.primaryStage, this.account);
            depositController.show();
        });

        this.withdrawBtn.setOnAction(e ->
        {
            WithdrawController withdrawController = new WithdrawController(this.primaryStage, this.account);
            withdrawController.show();
        });

        this.depositLbl.setOpacity(0);
        this.withdrawSuccessLbl.setOpacity(0);
        this.withdrawErrorLbl.setOpacity(0);
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

    public void previousIsDeposit()
    {
        this.depositLbl.setOpacity(1);
    }

    public void previousIsWithdraw(boolean success)
    {
        if(success)
            this.withdrawSuccessLbl.setOpacity(1);
        else
            this.withdrawErrorLbl.setOpacity(1);
        System.out.println(success);
    }
}
