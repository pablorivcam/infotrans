package com.pablorivcam.infotrans;

import java.util.List;

import com.pablorivcam.infotrans.transferencia.Transferencia;

/**
 * Clase con utilidades para imprimir el informe por pantalla.
 * 
 * @author Pablo Rivas
 */
public final class PrintUtils {

    /**
     * Devuelve un string con varios espacioss a la derecha en caso de ser menor a
     * la longitud introducida.
     *
     * @param string cadena de caracteres a la que aplicar el formato
     * @param length tamaño máximo esperado de la cadena de caracteres
     * @return la cadena de caracteres tras haberle aplicado formato
     */
    public static String paddingStringLeft(String string, int length) {
	return String.format("%-" + length + "s", string);
    }

    /**
     * Aplica formato de tabla a dos cadenas de caracteres.
     *
     * @param string1 cadena de carcteres que estará a la izquierda
     * @param string2 cadena de carcteres que estará a la derecha
     * @param length  longitud de la cadena de caracteres resultante tras aplicarle
     *                el formato
     * @return cadena de caracteres compuesta por las dos cadenas recibidas
     */
    public static String paddingStrings(String string1, String string2, int length) {
	return String.format("%1$-" + (5 * length / 6) + "s%2$" + length / 6 + "s", string1, string2);
    }

    /**
     * Método que devuelve una línea de caracteres = de una determinada longitud.
     *
     * @param c      caracter que compone la línea
     * @param length longitud de la línea
     * @return la línea de caracteres compuesta por el caracter introducido
     */
    public static String line(char c, int length) {
	return String.format("%" + length + "s", "").replace(' ', c);
    }

    /**
     * Devuelve una cabecera con formato.
     *
     * @param titulo título de la cabecera
     * @param length tamaño de la cabecera
     * @return cadena de caracteres representando la cabecera
     */
    public static String getCabecera(String titulo, int length) {
	String result = "";
	result += "\n\n*" + PrintUtils.line('=', length) + "*\n";
	result += "|" + PrintUtils.paddingStringLeft(titulo, length) + "|\n";
	result += "*" + PrintUtils.line('=', length) + "*\n";
	return result;
    }

    /**
     * Devuelve una cadena con las transferencias tras haberles aplicado formato. *
     *
     * @param transferencias listado de transferencias a las que se le van a aplicar
     *                       formato
     * @param length         tamaño de la página
     * @return adena con las transferencias con formato
     */
    public static String getHojaTransferenciasString(List<Transferencia> transferencias, int length) {
	String result = "";
	for (Transferencia t : transferencias) {
	    result += "|" + PrintUtils.paddingStrings(
		    t.getId() + "." + t.getFechaTransferenciaAsString() + " " + t.getTipoTransferencia(),
		    t.getTotalAsString(), length) + "|\n";
	    result += "|" + PrintUtils.paddingStrings("   " + t.getConcepto() + " (" + t.getCantidadAsString() + ")",
		    t.getDiferenciaTotal(), length) + "|\n";
	    result += "|" + PrintUtils.paddingStringLeft("   " + t.getEstado().getMensaje(), length) + "|\n";
	    if (t.getInformacionAdicional() != null) {
		result += "|" + PrintUtils.paddingStringLeft("   " + t.getInformacionAdicional(), length) + "|\n";
	    }
	    result += "|" + PrintUtils.line(' ', length) + "|\n";

	}

	result += "*" + PrintUtils.line('=', length) + "*\n";

	return result;
    }
}
