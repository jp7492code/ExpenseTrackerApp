import java.sql.*;
import java.io.*;

public class ReportGenerator {
    private Connection conn;

    public ReportGenerator() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:expenses.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void generateCSVReport(String filename) {
        String selectSQL = "SELECT * FROM expenses";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL);
             BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("ID, Name, Amount
");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double amount = rs.getDouble("amount");
                writer.write(id + ", " + name + ", " + amount + "
");
            }
            System.out.println("Report generated at " + filename);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
