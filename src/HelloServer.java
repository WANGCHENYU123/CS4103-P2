import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class HelloServer {
	private ServerSocket listener; // listen for client connection requests on this server socket
	private Hashtable h = new Hashtable(); 
	int ID;
	String pName = "ResourceP";
	String qName = "clientName";
	String coordinator = "6";
	int index = 0;
	public HelloServer(int port) {
		// Create a ServerSocket to listen on the communication
		// end-point specified by the IP address of this
		// machine and the port provided as an argument. ServerSocket listener = new
		//作为服务器接受coordinator一开始的连接
		try {
			listener = new ServerSocket(port);
			System.out.println("Server started ... listening on port " + port + "...");
			MessageLog logjava0 = new MessageLog(String.valueOf(port), "Server started ... listening on port " + port + "...");
			logjava0.logjava();
			while(true) {
				//receive message
				Socket nodes = listener.accept();
				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(nodes.getInputStream()));
				//BufferedReader electionPort = new BufferedReader(new InputStreamReader(client.getInputStream()));
				PrintWriter outToClient = new PrintWriter(new OutputStreamWriter(nodes.getOutputStream()), true);
				BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
				
				//STEP1
				System.out.println("RECEIVE NEW coordinator (Y(y)/N(n))?");
				BufferedReader step1 = new BufferedReader(new InputStreamReader(System.in));
				String strStep1 = step1.readLine();
				if(strStep1.equals("Y") || strStep1.equals("y")) {
					String newCoor = null;
					String nextNode = null;
					String message = null;				
					message = inFromClient.readLine();
					newCoor = inFromClient.readLine();
					System.out.println(message);
					MessageLog logjava1 = new MessageLog(String.valueOf(port), message);
					logjava1.logjava();
					//System.out.println("NEWCOOR = " + newCoor);
					nextNode = nextstr(port);
					if(port == 1)
					continue;
					Socket s = new Socket("localhost", Integer.parseInt(nextNode));
					System.out.println("Connected to localhost on port " + nextNode + ".");
					MessageLog logjava2 = new MessageLog(String.valueOf(port), "Connected to localhost on port " + nextNode + ".");
					logjava2.logjava();
					PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);
					BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
					out.println("Receive from " + port +": COORDINATOR {" + newCoor + "}");
					out.println(newCoor);
					continue;
					//break;
				}
				//step 3
				System.out.println("ready to RECEIVE client connection?(Y/N)?");
				BufferedReader step3 = new BufferedReader(new InputStreamReader(System.in));
				String strStep3 = step3.readLine();
				if(strStep3.equals("Y") || strStep3.equals("y")) {
						outToClient.println("Hello World from HelloServer");
						String mes3 = null;
						mes3 = inFromClient.readLine();
						System.out.println("Receive from " + mes3);
						//System.out.println("from " + mes4);
						MessageLog logjava3 = new MessageLog(String.valueOf(port), "Receive from " + mes3);
						logjava3.logjava();
						//continue;
				// 		System.out.println("Receive or send post?(Y/N)?");
				// BufferedReader step4 = new BufferedReader(new InputStreamReader(System.in));
				// String strStep4 = step4.readLine();
				// if(strStep4.equals("Y")) {
				// 	System.out.println(readFile(pName)); 
				// 	System.out.println(readClient(qName));
				// 	outToClient.println("hello");
				// 	String tokenMes = inFromClient.readLine();
				// 	System.out.println("tokenMes = " + tokenMes);
				// 	h.put(0, mes4);
				// 	System.out.println("HASH = " + h.values());
				// 	continue;
				// 	}
					//continue;
			}


				//STEP2
				System.out.println("Start coordinator election?(Y(y)/N(n))?");
				BufferedReader step2 = new BufferedReader(new InputStreamReader(System.in));
				String strStep2 = step2.readLine();
				if(strStep2.equals("Y") || strStep2.equals("y")) {
				// interaction with the client
				outToClient.println("PORT = " + port);
				String mes0 = null;
				String nextStr = null;
				String beforeStr = null;
				String next2Str = null;
				String electionNum = null;
				String electionNum1 = null;
				String mes1 = null;
				String mes2 = null;
				mes0 = inFromClient.readLine();
				System.out.println(mes0);
				MessageLog logjava4 = new MessageLog(String.valueOf(port), mes0);
				logjava4.logjava();
				nextStr = nextstr(port);
				beforeStr = beforestr(port);
				mes1 = inFromClient.readLine();
				System.out.println(mes1);
				MessageLog logjava5 = new MessageLog(String.valueOf(port), mes1);
				logjava5.logjava();
				mes2 = inFromClient.readLine();
				System.out.println("Receive from " + beforeStr + mes2 + "}");
				MessageLog logjava6 = new MessageLog(String.valueOf(port), "Receive from " + beforeStr + mes2 + "}");
				logjava6.logjava();
				System.out.println("Send to " + port + ":" + mes2 + "," + port + "}");
				MessageLog logjava7 = new MessageLog(String.valueOf(port), "Send to " + port + mes2 + "," + port + "}");
				logjava7.logjava();
				electionNum = inFromClient.readLine();
				//System.out.println("electionnum = " + electionNum);
				//System.out.println(electionNum);
				electionNum1 = electionNum + "," + port;
				beforeStr = beforestr(port);
				next2Str = next2str(port);
				if(nextStr.equals(coordinator)) {
					if(true) {
						Socket client1 = listener.accept();
						//System.out.println("port = " + port);
						System.out.println("Send current coordinator (Y(y)/N(n))?");
						BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
						String strIn = in.readLine();
						if(strIn.equals("Y") || strIn.equals("y")) {
							PrintWriter outToCoordinator = new PrintWriter(new OutputStreamWriter(client1.getOutputStream()), true);
							BufferedReader inFrom = new BufferedReader(new InputStreamReader(client1.getInputStream()));
							inFrom.readLine();
							outToCoordinator.println("Receive from " + port + " : ELECTION {" + electionNum1 + "}");
							outToCoordinator.println(electionNum1);
							continue;
						}
					}
				}

				//send message 
					int nextNode = Integer.parseInt(nextStr);
					Socket server = new Socket("localhost", nextNode);
					System.out.println("Client connected to localhost on port " + nextNode + ".");
					MessageLog logjava8 = new MessageLog(String.valueOf(port), "Client connected to localhost on port" + nextNode + ".");
					logjava8.logjava();
					BufferedReader inFromBeforeNode = new BufferedReader(new InputStreamReader(server.getInputStream()));
					PrintWriter outToNextNode = new PrintWriter(new OutputStreamWriter(server.getOutputStream()), true);
					outToNextNode.println("Receive from " + port + ": INFORM successor " + next2Str);
					outToNextNode.println("Ring connection to " + nextstr(Integer.parseInt(nextStr)));
					outToNextNode.println(" : ELECTION {" + electionNum1);
					outToNextNode.println(electionNum1);
					continue;
			}
			}
		} catch (IOException ioe) {
			System.out.println("Ooops " + ioe.getMessage());
		}
	}

	private String next2str(int port) {
		// TODO Auto-generated method stub
		List<String> str = new ArrayList<>();
		str = readFile(pName);
		String next2Str = null;
		String s = "";
		//find the next two node
		for(int i = 1; i < str.size(); i++) {
			s=String.valueOf(port);
			if(s.equals(str.get(i))) {
				if(i + 2 < str.size())
				{
					next2Str = str.get(i + 2);
					//System.out.println("next2Node = " + next2Str);
				}
				else if(i + 2 == str.size()){
					next2Str = str.get(1);
					//System.out.println("next2Node = " + next2Str);
				}
				else {
					next2Str = str.get(2);
					//System.out.println("next2Node = " + next2Str);
				}
			}
		}
		return next2Str;
	}

	private String maxnextstr(int port) {
		// TODO Auto-generated method stub
		List<String> str = new ArrayList<>();
		str = readFile(pName);
		String maxStr = str.get(1);
		String maxNextStr = null;
		//find the maximum node in the ring
		for(int i = 1; i < str.size(); i++) {
			if(Integer.parseInt(str.get(i)) > Integer.parseInt(maxStr)) {
				maxStr = str.get(i);
				if(i + 1 < str.size()) {
				maxNextStr = str.get(i + 1);}
				else {
					maxNextStr = str.get(1);}
			}
		}
		return maxNextStr;
	}

	private String beforestr(int port) {
		// TODO Auto-generated method stub
		List<String> str = new ArrayList<>();
		str = readFile(pName);
		String beforeStr = null;
		String s = "";
		//find the last node
		for(int i = 1; i< str.size(); i++) {
			s=String.valueOf(port);
			if(s.equals(str.get(i))) {
				if(i - 1 >= 1)
				{
					beforeStr = str.get(i - 1);
					//System.out.println("beforestr" + beforeStr);
				}
				else {
					beforeStr = str.get(str.size() - 1);
					//System.out.println("beforestr = " + beforeStr);
				}
			}
		}
		return beforeStr;
	}

	private String nextstr(int port) {
		// TODO Auto-generated method stub
		List<String> str = new ArrayList<>();
		str = readFile(pName);
		String nextStr = null;
		String s = "";
		//find the next node
		for(int i = 1; i < str.size(); i++) {
			s=String.valueOf(port);
			if(s.equals(str.get(i))) {
				if(i + 1 < str.size())
				{
					nextStr = str.get(i + 1);
					//System.out.println(nextStr);
				}
				else {
					nextStr = str.get(1);
					//System.out.println(nextStr);
				}
			}
		}
		return nextStr;
	}

	public static List<String> readFile(String fileName) {
		 String filePath = fileName;
		 List<String>  strings =new ArrayList<>();
		try {
		    File file = new File(filePath);
		    if(file.isFile() && file.exists()) {
		      InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
		      BufferedReader br = new BufferedReader(isr);
		      String lineTxt = null;
		      while ((lineTxt = br.readLine()) != null) {
		    	  String[] parts = lineTxt.split(" ");
		    			  strings.add(parts[0]);
		      }
		      br.close();
		    }
		  } catch (Exception e) {
		    System.out.println("文件读取错误!");
		  }
		return strings;
	}

public static List<String> readClient(String fileName) {
		 String filePath = fileName;
		 List<String>  strings =new ArrayList<>();
		try {
		    File file = new File(filePath);
		    if(file.isFile() && file.exists()) {
		      InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
		      BufferedReader br = new BufferedReader(isr);
		      String lineTxt = null;
		      while ((lineTxt = br.readLine()) != null) {
		    	  String[] parts = lineTxt.split(" ");
						  strings.add(parts[1]);
		      }
		      br.close();
		    }
		  } catch (Exception e) {
		    System.out.println("文件读取错误!");
		  }
		return strings;
	}
}
