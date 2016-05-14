package sv.edu.uesocc.ingenieria.tpi135_2016.clinica.presentacion.web.paginas.backing;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import sv.edu.uesocc.ingenieria.tpi135_2016.clinica.datos.acceso.PacienteFacadeLocal;
import sv.edu.uesocc.ingenieria.tpi135_2016.clinica.datos.definiciones.Paciente;

/**
 *
 * @author kari
 */
@Named
@ViewScoped
public class FrmPaciente extends DefaultGenerador<Paciente> implements Serializable {

    @EJB
    private PacienteFacadeLocal pacienteFacade;
    private LazyDataModel<Paciente> modelo;
    private Paciente registro;

    @Override
    public AbstractFacadeInterface<Paciente> getFacade() {
        return pacienteFacade;
    }

    @Override
    public void generarModeloTabla() {
        this.modelo = new LazyDataModel<Paciente>() {

            @Override
            public Object getRowKey(Paciente object) {
                if (object != null) {
                    return object.getIdPaciente();
                }
                return null;
            }

            @Override
            public Paciente getRowData(String rowKey) {
                if (rowKey != null && !rowKey.trim().isEmpty() && getWrappedData() != null) {
                    try {
                        Long buscado = new Long(rowKey);
                        for (Paciente reg : ((List<Paciente>) getWrappedData())) {
                            if (reg.getIdPaciente().compareTo(buscado) == 0) {
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
            public List<Paciente> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                List salida = new ArrayList();
                int tamanioPagina = pageSize;
                try {
                    if (pacienteFacade != null) {
                        if (filters != null && !filters.isEmpty()) {
                            salida = pacienteFacade.findByDataFilters(filters, first, pageSize);
                            this.setRowCount(pacienteFacade.countByDataFilters(filters));
                        } else {
                            salida = pacienteFacade.findRango(first, tamanioPagina);
                            this.setRowCount(pacienteFacade.count());
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
    public Paciente crearNuevo() {
        this.registro = new Paciente();
        this.registro.setFechaNacimiento(new Date());
        return this.registro;
    }

    @Override
    public Paciente crearRegistro() {
        return this.registro;
    }

    @Override
    public LazyDataModel<Paciente> getModelo() {
        return super.getModelo(); //To change body of generated methods, choose Tools | Templates.
    }

    public Paciente getRegistro() {
        return registro;
    }

    public void setRegistro(Paciente registro) {
        this.registro = registro;
    }

}
