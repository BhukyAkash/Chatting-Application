package Application;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.*;
import java.net.*;



public class Client implements ActionListener  {
	
	JTextField text;
	static JPanel a1;
	static Box vertical = Box.createVerticalBox();
	static JFrame f = new JFrame();
	static DataOutputStream dout;
	
    Client(){

        f.setLayout(null);
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(7,94,84));
        p1.setBounds(0,0,450,70);
        f.add(p1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(10,25,25,25);
        p1.add(back);
        p1.setLayout(null);
        
        back.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e){
        		System.exit(0);
        	}
        });


        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("Icons/2.png"));
        Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel pic = new JLabel(i6);
        pic.setBounds(45,13,50,50);
        p1.add(pic);

        JLabel name  = new JLabel("Client");
        name.setBounds(110,20,100,15);
        name.setForeground(Color.white);
        name.setFont(new Font("ARIAL", Font.BOLD, 16));
        p1.add(name);
        
        JLabel active  = new JLabel("Online");
        p1.add(active);
        active.setForeground(Color.white);
        active.setFont(new Font("ARIAL", Font.BOLD, 12));
        active.setBounds(110,40,100,15);
        
        
        a1 = new JPanel();
        a1.setBounds(5,73,440,525);
        a1.setBackground(Color.white);
        f.add(a1);
        
        text = new JTextField();
        text.setBounds(5,600,330,40);
        text.setBackground(Color.gray);
        text.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 14));
        f.add(text);
        
        JButton but = new JButton("SEND");
        but.setBounds(340,600,110,40);
        but.setForeground(Color.white);
        but.setBackground(new Color(7,94,84));
        but.addActionListener(this);
        but.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 16));
        f.add(but);
        
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("Icons/video1.png"));
        Image i8 = i7.getImage().getScaledInstance(50, 55, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel video = new JLabel(i9);
        video.setBounds(300,10,50 ,55);
        p1.add(video);

        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("Icons/call1.png"));
        Image i11 = i10.getImage().getScaledInstance(53, 50, Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel phone = new JLabel(i12);
        phone.setBounds(350,10,50 ,55);
        p1.add(phone);

        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("Icons/more1.png"));
        Image i14 = i13.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel more = new JLabel(i15);
        more.setBounds(393,23,35,30);
        p1.add(more);
        
        
        f.setSize(450,670);
        f.setLocation(800,5);
        f.setUndecorated(true);
        f.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
    	try {
	    	//System.out.println(out);  //output in terminal 
			String out = text.getText();
	    	
			JPanel p2 = formatLabel(out);
			
			
	    	a1.setLayout(new BorderLayout());
	    	JPanel right = new JPanel(new BorderLayout());
	    	right.add(p2,BorderLayout.LINE_END);
	    	vertical.add(right);
	    	vertical.add(Box.createVerticalStrut(10));
	    	
	    	a1.add(vertical, BorderLayout.PAGE_START);
	    	
	    	dout.writeUTF(out);
	    	
	    	text.setText("");
	    	
	    	f.repaint();   //directly calling of Frame
	    	f.invalidate();   //use of repaint
	    	f.validate();
    	}catch(Exception ae ) {
    		ae.printStackTrace();
    	}
    	
	}
    public static JPanel formatLabel(String out) {
    	JPanel p3 = new JPanel();
    	p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));
    	
    	JLabel out1  = new JLabel(out);
    	out1.setFont(new Font("TIMES NEW ROMAN", Font.BOLD,16));
    	out1.setBackground(Color.green);
    	out1.setOpaque(true);
    	out1.setBorder(new EmptyBorder(5,5,5,50));
    	
    	p3.add(out1);
    	
    	
    	return p3;
    }
		
  
    public static void main(String[] args) {
        new Client();
        
        try {
        	Socket s = new Socket("127.0.0.1", 6001);
        	DataInputStream din = new DataInputStream(s.getInputStream());
        	dout = new DataOutputStream(s.getOutputStream());
        	
        	while(true) {
        		a1.setLayout(new BorderLayout());
        		String msg = din.readUTF();
        		
        		JPanel panel = formatLabel(msg); 
        		JPanel left = new JPanel(new BorderLayout());
        		left.add(panel,BorderLayout.LINE_START);
        		vertical.add(left);
        		
        		vertical.add(Box.createVerticalStrut(15));
        		a1.add(vertical, BorderLayout.PAGE_START);
        		
        		f.validate();
        		
        	}
        	
        	
        }catch(Exception e ) {
        	e.printStackTrace();
        }
        
    }

	
}
