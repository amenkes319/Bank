package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeController
{
    BankAccount account;

    public HomeController(BankAccount account)
    {
        this.account = account;
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
