/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.tpi135_2016.clinica.datos.acceso;

import java.util.List;
import java.util.Map;

/**
 *
 * @author gual
 */
public interface AbstractFacadeInterface<T> {

    boolean create(T entity);

    boolean edit(T entity);

    boolean remove(T entity);

    T find(Object id);

    List<T> findAll();

    List<T> findRange(int[] range);

    int count();

    List<T> findByDataFilters(Map<String, Object> filtros, int desde, int tamanioPagina);

    int countByDataFilters(Map<String, Object> filtros);

    List<T> findRango(int first, int tamanioPagina);
}
