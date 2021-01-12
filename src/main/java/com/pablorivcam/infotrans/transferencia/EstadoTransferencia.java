package com.pablorivcam.infotrans.transferencia;

import lombok.Getter;

// TODO: Auto-generated Javadoc
/**
 * Enumerado con los posibles estados que puede tener una clase
 * {@link Transferencia}.
 * 
 * @author Pablo Rivas
 */
public enum EstadoTransferencia {

    /** Estado pendiente de ser procesado. */
    PENDIENTE("Pendiente de procesado"),

    /** Estado correcto. */
    OK("Ok: transferencia correcta"),

    /** Estado erróneo para las transferencias sin fecha. */
    SIN_FECHA("Error: Transferencia sin fecha"),

    /** Estado erróneo para las transferencias con un identificador inválido. */
    ID_INVALIDO("Error: identificador inválido"),

    /** Estado erróneo para las transferencias con una cantidad incorrecta. */
    CANTIDAD_INCORRECTA("Error: cantidad incorrecta"),

    /** Estado erróneo para las transferencias cuya validación adicional falle. */
    ERROR_VALIDACION_ADICIONAL("Error: validaciones adicionales incorrectas");

    /** Mensaje asociado al estado de la transferencia */
    @Getter
    private final String mensaje;

    /**
     * Instancia un nuevo estado de una transferencia.
     *
     * @param mensaje el mensaje del estado
     */
    private EstadoTransferencia(String mensaje) {
	this.mensaje = mensaje;
    }

}
