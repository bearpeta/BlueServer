
package main;

import java.io.OutputStream;
import javax.microedition.io.StreamConnection;

/**
 *
 * @author Alessandro
 */
public class SendThread implements Runnable {

    private StreamConnection mConnection;

    private static final int EXIT_CMD = -1;
    
    private final String logMessage;

    public SendThread(StreamConnection connection) {
        mConnection = connection;
        logMessage = "";
    }
    
    public SendThread(StreamConnection connection, String message) {
        mConnection = connection;
        logMessage = message;
    }

    @Override
    public void run() {
        try {

            // prepare to receive data
            OutputStream outputStream = mConnection.openOutputStream();

            System.out.println("ready for output");
            if("".equals(logMessage)) {
                outputStream.write(EXIT_CMD);
                System.out.println("Stopsignal sended");
            }
            else {
                outputStream.write(logMessage.getBytes());
                System.out.println("Logmessage sended");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
