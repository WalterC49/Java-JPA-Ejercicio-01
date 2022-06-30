/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria_01.servicios;

import java.util.Collection;
import static libreria_01.Libreria_01.leer;
import libreria_01.entidades.Autor;
import libreria_01.excepciones.AutorException;
import libreria_01.persistencia.AutorDAO;

/**
 *
 * @author Walter
 */
public class AutorServicio {
    
    private static AutorDAO dao = new AutorDAO();
    
    public static void ingresarAutor() throws Exception{
        try {
            System.out.print("\nIngrese el nombre del autor: ");
            String nombre=leer.next();
            Autor autor = new Autor();
            validarNombre(nombre);
            autor.setNombre(nombre);
            autor.setAlta(true);
            dao.guardar(autor);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public static Autor seleccionarAutor() throws Exception{
        Autor autor = null;
        Collection<Autor> autores = listarAutores();
        
        try {
            System.out.println("\nElija el Id de un autor:");
            mostrarAutores();
            System.out.print("\nId: ");
            String aux = leer.next();
            int respuesta = Integer.parseInt(aux);
            
            if(respuesta>autores.size() | respuesta<0){
                throw new AutorException("Ese Id no existe.");
            }
            
            for (Autor au : autores) {
                if(au.getId()== respuesta){
                    autor = au;
                }
            }
            return autor;
        } catch (NumberFormatException e){
            System.out.println("Ingreso no valido, intenta de vuelta.");
            return autor=seleccionarAutor();
        } catch (AutorException e){
            e.getMessage();
            return autor=seleccionarAutor();
        } catch (Exception e) {
            throw e;
        }
    }
    
    private static Collection<Autor> listarAutores() throws Exception{
        
        try {
            Collection<Autor> autores = dao.listarAutores();
            return autores;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public static void mostrarAutores() throws Exception {
        
        try {
            Collection<Autor> autores = listarAutores();
            
            if (autores.isEmpty()) {
                throw new Exception("No existen autores para imprimir.");
            }else {
                for (Autor au : autores) {
                    System.out.println("\n"+au);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public static void darDeAltaAutor() throws Exception {
        
        try {
            Autor autor = seleccionarAutor();
            autor.setAlta(true);
            dao.editar(autor);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public static void darDeBajaAutor() throws Exception {
        
        try {
            Autor autor = seleccionarAutor();
            autor.setAlta(false);
            dao.editar(autor);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public static void editarAutor() throws Exception{
        
        try {
            Autor autor = seleccionarAutor();
            System.out.println("\nAutor seleccionado:");
            System.out.println("\n"+autor);
            
            boolean continuar;
            do {                
                continuar=false;
                
                System.out.println("\nSeleccione el dato a modificar:"
                        + "\n1- Id"
                        + "\n2- Nombre"
                        + "\n3- Volver al menú");
                System.out.print("Opción: ");
                String respuesta = leer.next();
                
                switch (respuesta) {
                    case "1":
                        editarId(autor);
                        break;
                    case "2":
                        editarNombre(autor);
                        break;
                    case "3":
                        break;
                    default:
                        System.out.println("Opción no valida.");
                        continuar=true;
                }
            } while (continuar);
        } catch (Exception e) {
            throw e;
        }
    }
    
    private static void editarId(Autor autor) throws Exception{
        
        try {
            System.out.println("\nAutor seleccionado:");
            System.out.println("\n"+autor);
            System.out.print("\nNuevo Id: ");
            int id = leer.nextInt();
            validarId(autor, id);
            autor.setId(id);
            dao.editar(autor);
        } catch (Exception e) {
            throw e;
        }
    }
    
    private static void editarNombre(Autor autor) throws Exception{
        
        try {
            System.out.println("\nAutor seleccionado:");
            System.out.println("\n"+autor);
            System.out.print("\nNuevo nombre: ");
            String nombre = leer.next();
            validarNombre(autor, nombre);
            autor.setNombre(nombre);
            dao.editar(autor);
        } catch (Exception e) {
            throw e;
        }
    }
    
    private static void validarId(Autor autor, Integer id) throws Exception{
        
        try {
            Collection<Autor> autores = listarAutores();
            
            if (id == null) {
                System.out.println("Ese valor no es valido.");
                editarId(autor);
            }
            
            boolean repetido=false;
            for (Autor au : autores) {
                if (au.getId().equals(id)) {
                    System.out.println("Ese valor ya existe.");
                    repetido=true;
                    break;
                }
            }
            if (repetido) {
                editarId(autor);
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    private static void validarNombre(String nombre) throws Exception{
        
        try {
            Collection<Autor> autores = listarAutores();
            
            if (nombre.trim().isEmpty()) {
                System.out.println("Ese valor no es valido.");
                ingresarAutor();
            }
            
            boolean repetido=false;
            for (Autor au : autores) {
                if (au.getNombre().equals(nombre)) {
                    System.out.println("Ese valor ya existe.");
                    repetido=true;
                    break;
                }
            }
            if (repetido) {
                ingresarAutor();
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    private static void validarNombre(Autor autor, String nombre) throws Exception{
        
        try {
            Collection<Autor> autores = listarAutores();
            
            if (nombre.trim().isEmpty()) {
                System.out.println("Ese valor no es valido.");
                ingresarAutor();
            }
            
            boolean repetido=false;
            for (Autor au : autores) {
                if (au.getNombre().equals(nombre)) {
                    System.out.println("Ese valor ya existe.");
                    repetido=true;
                    break;
                }
            }
            if (repetido) {
                editarNombre(autor);
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public static void buscarAutorPorNombre(){
        
        try {
            System.out.print("\nIngrese el nombre del autor a buscar: ");
            String nombre = leer.next();
            Autor autor = dao.buscarPorNombre(nombre);
            if (autor == null) {
                System.out.println("No se encontró ese autor");
            } else {
                System.out.println("Autor:");
                System.out.println(autor);
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
