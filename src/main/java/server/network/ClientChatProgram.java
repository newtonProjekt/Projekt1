/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.network;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.beans.Login;
import server.beans.Message;
import server.datamodel.Answer;
import server.datamodel.Question;
/**
 * 
 * @author DanielB
 *
 *	Detta är klientklassen till ServerChatProgram, denna innehåller både en skrivarklass och läsarklass.
 *	Kan användas för att koppla upp till alla socket-serverar som skriver ut information.
 */
public class ClientChatProgram {
	
	public static void main(String[] args){
//		Adress och portnummer behövs alltid för att skapa anslutning/använda sockets.
		String adress = "localhost";
		int portNumber = 3004;
		
		System.out.println("Klienten startar");
//		
		try {
//			Skapar en anslutning mot en server, denna kan kasta ett par exceptions (vilka alla är eller ligger under IOException)
			Socket socket = new Socket(adress, portNumber);

//			Kör igång en tråd för att kunna skriva till servern, klassen ser samma ut som serverns skrivar klass.			
			PrintServer pServer = new PrintServer(socket);
			Thread t1 = new Thread(pServer);
			t1.start();
			
//			Det måste gå att läsa det som servern skriver, vilket görs genom att först öppna en ström.
			InputStream stream  = socket.getInputStream();
//			Sedan öppnas en ström för att läsa det man får till datorn. 
			Scanner reader = new Scanner(stream);
//			Så länge som det finns en ny rad att hämta kommer denna skrivas ut, vilket endast kommer hända en gång i detta exempel.
			while(reader.hasNextLine()) {
//				nextLine kastar exception men borde inte hända då vi kollar om det finns en ny rad varje gång, innan de läses.
				System.out.println(reader.nextLine());
			}

		} 
//		Denna catch-sats fångar exception från nästan alla rader i try-satsen, enkelt att göra men kanske inte så bra då det blir så generellt.
		catch (IOException e) { 
			System.out.println("Exception som kastades: "+e);
		}
	}
}
/**
 * 
 *	Klassen används för att skriva till en server. är i princip samma som klassen i Servern.
 *
 */
class PrintServer implements Runnable{
	
	private Socket connection;
        Login login = new Login();
        Gson gson = new Gson();
        //JsonObject json = new JsonObject();
        Message message = new Message("login");
        
	
	public PrintServer(Socket connection) throws IOException {
		this.connection = connection;
                
                
                login.setLoginId("45454");
                login.setPassword("password");
                message.addCommandData(login);
                
                
                /*
                Question question = new Question();
                question.setId(1);
                question.setMultiQuestion(false);
                question.setPoints(2);
                question.setQuestionText("Huvudstaden i Spanien?");
                List<Answer> list = new ArrayList<Answer>();
                Answer answer = new Answer();
                answer.setId(0);
                answer.setAnswerText("madrid");
                list.set(0, answer);
                question.setAnswers(list);
                */
        
                PrintWriter pWriter = new PrintWriter(connection.getOutputStream());
                pWriter.println(gson.toJson(message));
		pWriter.flush();
                
                //gson.toJson(employee);
                
	}
	@Override
	public void run() {
//		Skapar ett scanner-objekt som lyssnar på vad användaren skriver i konsolen.
		//Scanner readingConsole = new Scanner(System.in);

		try{
//			Öppnar upp strömmen mot socket med 
                        //ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());
			//PrintWriter pWriter = new PrintWriter(connection.getOutputStream());
                        
                        Thread.sleep(1000);
			
			while(true){
				
                                //out.writeObject(gson);
				//pWriter.println(gson.toJson(employee));
				//pWriter.flush();
			}
		} catch (InterruptedException ex) {
                Logger.getLogger(PrintServer.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
}