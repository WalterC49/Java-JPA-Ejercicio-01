/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria_01.servicios;

import java.util.Collection;
import java.util.List;
import static libreria_01.Libreria_01.leer;
import libreria_01.entidades.Autor;
import libreria_01.entidades.Editorial;
import libreria_01.entidades.Libro;
import libreria_01.excepciones.LibroException;
import libreria_01.persistencia.LibroDAO;
import static libreria_01.servicios.AutorServicio.seleccionarAutor;
import static libreria_01.servicios.EditorialServicio.seleccionarEditorial;

/**
 *
 * @author Walter
 */
public class LibroServicio {
    
    private static LibroDAO dao = new LibroDAO();
    
    public static void ingresarLibro() throws Exception{
        
        try {
            Libro libro = new Libro();
            
            String titulo = ingresarTitulo();
            libro.setTitulo(titulo);
            
            int anio = ingresarAnio();
            libro.setAnio(anio);
            
            int ejem = ingresarEjemTot();
            libro.setEjemplares(ejem);
            libro.setEjemplaresPrestados(0);
            libro.setEjemplaresRestantes(ejem);
            
            libro.setAlta(true);
            Autor autor = seleccionarAutor();
            libro.setAutor(autor);
            
            Editorial editorial = seleccionarEditorial();
            libro.setEditorial(editorial);
            dao.guardar(libro);
        } catch (Exception e) {
            throw e;
        }
    }
    
    private static String ingresarTitulo() throws Exception{
        try {
            System.out.print("\nIngrese el título del libro: ");
            String titulo=leer.next();
            validarTitulo(titulo);
            return titulo;
        } catch (Exception e) {
            throw e;
        }
    }
    
    private static int ingresarAnio() throws Exception{
        try {
            System.out.print("Ingrese el año de publicación: ");
            int anio=leer.nextInt();
            validarAnio(anio);
            return anio;
        } catch (Exception e) {
            throw e;
        }
    }
    
    private static int ingresarEjemTot() throws Exception{
        try {
            System.out.print("Cant. de ejemplares totales: ");
            int ejem=leer.nextInt();
            validarEjemTot(ejem);
            return ejem;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public static Libro seleccionarLibro() throws Exception{
        Libro libro = null;
        Collection<Libro> libros = listarLibros();
        
        try {
            System.out.println("\nElija el ISBN de un libro:");
            mostrarLibros();
            System.out.print("\nISBN: ");
            String aux = leer.next();
            int respuesta = Integer.parseInt(aux);
            
            if(respuesta>libros.size() | respuesta<0){
                throw new LibroException("Ese Id no existe.");
            }
            
            for (Libro li : libros) {
                if(li.getIsbn()== respuesta){
                    libro = li;
                }
            }
            return libro;
        } catch (NumberFormatException e){
            System.out.println("Ingreso no valido, intenta de vuelta.");
            return libro=seleccionarLibro();
        } catch (LibroException e){
            e.getMessage();
            return libro=seleccionarLibro();
        } catch (Exception e) {
            throw e;
        }
    }
    
    private static Collection<Libro> listarLibros() throws Exception{
        
        try {
            Collection<Libro> libros = dao.listarLibros();
            return libros;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public static void mostrarLibros() throws Exception {
        
        try {
            Collection<Libro> libros = listarLibros();
            
            if (libros.isEmpty()) {
                throw new Exception("No existen libros para imprimir.");
            }else {
                for (Libro li : libros) {
                    System.out.println("\n"+li);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public static void darDeAltaLibro() throws Exception{
        
        try {
            Libro libro = seleccionarLibro();
            libro.setAlta(true);
            dao.editar(libro);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public static void darDeBajaLibro() throws Exception{
        
        try {
            Libro libro = seleccionarLibro();
            libro.setAlta(false);
            dao.editar(libro);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public static void editarLibro() throws Exception{
        
        try {
            Libro libro = seleccionarLibro();
            System.out.println("\nLibro seleccionado:");
            System.out.println(libro);
            
            boolean continuar;
            do {                
                continuar=false;
                
                System.out.println("\nSeleccione el dato a modificar:"
                        + "\n0- ISBN"
                        + "\n1- Título"
                        + "\n2- Año"
                        + "\n3- Ejemplares totales"
                        + "\n4- Ejemplares prestados"
                        + "\n5- Ejemplares restantes"
                        + "\n6- Autor"
                        + "\n7- Editorial"
                        + "\n8- Volver al menú");
                System.out.print("Opción: ");
                String respuesta = leer.next();
                
                switch (respuesta) {
                    case "0":
                        editarISBN(libro);
                        break;
                    case "1":
                        editarTitulo(libro);
                        break;
                    case "2":
                        editarAnio(libro);
                        break;
                    case "3":
                        editarEjemTot(libro);
                        break;
                    case "4":
                        editarEjemPres(libro);
                        break;
                    case "5":
                        editarEjemRes(libro);
                        break;
                    case "6":
                        editarAutor(libro);
                        break;
                    case "7":
                        editarEditorial(libro);
                        break;
                    case "8":
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
    
    private static void editarISBN(Libro libro) throws Exception{
        
        try {
            System.out.println("\nLibro seleccionado:");
            System.out.println("\n"+libro);
            System.out.print("\nNuevo ISBN: ");
            long isbn = leer.nextLong();
            validarISBN(libro, isbn);
            libro.setIsbn(isbn);
            dao.editar(libro);
        } catch (Exception e) {
            throw e;
        }
    }
    
    private static void editarTitulo(Libro libro) throws Exception{
        
        try {
            System.out.println("\nLibro seleccionado:");
            System.out.println("\n"+libro);
            System.out.print("\nNuevo título: ");
            String titulo = leer.next();
            validarTitulo(libro, titulo);
            
            libro.setTitulo(titulo);
            dao.editar(libro);
        } catch (Exception e) {
            throw e;
        }
    }
    
    private static void editarAnio(Libro libro) throws Exception{
        
        try {
            System.out.println("\nLibro seleccionado:");
            System.out.println("\n"+libro);
            System.out.print("\nNuevo año: ");
            int anio = leer.nextInt();
            validarAnio(libro, anio);
            
            libro.setAnio(anio);
            dao.editar(libro);
        } catch (Exception e) {
            throw e;
        }
    }
    
    private static void editarEjemTot(Libro libro) throws Exception{
        
        try {
            System.out.println("\nLibro seleccionado:");
            System.out.println("\n"+libro);
            System.out.print("\nNueva cant. de ejemplares: ");
            int ejem = leer.nextInt();
            validarEjemTot(libro, ejem);
            
            libro.setEjemplares(ejem);
            libro.setEjemplaresRestantes(ejem+libro.getEjemplaresRestantes());
            dao.editar(libro);
        } catch (Exception e) {
            throw e;
        }
    }
    
    private static void editarEjemPres(Libro libro) throws Exception{
        
        try {
            System.out.println("\nLibro seleccionado:");
            System.out.println("\n"+libro);
            System.out.print("\nNueva cant. de ejemplares prestados: ");
            int ejem = leer.nextInt();
            validarEjemPres(libro, ejem);
            
            if (ejem>libro.getEjemplaresRestantes()) {
                System.out.println("Intenta otro valor.");
                editarEjemPres(libro);
            }
            
            libro.setEjemplaresPrestados(ejem);
            libro.setEjemplaresRestantes(libro.getEjemplaresRestantes()-ejem);
            dao.editar(libro);
        } catch (Exception e) {
            throw e;
        }
    }
    
    private static void editarEjemRes(Libro libro) throws Exception{
        
        try {
            System.out.println("\nLibro seleccionado:");
            System.out.println("\n"+libro);
            System.out.print("\nNueva cant. de ejemplares restantes: ");
            int ejem = leer.nextInt();
            validarEjemRes(libro, ejem);
            
            libro.setEjemplaresRestantes(ejem);
            libro.setEjemplares(ejem+libro.getEjemplares());
            dao.editar(libro);
        } catch (Exception e) {
            throw e;
        }
    }
    
    private static void editarAutor(Libro libro) throws Exception{
        
        try {
            System.out.println("\nLibro seleccionado:");
            System.out.println("\n"+libro);
            Autor autor = seleccionarAutor();
            libro.setAutor(autor);
            dao.editar(libro);
        } catch (Exception e) {
            throw e;
        }
    }
    
    private static void editarEditorial(Libro libro) throws Exception{
        
        try {
            System.out.println("\nLibro seleccionado:");
            System.out.println("\n"+libro);
            Editorial editorial  = seleccionarEditorial();
            libro.setEditorial(editorial);
            dao.editar(libro);
        } catch (Exception e) {
            throw e;
        }
    }
    
    private static void validarISBN(Libro libro, Long isbn) throws Exception{
        
        try {
            Collection<Libro> libros = listarLibros();
            
            if (isbn == null) {
                System.out.println("Ese valor no es valido.");
                editarISBN(libro);
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    private static void validarTitulo(String nombre) throws Exception{
        
        try {
            Collection<Libro> libros = listarLibros();
            
            if (nombre.trim().isEmpty()) {
                System.out.println("Ese valor no es valido.");
                ingresarTitulo();
            }
            
            boolean repetido=false;
            for (Libro li : libros) {
                if (li.getTitulo().equals(nombre)) {
                    System.out.println("Ese valor ya existe.");
                    repetido=true;
                    break;
                }
            }
            if (repetido) {
                ingresarTitulo();
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    private static void validarTitulo(Libro libro, String nombre) throws Exception{
        
        try {
            Collection<Libro> libros = listarLibros();
            
            if (nombre.trim().isEmpty()) {
                System.out.println("Ese valor no es valido.");
                editarTitulo(libro);
            }
            
            boolean repetido=false;
            for (Libro li : libros) {
                if (li.getTitulo().equals(nombre)) {
                    System.out.println("Ese valor ya existe.");
                    repetido=true;
                    break;
                }
            }
            if (repetido) {
                editarTitulo(libro);
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    private static void validarAnio(Integer anio) throws Exception{
        
        try {
            Collection<Libro> libros = listarLibros();
            
            if (anio == null) {
                System.out.println("Ese valor no es valido.");
                ingresarAnio();
            }
            
            boolean repetido=false;
            for (Libro li : libros) {
                if (li.getAnio().equals(anio)) {
                    System.out.println("Ese valor ya existe.");
                    repetido=true;
                    break;
                }
            }
            if (repetido) {
                ingresarAnio();
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    private static void validarAnio(Libro libro, Integer anio) throws Exception{
        
        try {
            Collection<Libro> libros = listarLibros();
            
            if (anio == null) {
                System.out.println("Ese valor no es valido.");
                editarAnio(libro);
            }
            
            boolean repetido=false;
            for (Libro li : libros) {
                if (li.getAnio().equals(anio)) {
                    System.out.println("Ese valor ya existe.");
                    repetido=true;
                    break;
                }
            }
            if (repetido) {
                editarAnio(libro);
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    private static void validarEjemTot(Integer ejem) throws Exception{
        
        try {
            Collection<Libro> libros = listarLibros();
            
            if (ejem == null || ejem <= 0) {
                System.out.println("Ese valor no es valido.");
                ingresarEjemTot();
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    private static void validarEjemTot(Libro libro, Integer ejem) throws Exception{
        
        try {
            Collection<Libro> libros = listarLibros();
            
            if (ejem == null || ejem <= 0) {
                System.out.println("Ese valor no es valido.");
                editarEjemTot(libro);
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    private static void validarEjemPres(Libro libro, Integer ejem) throws Exception{
        
        try {
            Collection<Libro> libros = listarLibros();
            
            if (ejem == null || ejem < 0) {
                System.out.println("Ese valor no es valido.");
                editarEjemPres(libro);
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    private static void validarEjemRes(Libro libro, Integer ejem) throws Exception{
        
        try {
            Collection<Libro> libros = listarLibros();
            
            if (ejem == null || ejem < 0) {
                System.out.println("Ese valor no es valido.");
                editarEjemRes(libro);
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public static void buscarLibroPorISBN(){
        try {
            System.out.print("\nIngrese el isbn del libro a buscar: ");
            long isbn = leer.nextLong();
            Libro libro = dao.buscarPorISBN(isbn);
            if (libro == null) {
                System.out.println("No se encontró ese libro");
            } else {
                System.out.println("Libro:");
                System.out.println(libro);
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public static void buscarLibroPorTitulo(){
        try {
            System.out.print("\nIngrese el título del libro a buscar: ");
            String titulo = leer.next();
            Libro libro = dao.buscarPorTitulo(titulo);
            if (libro == null) {
                System.out.println("No se encontró ese libro");
            } else {
                System.out.println("Libro:");
                System.out.println(libro);
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public static void buscarLibrosPorNombreAutor(){
        
        try {
            System.out.print("\nIngrese el nombre del autor del libro: ");
            String nombre=leer.next();
            List<Libro> libros = dao.buscarPorNombreAutor(nombre);
            if (libros.isEmpty()) {
                System.out.println("No se encontraron libros con ese autor");
            } else {
                System.out.println("\nLibros del autor: "+nombre);
                for (Libro li : libros) {
                    System.out.println("\n"+li);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public static void buscarLibrosPorNombreEditorial(){
        
        try {
            System.out.print("\nIngrese el nombre de la editorial del libro: ");
            String nombre=leer.next();
            List<Libro> libros = dao.buscarPorNombreEditorial(nombre);
            if (libros.isEmpty()) {
                System.out.println("No se encontraron libros con esa editorial");
            } else {
                System.out.println("\nLibros de la editorial: "+nombre);
                for (Libro li : libros) {
                    System.out.println("\n"+li);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
}
