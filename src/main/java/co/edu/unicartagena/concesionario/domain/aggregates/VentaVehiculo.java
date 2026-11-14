package co.edu.unicartagena.concesionario.domain.aggregates;
 
import co.edu.unicartagena.concesionario.domain.entities.OpcionSeleccionada;
import co.edu.unicartagena.concesionario.domain.entities.VehiculoUsado;
import co.edu.unicartagena.concesionario.domain.events.VehiculoVendidoEvent;
import co.edu.unicartagena.concesionario.domain.exceptions.ExcepcionDominio;
import co.edu.unicartagena.concesionario.domain.valueobjects.Dinero;
import co.edu.unicartagena.concesionario.domain.valueobjects.Matricula;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
 
public class VentaVehiculo { // Aggregate Root
 
    private final String id;
    private final String clienteId;
    private final String vendedorId;
    private final String modeloId;
    private final Matricula matricula;
    private final LocalDate fechaVenta;
    private final Set<String> opcionesPermitidas;
    private final List<OpcionSeleccionada> opciones = new ArrayList<>();
    private Dinero precioTotal;
    private VehiculoUsado vehiculoUsado;
    private EstadoVenta estado;
 
    // el constructor es del paquete: las ventas se crean con VentaVehiculoFactory
    VentaVehiculo(String id, String clienteId, String vendedorId, String modeloId,
                  Set<String> opcionesPermitidas, Dinero precioBase,
                  Matricula matricula, LocalDate fechaVenta) {
        if (clienteId == null || clienteId.isBlank()) {
            throw new ExcepcionDominio("El cliente es obligatorio");
        }
        if (vendedorId == null || vendedorId.isBlank()) {
            throw new ExcepcionDominio("El vendedor es obligatorio");
        }
        if (modeloId == null || modeloId.isBlank()) {
            throw new ExcepcionDominio("El modelo es obligatorio");
        }
        if (matricula == null) {
            throw new ExcepcionDominio("La matricula del vehiculo nuevo es obligatoria");
        }
        if (fechaVenta == null) {
            throw new ExcepcionDominio("La fecha de venta es obligatoria");
        }
        if (precioBase == null) {
            throw new ExcepcionDominio("El precio base es obligatorio");
        }
        this.id = id;
        this.clienteId = clienteId;
        this.vendedorId = vendedorId;
        this.modeloId = modeloId;
        this.opcionesPermitidas = new HashSet<>(opcionesPermitidas);
        this.precioTotal = precioBase;
        this.matricula = matricula;
        this.fechaVenta = fechaVenta;
        this.estado = EstadoVenta.EN_PROCESO;
    }
 
    public void agregarOpcion(OpcionSeleccionada opcion) {
        validarNoFinalizada();
        if (opcion == null) {
            throw new ExcepcionDominio("La opcion es obligatoria");
        }
        if (!opcionesPermitidas.contains(opcion.getId())) {
            throw new ExcepcionDominio("La opcion no aplica al modelo vendido");
        }
        if (opciones.contains(opcion)) {
            throw new ExcepcionDominio("La opcion ya fue agregada a la venta");
        }
        opciones.add(opcion);
        this.precioTotal = precioTotal.sumar(opcion.getPrecio());
    }
 
    public void registrarVehiculoUsado(VehiculoUsado usado) {
        validarNoFinalizada();
        if (usado == null) {
            throw new ExcepcionDominio("El vehiculo usado es obligatorio");
        }
        if (vehiculoUsado != null) {
            throw new ExcepcionDominio("La venta ya tiene un vehiculo usado");
        }
        if (usado.getPrecioTasacion().esMayorOIgualQue(precioTotal)) {
            throw new ExcepcionDominio("La tasacion no puede cubrir el total de la venta");
        }
        this.vehiculoUsado = usado;
        this.precioTotal = precioTotal.restar(usado.getPrecioTasacion());
    }
 
    public VehiculoVendidoEvent finalizarVenta() {
        validarNoFinalizada();
        this.estado = EstadoVenta.FINALIZADA;
        return new VehiculoVendidoEvent(id, matricula, clienteId);
    }
 
    private void validarNoFinalizada() {
        if (estado == EstadoVenta.FINALIZADA) {
            throw new ExcepcionDominio("La venta ya esta finalizada");
        }
    }
 
    public String getId() {
        return id;
    }
 
    public String getClienteId() {
        return clienteId;
    }
 
    public String getVendedorId() {
        return vendedorId;
    }
 
    public String getModeloId() {
        return modeloId;
    }
 
    public Matricula getMatricula() {
        return matricula;
    }
 
    public LocalDate getFechaVenta() {
        return fechaVenta;
    }
 
    public Dinero getPrecioTotal() {
        return precioTotal;
    }
 
    public EstadoVenta getEstado() {
        return estado;
    }
 
    public List<OpcionSeleccionada> getOpciones() {
        return Collections.unmodifiableList(opciones);
    }
 
    public Optional<VehiculoUsado> getVehiculoUsado() {
        return Optional.ofNullable(vehiculoUsado);
    }
}