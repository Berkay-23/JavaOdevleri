public class Base_banka {
		
	public double mevduatVergiOrani = 16;
	private double krediFaizOrani = 0.96;
	private double mevduatFaizOrani = FaizOraniHesapla(mevduatVergiOrani);
	
	public String MevduatGetirisiHesapla (double anaPara , int vade) {
		
		double brutTutar , netTutar , vergiTutari;
		
		brutTutar = (anaPara / 100) * (getFaizOrani() / 365) * vade;
		
		vergiTutari = (brutTutar * mevduatVergiOrani) / 100;
		
		netTutar = brutTutar - vergiTutari;
		
		String [] tutar = {Double.toString(netTutar)};
		
		return SayiDüzenle(tutar)[0] + " TL";
	}
	
	public String [][] KrediFaiziHesapla (int arzPara , int taksitSayisi) {
		   
		double taksitTutari = 0 , krediTutari = 0 , toplamFaiz = 0;
		
		double borcTaksiti , kalanBorc , faizTaksiti ;
		
		String [] Stringler = new String [7];
		
		String [][] veriler = new String [taksitSayisi][Stringler.length];
		
		int taksitNo = 1;
		
		kalanBorc = arzPara; 
		
		borcTaksiti = arzPara / taksitSayisi;
		
		while (taksitNo <= taksitSayisi) {
			
			faizTaksiti = (kalanBorc * krediFaizOrani) / 100;
			
			taksitTutari = faizTaksiti + borcTaksiti;
			
			kalanBorc = arzPara - (borcTaksiti * taksitNo);
			
			toplamFaiz = toplamFaiz + faizTaksiti;
			
			krediTutari = krediTutari + taksitTutari;
			
			Stringler[0] =  Integer.toString(taksitNo);
			Stringler[1] =  Double.toString(borcTaksiti);
			Stringler[2] =  Double.toString(faizTaksiti);
			Stringler[3] =  Double.toString(taksitTutari);
			Stringler[4] =  Double.toString(kalanBorc);  
			Stringler[5] =  Double.toString(toplamFaiz); 
			Stringler[6] =  Double.toString(krediTutari);
			 			
			SayiDüzenle(Stringler);
			
			Stringler[0] =  Integer.toString(taksitNo);
			
			for (int i = 0 ; i < Stringler.length ; i++) {
				veriler[taksitNo-1][i] = Stringler[i];
			}
			taksitNo = taksitNo + 1;
		} return veriler;
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
	
	public static String [] SayiDüzenle (String [] Stringler) {
		
		for (int i=0 ; i<Stringler.length ; i++) {
			
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
		} 
		return Stringler;
	}
}
