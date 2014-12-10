package br.wdev.desafioum;

public class MensagemSMS {

	public String getSMS(String valueText) throws Exception{
		String[] texto = valueText.split("");
		if(texto.length>255){
			throw new Exception("Mensagem Limitada a 255 Caracteres");
		}
		String sms = "";
		String ultimaLetra = "";
		for (int i = 0; i < texto.length; i++) {
			if(texto[i].equalsIgnoreCase(" ")){
				sms += "0";
			}else{
				for(GrupoCaracter grupo : GrupoCaracter.values()){
					if(texto[i].equalsIgnoreCase(grupo.toString())){
						if(ultimaLetra.equalsIgnoreCase(grupo.numberValue())){
							sms += "_";
						}
						sms += grupo.getNumber();
						ultimaLetra = grupo.numberValue();
					}
				}
			}
		}
		return sms;
	}
	
	public static void main(String[] args) {
		try {
			String texto = "QUERO TRABALHAR NA WDEV";
			String sms;
			sms = new MensagemSMS().getSMS(texto);
			System.out.println(sms);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
