package co.edu.unicartagena.concesionario.domain.valueobjects;
 
import co.edu.unicartagena.concesionario.domain.exceptions.ExcepcionDominio;
import java.math.BigDecimal;
import java.util.Objects;
 
public final class Dinero {
 
    private final BigDecimal monto;
    private final String moneda;
 
    public Dinero(BigDecimal monto, String moneda) {
        if (monto == null || monto.compareTo(BigDecimal.ZERO) < 0) {
            throw new ExcepcionDominio("El monto no puede ser negativo");
        }
        if (moneda == null || moneda.isBlank()) {
            throw new ExcepcionDominio("La moneda es obligatoria");
        }
        this.monto = monto;
        this.moneda = moneda;
    }
 
    public static Dinero cero(String moneda) {
        return new Dinero(BigDecimal.ZERO, moneda);
    }
 
    public Dinero sumar(Dinero otro) {
        validarMismaMoneda(otro);
        return new Dinero(this.monto.add(otro.monto), this.moneda);
    }
 
    public Dinero restar(Dinero otro) {
        validarMismaMoneda(otro);
        return new Dinero(this.monto.subtract(otro.monto), this.moneda);
    }
 
    public boolean esMayorOIgualQue(Dinero otro) {
        validarMismaMoneda(otro);
        return this.monto.compareTo(otro.monto) >= 0;
    }
 
    private void validarMismaMoneda(Dinero otro) {
        if (otro == null) {
            throw new ExcepcionDominio("El valor a operar es obligatorio");
        }
        if (!this.moneda.equals(otro.moneda)) {
            throw new ExcepcionDominio("No se pueden operar monedas distintas");
        }
    }
 
    public BigDecimal getMonto() {
        return monto;
    }
 
    public String getMoneda() {
        return moneda;
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dinero)) return false;
        Dinero otro = (Dinero) o;
        return monto.compareTo(otro.monto) == 0 && moneda.equals(otro.moneda);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(monto.stripTrailingZeros(), moneda);
    }
 
    @Override
    public String toString() {
        return monto.toPlainString() + " " + moneda;
    }
}
