# VendingMachine-UTS
Tugas UTS Pemograman Berorientasi Objek
# VENDING MACHINE MANAGEMENT SYSTEM

## Informasi Mahasiswa
- **Nama**: [Edo Syahputra]
- **NIM**: [1001250049]
- **Kelas**: Eksekutif
- **Mata Kuliah**: IF104 - Pemrograman Berorientasi Objek
- **Dosen**: Taufik Iqbal Ramdhani, S.Kom., M.Sc.

## Deskripsi Aplikasi
Aplikasi Vending Machine Management System adalah sistem manajemen mesin vending berbasis console yang dibangun menggunakan Java. Sistem ini menerapkan konsep-konsep Object-Oriented Programming (OOP) seperti Class, Object, Encapsulation, Constructor Overloading, Method dengan parameter dan return value, serta penggunaan ArrayList untuk mengelola koleksi objek.

## Fitur Utama
1. **Manajemen Produk**
   - Menambah produk baru
   - Melihat semua produk
   - Mencari produk berdasarkan ID atau nama
   - Restock produk
   - Cek produk dengan stok rendah

2. **Transaksi**
   - Pembelian produk
   - Perhitungan otomatis total harga
   - Validasi pembayaran
   - Perhitungan kembalian
   - Cetak struk transaksi

3. **Laporan**
   - Riwayat transaksi lengkap
   - Total revenue/pendapatan
   - Informasi mesin vending
   - Alert untuk stok rendah

## Struktur Class

### 1. Product.java
Class yang merepresentasikan produk dalam vending machine.
- **Atribut**: productId, name, price, stock, category
- **Constructor Overloading**: 3 constructor dengan parameter berbeda
- **Method**: displayInfo(), isAvailable(), reduceStock(), addStock(), calculateTotal(), isLowStock()

### 2. Transaction.java
Class yang merepresentasikan transaksi pembelian.
- **Atribut**: transactionId, product, quantity, totalPrice, paymentAmount, changeAmount, transactionDate
- **Constructor Overloading**: 2 constructor
- **Method**: processTransaction(), calculateChange(), validatePayment(), printReceipt(), getTransactionSummary(), isSuccessful()

### 3. VendingMachine.java
Class yang merepresentasikan mesin vending dan mengelola semua operasi.
- **Atribut**: machineId, location, products (ArrayList), transactionHistory (ArrayList), totalRevenue
- **Constructor Overloading**: 2 constructor
- **Method**: addProduct(), displayAllProducts(), findProductById(), findProductByName(), purchaseProduct(), displayTransactionHistory(), displayLowStockProducts(), getTotalProducts(), getTotalItems(), displayMachineInfo()

### 4. VendingMachineApp.java
Class utama yang menjalankan aplikasi dan menyediakan menu interaktif.

## Cara Menjalankan Program

### Requirements
- Java Development Kit (JDK) 8 atau lebih tinggi
- Terminal/Command Prompt

### Langkah Kompilasi
1. Buka terminal/command prompt
2. Navigate ke folder yang berisi semua file .java
3. Compile semua file Java:
   ```bash
   javac *.java
   ```

### Langkah Menjalankan
Setelah kompilasi berhasil, jalankan program:
```bash
java VendingMachineApp
```

### Alternatif menggunakan IDE
- **IntelliJ IDEA**: Buka project, klik kanan VendingMachineApp.java → Run
- **Eclipse**: Buka project, klik kanan VendingMachineApp.java → Run As → Java Application
- **NetBeans**: Buka project, klik Run Project (F6)
- **VS Code**: Install Java Extension Pack, tekan F5 atau Run → Start Debugging

## Konsep OOP yang Diterapkan

### 1. Encapsulation (Enkapsulasi)
- Semua atribut dibuat **private**
- Akses ke atribut melalui **getter** dan **setter** method
- Contoh: `private String productId;` dengan `getProductId()` dan `setProductId()`

### 2. Constructor Overloading
- **Product class**: 3 constructor (full parameters, without category, no parameters)
- **Transaction class**: 2 constructor (with payment, without payment)
- **VendingMachine class**: 2 constructor (with parameters, without parameters)

### 3. Method dengan Parameter dan Return Value
- Method dengan parameter: `isAvailable(int quantity)`, `reduceStock(int quantity)`
- Method dengan return value: `calculateTotal(int quantity)` returns double, `findProductById(String id)` returns Product
- Method tanpa parameter: `displayInfo()`, `printReceipt()`

### 4. Pass by Value
Contoh pada method `purchaseProduct()`:
```java
double payment = 50000;
vendingMachine.purchaseProduct("P001", 2, payment);
// Nilai payment tidak berubah meski diproses di method
```

### 5. ArrayList
- `ArrayList<Product>` untuk menyimpan daftar produk
- `ArrayList<Transaction>` untuk menyimpan riwayat transaksi
- Operasi: add(), iterasi dengan for-each loop, search

## Contoh Screenshot
(Sisipkan screenshot saat menjalankan program untuk laporan)

## Data Awal yang Tersedia
Program sudah memiliki 5 produk awal:
1. Coca Cola (P001) - Rp 8,000 - Stock: 15
2. Sprite (P002) - Rp 7,500 - Stock: 12
3. KitKat (P003) - Rp 5,000 - Stock: 20
4. Pocari Sweat (P004) - Rp 9,000 - Stock: 10
5. Cheetos (P005) - Rp 6,000 - Stock: 8

## Pengembangan Selanjutnya
- Database integration untuk persistent storage
- GUI menggunakan JavaFX atau Swing
- Payment method yang lebih bervariasi (e-wallet, kartu)
- Report export ke PDF atau Excel
- Multi vending machine management
- User authentication untuk admin

## Catatan
- Program ini dibuat untuk memenuhi tugas UTS Pemrograman Berorientasi Objek
- Semua konsep OOP yang diminta telah diimplementasikan
- Program telah diuji dan berjalan dengan baik pada Java JDK 8+

---
**Institut Teknologi Tangerang Selatan**  
Tahun Akademik 2025/2026
