package jpin20210410;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class HelloServer {

	public static void main(String[] args) throws Exception {

		ServerSocket server = new ServerSocket(9999);
		Socket socket = server.accept();

		InputStream is = socket.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		while(true) {
			String line = br.readLine();

			if (line == null) {
				continue;
			}

			if (line.isEmpty()) {
				continue;
			}

			if ("bye".equals(line)) {
				break;
			}

			System.out.println(line);
		}

		socket.close();

	}

}
