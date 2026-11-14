package co.edu.unicartagena.concesionario.domain.entities;
 
import co.edu.unicartagena.concesionario.domain.exceptions.GarantiaExpiradaException;
import co.edu.unicartagena.concesionario.domain.valueobjects.Matricula;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
 
public class GarantiaVehiculo {
 
    private final Matricula matricula; // identidad
    private final LocalDate fechaFin;
    private final Set<String> piezasCubiertas;
 
    public GarantiaVehiculo(Matricula matricula, LocalDate fechaFin,
                            Collection<String> piezasCubiertas) {
        this.matricula = Objects.requireNonNull(matricula, "La matricula es obligatoria");
        this.fechaFin = Objects.requireNonNull(fechaFin, "La fecha fin es obligatoria");
        this.piezasCubiertas = new HashSet<>(
                Objects.requireNonNull(piezasCubiertas, "Las piezas cubiertas son obligatorias"));
    }
 
    public boolean estaVigenteEn(LocalDate fecha) {
        return !fecha.isAfter(fechaFin);
    }
 
    public void validarVigenteEn(LocalDate fecha) {
        if (!estaVigenteEn(fecha)) {
            throw new GarantiaExpiradaException(matricula, fechaFin);
        }
    }
 
    public boolean cubre(String piezaId) {
        return piezasCubiertas.contains(piezaId);
    }
 
    public Matricula getMatricula() {
        return matricula;
    }
 
    public LocalDate getFechaFin() {
        return fechaFin;
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GarantiaVehiculo)) return false;
        return matricula.equals(((GarantiaVehiculo) o).matricula);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }
}
