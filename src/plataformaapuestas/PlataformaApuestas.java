/*
***** PLATAFORMA DE APUESTAS *****
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
public class PlataformaApuestas {

    public static int contador=0;
    public static int[] vectorNumApuesta = new int [100];
    public static float[] vectorSaldo = new float [100];
            
    public static void main(String[] args) {
        try {
            
            float saldoInicial=0;
            float saldoActual=0;
            float saldoAnterior=0; 
            float valorApostado=0;
            float valorGanado=0;
            float cuotaDeApuesta=0;
            float probabilidadGanarJuego=0;
            float generadorNumAleatorio;
            int terminarApuesta=1;
            
            
            
            saldoInicial=Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el saldo inicial en pesos"));
            while (saldoInicial<=0){
                saldoInicial=Integer.parseInt(JOptionPane.showInputDialog(null, "ERROR. DATO INVÁLIDO. \n Ingrese el saldo inicial en pesos"));
            }
            
            saldoAnterior=saldoInicial;
            saldoActual=saldoInicial;
            vectorNumApuesta[contador] = contador;
            vectorSaldo [contador] = saldoInicial;
            
            probabilidadGanarJuego=Float.parseFloat(JOptionPane.showInputDialog(null, "Ingrese la probabilidad de ganar el juego en %"));
            while (probabilidadGanarJuego<0){
                probabilidadGanarJuego=Float.parseFloat(JOptionPane.showInputDialog(null, "ERROR. DATO INVÁLIDO. \n Ingrese la probabilidad de ganar el juego en %"));
            }
            
            probabilidadGanarJuego=probabilidadGanarJuego/100;
            cuotaDeApuesta = 1/probabilidadGanarJuego;
            JOptionPane.showMessageDialog(null, "Su cuota de apuesta es: " + cuotaDeApuesta);   
                
            do {
                while (terminarApuesta!=0){
                    
                    saldoAnterior=saldoActual;
                    
                    valorApostado=Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad de dinero que va a apostar en pesos"));
                    while (saldoAnterior<valorApostado){
                        valorApostado=Integer.parseInt(JOptionPane.showInputDialog(null, "ERROR. DATO INVÁLIDO. \n Debe apostar un valor menor que su saldo ($" + saldoAnterior + ")\n Ingrese la cantidad de dinero que va a apostar en pesos"));
                    }

                    generadorNumAleatorio = (float)(Math.random());
                    System.out.println("generadorNumAleatorio" + generadorNumAleatorio);


                    if (generadorNumAleatorio <= (probabilidadGanarJuego)){ //EL JUGADOR GANÓ
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
                    vectorSaldo [contador] = saldoActual;
                    hacerGrafica();

                    terminarApuesta=Integer.parseInt(JOptionPane.showInputDialog(null, "¿Desea continuar apostando? \n En caso de que NO desee continuar apostando digite 0, de lo contrario digite un número diferente"));
                } 
                
            } while (saldoActual>0);
            
            if (saldoActual<=0) {
                JOptionPane.showMessageDialog(null, "Su saldo es insuficiente para continuar con el juego de apuestas");
            }
        
        }
        
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error: " + e);
        }
    }
    
    public static void hacerGrafica(){
        
        XYSeries series = new XYSeries("Saldo de jugador");

        // Introduccion de datos
       
        for (int i=0; i<=contador; i++){
            series.add(vectorNumApuesta[i],vectorSaldo[i]);
        
        }
        
        
        /*series.add(1,1);
        series.add(2,6);
        series.add(3,3);
        series.add(4,10);*/

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
