package org.adataq.jserializer.tests;

import java.util.Date;

import org.adataq.jserializer.JSerializer;
import org.adataq.jserializer.json.JsonStructure;

public class Main {

	public static void main(String[] args) {
		JsonStructure json = JSerializer.json().parse("{\r\n" + 
				"	\"associados\" : [null],\r\n" + 
				"	\"chefe\" : {},\r\n" + 
				"	\"dataNascimento\" : 129383129,\r\n" + 
				"	\"enderecoPrincipal\" : {},\r\n" + 
				"	\"id\" : 123,\r\n" + 
				"	\"nome\" : \"Gerson\",\r\n" + 
				"	\"salario\" : 1823829138.3123,\r\n" + 
				"	\"tipo\" : \"COMUM\"\r\n" + 
				"}");
		
		//Convertido
		Usuario usuarioConvertido = json.asJsonObject().to(Usuario.class);
		System.out.println(usuarioConvertido.getSalario());
		
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
