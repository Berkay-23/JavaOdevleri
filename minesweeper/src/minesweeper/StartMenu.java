package minesweeper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class StartMenu extends JFrame {

	private JPanel contentPane;
	
	public int yukseklik = 16;
	public int genislik = 16;
	public int mayinSayisi = 40;
	
	public StartMenu () {
		
		setTitle("Baþlat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 300, 500, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setVisible(true); 
		setResizable(false);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		 
		
		JLabel lblZorluklar = new JLabel("Zorluklar");
		lblZorluklar.setForeground(Color.WHITE);
		lblZorluklar.setBackground(Color.BLACK);
		lblZorluklar.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblZorluklar.setHorizontalAlignment(SwingConstants.CENTER);
		lblZorluklar.setBounds(55, 20, 150, 25);
		contentPane.add(lblZorluklar);
		
		JLabel lblOzel = new JLabel("Özel");
		lblOzel.setForeground(Color.WHITE);
		lblOzel.setHorizontalAlignment(SwingConstants.CENTER);
		lblOzel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblOzel.setBounds(270, 20, 150, 25);
		contentPane.add(lblOzel);
		
		
		
		JTextField text_Satir = new JTextField();
		text_Satir.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				
				char vchar = evt.getKeyChar();
				
				if(!(Character.isDigit(vchar)) 
						|| (vchar == KeyEvent.VK_BACK_SPACE)
						|| (vchar == KeyEvent.VK_DELETE)) {
					evt.consume();
				}
				
			}
		});
		text_Satir.setFont(new Font("Tahoma", Font.BOLD, 20));
		text_Satir.setHorizontalAlignment(SwingConstants.CENTER);
		text_Satir.setBounds(340, 142, 100, 30);
		contentPane.add(text_Satir);
		text_Satir.setColumns(10);
		
		
		
		JTextField text_Sutun = new JTextField();
		text_Sutun.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				
				char vchar = evt.getKeyChar();
				
				if(!(Character.isDigit(vchar)) 
						|| (vchar == KeyEvent.VK_BACK_SPACE)
						|| (vchar == KeyEvent.VK_DELETE)) {
					evt.consume();
				}
				
			}
		});
		text_Sutun.setFont(new Font("Tahoma", Font.BOLD, 20));
		text_Sutun.setHorizontalAlignment(SwingConstants.CENTER);
		text_Sutun.setColumns(10);
		text_Sutun.setBounds(340, 82, 100, 30);
		contentPane.add(text_Sutun);
		
		JTextField text_Mayin = new JTextField();
		text_Mayin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				
				char vchar = evt.getKeyChar();
				
				if(!(Character.isDigit(vchar)) 
						|| (vchar == KeyEvent.VK_BACK_SPACE)
						|| (vchar == KeyEvent.VK_DELETE)) {
					evt.consume();
				}
				
			}
		});
		text_Mayin.setFont(new Font("Tahoma", Font.BOLD, 20));
		text_Mayin.setHorizontalAlignment(SwingConstants.CENTER);
		text_Mayin.setColumns(10);
		text_Mayin.setBounds(340, 200, 100, 30);
		contentPane.add(text_Mayin);
		
		JButton btn_Basit = new JButton("Basit");
		btn_Basit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int satir = 9, sutun = 9, mayin = 10;
				
				text_Sutun.setText(Integer.toString(sutun));
				text_Satir.setText(Integer.toString(satir));
				text_Mayin.setText(Integer.toString(mayin));
			}
		});
		btn_Basit.setBackground(Color.GREEN);
		btn_Basit.setFont(new Font("Times New Roman", Font.BOLD, 19));
		btn_Basit.setBounds(55, 80, 150, 30);
		contentPane.add(btn_Basit);
		
		JButton btn_Orta = new JButton("Orta");
		btn_Orta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int satir = 16, sutun = 16, mayin = 40;
				
				text_Sutun.setText(Integer.toString(sutun));
				text_Satir.setText(Integer.toString(satir));
				text_Mayin.setText(Integer.toString(mayin));
				
			}
		});
		btn_Orta.setBackground(Color.YELLOW);
		btn_Orta.setFont(new Font("Times New Roman", Font.BOLD, 19));
		btn_Orta.setBounds(55, 140, 150, 30);
		contentPane.add(btn_Orta);
		
		JButton btn_Zor = new JButton("Zor");
		btn_Zor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int satir = 16, sutun = 30, mayin = 99;
				
				text_Sutun.setText(Integer.toString(sutun));
				text_Satir.setText(Integer.toString(satir));
				text_Mayin.setText(Integer.toString(mayin));
				
			}
		});
		btn_Zor.setBackground(Color.RED);
		btn_Zor.setFont(new Font("Times New Roman", Font.BOLD, 19));
		btn_Zor.setBounds(55, 200, 150, 30);
		contentPane.add(btn_Zor);
		
		
		JLabel lblSatir = new JLabel("Yükseklik :");
		lblSatir.setFont(new Font("Times New Roman", Font.BOLD, 19));
		lblSatir.setForeground(Color.WHITE);
		lblSatir.setBounds(240, 140, 100, 30);
		contentPane.add(lblSatir);
		
		JLabel lblSutun = new JLabel("Geniþlik :");
		lblSutun.setForeground(Color.WHITE);
		lblSutun.setFont(new Font("Times New Roman", Font.BOLD, 19));
		lblSutun.setBounds(240, 80, 100, 30);
		contentPane.add(lblSutun);
		
		JLabel lblMayin = new JLabel("Mayýn :");
		lblMayin.setForeground(Color.WHITE);
		lblMayin.setFont(new Font("Times New Roman", Font.BOLD, 19));
		lblMayin.setBounds(240, 200, 100, 30);
		contentPane.add(lblMayin);
		
		JButton btn_Refresh = new JButton("");
		btn_Refresh.setBackground(Color.BLACK);
		btn_Refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				text_Mayin.setText("");
				text_Satir.setText("");
				text_Sutun.setText("");
				
			}
		});
		btn_Refresh.setIcon(new ImageIcon(StartMenu.class.getResource("/com/sun/javafx/scene/control/skin/caspian/fxvk-backspace-button.png")));
		btn_Refresh.setBounds(402, 260, 40, 40);
		contentPane.add(btn_Refresh);
		
		JButton btn_Start = new JButton("Oyuna Baþla");
		btn_Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bos = "";
				
				if (bos.equals(text_Satir.getText()))
					yukseklik = 0;
				
				else if (bos.equals(text_Sutun.getText()))
					genislik = 0;
				
				else if (bos.equals(text_Mayin.getText()))
					mayinSayisi = 0;
				
				else {
					
					yukseklik = Integer.parseInt(text_Satir.getText());
					genislik = Integer.parseInt(text_Sutun.getText());
					mayinSayisi = Integer.parseInt(text_Mayin.getText());
				}
				
				dispose();
				//new GUI();
				//new GUI(genislik , yukseklik , mayinSayisi).setVisible(true);
				
			}
		});
		btn_Start.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_Start.setBounds(55, 260, 340, 40);
		contentPane.add(btn_Start);
	}
}