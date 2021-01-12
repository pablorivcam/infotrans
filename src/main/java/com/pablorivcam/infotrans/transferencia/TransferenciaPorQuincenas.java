package com.pablorivcam.infotrans.transferencia;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * Clase para las transferencias cuya comisión se incrementa por cada quincena
 * de retraso en el procesamiento hasta un número máximo de quincenas.
 * 
 * @author Pablo Rivas
 */
public class TransferenciaPorQuincenas extends Transferencia {

    /** Porcentaje acumulado por quincena. */
    public final static double PORCENTAJE_QUINCENAL = 0.5;

    /** Número máximo de quincenas para incrementar el porcentaje. */
    public final static int QUINCENAS_MAXIMAS = 40;

    /**
     * Instancia una nueva transferencia por fecha
     *
     * @param id                 el identificador de la transferencia.
     * @param cantidad           la cantidad de la transferencia.
     * @param concepto           el concepto de la transferencia.
     * @param fechaTransferencia la fecha en la que fue realizada la transferencia.
     */
    public TransferenciaPorQuincenas(long id, double cantidad, String concepto, Date fechaTransferencia) {
	super(id, cantidad, concepto, fechaTransferencia);
	this.tipoTransferencia = "Transferencia con comisiones por fecha";
    }

    /**
     * Método que devuelve el retraso del procesamiento en quincenas.
     *
     * @return quincenas de retraso en el procesamiento.
     */
    private long getQuincenasRetraso() {
	Calendar now = Calendar.getInstance();
	return ChronoUnit.DAYS.between(now.toInstant(), fechaTransferencia.toInstant()) / 15;
    }

    /**
     * Método que devuelve el porcentaje de comisión a aplichar por fecha
     *
     * @return el porcentaje de comisión a aplicar por fecha
     */
    private double getComision() {
	return getQuincenasRetraso() < QUINCENAS_MAXIMAS ? getQuincenasRetraso() * PORCENTAJE_QUINCENAL
		: PORCENTAJE_QUINCENAL * QUINCENAS_MAXIMAS;
    }

    @Override
    public double getTotal() {
	return cantidad + cantidad * getComision() / 100;
    }

    @Override
    protected boolean validacionAdicional() {
	return true;
    }

    @Override
    public String getInformacionAdicional() {
	return String.format("Aplicado un porcentaje del %.2f%% por retraso de %d quincenas.", +getComision(),
		getQuincenasRetraso());
    }

}
