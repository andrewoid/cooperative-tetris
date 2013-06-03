package edu.touro.cooptetris.net.discovery;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;

public class DiscoverServer extends Thread {

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
				System.out.println("Listening for a packet");
				socket.receive(packet);
				System.out.println("Recieved a packet");

				DatagramPacket reply = new DatagramPacket(
						new byte[] { (byte) 0x0F }, 1,
						packet.getSocketAddress());
				System.out.println("Sending a packet");
				socket.send(reply);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String args[]) throws IOException {

		new DiscoverServer().run();

	}

}
