import java.io.IOException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class MessageLog {
	// TODO Auto-generated method stub
	private String node;
	private String message;

	public MessageLog(String node, String message) {
		// TODO Auto-generated method stub
		this.node = node;
		this.message = message;
	}

	public void logjava() throws IOException {
		// TODO Auto-generated method stub
		Logger log = Logger.getLogger("tesglog");
		log.setLevel(Level.ALL);
		FileHandler fileHandler = new FileHandler("MessageLog.log");
		fileHandler.setLevel(Level.ALL);
		fileHandler.setFormatter(new LogFormatter());
		log.addHandler(fileHandler);
		log.info(node + " ] " + message);
	}
}

class LogFormatter extends Formatter {
	// TODO Auto-generated method stub
	public String format(LogRecord record) {
		Date date = new Date();
		String sDate = date.toString();
		return "[" + sDate +" |" +  record.getMessage() + "\n";
	}
}