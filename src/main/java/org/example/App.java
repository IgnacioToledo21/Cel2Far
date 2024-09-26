package org.example;

import javafx.application.Application;
import javafx.beans.binding.DoubleExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.util.converter.NumberStringConverter;

public class App extends Application {

    //Vista

    private TextField celsiusText, fahrenheitText;

    //Modelo que manipula la informacion

    private DoubleProperty celsius = new SimpleDoubleProperty();

    @Override
    public void start (Stage primarystage) throws Exception {

        celsiusText = new TextField();
        celsiusText.setPrefColumnCount(5);

        fahrenheitText = new TextField();
        fahrenheitText.setPrefColumnCount(5);
        fahrenheitText.setEditable(false);

        HBox celsiusBox = new HBox(5, new Label("Celsius"), celsiusText);
        celsiusBox.setAlignment(Pos.BASELINE_CENTER);

        HBox fahrenheitBox = new HBox(5, new Label("Fahrenheit"), fahrenheitText);
        fahrenheitBox.setAlignment(Pos.BASELINE_CENTER);

        VBox root = new VBox(5, celsiusBox, fahrenheitBox);
        root.setAlignment(Pos.CENTER);
        root.setFillWidth(false);

        Scene scene = new Scene(root, 320, 200);

        primarystage.setTitle("Celsius to Fahrenheit");
        primarystage.setScene(scene);
        primarystage.show();

        //Vínculos, Bindings


        celsiusText.textProperty().bindBidirectional(celsius, new NumberStringConverter());

        //(grados centígrados × 9/5) +32.

        DoubleExpression cel2far = celsius.multiply(9.0/5.0).add(32);

        fahrenheitText.textProperty().bind(cel2far.asString("%.2f"));

        //Vinculacion de cuadros de texto, lo que se escriba en celsius tambien sale en fahrenheit
        //fahrenheitText.textProperty().bind(celsiusText.textProperty());
    }
}





