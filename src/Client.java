import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	private Socket client;
	private Scanner keyboardInput = new Scanner(System.in);
	private String IP;
	private void initializeClient() throws ClassNotFoundException {
		try {
			System.out.println("Type the IP to connect to: (Local = 127.0.0.1)");
			IP = keyboardInput.nextLine();
			client = new Socket(IP, 3322);
			System.out.println("Connection established on port 3322");
			System.out.println("Send EXIT to close the connection");
			ObjectOutputStream output = new ObjectOutputStream(client.getOutputStream());
			new Thread(new ClientListener(new ObjectInputStream(client.getInputStream()))).start();;
			while(true) {
				String message = keyboardInput.nextLine();
				if(message.equals("EXIT")) {
					System.out.println("---Connection closed---");
					client.close();
					System.exit(0);
				}
				else
					output.writeObject(message);
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main (String[] args) throws ClassNotFoundException {
		new Client().initializeClient();
	}
}
