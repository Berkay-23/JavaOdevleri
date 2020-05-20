package oopSorum;

import java.util.*;

class Bankacilik {

	public static void main(String[] args) {
		
		Scanner scn = new Scanner(System.in);
		
		System.out.print("Ana Para miktarýný giriniz = ");
		double anaPara = scn.nextDouble();
		
		System.out.print("Vadeyi giriniz (gün) = ");
		int vade = scn.nextInt();
		
		System.out.print("Kredi miktarýný giriniz = ");
		double kredi = scn.nextDouble();
		
		System.out.print("Taksit sayýsýný giriniz (ay) = ");
		int taksit = scn.nextInt();
		
		System.out.println("\n");
		
		Base_iþlemler [] base_iþlemler = new  Base_iþlemler [] {
			new GarantiBankasi() , new VakifBank() , new ZiraatBankasi()	
		};
		
		
		for (Base_iþlemler bankalar : base_iþlemler ) {
			
			System.out.println(vade + " gün vade ile " + anaPara + " TL paranýzý bankamýzda býrakýrsanýz net kazancýnýz = " + bankalar.MevduatGetirisiHesapla(anaPara , vade) + " TL");
						
			bankalar.KrediFaiziHesapla(kredi, taksit);
		}
	}
}

class Base_iþlemler {
	
	public double mevduatVergiOrani = 16;
	private double krediFaizOrani = 0.96;
	private double mevduatFaizOrani = FaizOraniHesapla(mevduatVergiOrani);
	
	
	
	public double MevduatGetirisiHesapla (double anaPara , int vade) {
		
		System.out.println("\n");
		
		double brutTutar , netTutar , vergiTutari;
		
		brutTutar = (anaPara / 100) * (getFaizOrani() / 365) * vade;
		
		vergiTutari = (brutTutar * mevduatVergiOrani) / 100;
		
		netTutar = brutTutar - vergiTutari;
		
		return netTutar;
	}
	
	public void KrediFaiziHesapla (double arzPara , int taksitSayisi) {
		
		System.out.println("\n\t\tBorç Taksiti" + "\t" + "Faiz Taksiti" + "\t" + "Toplam Taksit" + "\t" + "Kalan Borcunuz" + "\t" + "Toplam Faiz" + "\t" + "Kredi Tutarý" );
		   
		double taksitTutari = 0 , krediTutari = 0 , toplamFaiz = 0;
		
		double borcTaksiti , kalanBorc , faizTaksiti ;
		
		String [] Stringler = new String [6];
		
		int taksitNo = 1;
		
		kalanBorc = arzPara; 
		
		borcTaksiti = arzPara / taksitSayisi;
		
		while (taksitNo <= taksitSayisi) {
			
			faizTaksiti = (kalanBorc * krediFaizOrani) / 100;
			
			taksitTutari = faizTaksiti + borcTaksiti;
			
			kalanBorc = arzPara - (borcTaksiti * taksitNo);
			
			toplamFaiz = toplamFaiz + faizTaksiti;
			
			krediTutari = krediTutari + taksitTutari;
			
			 Stringler[0] =  Double.toString(borcTaksiti);
			 Stringler[1] =  Double.toString(faizTaksiti);
			 Stringler[2] =  Double.toString(taksitTutari);
			 Stringler[3] =  Double.toString(kalanBorc);  
			 Stringler[4] =  Double.toString(toplamFaiz); 
			 Stringler[5] =  Double.toString(krediTutari);
			 
			 System.out.print(taksitNo + ". Taksit = \t");
			 			
			for (int i=0 ; i<6 ; i++) {
				
				int noktaIndex = Stringler[i].indexOf(".");
				
				if (Stringler[i].length() >= noktaIndex + 3 && noktaIndex != -1) {
					
					Stringler[i] = Stringler[i].substring(0 , noktaIndex + 3);
					Stringler[i] = Stringler[i].replace(".", ",");
					
					if (Stringler[i].indexOf(",") > 2 ) {
						
						String birinciParca , ikinciParca;
						
						boolean durum = true;
						
						int index = Stringler[i].indexOf(",");
						
						while (durum) {
							
							index = index - 3;
							
							if (index > 0) {
								ikinciParca = Stringler[i].substring(index);
								birinciParca = Stringler[i].substring(0 , index );
								
								Stringler[i] = birinciParca + "." + ikinciParca;
							}
							
							if (index < 2)
								durum = false;
						}
						
					}
					
				}
					
				else if (Stringler[i].length() >= noktaIndex + 1) {
					
					Stringler[i] = Stringler[i] + "0"; 
					Stringler[i] = Stringler[i].replace(".", ",");
					
					if (Stringler[i].indexOf(",") > 2 ) {
						
						String birinciParca , ikinciParca;
						
						boolean durum = true;
						
						int index = Stringler[i].indexOf(",");
						
						while (durum) {
							
							index = index - 3;
							
							if (index > 0) {
								ikinciParca = Stringler[i].substring(index);
								birinciParca = Stringler[i].substring(0 , index );
								
								Stringler[i] = birinciParca + "." + ikinciParca;
							}
							
							if (index < 2)
								durum = false;
						}
						
					}
					
				}
				
					System.out.print(Stringler[i] + "\t\t");
				
				
			} System.out.println();
			
			taksitNo = taksitNo + 1;
		} System.out.println("\n\n\n");
	} 
	
	private double FaizOraniHesapla (double mevduatVergisi) {
		
		
		if (0 < mevduatVergisi && mevduatVergisi <= 15)
			return 9.5;
		
		else if (15 < mevduatVergisi && mevduatVergisi <= 30)
			return 8.75;
		
		else if (30 < mevduatVergisi && mevduatVergisi <= 45)
			return 7.0;
		
		else if (45 < mevduatVergisi && mevduatVergisi <= 60)
			return 4.9;
		
		else if (60 < mevduatVergisi && mevduatVergisi <= 75)
			return 2.7;
		
		else if (75 < mevduatVergisi && mevduatVergisi <= 90)
			return 1.5;
		
		else if (90 < mevduatVergisi && mevduatVergisi <= 100)
			return 0.1;
		
		else
			return 0;
		
	}
	
	public double getFaizOrani() {
		return mevduatFaizOrani;
	}
	
	public void setFaizOrani(double mevduatFaizOrani) {
		this.mevduatFaizOrani = mevduatFaizOrani;
	}

	public double getKrediFaizOrani() {
		return krediFaizOrani;
	}

	public void setKrediFaizOrani(double krediFaizOrani) {
		this.krediFaizOrani = krediFaizOrani;
	}
}

class GarantiBankasi extends Base_iþlemler{
	
	@Override
	public double MevduatGetirisiHesapla(double anaPara, int vade) {
		
		System.out.println("______________________________________________________ Garanti Bankasý _________________________________________________________________");
		
		mevduatVergiOrani = 8.2;
		setFaizOrani(6.25);
		
		return super.MevduatGetirisiHesapla(anaPara, vade);
	}
	
	@Override
	public void KrediFaiziHesapla(double arzPara, int taksitSayisi) {
		
		setKrediFaizOrani(1.68);
		
		super.KrediFaiziHesapla(arzPara, taksitSayisi);
	}
	
}

class VakifBank extends Base_iþlemler {
	
	@Override
	public double MevduatGetirisiHesapla(double anaPara, int vade) {
		
		System.out.println("______________________________________________________ Vakýf Bank _________________________________________________________________");
	
			//setFaizOrani(8.75);
		
		return super.MevduatGetirisiHesapla(anaPara, vade);
	}
	
	@Override
	public void KrediFaiziHesapla(double arzPara, int taksitSayisi) {
		
		setKrediFaizOrani(1.05);
		
		super.KrediFaiziHesapla(arzPara, taksitSayisi);
	}
}

class ZiraatBankasi extends Base_iþlemler {
	
	
	@Override
	public double MevduatGetirisiHesapla(double anaPara, int vade) {
		
		System.out.println("______________________________________________________ Ziraat Bankasý _________________________________________________________________");
		
		setFaizOrani(8);
		
		return super.MevduatGetirisiHesapla(anaPara, vade);
	}
	
	@Override
	public void KrediFaiziHesapla(double arzPara, int taksitSayisi) {
		
		setKrediFaizOrani(1.05);
		
		super.KrediFaiziHesapla(arzPara, taksitSayisi);
	}
	
}













