/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria_01.persistencia;

import java.util.List;
import libreria_01.entidades.Libro;

/**
 *
 * @author Walter
 */
public class LibroDAO extends DAO<Libro>{
    
    @Override
    public void guardar(Libro libro){
        super.guardar(libro);
    }
    
    @Override
    public void editar(Libro libro) {
        super.editar(libro);
    }
    
    public List<Libro> listarLibros() throws Exception {
        conectar();
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l")
                .getResultList();
        desconectar();
        return libros;
    }
    
    public Libro buscarPorISBN(long isbn){
        conectar();
        Libro libro = (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.isbn LIKE :isbn")
                .setParameter("isbn", isbn).getSingleResult();
        desconectar();
        return libro;
    }
    
    public Libro buscarPorTitulo(String titulo){
        conectar();
        Libro libro = (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.titulo LIKE :titulo")
                .setParameter("titulo", titulo).getSingleResult();
        desconectar();
        return libro;
    }
    
    public List<Libro> buscarPorNombreAutor(String nombre){
        conectar();
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l JOIN l.autor a WHERE a.nombre LIKE :nombre")
                .setParameter("nombre", nombre).getResultList();
        desconectar();
        return libros;
    }
    
    public List<Libro> buscarPorNombreEditorial(String nombre){
        conectar();
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l JOIN l.editorial e WHERE e.nombre LIKE :nombre")
                .setParameter("nombre", nombre).getResultList();
        desconectar();
        return libros;
    }
    
    
}
