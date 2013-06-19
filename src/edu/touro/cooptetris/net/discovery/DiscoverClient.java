package edu.touro.cooptetris.net.discovery;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DiscoverClient {

	private final static Logger log = Logger.getLogger(DiscoverClient.class
			.getName());

	public String discoverTetrisServer() throws IOException {

		// copied from
		// http://stackoverflow.com/questions/10453721/broadcast-server-discovery
		MulticastSocket socket = new MulticastSocket();
		InetAddress group = InetAddress.getByName(DiscoverServer.IP);

		DatagramPacket packet = new DatagramPacket(new byte[] { (byte) 0xF0 },
				1, group, DiscoverServer.PORT);
		socket.send(packet);

		byte buf[] = new byte[1];
		packet = new DatagramPacket(buf, buf.length);
		socket.receive(packet);

		String address = packet.getAddress().getHostAddress();

		return address;

	}

	public static void main(String args[]) throws IOException {

		log.log(Level.INFO, new DiscoverClient().discoverTetrisServer());

	}

}
