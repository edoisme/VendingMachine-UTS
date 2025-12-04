public class Product {
    // Atribut private (Enkapsulasi)
    private String productId;
    private String name;
    private double price;
    private int stock;
    private String category;

    // Constructor overloading - Constructor 1 (semua parameter)
    public Product(String productId, String name, double price, int stock, String category) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    // Constructor overloading - Constructor 2 (tanpa category)
    public Product(String productId, String name, double price, int stock) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = "General";
    }

    // Constructor overloading - Constructor 3 (tanpa parameter)
    public Product() {
        this.productId = "P000";
        this.name = "Unknown Product";
        this.price = 0.0;
        this.stock = 0;
        this.category = "General";
    }

    // Getter dan Setter (Enkapsulasi)
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Method 1: Menampilkan informasi produk
    public void displayInfo() {
        System.out.println("=================================");
        System.out.println("Product ID   : " + productId);
        System.out.println("Name         : " + name);
        System.out.println("Price        : Rp " + String.format("%.2f", price));
        System.out.println("Stock        : " + stock + " units");
        System.out.println("Category     : " + category);
        System.out.println("=================================");
    }

    // Method 2: Cek ketersediaan stok (dengan parameter)
    public boolean isAvailable(int quantity) {
        return stock >= quantity;
    }

    // Method 3: Kurangi stok (dengan parameter, return boolean)
    public boolean reduceStock(int quantity) {
        if (isAvailable(quantity)) {
            stock -= quantity;
            return true;
        }
        return false;
    }

    // Method 4: Tambah stok (dengan parameter)
    public void addStock(int quantity) {
        if (quantity > 0) {
            stock += quantity;
            System.out.println("Stock added successfully! New stock: " + stock);
        } else {
            System.out.println("Invalid quantity!");
        }
    }

    // Method 5: Hitung total harga (dengan parameter, return double)
    public double calculateTotal(int quantity) {
        return price * quantity;
    }

    // Method 6: Cek apakah produk hampir habis (return boolean)
    public boolean isLowStock() {
        return stock < 5;
    }

    // Override toString untuk kemudahan display
    @Override
    public String toString() {
        return String.format("%-10s %-20s Rp%-10.2f %-5d %-15s", 
                             productId, name, price, stock, category);
    }
}
