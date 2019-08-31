
public class CoordinatorServerMain {
	private static int port;
	public static void main(String[] args) {
		if(args.length < 1){ 
			System.out.println("Usage: <port>");
			System.exit(1);
		}
		port=Integer.valueOf(args[0]);
		CoordinatorServer s = new CoordinatorServer(port);
	}
}