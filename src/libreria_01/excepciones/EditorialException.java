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
public class EditorialException extends Exception {

    /**
     * Creates a new instance of <code>EditorialException</code> without detail
     * message.
     */
    public EditorialException() {
    }

    /**
     * Constructs an instance of <code>EditorialException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public EditorialException(String msg) {
        super(msg);
    }
}
