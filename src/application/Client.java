package application;

import javafx.application.Application;
import javafx.stage.Stage;


public class Client extends Application
{
	@Override
	public void start(Stage primaryStage)
	{
		primaryStage.show();
		LogInController logIn = new LogInController(primaryStage);
		logIn.show();
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
