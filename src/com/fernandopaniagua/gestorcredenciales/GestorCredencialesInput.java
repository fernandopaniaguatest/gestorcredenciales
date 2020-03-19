package com.fernandopaniagua.gestorcredenciales;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import com.fernandopaniagua.gestorcredenciales.persistencia.FileManager;
import com.fernandopaniagua.gestorcredenciales.security.DigestManager;

public class GestorCredencialesInput {

	public static void main(String[] args) {
		String email;
		String password;
		byte[] digest;
		Scanner sc = new Scanner(System.in);
		System.out.println("Registro de usuario");
		System.out.print("Introduzca email:");
		email = sc.nextLine();
		System.out.print("Introduzca password:");
		password = sc.nextLine();
		
		digest = DigestManager.getMessageDigest(password);
		
		try {
			FileManager.storePassword("H:/__PROYECTOS_TARDE/passwords/" 
					+ email + ".password", digest);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sc.close();
	}

}
