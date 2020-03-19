package com.fernandopaniagua.security;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fernandopaniagua.gestorcredenciales.persistencia.FileManager;

class FileManagerTest {

	String nombreFichero = "H:/__PROYECTOS_TARDE/passwords/prueba.txt";
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("BeforeEach");
		//1. CREACIÓN DEL FICHERO;
		String texto = "password@gmail.com";
		FileManager.storePassword(nombreFichero, texto.getBytes());
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("AfterEach");
		//3. BORRADO DEL FICHERO
		Path p = Paths.get("H:/__PROYECTOS_TARDE/passwords/prueba.txt");
		Files.delete(p);
	}

	@Test
	void testLectura() {
		System.out.println("Ejecutando testLectura");
		//2. INTENTO DE LECTURA DEL FICHERO
		try {
			byte[] digest = FileManager.getPassword(nombreFichero);
		} catch (NoSuchFileException e) {
			fail("NoSuchFileException al leer el fichero");
		} catch (IOException e) {
			fail("IOException al leer el fichero");
		}
	}
	
	@Test
	void testFicheroNoEncontrado() {
		assertThrows(NoSuchFileException.class, 
				() -> FileManager.getPassword("H:/__PROYECTOS_TARDE/passwords/pruebaMal.txt"));
	}

}
