
public class ClientMain {
	private static int port;
	public static void main(String[] args) {
		if(args.length < 1){ 
			System.out.println("Usage: ClientMain <port>");
			System.exit(1);
		} 
		port=Integer.valueOf(args[0]);
		HelloClient c = new HelloClient("localhost", port);
	}
}
