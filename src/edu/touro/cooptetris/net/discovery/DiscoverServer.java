package edu.touro.cooptetris.net.discovery;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DiscoverServer extends Thread {

	private final static Logger log = Logger.getLogger(DiscoverServer.class
			.getName());

	public void run() {

		MulticastSocket socket;
		try {
			socket = new MulticastSocket(6020);
			InetAddress group = InetAddress.getByName("226.0.0.1");
			socket.joinGroup(group);

			byte[] buf;
			DatagramPacket packet;
			while (true) {
				buf = new byte[1];
				packet = new DatagramPacket(buf, buf.length);
				log.log(Level.INFO, "Listening for a packet");
				socket.receive(packet);
				log.log(Level.INFO, "Recieved a packet");

				DatagramPacket reply = new DatagramPacket(
						new byte[] { (byte) 0x0F }, 1,
						packet.getSocketAddress());
				log.log(Level.INFO, "Sending a packet");
				socket.send(reply);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String args[]) throws IOException {

		new DiscoverServer().run();

	}

}
