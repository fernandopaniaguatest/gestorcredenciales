package com.fernandopaniagua.gestorcredenciales;

import java.util.Scanner;

import com.fernandopaniagua.gestorcredenciales.persistencia.FileManager;
import com.fernandopaniagua.gestorcredenciales.security.DigestManager;

public class GestorCredencialesValidacion {

	public static void main(String[] args) {
		try {
			String email;
			String password;
			byte[] digest;
			Scanner sc = new Scanner(System.in);
			System.out.println("Registro de usuario");
			System.out.print("Introduzca email:");
			email = sc.nextLine();
			System.out.print("Introduzca password:");
			password = sc.nextLine();
			
			String fichero = "H:/__PROYECTOS_TARDE/passwords/" + email + ".password";
			digest = FileManager.getPassword(fichero);
			
			byte[] nuevoDigest = DigestManager.getMessageDigest(password);
			
			boolean isOk = DigestManager.equalsDigest(digest, nuevoDigest);
			
			System.out.println(isOk);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
