/**
* SHA-2 es un conjunto de funciones hash criptográficas (SHA-224, SHA-256, 
* SHA-384, SHA-512) diseñadas por la Agencia de Seguridad Nacional (NSA) y 
* publicada en 2001 por el Instituto Nacional de Estándares y Tecnología (NIST) 
* como un Estándar Federal de Procesamiento de la Información (FIPS).
* 
* Una función hash es un algoritmo que transforma ("digiere") un conjunto 
* arbitrario de elementos de datos, como puede ser un fichero de texto, en un 
* único valor de longitud fija (el "hash"). El valor hash calculado puede ser 
* utilizado para la verificación de la integridad de copias de un dato original 
* sin la necesidad de proveer el dato original. Esta irreversibilidad significa 
* que un valor hash puede ser libremente distribuido o almacenado, ya que sólo 
* se utiliza para fines de comparación. SHA significa algoritmo de hash seguro. 
* SHA-2 incluye un significante número de cambios respecto a su predecesor, 
* SHA-1; y consiste en un conjunto de cuatro funciones hash de 224, 256, 384 o 
* 512 bits.
* 
* La seguridad proporcionada por un algoritmo hash es sumamente dependiente de 
* su capacidad de producir un único valor para un conjunto de datos dados. 
* Cuando una función hash produce el mismo valor para dos conjuntos de datos 
* distintos, entonces se dice que se ha producido una colisión. Una colisión 
* aumenta la posibilidad de que un atacante pueda elaborar computacionalmente 
* conjuntos de datos que proporcionen acceso a información segura o para 
* alterar ficheros de datos informáticos de tal forma que no cambiara el valor 
* hash resultante y así eludir la detección. Una función hash fuerte es aquella 
* que es resistente a este tipo de ataques computacionales mientras que una 
* función hash débil es aquella donde existe una creencia casi certera de que 
* se pueden producir colisiones. Finalmente, una función hash quebrantada es 
* aquella sobre la que se conoce métodos computacionales para producir 
* colisiones.
* 
* En 2005, se identificaron fallas de seguridad en el SHA-1, permitiendo que 
* existiera una debilidad matemática y evidenciando así la necesidad de una 
* elaborar una función hash más fuerte.1​ Aunque el SHA-2 se comporta de forma 
* parecida al algoritmo SHA-1, estos ataques no han sido extendidos 
* satisfactoriamente a SHA-2.
* 
* https://es.wikipedia.org/wiki/SHA-2
* 
*/

package com.fernandopaniagua.gestorcredenciales.security;
 
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * Proporciona mecanimos para cifrado y validación de contraseñas 
 *  
 * @author fpaniagua
 */
public class DigestManager {
    public static final String SHA_224 = "SHA-224";
    public static final String SHA_256 = "SHA-256";
    public static final String SHA_384 = "SHA-384";
    public static final String SHA_512 = "SHA-512";
    
    public static byte[] getMessageDigest(String _password) {
        byte[] digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance(SHA_256);
            md.update(_password.getBytes());
            digest = md.digest();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        } 
        return digest;
    }
    
    /*public static byte[] getMessageDigest(String message) {
        byte[] digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance(SHA_384);
            digest = md.digest(message.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        return digest;
    }*/
    public static boolean equalsDigest(byte[] digesta, byte[] digestb){
        return MessageDigest.isEqual(digesta, digestb);
    }
}
