package co.edu.unicartagena.concesionario.domain.events;
 
import co.edu.unicartagena.concesionario.domain.valueobjects.Matricula;
import java.time.Instant;
import java.util.UUID;
 
public final class VehiculoVendidoEvent {
 
    private final UUID eventoId;
    private final Instant ocurridoEn;
    private final String ventaId;
    private final Matricula matricula;
    private final String clienteId;
 
    public VehiculoVendidoEvent(String ventaId, Matricula matricula, String clienteId) {
        this.eventoId = UUID.randomUUID();
        this.ocurridoEn = Instant.now();
        this.ventaId = ventaId;
        this.matricula = matricula;
        this.clienteId = clienteId;
    }
 
    public UUID getEventoId() {
        return eventoId;
    }
 
    public Instant getOcurridoEn() {
        return ocurridoEn;
    }
 
    public String getVentaId() {
        return ventaId;
    }
 
    public Matricula getMatricula() {
        return matricula;
    }
 
    public String getClienteId() {
        return clienteId;
    }
}