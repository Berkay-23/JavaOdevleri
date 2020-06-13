import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class YetkiliGiris extends JFrame {
	
	DataBase db = new DataBase();

	private JPanel contentPane;
	private JTable table;
	private JTextField text_adi;
	private JTextField text_soyadi;
	private JTextField text_ePosta;
	private JTextField text_sifre;
	private JTextField text_dogumTarihi;
	private JTextField text_tc;
	private JTextField text_telefon;
	private JTextField text_id;
	private JTextField textFilitre;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					YetkiliGiris frame = new YetkiliGiris();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public YetkiliGiris() {
		
		Image mQuit = new ImageIcon(this.getClass().getResource("/mQuit.png")).getImage();
		Image yQuit = new ImageIcon(this.getClass().getResource("/yQuit.png")).getImage();
		Image sQuit = new ImageIcon(this.getClass().getResource("/sQuit.png")).getImage();
		
		DefaultTableModel modelim = new DefaultTableModel();
		DefaultTableCellRenderer duzenle = new DefaultTableCellRenderer();
		
		Object [] sutunlar = {"ID", "Ýsim", "Soy Ýsim", "e-posta", "Þifre", "Doðum Tarihi", "TC Kimlik No" , "Telefon No"}; 
		
		Object [] satirlar = new Object [8];
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(550, 200, 900, 600);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(0, 0, 51));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 223, 870, 329);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		duzenle.setHorizontalAlignment(JLabel.CENTER);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setDefaultRenderer(String.class, duzenle);
		modelim.setColumnIdentifiers(sutunlar);
		table.setModel(modelim);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(110);
		table.getColumnModel().getColumn(7).setPreferredWidth(110);
		
		scrollPane.setViewportView(table);
		
		
		text_adi = new JTextField();
		text_adi.setFont(new Font("Tahoma", Font.BOLD, 13));
		text_adi.setHorizontalAlignment(SwingConstants.CENTER);
		text_adi.setBounds(80, 60, 125, 25);
		contentPane.add(text_adi);
		text_adi.setColumns(10);
		
		text_soyadi = new JTextField();
		text_soyadi.setFont(new Font("Tahoma", Font.BOLD, 13));
		text_soyadi.setHorizontalAlignment(SwingConstants.CENTER);
		text_soyadi.setColumns(10);
		text_soyadi.setBounds(80, 105, 125, 25);
		contentPane.add(text_soyadi);
		
		text_ePosta = new JTextField();
		text_ePosta.setBackground(Color.GRAY);
		text_ePosta.setEditable(false);
		text_ePosta.setFont(new Font("Tahoma", Font.BOLD, 13));
		text_ePosta.setHorizontalAlignment(SwingConstants.CENTER);
		text_ePosta.setColumns(10);
		text_ePosta.setBounds(335, 35, 200, 25);
		contentPane.add(text_ePosta);
		
		text_sifre = new JTextField();
		text_sifre.setBackground(Color.GRAY);
		text_sifre.setEditable(false);
		text_sifre.setFont(new Font("Tahoma", Font.BOLD, 13));
		text_sifre.setHorizontalAlignment(SwingConstants.CENTER);
		text_sifre.setColumns(10);
		text_sifre.setBounds(360, 95, 150, 25);
		contentPane.add(text_sifre);
		
		text_dogumTarihi = new JTextField();
		text_dogumTarihi.setFont(new Font("Tahoma", Font.BOLD, 13));
		text_dogumTarihi.setHorizontalAlignment(SwingConstants.CENTER);
		text_dogumTarihi.setColumns(10);
		text_dogumTarihi.setBounds(757, 15, 125, 25);
		contentPane.add(text_dogumTarihi);
		
		text_tc = new JTextField();
		text_tc.setBackground(Color.GRAY);
		text_tc.setEditable(false);
		text_tc.setFont(new Font("Tahoma", Font.BOLD, 13));
		text_tc.setHorizontalAlignment(SwingConstants.CENTER);
		text_tc.setColumns(10);
		text_tc.setBounds(757, 60, 125, 25);
		contentPane.add(text_tc);
		
		text_telefon = new JTextField();
		text_telefon.setFont(new Font("Tahoma", Font.BOLD, 13));
		text_telefon.setHorizontalAlignment(SwingConstants.CENTER);
		text_telefon.setColumns(10);
		text_telefon.setBounds(757, 105, 125, 25);
		contentPane.add(text_telefon);
		
		text_id = new JTextField();
		text_id.setEditable(false);
		text_id.setBackground(Color.GRAY);
		text_id.setFont(new Font("Tahoma", Font.BOLD, 13));
		text_id.setHorizontalAlignment(SwingConstants.CENTER);
		text_id.setColumns(10);
		text_id.setBounds(80, 15, 125, 25);
		contentPane.add(text_id);
		
		JLabel lblID = new JLabel("ID : ");
		lblID.setForeground(Color.WHITE);
		lblID.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setBounds(0, 15, 70, 25);
		contentPane.add(lblID);
		
		JLabel lblIsim = new JLabel("Ýsim :");
		lblIsim.setHorizontalAlignment(SwingConstants.CENTER);
		lblIsim.setForeground(Color.WHITE);
		lblIsim.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIsim.setBounds(0, 60, 70, 25);
		contentPane.add(lblIsim);
		
		JLabel lblSoyad = new JLabel("Soyisim :");
		lblSoyad.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoyad.setForeground(Color.WHITE);
		lblSoyad.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSoyad.setBounds(0, 105, 70, 25);
		contentPane.add(lblSoyad);
		
		JLabel lblTelefonNo = new JLabel("Telefon No :");
		lblTelefonNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelefonNo.setForeground(Color.WHITE);
		lblTelefonNo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTelefonNo.setBounds(655, 104, 90, 25);
		contentPane.add(lblTelefonNo);
		
		JLabel lblTcNo = new JLabel("TC No :");
		lblTcNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTcNo.setForeground(Color.WHITE);
		lblTcNo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTcNo.setBounds(655, 59, 90, 25);
		contentPane.add(lblTcNo);
		
		JLabel lblDogumT = new JLabel("Doðum T :");
		lblDogumT.setHorizontalAlignment(SwingConstants.CENTER);
		lblDogumT.setForeground(Color.WHITE);
		lblDogumT.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDogumT.setBounds(655, 14, 90, 25);
		contentPane.add(lblDogumT);
		
		JLabel lblEposta = new JLabel("e-posta :");
		lblEposta.setHorizontalAlignment(SwingConstants.CENTER);
		lblEposta.setForeground(Color.WHITE);
		lblEposta.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEposta.setBounds(335, 5, 200, 25);
		contentPane.add(lblEposta);
		
		JLabel lblSifre = new JLabel("Þifre :");
		lblSifre.setHorizontalAlignment(SwingConstants.CENTER);
		lblSifre.setForeground(Color.WHITE);
		lblSifre.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSifre.setBounds(360, 65, 150, 25);
		contentPane.add(lblSifre);
		
		JButton btnListele = new JButton("Kayýtlarý Listele");
		btnListele.setForeground(Color.WHITE);
		btnListele.setBackground(Color.DARK_GRAY);
		btnListele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				TabloDoldur(satirlar, modelim, duzenle, "SELECT * FROM users");
				
				text_id.setText(null);
				text_adi.setText(null);
				text_soyadi.setText(null);
				text_ePosta.setText(null);
				text_sifre.setText(null);
				text_dogumTarihi.setText(null);
				text_tc.setText(null);
				text_telefon.setText(null);
			}
		});
		btnListele.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnListele.setBounds(360, 135, 150, 30);
		contentPane.add(btnListele);
		
		JButton btnGuncelle = new JButton("Kaydý Güncelle");
		btnGuncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String id = text_id.getText();
				String isim = text_adi.getText();
				String soyisim = text_soyadi.getText();
				String ePosta = text_ePosta.getText();
				String sifre = text_sifre.getText();
				String dogumTarihi = text_dogumTarihi.getText();
				String tc = text_tc.getText();
				String telefon = text_telefon.getText();
				
				
				String mySQL_sorgu = "UPDATE users SET " 
						+ "id='" + id + "',"
						+ "isim='" + isim + "',"
						+ "soyisim='" + soyisim + "',"
						+ "ePosta='" + ePosta + "',"
						+ "sifre='" + sifre + "',"
						+ "dogumTarihi='" + dogumTarihi + "',"
						+ "tc='" + tc + "',"
						+ "telefon='" + telefon + "'"
						+ "WHERE id='" + id + "'"
						;
				
				db.ekle_guncelle_sil(mySQL_sorgu);
				
				text_id.setText(null);
				text_adi.setText(null);
				text_soyadi.setText(null);
				text_ePosta.setText(null);
				text_sifre.setText(null);
				text_dogumTarihi.setText(null);
				text_tc.setText(null);
				text_telefon.setText(null);
				
				TabloDoldur(satirlar, modelim, duzenle, "SELECT * FROM users");
			}
		});
		btnGuncelle.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGuncelle.setBounds(12, 180, 150, 30);
		contentPane.add(btnGuncelle);
		
		JButton btnSil = new JButton("Kaydý Sil");
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String id = text_id.getText();
				
				String mySQL_sorgu = "DELETE FROM users WHERE id='" + id + "'";
				
				db.ekle_guncelle_sil(mySQL_sorgu);
				
				text_id.setText(null);
				text_adi.setText(null);
				text_soyadi.setText(null);
				text_ePosta.setText(null);
				text_sifre.setText(null);
				text_dogumTarihi.setText(null);
				text_tc.setText(null);
				text_telefon.setText(null);
				
				TabloDoldur(satirlar, modelim, duzenle, "SELECT * FROM users");
			}
		});
		btnSil.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSil.setBounds(732, 180, 150, 30);
		contentPane.add(btnSil);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"ID", "Ýsim", "Soyisim", "e-posta", "Doðum Tarihi", "TC No", "Telefon"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(465, 180, 150, 30);
		contentPane.add(comboBox);
		
		textFilitre = new JTextField();
		textFilitre.setHorizontalAlignment(SwingConstants.CENTER);
		textFilitre.setFont(new Font("Tahoma", Font.BOLD, 13));
		textFilitre.setColumns(10);
		textFilitre.setBounds(255, 180, 150, 30);
		contentPane.add(textFilitre);
		
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
		lblQuit.setBounds(410, 170, 50, 50);
		contentPane.add(lblQuit);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				text_id.setText((String) modelim.getValueAt(table.getSelectedRow() , 0));
				text_adi.setText((String) modelim.getValueAt(table.getSelectedRow() , 1));
				text_soyadi.setText((String) modelim.getValueAt(table.getSelectedRow() , 2));
				text_ePosta.setText((String) modelim.getValueAt(table.getSelectedRow() , 3));
				text_sifre.setText((String) modelim.getValueAt(table.getSelectedRow() , 4));
				text_dogumTarihi.setText((String) modelim.getValueAt(table.getSelectedRow() , 5));
				text_tc.setText((String) modelim.getValueAt(table.getSelectedRow() , 6));
				text_telefon.setText((String) modelim.getValueAt(table.getSelectedRow() , 7));
				
			}
		});
		
		//Timer timer = new Timer();
		//TimerTask yenile = new TimerTask() {
		//	
		//	@Override
		//	public void run() {
		//	}
		//};
		//timer.schedule(yenile, 0 , 1000);
		
		textFilitre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				
				String mySQL_sorgu = "SELECT * FROM users";

				
				if (textFilitre.getText().equals("")) {
					
					mySQL_sorgu = "SELECT * FROM users";
				}
				
				else {
					
					if (comboBox.getSelectedIndex() == 0) {
						
						mySQL_sorgu = "SELECT * FROM users WHERE id LIKE '%" + textFilitre.getText() + "%'";
					}
						
					
					if (comboBox.getSelectedIndex() == 1) {
						
						mySQL_sorgu = "SELECT * FROM users WHERE isim LIKE '%" + textFilitre.getText() + "%'";
					}
						
					
					if (comboBox.getSelectedIndex() == 2) {
						
						mySQL_sorgu = "SELECT * FROM users WHERE soyisim LIKE '%" + textFilitre.getText() + "%'";
					}
						
					
					if (comboBox.getSelectedIndex() == 3) {
						
						mySQL_sorgu = "SELECT * FROM users WHERE ePosta LIKE '%" + textFilitre.getText() + "%'";
					}
						
					
					if (comboBox.getSelectedIndex() == 4) {
						
						mySQL_sorgu = "SELECT * FROM users WHERE dogumTarihi LIKE '%" + textFilitre.getText() + "%'";
					}
						
					
					if (comboBox.getSelectedIndex() == 5) {
						
						mySQL_sorgu = "SELECT * FROM users WHERE tc LIKE '%" + textFilitre.getText() + "%'";
					}
						
					
					if (comboBox.getSelectedIndex() == 6) {
						
						mySQL_sorgu = "SELECT * FROM users WHERE telefon LIKE '%" + textFilitre.getText() + "%'";
					}
				}
				
				TabloDoldur(satirlar, modelim, duzenle, mySQL_sorgu);
			}
		});
		
	}
	
	public void TabloDoldur (Object [] satirlar , Object modelim , DefaultTableCellRenderer duzenle , String mySQL_sorgu) {
		
		ResultSet myRs = db.veriAl(mySQL_sorgu);
		
		((DefaultTableModel) modelim).setRowCount(0);
		
		try {
			
			while(myRs.next()) {
				
				satirlar[0] = myRs.getString("id");
				satirlar[1] = myRs.getString("isim");
				satirlar[2] = myRs.getString("soyisim");
				satirlar[3] = myRs.getString("ePosta");
				satirlar[4] = myRs.getString("sifre");
				satirlar[5] = myRs.getString("dogumTarihi");
				satirlar[6] = myRs.getString("tc");
				satirlar[7] = myRs.getString("telefon");
				
				((DefaultTableModel) modelim).addRow(satirlar);
				
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		for (int i=0 ; i<8 ; i++)
			table.getColumnModel().getColumn(i).setCellRenderer((TableCellRenderer) duzenle);
		
	}
}