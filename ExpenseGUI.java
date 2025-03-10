import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ExpenseGUI extends Application {
    private DatabaseManager dbManager = new DatabaseManager();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox vbox = new VBox(10);

        TextField nameField = new TextField();
        nameField.setPromptText("Expense Name");

        TextField amountField = new TextField();
        amountField.setPromptText("Amount");

        Button addButton = new Button("Add Expense");
        addButton.setOnAction(e -> {
            String name = nameField.getText();
            double amount = Double.parseDouble(amountField.getText());
            dbManager.addExpense(new Expense(name, amount));
            nameField.clear();
            amountField.clear();
        });

        ListView<String> expenseListView = new ListView<>();
        Button refreshButton = new Button("Refresh List");
        refreshButton.setOnAction(e -> {
            expenseListView.getItems().clear();
            dbManager.viewExpenses();
        });

        vbox.getChildren().addAll(nameField, amountField, addButton, refreshButton, expenseListView);
        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Expense Tracker");
        primaryStage.show();
    }
}
