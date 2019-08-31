import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class CoordinatorServer {
	String fileName = "ResourceP";
	public CoordinatorServer(int port) {
		//like a client
		try {
			//connect to other nodes
			List<String> str = new ArrayList<>();
			str = readFile(fileName);
			String coordinator = String.valueOf(port);
			String nextStr = null;
			String beforeStr = null;
			String next2Str = null;
			String electionNum = null;
			//String electionTotalNum = null;
			String maxElectionNum = null;
			nextStr = nextstr(Integer.parseInt(coordinator));
			beforeStr = beforestr(Integer.parseInt(coordinator));
			next2Str = next2str(port);
			int nextNode = Integer.parseInt(nextStr);
				
				//Step 1: transfer messages and coordinator election
				System.out.println("Start coordinator election?(Y(y)/N(n))");
				BufferedReader step1 = new BufferedReader(new InputStreamReader(System.in));
				String strStep1 = step1.readLine();
				if(strStep1.equals("Y") || strStep1.equals("y")) {
					Socket nextServer = new Socket("localhost", nextNode);
					System.out.println("Client connected to localhost on port " + nextNode + ".");
					MessageLog logjava0 = new MessageLog(coordinator, "Client connected to localhost on port " + nextNode + ".");
					logjava0.logjava();
					BufferedReader inFromServer1 = new BufferedReader(new InputStreamReader(nextServer.getInputStream()));
					PrintWriter outToServer1 = new PrintWriter(new OutputStreamWriter(nextServer.getOutputStream()), true);
					// interaction with the client
					String node = inFromServer1.readLine();
					outToServer1.println("Receive from current coordinator " + coordinator +": INFORM successor " + nextStr);
					outToServer1.println("Ring connection to " + next2Str);
					outToServer1.println(": ELECTION {6");
					outToServer1.println("6");

					//Step 2: select a new coordinator
					System.out.println("Select new coordinator? (Y(y)/N(n))");
					BufferedReader step2 = new BufferedReader(new InputStreamReader(System.in));
					String strStep2 = step2.readLine();
					if(strStep2.equals("Y") || strStep2.equals("y"))
					{
						Socket lastServer = new Socket("localhost", Integer.parseInt(beforeStr));
						System.out.println("Client connected to localhost on port " + beforeStr + ".");
						BufferedReader inFromServer2 = new BufferedReader(new InputStreamReader(lastServer.getInputStream()));
						PrintWriter outToServer2 = new PrintWriter(new OutputStreamWriter(lastServer.getOutputStream()), true);
						outToServer2.println("hi");
						String mes0 = null;
						mes0 = inFromServer2.readLine();
						System.out.println(mes0);
						MessageLog logjava1 = new MessageLog(coordinator, mes0);
						logjava1.logjava();
						electionNum = inFromServer2.readLine();
						//electionTotalNum = electionNum;
						//System.out.println("electionNUM = " + electionNum);
						maxElectionNum = order(electionNum);
						//System.out.println("electiontotalnum = " + electionTotalNum);
						System.out.println("Send to " + coordinator + ": ELECTION {" + electionNum+ "}");
						MessageLog logjava2 = new MessageLog(coordinator, "Send to " + coordinator + ": ELECTION {" + electionNum+ "}");
						logjava2.logjava();
					}
					//System.out.println("maxElectionNum = " + maxElectionNum);
					//coordinator = maxElectionNum;
				}
				
				//step 3: pass the new coordinator to other nodes
				System.out.println("Pass the new coordinator(Y(y)/N(n))?");
				BufferedReader step3 = new BufferedReader(new InputStreamReader(System.in));
				String strStep3 = step3.readLine();
				if(strStep3.equals("Y") || strStep3.equals("y")) {
					//System.out.println("coordinator = " + coordinator);
						Socket serverNew = new Socket("localhost", nextNode);
						System.out.println("Client connected to localhost on port " + nextNode + ".");
						MessageLog logjava3 = new MessageLog(coordinator, "Client connected to localhost on port " + nextNode + ".");
						logjava3.logjava();
						BufferedReader inFromServer3 = new BufferedReader(new InputStreamReader(serverNew.getInputStream()));
						PrintWriter outToServer3 = new PrintWriter(new OutputStreamWriter(serverNew.getOutputStream()), true);
						// interaction with the client
						outToServer3.println("Receive from current coordinator " + coordinator +": COORDINATOR {" + maxElectionNum + "}");
						outToServer3.println(maxElectionNum);
						//inFrom.readLine();
				}
				
				//step 4: start a client connection
				System.out.println("Start a client connection(Y(y)/N(n))?");
				BufferedReader step4 = new BufferedReader(new InputStreamReader(System.in));
				String strStep4 = step4.readLine();
				if(strStep4.equals("Y") || strStep4.equals("y")) {
					// listen for client connection requests on this server socket
					ServerSocket listener = new ServerSocket(port);
					System.out.println("Server started ... listening on port " + port + "...");
					MessageLog logjava4 = new MessageLog(coordinator, "Server started ... listening on port " + port + "...");
					logjava4.logjava();
					while (true) {
						// Block until a connection request is received at the ServerSocket.
						Socket client = listener.accept();
						BufferedReader inFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
						PrintWriter outToClient = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);
						// interaction with the client
						outToClient.println("Hello World from HelloServer");
						String mes1 = null;
						mes1 = inFromClient.readLine();
						System.out.println("receive from " + mes1);
						MessageLog logjava5 = new MessageLog(coordinator, "receive from " + mes1);
						logjava5.logjava();
					}
				}


				System.out.println("Post TOKEN (Y(y)/N(n))?");
				BufferedReader step5 = new BufferedReader(new InputStreamReader(System.in));
				String  strStep5= step5.readLine();
				if(strStep5.equals("Y") || strStep5.equals("y")) { 
						Socket tokenServer = new Socket("localhost", nextNode);
						System.out.println("Client connected to localhost on port " + nextNode + ".");
						BufferedReader infrom = new BufferedReader(new InputStreamReader(tokenServer.getInputStream()));
						PrintWriter outToNext = new PrintWriter(new OutputStreamWriter(tokenServer.getOutputStream()), true);
						infrom.readLine();
						outToNext.println("TOKEN");
						//continue;
				}

		} catch (IOException ioe) {
			System.out.println("Ooops " + ioe.getMessage());
		}
	}
	
	private String beforestr(int port) {
		// TODO Auto-generated method stub
		List<String> str = new ArrayList<>();
		str = readFile(fileName);
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
		str = readFile(fileName);
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

	private String next2str(int port) {
		// TODO Auto-generated method stub
		List<String> str = new ArrayList<>();
		str = readFile(fileName);
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

	public String order(String a) {
		String[] parts = a.split(",");
		String max = parts[1];
		for(int i = 1; i < parts.length; i++) {
			if(Integer.parseInt(parts[i]) > Integer.parseInt(max)) 
				max = parts[i];
		}
		//System.out.println("max = " + max);
		return max;
	}
	
public static List<String> readFile(String fileName) {
	 List<String>  strings =new ArrayList<>();
	try {
		 File file = new File(fileName);
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
		    else {
		    	System.out.println("the file does not exist");
		    }
	  } catch (Exception e) {
	    System.out.println("文件读取错误!");
	  }
	return strings;
}
}
