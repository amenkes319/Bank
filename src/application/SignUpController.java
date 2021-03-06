package application;

import java.io.File;
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
    private @FXML Label usernameUnavailableLbl, notMatchingPasswordLbl, notEnoughCharactersLbl;

    public SignUpController(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
    }

    public void initialize()
    {
        signUpBtn.setOnAction(e ->
        {
            this.usernameUnavailableLbl.setOpacity(0);
            this.notMatchingPasswordLbl.setOpacity(0);
            this.notEnoughCharactersLbl.setOpacity(0);

            boolean bUsesUsername = false;

            for(int i = 0; i < Client.userList.size(); i++)
            {
                if(this.usernameField.getText().equalsIgnoreCase(Client.userList.get(i).getUsername()))
                     bUsesUsername = true;
            }

            if(bUsesUsername)
                this.usernameUnavailableLbl.setOpacity(1);
            else if(passwordField.getText().length() < 8)
                this.notEnoughCharactersLbl.setOpacity(1);
            else if(!passwordField.getText().equals(confirmPasswordField.getText()))
                this.notMatchingPasswordLbl.setOpacity(1);
            else
            {
                File log = new File("src\\logs\\" + usernameField.getText() + "Log.txt");
                try
                {
                    log.createNewFile();
                }
                catch (IOException exception)
                {
                    System.out.println("File not created!");
                }

                Client.userList.add(new BankAccount(this.usernameField.getText(), this.passwordField.getText()));

                LogInController logInController = new LogInController(this.primaryStage);
                logInController.show();

            }
        });

        logInBtn.setOnAction(e ->
        {
            LogInController logInController = new LogInController(primaryStage);
            logInController.show();
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
