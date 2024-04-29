import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class depositInfo {
private String item;
private String vendor;
private double amount = 0.0;


    public depositInfo(String item, String vendor, double amount) {
        this.item = item;
        this.vendor = vendor;
        this.amount = amount;

    }

    @Override
    public String toString() {
        return "Accounting{" +
                "item='" + item + '\'' +
                ", vendor='" + vendor + '\'' +
                ", amount=" + amount +
                '}';
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
    public String getDateTime() {
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter =DateTimeFormatter.ofPattern("yyyy-mm-dd|hh:mm:ss");
        String formattedDateTime = today.format(formatter);
        return formattedDateTime;
    }
}
