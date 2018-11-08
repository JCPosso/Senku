package pruebas;
import aplicacion.*;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
/**
 * The test class SenkuTest.
 *
 * @author  Juan Camilo P.
 * @version (a version number or a date)
 */
public class PanelTableroTest
{   
    /**
     * Default constructor for test class SenkuTest
     */
	private PanelTablero panel;
	
    public PanelTableroTest() {
        panel = new PanelTablero(3,7);
    }
	@Test
    public void noDeberiaRealizarMovimientoDesdeFichasSeguidas() {
		char[][] tablero = panel.getTablero();
		assertTrue(tablero[3][3]=='x');
		panel.jugar(3,4,3,3);
		assertFalse(tablero[3][3]=='+');
    }
	@Test
    public void noDeberiaRealizarMovimientoFichasMuyLejanas() {
		char[][] tablero = panel.getTablero();
		panel.jugar(3,6,3,3);
		assertFalse(tablero[3][3]=='+');
    }
	@Test
	public void noDeberiaRealizarMovimientoFueraDelTablero() {
		char[][] tablero = panel.getTablero();
		panel.jugar(5,3,5,6);
		assertFalse(tablero[5][3]=='x');
    }
}
