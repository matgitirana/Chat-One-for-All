import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread {
	private Socket client;
	public ServerThread(Socket client) {
		this.client = client;
	}
	public void run() {
		try {
			ObjectInputStream input = new ObjectInputStream(client.getInputStream());
			ObjectOutputStream output;
			while(true) {
				String message = (String)input.readObject();
				System.out.println("Message received: '" + message + "'");
				for (int i = 0; i < Server.clients.size(); i++) {
					output =Server.clients.get(i);
					output.writeObject(message);
				}
			}

		} catch (EOFException e) {
			System.out.println("Client disconnected");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}


}
