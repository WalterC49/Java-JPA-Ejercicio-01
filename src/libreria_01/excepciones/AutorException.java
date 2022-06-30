/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria_01.excepciones;

/**
 *
 * @author Walter
 */
public class AutorException extends Exception {

    /**
     * Creates a new instance of <code>AutorException</code> without detail
     * message.
     */
    public AutorException() {
    }

    /**
     * Constructs an instance of <code>AutorException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public AutorException(String msg) {
        super(msg);
    }
}
