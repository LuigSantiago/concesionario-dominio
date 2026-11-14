package co.edu.unicartagena.concesionario.domain.aggregates;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
 
import co.edu.unicartagena.concesionario.domain.entities.ModeloVehiculo;
import co.edu.unicartagena.concesionario.domain.entities.OpcionSeleccionada;
import co.edu.unicartagena.concesionario.domain.entities.VehiculoUsado;
import co.edu.unicartagena.concesionario.domain.events.VehiculoVendidoEvent;
import co.edu.unicartagena.concesionario.domain.exceptions.ExcepcionDominio;
import co.edu.unicartagena.concesionario.domain.valueobjects.Dinero;
import co.edu.unicartagena.concesionario.domain.valueobjects.Matricula;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
 
class VentaVehiculoTest {
 
    private VentaVehiculo venta;
    private OpcionSeleccionada aireAcondicionado;
 
    private static Dinero cop(String valor) {
        return new Dinero(new BigDecimal(valor), "COP");
    }
 
    @BeforeEach
    void preparar() {
        ModeloVehiculo modelo = new ModeloVehiculo("M-01", "Mazda", "CX-5", 2000, cop("100000000"));
        modelo.habilitarOpcion("OP-AC");
        aireAcondicionado = new OpcionSeleccionada("OP-AC", "Aire acondicionado", cop("3000000"));
        venta = VentaVehiculoFactory.crear("1001", "2002", modelo,
                new Matricula("ABC123"), LocalDate.now());
    }
 
    @Test
    void noDebePermitirTasacionMayorAlValorDelVehiculoNuevo() {
        VehiculoUsado usado = new VehiculoUsado(new Matricula("XYZ789"), "Mazda", "3",
                cop("110000000"), LocalDate.now());
 
        assertThrows(ExcepcionDominio.class, () -> venta.registrarVehiculoUsado(usado));
    }
 
    @Test
    void debeDescontarLaTasacionDelPrecioTotal() {
        VehiculoUsado usado = new VehiculoUsado(new Matricula("XYZ789"), "Mazda", "3",
                cop("20000000"), LocalDate.now());
 
        venta.registrarVehiculoUsado(usado);
 
        assertEquals(cop("80000000"), venta.getPrecioTotal());
    }
 
    @Test
    void noDebePermitirModificarUnaVentaFinalizada() {
        venta.finalizarVenta();
 
        assertThrows(ExcepcionDominio.class, () -> venta.agregarOpcion(aireAcondicionado));
    }
 
    @Test
    void noDebePermitirAgregarLaMismaOpcionDosVeces() {
        venta.agregarOpcion(aireAcondicionado);
 
        assertThrows(ExcepcionDominio.class, () -> venta.agregarOpcion(aireAcondicionado));
    }
 
    @Test
    void noDebePermitirOpcionesQueNoAplicanAlModelo() {
        OpcionSeleccionada techoPanoramico = new OpcionSeleccionada("OP-TP",
                "Techo panoramico", cop("5000000"));
 
        assertThrows(ExcepcionDominio.class, () -> venta.agregarOpcion(techoPanoramico));
    }
 
    @Test
    void debeSumarElPrecioDeLaOpcionAlTotal() {
        venta.agregarOpcion(aireAcondicionado);
 
        assertEquals(cop("103000000"), venta.getPrecioTotal());
    }
 
    @Test
    void alFinalizarLaVentaDebeEmitirElEventoDeVehiculoVendido() {
        VehiculoVendidoEvent evento = venta.finalizarVenta();
 
        assertEquals(EstadoVenta.FINALIZADA, venta.getEstado());
        assertEquals(venta.getId(), evento.getVentaId());
        assertEquals(new Matricula("ABC123"), evento.getMatricula());
        assertTrue(evento.getOcurridoEn() != null);
    }
}
