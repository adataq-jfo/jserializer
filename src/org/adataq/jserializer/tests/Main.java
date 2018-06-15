package org.adataq.jserializer.tests;

import java.util.Date;

import org.adataq.jserializer.JSerializer;
import org.adataq.jserializer.json.JsonStructure;

public class Main {

	public static void main(String[] args) {
		JsonStructure json = JSerializer.json().parse("{}");
		System.out.println(json);
		
		//Objeto
		Usuario usuario = new Usuario();
		usuario.setAssociados(new Usuario[] {usuario});
		usuario.setChefe(usuario);
		usuario.setDataDeNascimento(new Date());
		usuario.setEnderecoPrincipal(new Endereco());
		usuario.setId(10L);
		usuario.setNome("Felipe Pereira de Oliveira");
		usuario.setSalario(10500d);
		usuario.setTipo(Tipos.ADMINISTRADOR);
		
		
		System.out.println(JSerializer.json().serialize(usuario));
	}

}
