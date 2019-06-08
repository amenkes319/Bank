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

public class SignUpController
{
    private Stage primaryStage;

    private @FXML TextField usernameField;
    private @FXML PasswordField passwordField, confirmPasswordField;
    private @FXML Button signUpBtn, logInBtn;
    private @FXML Label incorrectPasswordLbl, notEnoughCharactersLbl;

    public SignUpController(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
    }

    public void initialize()
    {
        signUpBtn.setOnAction(e ->
        {
            if(passwordField.getText().length() >= 8 && passwordField.getText().equals(confirmPasswordField.getText()))
            {

            }
        });
    }

    public void show()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUp.fxml"));

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
