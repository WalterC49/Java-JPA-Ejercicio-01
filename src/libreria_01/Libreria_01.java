/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria_01;

import java.util.Scanner;
import libreria_01.servicios.AutorServicio;
import libreria_01.servicios.EditorialServicio;
import libreria_01.servicios.LibroServicio;

/**
 *
 * @author Walter
 */
public class Libreria_01 {

    /**
     * @param args the command line arguments
     */
    
    public static Scanner leer = new Scanner(System.in).useDelimiter("\n");
    
    public static void main(String[] args) throws Exception {
        try {
            Menu();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public static void Menu()throws Exception{
        System.out.println("\nMenú:"
                + "\nA- Ingresar un Autor"
                + "\nB- Ingresar una editorial"
                + "\nC- Ingresar un Libro"
                + "\nD- Dar de alta/baja (Autor/Editorial/Libro)"
                + "\nE- Editar (Autor/Editorial/Libro)"
                + "\nF- Buscar un autor por su nombre"
                + "\nG- Buscar un libro por su ISBN"
                + "\nH- Buscar un libro por su título"
                + "\nI- Buscar un libro/s por nombre de autor"
                + "\nJ- Buscar un libro/s por nombre de editorial"
                + "\nK- Salir del programa");
        System.out.print("Opción: ");
        String op = leer.next().toUpperCase();
        
        try {
            switch (op) {
            case "A":
                AutorServicio.ingresarAutor();
                Menu();
                break;
            case "B":
                EditorialServicio.ingresarEditorial();
                Menu();
                break;
            case "C":
                LibroServicio.ingresarLibro();
                Menu();
                break;
            case "D":
                darAltaBaja();
                Menu();
                break;
            case "E":
                editar();
                Menu();
                break;
            case "F":
                AutorServicio.buscarAutorPorNombre();
                Menu();
                break;
            case "G":
                LibroServicio.buscarLibroPorISBN();
                Menu();
                break;
            case "H":
                LibroServicio.buscarLibroPorTitulo();
                Menu();
                break;
            case "I":
                LibroServicio.buscarLibrosPorNombreAutor();
                Menu();
                break;
            case "J":
                LibroServicio.buscarLibrosPorNombreEditorial();
                Menu();
                break;
            case "K":
                System.out.println("\nHasta luego.");
                break;
            default:
                System.out.println("\nOpción no valida.");
                Menu();
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    private static void darAltaBaja() throws Exception{
        
        try {
            System.out.println("\nSeleccione que quiere dar de alta/baja:"
                    + "\n1- Autor"
                    + "\n2- Editorial"
                    + "\n3- Libro"
                    + "\n4- Volver al menú principal");
            System.out.print("Opción: ");
            String respuesta = leer.next();
            switch (respuesta) {
                case "1":
                    darAltaBajaAutor();
                    break;
                case "2":
                    darAltaBajaEditorial();
                    break;
                case "3":
                    darAltaBajaLibro();
                    break;
                case "4":
                    Menu();
                    break;
                default:
                    System.out.println("Opción no valida.");
                    darAltaBaja();
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    private static void darAltaBajaAutor() throws Exception{
        try {
            System.out.println("\n¿Quiere dar de alta o baja?:"
                    + "\n1- Alta"
                    + "\n2- Baja"
                    + "\n3- Volver al menú anterior");
            System.out.print("Opción: ");
            String respuesta = leer.next();
            switch (respuesta) {
                case "1":
                    AutorServicio.darDeAltaAutor();
                    break;
                case "2":
                    AutorServicio.darDeBajaAutor();
                    break;
                case "3":
                    darAltaBaja();
                    break;
                default:
                    System.out.println("Opción no valida.");
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    private static void darAltaBajaEditorial() throws Exception{
        try {
            System.out.println("\n¿Quiere dar de alta o baja?:"
                    + "\n1- Alta"
                    + "\n2- Baja"
                    + "\n3- Volver al menú anterior");
            System.out.print("Opción");
            String respuesta = leer.next();
            switch (respuesta) {
                case "1":
                    EditorialServicio.darDeAltaEditorial();
                    break;
                case "2":
                    EditorialServicio.darDeBajaEditorial();
                    break;
                case "3":
                    darAltaBaja();
                    break;
                default:
                    System.out.println("Opción no valida.");
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    private static void darAltaBajaLibro() throws Exception{
        try {
            System.out.println("\n¿Quiere dar de alta o baja?:"
                    + "\n1- Alta"
                    + "\n2- Baja"
                    + "\n3- Volver al menú anterior");
            System.out.print("Opción: ");
            String respuesta = leer.next();
            switch (respuesta) {
                case "1":
                    LibroServicio.darDeAltaLibro();
                    break;
                case "2":
                    LibroServicio.darDeBajaLibro();
                    break;
                case "3":
                    darAltaBaja();
                    break;
                default:
                    System.out.println("Opción no valida.");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private static void editar() throws Exception{
        
        try {
            System.out.println("\nSeleccione que quiere editar:"
                    + "\n1- Autor"
                    + "\n2- Editorial"
                    + "\n3- Libro"
                    + "\n4- Volver al menú principal");
            System.out.print("Opción: ");
            String respuesta = leer.next();
            switch (respuesta) {
                case "1":
                    AutorServicio.editarAutor();
                    break;
                case "2":
                    EditorialServicio.editarEditorial();
                    break;
                case "3":
                    LibroServicio.editarLibro();
                    break;
                case "4":
                    Menu();
                    break;
                default:
                    System.out.println("Opción no valida.");
                    editar();
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
