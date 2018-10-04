import java.io.IOException;
import java.io.ObjectInputStream;

public class ClientListener implements Runnable {
	ObjectInputStream input;

	ClientListener(ObjectInputStream input) {
		this.input = input;
	}

	public void run() {
		try {
			while(true) {
				System.out.println((String)input.readObject());
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		};
	}

}
