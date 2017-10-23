package master_gardener_main;

import java.util.Scanner;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;

public class Main {
	public static void main(String[] args) throws Exception {
		Server server = new Server(8081);

		// Create and register a webapp context
		WebAppContext handler = new WebAppContext();
		handler.setContextPath("/Master-Gardener");
		handler.setWar("./war"); // web app is in the war directory of the project
		server.setHandler(handler);
		
		// Use 20 threads to handle requests
		server.setThreadPool(new QueuedThreadPool(20));
		
		// Start the server
		server.start();
		
		// Wait for the user to type "quit" or "restart
		System.out.println("Web server started at http://localhost:8081/Master-Gardener/home, type restart/0 to restart or quit/1 \n");
		Scanner keyboard = new Scanner(System.in);
		 while (keyboard.hasNextLine()) {
            String line = keyboard.nextLine();
            if (line.trim().toLowerCase().equals("restart") || line.trim().toLowerCase().equals("0")) {
                break;
            } else if (line.trim().toLowerCase().equals("quit") || line.trim().toLowerCase().equals("1")) {
                break;
            }
        }

		System.out.println("Shutting down...");
		keyboard.close();
		server.stop();
		server.join();
		System.out.println("Server has shut down, exiting");
	}
}
