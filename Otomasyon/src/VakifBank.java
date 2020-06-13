
public class VakifBank extends Base_banka {
	
	@Override
	public String MevduatGetirisiHesapla(double anaPara, int vade) {
	
			//setFaizOrani(8.75);
		
		return super.MevduatGetirisiHesapla(anaPara, vade);
	}
	
	@Override
	public String [][] KrediFaiziHesapla(int arzPara, int taksitSayisi) {
		
		setKrediFaizOrani(2.05);
		
		return super.KrediFaiziHesapla(arzPara, taksitSayisi);
	}

}
