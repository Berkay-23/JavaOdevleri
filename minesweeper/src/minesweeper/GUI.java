package minesweeper;

import javax.swing.*;
//import javax.swing.text.StyleContext.SmallAttributeSet;

import java.util.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GUI extends JFrame {
	
	/*   Bir arayüz daha oluþturup geniþlik, yükseklik ve mayýn sayýsýný o arayüzden alacaktým ancak oradaki deðiþkenleri bu arayüze aktarýrken bu sýnýfýn içinde global deðiþken olarak  
	 * tanýmlayamadým bu yorum satýrýnýn altýndaki x = geniþlik , y = yükseklik , number_of_mines = mayýn sayýsý , px = kutularýn boyutu (pixel olarak) tanýmlanmýþ deðiþkenlerdir 
	 * bu deðiþkenlerin verileri elle deðiþince programda bir aksaklýk yaþanmamakadýr.   */
	
	//StartMenu sm = new StartMenu();
	
	int x = 16;
	int y = 16;
	int px = 48;
	int number_of_mines = 40;
	int dmn = number_of_mines;
	
	public int yanalEksen = (px * x) + (x+2);
	public int dikeyEksen = (px * y) + (y+2);
	
	public int mx = -100;
	public int my = -100;

	public boolean resetter = false;
	int neighs = 0;
	
	public int smileX = (x/2)*px + (x/2) ; 
	public int smileY = 0;
	public int smileCenterX = smileX + (px/2);
	public int smileCenterY = smileY + (px/2);
	
	public int flaggerX =  (x/4)*px + (x/4);
	public int flaggerY =  0;
	public int flaggerCenterX = flaggerX + (px/2);
	public int flaggerCenterY = flaggerY+ (px/2);
	
	public int timeX = yanalEksen - (3*px)-3;
	public int second = 0;
	
	public boolean victory = false;
	public boolean defeat = false;
	public boolean happeness = true;
	public boolean flagger = false;
	
	int [][] mines = new int [y][x];
	int [][] neigbours = new int [y][x];
	boolean [][] revealed = new boolean [y][x];
	boolean [][] flagged = new boolean [y][x];
	
	Date startDate = new Date();

	public GUI () {
		
		//int genislik , int yukseklik , int mayinSayisi
		//x = genislik;
		//y = yukseklik;
		//number_of_mines = mayinSayisi;
		
		if(number_of_mines > x*y) {
			
			number_of_mines = x*y ;
			dmn = number_of_mines ;
		}
		
		this.setTitle("Mayýn Tarlasý");
		this.setBounds(0,0,yanalEksen+px/8,dikeyEksen+(px+1)+32); // px/8 de deðiþken kullanýlacak.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		
		mayinYerlestir(x , y , number_of_mines , mines);
		
		for (int i=0 ; i<y ; i++) {
			for (int j=0 ; j <x ; j++) {
				
					revealed [i][j] = false;
			}
		}
		
		for (int i=0 ; i<y ; i++) {
			for (int j=0 ; j<x ; j++) {
				neighs = 0;
				for (int m=0 ; m<y ; m++) {
					for (int n=0 ; n<x ; n++) {
						if(!(m == i && n == j)) {
							if (isN(i,j,m,n) == true) {
								neighs++;
							}
						}
					}
				}
				neigbours[i][j] = neighs;
			}
		}
		
		Board board = new Board();
		this.setContentPane(board);
		
		Move move = new Move();
		this.addMouseMotionListener(move);
		
		Click click = new Click();
		this.addMouseListener(click);
		
	}
	
	public class Board extends JPanel {	
		public void paintComponent (Graphics g) {
				
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0,0,yanalEksen+2,dikeyEksen+32+(px+1));
			
			int a=1 , b=px+1 ;
			
			for (int i=0 ; i<y ; i++) {
				for (int j=0 ; j<x ; j++) {	
					g.setColor(Color.GRAY);
					
					if (revealed[i][j] == true) {
						g.setColor(Color.WHITE);
						
						if (mines[i][j] == 1) {
							g.setColor(Color.RED);
						}
					}
					
					if (mx>=a && mx<=px+a && my>=b+26 && my<=px+b+26) {
						g.setColor(Color.LIGHT_GRAY);
					}
					
					g.fillRect(a,b,px,px);
					a = a + px+1 ;
					
					if (revealed[i][j] == true) {
						
						g.setColor(Color.BLACK);
						
						if (mines[i][j] == 0 && neigbours[i][j] != 0) {
							
							
							switch(neigbours[i][j]) {
							
							case 1:
								g.setColor(Color.BLUE);
								break;
								
							case 2:
								g.setColor(Color.GREEN);
								break;
								
							case 3:
								g.setColor(Color.RED);
								break;
								
							case 4:
								g.setColor(new Color(0,0,128));
								break;
								
							case 5:
								g.setColor(new Color(178,34,34));
								break;
								
							case 6:
								g.setColor(new Color(72,209,204));
								break;
								
							case 7:
								g.setColor(Color.BLACK);
								break;
								
							case 8:
								g.setColor(Color.DARK_GRAY);
								break;
							
							}
							
							g.setFont(new Font("Tahoma" , Font.BOLD , px/2));
							g.drawString(Integer.toString(neigbours[i][j]) , a-(px/2+10) , b+(px/2+7)); //boyutlara göre (px/2+10) ifadesinde deðiþkenler kullanýlacak.(64px e göre ayarlýdýr)
						}
						else if (mines[i][j] == 1) {
							g.fillRect(px * j + (j+1) + 4 + 10   ,  b - (4) + 0  + (px/5)  , 20 , 40); 
							g.fillRect(px * j + (j+1) + 4 + 0    ,  b - (4) + 10 + (px/5)  , 40 , 20); 
							g.fillRect(px * j + (j+1) + 4 + 5    ,  b - (4) + 5  + (px/5)  , 30 , 30); 
							// +4 ve -4 ler 48 px e göredir ayrýca mayýnýn ölçüleri 48-64 px le uyumlu 32 px le uyumlu deðil.
						}
					}
					
					// bayraklarýn  kutulardaki çizimi 
					
					if (flagged[i][j] == true) {
						
						g.setColor(Color.BLACK);
						g.fillRect(px * j + (j+1) + (px/3)  ,  b - (4) + 0  + (px/5)  ,  (px/16)  ,  px-(px/3));
						g.fillRect(px * j + (j+1) + (px/5)  ,  b + (px/2) + 0  + (px/5)  ,  (px/3)+(px/8)  ,  (px/12));
						
						g.setColor(Color.RED);
						g.fillRect(px * j + (j+1) + (px/3) + (px/16)  ,  b - (px/8) + 0  + (px/5)  ,  (px/3)  ,  (px/4));
					}
					
				}
				b = b + px+1;
				a = 1;
			}
			
			// gülen surat çizimi
			
			g.setColor(Color.YELLOW);
			g.fillOval(smileX, smileY, px, px);
			g.setColor(Color.BLACK);
			g.fillOval(smileX+(px/4) , smileY+(px/4) , px/6 , px/6);
			g.fillOval(smileX+(px/2) , smileY+(px/4) , px/6 , px/6);
			
			if (happeness == true) {
				g.fillRect(smileX+(px/4)-(px/24) , smileY+(px/4)+(px/3)+(px/16) , px/2 , px/16);
				g.fillRect(smileX+(px/4)-(px/24),smileY+(px/4)+(px/3) , px/8, px/8);
				g.fillRect(smileX+(px/4)-(px/24)+(px/2)-(px/8),smileY+(px/4)+(px/3) , px/8, px/8);
			}
			else {
				g.fillRect(smileX+(px/4)-(px/24) , smileY+(px/4)+(px/3) , px/2 , px/16);
				g.fillRect(smileX+(px/4)-(px/24),smileY+(px/4)+(px/3) , px/8, px/8);
				g.fillRect(smileX+(px/4)-(px/24)+(px/2)-(px/8),smileY+(px/4)+(px/3) , px/8, px/8);
			}
			
			// bayrak çizimi
			
			g.setColor(Color.WHITE);
			g.fillRect(flaggerX+(px/3), flaggerY+(px/8), (px/16), px-(px/3));
			g.fillRect(flaggerX+(px/5), flaggerY+(px/2)+(px/4), (px/3)+(px/8), (px/12));
			
			g.setColor(Color.RED);
			g.fillRect(flaggerX+(px/4)+(px/7), flaggerY+(px/8), (px/3), (px/4));
			
			g.setColor(Color.BLACK);

			if (flagger == true)
				g.setColor(Color.WHITE);
			
			g.drawOval(flaggerX, flaggerY, px, px);
			g.drawOval(flaggerX+1, flaggerY+1, px-2, px-2);
			g.drawOval(flaggerX+2, flaggerY+2, px-4, px-4);
			
			
			// süre çizimi
			
			g.setColor(Color.BLACK);
			g.fillRect(timeX , 0 , px*3+3 , px);
			
			if (defeat == false && victory == false) {
				second = (int) ((new Date().getTime()-startDate.getTime()) / 1000 );
			}
			
			if (second > 999)
				second = 999;
			g.setColor(Color.WHITE);
			
			if(victory == true)
				g.setColor(Color.GREEN);
			
			else if (defeat == true)
				g.setColor(Color.RED);
			
			g.setFont(new Font("THOMA" , Font.BOLD , px));
			
			if (second < 10)
				g.drawString("00" + Integer.toString(second), timeX+(px/2)+(px/4) , px-(px/8));
			
			else if (second > 9 && second < 100)
				g.drawString("0" + Integer.toString(second), timeX+(px/2)+(px/4) , px-(px/8));
			
			else if (second > 99)
				g.drawString(Integer.toString(second), timeX+(px/2)+(px/4) , px-(px/8));
			
			
			// Mayýn sayýsý çizimi 
						
			int basamak = Integer.toString(number_of_mines).length();
			
			int width;
			
			switch (basamak) {
				
			case 1:
				width = px;
				break;
			
			case 2: 
				width = px + px/4;
				break;
				
			case 3 :
				width = 2*px - px/8;
				break;
				
			default :
				width = px*3;
				
			}
			
			
			g.setColor(Color.BLACK);
			g.fillRect(0  ,  0  ,  width  ,  px);
			
			g.setColor(Color.WHITE);
			g.setFont(new Font("THOMA" , Font.BOLD , (px/(3/2))));
			g.drawString(Integer.toString(dmn)  ,  px/24  , px-(px/8) );
			
			
			repaint();
		}
	}
	
	public class Move implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {
			
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			mx = e.getX();
			my = e.getY();
			repaint();
		}
	}
	
	public class Click implements MouseListener {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			
			mx = e.getX();
			my = e.getY();
			
			if (inBoxX() != -1 && inBoxY() != -1) {
				
				if (flagger == true && revealed [inBoxX()][inBoxY()] == false) {
					
					if(flagged[inBoxX()][inBoxY()] == false) {
						flagged[inBoxX()][inBoxY()] = true;
						dmn--;
					}
					else {
						flagged[inBoxX()][inBoxY()] = false;
							dmn++;
					}
				}
				else {
					
					if (flagged[inBoxX()][inBoxY()] == false) {
						revealed[inBoxX()][inBoxY()] = true;
					}
				}
					
			}
			
			if (inSmile() == true)
				resetAll();
			
			if (inFlagger() == true) {
				
				if (flagger == false) 
					flagger = true;
				
				else 
					flagger = false;
			}
			
			repaint();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
			
		}
		
	}
	
	public static String [] koordinatlar (int x , int y ,int number_of_mines) {
		
		//Bu fonksiyon tekrarsýz olmak þartýyla x,y formunda , verilen mayýn sayýsana göre koordinatlar oluþturup bu koordinatlarý String tipdeki diziye kaydeder.
		
		
		if (number_of_mines > (x * y)) {
			number_of_mines = x * y;
		}
		
		Random rnd = new Random();
		
		boolean durum = true ;
		
		String coordinate [] = new String [number_of_mines];
		
		int i = 0;
		
		while (durum) {
			
			int coordinate_x , coordinate_y ;
			
			coordinate_x = rnd.nextInt(x);
			coordinate_y = rnd.nextInt(y);
			
			String location = String.valueOf(coordinate_x) + "," + String.valueOf(coordinate_y);
			
			int tespit_sayisi = 0;
			
			for (int j=0 ; j<coordinate.length ; j++) {
				
				if (location.equals(coordinate[j])) 
					tespit_sayisi++;
			}
			
			if(tespit_sayisi == 0) {
				coordinate[i] = location;
				i = i + 1;
				number_of_mines-- ;
			}
			
			if(number_of_mines == 0)
				durum = false ;
			
		}
		return coordinate;
	}
		
	public void mayinYerlestir (int x , int y ,int number_of_mines , int[][] mines) {
		
		if (inSmile() == true) {
			
		}
		
		String [] konumlar = new String [number_of_mines];
		
		konumlar = koordinatlar(x, y, number_of_mines);
		
		int coordinate_x , coordinate_y ;
		String coordinate_X = null , coordinate_Y = null;
		String location;
		
		for (int i=0 ; i<konumlar.length ; i++) {
			
			location = konumlar[i];
			
			for(int j=0 ; j<location.length() ; j++ ) {
				
				if (location.indexOf(",") ==  1) {
					coordinate_X = location.substring(0,1);
					coordinate_Y = location.substring(2);
				}
				else if (location.indexOf(",") ==  2) {
					coordinate_X = location.substring(0,2);
					coordinate_Y = location.substring(3);
				}
			}
			
			coordinate_x = Integer.parseInt(coordinate_X);
			coordinate_y = Integer.parseInt(coordinate_Y);
			
			mines[coordinate_y][coordinate_x] = 1 ;
		}
	}
	
	public void checkVictoryStatus() {
		

		for (int i=0 ; i<y ; i++) {
			for (int j=0 ; j<x ; j++) {	
				
				if (revealed[i][j] == true && mines[i][j] == 1) {
					defeat = true;
					happeness = false;
					victory = false ;
					
					for(int k=0 ; k<y ; k++) {
						for (int l=0 ; l<x ; l++) {
							revealed[k][l] = true;
						}
					}
				}
				
				if(flagged[i][j] == true && mines [i][j] == 1 && dmn == 0) {
					victory = true;
				}
				
			}
		}
		
		//if (totalBoxsesRevealed() >= x*y - totalMines()) {
		//	victory = true;
		//}
		
		
	}
	
	public int totalMines () {
		
		int total = number_of_mines;
		
		return total;
	}

	public int totalBoxsesRevealed () {
		
		int total = 0;
		
		for (int i=0 ; i<y ; i++) {
			for (int j=0 ; j<x ; j++) {	
				
				if (revealed[i][j] == true)
					total = total + 1;
			}
		}
		return total;
	}

	public void resetAll() {
		
		if (inSmile() == true) {
			
			for (int i = 0; i < y ; i++) {
				for (int j = 0; j < x ; j++) {
					
					mines[i][j] = 0;
				}
			}
		}
		
		resetter = true;
		flagger = false;
		
		startDate = new Date();
		
		happeness = true ;
		victory = false;
		defeat = false ;
		dmn = number_of_mines;
		
		mayinYerlestir(x , y , number_of_mines , mines);
		
		for (int i=0 ; i<y ; i++) {
			for (int j=0 ; j <x ; j++) {
				
				revealed [i][j] = false;
				flagged [i][j] = false;
			}
		}
		
		for (int i=0 ; i<y ; i++) {
			for (int j=0 ; j<x ; j++) {
				neighs = 0;
				for (int m=0 ; m<y ; m++) {
					for (int n=0 ; n<x ; n++) {
						if(!(m == i && n == j)) {
							if (isN(i,j,m,n) == true) {
								neighs++;
							}
						}
					}
				}
				neigbours[i][j] = neighs;
			}
		}
		resetter = false;
		//mayinYerlestir(x , y , number_of_mines , mines);
	}
	
	public boolean inSmile () {
		
		int dif = (int) Math.sqrt(Math.abs(mx-smileCenterX)*Math.abs(mx-smileCenterX)+Math.abs(my-smileCenterY)*Math.abs(my-smileCenterY));
		
		if (smileX <= mx && mx <= smileX+px && smileY <= my && my <= smileY+px+(px/2))
			return true;
		
		if (dif < px/2) {
			return true;
		}
		
		return false;
	}
	
	public boolean inFlagger () {
		
		int dif = (int) Math.sqrt(Math.abs(mx-flaggerCenterX)*Math.abs(mx-flaggerCenterX)+Math.abs(my-flaggerCenterY)*Math.abs(my-flaggerCenterY));
		
		if (flaggerX <= mx && mx <= flaggerX+px && flaggerY <= my && my <= flaggerY+px+(px/2))
			return true;
		
		if (dif < px/2) {
			return true;
		}
		
		return false;
	}
	
	public int inBoxX () {
		
		int a=1 , b=px+1 ;
		
		for (int i=0 ; i<y ; i++) {
			for (int j=0 ; j<x ; j++) {	
				
				if (mx>=a && mx<=px+a && my>=b+26 && my<=px+b+26) {
					return i;
				}
				a = a + px+1 ;
			}
			b = b + px+1;
			a = 1;
		}
		return -1;
	}
	
	public int inBoxY () {
		
int a=1 , b=px+1 ;
		
		for (int i=0 ; i<y ; i++) {
			for (int j=0 ; j<x ; j++) {	
				
				if (mx>=a && mx<=px+a && my>=b+26 && my<=px+b+26) {
					return j;
				}
				a = a + px+1 ;
			}
			b = b + px+1;
			a = 1;
		}
		return -1;
	}
	
	public boolean isN (int mX, int mY , int cX , int cY) {
		
		if (mX-cX < 2 && mX-cX > -2 && mY-cY < 2 && mY-cY > -2 && mines[cX][cY] == 1) {
			return true;
		}
		
		return false;
		
	}	
}