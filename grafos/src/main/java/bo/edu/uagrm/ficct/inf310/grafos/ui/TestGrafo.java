package bo.edu.uagrm.ficct.inf310.grafos.ui;

import bo.edu.uagrm.ficct.inf310.grafos.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310.grafos.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310.grafos.excepciones.ExcepcionNroVerticesInvalido;
import bo.edu.uagrm.ficct.inf310.grafos.nopesados.Digrafo;
import bo.edu.uagrm.ficct.inf310.grafos.nopesados.Grafo;

public class TestGrafo {
    public static void main(String[] argumentos) throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste, ExcepcionAristaNoExiste {

            Grafo grafo1 = new Grafo(6);
        Digrafo digrafo = new Digrafo(6);

          /*  grafo1.insertarArista(0,1);
            grafo1.insertarArista(0,3);
            grafo1.insertarArista(1,3);
             grafo1.insertarArista(3,4);
             grafo1.insertarArista(2,5);

             grafo1.eliminarArista(2,5);

        System.out.println("CantIslas:" + grafo1.cantidadDeIslas());*/

        digrafo.insertarArista(0,1);
        digrafo.insertarArista(0,3);
        digrafo.insertarArista(3,4);
        digrafo.insertarArista(2,5);

        System.out.println("Cantidad de Islas:" + digrafo.cantidadDeIslas());
        System.out.println("Hay ciclo: " + digrafo.hayCiclos());


    }
}
