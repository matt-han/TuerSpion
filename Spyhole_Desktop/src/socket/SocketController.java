package socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketController {
	
	final static String IP_ADDR = "localhost";
	final static String IP_PORT = "1988";
	
	public String checkString(String user_ID, String dl_ID){
		
		if (user_ID.length() == 1){
			user_ID = "00"+user_ID;
		} else if (user_ID.length() == 2) {
			user_ID = "0"+user_ID;
		} else if (user_ID.length() == 3) {
			
		}
			
		if (dl_ID.length() == 1){
			dl_ID = "000"+dl_ID;
		} else if (dl_ID.length() == 2) {
			dl_ID = "00"+dl_ID;
		} else if (dl_ID.length() == 3) {
			dl_ID = "0"+dl_ID;
		} else if (dl_ID.length() == 4) {
		
		} 
			
		
		
		
		return dl_ID+user_ID;
		
	}
	
	
	public void SocketClient(String modus, String user_ID, String dl_ID) {
		String string_ID= checkString(user_ID, dl_ID);
		Socket socket = null;
	    try {
	    	InetAddress inetAddress = InetAddress.getByName(IP_ADDR);
	        int port = Integer.parseInt(IP_PORT);
	         
	        socket = new Socket(inetAddress, port);
	        System.out.println("InetAddress: " + inetAddress);
	        System.out.println("Port: " + port);
	         
	        Scanner scanner = new Scanner(socket.getInputStream());
	        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
	         
	        Scanner userScanner = new Scanner(modus+string_ID);
	        String userInput = userScanner.nextLine();
	         
	        printWriter.println(userInput);
	        String serverEcho = scanner.nextLine();
	        System.out.println(serverEcho);
	
	    } catch (UnknownHostException ex) {
	        Logger.getLogger(SocketController.class.getName()).log(Level.SEVERE, null, ex);
	    } catch (IOException ex1) {
	        Logger.getLogger(SocketController.class.getName()).log(Level.SEVERE, null, ex1);
	    } finally {
	        if( socket != null){
	            try {
	                socket.close();
	                System.out.println("Socket closed...");
	            } catch (IOException ex) {
	                Logger.getLogger(SocketController.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
	    }
	}
	
	/*
	public void SocketServer() {
		int port;
	    ServerSocket serverSocket;
	    final String IP_PORT = "1988";
	        port = Integer.parseInt(IP_PORT);
	        System.out.println("Port: " + port);
	 
	        Socket socket = null;
	        try {
	            serverSocket = new ServerSocket(port);
	            
	            socket = serverSocket.accept();
	            Scanner scanner = new Scanner(socket.getInputStream());
	            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
	 
	            String line = scanner.nextLine();
	 
	            System.out.println("received: " + line);
	            printWriter.println("echo: " + line);
	        } catch (IOException ex) {
	            Logger.getLogger(SocketController.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	 
	            if (socket != null) {
	                try {
	                    socket.close();
	                } catch (IOException ex) {
	                    Logger.getLogger(SocketController.class.getName()).log(Level.SEVERE, null, ex);
	                }
	            }
	 
	        }
	 
	    }
	*/
}



