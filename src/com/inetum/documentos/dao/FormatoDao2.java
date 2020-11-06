package com.inetum.documentos.dao;



import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.inetum.documentos.entity.Formato;

@Stateless
public class FormatoDao2 implements Serializable{
    
    @PersistenceContext(unitName = "documentosPU")
    private EntityManager em;

    
    public FormatoDao2() {
    }

    public void save(Formato entity) {
        try {
            em.persist(entity);            
        } catch (Exception e) {
            e.printStackTrace();
        }
     }
    
}
