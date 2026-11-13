package main.java.co.edu.unicartagena.concesionario.domain.valueobjects;
 
import co.edu.unicartagena.concesionario.domain.exceptions.ExcepcionDominio;
import java.util.Objects;
 
public final class Matricula {
 
    private final String valor;
 
    public Matricula(String valor) {
        if (valor == null || valor.isBlank()) {
            throw new ExcepcionDominio("La matricula es obligatoria");
        }
        String normalizada = valor.trim().toUpperCase();
        if (!normalizada.matches("[A-Z0-9]{5,7}")) {
            throw new ExcepcionDominio("La matricula tiene un formato invalido");
        }
        this.valor = normalizada;
    }
 
    public String getValor() {
        return valor;
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Matricula)) return false;
        return valor.equals(((Matricula) o).valor);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
 
    @Override
    public String toString() {
        return valor;
    }
}
