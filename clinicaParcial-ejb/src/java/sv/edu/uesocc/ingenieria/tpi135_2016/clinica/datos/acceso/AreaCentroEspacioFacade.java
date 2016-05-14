/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.tpi135_2016.clinica.datos.acceso;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.edu.uesocc.ingenieria.tpi135_2016.clinica.datos.definiciones.AreaCentroEspacio;

/**
 *
 * @author gual
 */
@Stateless
public class AreaCentroEspacioFacade extends AbstractFacade<AreaCentroEspacio> implements AreaCentroEspacioFacadeLocal {

    @PersistenceContext(unitName = "clinicaParcial-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AreaCentroEspacioFacade() {
        super(AreaCentroEspacio.class);
    }
    
}
