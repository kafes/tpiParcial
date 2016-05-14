package sv.edu.uesocc.ingenieria.tpi135_2016.clinica.presentacion.web.paginas.backing;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import sv.edu.uesocc.ingenieria.tpi135_2016.clinica.datos.acceso.ExamenFacadeLocal;
import sv.edu.uesocc.ingenieria.tpi135_2016.clinica.datos.acceso.TipoExamenFacadeLocal;
import sv.edu.uesocc.ingenieria.tpi135_2016.clinica.datos.definiciones.Examen;
import sv.edu.uesocc.ingenieria.tpi135_2016.clinica.datos.definiciones.TipoExamen;

/**
 *
 * @author kari
 */
@Named
@ViewScoped
public class FrmExamen extends DefaultGenerador<Examen> implements Serializable {

    @EJB
    private ExamenFacadeLocal examenFacade;
    @EJB
    private TipoExamenFacadeLocal tipoExamenFacade;
    private List<TipoExamen> tipoExamenList;
    private Examen registro;
    private LazyDataModel<Examen> modelo;
    private int tamanioPagina;

    @Override
    public AbstractFacadeInterface<Examen> getFacade() {
        return this.examenFacade;
    }

    @Override
    public void generarListas() {
        super.generarListas(); //To change body of generated methods, choose Tools | Templates.
        if (this.tipoExamenList == null || this.tipoExamenList.isEmpty()) {
            try {
                if (tipoExamenFacade != null) {
                    tipoExamenList = tipoExamenFacade.findAll();
                }
            } catch (Exception ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
            } finally {
                if (tipoExamenList == null) {
                    tipoExamenList = new ArrayList<>();
                }
            }
        }
    }

    @Override
    public void generarModeloTabla() {
        this.modelo = new LazyDataModel<Examen>() {

            @Override
            public Object getRowKey(Examen object) {
                if (object != null) {
                    return object.getIdExamen();
                }
                return null;
            }

            @Override
            public Examen getRowData(String rowKey) {
                if (rowKey != null && !rowKey.trim().isEmpty() && this.getWrappedData() != null) {
                    try {
                        Long buscado = new Long(rowKey);
                        for (Examen reg : ((List<Examen>) this.getWrappedData())) {
                            if (reg.getIdExamen().compareTo(buscado) == 0) {
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
            public List<Examen> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                List salida = new ArrayList();
                try {
                    if (examenFacade != null) {
                        if (filters != null && !filters.isEmpty()) {
                            salida = examenFacade.findByDataFilters(filters, first, pageSize);
                            this.setRowCount(examenFacade.countByDataFilters(filters));
                        } else {
                            salida = examenFacade.findRango(first, pageSize);
                            this.setRowCount(examenFacade.count());
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
    public Examen crearNuevo() {
        this.registro = new Examen();
        this.registro.setActivo(true);
        if (tipoExamenList != null && !tipoExamenList.isEmpty()) {
            this.registro.setIdTipoExamen(tipoExamenList.get(0));
        }
        return this.registro;
    }

    @Override
    public Examen crearRegistro() {
        return this.registro;
    }

    @Override
    public LazyDataModel<Examen> getModelo() {
        return super.getModelo(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the tipoExamenList
     */
    public List<TipoExamen> getTipoExamenList() {
        return tipoExamenList;
    }

    /**
     * @param tipoExamenList the tipoExamenList to set
     */
    public void setTipoExamenList(List<TipoExamen> tipoExamenList) {
        this.tipoExamenList = tipoExamenList;
    }

    public Integer getIdTipoExamen() {
        if (this.registro != null && this.registro.getIdTipoExamen() != null) {
            return this.registro.getIdTipoExamen().getIdTipoExamen();
        }
        return null;
    }

    public void setIdTipoExamen(Integer idTipoExamen) {
        if (this.tipoExamenList != null && !this.tipoExamenList.isEmpty()) {
            for (TipoExamen t : this.tipoExamenList) {
                if (t.getIdTipoExamen().compareTo(idTipoExamen) == 0) {
                    this.registro.setIdTipoExamen(t);
                }
            }
        }
    }

    public Examen getRegistro() {
        return registro;
    }

    public void setRegistro(Examen registro) {
        this.registro = registro;
    }

}
