package com.gczutn.util;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class Utileria {
	
	public static String guardarArchivo(MultipartFile multiPart, String ruta){
		//obtenemos el nombre del archivo original
		String nombreOrig = multiPart.getOriginalFilename();
		nombreOrig = nombreOrig.replace(' ', '_');
		nombreOrig = secuenciaAleatoria(5) + nombreOrig;
		try {
			//formamos el nombre del archivo para guardarlo en el HD
			File imageFile = new File(ruta+nombreOrig); 
			System.out.println("Archivo: " + imageFile);
			//guardamos fisicamente el archivo en el HD
			multiPart.transferTo(imageFile);
			return nombreOrig;
		}catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
			return null;
		}
	}
	
	public static String secuenciaAleatoria(int contador) {
		String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder msg = new StringBuilder();
		while(contador-- != 0) {
			int caracter = (int)(Math.random()*caracteres.length());
			msg.append(caracteres.charAt(caracter));
		}
		return msg.toString();
	}
	
}
