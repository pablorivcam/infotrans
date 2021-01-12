package com.pablorivcam.infotrans.transferencia;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa una transferencia.
 * 
 * @author Pablo Rivas
 */
public abstract class Transferencia {

    /** Identificador de la transferencia. */
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    protected long id;

    /** Cantidad de la transferencia. */
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    protected double cantidad;

    /** Concepto de la transferencia. */
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    protected String concepto;

    /** Fecha en la que se realizó la transferencia. */
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    protected Date fechaTransferencia;

    /** Estado de la transferencia. */
    @Getter(AccessLevel.PUBLIC)
    protected EstadoTransferencia estado = EstadoTransferencia.PENDIENTE;

    @Getter(AccessLevel.PUBLIC)
    /** Tipo de transferencia. */
    protected String tipoTransferencia;

    /**
     * Instancia una nueva transferencia.
     *
     * @param id                 el identificador de la transferencia
     * @param cantidad           la cantidad de la transferencia
     * @param concepto           el concepto de la transferencia
     * @param fechaTransferencia fecha en la que fue realizada la transferencia
     */
    public Transferencia(long id, double cantidad, String concepto, Date fechaTransferencia) {
	this.id = id;
	this.cantidad = cantidad;
	this.concepto = concepto;
	this.fechaTransferencia = fechaTransferencia;
    }

    /**
     * Devuelve el valor total de la transferencia.
     *
     * @return el valor total de la transferencia.
     */
    public abstract double getTotal();

    /**
     * Realiza las validaciones adicionales por cada tipo concreto de transferencia.
     *
     * @return verdadero en caso de que la transferencia se valide
     *         satisfactoriamente, falso en caso contrario.
     */
    protected abstract boolean validacionAdicional();

    /**
     * Método para poder añadir informacion adicional de la transferencia.
     *
     * @return información adicional de la transferencia
     */
    public abstract String getInformacionAdicional();

    /**
     * Devuelve el valor de {@link #fechaTransferencia} en formato DD/MM/AAAA
     *
     * @return valor de {@link #fechaTransferencia} en formato DD/MM/AAAA
     */
    public String getFechaTransferenciaAsString() {
	SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");
	return sdfr.format(fechaTransferencia);
    }

    /**
     * Devuelve el valor del campo {@link #cantidad} como cadena de texto.
     *
     * @return el valor del campo {@link #cantidad} como cadena de texto.
     */
    public String getCantidadAsString() {
	return String.format("%.2f€", cantidad);
    }

    /**
     * Devuelve el valor del total como cadena de texto.
     *
     * @return el valor del total como cadena de texto.
     */
    public String getTotalAsString() {
	return String.format("%.2f€", getTotal());
    }

    /**
     * Devuelve el valor de las comisiones aplicadas en formato texto
     *
     * @return el valor de las comisiones aplicadas en formato texto.
     */
    public String getDiferenciaTotal() {
	return String.format("%.2f€", getTotal() - getCantidad());
    }

    /**
     * Valida una transferencia y le asigna un estado según el resusltado de la
     * validación.
     *
     * @return verdadero en caso de que la transferencia se valide
     *         satisfactoriamente, falso en caso contrario.
     */
    public boolean validarTransferencia() {

	if (id <= 0) {
	    estado = EstadoTransferencia.ID_INVALIDO;
	    return false;
	}

	else if (cantidad <= 0) {
	    estado = EstadoTransferencia.CANTIDAD_INCORRECTA;
	    return false;
	}

	else if (fechaTransferencia == null) {
	    estado = EstadoTransferencia.SIN_FECHA;
	    return false;
	}

	estado = EstadoTransferencia.OK;

	return validacionAdicional();

    }

}
