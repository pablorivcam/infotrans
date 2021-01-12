package com.pablorivcam.infotrans;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;

import org.junit.Test;

import com.pablorivcam.infotrans.transferencia.EstadoTransferencia;
import com.pablorivcam.infotrans.transferencia.TransferenciaComision;
import com.pablorivcam.infotrans.transferencia.TransferenciaPorQuincenas;
import com.pablorivcam.infotrans.transferencia.TransferenciaSimple;

/**
 * Clase para probar los métodos asociados con las transferencias.
 */
public class TransferenciasTest {

    private static String TEST_CONCEPTO = "Concepto prueba";

    @Test
    public void testTransferenciaSimple() {

	TransferenciaSimple t = new TransferenciaSimple(10, 10.0, TEST_CONCEPTO, Calendar.getInstance().getTime());

	assertTrue(t.getCantidad() == t.getTotal());

    }

    @Test
    public void testTransferenciaComision() {

	TransferenciaComision t = new TransferenciaComision(10, 10.0, TEST_CONCEPTO, Calendar.getInstance().getTime(),
		10);

	assertTrue(t.getCantidad() != t.getTotal());
	assertTrue(t.getCantidad() * ((100 + t.getPorcentajeComision()) / 100) == t.getTotal());
    }

    @Test
    public void testTransferenciaPorFecha() {

	Calendar now = Calendar.getInstance();
	Calendar fecha = Calendar.getInstance();
	fecha.add(Calendar.MONTH, 2);

	TransferenciaPorQuincenas t = new TransferenciaPorQuincenas(10, 10.0, TEST_CONCEPTO,
		Calendar.getInstance().getTime());

	assertTrue(t.getCantidad() == t.getTotal());

	t.setFechaTransferencia(fecha.getTime());

	assertTrue(t.getCantidad() != t.getTotal());

	long quincenasRetraso = ChronoUnit.DAYS.between(now.toInstant(), fecha.toInstant()) / 15;

	assertTrue(
		(t.getCantidad() * (100 + quincenasRetraso * TransferenciaPorQuincenas.PORCENTAJE_QUINCENAL) / 100) == t
			.getTotal());

	fecha.add(Calendar.MONTH, 2000);
	t.setFechaTransferencia(fecha.getTime());

	assertTrue((t.getCantidad()
		* (100 + TransferenciaPorQuincenas.QUINCENAS_MAXIMAS * TransferenciaPorQuincenas.PORCENTAJE_QUINCENAL)
		/ 100) == t.getTotal());

    }

    @Test
    public void testValidaciones() {

	Calendar fecha = Calendar.getInstance();
	fecha.add(Calendar.MONTH, 2);

	TransferenciaComision t = new TransferenciaComision(-10, 10.0, TEST_CONCEPTO, Calendar.getInstance().getTime(),
		10);

	assertTrue(t.getEstado().equals(EstadoTransferencia.PENDIENTE));

	assertFalse(t.validarTransferencia());
	assertTrue(t.getEstado().equals(EstadoTransferencia.ID_INVALIDO));

	t.setId(10);
	t.setPorcentajeComision(120);
	assertFalse(t.validarTransferencia());
	assertTrue(t.getEstado().equals(EstadoTransferencia.ERROR_VALIDACION_ADICIONAL));

	t.setPorcentajeComision(90.9);

	assertTrue(t.validarTransferencia());
	assertTrue(t.getEstado().equals(EstadoTransferencia.OK));

    }

}
