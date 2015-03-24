package main;



import java.io.DataInputStream;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

public class WaitThread implements Runnable{
    
    public final UUID uuid = new UUID("41428440cf0211e488300800200c9a66", false); //it can be generated randomly
    public final String name = "PREN30BlueServer";                  //the name of the service
    public final String url  =  "btspp://localhost:" + uuid         //the service url
                                + ";name=" + name 
                                + ";authenticate=false;encrypt=false;";
    LocalDevice local = null;
    StreamConnectionNotifier server = null;
    StreamConnection conn = null;
    
	/** Constructor */
	public WaitThread() {
            System.out.println(uuid);
	}
	
	@Override
	public void run() {
		waitForConnection();		
	}
	
	/** Waiting for connection from devices */
	 public void waitForConnection() {
        try {
            System.out.println("Setting device to be discoverable...");
            local = LocalDevice.getLocalDevice();
            local.setDiscoverable(DiscoveryAgent.GIAC);
            System.out.println("Start advertising service...");
            server = (StreamConnectionNotifier)Connector.open(url);
            System.out.println("Waiting for incoming connection...");
            conn = server.acceptAndOpen();
            System.out.println("Client Connected...");
            DataInputStream din   = new DataInputStream(conn.openInputStream());
            while(true){
                String cmd = "";
                char c;
                while (((c = din.readChar()) > 0) && (c!='\n') ){
                    cmd = cmd + c;
                }
                System.out.println("Received " + cmd);
            }
            
        } catch (Exception  e) {System.out.println("Exception Occured: " + e.toString());}
    }
}
