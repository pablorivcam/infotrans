package com.pablorivcam.infotrans.transferencia;

import java.util.Date;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Transferencia a la que se le aplica un porcentaje de comisión.
 * 
 * @author Pablo Rivas
 */
public class TransferenciaComision extends Transferencia {

    /** Porcentaje aplicado a la transferencia. */
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private double porcentajeComision;

    /**
     * Instancia una nueva transferencia con comisión.
     *
     * @param id                 el identificador de la transferencia.
     * @param cantidad           la cantidad de la transferencia.
     * @param concepto           el concepto de la transferencia.
     * @param fechaTransferencia la fecha en la que fue realizada la transferencia.
     * @param porcentajeComision el porcentaje de comisión a aplicar
     */
    public TransferenciaComision(long id, double cantidad, String concepto, Date fechaTransferencia,
	    double porcentajeComision) {
	super(id, cantidad, concepto, fechaTransferencia);
	this.porcentajeComision = porcentajeComision;
	this.tipoTransferencia = "Transferencia con comisiones";
    }

    @Override
    public double getTotal() {
	return cantidad + cantidad * porcentajeComision / 100;
    }

    @Override
    protected boolean validacionAdicional() {
	if (porcentajeComision < 0 || porcentajeComision > 100) {
	    estado = EstadoTransferencia.ERROR_VALIDACION_ADICIONAL;
	    return false;
	} else {
	    return true;
	}
    }

    @Override
    public String getInformacionAdicional() {
	return "Aplicada una comisión del " + porcentajeComision + "%.";
    }

}
