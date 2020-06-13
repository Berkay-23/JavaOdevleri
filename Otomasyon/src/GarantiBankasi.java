
public class GarantiBankasi extends Base_banka {
	
	@Override
	public String MevduatGetirisiHesapla(double anaPara, int vade) {
		
		mevduatVergiOrani = 8.2;
		setFaizOrani(6.25);
		
		return super.MevduatGetirisiHesapla(anaPara, vade);
	}
	
	@Override
	public String [][] KrediFaiziHesapla(int arzPara, int taksitSayisi) {
		
		setKrediFaizOrani(1.68);
		
		return super.KrediFaiziHesapla(arzPara, taksitSayisi);
	}
}
