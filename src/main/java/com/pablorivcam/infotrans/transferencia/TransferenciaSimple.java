package com.pablorivcam.infotrans.transferencia;

import java.util.Date;

/**
 * Transferencias simples a las que no se le aplica ningún tipo de comisión.
 * 
 * @author Pablo Rivas
 */
public class TransferenciaSimple extends Transferencia {

    /**
     * Instancia una nueva transferencia simple.
     *
     * @param id                 el identificador de la transferencia.
     * @param cantidad           la cantidad de la transferencia.
     * @param concepto           el concepto de la transferencia.
     * @param fechaTransferencia la fecha en la que fue realizada la transferencia.
     */
    public TransferenciaSimple(long id, double cantidad, String concepto, Date fechaTransferencia) {
	super(id, cantidad, concepto, fechaTransferencia);
	this.tipoTransferencia = "Transferencia simple";
    }

    @Override
    public double getTotal() {
	return cantidad;
    }

    @Override
    protected boolean validacionAdicional() {
	return true;
    }

    @Override
    public String getInformacionAdicional() {
	return null;
    }

}
