class RecieptGeneration {
       static void receiptgeneration(Customer customer){
    	   System.out.println(customer.order.size());
            double totalammount = 0;
            System.out.println("----------------------------------  Reciept -----------------------------------");
            System.out.println("_________________________________________________________________________________________________");
            System.out.printf("|%-10s | %-20s | %-18s |%-8s | %-11s | %-12s |%n","product id","product name","brand","quantity","price","total price");
            for (PurcasedProduct products: customer.order){
                System.out.println();
                System.out.printf("|%-10s | %-20s | %-18s | %-8s | %-10s | %-12s | %n",products.productID,products.productName,products.brand,products.quantity,products.price,products.totalPrice);
                totalammount += products.totalPrice;
            }
            System.out.println("------------------------------------------------------------------------------------------------");
            System.out.printf("|%-78s | %-12s | %n","Grand Total", totalammount);
            System.out.println("------------------------------------------------------------------------------------------------");
            customer.TransactionList.add(customer.order);
       }
}
