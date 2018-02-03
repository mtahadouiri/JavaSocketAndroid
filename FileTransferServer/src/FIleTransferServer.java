import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FIleTransferServer {

	public static void main(String[] args) throws IOException {
		System.out.println("Server started");
		int bytesRead;
		int current = 0;

		ServerSocket serverSocket = null;
		serverSocket = new ServerSocket(13267);

		while (true) {
			Socket clientSocket = null;
			clientSocket = serverSocket.accept();

			InputStream in = clientSocket.getInputStream();

			DataInputStream din = new DataInputStream(in);
			String fileName = din.readUTF();

			// Writing the file to disk
			// Instantiating a new output stream object

			System.out.println("Downloading file "+fileName);
			OutputStream output = new FileOutputStream("C:/Users/PC/Desktop/files/" + fileName);
			long size = din.readLong();
			byte[] buffer = new byte[1024];
			while (size > 0 && (bytesRead = in.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
				output.write(buffer, 0, bytesRead);
				size -= bytesRead;
			}
			// Closing the FileOutputStream handle
			output.close();
		}

	}
}
