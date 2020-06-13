import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Islemler extends JFrame {
	
	public int garantiClick = 0;
	public int ziraatClick = 0;
	public int vakifClick = 0;

	private JPanel contentPane;
	private JTable table;
	private JTextField textKrediMiktari;
	private JTextField textAnaPara;
	private JTextField textVade;
	
	Base_banka [] base_banka = new  Base_banka [] {new GarantiBankasi() , new VakifBank() , new ZiraatBankasi()};

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Islemler frame = new Islemler();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Islemler() {
		
		// images ------------------------------------------------------------------------------------
		
		
		Image imgGaranti = new ImageIcon(this.getClass().getResource("/garanti.png")).getImage();
		Image imgZiraat = new ImageIcon(this.getClass().getResource("/ziraat.png")).getImage();
		Image imgVakif = new ImageIcon(this.getClass().getResource("/vakif.png")).getImage();
		
		Image imgmGaranti = new ImageIcon(this.getClass().getResource("/mGaranti.png")).getImage();
		Image imgmZiraat = new ImageIcon(this.getClass().getResource("/mZiraat.png")).getImage();
		Image imgmVakif = new ImageIcon(this.getClass().getResource("/mVakif.png")).getImage();
		
		Image imgpGaranti = new ImageIcon(this.getClass().getResource("/pGaranti.png")).getImage();
		Image imgpZiraat = new ImageIcon(this.getClass().getResource("/pZiraat.png")).getImage();
		Image imgpVakif = new ImageIcon(this.getClass().getResource("/pVakif.png")).getImage();
		
		Image mQuit = new ImageIcon(this.getClass().getResource("/mQuit.png")).getImage();
		Image yQuit = new ImageIcon(this.getClass().getResource("/yQuit.png")).getImage();
		Image sQuit = new ImageIcon(this.getClass().getResource("/sQuit.png")).getImage();
		
		
		// -------------------------------------------------------------------------------------------
		DefaultTableModel modelim = new DefaultTableModel();
		DefaultTableCellRenderer duzenle = new DefaultTableCellRenderer();
		
		Object [] sutunlar = {"Taksit no", "Borç Taksiti", "Faiz Taksiti", "Toplam Taksit", "Kalan Borcu", "Toplam Faiz", "Kredi Tutarý"}; 
		
		Object [] satirlar = {null};
		
		setTitle("Bankacýlýk Ýþlemleri");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(550, 200, 800, 600);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(new Color(0, 0, 51));
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 289, 770, 263);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setEnabled(false);
		table.setForeground(Color.WHITE);
		table.setBackground(Color.DARK_GRAY);
		duzenle.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(String.class, duzenle);
		table.setFont(new Font("Tahoma", Font.BOLD, 13));
		modelim.setColumnIdentifiers(sutunlar);
		modelim.addRow(satirlar);
		table.setModel(modelim);
		table.setBounds(618, 491, 457, 224);
		scrollPane.setViewportView(table);
		
		JLabel lblKrediIslemleri = new JLabel("Kredi Ýþlemleri");
		lblKrediIslemleri.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblKrediIslemleri.setForeground(Color.WHITE);
		lblKrediIslemleri.setHorizontalAlignment(SwingConstants.CENTER);
		lblKrediIslemleri.setBounds(12, 12, 278, 30);
		contentPane.add(lblKrediIslemleri);
		
		textKrediMiktari = new JTextField();
		textKrediMiktari.setFont(new Font("Tahoma", Font.BOLD, 17));
		textKrediMiktari.addKeyListener(new KeyAdapter() {
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
		textKrediMiktari.setBounds(140, 80, 150, 30);
		contentPane.add(textKrediMiktari);
		textKrediMiktari.setColumns(10);
		
		String [] taksitler = {"3","5","6","9","10","12","18","24","32","36","48","60"};
		
		JComboBox comboTaksit = new JComboBox(taksitler);
		comboTaksit.setSelectedIndex(-1);
		comboTaksit.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboTaksit.setBounds(140, 125, 50, 30);
		contentPane.add(comboTaksit);
		
		JLabel lblKrediMitar = new JLabel("Kredi Mitarý (TL) :");
		lblKrediMitar.setForeground(Color.WHITE);
		lblKrediMitar.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblKrediMitar.setBounds(12, 79, 127, 30);
		contentPane.add(lblKrediMitar);
		
		JLabel lblTaksitSays = new JLabel("Taksit Sayýsý (ay) :");
		lblTaksitSays.setForeground(Color.WHITE);
		lblTaksitSays.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTaksitSays.setBounds(12, 125, 127, 30);
		contentPane.add(lblTaksitSays);
		
		JButton btn_krediHesapla = new JButton("Hesapla");
		btn_krediHesapla.setBounds(202, 125, 88, 30);
		contentPane.add(btn_krediHesapla);
		
		textAnaPara = new JTextField();
		textAnaPara.setFont(new Font("Tahoma", Font.BOLD, 17));
		textAnaPara.addKeyListener(new KeyAdapter() {
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
		textAnaPara.setColumns(10);
		textAnaPara.setBounds(632, 80, 150, 30);
		contentPane.add(textAnaPara);
		
		JButton btn_MevduatHesapla = new JButton("Hesapla");
		btn_MevduatHesapla.setBounds(694, 126, 88, 30);
		contentPane.add(btn_MevduatHesapla);
		
		JLabel lblMevduatIslemleri = new JLabel("Mevduat Ýþlemleri");
		lblMevduatIslemleri.setHorizontalAlignment(SwingConstants.CENTER);
		lblMevduatIslemleri.setForeground(Color.WHITE);
		lblMevduatIslemleri.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblMevduatIslemleri.setBounds(504, 12, 278, 30);
		contentPane.add(lblMevduatIslemleri);
		
		JLabel lblAnaPara = new JLabel("Ana Para (TL) :");
		lblAnaPara.setForeground(Color.WHITE);
		lblAnaPara.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAnaPara.setBounds(510, 80, 110, 30);
		contentPane.add(lblAnaPara);
		
		JLabel lblVade = new JLabel("Vade (gün) :");
		lblVade.setForeground(Color.WHITE);
		lblVade.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVade.setBounds(510, 126, 96, 30);
		contentPane.add(lblVade);
		
		textVade = new JTextField();
		textVade.setFont(new Font("Tahoma", Font.BOLD, 14));
		textVade.addKeyListener(new KeyAdapter() {
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
		textVade.setColumns(10);
		textVade.setBounds(632, 126, 50, 30);
		contentPane.add(textVade);
		
		JLabel logoZiraat = new JLabel("");
		logoZiraat.setIcon(new ImageIcon(imgZiraat));
		logoZiraat.setHorizontalAlignment(SwingConstants.CENTER);
		logoZiraat.setBounds(340, 175, 105, 105);
		contentPane.add(logoZiraat);
		
		JLabel logoGaranti = new JLabel("");
		logoGaranti.setIcon(new ImageIcon(imgGaranti));
		logoGaranti.setHorizontalAlignment(SwingConstants.CENTER);
		logoGaranti.setBounds(189, 175, 105, 105);
		contentPane.add(logoGaranti);
		
		JLabel logoVakif = new JLabel("");
		logoVakif.setIcon(new ImageIcon(imgVakif));
		logoVakif.setHorizontalAlignment(SwingConstants.CENTER);
		logoVakif.setBounds(492, 175, 105, 105);
		contentPane.add(logoVakif);
		
		JLabel lblkazancbaslik = new JLabel("Net Kazancýnýz (TL)");
		lblkazancbaslik.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblkazancbaslik.setForeground(Color.WHITE);
		lblkazancbaslik.setHorizontalAlignment(SwingConstants.CENTER);
		lblkazancbaslik.setBounds(632, 175, 150, 16);
		contentPane.add(lblkazancbaslik);
		
		JLabel netKazanc = new JLabel("");
		netKazanc.setForeground(Color.WHITE);
		netKazanc.setFont(new Font("Tahoma", Font.BOLD, 15));
		netKazanc.setHorizontalAlignment(SwingConstants.CENTER);
		netKazanc.setBounds(620, 204, 150, 45);
		contentPane.add(netKazanc);
		
		JLabel uyari = new JLabel("");
		uyari.setForeground(Color.RED);
		uyari.setFont(new Font("Tahoma", Font.BOLD, 15));
		uyari.setHorizontalAlignment(SwingConstants.CENTER);
		uyari.setBounds(302, 80, 196, 30);
		contentPane.add(uyari);
		
		JLabel lblQuit = new JLabel("");
		lblQuit.setIcon(new ImageIcon(mQuit));
		lblQuit.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				Giris giris = new Giris();
				giris.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblQuit.setIcon(new ImageIcon(yQuit));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblQuit.setIcon(new ImageIcon(mQuit));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				lblQuit.setIcon(new ImageIcon(sQuit));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				lblQuit.setIcon(new ImageIcon(yQuit));
			}
		});
		lblQuit.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuit.setBounds(365, 17, 50, 50);
		contentPane.add(lblQuit);
		
		// seçilim iþlemleri ------------------------------------------------------------------------
		
		logoGaranti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				uyari.setText(null);
				
				garantiClick = garantiClick + 1;
				ziraatClick = 0;
				vakifClick = 0;
				
				modelim.setRowCount(0);
				
				boolean text_bosMu = textKrediMiktari.getText().equals("") , combo_bosMu = false;
				boolean textBosMu1 = textAnaPara.getText().equals("") , textBosMu2 = textVade.getText().equals("");
				
				int krediMiktari = 0 , taksitSayisi = 1 , anaPara = 0 , vade = 1;
				
				// --------------------------------------------------------------------------------------------------------------------------
				
				if (textBosMu1 == false && textBosMu2 == false && (garantiClick % 2 == 1 || ziraatClick % 2 == 1 || vakifClick % 2 == 1)) {
					
					anaPara = Integer.parseInt(textAnaPara.getText());
					vade = Integer.parseInt(textVade.getText());
				}
				else if (textBosMu1 == false && textBosMu2 == false)
					uyari.setText("Banka Seçiniz");
				
				TextDoldur(textBosMu1, textBosMu2, anaPara, vade, netKazanc);
				
				// --------------------------------------------------------------------------------------------------------------------------
				
				if (comboTaksit.getSelectedIndex() == -1)
					combo_bosMu = true;
				
				if (text_bosMu == false && combo_bosMu == false) {
					krediMiktari = Integer.parseInt(textKrediMiktari.getText());
					taksitSayisi = Integer.parseInt(taksitler[comboTaksit.getSelectedIndex()]);
				}
				
				TabloDoldur(text_bosMu, combo_bosMu, taksitSayisi, krediMiktari, modelim, duzenle);
				
				// -------------------------------------------------------------------------------------------------------------------------
				
				
				if (garantiClick % 2 == 1) {
					logoGaranti.setIcon(new ImageIcon(imgmGaranti));
					logoZiraat.setIcon(new ImageIcon(imgZiraat));
					logoVakif.setIcon(new ImageIcon(imgVakif));
				}
				else
					logoGaranti.setIcon(new ImageIcon(imgGaranti));
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				logoGaranti.setIcon(new ImageIcon(imgpGaranti));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				if (garantiClick == 1)
					logoGaranti.setIcon(new ImageIcon(imgmGaranti));
				
				else
					logoGaranti.setIcon(new ImageIcon(imgGaranti));
				
			}
		});
		
		logoZiraat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				uyari.setText(null);
				
				ziraatClick = ziraatClick + 1;
				garantiClick = 0;
				vakifClick = 0;
				
				modelim.setRowCount(0);
				
				boolean text_bosMu = textKrediMiktari.getText().equals("") , combo_bosMu = false;
				
				boolean textBosMu1 = textAnaPara.getText().equals("") , textBosMu2 = textVade.getText().equals("");
				
				int krediMiktari = 0 , taksitSayisi = 1 , anaPara = 0 , vade = 1;
				
				// --------------------------------------------------------------------------------------------------------------------------
				
				if (textBosMu1 == false && textBosMu2 == false && (garantiClick % 2 == 1 || ziraatClick % 2 == 1 || vakifClick % 2 == 1)) {
					
					anaPara = Integer.parseInt(textAnaPara.getText());
					vade = Integer.parseInt(textVade.getText());
				}
				else if (textBosMu1 == false && textBosMu2 == false)
					uyari.setText("Banka Seçiniz");
				
				TextDoldur(textBosMu1, textBosMu2, anaPara, vade, netKazanc);
				
				// --------------------------------------------------------------------------------------------------------------------------
				
				if (comboTaksit.getSelectedIndex() == -1)
					combo_bosMu = true;
				
				if (text_bosMu == false && combo_bosMu == false) {
					krediMiktari = Integer.parseInt(textKrediMiktari.getText());
					taksitSayisi = Integer.parseInt(taksitler[comboTaksit.getSelectedIndex()]);
				}
				
				TabloDoldur(text_bosMu, combo_bosMu, taksitSayisi, krediMiktari, modelim, duzenle);
				
				if(ziraatClick % 2 == 1) {
					logoZiraat.setIcon(new ImageIcon(imgmZiraat));
					logoGaranti.setIcon(new ImageIcon(imgGaranti));
					logoVakif.setIcon(new ImageIcon(imgVakif));
				}
				else
					logoZiraat.setIcon(new ImageIcon(imgZiraat));
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				logoZiraat.setIcon(new ImageIcon(imgpZiraat));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				if (ziraatClick == 1)
					logoZiraat.setIcon(new ImageIcon(imgmZiraat));
				
				else
					logoZiraat.setIcon(new ImageIcon(imgZiraat));
			}
		});
		
		logoVakif.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				uyari.setText(null);
				
				vakifClick = vakifClick + 1;
				garantiClick = 0;
				ziraatClick = 0;
				
				modelim.setRowCount(0);
				
				boolean text_bosMu = textKrediMiktari.getText().equals("") , combo_bosMu = false;
				
				boolean textBosMu1 = textAnaPara.getText().equals("") , textBosMu2 = textVade.getText().equals("");
				
				int krediMiktari = 0 , taksitSayisi = 1 , anaPara = 0 , vade = 1;
				
				// --------------------------------------------------------------------------------------------------------------------------
				
				if (textBosMu1 == false && textBosMu2 == false && (garantiClick % 2 == 1 || ziraatClick % 2 == 1 || vakifClick % 2 == 1)) {
					
					anaPara = Integer.parseInt(textAnaPara.getText());
					vade = Integer.parseInt(textVade.getText());
				}
				else if (textBosMu1 == false && textBosMu2 == false)
					uyari.setText("Banka Seçiniz");
				
				TextDoldur(textBosMu1, textBosMu2, anaPara, vade, netKazanc);
				
				// --------------------------------------------------------------------------------------------------------------------------
				
				if (comboTaksit.getSelectedIndex() == -1)
					combo_bosMu = true;
				
				if (text_bosMu == false && combo_bosMu == false) {
					krediMiktari = Integer.parseInt(textKrediMiktari.getText());
					taksitSayisi = Integer.parseInt(taksitler[comboTaksit.getSelectedIndex()]);
				}
				
				TabloDoldur(text_bosMu, combo_bosMu, taksitSayisi, krediMiktari, modelim, duzenle);
				
				if(vakifClick % 2 == 1) {
					logoVakif.setIcon(new ImageIcon(imgmVakif));
					logoGaranti.setIcon(new ImageIcon(imgGaranti));
					logoZiraat.setIcon(new ImageIcon(imgZiraat));
				}
				else
					logoVakif.setIcon(new ImageIcon(imgVakif));
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				logoVakif.setIcon(new ImageIcon(imgpVakif));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				if (vakifClick == 1)
					logoVakif.setIcon(new ImageIcon(imgmVakif));
				
				else
					logoVakif.setIcon(new ImageIcon(imgVakif));
				
				
			}
		});
		
		// ----------------------------------------------------------------------------------------------
		
		btn_krediHesapla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				uyari.setText(null);
				
				modelim.setRowCount(0);
				
				int krediMiktari = 0;
				int taksitSayisi = 1;
				
				String [] gecici = new String [7]; 
				
				// box'larýn doluluk kontrolü --------------------------------------------------------------------
				
				boolean text_bosMu = textKrediMiktari.getText().equals("") , combo_bosMu;
				
				if (comboTaksit.getSelectedIndex() == -1)
					combo_bosMu = true;
				
				else
					combo_bosMu = false;
				
				// box'lardan veri alýmý -----------------------------------------------------------------------------------
				
				if (text_bosMu == false && combo_bosMu == false && (garantiClick == 1 || ziraatClick == 1 || vakifClick == 1)) {
					
					krediMiktari = Integer.parseInt(textKrediMiktari.getText());
					
					taksitSayisi = Integer.parseInt(taksitler[comboTaksit.getSelectedIndex()]);
				}
				
				else if (text_bosMu == false && combo_bosMu == false)
					uyari.setText("Banka Seçiniz");
					
				// -------------------------------------------------------------------------------------------------------------
				
				TabloDoldur(text_bosMu, combo_bosMu, taksitSayisi, krediMiktari, modelim, duzenle);
				
				if (text_bosMu == true || combo_bosMu == true)
					uyari.setText("Alanlarý doldurunuz");
			}
		});
		
		// ________________________________________________________________________________________________________________________________________________________________________________________
		
		btn_MevduatHesapla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				uyari.setText(null);
				
				int anaPara = 0;
				int vade = 1;
				
				// box'larýn doluluk kontrolü ----------------------------------------------------------------------
				
				boolean text_bosMu1 = textAnaPara.getText().equals("");
				boolean text_bosMu2 = textVade.getText().equals("");
				
				
				// box'lardan veri alýmý ----------------------------------------------------------------------------
				
				if (text_bosMu1 == false && text_bosMu2 == false && (garantiClick == 1 || ziraatClick == 1 || vakifClick == 1)) {
					
					anaPara = Integer.parseInt(textAnaPara.getText());
					
					vade = Integer.parseInt(textVade.getText());
				}
				
				else if (text_bosMu1 == false && text_bosMu2 == false)
					uyari.setText("Banka Seçiniz");
				
				// ---------------------------------------------------------------------------------------------------
				
				TextDoldur(text_bosMu1, text_bosMu2, anaPara, vade, netKazanc);
			}
		});	
	}
	
	public void TabloDoldur (boolean text_bosMu , boolean combo_bosMu , int taksitSayisi , int krediMiktari , Object modelim , Object duzenle ) {
		
		String [] gecici = new String [7];
		
		if (garantiClick % 2 == 1 && (text_bosMu == false && combo_bosMu == false)) {
			
			for (int i = 0 ; i < taksitSayisi ; i++) {
				for (int j = 0 ; j < 7 ; j++) {
					gecici[j] = base_banka[0].KrediFaiziHesapla(krediMiktari, taksitSayisi)[i][j];
					table.getColumnModel().getColumn(j).setCellRenderer((TableCellRenderer) duzenle);
				} ((DefaultTableModel) modelim).addRow(gecici);
			}
			
		}
		
		else if (ziraatClick % 2 == 1 && (text_bosMu == false && combo_bosMu == false)) {
			
			for (int i = 0 ; i < taksitSayisi ; i++) {
				for (int j = 0 ; j < 7 ; j++) {
					gecici[j] = base_banka[1].KrediFaiziHesapla(krediMiktari, taksitSayisi)[i][j];
					table.getColumnModel().getColumn(j).setCellRenderer((TableCellRenderer) duzenle);
				} ((DefaultTableModel) modelim).addRow(gecici);
			}
		}
		
		else if (vakifClick % 2 == 1 && (text_bosMu == false && combo_bosMu == false)) {

			for (int i = 0 ; i < taksitSayisi ; i++) {
				for (int j = 0 ; j < 7 ; j++) {
					gecici[j] = base_banka[2].KrediFaiziHesapla(krediMiktari, taksitSayisi)[i][j];
					table.getColumnModel().getColumn(j).setCellRenderer((TableCellRenderer) duzenle);
				} ((DefaultTableModel) modelim).addRow(gecici);
			}
		}
	}
	
	public void TextDoldur (boolean text_bosMu1 , boolean text_bosMu2, int anaPara , int vade , Object netKazanc) {
		
		// Yönlendirme ve class çaðýrma iþlemleri ----------------------------------------------------------------------
		
		if (garantiClick % 2 == 1 && (text_bosMu1 == false && text_bosMu2 == false)) {
			((JLabel) netKazanc).setText(base_banka[0].MevduatGetirisiHesapla(anaPara, vade));
		}
		
		else if (ziraatClick % 2 == 1 && (text_bosMu1 == false && text_bosMu2 == false)) {
			((JLabel) netKazanc).setText(base_banka[1].MevduatGetirisiHesapla(anaPara, vade));
		}
		
		else if (vakifClick % 2 == 1 && (text_bosMu1 == false && text_bosMu2 == false)) {
			((JLabel) netKazanc).setText(base_banka[2].MevduatGetirisiHesapla(anaPara, vade));
		}
		else {
			((JLabel) netKazanc).setText(null);
		}
	}
}