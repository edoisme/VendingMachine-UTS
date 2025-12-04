import java.util.Scanner;
import java.util.ArrayList;

public class VendingMachineApp {
    private static Scanner scanner = new Scanner(System.in);
    private static VendingMachine vendingMachine;

    public static void main(String[] args) {
        // Inisialisasi Vending Machine
        vendingMachine = new VendingMachine("VM001", "ITTS Campus - Building A");

        // Tambah produk awal menggunakan constructor overloading
        // Constructor dengan semua parameter
        Product p1 = new Product("P001", "Coca Cola", 8000, 15, "Beverages");
        Product p2 = new Product("P002", "Sprite", 7500, 12, "Beverages");
        Product p3 = new Product("P003", "KitKat", 5000, 20, "Snacks");
        
        // Constructor tanpa category (menggunakan default "General")
        Product p4 = new Product("P004", "Pocari Sweat", 9000, 10);
        Product p5 = new Product("P005", "Cheetos", 6000, 8);

        // Tambahkan produk ke vending machine
        vendingMachine.addProduct(p1);
        vendingMachine.addProduct(p2);
        vendingMachine.addProduct(p3);
        vendingMachine.addProduct(p4);
        vendingMachine.addProduct(p5);

        System.out.println("\n╔════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║              WELCOME TO ITTS VENDING MACHINE SYSTEM                    ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════╝");

        // Menu utama
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput("Choose menu: ");

            switch (choice) {
                case 1:
                    viewAllProducts();
                    break;
                case 2:
                    addNewProduct();
                    break;
                case 3:
                    searchProduct();
                    break;
                case 4:
                    buyProduct();
                    break;
                case 5:
                    viewTransactionHistory();
                    break;
                case 6:
                    restockProduct();
                    break;
                case 7:
                    checkLowStock();
                    break;
                case 8:
                    viewMachineInfo();
                    break;
                case 0:
                    System.out.println("\n╔════════════════════════════════════════╗");
                    System.out.println("║   Thank you for using our system!      ║");
                    System.out.println("╚════════════════════════════════════════╝\n");
                    running = false;
                    break;
                default:
                    System.out.println("\n❌ Invalid choice! Please try again.\n");
            }
        }

        scanner.close();
    }

    // Menampilkan menu utama
    private static void displayMenu() {
        System.out.println("╔════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                       VENDING MACHINE MENU                             ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║  1. View All Products                                                  ║");
        System.out.println("║  2. Add New Product                                                    ║");
        System.out.println("║  3. Search Product                                                     ║");
        System.out.println("║  4. Buy Product                                                        ║");
        System.out.println("║  5. View Transaction History                                           ║");
        System.out.println("║  6. Restock Product                                                    ║");
        System.out.println("║  7. Check Low Stock Alert                                              ║");
        System.out.println("║  8. View Machine Information                                           ║");
        System.out.println("║  0. Exit                                                               ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════╝");
    }

    // Menu 1: Lihat semua produk
    private static void viewAllProducts() {
        vendingMachine.displayAllProducts();
        pauseScreen();
    }

    // Menu 2: Tambah produk baru
    private static void addNewProduct() {
        System.out.println("\n=== ADD NEW PRODUCT ===");
        
        String id = getStringInput("Product ID: ");
        String name = getStringInput("Product Name: ");
        double price = getDoubleInput("Price: Rp ");
        int stock = getIntInput("Stock: ");
        String category = getStringInput("Category: ");

        Product newProduct = new Product(id, name, price, stock, category);
        vendingMachine.addProduct(newProduct);
        
        System.out.println("\n✅ Product added successfully!\n");
        pauseScreen();
    }

    // Menu 3: Cari produk
    private static void searchProduct() {
        System.out.println("\n=== SEARCH PRODUCT ===");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Name");
        int choice = getIntInput("Choose: ");

        if (choice == 1) {
            String id = getStringInput("Enter Product ID: ");
            Product product = vendingMachine.findProductById(id);
            
            if (product != null) {
                System.out.println("\n✅ Product Found:");
                product.displayInfo();
            } else {
                System.out.println("\n❌ Product not found!");
            }
        } else if (choice == 2) {
            String name = getStringInput("Enter Product Name: ");
            ArrayList<Product> results = vendingMachine.findProductByName(name);
            
            if (results.isEmpty()) {
                System.out.println("\n❌ No products found!");
            } else {
                System.out.println("\n✅ Found " + results.size() + " product(s):");
                for (Product p : results) {
                    p.displayInfo();
                }
            }
        } else {
            System.out.println("\n❌ Invalid choice!");
        }
        
        pauseScreen();
    }

    // Menu 4: Beli produk
    private static void buyProduct() {
        System.out.println("\n=== BUY PRODUCT ===");
        
        vendingMachine.displayAllProducts();
        
        String productId = getStringInput("Enter Product ID: ");
        Product product = vendingMachine.findProductById(productId);
        
        if (product == null) {
            System.out.println("\n❌ Product not found!");
            pauseScreen();
            return;
        }

        System.out.println("\nSelected Product: " + product.getName());
        System.out.println("Price: Rp " + String.format("%.2f", product.getPrice()));
        System.out.println("Available Stock: " + product.getStock());
        
        int quantity = getIntInput("\nEnter Quantity: ");
        
        if (quantity <= 0) {
            System.out.println("\n❌ Invalid quantity!");
            pauseScreen();
            return;
        }

        double totalPrice = product.calculateTotal(quantity);
        System.out.println("\nTotal Price: Rp " + String.format("%.2f", totalPrice));
        
        double payment = getDoubleInput("Enter Payment: Rp ");
        
        // Contoh pass by value: nilai payment dikirim sebagai copy ke method
        boolean success = vendingMachine.purchaseProduct(productId, quantity, payment);
        
        if (success) {
            System.out.println("\n✅ Transaction completed successfully!");
        } else {
            System.out.println("\n❌ Transaction failed!");
        }
        
        pauseScreen();
    }

    // Menu 5: Lihat riwayat transaksi
    private static void viewTransactionHistory() {
        vendingMachine.displayTransactionHistory();
        pauseScreen();
    }

    // Menu 6: Restock produk
    private static void restockProduct() {
        System.out.println("\n=== RESTOCK PRODUCT ===");
        
        vendingMachine.displayAllProducts();
        
        String productId = getStringInput("Enter Product ID to restock: ");
        Product product = vendingMachine.findProductById(productId);
        
        if (product == null) {
            System.out.println("\n❌ Product not found!");
        } else {
            System.out.println("\nCurrent Stock: " + product.getStock());
            int additionalStock = getIntInput("Enter additional stock: ");
            
            product.addStock(additionalStock);
            System.out.println("✅ Stock updated successfully!");
        }
        
        pauseScreen();
    }

    // Menu 7: Cek stok rendah
    private static void checkLowStock() {
        vendingMachine.displayLowStockProducts();
        pauseScreen();
    }

    // Menu 8: Info mesin vending
    private static void viewMachineInfo() {
        vendingMachine.displayMachineInfo();
        pauseScreen();
    }

    // Helper method: input integer
    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("❌ Invalid input! Please enter a number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return value;
    }

    // Helper method: input double
    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.print("❌ Invalid input! Please enter a number: ");
            scanner.next();
        }
        double value = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        return value;
    }

    // Helper method: input string
    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    // Helper method: pause screen
    private static void pauseScreen() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
}
```

---

## **📝 CARA MENGGUNAKAN FILE-FILE INI:**

### **Step 1: Copy Code**
1. Copy code **Product.java** di atas
2. Buka **Notepad** atau **VS Code** atau **IntelliJ**
3. Paste code
4. Save as `Product.java` (pastikan extensionnya .java)

### **Step 2: Ulangi untuk file lainnya**
- Copy **Transaction.java** → Save as `Transaction.java`
- Copy **VendingMachine.java** → Save as `VendingMachine.java`
- Copy **VendingMachineApp.java** → Save as `VendingMachineApp.java`

### **Step 3: Taruh semua file di folder yang sama**
```
VendingMachineProject/
├── Product.java
├── Transaction.java
├── VendingMachine.java
└── VendingMachineApp.java
