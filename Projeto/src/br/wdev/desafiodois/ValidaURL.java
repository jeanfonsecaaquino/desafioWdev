package br.wdev.desafiodois;


public class ValidaURL {

	private String protocolo,host, dominio, path, parametros, usuario, senha;
	
	public String getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getParametros() {
		return parametros;
	}

	public void setParametros(String parametros) {
		this.parametros = parametros;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public ValidaURL getPartesURL(String url) throws Exception{
		ValidaURL validaUrl = new ValidaURL();
		String[] splitProtocolo = url.split("://");
		String path = "";
		for (URLS urls : URLS.values()) {
			if(urls.getURLString().equalsIgnoreCase(splitProtocolo[0])){
				if(urls.equals(URLS.HTTP)){
					String[] splitHost = splitProtocolo[1].split("\\.", 2);
					String[] paths = splitHost[1].split("\\/");
					for (int i = 1; i < paths.length; i++) {
						if(paths[i].contains("=") || paths[i].contains("&")){
							validaUrl.setProtocolo(splitProtocolo[0]);
							validaUrl.setHost(splitHost[0]);
							validaUrl.setPath(path);
							validaUrl.setDominio(paths[0]);
							validaUrl.setParametros(path);
							String [] parametros = paths[i].split("&");
							String parametro = "";
							for (int j = 0; j < parametros.length; j++) {
								parametro += parametros[j] + " ";
							}
							validaUrl.setParametros(parametro);
						}else{
							path += paths[i] + "/";
						}
					}
				}else if(urls.equals(URLS.SSH)){
					String[] dados = splitProtocolo[1].split("\\%");
					String[] dominio = dados[1].split("@");
					validaUrl.setProtocolo(splitProtocolo[0]);
					validaUrl.setUsuario(dados[0]);
					validaUrl.setSenha(dominio[0]);
					validaUrl.setDominio(dominio[1]);
				}
			}
		}
		if(validaUrl.getProtocolo()==null){
			throw new Exception("URL Invalida");
		}
		return validaUrl;
	}
	
	public static void main(String[] args) {
		try {
			new ValidaURL().getPartesURL("http://www.google.com/mail/usertemp/negocios/user=fulano&senha=teste&blabla=hohoho").toString();
			new ValidaURL().getPartesURL("ssh://fulano%senha@git.com/").toString();
			new ValidaURL().getPartesURL("sshs://fulano%senha@git.com/").toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		String retorno = "";
		if(this.protocolo!=null){
			retorno+= "protocolo: " + this.protocolo + "\n";
		}
		if(this.host!=null){
			retorno+= "host: " + this.host + "\n";
		}
		if(this.dominio!=null){
			retorno+= "dominio: " + this.dominio + "\n";
		}
		if(this.path!=null){
			retorno+= "path: " + this.path + "\n";
		}
		if(this.parametros!=null){
			retorno+= "parametros: " + this.parametros + "\n";
		}
		if(this.usuario!=null){
			retorno+= "usuario: " + this.usuario + "\n";
		}
		if(this.senha!=null){
			retorno+= "senha: " + this.senha + "\n";
		}
		System.out.println(retorno);
		return retorno;
	}
	
}
