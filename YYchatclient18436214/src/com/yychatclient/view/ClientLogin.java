package com.yychatclient.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

import javax.swing.*;

import com.yychat.model.Message;
import com.yychat.model.User;
import com.yychatclient.controller.ClientConnect;

public class ClientLogin extends JFrame implements ActionListener{
	public static HashMap hmFriendList=new HashMap<String,FriendList>();
	
	JLabel jlbl1;
	
	JTabbedPane jtp1;
	JPanel jp2,jp3,jp4;
	JLabel jlbl2,jlbl3,jlbl4,jlbl5;
	JTextField jtf1;
	JPasswordField jpf1;
	JButton jb4;
	JCheckBox jcb1,jcb2;
	
	JButton jb1,jb2,jb3;
	JPanel jp1;
	
	public ClientLogin() {
		
		jlbl1=new JLabel(new ImageIcon("images/tou.gif"));
		this.add(jlbl1,"North");
		
		jp2=new JPanel(new GridLayout(3,3));
		jp3=new JPanel();jp4=new JPanel();
		jlbl2=new JLabel("YY∫≈¬Î",JLabel.CENTER);jlbl3=new JLabel("YY√‹¬Î",JLabel.CENTER);
		jlbl4=new JLabel("Õ¸º«√‹¬Î",JLabel.CENTER);
		jlbl4.setForeground(Color.blue);
		jlbl5=new JLabel("…Í«Î√‹¬Î±£ª§",JLabel.CENTER);
		jtf1=new JTextField();
		jpf1=new JPasswordField();
		jb4=new JButton(new ImageIcon("images/clear.gif"));
		jcb1=new JCheckBox("“˛…Ìµ«¬º");jcb2=new JCheckBox("º«◊°√‹¬Î");
		
		jp2.add(jlbl2);jp2.add(jtf1);jp2.add(jb4);
		jp2.add(jlbl3);jp2.add(jpf1);jp2.add(jlbl4);
		jp2.add(jcb1);jp2.add(jcb2);jp2.add(jlbl5);
		jtp1=new JTabbedPane();
		jtp1.add(jp2,"YY∫≈¬Î");jtp1.add(jp3," ÷ª˙∫≈¬Î");jtp1.add(jp4,"µÁ◊”” œ‰");
		this.add(jtp1);
		
		jb1=new JButton(new ImageIcon("images/denglu.gif"));
		jb1.addActionListener(this);
		jb2=new JButton(new ImageIcon("images/zhuce.gif"));
		jb3=new JButton(new ImageIcon("images/quxiao.gif"));
		jp1=new JPanel();
		jp1.add(jb1);jp1.add(jb2);jp1.add(jb3);
		this.add(jp1,"South");
		
		this.setSize(350,240);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientLogin clientLogin=new ClientLogin();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jb1) {
			String userName=jtf1.getText().trim();
			String passWord=new String(jpf1.getPassword());
			User user=new User();
			user.setUserName(userName);
			user.setPassWord(passWord);
			
			boolean loginSuccess=new ClientConnect().loginValidate(user);
			if(loginSuccess) {
				FriendList friendList=new FriendList(userName);
				hmFriendList.put(userName, friendList);
				
				Message mess=new Message();
				mess.setSender(userName);
				mess.setReceiver("Server");
				mess.setMessageType(Message.message_RequestOnlineFriend);
				
				Socket s=(Socket)ClientConnect.hmSocket.get(userName);
				ObjectOutputStream oos;
				try {
					oos=new ObjectOutputStream(s.getOutputStream());
					oos.writeObject(mess);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				this.dispose();
			}else {
				JOptionPane.showMessageDialog(this,"√‹¬Î¥ÌŒÛ");
			}
			
			
		}
		
	}

}
