import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Giris extends JFrame {
	
	Image imgRefresh= new ImageIcon(this.getClass().getResource("/cRefresh.png")).getImage();
	
	Image imgBeyazGoz = new ImageIcon(this.getClass().getResource("/eyesBeyaz.png")).getImage();
	Image imgMaviGoz = new ImageIcon(this.getClass().getResource("/eyesMavi.png")).getImage();
	Image imgKirmiziGoz = new ImageIcon(this.getClass().getResource("/eyesKirmizi.png")).getImage();
	
	YetkiliGiris yetkiliGiris = new YetkiliGiris();
	Islemler islemler = new Islemler();
	Kayit kayit = new Kayit();
	DataBase db = new DataBase();

	private JPanel contentPane;
	private JTextField text_kullaniciAdi;
	private JPasswordField passwordField;
	private JTextField text_antiChaptcha;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Giris frame = new Giris();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Giris() {
		setTitle("Giriþ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(760, 240, 400, 600);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(0, 0, 51));
		
		text_kullaniciAdi = new JTextField();
		text_kullaniciAdi.setFont(new Font("Tahoma", Font.BOLD, 18));
		text_kullaniciAdi.setBounds(110, 160, 250, 40);
		contentPane.add(text_kullaniciAdi);
		text_kullaniciAdi.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Kullanýcý Adý");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setBounds(15, 160, 80, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("e-mail");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(SystemColor.text);
		lblNewLabel_1.setBounds(15, 185, 80, 20);
		contentPane.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 18));
		passwordField.setBounds(110, 230, 205, 40);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_2 = new JLabel("Þifre");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(SystemColor.text);
		lblNewLabel_2.setBounds(15, 230, 80, 40);
		contentPane.add(lblNewLabel_2);
		
		JLabel Chaptcha = new JLabel(Chaptcha_ureten());
		Chaptcha.setForeground(new Color(255, 140, 0));
		Chaptcha.setFont(new Font("Tahoma", Font.BOLD, 20));
		Chaptcha.setHorizontalAlignment(SwingConstants.CENTER);
		Chaptcha.setBounds(15, 80, 295, 40);
		contentPane.add(Chaptcha);
		
		text_antiChaptcha = new JTextField();
		text_antiChaptcha.setHorizontalAlignment(SwingConstants.CENTER);
		text_antiChaptcha.setFont(new Font("Tahoma", Font.BOLD, 18));
		text_antiChaptcha.setBounds(110, 300, 124, 40);
		contentPane.add(text_antiChaptcha);
		text_antiChaptcha.setColumns(10);
		
		JButton btn_refresh = new JButton("");
		btn_refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Chaptcha.setText(Chaptcha_ureten());
				text_antiChaptcha.setText("");
			}
		});
		btn_refresh.setIcon(new ImageIcon(imgRefresh));
		btn_refresh.setBounds(320, 80, 40, 40);
		contentPane.add(btn_refresh);
		
		JButton btn_giris = new JButton("Giriþ");
		btn_giris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane uyari = new JOptionPane();
				
				boolean chaptchaDogruMu = false , eslesiyorMU = false , ePostaMi = false , alanBosMu = true ;
				
				if (text_antiChaptcha.getText().equals(Chaptcha.getText()))
					chaptchaDogruMu = true;
				
				if (text_kullaniciAdi.getText().equals("") || text_antiChaptcha.getText().equals("") || passwordField.getText().equals(""))
					alanBosMu = true;
				
				else
					alanBosMu = false;
					
				
				String mySQL_sorgu = null;
				ResultSet myRS = null;
				
				if (text_kullaniciAdi.getText().contains("@")){
					
					mySQL_sorgu = "SELECT count(id) as kontrol FROM users WHERE ePosta = '" + text_kullaniciAdi.getText() + "' AND sifre = '" + passwordField.getText() + "'" ;
					ePostaMi = true;
				}
				
				else {
					
					mySQL_sorgu = "SELECT count(id) as kontrol FROM yetkili WHERE kullanici_adi = '" + text_kullaniciAdi.getText() + "' AND sifre = '" + passwordField.getText() + "'" ;
					ePostaMi = false;
				}
				
				myRS = db.veriAl(mySQL_sorgu);
				
				
				try {
					
					while (myRS.next()) {
						
						if (myRS.getInt("kontrol") == 1)
							eslesiyorMU = true;
						
						else
							eslesiyorMU = false;
					}
				} 
				
				catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				if (chaptchaDogruMu == true && eslesiyorMU == true && ePostaMi == true) {
					islemler.setVisible(true);
					dispose();
				}
				else if (chaptchaDogruMu == true && eslesiyorMU == true && ePostaMi == false) {
					yetkiliGiris.setVisible(true);
					dispose();
				}
				else if (chaptchaDogruMu == false && eslesiyorMU == true && alanBosMu == false) {
					uyari.showMessageDialog(null, "Güvenlik Kodunu Hatalý Girdiniz" , "Uyarý", uyari.ERROR_MESSAGE);
				}
				else if (chaptchaDogruMu == true && eslesiyorMU == false && ePostaMi == true && alanBosMu == false) {
					uyari.showMessageDialog(null, "Bilgilerinizi Hatalý Girdiniz ya da Kayýtlý Deðilsiniz" , "Uyarý", uyari.ERROR_MESSAGE);
				}
				else if (chaptchaDogruMu = true && eslesiyorMU == false && ePostaMi == false && alanBosMu == false) {
					uyari.showMessageDialog(null, "Bilgilerinizi Hatalý Girdiniz ya da Yetkili Statüsünde Deðilsiniz" , "Uyarý", uyari.ERROR_MESSAGE);
					uyari.showMessageDialog(null, "Kullanýcý adýný sadece yetkililer kullanabilir" , "Bilgilendirme", uyari.INFORMATION_MESSAGE);
				}
				else if (chaptchaDogruMu == false && eslesiyorMU == false && alanBosMu == false) {
					uyari.showMessageDialog(null, "Bilgilerinizi ve Güvenlik Kodunu Hatalý Girdiniz" , "Uyarý", uyari.ERROR_MESSAGE);
					uyari.showMessageDialog(null, "Üyeliðiniz yoksa kendinize bir üyelik oluþturunuz" , "Bilgilendirme", uyari.INFORMATION_MESSAGE);
				}
				else if (alanBosMu == true) {
					uyari.showMessageDialog(null, "Herhangi bir Alan Boþ Býrakýlamaz" , "Uyarý", uyari.ERROR_MESSAGE);
				}
				
				Chaptcha.setText(Chaptcha_ureten());
				
				text_kullaniciAdi.setText(null);
				text_antiChaptcha.setText(null);
				passwordField.setText(null);
			}
		});
		btn_giris.setForeground(SystemColor.text);
		btn_giris.setBackground(SystemColor.windowBorder);
		btn_giris.setFont(new Font("Tahoma", Font.BOLD, 18));
		btn_giris.setBounds(236, 300, 124, 40);
		contentPane.add(btn_giris);
		
		JLabel lblNewLabel_3 = new JLabel("Güvenlik kodu");
		lblNewLabel_3.setForeground(SystemColor.text);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(15, 300, 90, 40);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Kayýt olmak için ");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(150, 353, 97, 16);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("buraya");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblNewLabel_5.setForeground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_5.setForeground(Color.BLUE);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				lblNewLabel_5.setForeground(Color.YELLOW);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				lblNewLabel_5.setForeground(Color.RED);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				Kayit kayit = new Kayit();
				kayit.setVisible(true);
			}
		});
		lblNewLabel_5.setForeground(Color.BLUE);
		lblNewLabel_5.setBounds(250, 353, 48, 16);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("týklayýnýz");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setBounds(306, 353, 54, 16);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblSifreGoster = new JLabel("");
		lblSifreGoster.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblSifreGoster.setIcon(new ImageIcon(imgMaviGoz));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblSifreGoster.setIcon(new ImageIcon(imgBeyazGoz));
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				lblSifreGoster.setIcon(new ImageIcon(imgKirmiziGoz));
				passwordField.setEchoChar((char)0);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				lblSifreGoster.setIcon(new ImageIcon(imgMaviGoz));
				passwordField.setEchoChar('•');
			}
		});
		lblSifreGoster.setHorizontalAlignment(SwingConstants.CENTER);
		lblSifreGoster.setForeground(Color.WHITE);
		lblSifreGoster.setBounds(320, 230, 40, 40);
		lblSifreGoster.setIcon(new ImageIcon(imgBeyazGoz));
		contentPane.add(lblSifreGoster);
		
	}
	public static String Chaptcha_ureten () {
		
		Random rnd = new Random();
		
		String chaptcha = "";
		
		char kucuk_harfler_dizisi [] = new char [26];
		char buyuk_harfler_dizisi [] = new char [26];
		char rakamlar_dizisi [] = new char [10];
		
		for (int i=0 ; i<26 ;i++) {
			kucuk_harfler_dizisi[i] = (char)(97+i);
			buyuk_harfler_dizisi[i] = (char)(65+i);
		}
		for (int i=0 ; i<10 ; i++) {
			rakamlar_dizisi[i] = (char)(48+i) ;
		}
		
		int khs=0 , bhs=0 ,rs=0 ;
		
		boolean durum = true ;
		
		while (durum) {
			
			int no = rnd.nextInt(3);
			
			if (no==0 && khs<3) {
				chaptcha = chaptcha + kucuk_harfler_dizisi[rnd.nextInt(26)];
				khs = khs + 1 ;
				}
			
			else if (no==1 && bhs<3) {
				chaptcha = chaptcha + buyuk_harfler_dizisi[rnd.nextInt(26)];
				bhs = bhs + 1 ;
				}
			
			else if (no==2 && rs<3) {
				chaptcha = chaptcha + rakamlar_dizisi[rnd.nextInt(10)];
				rs = rs + 1 ;
				}
			
			if (khs==3 && bhs==3 && rs==3) {
				durum = false ;
			}
		}
		return chaptcha;
	}
}
