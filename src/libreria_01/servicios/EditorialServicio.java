/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria_01.servicios;

import java.util.Collection;
import static libreria_01.Libreria_01.leer;
import libreria_01.entidades.Editorial;
import libreria_01.excepciones.EditorialException;
import libreria_01.persistencia.EditorialDAO;

/**
 *
 * @author Walter
 */
public class EditorialServicio {
    
    private static EditorialDAO dao = new EditorialDAO();
    
    public static void ingresarEditorial() throws Exception{
        try {
            System.out.print("\nIngrese el nombre de la editorial: ");
            String nombre=leer.next();
            validarNombre(nombre);
            Editorial editorial = new Editorial();
            editorial.setNombre(nombre);
            editorial.setAlta(true);
            dao.guardar(editorial);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public static Editorial seleccionarEditorial() throws Exception{
        Editorial editorial = null;
        Collection<Editorial> editoriales = listarEditoriales();
        
        try {
            System.out.println("\nElija el Id de una editorial:");
            mostrarEditoriales();
            System.out.print("\nId: ");
            String aux = leer.next();
            int respuesta = Integer.parseInt(aux);
            
            if(respuesta>editoriales.size() | respuesta<0){
                throw new EditorialException("Ese Id no existe.");
            }
            
            for (Editorial ed : editoriales) {
                if(ed.getId()== respuesta){
                    editorial = ed;
                }
            }
            return editorial;
        } catch (NumberFormatException e){
            System.out.println("Ingreso no valido, intenta de vuelta.");
            return editorial=seleccionarEditorial();
        } catch (EditorialException e){
            e.getMessage();
            return editorial=seleccionarEditorial();
        } catch (Exception e) {
            throw e;
        }
    }
    
    private static Collection<Editorial> listarEditoriales() throws Exception{
        
        try {
            Collection<Editorial> editoriales = dao.listarEditoriales();
            return editoriales;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public static void mostrarEditoriales() throws Exception {
        
        try {
            Collection<Editorial> editoriales = listarEditoriales();
            
            if (editoriales.isEmpty()) {
                throw new Exception("No existen editoriales para imprimir.");
            }else {
                for (Editorial ed : editoriales) {
                    System.out.println("\n"+ed);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public static void darDeAltaEditorial() throws Exception{
        
        try {
            Editorial editorial = seleccionarEditorial();
            editorial.setAlta(true);
            dao.editar(editorial);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public static void darDeBajaEditorial() throws Exception{
        
        try {
            Editorial editorial = seleccionarEditorial();
            editorial.setAlta(false);
            dao.editar(editorial);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public static void editarEditorial() throws Exception{
        
        try {
            Editorial editorial = seleccionarEditorial();
            System.out.println("\nEditorial seleccionada:");
            System.out.println("\n"+editorial);
            
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
                        editarId(editorial);
                        break;
                    case "2":
                        editarNombre(editorial);
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
    
    private static void editarId(Editorial editorial) throws Exception{
        
        try {
            System.out.println("\nEditorial seleccionada:");
            System.out.println("\n"+editorial);
            System.out.print("\nNuevo Id: ");
            int id = leer.nextInt();
            validarId(editorial, id);
            editorial.setId(id);
            dao.editar(editorial);
        } catch (Exception e) {
            throw e;
        }
    }
    
    private static void editarNombre(Editorial editorial) throws Exception{
        
        try {
            System.out.println("\nEditorial seleccionada:");
            System.out.println("\n"+editorial);
            System.out.print("\nNuevo nombre: ");
            String nombre = leer.next();
            validarNombre(editorial, nombre);
            editorial.setNombre(nombre);
            dao.editar(editorial);
        } catch (Exception e) {
            throw e;
        }
    }
    
    private static void validarId(Editorial editorial, Integer id) throws Exception{
        
        try {
            Collection<Editorial> editoriales = listarEditoriales();
            
            if (id == null) {
                System.out.println("Ese valor no es valido.");
                editarId(editorial);
            }
            
            boolean repetido=false;
            for (Editorial ed : editoriales) {
                if (ed.getId().equals(id)) {
                    System.out.println("Ese valor ya existe.");
                    repetido=true;
                    break;
                }
            }
            if (repetido) {
                editarId(editorial);
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    private static void validarNombre(String nombre) throws Exception{
        
        try {
            Collection<Editorial> editoriales = listarEditoriales();
            
            if (nombre.trim().isEmpty()) {
                System.out.println("Ese valor no es valido.");
                ingresarEditorial();
            }
            
            boolean repetido=false;
            for (Editorial ed : editoriales) {
                if (ed.getNombre().equals(nombre)) {
                    System.out.println("Ese valor ya existe.");
                    repetido=true;
                    break;
                }
            }
            if (repetido) {
                ingresarEditorial();
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    private static void validarNombre(Editorial editorial, String nombre) throws Exception{
        
        try {
            Collection<Editorial> editoriales = listarEditoriales();
            
            if (nombre.trim().isEmpty()) {
                System.out.println("Ese valor no es valido.");
                editarNombre(editorial);
            }
            
            boolean repetido=false;
            for (Editorial ed : editoriales) {
                if (ed.getNombre().equals(nombre)) {
                    System.out.println("Ese valor ya existe.");
                    repetido=true;
                    break;
                }
            }
            if (repetido) {
                editarNombre(editorial);
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
