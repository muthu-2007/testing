import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    
    Client(){

    }

   void startMessage(){
      try {
        Socket client=new Socket("192.168.57.236 2401:4900:7b8c:77a8:ef7:f294:132c:891f 2401:4900:7b8c:77a8:423e:eea:e3c1:9687", 1235);
        
        DataInputStream in =new DataInputStream(client.getInputStream());
        DataOutputStream out = new  DataOutputStream(client.getOutputStream());
       

        Scanner sc=new Scanner(System.in);

        String paragraph="";
        String word="";
        while(!word.equals("END")){
            System.out.println("Please enter you para");
            word=sc.nextLine();
            paragraph+=word;

        }
        
        out.writeUTF(paragraph);
        System.out.println(in.readUTF());

    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
   }

   public static void main(String[] args) {
    Client c=new Client();
    c.startMessage();
   }

}