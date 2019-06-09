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

    @FXML private Label welcomeLbl, balanceLbl, depositLbl, withdrawLbl, transferLbl;
    @FXML private Button signOutBtn, depositBtn, withdrawBtn, transferBtn;

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

        this.transferBtn.setOnAction(e ->
        {
            TransferController transferController = new TransferController(this.primaryStage, this.account);
            transferController.show();
        });

        this.depositLbl.setOpacity(0);
        this.withdrawLbl.setOpacity(0);
        this.transferLbl.setOpacity(0);
    }

    public void show()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));

            loader.setController(this);
            this.primaryStage.setScene(new Scene(loader.load()));
            this.primaryStage.centerOnScreen();
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

    public void previousIsWithdraw()
    {
        this.withdrawLbl.setOpacity(1);
    }

    public void previousIsSend()
    {
        this.transferLbl.setOpacity(1);
    }
}
