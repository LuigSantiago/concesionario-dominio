package co.edu.unicartagena.concesionario.domain.entities;
 
import co.edu.unicartagena.concesionario.domain.exceptions.ExcepcionDominio;
import co.edu.unicartagena.concesionario.domain.valueobjects.Dinero;
import co.edu.unicartagena.concesionario.domain.valueobjects.Matricula;
import java.time.LocalDate;
import java.util.Objects;
 
public class VehiculoUsado {
 
    private final Matricula matricula; // identidad
    private final String marca;
    private final String modelo;
    private final Dinero precioTasacion;
    private final LocalDate fechaEntrega;
 
    public VehiculoUsado(Matricula matricula, String marca, String modelo,
                         Dinero precioTasacion, LocalDate fechaEntrega) {
        if (marca == null || marca.isBlank()) {
            throw new ExcepcionDominio("La marca del usado es obligatoria");
        }
        if (modelo == null || modelo.isBlank()) {
            throw new ExcepcionDominio("El modelo del usado es obligatorio");
        }
        this.matricula = Objects.requireNonNull(matricula, "La matricula es obligatoria");
        this.marca = marca;
        this.modelo = modelo;
        this.precioTasacion = Objects.requireNonNull(precioTasacion, "La tasacion es obligatoria");
        this.fechaEntrega = Objects.requireNonNull(fechaEntrega, "La fecha de entrega es obligatoria");
    }
 
    public Matricula getMatricula() {
        return matricula;
    }
 
    public String getMarca() {
        return marca;
    }
 
    public String getModelo() {
        return modelo;
    }
 
    public Dinero getPrecioTasacion() {
        return precioTasacion;
    }
 
    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VehiculoUsado)) return false;
        return matricula.equals(((VehiculoUsado) o).matricula);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }
}