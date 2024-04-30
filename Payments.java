import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Payments {
    private String item;
    private String paidTo;
    private double amount;

    public Payments(String item, String paidTo, double amount){
        this.item = item;
        this.paidTo = paidTo;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaidTo() {
        return paidTo;
    }

    public void setPaidTo(String paidTo) {
        this.paidTo = paidTo;
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

    @Override
    public String toString() {
        return "Payments{" +
                "item='" + item + '\'' +
                ", paidTo='" + paidTo + '\'' +
                ", amount=" + amount +
                '}';
    }
}