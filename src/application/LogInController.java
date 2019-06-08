package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogInController
{
    private Stage primaryStage;

	private ArrayList<BankAccount> list;

	private @FXML TextField usernameField;
	private @FXML PasswordField passwordField;
	private @FXML Button signUpBtn, logInBtn;
	private @FXML Label incorrectLbl;

	public LogInController(Stage primaryStage)
	{
	    this.primaryStage = primaryStage;
		this.list = new ArrayList<BankAccount>();
	}

	public void initialize()
	{
	    logInBtn.setOnAction(e ->
	    {
	        HomeController homeController = new HomeController(new BankAccount(usernameField.getText(), passwordField.getText()));
	        homeController.show(primaryStage);
	    });

		signUpBtn.setOnAction(e -> list.add(new BankAccount(usernameField.getText(), passwordField.getText())));
	}

	public void show()
	{
		try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LogIn.fxml"));

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
