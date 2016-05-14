package sv.edu.uesocc.ingenieria.tpi135_2016.clinica.presentacion.web.paginas.backing;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.primefaces.model.LazyDataModel;
import sv.edu.uesocc.ingenieria.tpi135_2016.clinica.datos.acceso.AbstractFacadeInterface;

/**
 *
 * @author kari
 */
public abstract class DefaultGenerador<T> {

    private LazyDataModel<T> modelo;
    
    private T registro;

    protected abstract AbstractFacadeInterface<T> getFacade();

    public void generarListas() {
    }

    public void generarModeloTabla() {
    }

    public LazyDataModel<T> getModelo() {
        return this.modelo;
    }

    public void setModel(LazyDataModel<T> modelo) {
        this.modelo = modelo;
    }

    public T getRegistro() {
        return registro;
    }

    public void setRegistro(T registro) {
        this.registro = registro;
    }

    protected abstract T crearNuevo();

    protected abstract T crearRegistro();
}
