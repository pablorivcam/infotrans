package com.pablorivcam.infotrans;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.pablorivcam.infotrans.transferencia.EstadoTransferencia;
import com.pablorivcam.infotrans.transferencia.Transferencia;

/**
 * Clase encargada de crear un informe de transferencias dividido en
 * transferencias correctas y transferencias erróneas.
 * 
 * @author Pablo Rivas
 */
public class InformeTransferenciasBuilder {

    /** Ancho del informe generado. */
    private final int PAGE_WIDTH = 66;

    /** Listado de transferencias a procesar. */
    private List<Transferencia> transferencias;

    /**
     * Crea una nueva instancia de InformeTransferenciasBuilder.
     */
    public InformeTransferenciasBuilder() {
	transferencias = new ArrayList<Transferencia>();
    }

    /**
     * Añade una nueva transferencia al InformeTransferenciasBuilder y la valida.
     *
     * @param transferencia transferencia a añadir.
     * @return el propio InformeTransferenciasBuilder
     */
    public InformeTransferenciasBuilder addTransferencia(Transferencia transferencia) {
	transferencias.add(transferencia);
	transferencia.validarTransferencia();
	return this;
    }

    /**
     * Devuelve un informe de las transferencias almacenadas.
     *
     * @return string que contiene el informe de las transferencias almacenadas en
     *         la clase
     */
    public String generarInforme() {

	String result = "";

	List<Transferencia> transferenciasCorrectas = transferencias.stream()
		.filter(t -> t.getEstado().equals(EstadoTransferencia.OK)).collect(Collectors.toList());

	List<Transferencia> transferenciasErroneas = transferencias.stream()
		.filter(t -> !t.getEstado().equals(EstadoTransferencia.OK)).collect(Collectors.toList());

	result += PrintUtils.getCabecera("Transferencias correctas", PAGE_WIDTH);
	result += PrintUtils.getHojaTransferenciasString(transferenciasCorrectas, PAGE_WIDTH);

	result += PrintUtils.getCabecera("Transferencias erróneas", PAGE_WIDTH);
	result += PrintUtils.getHojaTransferenciasString(transferenciasErroneas, PAGE_WIDTH);

	return result;
    }

}
