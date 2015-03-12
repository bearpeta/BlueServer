package main;

public class RemoteBluetoothServer{
	
	public RemoteBluetoothServer() {
        }
        
        public void startServer(){
            Thread waitThread = new Thread(new WaitThread());
            waitThread.start();
        }
	
}
