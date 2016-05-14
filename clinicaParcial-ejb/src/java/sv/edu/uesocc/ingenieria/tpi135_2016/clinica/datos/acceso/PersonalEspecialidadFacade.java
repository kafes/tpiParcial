/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.tpi135_2016.clinica.datos.acceso;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.edu.uesocc.ingenieria.tpi135_2016.clinica.datos.definiciones.PersonalEspecialidad;

/**
 *
 * @author gual
 */
@Stateless
public class PersonalEspecialidadFacade extends AbstractFacade<PersonalEspecialidad> implements PersonalEspecialidadFacadeLocal {

    @PersistenceContext(unitName = "clinicaParcial-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonalEspecialidadFacade() {
        super(PersonalEspecialidad.class);
    }
    
}
