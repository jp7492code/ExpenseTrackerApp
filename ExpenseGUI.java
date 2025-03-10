import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class ExpenseGUI extends Application {
    private TableView<Expense> table;
    private TextField descriptionField, amountField, categoryField, dateField;
    private ObservableList<Expense> expenseList;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Expense Tracker");

        // Table
        table = new TableView<>();
        setupTable();

        // Input Fields
        descriptionField = new TextField();
        descriptionField.setPromptText("Description");

        amountField = new TextField();
        amountField.setPromptText("Amount");

        categoryField = new TextField();
        categoryField.setPromptText("Category");

        dateField = new TextField();
        dateField.setPromptText("YYYY-MM-DD");

        Button addButton = new Button("Add Expense");
        addButton.setOnAction(e -> addExpense());

        Button generateReportButton = new Button("Generate Report (CSV)");
        generateReportButton.setOnAction(e -> ReportGenerator.generateCSV());

        HBox inputLayout = new HBox(10, descriptionField, amountField, categoryField, dateField, addButton, generateReportButton);
        inputLayout.setPadding(new Insets(10));

        VBox layout = new VBox(10, table, inputLayout);
        layout.setPadding(new Insets(20));

        refreshTable();

        Scene scene = new Scene(layout, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setupTable() {
        TableColumn<Expense, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(data -> javafx.beans.property.SimpleStringProperty(data.getValue().getDescription()));

        TableColumn<Expense, Double> amountColumn = new TableColumn<>("Amount ($)");
        amountColumn.setCellValueFactory(data -> javafx.beans.property.SimpleDoubleProperty(data.getValue().getAmount()).asObject());

        TableColumn<Expense, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(data -> javafx.beans.property.SimpleStringProperty(data.getValue().getCategory()));

        TableColumn<Expense, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(data -> javafx.beans.property.SimpleStringProperty(data.getValue().getDate()));

        table.getColumns().addAll(descriptionColumn, amountColumn, categoryColumn, dateColumn);
    }

    private void addExpense() {
        String description = descriptionField.getText();
        String amountText = amountField.getText();
        String category = categoryField.getText();
        String date = dateField.getText();

        if (description.isEmpty() || amountText.isEmpty() || category.isEmpty() || date.isEmpty()) {
            showAlert("Error", "Please fill all fields!");
            return;
        }

        try {
            double amount = Double.parseDouble(amountText);
            Expense expense = new Expense(description, amount, category, date);
            DatabaseManager.addExpense(expense);
            refreshTable();
            clearFields();
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid amount entered!");
        }
    }

    private void refreshTable() {
        List<Expense> expenses = DatabaseManager.getExpenses();
        expenseList = FXCollections.observableArrayList(expenses);
        table.setItems(expenseList);
    }

    private void clearFields() {
        descriptionField.clear();
        amountField.clear();
        categoryField.clear();
        dateField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
