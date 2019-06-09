package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class WithdrawController
{
    private BankAccount account;

    @FXML private ArrayList<Button> buttonList;
    @FXML private TextField customField;

    private Stage primaryStage;

    public WithdrawController(Stage primaryStage, BankAccount account)
    {
        this.primaryStage = primaryStage;
        this.account = account;
    }

    public void initialize()
    {
        for(int i = 0; i < this.buttonList.size(); i++)
            this.buttonList.get(i).setOnAction(e ->
            {
                boolean bSuccess;

                if(!((Button) e.getSource()).getText().equals("Custom"))
                    bSuccess = this.account.withdraw(Double.valueOf(((Button) e.getSource()).getText().substring(1)));
                else
                    bSuccess = this.account.withdraw(Double.valueOf(this.customField.getText()));

                HomeController homeController = new HomeController(this.primaryStage, this.account);
                homeController.show(this.primaryStage);
                homeController.previousIsWithdraw(bSuccess);
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
