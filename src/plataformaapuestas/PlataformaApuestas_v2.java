/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plataformaapuestas;

import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author cleme
 */
public class PlataformaApuestas_v2 {

    public static int contador = 0;
    public static int[] vectorNumApuesta = new int[1000];
    public static float[] vectorSaldo = new float[1000];

    public static void main(String[] args) {

        int modoApuesta;
        
        modoApuesta = Integer.parseInt(JOptionPane.showInputDialog(null, "MODO DE APUESTA \n Ingrese: \n 1 para apostar en modo manual \n 2 para apostar en modo automático"));
        while (modoApuesta!=1 && modoApuesta!=2) {
            modoApuesta = Integer.parseInt(JOptionPane.showInputDialog(null, "ERROR. DATO INVÁLIDO. \n Ingrese: \n 1 para apostar en modo manual \n 2 para apostar en modo automático"));
        }
        
        if (modoApuesta == 1){
            apuestaModoManual();
        }
        else if (modoApuesta == 2){
            apuestaModoAutomatico();
        }

    }

    public static void apuestaModoManual() {
        try {
            float saldoInicial = 0;
            float saldoActual = 0;
            float saldoAnterior = 0;
            float valorApostado = 0;
            float valorGanado = 0;
            float cuotaDeApuesta = 0;
            float probabilidadGanarJuego = 0;
            float generadorNumAleatorio;
            int terminarApuesta = 1;

            saldoInicial = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el saldo inicial en pesos"));
            while (saldoInicial <= 0) {
                saldoInicial = Integer.parseInt(JOptionPane.showInputDialog(null, "ERROR. DATO INVÁLIDO. \n Ingrese el saldo inicial en pesos"));
            }

            saldoAnterior = saldoInicial;
            saldoActual = saldoInicial;
            vectorNumApuesta[contador] = contador;
            vectorSaldo[contador] = saldoInicial;

            probabilidadGanarJuego = Float.parseFloat(JOptionPane.showInputDialog(null, "Ingrese la probabilidad de ganar el juego en %"));
            while (probabilidadGanarJuego < 0) {
                probabilidadGanarJuego = Float.parseFloat(JOptionPane.showInputDialog(null, "ERROR. DATO INVÁLIDO. \n Ingrese la probabilidad de ganar el juego en %"));
            }

            probabilidadGanarJuego = probabilidadGanarJuego / 100;
            cuotaDeApuesta = 1 / probabilidadGanarJuego;
            JOptionPane.showMessageDialog(null, "Su cuota de apuesta es: " + cuotaDeApuesta);

            do {
                while (terminarApuesta != 0) {

                    saldoAnterior = saldoActual;

                    valorApostado = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad de dinero que va a apostar en pesos"));
                    while (saldoAnterior < valorApostado) {
                        valorApostado = Integer.parseInt(JOptionPane.showInputDialog(null, "ERROR. DATO INVÁLIDO. \n Debe apostar un valor menor que su saldo ($" + saldoAnterior + ")\n Ingrese la cantidad de dinero que va a apostar en pesos"));
                    }

                    generadorNumAleatorio = (float) (Math.random());
                    System.out.println("generadorNumAleatorio" + generadorNumAleatorio);

                    if (generadorNumAleatorio <= (probabilidadGanarJuego)) { //EL JUGADOR GANÓ
                        valorGanado = valorApostado * cuotaDeApuesta;       // si gana apuesta, pero si pierde valorGanado=0
                        JOptionPane.showMessageDialog(null, "GANA");
                    } else {                                                    //EL JUGADOR PERDIÓ
                        valorGanado = 0;
                        JOptionPane.showMessageDialog(null, "PIERDE");
                    }

                    saldoActual = saldoAnterior - valorApostado + valorGanado;
                    JOptionPane.showMessageDialog(null, "Su saldo actual es: $" + saldoActual);

                    contador += 1;
                    vectorNumApuesta[contador] = contador;
                    vectorSaldo[contador] = saldoActual;
                    hacerGrafica();

                    terminarApuesta = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Desea continuar apostando? \n En caso de que NO desee continuar apostando digite 0, de lo contrario digite un número diferente"));
                }

            } while (saldoActual > 0);

            if (saldoActual <= 0) {
                JOptionPane.showMessageDialog(null, "Su saldo es insuficiente para continuar con el juego de apuestas");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error: " + e);
        }
    }

    public static void apuestaModoAutomatico() {

        // Datos propios de la apuesta en modo automático
        int numeroApuestas;
        int tipoEstrategiaProbGanar;
        int tipoEstrategiaValorApostado;
        float probabilidadGanarJuego = 0;
        float valorApostadoEnTerminosDeProb = 0;

        // Datos generales de la apuesta
        float saldoInicial = 0;
        float saldoActual = 0;
        float saldoAnterior = 0;
        float valorApostado = 0;
        float valorGanado = 0;
        float cuotaDeApuesta = 0;
        float generadorNumAleatorio;
        int terminarApuesta = 1;

        // Recolección de datos propios de la apuesta e
        saldoInicial = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el saldo inicial en pesos"));
        while (saldoInicial <= 0) {
            saldoInicial = Integer.parseInt(JOptionPane.showInputDialog(null, "ERROR. DATO INVÁLIDO. \n Ingrese el saldo inicial en pesos"));
        }

        saldoAnterior = saldoInicial;
        saldoActual = saldoInicial;
        vectorNumApuesta[contador] = contador;
        vectorSaldo[contador] = saldoInicial;

        // Recolección de datos propios de la apuesta en modo automático
        numeroApuestas = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número de apuestas que va a realizar"));
        while (numeroApuestas <= 0) {
            numeroApuestas = Integer.parseInt(JOptionPane.showInputDialog(null, "ERROR. DATO INVÁLIDO. \n Ingrese el el número de apuestas que va a realizar"));
        }
        tipoEstrategiaProbGanar = Integer.parseInt(JOptionPane.showInputDialog(null, "ESTRATEGIA PARA PROBABILIDAD DE GANAR \n Ingrese: \n 1 para apostar con estrategia CONSERVADORA \n 2 para apostar con estrategia ARRIESGADA"));
        while (tipoEstrategiaProbGanar != 1 && tipoEstrategiaProbGanar != 2) {
            tipoEstrategiaProbGanar = Integer.parseInt(JOptionPane.showInputDialog(null, "ERROR. DATO INVÁLIDO. \n ESTRATEGIA PARA PROBABILIDAD DE GANAR \n Ingrese: \n 1 para apostar con estrategia CONSERVADORA \n 2 para apostar con estrategia ARRIESGADA"));
        }
        tipoEstrategiaValorApostado = Integer.parseInt(JOptionPane.showInputDialog(null, "ESTRATEGIA PARA VALOR APOSTADO \n Ingrese: \n 1 para apostar con estrategia ECONOMIZADORA \n 2 para apostar con estrategia DERROCHADORA"));
        while (tipoEstrategiaValorApostado != 1 && tipoEstrategiaValorApostado != 2) {
            tipoEstrategiaValorApostado = Integer.parseInt(JOptionPane.showInputDialog(null, "ERROR. DATO INVÁLIDO. \n ESTRATEGIA PARA VALOR APOSTADO \n Ingrese: \n 1 para apostar con estrategia ECONOMIZADORA \n 2 para apostar con estrategia DERROCHADORA"));
        }
        // ****** ESTRATEGIA CONSERVADORA *****
        if (tipoEstrategiaProbGanar == 1) {
            //FUNCIÓN RANDOM:
            float N = (float) 0.90;
            float M = (float) 0.5;
            probabilidadGanarJuego = (float) (Math.random() * (N - M) + M);
            System.out.println("probabilidadGanarJuego ESTRATEGIA CONSERVADORA " + probabilidadGanarJuego);
        } // ****** ESTRATEGIA ARRIESGADA *****
        else if (tipoEstrategiaProbGanar == 2) {
            //FUNCIÓN RANDOM:
            float N = (float) 0.15;
            float M = (float) 0.0;
            probabilidadGanarJuego = (float) (Math.random() * (N - M) + M);
            System.out.println("probabilidadGanarJuego ESTRATEGIA ARRIESGADA " + probabilidadGanarJuego);
        }

        //
        cuotaDeApuesta = 1 / probabilidadGanarJuego;
        JOptionPane.showMessageDialog(null, "Su cuota de apuesta es: " + cuotaDeApuesta);

        do {

            saldoAnterior = saldoActual;

            // ****** ESTRATEGIA ECONOMIZADORA *****
            if (tipoEstrategiaValorApostado == 1) {
                //FUNCIÓN RANDOM:
                float N = (float) 0.1;
                float M = (float) 0.02;
                valorApostadoEnTerminosDeProb = (float) (Math.random() * (N - M) + M);
                System.out.println("valorApostado ESTRATEGIA ECONOMIZADORA " + valorApostadoEnTerminosDeProb);
            }
            // ****** ESTRATEGIA DERROCHADORA *****
            if (tipoEstrategiaValorApostado == 2) {
                //FUNCIÓN RANDOM:
                float N = (float) 0.90;
                float M = (float) 0.60;
                valorApostadoEnTerminosDeProb = (float) (Math.random() * (N - M) + M);
                System.out.println("valorApostado ESTRATEGIA DERROCHADORA " + valorApostadoEnTerminosDeProb);
            }

            valorApostado = saldoAnterior * valorApostadoEnTerminosDeProb;
            //JOptionPane.showMessageDialog(null, "El valor apostado en esta ronda es: $" + valorApostado);

            generadorNumAleatorio = (float) (Math.random());
            System.out.println("generadorNumAleatorio" + generadorNumAleatorio);

            if (generadorNumAleatorio <= (probabilidadGanarJuego)) { //EL JUGADOR GANÓ
                valorGanado = valorApostado * cuotaDeApuesta;       // si gana apuesta, pero si pierde valorGanado=0
                JOptionPane.showMessageDialog(null, "GANA");
            } else {                                                    //EL JUGADOR PERDIÓ
                valorGanado = 0;
                JOptionPane.showMessageDialog(null, "PIERDE");
            }

            saldoActual = saldoAnterior - valorApostado + valorGanado;
            JOptionPane.showMessageDialog(null, "Su saldo actual es: $" + saldoActual);

            contador += 1;
            vectorNumApuesta[contador] = contador;
            vectorSaldo[contador] = saldoActual;
            hacerGrafica();

        } while (saldoActual > 0 && contador < numeroApuestas);

        if (saldoActual <= 0) {
            JOptionPane.showMessageDialog(null, "Su saldo es insuficiente para continuar con el juego de apuestas");
        }

    }

    public static void hacerGrafica() {

        XYSeries series = new XYSeries("Saldo de jugador");
        // Introduccion de datos
        for (int i = 0; i <= contador; i++) {
            series.add(vectorNumApuesta[i], vectorSaldo[i]);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Plataforma de Apuestas", // Título
                "Número de Apuestas", // Etiqueta Coordenada X
                "Saldo ($)", // Etiqueta Coordenada Y
                dataset, // Datos
                PlotOrientation.VERTICAL,
                true, // Muestra la leyenda de los productos (Producto A)
                false,
                false
        );
        // Mostramos la grafica en pantalla
        ChartFrame frame = new ChartFrame("Plataforma de apuestas", chart);
        frame.pack();
        frame.setVisible(true);
    }

}
