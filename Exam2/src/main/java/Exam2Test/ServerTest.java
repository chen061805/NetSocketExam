package Exam2Test;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {

	int port = 8821;
	void start() {
		Socket s =  null;
		try {
			
			ServerSocket ss = new ServerSocket(port);
			while(true) {
				String filePath = "C:/Users/chenbei/NetSocketExam/Exam1/target.pdf";
				File fi = new File(filePath);
				System.out.println("文件长度："+(int)fi.length());
				s = ss.accept();
				System.out.println("建立socket连接");
				DataInputStream dis = new DataInputStream(new BufferedInputStream(s.getInputStream()));
				dis.readByte();
				
				DataInputStream fis = new DataInputStream(new BufferedInputStream(new FileInputStream(filePath)));
				DataOutputStream ps = new DataOutputStream(s.getOutputStream());
				ps.writeUTF(fi.getName());
				ps.flush();
				
				int bufferSize = 8192;
				byte[] buf = new byte[bufferSize];
				
				while(true) {
					int read = 0;
					if (fis != null) {
						read = fis.read(buf);
					}
					if (read == -1)  {
						break;
					}
					ps.write(buf,0,read);
				}
				ps.flush();
				fis.close();
				s.close();
				System.out.println("传输完成");
			}
			

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		
		new ServerTest().start();
	}

}
