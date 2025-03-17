import java.util.ArrayList;
//import java.util.List;

public class LoginSystem {
    static ArrayList<User> usersList1 = new ArrayList<User>();
    static User  userLoginSystem() {
        boolean check = true;
        User currentuser = new User();
        while (check) {
            System.out.println("Enter the user name : ");
            String userName = Getinput.getInputString();
            System.out.println("Enter the password : ");
            String password1 = Getinput.getInputString();
            // LoginSystem.userLoginSystem(userName,password1);
            for (int i=0; i<usersList1.size(); i++){
                if ((usersList1.get(i).userName).equals(userName) && usersList1.get(i).password.equals(password1)){
                    check = false;
                    System.out.println("\n"+Color.GREEN+"Logined successfully "+Color.RESET);
                    currentuser = usersList1.get(i);
                    return currentuser;            }
            }
            System.out.println(Color.RED+"Invalid login please enter another time : "+Color.RESET);
        }
        return currentuser;
    }
}

abstract class person {
    String userName;
    String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    
}

class User extends person{
    
    User(String username, String password){
        this.userName = username;
        this.password = password;
    }
    User(){}
}

class Admin extends User{

    Admin(String username, String password){
        super(username,password);
    }

}

class Cashier extends User{
    static int count;
    int cashierId;
    String userName;
    String password;
    double salary;
    long phoneNumber;
    Cashier(){}
    Cashier(String username, String password, double salary,long phoneNumber){
        super(username,password);
        cashierId = (++count);
        this.salary = salary;
        this.phoneNumber = phoneNumber;
    }
    Cashier(int id,String username, String password, double salary,long phoneNumber){
        super(username,password);
        cashierId = id;
        this.salary = salary;
        this.phoneNumber = phoneNumber;
    }
	//Cashier cashier = new Cashier("jaga", "jaga@123");
    //LoginSystem.usersList.add(cashier);
    public static int getCount() {
        return count;
    }
    public static void setCount(int count) {
        Cashier.count = count;
    }
    public int getCashierId() {
        return cashierId;
    }
    public void setCashierId(int cashierId) {
        this.cashierId = cashierId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public long getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(long phnumber) {
        this.phoneNumber = phnumber;
    }
}
