package com.fernandopaniagua.gestorcredenciales.persistencia;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

/*
 * Clase de ayuda (Helper) para el almacenamiento y lectura de claves privadas
 * para su utilización en la implementación del algoritmo asimétrico RSA
 * 
 * @author fpaniagua
 */
public class FileManager {
    /**
     * Almacena la clave recibida en la ruta indicada (ej. c:/tmp/clave.dat).
     * 
     * @param pk PublicKey a almacenar
     * @param path Ruta donde almacenar la clave
     */
    public static void storePublicKey(PublicKey pk, String path){
        FileOutputStream fos  = null;
        try {
            fos = new FileOutputStream(path);
            fos.write(pk.getEncoded(),0,pk.getEncoded().length);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    /**
     * Recupera la clave pública almacenada en la ruta indicada
     * 
     * @param path Ruta donde leer la clave
     * @return Clave
     */
    public static PublicKey readPublicKey(String path){
        PublicKey pk = null;
        try {
            //Lectura de la clave
            Path fileLocation = Paths.get(path);
            byte[] publicData = Files.readAllBytes(fileLocation);
            System.out.println(publicData);
            //Reconstrucción de la calve
            KeyFactory kf = KeyFactory.getInstance("RSA");
            //PrivateKey prv_recovered = kf.generatePrivate(new PKCS8EncodedKeySpec(privateData));
            pk = kf.generatePublic(new X509EncodedKeySpec(publicData));
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        } catch (InvalidKeySpecException ex) {
            ex.printStackTrace();
        } 
        return pk;
    }
    
    /**
     * Almacena el resumen de una password en la ruta indicada
     * 
     * @param path Ruta de almacenamiento 
     * @param digest Resumen a almacenar
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    public static void storePassword(String path, byte[] digest) throws FileNotFoundException, IOException {
        FileOutputStream fos  = null;
        try {
            fos = new FileOutputStream(path);
            fos.write(digest,0,digest.length);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (IOException ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    /**
     * Lee un password has de la ruta indicada
     * 
     * @param path Ruta donde esta el hash
     * @return Constrasenya
     * @throws java.io.FileNotFoundException Indica que el fichero no se ha encontrado 
     */
    public static byte[] getPassword(String path) throws FileNotFoundException, IOException{
        byte[] digest = null;
        try {
            Path fileLocation = Paths.get(path);
            digest = Files.readAllBytes(fileLocation);
        } catch (IOException ex) {
            //ex.printStackTrace();
            throw ex;
        }
        return digest;
    }
}
