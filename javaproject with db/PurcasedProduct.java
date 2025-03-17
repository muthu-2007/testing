class PurcasedProduct {
    int productID;
    String productName;
    int quantity;
    String brand;
    double price;
    double totalPrice;
    PurcasedProduct(int productID,String productName,String brand,int quantity, double price, double totalPrice){
        this.productID = productID;
        this.productName = productName;
        this.brand = brand;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
    }
    public int getProductID() {
        return productID;
    }
    public void setProductID(int productID) {
        this.productID = productID;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
