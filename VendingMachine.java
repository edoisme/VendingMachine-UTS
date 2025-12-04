import java.util.ArrayList;

public class VendingMachine {
    // Atribut private (Enkapsulasi)
    private String machineId;
    private String location;
    private ArrayList<Product> products;
    private ArrayList<Transaction> transactionHistory;
    private double totalRevenue;

    // Constructor overloading - Constructor 1 (dengan parameter)
    public VendingMachine(String machineId, String location) {
        this.machineId = machineId;
        this.location = location;
        this.products = new ArrayList<>();
        this.transactionHistory = new ArrayList<>();
        this.totalRevenue = 0.0;
    }

    // Constructor overloading - Constructor 2 (tanpa parameter)
    public VendingMachine() {
        this.machineId = "VM001";
        this.location = "Unknown Location";
        this.products = new ArrayList<>();
        this.transactionHistory = new ArrayList<>();
        this.totalRevenue = 0.0;
    }

    // Getter dan Setter
    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    // Method 1: Tambah produk ke vending machine
    public void addProduct(Product product) {
        products.add(product);
        System.out.println("Product '" + product.getName() + "' added successfully!");
    }

    // Method 2: Tampilkan semua produk
    public void displayAllProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available in the vending machine.");
            return;
        }

        System.out.println("\n╔════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                    VENDING MACHINE PRODUCTS                            ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════════╣");
        System.out.println("  Machine ID: " + machineId + " | Location: " + location);
        System.out.println("════════════════════════════════════════════════════════════════════════");
        System.out.printf("  %-10s %-20s %-12s %-5s %-15s%n", 
                         "ID", "Name", "Price", "Stock", "Category");
        System.out.println("────────────────────────────────────────────────────────────────────────");
        
        for (Product product : products) {
            System.out.println("  " + product.toString());
        }
        System.out.println("╚════════════════════════════════════════════════════════════════════════╝\n");
    }

    // Method 3: Cari produk berdasarkan ID (dengan parameter, return Product)
    public Product findProductById(String productId) {
        for (Product product : products) {
            if (product.getProductId().equalsIgnoreCase(productId)) {
                return product;
            }
        }
        return null;
    }

    // Method 4: Cari produk berdasarkan nama (dengan parameter, return ArrayList)
    public ArrayList<Product> findProductByName(String name) {
        ArrayList<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(product);
            }
        }
        return result;
    }

    // Method 5: Proses pembelian produk (dengan parameter, return boolean)
    public boolean purchaseProduct(String productId, int quantity, double payment) {
        Product product = findProductById(productId);
        
        if (product == null) {
            System.out.println("Product not found!");
            return false;
        }

        if (!product.isAvailable(quantity)) {
            System.out.println("Insufficient stock! Available: " + product.getStock());
            return false;
        }

        double totalPrice = product.calculateTotal(quantity);
        
        if (payment < totalPrice) {
            System.out.println("Insufficient payment! Required: Rp " + String.format("%.2f", totalPrice));
            return false;
        }

        // Buat transaksi
        String transactionId = "TRX" + String.format("%05d", transactionHistory.size() + 1);
        Transaction transaction = new Transaction(transactionId, product, quantity, payment);
        
        // Proses transaksi
        if (transaction.processTransaction()) {
            transactionHistory.add(transaction);
            totalRevenue += totalPrice;
            transaction.printReceipt();
            return true;
        }
        
        return false;
    }

    // Method 6: Tampilkan riwayat transaksi
    public void displayTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transaction history available.");
            return;
        }

        System.out.println("\n╔════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                      TRANSACTION HISTORY                               ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════════╣");
        System.out.printf("  %-12s %-20s %-3s %-12s %-16s%n", 
                         "Trans ID", "Product", "Qty", "Total", "Date");
        System.out.println("────────────────────────────────────────────────────────────────────────");
        
        for (Transaction transaction : transactionHistory) {
            System.out.println("  " + transaction.getTransactionSummary());
        }
        System.out.println("────────────────────────────────────────────────────────────────────────");
        System.out.println("  Total Revenue: Rp " + String.format("%.2f", totalRevenue));
        System.out.println("╚════════════════════════════════════════════════════════════════════════╝\n");
    }

    // Method 7: Tampilkan produk dengan stok rendah
    public void displayLowStockProducts() {
        System.out.println("\n=== LOW STOCK ALERT ===");
        boolean hasLowStock = false;
        
        for (Product product : products) {
            if (product.isLowStock()) {
                System.out.println("⚠ " + product.getName() + " - Stock: " + product.getStock());
                hasLowStock = true;
            }
        }
        
        if (!hasLowStock) {
            System.out.println("All products have sufficient stock.");
        }
        System.out.println();
    }

    // Method 8: Hitung total produk (return int)
    public int getTotalProducts() {
        return products.size();
    }

    // Method 9: Hitung total item di mesin (return int)
    public int getTotalItems() {
        int total = 0;
        for (Product product : products) {
            total += product.getStock();
        }
        return total;
    }

    // Method 10: Informasi mesin vending
    public void displayMachineInfo() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║      VENDING MACHINE INFORMATION       ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("  Machine ID      : " + machineId);
        System.out.println("  Location        : " + location);
        System.out.println("  Total Products  : " + getTotalProducts());
        System.out.println("  Total Items     : " + getTotalItems());
        System.out.println("  Total Revenue   : Rp " + String.format("%.2f", totalRevenue));
        System.out.println("  Transactions    : " + transactionHistory.size());
        System.out.println("╚════════════════════════════════════════╝\n");
    }
}
