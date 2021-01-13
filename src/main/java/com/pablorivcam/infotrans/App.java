package com.pablorivcam.infotrans;

import java.util.Calendar;

import com.pablorivcam.infotrans.transferencia.TransferenciaComision;
import com.pablorivcam.infotrans.transferencia.TransferenciaPorQuincenas;
import com.pablorivcam.infotrans.transferencia.TransferenciaSimple;

/**
 * Programa sencillo para procesar conjuntos de transferencias y mostrar su procesamiento
 * en un informe.
 * <p>
 * Las transferencias según su tipo podrán ser: 
 * <ul>
 * 	<li>1. Transferencias simples
 * 	<li>2. Transferencias a las que se le aplica un porcentaje de comisión por el procesado.
 *      <li>3. Transferencias a las que se le aplica un porcentaje según el tiempo que pasase desde su registro
 *         hasta su procesado.
 *        </ul>
 * <p>
 * La clase InformeTransferenciasBuilder puede añadir transferencias y generar un informe sobre
 * las transferencias añadidas. 
 * 
 * Cada vez que una transferencia es añadida se le aplica una validación para decidir si es o no correcta.
 * En caso de tener algún error (fecha, id, cantidades erróneas), el informe muestra el resultado de la 
 * validación.
 * 
 * @author Pablo Rivas
 */
public class App {

    public static void main(String[] args) {

	Calendar now = Calendar.getInstance();
	Calendar date1 = Calendar.getInstance();
	date1.add(Calendar.MONTH, 2);
	Calendar date2 = Calendar.getInstance();
	date2.add(Calendar.MONTH, 200);

	// Parte que ejecuta el módulo como tal
	String informe = new InformeTransferenciasBuilder()
		.addTransferencia(new TransferenciaSimple(1L, 20, "Sin concepto", now.getTime()))
		.addTransferencia(new TransferenciaComision(2L, 20, "Sin concepto", now.getTime(), 10))
		.addTransferencia(new TransferenciaSimple(3L, 20, "Sin concepto", now.getTime()))
		.addTransferencia(new TransferenciaSimple(-3L, 20, "Sin concepto", now.getTime()))
		.addTransferencia(new TransferenciaPorQuincenas(4L, 20, "Sin concepto", date2.getTime()))
		.generarInforme();

	System.out.println(informe);
    }
}
