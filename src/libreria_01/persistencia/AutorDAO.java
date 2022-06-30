/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria_01.persistencia;

import java.util.List;
import libreria_01.entidades.Autor;

/**
 *
 * @author Walter
 */
public class AutorDAO extends DAO<Autor>{
    
    @Override
    public void guardar(Autor autor){
        super.guardar(autor);
    }
    
    public List<Autor> listarAutores() throws Exception {
        conectar();
        List<Autor> autores = em.createQuery("SELECT a FROM Autor a")
                .getResultList();
        desconectar();
        return autores;
    }
    
    @Override
    public void editar(Autor autor) {
        super.editar(autor);
    }
    
    public Autor buscarPorNombre(String nombre){
        conectar();
        Autor autor = (Autor) em.createQuery("SELECT a FROM Autor a WHERE a.nombre LIKE :nombre")
                .setParameter("nombre", nombre).getSingleResult();
        desconectar();
        return autor;
    }
    
}
