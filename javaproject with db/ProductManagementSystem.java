import java.util.Scanner;


import java.util.ArrayList;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class ProductManagementSystem {
    static ArrayList<Products> productsList = new ArrayList<>();
    static ArrayList<Customer> customerList = new ArrayList<>();

    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner input = new Scanner(System.in);
        int brandId = 0;
        try {
			String query = "select * from brand;";
			PreparedStatement stmt = (PreparedStatement) Dbconnection.getConnection().prepareStatement(query);
			ResultSet result = ((java.sql.Statement) stmt).executeQuery(query);
			
			int count=0;
			while (result.next()) {
				count++;
			}
			Products p = new Products(count);
			brandId = ++Products.count;
			System.out.println(Products.count);
		}
		catch (Exception e) {
			System.out.println("input mismatch error");
		}
        
        try {
			String query = "select * from cashier;";
			PreparedStatement stmt = (PreparedStatement) Dbconnection.getConnection().prepareStatement(query);
			ResultSet result = ((java.sql.Statement) stmt).executeQuery(query);
			
			int count=0;
			while (result.next()) {
				int id = result.getInt(1);
				String name = result.getString(2);
				String password = result.getString(3);
				double salary = result.getDouble(4);
				long phnumber = result.getLong(5);
				
				Cashier c = new Cashier(id,name,password,salary,phnumber);
				
				LoginSystem.usersList1.add(c);
			}
			Products p = new Products(brandId);
			System.out.println("count   "+Products.count);
		}
		catch (Exception e) {
			System.out.println("input mismatch error");
		}
        Admin admin = new Admin("muthu", "muthu@123");
        LoginSystem.usersList1.add(admin);
        main: while (true) {
            // System.out.println("Enter the user name : ");
            // String userName = input.nextLine();
            // System.out.println("enter the password : ");
            // String password = input.nextLine();
            User currentuser = LoginSystem.userLoginSystem();
        if (currentuser instanceof Admin) {
            while (true) {
                System.out.println("___________________________________________________________________________");
                System.out.println(Color.YELLOW+ "enter the function you want to do : \n1) Add new products \n2) update existing product \n3) Delete product \n4) view products \n5) Receipt generation \n6) add new customer \n7) view all customers \n8) Transaction history \n9) add cashier \n10) veiw all cashier \n11) modify cashier details \n12) Todays transaction history \n13) products want to restore \n14) sign up to another account \n15) Exit");
                System.out.println(Color.RESET + "___________________________________________________________________________");
                int option = Getinput.getinputNumber();
                switch (option) {
                    case 1: {
                        System.out.println("enter the product name : ");
                        String productname = input.nextLine();
                        System.out.println("enter the brand name : ");
                        String brand = input.nextLine();
                        System.out.println("enter the product price");
                        double price = Getinput.getinputDouble();
                        System.out.println("enter the stock quantity");
                        int stock = Getinput.getinputNumber();
                        
                        System.out.println(
                                "enter the type of the product you are addded : \n1)Grocery \n2)Technology \n3)Stationary");
                        int type = Getinput.getinputNumber();
                        switch (type) {
                            case 1: {
                                System.out.println("enter the expiry date of the product (yyyy-mm-dd) : ");
                                String date = input.nextLine();
                    			
                                Groceries pGroceries = new Groceries(productname, brand, stock, price, date);
                                Products.addproduct(pGroceries);
                                // brand
                                try {
                                	String Query = "insert into brand (bid,pid,brand) values(?,?,?)";
                        			PreparedStatement stmt = Dbconnection.getConnection().prepareStatement(Query);
                        			stmt.setInt(1, brandId);
                        			stmt.setString(2, pGroceries.brand);
                        			int resultSet = stmt.executeUpdate();
        						} catch (Exception e) {
        							System.out.println("mysql syntax error for brand");
                        			e.printStackTrace();
        						}
                                // product type//                                
//                               try {
//                                	String Query = "insert into products() values(?,?,?,?,?,?)";
//                        			PreparedStatement stmt = Dbconnection.getConnection().prepareStatement(Query);
//								} catch (Exception e) {
//									System.out.println("mysql syntax error");
//                        			e.printStackTrace();
//								}
                                // products
                                
                                try {
                        			String Query = "insert into products (pname, bid, stock_quantity, product_type_id, expiredate, price) values (?, ?, ?, ?, ?, ?)";
                        			PreparedStatement stmt = Dbconnection.getConnection().prepareStatement(Query);
                        			
                        			stmt.setString(1, productname);
                        			stmt.setInt(2, brandId);
                        			stmt.setInt(3, stock);
                        			stmt.setInt(4, 2);
                        			stmt.setDate(5, Date.valueOf(date));
                        			stmt.setDouble(6, price);
                        			brandId++;
                        			int resultSet = stmt.executeUpdate();
                        			
                        		} catch (SQLException e) {
                        			System.out.println("mysql syntax error for ");
                        			e.printStackTrace();
                        		}
                                
                                break;
                            }
                            case 2: {
                                System.out.println("enter the warranty or garranty of the product : (yyyy-mm-dd) ");
                                String date = input.nextLine();
                                Technology pTechnology = new Technology(productname, brand, stock, price, date);
                                System.out.println(pTechnology.brandId);
                                try {
                                	String Query = "insert into brand (bid,brand) values(?,?)";
                        			PreparedStatement stmt = Dbconnection.getConnection().prepareStatement(Query);
                        			stmt.setInt(1, brandId);
                        	
                        			stmt.setString(2, pTechnology.brand);
                        			int resultSet = stmt.executeUpdate();
                        			
        						} catch (Exception e) {
        							System.out.println("mysql syntax error for brand");
                        			e.printStackTrace();
        						}
                                Products.addproduct(pTechnology);
                                try {
                        			String Query = "insert into products (pname, bid, stock_quantity, product_type_id, expiredate, price) values (?, ?, ?, ?, ?, ?)";
                        			PreparedStatement stmt = Dbconnection.getConnection().prepareStatement(Query);
                        			
                        			stmt.setString(1, productname);
                        			stmt.setInt(2, brandId);
                        			stmt.setInt(3, stock);
                        			stmt.setInt(4, 2);
                        			stmt.setDate(5, Date.valueOf(date));
                        			stmt.setDouble(6, price);
                        			brandId++;
                        			int resultSet = stmt.executeUpdate();
                        			
                        		} catch (SQLException e) {
                        			System.out.println("mysql syntax error for ");
                        			e.printStackTrace();
                        		}
                                break;
                            }
                            case 3: {
                                System.out.println("Enter the durability of the product : ");
                                String date = input.nextLine();
                                Stationary pStationary = new Stationary(productname, brand, stock, price, date);
                                try {
                                	String Query = "insert into brand (bid,brand) values(?,?)";
                        			PreparedStatement stmt = Dbconnection.getConnection().prepareStatement(Query);
                        			stmt.setInt(1, brandId);
                        			stmt.setString(2, pStationary.brand);
                        			
                        			int resultSet = stmt.executeUpdate();
        						} catch (Exception e) {
        							System.out.println("mysql syntax error for brand");
                        			e.printStackTrace();
        						}
                                Products.addproduct(pStationary);
                                try {
                        			String Query = "insert into products (pname, bid, stock_quantity, product_type_id, expiredate, price) values (?, ?, ?, ?, ?, ?)";
                        			PreparedStatement stmt = Dbconnection.getConnection().prepareStatement(Query);
                        			
                        			stmt.setString(1, productname);
                        			stmt.setInt(2, brandId);
                        			stmt.setInt(3, stock);
                        			stmt.setInt(4, 2);
                        			stmt.setDate(5, Date.valueOf(date));
                        			stmt.setDouble(6, price);
                        			
                        			int resultSet = stmt.executeUpdate();
                        			brandId++;
                        		} catch (SQLException e) {
                        			System.out.println("mysql syntax error for ");
                        			e.printStackTrace();
                        		}
                                break;
                            }
                            default: {
                                System.out.println(Color.RED + "invalid option" + Color.RESET);
                                break;
                            }
                        }
                        break;
                    }

                    case 2: {
                        // Products.displayAllProduct();
                    	ArrayList<Products> products = new ArrayList<>();
                		try {
                			String query = "select * from products;";
                			PreparedStatement stmt = (PreparedStatement) Dbconnection.getConnection().prepareStatement(query);
                			ResultSet result = ((java.sql.Statement) stmt).executeQuery(query);
                			
                			while (result.next()) {
                				int id = result.getInt(1);
                				String name = result.getString(2);
                				int brandid = result.getInt(3);
                				int stock = result.getInt(4);
                				int product_type_id = result.getInt(5);
                				String expire_date = result.getString(6);
                				double Price = result.getDouble(7);
                				Products p = new Products(id,name,brandid,stock,product_type_id,expire_date,Price);
                				products.add(p);
                			}
                		}
                		catch (Exception e) {
                			System.out.println("input mismatch error");
                		}
                		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
                        System.out.printf("|%-5s | %-15s | %-18s |%-11s | %-15s | %-25s | %-9s | %n", "Id","Product Name", "brandId", "stock ", "product type", "expiry date","price");
                        for (int i = 0; i < products.size(); i++) {
                            products.get(i).displayProduct();
                        }
                        System.out.println("-------------------------------------------------------------------------------------------------------------------------");              		
                        
                        System.out.println("enter the id of the product you are going to update : ");
                        int updatingProduct = Getinput.getinputNumber();
                        for (Products pro : products) {
                            if (updatingProduct == pro.productID) {
                                System.out.println(
                                        "which thing do you want to update \n 1)product name \n 2)product price \n 3)stock quantity");
                                int opt = Getinput.getinputNumber();
                                switch (opt) {
                                    case 1: {
                                        System.out.println("enter the product name : ");
                                        String Pname = input.nextLine();
                                        PreparedStatement stmt;
                                		try {
                                			stmt = (Dbconnection.getConnection()).prepareStatement("update products set pname = ? where pid = ?");
                                			stmt.setString(1, Pname);
                                			stmt.setInt(2, updatingProduct);
                                			int resultSet = stmt.executeUpdate();
                                		} catch (SQLException e) {
                                			e.printStackTrace();
                                		}
                                        pro.updateproductname(Pname);
                                        break;
                                    }
                                    case 2: {
                                        System.out.println("enter the product price : ");
                                        PreparedStatement stmt;
                                        try {
                                            double price = Getinput.getinputDouble();
                                            while (price < 0) {
                                                System.out.println("enter the valid price :");
                                                price = Getinput.getinputDouble();
                                            }
                                            stmt = (Dbconnection.getConnection()).prepareStatement("update products set price = ? where pid = ?");
                                			stmt.setDouble(1, price);
                                			stmt.setInt(2, updatingProduct);
                                			int resultSet = stmt.executeUpdate();
//                                            pro.updateproductprice(price);
                                        } catch (Exception e) {
                                            System.out.println("enter the valid input :");
                                            e.getMessage();
                                        }
                                        break;
                                    }
                                    case 3: {
                                        System.out.println("enter the stock quantity of the product : ");
                                        int stock = Getinput.getinputNumber();
                                        while (stock < 0) {
                                            stock = Getinput.getinputNumber();
                                        }
                                        PreparedStatement stmt;
                                		try {
                                			stmt = (Dbconnection.getConnection()).prepareStatement("update products set pname = ? where pid = ?");
                                			stmt.setInt(1, stock);
                                			stmt.setInt(2, updatingProduct);
                                			int resultSet = stmt.executeUpdate();
                                		} catch (SQLException e) {
                                			e.printStackTrace();
                                		}
                                        break;
                                    }
                                    default:
                                        break;
                                }
                            }
                        }
                        break;
                    }

                    case 3: {
                        System.out.println("enter the id of the product you want to delete : ");
                        int id = Getinput.getinputNumber();
                        PreparedStatement stmt;
                		try {
                			stmt = (Dbconnection.getConnection()).prepareStatement("delete from products where pid = ?;");
                			stmt.setInt(1, id);
                			int resultSet = stmt.executeUpdate();
                		} catch (SQLException e) {
                			e.printStackTrace();
                		}
//                        if (productsList.removeIf(product -> product.productID == id)) {
//                            System.out.println(Color.GREEN + "product successfully deleted : ");
//                        } else {
//                            System.out.println(Color.RED + "product is unavailable" + Color.RESET);
//                        }
                        break;
                    }
                    case 4: {
                    	ArrayList<Products> products = new ArrayList<>();
                		try {
                			String query = "select * from products;";
                			PreparedStatement stmt = (PreparedStatement) Dbconnection.getConnection().prepareStatement(query);
                			ResultSet result = ((java.sql.Statement) stmt).executeQuery(query);
                			
                			while (result.next()) {
                				int id = result.getInt(1);
                				String name = result.getString(2);
                				int brandid = result.getInt(3);
                				int stock = result.getInt(4);
                				int product_type_id = result.getInt(5);
                				String expire_date = result.getString(6);
                				double Price = result.getDouble(7);
                				Products p = new Products(id,name,brandid,stock,product_type_id,expire_date,Price);
                				products.add(p);
                			}
                		}
                		catch (Exception e) {
                			System.out.println("input mismatch error");
                		}
                        System.out.println(
                                "what type of product you want to see \n1)view all products \n2)product stock \n3)brand");
                        int disopt = Getinput.getinputNumber();
                        switch (disopt) {
                            case 1: {
                                System.out.println("-------------------------------------------------------------------------------------------------------------------------");
                                System.out.printf("|%-5s | %-15s | %-18s |%-11s | %-15s | %-25s | %-9s | %n", "Id","Product Name", "brandId", "stock ", "product type", "expiry date","price");
                                for (int i = 0; i < products.size(); i++) {
                                    products.get(i).displayProduct();
                                }
                                System.out.println("-------------------------------------------------------------------------------------------------------------------------");
                                break;
                            }
                            
                            case 2: {
                                System.out.println("Enter the amount of stock you are going to search");
                                int stock = Getinput.getinputNumber();
                                System.out.println(
                                        "-------------------------------------------------------------------------------------------------------------------------");
                                System.out.printf("|%-5s | %-15s | %-18s |%-11s | %-15s | %-25s | %-9s | %n", "Id","Product Name", "brandId", "stock ", "product type", "expiry date","price");
                                for (int i = 0; i < products.size(); i++) {
                                    if (products.get(i).stockQuantity <= stock) {
                                        products.get(i).displayProduct();
                                    }
                                }
                                System.out.println(
                                        "-------------------------------------------------------------------------------------------------------------------------");
                                break;
                            }
                            case 3: {
                                // input.nextLine();
                                System.out.println("enter the brand you are going to view : ");
                                String brand1 = input.nextLine();
                                System.out.println(
                                        "-------------------------------------------------------------------------------------------------------------------------");
                                System.out.printf("|%-5s | %-15s | %-18s |%-11s | %-15s | %-25s | %-9s | %n", "Id","Product Name", "brandId", "stock ", "product type", "expiry date","price");
                                for (int i = 0; i < products.size(); i++) {
                                    if ((products.get(i).brand).equals(brand1 + "")) {
                                        products.get(i).displayProduct();
                                    }
                                }
                                System.out.println(
                                        "-------------------------------------------------------------------------------------------------------------------------");
                                break;
                            }
                            default: {
                                System.out.println("you have entered invalid input : ");
                                break;
                            }
                        }
                        break;
                    }
                    case 5:{
                    	ArrayList<Customer> customer = new ArrayList<>();
                		try {
                			String query = "select * from customer;";
                			PreparedStatement stmt = (PreparedStatement) Dbconnection.getConnection().prepareStatement(query);
                			ResultSet result = ((java.sql.Statement) stmt).executeQuery(query);
                			
                			while (result.next()) {
                				int id = result.getInt(1);
                				String name = result.getString(2);
                				long phoneNumber = result.getLong(3);
                				Customer c = new Customer(id,name,phoneNumber);
                				customer.add(c);
                			}
                		}
                		catch (Exception e) {
                			System.out.println("input mismatch error");
                		}
                        System.out.println("enter (1) for you are a existing customer \nenter (2) for you are a new customer : ");
                            int option1 = Getinput.getinputNumber();
                            Customer cust = new Customer();
                            if (option1 == 1) {
                                System.out.println("enter the customer id :");
                                int custemerid = Getinput.getinputNumber();
                                for (Customer cus : customer) {
                                    if ((custemerid) == (cus.custemerId)) {
                                        cust = cus;
                                        break;
                                    }
                                }
                            } else {
                                System.out.println("enter the coustomer name : ");
                                String custName = input.nextLine();
                                System.out.println("enter the customer phone number : ");
                                long phnumber =(long) Getinput.getinputLong();
                                while (true) {
                                    if ((phnumber+"").trim().length() == 10) {
                                        break;
                                    } else {
                                        System.out.println("Enter the phone number properly");
                                        phnumber = (long) Getinput.getinputLong();
                                    }
                                }
                                Customer costumer1 = new Customer(custName, phnumber);
                                try {
                        			String Query = "insert into customer (cname,phone_number) values (?,?)";
                        			PreparedStatement stmt = Dbconnection.getConnection().prepareStatement(Query);
                        			
                        			stmt.setString(1, costumer1.customerName);
                        			stmt.setLong(2, costumer1.phoneNumber);
                        			
                        			int resultSet = stmt.executeUpdate();
                        			
                        		} catch (SQLException e) {
                        			System.out.println("mysql syntax error");
                        			e.printStackTrace();
                        		}
                                cust = costumer1;
                                cust.transactioncount++;
                            }
                            int opt = 0;
                            ArrayList<Products> products = new ArrayList<>();
                    		try {
                    			String query = "select * from products;";
                    			PreparedStatement stmt = (PreparedStatement) Dbconnection.getConnection().prepareStatement(query);
                    			ResultSet result = ((java.sql.Statement) stmt).executeQuery(query);
                    			
                    			while (result.next()) {
                    				int id = result.getInt(1);
                    				String name = result.getString(2);
                    				String brand = result.getString(3);
                    				int stock = result.getInt(4);
                    				String product_type = result.getString(5);
                    				String expire_date = result.getString(6);
                    				double Price = result.getDouble(7);
                    				Products p = new Products(id,name,brand,stock,product_type,expire_date,Price);
                    				products.add(p);
                    			}
                    		}
                    		catch (Exception e) {
                    			System.out.println("input mismatch error");
                    		}
                            System.out.println("---------------------------------------------------------");
                            System.out.printf("|%-5s | %-15s | %-18s |%-11s | %-15s | %-25s | %-9s | %n", "Id","Product Name", "brand", "stock", "product type", "expiry/durability/warranty","price");
                            
                            for (int i = 0; i < products.size(); i++) {
                                products.get(i).displayProduct();
                            }
                            
                            System.out.println("---------------------------------------------------------");
                            do {
                                Products product = new Products();
                                System.out.println("enter the product Id you want to buy : ");
                                int product_id = Getinput.getinputNumber();
                                for (PurcasedProduct product2 : cust.order) {
                                    while (product2.productID == product_id) {
                                        if (product2.productID == product_id) {
                                            System.out.println(
                                                    "you have already purchased this product: do you want to update or not enter :\n1) update this product\n2) change product ");
                                            product_id = Getinput.getinputNumber();
                                            cust.order.add(product2);
                                        }
                                    }
                                }
                                System.out.println("enter the quantity of the product : ");
                                int quantity = Getinput.getinputNumber();
                                while (quantity <= 0) {
                                    System.out.println("enter the valid quantity : ");
                                    quantity = Getinput.getinputNumber();
                                }
                                for (Products pro : products) {
                                    while (pro.productID == product_id) {
                                        if (quantity <= pro.stockQuantity) {
                                            product = pro;
                                            break;
                                        } else {
                                            System.out.println("there is only " + pro.stockQuantity
                                                    + " is available \nkindly enter the quantity again : ");
                                            quantity = Getinput.getinputNumber();
                                        }
                                    }
                                }
                                Products.updatestock(product_id, quantity);
                                cust.addPurcasedProduct(product,quantity);
                                System.out.println("do you want to add products : \n 1) yes \n 2) no");
                                opt = Getinput.getinputNumber();
                                for (int i=0; i<cust.order.size(); i++) {
                                	try {
                            			String Query = "insert into transaction (pid,cid,quantity,price,total_price) values(?,?,?,?,?)";
                            			PreparedStatement stmt = Dbconnection.getConnection().prepareStatement(Query);
                            			
                            			stmt.setInt(1, cust.order.get(i).productID);
                            			stmt.setInt(2, cust.custemerId);
                            			stmt.setInt(3, cust.order.get(i).quantity);
                            			stmt.setDouble(4, cust.order.get(i).price);
                            			stmt.setDouble(5, cust.order.get(i).quantity*cust.order.get(i).price);
                            			int resultSet = stmt.executeUpdate();
                            		} catch (SQLException e) {
                            			System.out.println("mysql syntax error");
                            			e.printStackTrace();
                            		}
                                }
                            } while (opt != 2);
                            RecieptGeneration.receiptgeneration(cust);
                            break;
                    }
                    case 6:{
                                System.out.println("enter the customer name : ");
                                String custName = input.nextLine();
                                System.out.println("enter the customer phone number : ");
                                long phnumber =(long) Getinput.getinputLong();
                                while (true) {
                                    if ((phnumber+"").trim().length() == 10) {
                                        break;
                                    } else {
                                        System.out.println("Enter the phone number properly");
                                        phnumber = (long) Getinput.getinputLong();
                                    }
                                }
                                Customer costumer1 = new Customer(custName, phnumber);
                                
                                try {
                                	String Query = "insert into customer (cname,phone_number) values(?,?)";
                        			PreparedStatement stmt = Dbconnection.getConnection().prepareStatement(Query);
                        			stmt.setString(1, custName);
                        			stmt.setLong(2, phnumber);
                        			int resultSet = stmt.executeUpdate();
                        			
        						} catch (Exception e) {
        							System.out.println("mysql syntax error");
                        			e.printStackTrace();
        						}
                                ProductManagementSystem.customerList.add(costumer1);
//                                try (FileWriter writer = new FileWriter(FILE_PATH1, true)) {
//                                    writer.append(costumer1.custemerId).append(",").append(custName).append(",")
//                                            .append(",").append("\n");
//                                } catch (IOException e) {
//                                    System.out.println("Error writing to file: " + e.getMessage());
//                                } 
                                System.out.println(Color.GREEN+"customer added successfully"+Color.RESET);           
                            break;
                    }
                    case 7: {
                    	ArrayList<Customer> customer = new ArrayList<>();
                		try {
                			String query = "select * from customer;";
                			PreparedStatement stmt = (PreparedStatement) Dbconnection.getConnection().prepareStatement(query);
                			ResultSet result = ((java.sql.Statement) stmt).executeQuery(query);
                			
                			while (result.next()) {
                				int id = result.getInt(1);
                				String name = result.getString(2);
                				long phoneNumber = result.getLong(3);
                				Customer c = new Customer(id,name,phoneNumber);
                				customer.add(c);
                			}
                		}
                		catch (Exception e) {
                			System.out.println("input mismatch error");
                		}
                		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
                        System.out.printf("|%-5s | %-15s | %-14s | %n","cid","customer name", "phone number");
                        
                        for (int i = 0; i < customer.size(); i++) {
                            customer.get(i).displayProduct();
                        }
                        
                        System.out.println("-------------------------------------------------------------------------------------------------------------------------");
                      
                        break;
                    }
                    case 8: {
                        System.out.println("enter the customer id :");
                        int customerId = Getinput.getinputNumber();
                        
                        //ArrayList<Products> transaction = new ArrayList<>();
                		try {
                			String query = "select * from transaction where cid = ? ";
                			PreparedStatement stmt = (PreparedStatement) Dbconnection.getConnection().prepareStatement(query);
                			stmt.setInt(1, customerId);
                			ResultSet result = ((java.sql.Statement) stmt).executeQuery(query);
                			
                			while (result.next()) {
                				int tid = result.getInt(1);
                				int pid = result.getInt(2);
                				int cid = result.getInt(3);
                				int quantity = result.getInt(4);
                				double price = result.getDouble(5);
                				double total_Price = result.getDouble(7);
                			System.out.printf("|%-10s |%-15s | %-15s | %-12s |%-12s | %n","product id","customer id","quantity","price","total price");
                			System.out.printf("|%-10s |%-15s | %-15s | %-12s |%-12s | %n",pid,cid,quantity,price,total_Price);
                			}
                		}
                		catch (Exception e) {
                			System.out.println("input mismatch error");
                			e.printStackTrace();
                		}
                		
                        break;
                    }
                    case 9:{
                        System.out.println("enter the name of the cashier");
                        String cashierName = input.nextLine();
                        System.out.println("enter the password : ");
                        String cpassword = input.nextLine();
                        System.out.println("enter the salary of the cashier : ");
                        Double salary = Getinput.getinputDouble();
                        System.out.println("enter the phone number of the cashier : ");
                        long phnumber = (long) Getinput.getinputLong();
                        System.out.println("phone number "+phnumber);
                        while (true) {
                            if ((phnumber+"").trim().length() == 10) {
                                break;
                            } else {
                                System.out.println("Enter the phone number properly");
                                phnumber = (long) Getinput.getinputLong();
                            }
                        }
                        Cashier c = new Cashier(cashierName, cpassword, salary , phnumber); 
                        LoginSystem.usersList1.add(c);
                        
                        try {
                        	String Query = "insert into cashier (cname,cpassword,salary,phone_number) values(?,?,?,?);";
                			PreparedStatement stmt = Dbconnection.getConnection().prepareStatement(Query);
                			stmt.setString(1, cashierName);
                			stmt.setString(2, cpassword);
                			stmt.setDouble(3, salary);
                			stmt.setLong(4, phnumber);
                			
                			int resultSet = stmt.executeUpdate();
						} catch (Exception e) {
							System.out.println("mysql syntax error");
                			e.printStackTrace();
						}
                        
//                        try (FileWriter writer = new FileWriter(FILE_PATH3, true)) {
//                            writer.append(c.cashierId).append(DELIMITER).append(cashierName).append(DELIMITER).append(cpassword).append(DELIMITER).append(c.salary+"").append(DELIMITER).append(c.phoneNumber).append("\n");
//                        } catch (IOException e) {
//                            System.out.println("Error writing to file: " + e.getMessage());
//                        }
                        break;
                    }
                    case 10:{
                        for(int i=0; i<LoginSystem.usersList1.size(); i++){
                            System.out.println(LoginSystem.usersList1.get(i).userName);
                        }
                        System.out.println("-----------------------------------------------------------------------------------");
                        System.out.printf("|%-10s |%-15s | %-15s | %-12s |%-12s | %n","cashier id","cashier name","password","salary","phone number");
                        for (int i=0; i<LoginSystem.usersList1.size(); i++){
                            if (LoginSystem.usersList1.get(i) instanceof Cashier){
                                Cashier cashier1 = (Cashier) LoginSystem.usersList1.get(i);
                                System.out.printf("\n"+"|%-10s |%-15s | %-15s | %-12s |%-12s | %n",cashier1.cashierId,LoginSystem.usersList1.get(i).userName,LoginSystem.usersList1.get(i).password,cashier1.salary,cashier1.phoneNumber);
                            }
                        }
                        System.out.println("-----------------------------------------------------------------------------------");
                        break;
                    }
                    case 11:{
                        for(int i=0; i<LoginSystem.usersList1.size(); i++){
                            System.out.println(LoginSystem.usersList1.get(i).userName);
                        }
                        System.out.println("-----------------------------------------------------------------------------------");
                        System.out.printf("|%-10s |%-15s | %-15s | %-12s |%-12s | %n","cashier id","cashier name","password","salary","phone number");
                        for (int i=0; i<LoginSystem.usersList1.size(); i++){
                            if (LoginSystem.usersList1.get(i) instanceof Cashier){
                                Cashier cashier1 = (Cashier) LoginSystem.usersList1.get(i);
                                System.out.printf("\n"+"|%-10s |%-15s | %-15s | %-12s |%-12s | %n",cashier1.cashierId,LoginSystem.usersList1.get(i).userName,LoginSystem.usersList1.get(i).password,cashier1.salary,cashier1.phoneNumber);
                            }
                        }
                        System.out.println("-----------------------------------------------------------------------------------");
                        boolean check = false;
                        Cashier newcashier = new Cashier();
                        System.out.println("enter the cashier id you are going to change : ");
                        int id = Getinput.getinputNumber();
                        for (int i=0; i<LoginSystem.usersList1.size(); i++){
                            if (LoginSystem.usersList1.get(i) instanceof Cashier){
                                Cashier cashier = (Cashier) LoginSystem.usersList1.get(i);
                                if (cashier.cashierId == (id)){
                                    newcashier = cashier;
                                    check = true;
                                }
                            }
                        }
                        if (check){
                            System.out.println("which one you are going to modify \n1) reset password \n2) phone number \n3) salary : ");
                            int cashopt = Getinput.getinputNumber();
                            switch (cashopt) {
                                case 1:{
                                    System.out.println("enter the new password : ");
                                    String pass = input.nextLine();
                            		PreparedStatement stmt;
                            		try {
                            			stmt = (Dbconnection.getConnection()).prepareStatement("update cashier set cpassword = ? where cid = ?");
                            			stmt.setString(1, pass);
                            			stmt.setInt(2, newcashier.cashierId);
                            			int resultSet = stmt.executeUpdate();
                            		} catch (SQLException e) {
                            			e.printStackTrace();
                            		}
                                    break;
                                }
                                case 2:{
                                    System.out.println("enter the new phone number : ");
                                    Long phnumber = (long) Getinput.getinputLong();
                                    PreparedStatement stmt;
                            		try {
                            			stmt = (Dbconnection.getConnection()).prepareStatement("update cashier set phone_number = ? where cid = ?");
                            			stmt.setLong(1, phnumber);
                            			stmt.setInt(2, newcashier.cashierId);
                            			int resultSet = stmt.executeUpdate();
                            		} catch (SQLException e) {
                            			e.printStackTrace();
                            		}
                                    break;
                                } 
                                case 3:{
                                    System.out.println("enter the new salary of the cashier : ");
                                    Double salary = Getinput.getinputDouble();
                                    PreparedStatement stmt;
                            		try {
                            			stmt = (Dbconnection.getConnection()).prepareStatement("update cashier set phone_number = ? where cid = ?");
                            			stmt.setDouble(1, salary);
                            			stmt.setInt(2, newcashier.cashierId);
                            		} catch (SQLException e) {
                            			e.printStackTrace();
                            		}
                                    break;
                                } 
                                default:{
                                    System.out.println("you have entered the invalid option : ");
                                    break;
                                }
                            }
                        }
                        else{
                            System.out.println(Color.RED+"there is no cashier available in this id"+Color.RESET);
                        }
                    }
                    
                    ArrayList<Products> product2 = new ArrayList<>();
            		try {
            			String query = "select * from products;";
            			PreparedStatement stmt = (PreparedStatement) Dbconnection.getConnection().prepareStatement(query);
            			ResultSet result = ((java.sql.Statement) stmt).executeQuery(query);
            			
            			while (result.next()) {
            				int id = result.getInt(1);
            				String name = result.getString(2);
            				int brandid = result.getInt(3);
            				int stock = result.getInt(4);
            				int product_type_id = result.getInt(5);
            				String expire_date = result.getString(6);
            				double Price = result.getDouble(7);
            				Products p = new Products(id,name,brandid,stock,product_type_id,expire_date,Price);
            				product2.add(p);
            			}
            		}
            		catch (Exception e) {
            			System.out.println("input mismatch error");
            		}
            		break;
                    case 12:{
                    	ArrayList<Products> transaction = new ArrayList<>();
                		try {
                			String query = "select * from transaction";
                			PreparedStatement stmt = (PreparedStatement) Dbconnection.getConnection().prepareStatement(query);
                			ResultSet result = ((java.sql.Statement) stmt).executeQuery(query);
                			
                			System.out.println("----------------------------------- Today's transaction -------------------------------");
                            System.out.printf("|%-8s | %-8s | %-8s | %-8s | %-11s| %-12s | %-10s | %n", "Id", "Pid", "cid", "stock","expire date","price","Total price");
                			while (result.next()) {
                				int tid = result.getInt(1);
                				int pid = result.getInt(2);
                				int cid = result.getInt(3);
                				int stock = result.getInt(4);
                				double price = result.getInt(5);
                				int total_price = result.getInt(6);
                				String expdate = result.getString(7);
                				System.out.printf("|%-8s | %-8s | %-8s | %-8s | %-11s| %-12s | %-10s | %n", tid, pid, cid, stock,expdate,price,total_price);
                			}
                			System.out.println("----------------------------------------------------------------------------------------");
                		}
                		catch (Exception e) {
                			System.out.println("input mismatch error");
                		}
                    	
                        break;
                    }
                    case 13:{
                    	ArrayList<Products> product = new ArrayList<>();
                		try {
                			String query = "select * from products where stock_quantity < 10;";
                			PreparedStatement stmt = (PreparedStatement) Dbconnection.getConnection().prepareStatement(query);
                			ResultSet result = ((java.sql.Statement) stmt).executeQuery(query);
                			
                			while (result.next()) {
                				int id = result.getInt(1);
                				String name = result.getString(2);
                				int brandid = result.getInt(3);
                				int stock = result.getInt(4);
                				int product_type_id = result.getInt(5);
                				String expire_date = result.getString(6);
                				double Price = result.getDouble(7);
                				Products p = new Products(id,name,brandid,stock,product_type_id,expire_date,Price);
                				product.add(p);
                			}
                		}
                		catch (Exception e) {
                			System.out.println("input mismatch error");
                		}
                		System.out.printf("|%-8s | %-8s | %-8s | %-8s | %-11s| %-12s | %-10s | %n", "Id", "Pid", "cid", "stock","expire date","price","Total price");
                		for (Products p : product) {
                			if (p.stockQuantity <= 10) {
                				p.displayProduct();
                			}
                		}
                        break;
                    }
                    case 14:{
                        continue main;
                    }
                    case 15: {
                        break main;
                    }
                    default: {
                        System.out.println(Color.RED + "you have entered the invalid option  (o_o)" + Color.RESET);
                        System.out.println("please enter again : ");
                    }
                }
            }
        }
        if (currentuser instanceof Cashier){
            while (true) {
                System.out.println("___________________________________________________________________________");
                System.out.println(Color.YELLOW+ "enter the function you want to do : \n1) Add new product  \n2) update product \n3) delete product \n4) view products \n5) Receipt Generation \n6) add new customer \n7) view all customers \n8) Transaction history \n9) sign up to another account \n10) Exit");
                System.out.println(
                        Color.RESET + "___________________________________________________________________________");
                int option = Getinput.getinputNumber();
                switch (option) {
                    case 1: {
                    	System.out.println("enter the product name : ");
                        String productname = input.nextLine();
                        System.out.println("enter the brand name : ");
                        String brand = input.nextLine();
                        System.out.println("enter the product price");
                        double price = Getinput.getinputDouble();
                        System.out.println("enter the stock quantity");
                        int stock = Getinput.getinputNumber();
                        
                        System.out.println(
                                "enter the type of the product you are addded : \n1)Grocery \n2)Technology \n3)Stationary");
                        int type = Getinput.getinputNumber();
                        switch (type) {
                            case 1: {
                                System.out.println("enter the expiry date of the product (yyyy-mm-dd) : ");
                                String date = input.nextLine();
                    			
                                Groceries pGroceries = new Groceries(productname, brand, stock, price, date);
                                Products.addproduct(pGroceries);
                                // brand
                                try {
                                	String Query = "insert into brand (bid,pid,brand) values(?,?,?)";
                        			PreparedStatement stmt = Dbconnection.getConnection().prepareStatement(Query);
                        			stmt.setInt(1, brandId);
                        			stmt.setString(2, pGroceries.brand);
                        			int resultSet = stmt.executeUpdate();
        						} catch (Exception e) {
        							System.out.println("mysql syntax error for brand");
                        			e.printStackTrace();
        						}

                                
                                try {
                        			String Query = "insert into products (pname, bid, stock_quantity, product_type_id, expiredate, price) values (?, ?, ?, ?, ?, ?)";
                        			PreparedStatement stmt = Dbconnection.getConnection().prepareStatement(Query);
                        			
                        			stmt.setString(1, productname);
                        			stmt.setInt(2, brandId);
                        			stmt.setInt(3, stock);
                        			stmt.setInt(4, 2);
                        			stmt.setDate(5, Date.valueOf(date));
                        			stmt.setDouble(6, price);
                        			brandId++;
                        			int resultSet = stmt.executeUpdate();
                        			
                        		} catch (SQLException e) {
                        			System.out.println("mysql syntax error for ");
                        			e.printStackTrace();
                        		}
                                
                                break;
                            }
                            case 2: {
                                System.out.println("enter the warranty or garranty of the product : (yyyy-mm-dd) ");
                                String date = input.nextLine();
                                Technology pTechnology = new Technology(productname, brand, stock, price, date);
                                System.out.println(pTechnology.brandId);
                                try {
                                	String Query = "insert into brand (bid,brand) values(?,?)";
                        			PreparedStatement stmt = Dbconnection.getConnection().prepareStatement(Query);
                        			stmt.setInt(1, brandId);
                        	
                        			stmt.setString(2, pTechnology.brand);
                        			int resultSet = stmt.executeUpdate();
                        			
        						} catch (Exception e) {
        							System.out.println("mysql syntax error for brand");
                        			e.printStackTrace();
        						}
                                Products.addproduct(pTechnology);
                                try {
                        			String Query = "insert into products (pname, bid, stock_quantity, product_type_id, expiredate, price) values (?, ?, ?, ?, ?, ?)";
                        			PreparedStatement stmt = Dbconnection.getConnection().prepareStatement(Query);
                        			
                        			stmt.setString(1, productname);
                        			stmt.setInt(2, brandId);
                        			stmt.setInt(3, stock);
                        			stmt.setInt(4, 2);
                        			stmt.setDate(5, Date.valueOf(date));
                        			stmt.setDouble(6, price);
                        			brandId++;
                        			int resultSet = stmt.executeUpdate();
                        			
                        		} catch (SQLException e) {
                        			System.out.println("mysql syntax error for ");
                        			e.printStackTrace();
                        		}
                                break;
                            }
                            case 3: {
                                System.out.println("Enter the durability of the product : ");
                                String date = input.nextLine();
                                Stationary pStationary = new Stationary(productname, brand, stock, price, date);
                                try {
                                	String Query = "insert into brand (bid,brand) values(?,?)";
                        			PreparedStatement stmt = Dbconnection.getConnection().prepareStatement(Query);
                        			stmt.setInt(1, brandId);
                        			stmt.setString(2, pStationary.brand);
                        			
                        			int resultSet = stmt.executeUpdate();
        						} catch (Exception e) {
        							System.out.println("mysql syntax error for brand");
                        			e.printStackTrace();
        						}
                                Products.addproduct(pStationary);
                                try {
                        			String Query = "insert into products (pname, bid, stock_quantity, product_type_id, expiredate, price) values (?, ?, ?, ?, ?, ?)";
                        			PreparedStatement stmt = Dbconnection.getConnection().prepareStatement(Query);
                        			
                        			stmt.setString(1, productname);
                        			stmt.setInt(2, brandId);
                        			stmt.setInt(3, stock);
                        			stmt.setInt(4, 2);
                        			stmt.setDate(5, Date.valueOf(date));
                        			stmt.setDouble(6, price);
                        			
                        			int resultSet = stmt.executeUpdate();
                        			brandId++;
                        		} catch (SQLException e) {
                        			System.out.println("mysql syntax error for ");
                        			e.printStackTrace();
                        		}
                                break;
                            }
                            default: {
                                System.out.println(Color.RED + "invalid option" + Color.RESET);
                                break;
                            }
                        }
                        break;
                    }
                    case 2: {
                    	ArrayList<Products> products = new ArrayList<>();
                		try {
                			String query = "select * from products;";
                			PreparedStatement stmt = (PreparedStatement) Dbconnection.getConnection().prepareStatement(query);
                			ResultSet result = ((java.sql.Statement) stmt).executeQuery(query);
                			
                			while (result.next()) {
                				int id = result.getInt(1);
                				String name = result.getString(2);
                				int brandid = result.getInt(3);
                				int stock = result.getInt(4);
                				int product_type_id = result.getInt(5);
                				String expire_date = result.getString(6);
                				double Price = result.getDouble(7);
                				Products p = new Products(id,name,brandid,stock,product_type_id,expire_date,Price);
                				products.add(p);
                			}
                		}
                		catch (Exception e) {
                			System.out.println("input mismatch error");
                		}
                    	System.out.println("enter the id of the product you are going to update : ");
                        int updatingProduct = Getinput.getinputNumber();
                        for (Products pro : products) {
                            if (updatingProduct == pro.productID) {
                                System.out.println(
                                        "which thing do you want to update \n 1)product name \n 2)product price \n 3)stock quantity");
                                int opt = Getinput.getinputNumber();
                                input.nextLine();
                                switch (opt) {
                                    case 1: {
                                        System.out.println("enter the product name : ");
                                        String Pname = input.nextLine();
                                        PreparedStatement stmt;
                                		try {
                                			stmt = (Dbconnection.getConnection()).prepareStatement("update products set pname = ? where pid = ?");
                                			stmt.setString(1, Pname);
                                			stmt.setInt(2, updatingProduct);
                                			int resultSet = stmt.executeUpdate();
                                		} catch (SQLException e) {
                                			e.printStackTrace();
                                		}
                                        pro.updateproductname(Pname);
                                        break;
                                    }
                                    case 2: {
                                        System.out.println("enter the product price : ");
                                        PreparedStatement stmt;
                                        try {
                                            double price = Getinput.getinputDouble();
                                            while (price < 0) {
                                                System.out.println("enter the valid price :");
                                                price = Getinput.getinputDouble();
                                            }
                                            stmt = (Dbconnection.getConnection()).prepareStatement("update products set price = ? where pid = ?");
                                			stmt.setDouble(1, price);
                                			stmt.setInt(2, updatingProduct);
                                			int resultSet = stmt.executeUpdate();
//                                            pro.updateproductprice(price);
                                        } catch (Exception e) {
                                            System.out.println("enter the valid input :");
                                            e.getMessage();
                                        }
                                        break;
                                    }
                                    case 3: {
                                        System.out.println("enter the stock quantity of the product : ");
                                        int stock = Getinput.getinputNumber();
                                        while (stock < 0) {
                                            stock = Getinput.getinputNumber();
                                        }
                                        PreparedStatement stmt;
                                		try {
                                			stmt = (Dbconnection.getConnection()).prepareStatement("update products set pname = ? where pid = ?");
                                			stmt.setInt(1, stock);
                                			stmt.setInt(2, updatingProduct);
                                			int resultSet = stmt.executeUpdate();
                                		} catch (SQLException e) {
                                			e.printStackTrace();
                                		}
                                        break;
                                    }
                                    default:
                                        break;
                                }
                            }
                        }
                        break;
                    }
                    case 3: {
                    	System.out.println("enter the id of the product you want to delete : ");
                        int id = Getinput.getinputNumber();
                        PreparedStatement stmt;
                		try {
                			stmt = (Dbconnection.getConnection()).prepareStatement("delete from products where pid = ?;");
                			stmt.setInt(1, id);
                			int resultSet = stmt.executeUpdate();
                		} catch (SQLException e) {
                			e.printStackTrace();
                		}
                		break;
                    }
                    case 4: {
                    	ArrayList<Products> products = new ArrayList<>();
                		try {
                			String query = "select * from products;";
                			PreparedStatement stmt = (PreparedStatement) Dbconnection.getConnection().prepareStatement(query);
                			ResultSet result = ((java.sql.Statement) stmt).executeQuery(query);
                			
                			while (result.next()) {
                				int id = result.getInt(1);
                				String name = result.getString(2);
                				int brandid = result.getInt(3);
                				int stock = result.getInt(4);
                				int product_type_id = result.getInt(5);
                				String expire_date = result.getString(6);
                				double Price = result.getDouble(7);
                				Products p = new Products(id,name,brandid,stock,product_type_id,expire_date,Price);
                				products.add(p);
                			}
                		}
                		catch (Exception e) {
                			System.out.println("input mismatch error");
                		}
                        System.out.println(
                                "what type of product you want to see \n1)view all products \n2)product stock \n3)brand");
                        int disopt = Getinput.getinputNumber();
                        switch (disopt) {
                            case 1: {
                                System.out.println("-------------------------------------------------------------------------------------------------------------------------");
                                System.out.printf("|%-5s | %-15s | %-18s |%-11s | %-15s | %-25s | %-9s | %n", "Id","Product Name", "brand", "stock", "product type", "expiry/durability/warranty","price");
                                for (int i = 0; i < products.size(); i++) {
                                    products.get(i).displayProduct();
                                }
                                System.out.println("-------------------------------------------------------------------------------------------------------------------------");
                                break;
                            }
                            
                            case 2: {
                                System.out.println("Enter the amount of stock you are going to search");
                                int stock = Getinput.getinputNumber();
                                System.out.println(
                                        "-------------------------------------------------------------------------------------------------------------------------");
                                System.out.printf("|%-5s | %-15s | %-18s |%-11s | %-15s | %-25s | %-9s | %n", "Id",
                                        "Product Name", "brand", "stock", "product type", "expiry/durability/warranty",
                                        "price");
                                for (int i = 0; i < products.size(); i++) {
                                    if (productsList.get(i).stockQuantity <= stock) {
                                        productsList.get(i).displayProduct();
                                    }
                                }
                                System.out.println(
                                        "-------------------------------------------------------------------------------------------------------------------------");
                                break;
                            }
                            case 3: {
                                // input.nextLine();
                                System.out.println("enter the brand you are going to view : ");
                                String brand1 = input.nextLine();
                                System.out.println(
                                        "-------------------------------------------------------------------------------------------------------------------------");
                                System.out.printf("|%-5s | %-15s | %-18s |%-11s | %-15s | %-25s | %-9s | %n", "Id",
                                        "Product Name", "brand", "stock", "product type", "expiry/durability/warranty",
                                        "price");
                                for (int i = 0; i < productsList.size(); i++) {
                                    if ((productsList.get(i).brand).equals(brand1 + "")) {
                                        productsList.get(i).displayProduct();
                                    }
                                }
                                System.out.println(
                                        "-------------------------------------------------------------------------------------------------------------------------");
                                break;
                            }
                            default: {
                                System.out.println("you have entered invalid input : ");
                                break;
                            }
                        }
                    }
                        break;
                case 5:{
                	System.out.println("enter (1) for you are a existing customer \nenter (2) for you are a new customer : ");
                    int option1 = Getinput.getinputNumber();
                    Customer customer = new Customer();
                    if (option1 == 1) {
                        System.out.println("enter the customer id :");
                        int custemerid = Getinput.getinputNumber();
                        for (Customer cus : customerList) {
                            if (("cust-" + custemerid).equals(cus.custemerId)) {
                                customer = cus;
                                break;
                            }
                        }
                    } else {
                        System.out.println("enter the coustomer name : ");
                        String custName = input.nextLine();
                        System.out.println("enter the customer phone number : ");
                        long phnumber =(long) Getinput.getinputLong();
                        while (true) {
                            if ((phnumber+"").trim().length() == 10) {
                                break;
                            } else {
                                System.out.println("Enter the phone number properly");
                                phnumber = (long) Getinput.getinputLong();
                            }
                        }
                        Customer costumer1 = new Customer(custName, phnumber);
                        try {
                			String Query = "insert into customer (cname,phone_number) values (?,?)";
                			PreparedStatement stmt = Dbconnection.getConnection().prepareStatement(Query);
                			
                			stmt.setString(1, costumer1.customerName);
                			stmt.setLong(2, costumer1.phoneNumber);
                			
                			int resultSet = stmt.executeUpdate();
                			
                		} catch (SQLException e) {
                			System.out.println("mysql syntax error");
                			e.printStackTrace();
                		}
                        customer = costumer1;
                        customer.transactioncount++;
                    }
                    int opt = 0;
                    ArrayList<Products> products = new ArrayList<>();
            		try {
            			String query = "select * from products;";
            			PreparedStatement stmt = (PreparedStatement) Dbconnection.getConnection().prepareStatement(query);
            			ResultSet result = ((java.sql.Statement) stmt).executeQuery(query);
            			
            			while (result.next()) {
            				int id = result.getInt(1);
            				String name = result.getString(2);
            				String brand = result.getString(3);
            				int stock = result.getInt(4);
            				String product_type = result.getString(5);
            				String expire_date = result.getString(6);
            				double Price = result.getDouble(7);
            				Products p = new Products(id,name,brand,stock,product_type,expire_date,Price);
            				products.add(p);
            			}
            		}
            		catch (Exception e) {
            			System.out.println("input mismatch error");
            		}
                    System.out.println("-------------------------------------------------------------------------------------------------------------------------");
                    System.out.printf("|%-5s | %-15s | %-18s |%-11s | %-15s | %-25s | %-9s | %n", "Id","Product Name", "brand", "stock", "product type", "expiry/durability/warranty","price");
                    
                    for (int i = 0; i < products.size(); i++) {
                        products.get(i).displayProduct();
                    }
                    
                    System.out.println("-------------------------------------------------------------------------------------------------------------------------");
                    do {
                        Products product = new Products();
                        System.out.println("enter the product Id you want to buy : ");
                        int product_id = Getinput.getinputNumber();
                        for (PurcasedProduct product2 : customer.order) {
                            while (product2.productID == product_id) {
                                if (product2.productID == product_id) {
                                    System.out.println(
                                            "you have already purchased this product: do you want to update or not enter :\n1) update this product\n2) change product ");
                                    product_id = Getinput.getinputNumber();
                                }
                            }
                        }
                        System.out.println("enter the quantity of the product : ");
                        int quantity = Getinput.getinputNumber();
                        while (quantity <= 0) {
                            System.out.println("enter the valid quantity : ");
                            quantity = Getinput.getinputNumber();
                        }
                        for (Products pro : ProductManagementSystem.productsList) {
                            while (pro.productID == product_id) {
                                if (quantity <= pro.stockQuantity) {
                                    product = pro;
                                    break;
                                } else {
                                    System.out.println("there is only " + pro.stockQuantity
                                            + " is available \nkindly enter the quantity again : ");
                                    quantity = Getinput.getinputNumber();
                                }
                            }
                        }
                        Products.updatestock(product_id, quantity);
                        customer.addPurcasedProduct(product, quantity);
                        System.out.println("do you want to add products : \n 1) yes \n 2) no");
                        opt = Getinput.getinputNumber();
                        for (int i=0; i<customer.order.size(); i++) {
                        	try {
                    			String Query = "insert into transaction (pid,cid,quantity,price,total_price) values(?,?,?,?,?)";
                    			PreparedStatement stmt = Dbconnection.getConnection().prepareStatement(Query);
                    			
                    			stmt.setInt(1, customer.order.get(i).productID);
                    			stmt.setInt(2, customer.custemerId);
                    			stmt.setInt(3, customer.order.get(i).quantity);
                    			stmt.setDouble(4, customer.order.get(i).price);
                    			stmt.setDouble(5, customer.order.get(i).quantity*customer.order.get(i).price);
                    			int resultSet = stmt.executeUpdate();
                    		} catch (SQLException e) {
                    			System.out.println("mysql syntax error");
                    			e.printStackTrace();
                    		}
                        }
                    } while (opt != 2);
                    RecieptGeneration.receiptgeneration(customer);
                    break;
                }
                case 6:{
                	System.out.println("enter the customer name : ");
                    String custName = input.nextLine();
                    System.out.println("enter the customer phone number : ");
                    long phnumber =(long) Getinput.getinputLong();
                    while (true) {
                        if ((phnumber+"").trim().length() == 10) {
                            break;
                        } else {
                            System.out.println("Enter the phone number properly");
                            phnumber = (long) Getinput.getinputLong();
                        }
                    }
                    Customer costumer1 = new Customer(custName, phnumber);
                    
                    try {
                    	String Query = "insert into customer (cname,phone_number) values(?,?)";
            			PreparedStatement stmt = Dbconnection.getConnection().prepareStatement(Query);
            			stmt.setString(1, custName);
            			stmt.setLong(2, phnumber);
            			int resultSet = stmt.executeUpdate();
            			
					} catch (Exception e) {
						System.out.println("mysql syntax error");
            			e.printStackTrace();
					}
                    ProductManagementSystem.customerList.add(costumer1);

                    System.out.println(Color.GREEN+"customer added successfully"+Color.RESET);           
                break;
                }
                case 7:{
                	ArrayList<Products> customer = new ArrayList<>();
            		try {
            			String query = "select * from customers;";
            			PreparedStatement stmt = (PreparedStatement) Dbconnection.getConnection().prepareStatement(query);
            			ResultSet result = ((java.sql.Statement) stmt).executeQuery(query);
            			
            			while (result.next()) {
            				int id = result.getInt(1);
            				String name = result.getString(2);
            				int phoneNumber = result.getInt(3);
            				
            				Customer c = new Customer(id,name,phoneNumber);
            				
            			}
            		}
            		catch (Exception e) {
            			System.out.println("input mismatch error");
            		}
                    break;
                }
                case 8:{
                	System.out.println("enter the customer id :");
                    int customerId = Getinput.getinputNumber();
                    
                    ArrayList<Products> transaction = new ArrayList<>();
            		try {
            			String query = "select * from transaction where cid = ? ;";
            			PreparedStatement stmt = (PreparedStatement) Dbconnection.getConnection().prepareStatement(query);
            			stmt.setInt(1, customerId);
            			ResultSet result = ((java.sql.Statement) stmt).executeQuery(query);
            			
            			while (result.next()) {
            				int tid = result.getInt(1);
            				int pid = result.getInt(2);
            				int cid = result.getInt(3);
            				int quantity = result.getInt(4);
            				double price = result.getDouble(5);
            				double total_Price = result.getDouble(7);
            			System.out.printf("|%-10s |%-15s | %-15s | %-12s |%-12s | %n","product id","customer id","quantity","price","total price");
            			System.out.printf("|%-10s |%-15s | %-15s | %-12s |%-12s | %n",pid,cid,quantity,price,total_Price);
            			}
            		}
            		catch (Exception e) {
            			System.out.println("input mismatch error");
            		}
                    break;
                }
                case 9:{
                    continue main;
                }
                case 10:{
                    break main;
                }
                default:{
                    System.out.println("you have entered the invalid input : ");
                    break;
                } 
            }
        }
    }
    }
}
    public static ArrayList<Products> getProductsList() {
        return productsList;
    }

    public static void setProductsList(ArrayList<Products> productsList) {
        ProductManagementSystem.productsList = productsList;
    }

    public static ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    public static void setCustomerList(ArrayList<Customer> customerList) {
        ProductManagementSystem.customerList = customerList;
    }

}