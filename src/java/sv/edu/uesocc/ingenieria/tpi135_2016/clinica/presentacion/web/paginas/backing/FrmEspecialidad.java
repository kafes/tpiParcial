/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.tpi135_2016.clinica.presentacion.web.paginas.backing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import sv.edu.uesocc.ingenieria.tpi135_2016.clinica.datos.acceso.AbstractFacadeInterface;
import sv.edu.uesocc.ingenieria.tpi135_2016.clinica.datos.acceso.EspecialidadFacadeLocal;
import sv.edu.uesocc.ingenieria.tpi135_2016.clinica.datos.acceso.TipoEspecialidadFacadeLocal;
import sv.edu.uesocc.ingenieria.tpi135_2016.clinica.datos.definiciones.Especialidad;
import sv.edu.uesocc.ingenieria.tpi135_2016.clinica.datos.definiciones.TipoEspecialidad;

/**
 *
 * @author jcpenya
 */
@Named
@ViewScoped
public class FrmEspecialidad extends DefaultGenerador<Especialidad> implements Serializable {

    @EJB
    private EspecialidadFacadeLocal especialidadFacade;
    @EJB
    private TipoEspecialidadFacadeLocal tipoEspecialidadFacade;

    private List<TipoEspecialidad> tipoEspecialidadList;
    private LazyDataModel<Especialidad> modelo;
    private Especialidad registro;
    private int tamanioPagina;

    public Especialidad getRegistro() {
        return registro;
    }

    public void setRegistro(Especialidad registro) {
        this.registro = registro;
    }

    public int getTamanioPagina() {
        return tamanioPagina;
    }

    public void setTamanioPagina(int tamanioPagina) {
        this.tamanioPagina = tamanioPagina;
    }

    @Override
    public AbstractFacadeInterface<Especialidad> getFacade() {
        return this.especialidadFacade;
    }

    @Override
    public void generarListas() {
        super.generarListas(); //To change body of generated methods, choose Tools | Templates.
        if (this.tipoEspecialidadList == null || this.tipoEspecialidadList.isEmpty()) {
            try {
                if (this.tipoEspecialidadFacade != null) {
                    this.tipoEspecialidadList = tipoEspecialidadFacade.findAll();
                }
            } catch (Exception ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
            } finally {
                if (tipoEspecialidadList == null) {
                    this.tipoEspecialidadList = new ArrayList<>();
                }
            }
        }
    }

    @Override
    public void generarModeloTabla() {
        this.modelo = new LazyDataModel<Especialidad>() {

            @Override
            public Object getRowKey(Especialidad object) {
                if (object != null) {
                    return object.getIdEspecialidad();
                }
                return null;
            }

            @Override
            public Especialidad getRowData(String rowKey) {
                if (rowKey != null && !rowKey.isEmpty() && this.getWrappedData() != null) {
                    try {
                        Integer buscado = new Integer(rowKey);
                        for (Especialidad reg : ((List<Especialidad>) getWrappedData())) {
                            if (reg.getIdEspecialidad().compareTo(buscado) == 0) {
                                return reg;
                            }
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
                    }
                }
                return null;
            }

            @Override
            public List<Especialidad> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                List salida = new ArrayList();
                tamanioPagina = pageSize;
                try {
                    if (especialidadFacade != null) {
                        if (filters != null && !filters.isEmpty()) {
                            salida = especialidadFacade.findByDataFilters(filters, first, tamanioPagina);
                            this.setRowCount(especialidadFacade.countByDataFilters(filters));
                        } else {
                            salida = especialidadFacade.findRango(first, tamanioPagina);
                            this.setRowCount(especialidadFacade.count());
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
                }
                return salida;
            }

        };
    }

    @Override
    public Especialidad crearNuevo() {
        this.registro = new Especialidad();
        this.registro.setActivo(true);
        return this.registro;
    }

    @Override
    public Especialidad crearRegistro() {
        return this.registro;
    }

    @Override
    public LazyDataModel<Especialidad> getModelo() {
        return super.getModelo(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the tipoEspecialidadList
     */
    public List<TipoEspecialidad> getTipoEspecialidadList() {
        return tipoEspecialidadList;
    }

    /**
     * @param tipoEspecialidadList the tipoEspecialidadList to set
     */
    public void setTipoEspecialidadList(List<TipoEspecialidad> tipoEspecialidadList) {
        this.tipoEspecialidadList = tipoEspecialidadList;
    }

    public Integer getIdTipoEspecialidadSeleccionado() {
        if (this.registro != null && this.registro.getIdTipoEspecialidad() != null) {
            return this.registro.getIdTipoEspecialidad().getIdTipoEspecialidad();
        }
        return null;
    }

    public void setIdTipoEspecialidadSeleccionado(Integer idTipo) {
        if (this.registro != null && this.tipoEspecialidadList != null && idTipo != null) {
            this.registro.setIdTipoEspecialidad(null);
            for (TipoEspecialidad te : this.tipoEspecialidadList) {
                if (te.getIdTipoEspecialidad().compareTo(idTipo) == 0) {
                    this.registro.setIdTipoEspecialidad(te);
                    return;
                }
            }
        }
    }
}