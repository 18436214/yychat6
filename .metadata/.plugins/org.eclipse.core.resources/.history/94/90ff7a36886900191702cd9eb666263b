package com.yychatserver.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.yychat.model.Message;
import com.yychat.model.User;

public class StartServer {
	public static HashMap hmSocket=new HashMap<String,Socket>();
	
	ServerSocket ss;
	Socket s;
	String userName;
	String passWord;
	Message mess;
	ObjectOutputStream oos;
	public StartServer() {
		try {
			ss=new ServerSocket(3456);
			System.out.println("服务器已经启动，监听3456端口");
			while(true) {
				s=ss.accept();
				System.out.println("连接成功:"+s);
				
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				User user=(User)ois.readObject();
				userName=user.getUserName();
				passWord=user.getPassWord();
				System.out.println(userName);
				System.out.println(passWord);
				
				mess=new Message();
				mess.setSender("Server");
				mess.setReceiver("userName");
				if(passWord.equals("123456")) {
					mess.setMessageType(Message.message_LoginSuccess);
				}else {
					mess.setMessageType(Message.message_LoginFailure);
				}
				
				sendMessage(s,mess);
				if(passWord.equals("123456")) {
					mess.setMessageType(Message.message_NewOnlineFriend);
					mess.setSender("Server");
					mess.setContent(this.userName);
					
					Set onlineFriendSet=hmSocket.keySet();
					Iterator it=onlineFriendSet.iterator();
					String friendName;
					while(it.hasNext()) {
						friendName=(String)it.next();
						mess.setReceiver(friendName);
						
						Socket s1=(Socket)hmSocket.get(friendName);
						sendMessage(s1,mess);
					}
					
					
					hmSocket.put(userName, s);
					new ServerReceiverThread(s).start();
				}
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void sendMessage(Socket s,Message mess) throws IOException {
		ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(mess);
	}

}
