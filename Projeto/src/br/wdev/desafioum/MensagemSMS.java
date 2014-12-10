package br.wdev.desafioum;

public class MensagemSMS {

	public String getSMS(String valueText){
		String[] texto = valueText.split("");
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
		String texto = "QUERO TRABALHAR NA WDEV";
		String sms = new MensagemSMS().getSMS(texto);
		System.out.println(sms);
	}
	
}
