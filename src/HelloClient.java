import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class HelloClient {
	private Socket socket;
	private String host;
	private int port;
	
	private BufferedReader inFromServer;
	private BufferedReader inFromUser;
	private PrintWriter outToServer;
	
	public HelloClient(String host, int port) {
		this.host = host;
		this.port = port;
		runClient();
	}

	private void runClient() {
		try {
			Socket server = new Socket(host, port);
			System.out.println("Client connected to " + host + " on port " + port + ".");
			inFromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
			outToServer = new PrintWriter(new OutputStreamWriter(server.getOutputStream()), true);
			// get something from the keyboard
			inFromUser = new BufferedReader(new InputStreamReader(System.in));
			// interaction with the server
			//outToServer.println("hi");
			System.out.println("I read from server: " + inFromServer.readLine());
			System.out.println("Please enter client name and message");
			outToServer.println(inFromUser.readLine());
			//outToServer.println(inFromUser.readLine());
			System.out.println("I read from server: " + inFromServer.readLine());
			printUserInputToSocket(); // this runs until something goes wrong
		} catch (Exception e) { // exit cleanly for any Exception (including IOException, DisconnectedException)
			System.out.println("Ooops on connection to " + host + " on port " + port + ". " + e.getMessage());
			cleanup(); // execute cleanup method to close connections cleanly
		}
	}
	
	private void printUserInputToSocket() throws IOException {
		while(true){
			String line = inFromUser.readLine(); // get user input
			outToServer.println(line);            // print line out on the socket's output stream
			outToServer.flush();                  // flush the output stream so that the server gets message immediately			
		}
	} 
	
	
	private void cleanup(){
		System.out.println("Client: ... cleaning up and exiting ... " );
		try {
			outToServer.close();
			inFromUser.close();
			socket.close();
		} catch (IOException ioe){
			System.out.println("Ooops " + ioe.getMessage());			
		}
	}
}
