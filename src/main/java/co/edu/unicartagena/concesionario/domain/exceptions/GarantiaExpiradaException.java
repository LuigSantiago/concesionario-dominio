package co.edu.unicartagena.concesionario.domain.exceptions;
 
import co.edu.unicartagena.concesionario.domain.valueobjects.Matricula;
import java.time.LocalDate;
 
public class GarantiaExpiradaException extends ExcepcionDominio {
 
    public GarantiaExpiradaException(Matricula matricula, LocalDate fechaFin) {
        super("La garantia del vehiculo " + matricula + " vencio el " + fechaFin);
    }
}
