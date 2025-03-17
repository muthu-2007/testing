import java.util.ArrayList;
import java.util.List;


class Customer {
    int transactioncount;
    int custemerId;
    String customerName;
    long phoneNumber;
    ArrayList<List<PurcasedProduct>>TransactionList = new ArrayList<>();
    ArrayList<PurcasedProduct> order = new ArrayList<>();
    Customer(String customerName,long phnumber){
        this.customerName = customerName;
        this.phoneNumber = phnumber;
    }
    Customer(int id,String customerName,long phnumber){
    	this.custemerId = id;
        this.customerName = customerName;
        this.phoneNumber = phnumber;
    }
    Customer(){}
    Customer(int id,String customerName){
        this.customerName = customerName;
        this.custemerId = id;
    }
    void addPurcasedProduct(Products pro,int quantity){
                PurcasedProduct product = new PurcasedProduct(pro.productID,pro.productname,pro.brand,quantity,pro.price,(pro.price*quantity));
                this.order.add(product);
    }
    void getTransactionDetail(Customer customer){
        System.out.println("--------------------------- Transaction History  -------------------------");
        System.out.println("__________________________________________________________________________");
        // System.out.printf("|%-10s | %-20s | %-8s | %-10s | %-11s |%n \n","product id","product name","quantity","price","total price");
        for (List<PurcasedProduct> PurcasedProduct : customer.TransactionList){
            double totalammount = 0;
            for (PurcasedProduct products: PurcasedProduct){
                System.out.printf("|%-10s | %-20s | %-8s | %-10s | %-11s | %n \n",products.productID,products.productName,products.quantity,products.price,products.totalPrice);
                totalammount += products.totalPrice;
            }
            System.out.println("-----------------------------------------------------------------------");
            System.out.printf("|%-57s | %-11s |\n %n","Grand Total ", totalammount);
            System.out.println("-----------------------------------------------------------------------");
        }
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public int getCustemerId() {
        return custemerId;
    }
    public void setCustemerId(int custemerId) {
        this.custemerId = custemerId;
    }
    public ArrayList<List<PurcasedProduct>> getTransactionList() {
        return TransactionList;
    }
    public void setTransactionList(ArrayList<List<PurcasedProduct>> transactionList) {
        TransactionList = transactionList;
    }
    public ArrayList<PurcasedProduct> getPurcasedProduct() {
        return order;
    }
    public void setPurcasedProduct(ArrayList<PurcasedProduct> PurcasedProduct) {
        this.order = PurcasedProduct;
    }
	public void displayProduct() {
		System.out.printf("|%-5s | %-15s | %-14s | %n", this.custemerId,this.customerName,this.phoneNumber );
        
	}
}
