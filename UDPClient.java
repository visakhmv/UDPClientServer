import java.io.*;
import java.net.*;
 
class UDPClient{
   public static void main(String args[]) throws Exception {
      System.out.println("Welcome to UDP chat.. Enter 'bye' to exit");
      BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
      DatagramSocket clientSocket = new DatagramSocket();
      InetAddress IPAddress = InetAddress.getByName("localhost");
      byte[] sendData = new byte[1024];
      byte[] receiveData = new byte[1024];
      String sentence;
      do{
		  sentence = inFromUser.readLine();
		  sendData = sentence.getBytes();
		  DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
		  clientSocket.send(sendPacket);//sending to server
		  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		  clientSocket.receive(receivePacket);//receiving from server
		  String modifiedSentence = new String(receivePacket.getData(),0, receivePacket.getLength());
		  System.out.println("FROM SERVER:" + modifiedSentence);
      }while (!sentence.trim().equals("bye"));
      clientSocket.close();
   }
}

