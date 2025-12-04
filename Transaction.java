import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    // Atribut private (Enkapsulasi)
    private String transactionId;
    private Product product;
    private int quantity;
    private double totalPrice;
    private double paymentAmount;
    private double changeAmount;
    private LocalDateTime transactionDate;

    // Constructor overloading - Constructor 1 (lengkap)
    public Transaction(String transactionId, Product product, int quantity, double paymentAmount) {
        this.transactionId = transactionId;
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = product.calculateTotal(quantity);
        this.paymentAmount = paymentAmount;
        this.changeAmount = paymentAmount - totalPrice;
        this.transactionDate = LocalDateTime.now();
    }

    // Constructor overloading - Constructor 2 (tanpa payment amount)
    public Transaction(String transactionId, Product product, int quantity) {
        this.transactionId = transactionId;
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = product.calculateTotal(quantity);
        this.paymentAmount = 0.0;
        this.changeAmount = 0.0;
        this.transactionDate = LocalDateTime.now();
    }

    // Getter dan Setter
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
        this.changeAmount = paymentAmount - totalPrice;
    }

    public double getChangeAmount() {
        return changeAmount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    // Method 1: Proses transaksi (return boolean)
    public boolean processTransaction() {
        if (product.isAvailable(quantity)) {
            if (paymentAmount >= totalPrice) {
                product.reduceStock(quantity);
                return true;
            } else {
                System.out.println("Payment insufficient! Need: Rp " + String.format("%.2f", totalPrice));
                return false;
            }
        } else {
            System.out.println("Product out of stock!");
            return false;
        }
    }

    // Method 2: Hitung kembalian (dengan parameter, return double)
    public double calculateChange(double payment) {
        return payment - totalPrice;
    }

    // Method 3: Validasi pembayaran (dengan parameter, return boolean)
    public boolean validatePayment(double payment) {
        return payment >= totalPrice;
    }

    // Method 4: Cetak struk transaksi
    public void printReceipt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║         VENDING MACHINE RECEIPT        ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("  Transaction ID : " + transactionId);
        System.out.println("  Date           : " + transactionDate.format(formatter));
        System.out.println("----------------------------------------");
        System.out.println("  Product        : " + product.getName());
        System.out.println("  Price          : Rp " + String.format("%.2f", product.getPrice()));
        System.out.println("  Quantity       : " + quantity);
        System.out.println("----------------------------------------");
        System.out.println("  Total          : Rp " + String.format("%.2f", totalPrice));
        System.out.println("  Payment        : Rp " + String.format("%.2f", paymentAmount));
        System.out.println("  Change         : Rp " + String.format("%.2f", changeAmount));
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("      Thank you for your purchase!");
        System.out.println();
    }

    // Method 5: Format transaksi untuk history (return String)
    public String getTransactionSummary() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return String.format("%-12s %-20s %-3d Rp%-10.2f %s", 
                           transactionId, 
                           product.getName(), 
                           quantity, 
                           totalPrice,
                           transactionDate.format(formatter));
    }

    // Method 6: Cek apakah transaksi sukses
    public boolean isSuccessful() {
        return paymentAmount >= totalPrice && product.getStock() >= 0;
    }
}
