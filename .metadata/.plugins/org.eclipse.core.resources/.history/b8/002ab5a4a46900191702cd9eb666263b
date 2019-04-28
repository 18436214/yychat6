package com.yychatclient.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.Socket;
import java.util.HashMap;

import javax.swing.*;

public class FriendList  extends JFrame implements ActionListener,MouseListener{
	public static HashMap hmFriendChat1=new HashMap<String,FriendChat1>();
	
	CardLayout cardLayout;
	
	
	JPanel myFriendPanel;
	JButton myFriendJButton;
	JPanel myStrangerBlackListJPanel;
	JButton myStrangerJButton;
	JButton blackListJButton;
	JPanel myFriendListJPanel;
	JScrollPane  myFriendJScrollPane;
	
	
	JPanel myStrangerPanel;
	JPanel myFriendStrangerPanel;
	JButton myFriendJButton1;
	JButton myStrangerJButton1;
	JButton blackListJButton1;
	JPanel myStrangerListJPanel;
	JScrollPane  myStrangerJScrollPane;
	
	
	JPanel BlackListPanel;
	JPanel myFriendStrangerBlackListPanel;
	JButton myFriendJButton2;
	JButton myStrangerJButton2;
	JButton blackListJButton2;
	JPanel myBlacklistJPanel;
	JScrollPane  myBlackListJScrollPane;
	
	
	
	static final int FRIENDCOUNT=51;
	JLabel[] myFriendJLabel=new JLabel[FRIENDCOUNT];
	
	static final int STRANGERCOUNT=21;
	JLabel[] myStrangerJLabel=new JLabel[STRANGERCOUNT];
	
	static final int BLACKLISTCOUNT=21;
	JLabel[] myBlacklistJLabel=new JLabel[BLACKLISTCOUNT];
	
	String userName;
	public FriendList(String userName) {
		this.userName=userName;//局部变量给成员变量赋值
		myFriendPanel=new JPanel(new BorderLayout());
		myFriendJButton=new JButton("我的好友");
		myFriendPanel.add(myFriendJButton,"North");
		myFriendListJPanel=new JPanel(new GridLayout(FRIENDCOUNT-1,1));
		for(int i=1;i<FRIENDCOUNT;i++) {
			myFriendJLabel[i]=new JLabel(i+"",new ImageIcon("images/YY1.gif"),JLabel.LEFT);
			myFriendJLabel[i].setEnabled(false);
			
			//if(Integer.parseInt(userName)==i) myFriendJLabel[i].setEnabled(true);
			
			myFriendJLabel[i].addMouseListener(this);
			myFriendListJPanel.add(myFriendJLabel[i]);
		}
		
		myFriendJLabel[Integer.parseInt(userName)].setEnabled(true);
		
		myFriendJScrollPane=new JScrollPane(myFriendListJPanel);
		myFriendPanel.add(myFriendJScrollPane);
		
		myStrangerBlackListJPanel=new JPanel(new GridLayout(2,1));
		myStrangerJButton=new JButton("我的陌生人");
		myStrangerJButton.addActionListener(this);
		blackListJButton=new JButton("黑名单");
		blackListJButton.addActionListener(this);
		myStrangerBlackListJPanel.add(myStrangerJButton);
		myStrangerBlackListJPanel.add(blackListJButton);
		myFriendPanel.add(myStrangerBlackListJPanel,"South");
		
		
		
		myStrangerPanel=new JPanel(new BorderLayout());
		myFriendStrangerPanel=new JPanel(new GridLayout(2,1));
		myFriendJButton1=new JButton("我的好友");
		myFriendJButton1.addActionListener(this);
		blackListJButton1=new JButton("黑名单");
		blackListJButton1.addActionListener(this);
		myStrangerJButton1=new JButton("我的陌生人");
		myFriendStrangerPanel.add(myFriendJButton1);
		myFriendStrangerPanel.add(myStrangerJButton1);
		myStrangerPanel.add(myFriendStrangerPanel,"North");
		myStrangerListJPanel=new JPanel(new GridLayout(STRANGERCOUNT-1,1));
		for(int i=1;i<STRANGERCOUNT;i++) {
			myStrangerJLabel[i]=new JLabel(i+"",new ImageIcon("images/YY1.gif"),JLabel.LEFT);
			myStrangerJLabel[i].addMouseListener(this);
			myStrangerListJPanel.add(myStrangerJLabel[i]);
		}
		myStrangerJScrollPane=new JScrollPane(myStrangerListJPanel);
		myStrangerPanel.add(myStrangerJScrollPane);
		
		myStrangerPanel.add(blackListJButton1,"South");
		
		
		
		BlackListPanel=new JPanel(new BorderLayout());
		myFriendStrangerBlackListPanel=new JPanel(new GridLayout(3,1));
		myFriendJButton2=new JButton("我的好友");
		myFriendJButton2.addActionListener(this);
		myStrangerJButton2=new JButton("我的陌生人");
		myStrangerJButton2.addActionListener(this);
		blackListJButton2=new JButton("黑名单");
		myFriendStrangerBlackListPanel.add(myFriendJButton2);
		myFriendStrangerBlackListPanel.add(myStrangerJButton2);
		myFriendStrangerBlackListPanel.add(blackListJButton2);
		BlackListPanel.add(myFriendStrangerBlackListPanel,"North");
		myBlacklistJPanel=new JPanel(new GridLayout(BLACKLISTCOUNT-1,1));
		for(int i=1;i<BLACKLISTCOUNT;i++) {
			myBlacklistJLabel[i]=new JLabel(i+"",new ImageIcon("images/YY1.gif"),JLabel.LEFT);
			myBlacklistJLabel[i].addMouseListener(this);
			myBlacklistJPanel.add(myBlacklistJLabel[i]);
		}
		myBlackListJScrollPane=new JScrollPane(myBlacklistJPanel);
		BlackListPanel.add(myBlackListJScrollPane);
		
		
		
		cardLayout=new CardLayout();
		this.setLayout(cardLayout);
		this.add(myFriendPanel,"1");
		this.add(myStrangerPanel,"2");
		this.add(BlackListPanel,"3");
		
		
		this.setSize(250,500);
		this.setTitle(userName+"的好友列表");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		//FriendList friendList=new FriendList();
		

	}
	public void setEnableFriendIcon(String friendString) {
		String[] friendName=friendString.split(" ");
		int count=friendName.length;
		for(int i=0;i<count;i++) {
			
			System.out.println("friendName["+i+"]:"+friendName[i]);
			myFriendJLabel[Integer.parseInt(friendName[i])].setEnabled(true);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==myFriendJButton1||arg0.getSource()==myFriendJButton2){
			cardLayout.show(this.getContentPane(),"1");
		}
		if(arg0.getSource()==myStrangerJButton||arg0.getSource()==myStrangerJButton2){
			cardLayout.show(this.getContentPane(),"2");
	    }
		if(arg0.getSource()==blackListJButton||arg0.getSource()==blackListJButton1){
			cardLayout.show(this.getContentPane(),"3");
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==2) {
			JLabel jlbl=(JLabel)e.getSource();
			String receiver=jlbl.getText();
			//new FriendChat(this.userName,receiver);
			//new Thread(new FriendChat(this.userName,receiver)).start();
			
			FriendChat1 friendChat1=(FriendChat1)hmFriendChat1.get(userName+"to"+receiver);
			if(friendChat1==null) {
				friendChat1=new FriendChat1(this.userName,receiver);
				hmFriendChat1.put(userName+"to"+receiver,friendChat1 );
			}else {
				friendChat1.setVisible(true);
			}
			
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JLabel jLabel=(JLabel)e.getSource();
		jLabel.setForeground(Color.red);
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JLabel jLabel=(JLabel)e.getSource();
		jLabel.setForeground(Color.black);
		
	}
}
