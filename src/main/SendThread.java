
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
    private IChangeState stateChanger;

    public SendThread(StreamConnection connection, IChangeState stateChanger) {
        mConnection = connection;
        logMessage = "";
        this.stateChanger = stateChanger;
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
                stateChanger.changeConnectionState(false);
                mConnection.close();
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
