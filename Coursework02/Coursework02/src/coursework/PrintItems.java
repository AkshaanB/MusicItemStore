package coursework;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class PrintItems extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox vb = new VBox();
        vb.setSpacing(10);
        vb.setPadding(new Insets(10,0,0,10));

        HBox hb = new HBox();

        Label greeting = new Label("List of items");
        greeting.setTextFill(Color.DARKRED);
        greeting.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD,75));
        greeting.setTextAlignment(TextAlignment.CENTER);
        greeting.setPadding(new Insets(150,30,50,740));
        vb.getChildren().add(greeting);

        TableView table = new TableView();
        TableColumn firstCol = new TableColumn("_id ObjectId");
        TableColumn secondCol = new TableColumn("Type");
        TableColumn thirdCol = new TableColumn("ItemId");
        TableColumn fourthCol = new TableColumn("Title");
        TableColumn fifthCol = new TableColumn("Genre");
        TableColumn sixthCol = new TableColumn("Release Date");
        TableColumn seventhCol = new TableColumn("Artist");
        TableColumn eighthCol = new TableColumn("Price");
        TableColumn ninthCol = new TableColumn("Duration");
        TableColumn tenthCol = new TableColumn("Speed");

        table.getColumns().addAll(firstCol,secondCol,thirdCol,fourthCol,fifthCol,sixthCol,seventhCol,eighthCol,ninthCol,tenthCol);
        firstCol.prefWidthProperty().bind(table.widthProperty().multiply(.1));
        secondCol.prefWidthProperty().bind(table.widthProperty().multiply(.1));
        thirdCol.prefWidthProperty().bind(table.widthProperty().multiply(.1));
        fourthCol.prefWidthProperty().bind(table.widthProperty().multiply(.1));
        fifthCol.prefWidthProperty().bind(table.widthProperty().multiply(.1));
        sixthCol.prefWidthProperty().bind(table.widthProperty().multiply(.1));
        seventhCol.prefWidthProperty().bind(table.widthProperty().multiply(.1));
        eighthCol.prefWidthProperty().bind(table.widthProperty().multiply(.1));
        ninthCol.prefWidthProperty().bind(table.widthProperty().multiply(.1));
        tenthCol.prefWidthProperty().bind(table.widthProperty().multiply(.1));

        vb.getChildren().add(table);

        BackgroundFill backgroundFill = new BackgroundFill(Color.GHOSTWHITE, CornerRadii.EMPTY,Insets.EMPTY);
        Background background = new Background(backgroundFill);
        vb.setBackground(background);
        primaryStage.setTitle("Print List of Items");
        primaryStage.setScene(new Scene(vb, 300, 275));
        primaryStage.show();
    }
}
