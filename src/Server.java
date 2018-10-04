import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	public static ArrayList<ObjectOutputStream> clients = new ArrayList<ObjectOutputStream>();

	public static void main(String[] args) throws ClassNotFoundException {

		try {
			ServerSocket server = new ServerSocket(3322);
			System.out.println("Server started on port 3322");			
			while(true) {
				Socket client = server.accept();
				clients.add(new ObjectOutputStream(client.getOutputStream()));
				System.out.println("Connection detected from IP " + client.getInetAddress().getHostAddress());
				ServerThread thread = new ServerThread(client);
				thread.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}


	}

}
