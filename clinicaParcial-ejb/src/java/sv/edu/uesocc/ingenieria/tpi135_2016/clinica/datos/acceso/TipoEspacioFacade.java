/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.tpi135_2016.clinica.datos.acceso;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.edu.uesocc.ingenieria.tpi135_2016.clinica.datos.definiciones.TipoEspacio;

/**
 *
 * @author gual
 */
@Stateless
public class TipoEspacioFacade extends AbstractFacade<TipoEspacio> implements TipoEspacioFacadeLocal {

    @PersistenceContext(unitName = "clinicaParcial-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoEspacioFacade() {
        super(TipoEspacio.class);
    }
    
}
