import java.sql.*;

public class DatabaseManager {
    private Connection conn;

    public DatabaseManager() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:expenses.db");
            createTableIfNotExists();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTableIfNotExists() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS expenses (" +
                                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                "name TEXT, " +
                                "amount REAL)";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addExpense(Expense expense) {
        String insertSQL = "INSERT INTO expenses (name, amount) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, expense.getName());
            pstmt.setDouble(2, expense.getAmount());
            pstmt.executeUpdate();
        } catch (SQLException e)            {
            e.printStackTrace();
        }
    }

    public void viewExpenses() {
        String selectSQL = "SELECT * FROM expenses";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double amount = rs.getDouble("amount");
                System.out.println("ID: " + id + ", Name: " + name + ", Amount: " + amount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void generateReport() {
        String selectSQL = "SELECT * FROM expenses";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {
            StringBuilder report = new StringBuilder();
            report.append("ID, Name, Amount
");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double amount = rs.getDouble("amount");
                report.append(id).append(", ").append(name).append(", ").append(amount).append("
");
            }
            // Assuming you want to print to console or save as a CSV
            System.out.println(report.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
