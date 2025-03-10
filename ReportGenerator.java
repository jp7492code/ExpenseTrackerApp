import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportGenerator {
    public static void generateCSV() {
        List<Expense> expenses = DatabaseManager.getExpenses();
        String fileName = "expense_report.csv";

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.append("ID,Description,Amount,Category,Date\n");
            for (Expense e : expenses) {
                writer.append(String.format("%d,%s,%.2f,%s,%s\n", 
                        e.getId(), e.getDescription(), e.getAmount(), e.getCategory(), e.getDate()));
            }
            System.out.println("CSV Report Generated: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
