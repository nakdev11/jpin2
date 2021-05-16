package jpin20210411;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HelloServer {

	public static void main(String[] args) throws Exception {

		ServerSocket server = new ServerSocket(9999);
		Socket socket = server.accept();

		InputStream is = socket.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		PrintWriter out = null;

		try {
			String line = br.readLine();
			System.out.println(line);

			out = new PrintWriter(socket.getOutputStream());
			out.print("HTTP/1.1 200 OK");
			out.print("\r\n");
			out.print("\r\n");

			if (line.contains("hello")) {
				out.print("<html>");
				out.print("<head></head>");
				out.print("<body>");
				out.print("<h1>hello!!</h1>");
				out.print("</body>");
				out.print("</html>");
			} else if (line.contains("bye")) {
				out.print("bye!!");
			} else {
				out.print("other");
			}
			out.flush();
		} finally {
			out.close();
			br.close();
			socket.close();
		}

	}

}
