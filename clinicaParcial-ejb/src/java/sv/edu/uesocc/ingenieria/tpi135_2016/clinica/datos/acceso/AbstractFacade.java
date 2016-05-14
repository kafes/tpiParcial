/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.tpi135_2016.clinica.datos.acceso;

import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;

/**
 *
 * @author gual
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;
    private List<T> listaFiltrada;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public boolean create(T entity) {
        boolean resultado = false;
        T entidad = crear(entity);
        if (entidad != null) {
            resultado = true;
        }
        return resultado;
    }

    public T crear(T entity) {
        T resultado = null;
        try {
            resultado = entity;
            EntityManager em = getEntityManager();
            if (resultado != null && em != null) {
                em.persist(entity);
            } else {
                resultado = null;
            }
        } catch (Exception e) {
        }
        return resultado;
    }

    public boolean edit(T entity) {
        boolean resultado = false;
        T entidad = editar(entity);
        if (entidad != null) {
            resultado = true;
        }
        return resultado;
    }

    public T editar(T entity) {
        T resultado = null;
        try {
            resultado = entity;
            EntityManager em = getEntityManager();
            if (resultado != null && em != null) {
                em.merge(entity);
            } else {
                resultado = null;
            }
        } catch (Exception e) {
        }
        return resultado;
    }

    public boolean remove(T entity) {
        boolean resultado = false;
        T entidad = eliminar(entity);
        if (entidad != null) {
            resultado = true;
        }
        return resultado;
    }

    public T eliminar(T entity) {
        T resultado = null;
        try {
            resultado = entity;
            EntityManager em = getEntityManager();
            if (resultado != null && em != null) {
                em.remove(em.merge(entity));
            } else {
                resultado = null;
            }
        } catch (Exception e) {
        }
        return resultado;
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public List<T> findByDataFilters(Map<String, Object> filtros, int desde, int tamanioPagina) {
        return null;
    }

    public int countByDataFilters(Map<String, Object> filtros) {
        return 0;
    }

    public List<T> findRango(int first, int tamanioPagina) {
        return null;
    }
}
