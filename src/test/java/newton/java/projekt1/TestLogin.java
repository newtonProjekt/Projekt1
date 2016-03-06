package newton.java.projekt1;

import server.beans.Login;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Johan on 2016-03-06.
 */
public class TestLogin {

	public static void main(String[] args) {
		Login login = new Login("760207","password");
		try {
			Socket connection = new Socket("127.0.0.1",3004);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
