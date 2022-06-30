/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria_01.persistencia;

import java.util.List;
import libreria_01.entidades.Editorial;

/**
 *
 * @author Walter
 */
public class EditorialDAO extends DAO<Editorial>{
    
    @Override
    public void guardar(Editorial editorial){
        super.guardar(editorial);
    }
    
    public List<Editorial> listarEditoriales() throws Exception {
        conectar();
        List<Editorial> autores = em.createQuery("SELECT e FROM Editorial e")
                .getResultList();
        desconectar();
        return autores;
    }
    
    @Override
    public void editar(Editorial editorial){
        super.editar(editorial);
    }
}
