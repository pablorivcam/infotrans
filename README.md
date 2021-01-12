# infotrans
Programa sencillo para procesar conjuntos de transferencias y mostrar su procesamiento en un informe.

Por cada transferencia se muestra la cantidad antes y después de aplicarle las comisiones oportunas, el concepto, su fecha y si fue o no procesada correctamente.

Las transferencias según su tipo podrán ser: 

   1. Transferencias simples.
   2. Transferencias a las que se le aplica un porcentaje de comisión por el procesado.
   3. Transferencias a las que se le aplica un porcentaje según el tiempo que pasase desde su registro hasta su procesado.

Cada vez que una transferencia es añadida se le aplica una validación para decidir si es o no correcta.
En caso de tener algún error (fecha, id, cantidad erróneas), el informe muestra el resultado de la validación.

## Ejecución

Ejecutar la clase `App.java` o bien ejecutar con Maven `mvn exec:java -Dexec.mainClass="com.pablorivcam.infotrans.App"`.

Versión Java: 1.8


