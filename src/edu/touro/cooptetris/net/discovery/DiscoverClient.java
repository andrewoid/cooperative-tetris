package edu.touro.cooptetris.net.discovery;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class DiscoverClient {

	public String discoverTetrisServer() throws IOException {

		// copied from
		// http://stackoverflow.com/questions/10453721/broadcast-server-discovery
		MulticastSocket socket = new MulticastSocket(6020);
		InetAddress group = InetAddress.getByName("226.0.0.1");
		socket.joinGroup(group);

		DatagramPacket packet = new DatagramPacket(new byte[] { (byte) 0xF0 },
				1, group, 6020);
		socket.send(packet);

		byte buf[] = new byte[1];
		packet = new DatagramPacket(buf, buf.length);
		socket.receive(packet);

		return packet.getAddress().toString();

	}

	public static void main(String args[]) throws IOException {

		System.out.println(new DiscoverClient().discoverTetrisServer());

	}

}
