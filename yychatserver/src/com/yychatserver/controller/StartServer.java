package com.yychatserver.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
				
				if(user.getUserMessageType().equals("USER_REGISTER")) {
					boolean seekUserResult=YychatDbUtil.seekUser(userName);
					mess=new Message();
					mess.setSender("Server");
					mess.setReceiver(userName);
					if(seekUserResult) {
						mess.setMessageType(Message.message_RegisterFailure);
					}else {
						YychatDbUtil.addUser(userName,passWord);
						mess.setMessageType(Message.message_RegisterSuccess);
					}
					sendMessage(s,mess);
					s.close();
				}
				if(user.getUserMessageType().equals("USER_LOGIN")) {
					boolean loginSuccess=YychatDbUtil.loginValidate(userName, passWord);
					
					mess=new Message();
					mess.setSender("Server");
					mess.setReceiver("userName");
					if(loginSuccess) {
						mess.setMessageType(Message.message_LoginSuccess);
						
						/*
						 * String
						 * friend_Relation_Sql="select slaveuser from relation where majoruser=? and relationtype='1'"
						 * ; ptmt=conn.prepareStatement(friend_Relation_Sql); ptmt.setString(1,
						 * userName); rs=ptmt.executeQuery(); String friendString=""; while(rs.next()) {
						 * //rs.getString(1); friendString=friendString+rs.getString("slaveuser")+" "; }
						 */
						String friendString=YychatDbUtil.getFriendString(userName);
						mess.setContent(friendString);
						System.out.println(userName+"的relation数据表中好友："+friendString);
						
					}else {
						mess.setMessageType(Message.message_LoginFailure);
					}
				
				
				/*
				 * Class.forName("com.mysql.jdbc.Driver");
				 * 
				 * //String url="jdbc:mysql://127.0.0.1:3306/yychat"; String url=
				 * "jdbc:mysql://127.0.0.1:3306/yychat?useUnicode=true&characterEncoding=UTF-8";
				 * String dbuser="root"; String dbpass=""; Connection
				 * conn=DriverManager.getConnection(url,dbuser,dbpass);
				 * 
				 * String user_Login_Sql="select * from user where username=? and password=?";
				 * PreparedStatement ptmt=conn.prepareStatement(user_Login_Sql);
				 * ptmt.setString(1, userName); ptmt.setString(2, passWord);
				 * 
				 * 
				 * ResultSet rs=ptmt.executeQuery();
				 * 
				 * 
				 * boolean loginSuccess=rs.next();
				 * System.out.println("loginSuccess为："+loginSuccess);
				 */
				
				
				
				sendMessage(s,mess);
				//if(passWord.equals("123456")) {
				//if(passWord.equals("123456")) {
				if(loginSuccess) {
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
			
		} 
		}catch (IOException|ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void sendMessage(Socket s,Message mess) throws IOException {
		ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(mess);
	}

}
