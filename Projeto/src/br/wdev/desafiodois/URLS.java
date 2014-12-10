package br.wdev.desafiodois;

public enum URLS {
	HTTP("http"), SSH("ssh");
	
	private String pattern;
	
	private URLS(String pattern){
		this.pattern = pattern;
	}
	
	public String getURLString(){
		return this.pattern;
	}
}
