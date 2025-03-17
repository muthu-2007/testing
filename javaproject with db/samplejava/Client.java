package samplejava;

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
        Socket client=new Socket("192.168.57.236 ", 1145);
        
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