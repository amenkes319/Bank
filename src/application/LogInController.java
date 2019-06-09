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

	private @FXML TextField usernameField;
	private @FXML PasswordField passwordField;
	private @FXML Button signUpBtn, logInBtn;
	private @FXML Label incorrectLbl;

	public LogInController(Stage primaryStage)
	{
	    this.primaryStage = primaryStage;
	}

	public void initialize()
	{
	    logInBtn.setOnAction(e ->
	    {
	        incorrectLbl.setOpacity(0);

	        boolean isCorrectUsername = false;
	        boolean isCorrectPassword = false;
	        
	        int accountIndex = 0;

	        for(int i = 0; i < Client.list.size() && !(isCorrectUsername && isCorrectPassword); i++)
	        {
	            if(usernameField.getText().equalsIgnoreCase(Client.list.get(i).getUsername()))
	            {
	                isCorrectUsername = true;
	                if(passwordField.getText().equals(Client.list.get(i).getPassword()))
	                {
	                    isCorrectPassword = true;
	                    accountIndex = i;
	                }
	                else
	                    isCorrectUsername = false;
	            }
	        }

	        if(!(isCorrectUsername && isCorrectPassword))
	            incorrectLbl.setOpacity(1);
	        else
            {
                HomeController homeController = new HomeController(this.primaryStage, Client.list.get(accountIndex));
                homeController.show(primaryStage);
            }
	    });

		signUpBtn.setOnAction(e ->
		{
		    SignUpController signUpController = new SignUpController(primaryStage);
		    signUpController.show();
		});
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
