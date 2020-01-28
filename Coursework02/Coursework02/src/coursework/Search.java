package coursework;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoDatabase;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.sql.Connection;
import java.util.Collection;

public class Search extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox vb = new VBox();
        vb.setPadding(new Insets(0,0,0,60));
        vb.setSpacing(120);

        HBox hb = new HBox();

        Label greeting = new Label("Welcome!");
        greeting.setTextFill(Color.DARKRED);
        greeting.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD,75));
        greeting.setTextAlignment(TextAlignment.CENTER);
        greeting.setPadding(new Insets(150,30,50,760));
        vb.getChildren().add(greeting);

        Label text = new Label("Search an item   :     ");
        text.setFont(Font.font("Times New Roman",FontWeight.BOLD,30));
        text.setTextFill(Color.BLACK);
        text.setPadding(new Insets(0,0,0,695));
        text.setTextAlignment(TextAlignment.LEFT);
        TextField textField = new TextField();
        textField.setPromptText("        Search by title");
        GridPane root = new GridPane();
        root.addRow(0,text,textField);
        vb.getChildren().addAll(root);


        javafx.scene.control.Button button = new Button("Submit");
        button.setAlignment(Pos.BOTTOM_RIGHT);
        button.setStyle("-fx-background-color: Blue;-fx-text-fill: White;-fx-padding: 10px;-fx-font-weight: bold;-fx-font-family: 'Times New Roman';-fx-font-size: 20px;");
        button.setOnAction(action -> {
            System.out.println(textField.getText());
            primaryStage.close();
        });
        vb.getChildren().add(button);


        BackgroundFill backgroundFill = new BackgroundFill(Color.GHOSTWHITE, CornerRadii.EMPTY,Insets.EMPTY);
        Background background = new Background(backgroundFill);
        vb.setBackground(background);
        primaryStage.setTitle("Search Item");
        primaryStage.setScene(new Scene(vb, 300, 275));
        primaryStage.show();

    }
    public static MongoDatabase getConnection() throws MongoException{
        MongoClient client = new MongoClient("localhost",27017);

        MongoDatabase database = client.getDatabase("TestMusic");
        return database;
    }
}
