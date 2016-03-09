package newton.java.projekt1;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import server.beans.Login;
import server.beans.Message;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Test to open socket, send commands and display feedback from server in stdout.
 *
 * Created by Johan on 2016-03-06.
 */
public class TestLogin {

	private Socket connection;

	public static void main(String[] args) {

		Thread thread = new Thread(new Connect());
		thread.start();
	}
}

class Connect implements Runnable{

	Socket connection;
	PrintWriter out;
	private boolean disconnect = false;
	Gson gson;

	public Connect(){
		gson = new Gson();
		try {
			connection = new Socket("127.0.0.1", 3004);
			out = new PrintWriter(connection.getOutputStream(),true);
			System.out.println("Connection established");
			doTest();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void paus(){
		System.out.println("Waiting 1 sec.");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void doTest(){
		System.out.println("Starting login");
		Login login = new Login("454545","password",true);
		send("login",login);

		paus();

		//System.out.println("Available tests");
		//send("gettestlist","");

		paus();

		//System.out.println("Setting test 10 as started");
		//send("starttest",10);

		paus();

		System.out.println("Requesting available tests");
		send("gettests","");

	}

	public <T> void send(String cmd, T cmdData){
		Message message = new Message(cmd);
		message.addCommandData(cmdData);
		System.out.println("TO SERVER: "+gson.toJson(message));
		out.println(gson.toJson(message));
	}

	public void disconnect(){
		disconnect = true;
	}

	@Override
	public void run() {
		while (!disconnect){
			try {
				Scanner sc = new Scanner(connection.getInputStream());
				while(sc.hasNextLine()){
					System.out.println("FROM SERVER: "+sc.nextLine());
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}