public class Products {
    static int count=0; 
    int brandId;
	String productname;
	int productID;
    String brand;
	double price;
	int product_type_id;
	int stockQuantity;
    String productType;
    String expdate;
    String warranty;
    String durability;
    Products(){
        count++;
		this.brandId = count;
    }
    Products(int count){
    	Products.count = count;
    }
	Products(String productname,String brand,int stockQuantity,double price){
		this();
		this.productname = productname;
        this.brand = brand;
		this.price = price;
		this.stockQuantity = stockQuantity;
	}
    Products(int id,String productname,String brand,int stockQuantity,double price){
		this.productID = id;
		this.productname = productname;
        this.brand = brand;
		this.price = price;
		this.stockQuantity = stockQuantity;
	}

    Products(int id,String productname,String brand,int stockQuantity,String productType,String expdate,double price){
		this.productID = id;
		this.productname = productname;
        this.brand = brand;
		this.price = price;
		this.productType = productType;
		this.expdate = expdate;
		this.stockQuantity = stockQuantity;
	}
    
    Products(int id,String productname,int brandid,int stockQuantity,String productType,String expdate,double price){
		this.productID = id;
		this.productname = productname;
        this.brandId = brandid;
		this.price = price;
		this.productType = productType;
		this.expdate = expdate;
		this.stockQuantity = stockQuantity;
	}
    
    
    public Products(int id, String name, int brandid, int stock, int product_type_id, String expire_date,double price) {
    	this.productID = id;
		this.productname = name;
        this.brandId = brandid;
		this.price = price;
		this.product_type_id = product_type_id;
		this.expdate = expire_date;
		this.stockQuantity = stock;
	}
	static void updatestock(int id,int quantity){
        for (Products pro : ProductManagementSystem.productsList){
            if (pro.productID == id){
                int currentStock = pro.stockQuantity - quantity;
                pro.setStockQuantity(currentStock);
            }
        }
    }
    static void addproduct(Products product){
		ProductManagementSystem.productsList.add(product);
        System.out.println(Color.GREEN+"product has successfully added "+Color.RESET);
    }
    static void displayAllProduct(){
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.printf("|%-5s | %-15s | %-18s |%-11s | %-7s | %n","Id","Product Name","brand","price","stock");
            for (Products pro : ProductManagementSystem.productsList){
                pro.displayProduct();
            }
        System.out.println("------------------------------------------------------------------------------------");
    }
    
    
    void displayProduct(){
    	System.out.printf("|%-5s | %-15s | %-18s |%-11s | %-15s | %-25s | %-9s | %n", this.productID,this.productname, this.brandId, this.stockQuantity, this.product_type_id,this.expdate,this.price);
    }
    void updateproductname(String productname){
        this.productname = productname;
        System.out.println(Color.GREEN+"product name successfully updated");
    }
    void updateproductprice(double price){
        this.price = price;
        System.out.println(Color.GREEN+"product price successfully updated");
    }
    void updatestockquantity(int stock){
        this.stockQuantity = stock;
        System.out.println(Color.GREEN+"product stock quantity successfully updated");
    }
    public static int getCount() {
        return count;
    }
    public static void setCount(int count) {
        Products.count = count;
    }
    public String getProductname() {
        return productname;
    }
    public String getbrand() {
        return brand;
    }
    public void setProductname(String productname) {
        this.productname = productname;
    }
    public int getProductID() {
        return productID;
    }
    public void setProductID(int productID) {
        this.productID = productID;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getStockQuantity() {
        return stockQuantity;
    }
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    public void setbrand(String brand) {
        this.brand = brand;
    }
}

class Groceries extends Products{
    String productType = "Grocery";
    String expdate;
    Groceries(int id,String productname,String brand,int stockQuantity,double price,String date){
        super(id,productname,brand,stockQuantity,price);
        this.expdate = date; 
    }
    Groceries(String productname,String brand,int stockQuantity,double price,String date){
        super(productname,brand,stockQuantity,price);
        this.expdate = date; 
    }
    void displayProduct(){
        System.out.printf("\n|%-5s | %-15s | %-18s | %-10s | %-15s | %-26s | %-9s | %n",this.productID,this.productname,this.brand,this.stockQuantity,this.productType,this.expdate,this.price);
    }
}


class Technology extends Products {
    String productType = "Technology";
    String warranty;
    Technology(int id,String productname,String brand,int stockQuantity,double price,String warranty){
        super(id,productname,brand,stockQuantity,price);
        this.warranty = warranty;
    }
    Technology(String productname,String brand,int stockQuantity,double price,String warranty){
        super(productname,brand,stockQuantity,price);
        this.warranty = warranty;
    }
    void displayProduct(){
        System.out.printf("\n|%-5s | %-15s | %-18s | %-10s | %-15s | %-26s | %-9s | %n",this.productID,this.productname,this.brand,this.stockQuantity,this.productType,this.warranty,this.price);
    }
}


class Stationary extends Products {
    String productType = "stationary";
    String durability;
    Stationary(int id,String productname,String brand,int stockQuantity,double price,String durability){
        super(id,productname,brand,stockQuantity,price);
        this.durability = durability;
    }
    Stationary(String productname,String brand,int stockQuantity,double price,String durability){
        super(productname,brand,stockQuantity,price);
        this.durability = durability;
    }
    void displayProduct(){
        System.out.printf("\n|%-5s | %-15s | %-18s | %-10s | %-15s | %-26s | %-9s | %n",this.productID,this.productname,this.brand,this.stockQuantity,this.productType,this.durability,this.price);
    }
}
