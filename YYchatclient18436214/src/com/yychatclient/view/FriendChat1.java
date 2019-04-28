package com.yychatclient.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.*;

import com.yychat.model.Message;
import com.yychatclient.controller.ClientConnect;

public class FriendChat1 extends JFrame implements ActionListener{
	JTextArea jta;
	JScrollPane jsp;
	
	JPanel jp;
	JTextField jtf;
	JButton jb;
	
	String sender;
	String receiver;
	public FriendChat1(String sender,String receiver) {
		this.sender=sender;
		this.receiver=receiver;
		
		jta=new JTextArea();
		jta.setEditable(false);
		jta.setForeground(Color.red);
		jsp=new JScrollPane(jta);
		this.add(jsp);
		
		jp=new JPanel();
		jtf=new JTextField(15);
		jtf.setForeground(Color.red);
		jb=new JButton("发送");
		jb.addActionListener(this);
		jp.add(jtf);
		jp.add(jb);
		this.add(jp,"South");
		
		this.setTitle(sender+"正在和"+receiver+"聊天");
		this.setSize(350,240);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	public static void main(String[] args) {
		//FriendChat friendChat=new FriendChat("1","2");
		
		

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jb) {
			jta.append(jtf.getText()+"\r\n");
		
			Message mess=new Message();
			mess.setSender(sender);
			mess.setReceiver(receiver);
			mess.setContent(jtf.getText());
			mess.setMessageType(Message.message_Common);
			ObjectOutputStream oos;
			try {
				Socket s=(Socket)ClientConnect.hmSocket.get(sender);
				oos = new ObjectOutputStream(s.getOutputStream());
				oos.writeObject(mess);
				
				/*
				 * ObjectInputStream ois=new
				 * ObjectInputStream(ClientConnect.s.getInputStream());
				 * mess=(Message)ois.readObject(); String
				 * showMessage=mess.getSender()+"对"+mess.getReceiver()+"说："+mess.getContent();
				 * System.out.println(showMessage); jta.append(showMessage+"\r\n");
				 */
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	
	}
	
	public void appendJta(String showMessage) {
		jta.append(showMessage+"\r\n");
	}
	

}
