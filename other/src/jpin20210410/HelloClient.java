package jpin20210410;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class HelloClient {

	public static void main(String[] args) throws Exception {

		Socket server = new Socket("127.0.0.1", 9999);

		PrintWriter out = new PrintWriter(server.getOutputStream());

		Scanner scan = new Scanner(System.in);

//		String message;

		while(true) {

			String message = scan.nextLine();
			out.println(message);
			out.flush();

			if (message.equals("bye")) {
				server.close();
				break;
			}

		}

		scan.close();

	}
}
