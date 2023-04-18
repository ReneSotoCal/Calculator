/**
 * File: csci1302/ch16/MileageCalculator.java
 * Package: ch16
 * @author Christopher Williams
 * Created on: Apr 12, 2017
 * Last Modified: Apr 15, 2019
 * Description:  
 */
package ch16;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MileageCalculatorNoConversion extends Application {
// default values/strings
private double txtWidth = 125.0;
private String defaultCalc = String.format("%.2f", 0.00);
private String defaultEntry = String.format("%.2f", 0.00);
private String defaultMileage = "Miles";
private String defaultCapacity = "Gallons";
private String defaultResult = "MPG";
private String altResult = "L/100KM";
private String altMileage = "Kilometers";
private String altCapacity = "Liters";

// create UI components split by type
private Button btnCalc = new Button("Calculate");
private Button btnReset = new Button("Reset");

private Label lblDistance = new Label(defaultMileage);
private Label lblCapacity = new Label(defaultCapacity);
private Label lblResult = new Label(defaultResult);
private Label lblEffType = new Label("Efficiency Type");

private TextField tfDistance = new TextField(defaultEntry);
private TextField tfCapacity = new TextField(defaultEntry);
private TextField tfResult = new TextField(defaultCalc);

private ComboBox<String> cbConv = new ComboBox<>();
private GridPane mainPane = new GridPane();

public void start(Stage primaryStage) {

	// set preferences for UI components
	tfDistance.setMaxWidth(txtWidth);
	tfCapacity.setMaxWidth(txtWidth);
	tfResult.setMaxWidth(txtWidth);
	tfResult.setEditable(false);

	// create a main grid pane to hold items
	mainPane.setPadding(new Insets(10.0));
	mainPane.setHgap(txtWidth / 2.0);
	mainPane.setVgap(txtWidth / 12.0);

	// add items to mainPane
	mainPane.add(lblEffType, 0, 0);
	mainPane.add(cbConv, 1, 0);
	mainPane.add(lblDistance, 0, 2);
	mainPane.add(tfDistance, 1, 2);
	mainPane.add(lblCapacity, 0, 3);
	mainPane.add(tfCapacity, 1, 3);
	mainPane.add(lblResult, 0, 4);
	mainPane.add(tfResult, 1, 4);
	mainPane.add(btnReset, 0, 5);
	mainPane.add(btnCalc, 1, 5);

	cbConv.getItems().addAll(defaultResult, altResult);
	cbConv.setValue(defaultResult);
	
	btnCalc.setOnAction(e -> calcMileage());
	tfDistance.setOnAction(e -> calcMileage());
	tfCapacity.setOnAction(e -> calcMileage());
	tfResult.setOnAction(e -> calcMileage());
	cbConv.setOnAction(e -> changeLabels());
	btnReset.setOnAction(e -> {
		lblDistance.setText(defaultMileage);
		lblCapacity.setText(defaultCapacity);
		lblResult.setText(defaultResult);
		tfDistance.setText("0.00");
		tfCapacity.setText("0.00");
		tfResult.setText("0.00");
		cbConv.setValue(defaultResult);
	});
	
	// create a scene and place it in the stage
	Scene scene = new Scene(mainPane);

	// set and show stage
	primaryStage.setTitle("Mileage Calculator");
	primaryStage.setScene(scene);
	primaryStage.show();

	// stick default focus in first field for usability
	tfDistance.requestFocus();
}

private void changeLabels() {
// check which efficiency type is selected
if (cbConv.getValue().matches("L/100KM")) {
// update labels for L/100KM
lblDistance.setText(altMileage);
lblCapacity.setText(altCapacity);
lblResult.setText(altResult);
tfDistance.setText("0.00");
tfCapacity.setText("0.00");
tfResult.setText("0.00");
} else {
// update labels for MPG
lblDistance.setText(defaultMileage);
lblCapacity.setText(defaultCapacity);
lblResult.setText(defaultResult);
tfDistance.setText("0.00");
tfCapacity.setText("0.00");
tfResult.setText("0.00");
}
}

private void calcMileage() {
double distance = 0.0, capacity = 0.0;

// make sure to get numeric values only
if (tfCapacity.getText() != null && !tfCapacity.getText().isEmpty() && tfDistance.getText() != null
&& !tfDistance.getText().isEmpty()) {
distance = Double.parseDouble(tfDistance.getText());
capacity = Double.parseDouble(tfCapacity.getText());
}

//check for type of calculation
double result = 0.0;
if (cbConv.getValue().matches("L/100KM")) {
// liters / 100KM
result = (distance != 0) ? capacity / (distance / 100.0) : 0;
tfResult.setText(String.format("%.2f", result));
} else {
// MPG
result = (capacity != 0) ? distance / capacity : 0;
tfResult.setText(String.format("%.2f", result));
}
}
public static void main(String[] args) {
	launch(args);
}
}