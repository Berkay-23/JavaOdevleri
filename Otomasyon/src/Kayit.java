import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextField;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class Kayit extends JFrame {

	private JPanel contentPane;
	private JTextField text_ad;
	private JTextField text_soyad;
	private JTextField text_ePosta;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textTC;
	private JTextField textTelefon;
	private JTextField antiChaptcha;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Kayit frame = new Kayit();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Kayit() {
		
		// icon ------------------------------------------------------------------------------------------
		
		Image imgBeyazGoz = new ImageIcon(this.getClass().getResource("/eyesBeyaz.png")).getImage();
		Image imgMaviGoz = new ImageIcon(this.getClass().getResource("/eyesMavi.png")).getImage();
		Image imgKirmiziGoz = new ImageIcon(this.getClass().getResource("/eyesKirmizi.png")).getImage();
		
		Image imgEslesti = new ImageIcon(this.getClass().getResource("/eTrue.png")).getImage();
		Image imgEslesmedi = new ImageIcon(this.getClass().getResource("/eFalse.png")).getImage();
		
		Image imgKotu = new ImageIcon(this.getClass().getResource("/gKirmizi.png")).getImage();
		Image imgOrta = new ImageIcon(this.getClass().getResource("/gTuruncu.png")).getImage();
		Image imgIyi= new ImageIcon(this.getClass().getResource("/gSari.png")).getImage();
		Image imgCokIyi= new ImageIcon(this.getClass().getResource("/gYesil.png")).getImage();
		
		Image imgKayitTuruncu= new ImageIcon(this.getClass().getResource("/kayitTuruncu.png")).getImage();
		Image imgKayitMor= new ImageIcon(this.getClass().getResource("/kayitMor.png")).getImage();
		Image imgKayitYesil= new ImageIcon(this.getClass().getResource("/kayitYesil.png")).getImage();
		
		Image imgRefresh= new ImageIcon(this.getClass().getResource("/cRefresh.png")).getImage();
		Image imgBack= new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		
		// Calendar ---------------------------------------------------------------------------------------
		
		String arrDays [] = new String [31];
		String arrYears [] = new String [150];
		String arrMoons [] = {"Ocak", "Þubat", "Mart", "Nisan", "Mayýs", "Haziran", "Temmuz", "Aðustos", "Eylül", "Ekim", "Kasým", "Aralýk"};
		
		for(int i=0 ; i<31 ; i++)
			arrDays[i] = Integer.toString(i+1);
		
		for(int i=0 ; i<150 ; i++)
			arrYears[i] = Integer.toString(2020-i);
		
		// -----------------------------------------------------------------------------------------------
		
		setTitle("Kayýt");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(760, 240, 400, 600);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(0, 0, 51));
		
		
		
		text_ad = new JTextField();
		text_ad.setFont(new Font("Tahoma", Font.BOLD, 15));
		text_ad.setBounds(50, 30, 125, 40);
		contentPane.add(text_ad);
		text_ad.setColumns(10);
		
		text_soyad = new JTextField();
		text_soyad.setFont(new Font("Tahoma", Font.BOLD, 15));
		text_soyad.setColumns(10);
		text_soyad.setBounds(245, 30, 125, 40);
		contentPane.add(text_soyad);
		
		JLabel lblNewLabel = new JLabel("Ad :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(12, 41, 28, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblSoyad = new JLabel("Soyad :");
		lblSoyad.setForeground(Color.WHITE);
		lblSoyad.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSoyad.setBounds(185, 41, 60, 16);
		contentPane.add(lblSoyad);
		
		text_ePosta = new JTextField();
		text_ePosta.setFont(new Font("Tahoma", Font.BOLD, 15));
		text_ePosta.setColumns(10);
		text_ePosta.setBounds(90, 93, 280, 40);
		contentPane.add(text_ePosta);
		
		JLabel lblEposta = new JLabel("e-posta : ");
		lblEposta.setForeground(Color.WHITE);
		lblEposta.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEposta.setBounds(12, 104, 69, 16);
		contentPane.add(lblEposta);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 15));
		passwordField.setBounds(90, 160, 240, 40);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		passwordField_1.setBounds(90, 230, 240, 40);
		contentPane.add(passwordField_1);
		
		JLabel lblifre = new JLabel("Þifre : ");
		lblifre.setForeground(Color.WHITE);
		lblifre.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblifre.setBounds(12, 170, 60, 16);
		contentPane.add(lblifre);
		
		JLabel lblifreTekrar = new JLabel("Þifre ");
		lblifreTekrar.setVerticalAlignment(SwingConstants.TOP);
		lblifreTekrar.setForeground(Color.WHITE);
		lblifreTekrar.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblifreTekrar.setBounds(12, 235, 52, 16);
		contentPane.add(lblifreTekrar);
		
		JLabel lblTekrar = new JLabel("tekrar");
		lblTekrar.setForeground(Color.WHITE);
		lblTekrar.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTekrar.setBounds(12, 255, 52, 16);
		contentPane.add(lblTekrar);
		
		JLabel lblifre_2_1 = new JLabel(".");
		lblifre_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblifre_2_1.setForeground(Color.WHITE);
		lblifre_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblifre_2_1.setBounds(63, 240, 15, 15);
		contentPane.add(lblifre_2_1);
		
		JLabel lblifre_2 = new JLabel(".");
		lblifre_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblifre_2.setForeground(Color.WHITE);
		lblifre_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblifre_2.setBounds(63, 245, 15, 15);
		contentPane.add(lblifre_2);
		
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
		lblSifreGoster.setBounds(330, 160, 40, 40);
		lblSifreGoster.setIcon(new ImageIcon(imgBeyazGoz));
		contentPane.add(lblSifreGoster);
		
		JLabel lblSifreGoster2 = new JLabel("");
		lblSifreGoster2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblSifreGoster2.setIcon(new ImageIcon(imgMaviGoz));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblSifreGoster2.setIcon(new ImageIcon(imgBeyazGoz));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				lblSifreGoster2.setIcon(new ImageIcon(imgKirmiziGoz));
				passwordField_1.setEchoChar((char)0);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				lblSifreGoster2.setIcon(new ImageIcon(imgMaviGoz));
				passwordField_1.setEchoChar('•');
			}
		});
		lblSifreGoster2.setHorizontalAlignment(SwingConstants.CENTER);
		lblSifreGoster2.setForeground(Color.WHITE);
		lblSifreGoster2.setBounds(330, 230, 40, 40);
		lblSifreGoster2.setIcon(new ImageIcon(imgBeyazGoz));
		contentPane.add(lblSifreGoster2);
		
		JLabel RGB_eslesme = new JLabel("");
		RGB_eslesme.setHorizontalAlignment(SwingConstants.CENTER);
		RGB_eslesme.setBounds(90, 275, 240, 20);
		contentPane.add(RGB_eslesme);
		
		JLabel RGB_Guvenlik = new JLabel("");
		RGB_Guvenlik.setHorizontalAlignment(SwingConstants.CENTER);
		RGB_Guvenlik.setBounds(90, 205, 240, 20);
		contentPane.add(RGB_Guvenlik);
		
		JComboBox gun = new JComboBox(arrDays);
		gun.setSelectedIndex(0);
		gun.setFont(new Font("Tahoma", Font.BOLD, 14));
		gun.setBounds(90, 302, 45, 25);
		contentPane.add(gun);
		
		JComboBox ay = new JComboBox(arrMoons);
		ay.setFont(new Font("Tahoma", Font.BOLD, 14));
		ay.setBounds(143, 303, 100, 25);
		contentPane.add(ay);
		
		JComboBox yil = new JComboBox(arrYears);
		yil.setSelectedIndex(0);
		yil.setFont(new Font("Tahoma", Font.BOLD, 14));
		yil.setBounds(250, 302, 80, 25);
		contentPane.add(yil);
		
		JLabel lblDoumT = new JLabel("Do\u011Fum T :");
		lblDoumT.setForeground(Color.WHITE);
		lblDoumT.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDoumT.setBounds(12, 302, 80, 25);
		contentPane.add(lblDoumT);
		
		textTC = new JTextField();
		textTC.setFont(new Font("Tahoma", Font.BOLD, 15));
		textTC.addKeyListener(new KeyAdapter() {
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
		textTC.setBounds(90, 362, 240, 30);
		contentPane.add(textTC);
		textTC.setColumns(10);
		
		JLabel lblTcNo = new JLabel("TC no : ");
		lblTcNo.setForeground(Color.WHITE);
		lblTcNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTcNo.setBounds(12, 369, 60, 16);
		contentPane.add(lblTcNo);
		
		textTelefon = new JTextField();
		textTelefon.setFont(new Font("Tahoma", Font.BOLD, 15));
		textTelefon.addKeyListener(new KeyAdapter() {
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
		textTelefon.setColumns(10);
		textTelefon.setBounds(90, 415, 240, 30);
		contentPane.add(textTelefon);
		
		JLabel lblTelefonNo = new JLabel("Telefon : ");
		lblTelefonNo.setForeground(Color.WHITE);
		lblTelefonNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTelefonNo.setBounds(12, 422, 69, 16);
		contentPane.add(lblTelefonNo);
		
		JButton btnRefresh = new JButton("");
		btnRefresh.setBackground(Color.WHITE);
		btnRefresh.setIcon(new ImageIcon(imgRefresh));
		btnRefresh.setBounds(340, 468, 30, 30);
		contentPane.add(btnRefresh);
		
		antiChaptcha = new JTextField();
		antiChaptcha.setFont(new Font("Tahoma", Font.BOLD, 18));
		antiChaptcha.setBounds(170, 468, 150, 30);
		contentPane.add(antiChaptcha);
		antiChaptcha.setColumns(10);
		
		JLabel Chaptcha = new JLabel(Chaptcha_ureten());
		Chaptcha.setHorizontalAlignment(SwingConstants.CENTER);
		Chaptcha.setFont(new Font("Tahoma", Font.BOLD, 18));
		Chaptcha.setForeground(Color.ORANGE);
		Chaptcha.setBounds(12, 468, 150, 30);
		contentPane.add(Chaptcha);
		
		JLabel kayýtOl = new JLabel("");
		kayýtOl.setIcon(new ImageIcon(imgKayitTuruncu));
		kayýtOl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				kayýtOl.setIcon(new ImageIcon(imgKayitMor));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				kayýtOl.setIcon(new ImageIcon(imgKayitTuruncu));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				kayýtOl.setIcon(new ImageIcon(imgKayitYesil));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				kayýtOl.setIcon(new ImageIcon(imgKayitMor));
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				DataBase db = new DataBase();
				
				// ad , soyad , ePosta , sifre , sifreTekrar , dogumTarihi , tc , telNo , chaptcha , antiChaptcha;
				
				JOptionPane uyari = new JOptionPane();
				
				String [] veriler = new String [10];
				
				boolean gecerlilik = true;
				
				int kayitNo = 0;
				
				veriler [0] = text_ad.getText();
				veriler [1] = text_soyad.getText();
				veriler [2] = text_ePosta.getText();
				veriler [3] = passwordField.getText();
				veriler [4] = passwordField_1.getText();
				veriler [5] = arrDays[gun.getSelectedIndex()] + " " + arrMoons[ay.getSelectedIndex()] + " " + arrYears[yil.getSelectedIndex()];
				veriler [6] = textTC.getText();
				veriler [7] = textTelefon.getText();
				veriler [8] = Chaptcha.getText();
				veriler [9] = Kayit.this.antiChaptcha.getText();
				
				boolean AlanlarBosMu = false;
				
				for (int i=0 ; i<veriler.length ; i++) {
					
					String kontrol;
					boolean boslukVarMi = false;
					
					if (i == 0 || i == 1 || i == 5) {
						kontrol = veriler[i].trim();
					}
					else
						kontrol = veriler[i].replaceAll(" ", "");
					
					if (kontrol.equals("")) {
						AlanlarBosMu = true;
						gecerlilik = false;
					}
					
					else {
						for (int j=0 ; j<veriler[i].length() ; j++) {
							
							if (veriler[i].charAt(j) == ' ')
								boslukVarMi = true;	
						}
												
						// bosluk kontrolü --------------------------------------------
						
						if (boslukVarMi == true) {
							
							if (i==2) {
								// hata1
								uyari.showMessageDialog(null, "e-posta Boþluk karakteri içeremez" , "Uyarý", uyari.ERROR_MESSAGE);
								gecerlilik = false;
							}
								
							else if (i==3) {
								// hata2
								uyari.showMessageDialog(null, "Þifre boþluk karakteri içeremez" , "Uyarý", uyari.ERROR_MESSAGE);
								gecerlilik = false;
							}
						}
						
						// e-posta'nýn geçerlilik kontrolleri ------------------------
						
						if (i==2) {
							
							if (veriler[i].contains("@") == false) {
								// hata3
								uyari.showMessageDialog(null, "e-posta Adresiniz geçerli deðil" , "Uyarý", uyari.ERROR_MESSAGE);
								gecerlilik = false;
							}
						}
						
						// þifre eþleþmesi ------------------------------------------
						
						if (i==4) {
							if (veriler[3].equals(veriler[i]) == false) {
								// hata4
								uyari.showMessageDialog(null, "Þifreler eþleþmiyor" , "Uyarý", uyari.ERROR_MESSAGE);
								gecerlilik = false;
							}
						}
						
						// TC kontrolü -----------------------------------------------
						
						if (i==6) {
							
							if (veriler[i].length() != "00000000000".length()) {
								// hata5
								uyari.showMessageDialog(null, "TC kimlik numaranýzý eksik ya da yanlýþ yazdýnýz" , "Uyarý", uyari.ERROR_MESSAGE);
								gecerlilik = false;
							}
							
						}
						
						// Telefon no kontrolü ---------------------------------------
						
						if (i==7) {
							
							if (veriler[i].length() == "05450000000".length() || veriler[i].length() == "5450000000".length()) {
								
							}
							
							else {
								// hata6
								uyari.showMessageDialog(null, "Telefon numaranýzý eksik ya da yanlýþ yazdýnýz" , "Uyarý", uyari.ERROR_MESSAGE);
								gecerlilik = false;
							}
						}
						
						// chaptcha eþleþmesi ----------------------------------------
						
						if (i==9) {
							
							if (veriler[8].equals(veriler[i]) == false){
								// hata7
								uyari.showMessageDialog(null, "Güvenlik kodlarý eþleþmiyor" , "Uyarý", uyari.ERROR_MESSAGE);
								gecerlilik = false;
							}
						}
					}
					
					// Verilerin uzunluk kontrolü ------------------------------------
					
					if (veriler[i].length() > 45) { 
						//hata8
						uyari.showMessageDialog(null, "Alanlardan en az biri çok fazla karakter içeriyor" , "Uyarý", uyari.ERROR_MESSAGE);
						gecerlilik = false;
					}
				}
				
				// Alanlarýn dolu/boþluk kontrolü ------------------------------
				
				if (AlanlarBosMu == true) {
					// hata0
					uyari.showMessageDialog(null, "Alan ya da alanlarý boþ býraktýn" , "Uyarý", uyari.ERROR_MESSAGE);
					gecerlilik = false;
				}
				
				// e-posta unique kontrolü -------------------------------------
				
				if (gecerlilik == true) {
					
					String mySQL_sorgu = "SELECT count('id') as kontrol FROM users WHERE ePosta = '" + veriler[2] + "'";
					
					ResultSet ePosta = db.veriAl(mySQL_sorgu);
					
					try {
						
						while(ePosta.next()) {
							
							if (ePosta.getInt("kontrol") == 1) {
								
								uyari.showMessageDialog(null, "Bu e-posta adresi sistemde kayýtlý" , "Uyarý", uyari.ERROR_MESSAGE);
								
								gecerlilik = false;
							}
						}
					}
					catch (SQLException e) {
						e.printStackTrace();
					}
				}
				// -------------------------------------------------------------
				
				if (gecerlilik == true) {
					
					// id belirleme ----------------------------------------------------------
					
					String IDsorgu = "SELECT id FROM users WHERE id LIKE '%" + Character.toString(veriler[0].charAt(0)) +  Character.toString(veriler[1].charAt(0)) + "_%'" ;
					
					 ResultSet myRS = DataBase.veriAl(IDsorgu);
					 
					 String gelenID = null , id = null;
					 
					try {
						
						while (myRS.next()) {
							gelenID = myRS.getString("id");
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					 
					if(gelenID == null) {
						id = Character.toString(veriler[0].charAt(0)) +  Character.toString(veriler[1].charAt(0)) + "_" + 0;
					}
					
					else {
						
						int index = Integer.parseInt(gelenID.substring(gelenID.indexOf('_') + 1)) + 1 ;
						
						id = Character.toString(veriler[0].charAt(0)) + Character.toString(veriler[1].charAt(0)) + "_" + index;
					}
					
					// Data Base kayýt iþlemi ------------------------------------------------
					
					String mySQL_sorgu = "INSERT INTO users (id,isim,soyisim,ePosta,sifre,dogumTarihi,tc,telefon) VALUES "
							+ "("
							+ "'" + id + "'" + ","
							+ "'" + veriler[0].trim() + "'," 
							+ "'" + veriler[1].trim() + "'," 
							+ "'" + veriler[2] + "'," 
							+ "'" + veriler[3] + "'," 
							+ "'" + veriler[5] + "'," 
							+ "'" + veriler[6] + "'," 
							+ "'" + veriler[7] + "'" 
							+ ")"
							;
					
					db.ekle_guncelle_sil(mySQL_sorgu);
					
					uyari.showMessageDialog(null, "Kaydýnýz tamamlandý" , "Bilgilendirme" , uyari.INFORMATION_MESSAGE);
					
					Giris giris = new Giris();
					giris.setVisible(true);
					dispose();
				}
				else 
					uyari.showMessageDialog(null, "Kaydýnýz tamamlanamadý bilgilerinizi kontrol edip tekrar deneyiniz" , "Bilgilendirme" , uyari.INFORMATION_MESSAGE);
				Chaptcha.setText(Chaptcha_ureten());
			} 
		});
		kayýtOl.setFont(new Font("Tahoma", Font.BOLD, 18));
		kayýtOl.setHorizontalAlignment(SwingConstants.CENTER);
		kayýtOl.setBounds(119, 512, 161, 40);
		contentPane.add(kayýtOl);
		
		JButton btnBack = new JButton("");
		btnBack.setIcon(new ImageIcon(imgBack));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Giris giris = new Giris();
				giris.setVisible(true);
				dispose();
				
			}
		});
		btnBack.setBackground(Color.WHITE);
		btnBack.setBounds(22, 512, 40, 40);
		contentPane.add(btnBack);
		
		
		// Þifre Kontrol Ýþlemi ----------------------------------------------------------
		
		Timer timer = new Timer();
		
		TimerTask kontrol = new TimerTask() {
			
			@Override
			public void run() {
				
				// Esleþme kontrolü -----------------------------------------------------------
				
				boolean eslesme = passwordField.getText().equals(passwordField_1.getText());
				
				if (passwordField.getText().length() == 0 && passwordField_1.getText().length() == 0){
					RGB_eslesme.setIcon(null);
				}
				
				else if (eslesme == true) {
					RGB_eslesme.setIcon(new ImageIcon(imgEslesti));
				}
				
				else if (eslesme == false){
					RGB_eslesme.setIcon(new ImageIcon(imgEslesmedi));
				}
				
				// Guvenlik Seviyesi ----------------------------------------------------------
				
				int zorluk = GuvenlikSeviyesi(passwordField.getText());
				
				if(zorluk == 0)
					RGB_Guvenlik.setIcon(null);
				
				else if(zorluk == 1)
					RGB_Guvenlik.setIcon(new ImageIcon(imgKotu));
				
				else if(zorluk == 2)
				RGB_Guvenlik.setIcon(new ImageIcon(imgOrta));
				
				else if(zorluk == 3)
					RGB_Guvenlik.setIcon(new ImageIcon(imgIyi));
				
				else if(zorluk == 4)
					RGB_Guvenlik.setIcon(new ImageIcon(imgCokIyi));
			}
		};
		timer.schedule(kontrol , 0 , 500);
		
		// ------------------------------------------------------------------------------- 
		
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			Chaptcha.setText(Chaptcha_ureten());
			}
		});
		
	}
	
	public static boolean BuyukHarf (String s1) {
		
		boolean varMi = false;
		
		char istisnalar[] =  {'Ð','Ü','Þ','Ý','Ö','Ç'};
		
		for (int ASCII = 65 ; ASCII < 96 ; ASCII ++) {
			
			for (int i=0 ; i<s1.length() ; i++) {
				
				if ((char)ASCII == s1.charAt(i))
					varMi = true;
			}
		}
		
		for (int i=0 ; i<istisnalar.length ; i++) {
			
			for (int j=0 ; j<s1.length() ; j++) {
				
				if (istisnalar[i] == s1.charAt(j))
					varMi = true;
			}
		}
		return varMi;
	}
	
	public static boolean KucukHarf (String s1) {
		
		boolean varMi = false;
		
		char istisnalar[] =  {'ý','ð','ü','þ','ö','ç'};
		
		for (int ASCII = 97 ; ASCII < 123 ; ASCII ++) {
			
			for (int i=0 ; i<s1.length() ; i++) {
				
				if ((char)ASCII == s1.charAt(i))
					varMi = true;
			}
		}
		
		for (int i=0 ; i<istisnalar.length ; i++) {
			
			for (int j=0 ; j<s1.length() ; j++) {
				
				if (istisnalar[i] == s1.charAt(j))
					varMi = true;
			}
		}
		return varMi;
	}
	
	public static boolean Rakam (String s1) {
		
		boolean varMi = false;
		
		for (int ASCII = 48 ; ASCII < 58 ; ASCII ++) {
			
			for (int i=0 ; i<s1.length() ; i++) {
				
				if ((char)ASCII == s1.charAt(i))
					varMi = true;
				
			}
		}
		return varMi;
	}
	
	public static boolean OzelKarekter (String s1) {
		
		boolean varMi = false;
		
		char karakterler [] = {'!','#','%','&','/','(',')','{','}','[',']','*','+','-','?','_','>','<','|',',',';','.',':','='};
		
		for (int i=0 ; i<karakterler.length ; i++) {
			
			for (int j=0 ; j<s1.length() ; j++) {
				
				if (karakterler[i] == s1.charAt(j))
					varMi = true;
			}
		}
		return varMi;
	}
	public static int GuvenlikSeviyesi (String s1) {
		
		int GuvenlikSeviyesi = 0 ;
		
		if (BuyukHarf(s1) == true) {
			GuvenlikSeviyesi = GuvenlikSeviyesi + 1;
		}
		if (KucukHarf(s1) == true) {
			GuvenlikSeviyesi = GuvenlikSeviyesi + 1;
		}
		if (Rakam(s1) == true) {
			GuvenlikSeviyesi = GuvenlikSeviyesi + 1;
		}
		if (OzelKarekter(s1) == true) {
			GuvenlikSeviyesi = GuvenlikSeviyesi + 1;
		}
		return GuvenlikSeviyesi;
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