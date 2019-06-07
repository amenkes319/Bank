package application;

import java.io.IOException;

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

	private @FXML TextField usernameField;
	private @FXML PasswordField passwordField;
	private @FXML Button signUpBtn, logInBtn;
	private @FXML Label incorrectLbl, passwordLbl;

	public LogInController(Stage primaryStage)
	{
		this.primaryStage = primaryStage;
	}

	public void initialize()
	{
		signUpBtn.setOnAction(e -> incorrectLbl.setOpacity(1));
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
