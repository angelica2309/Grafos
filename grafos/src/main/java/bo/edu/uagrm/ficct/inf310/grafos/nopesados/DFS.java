package bo.edu.uagrm.ficct.inf310.grafos.nopesados;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DFS {
    private List<Integer> recorrido;
    private Grafo grafo;
    protected UtilsRecorrido controlMarcados;
    public DFS(Grafo unGrafo, int posVerticePartida){
        this.grafo = unGrafo;
        grafo.validarVertice(posVerticePartida);
        recorrido = new ArrayList<>();
        controlMarcados = new UtilsRecorrido(grafo.cantidadDeVertices());
        controlMarcados.desmarcarTodos();
        continuarDFS(posVerticePartida);
    }


    public void  continuarDFS(int posVertice){
        controlMarcados.marcarVertice(posVertice);
        recorrido.add(posVertice);
        Iterable<Integer> adyacenteEnTurno = grafo.adyacentesDeVertice(posVertice);
        for(Integer posVerticeAdyacente : adyacenteEnTurno){
            if(!controlMarcados.estaMarcado(posVerticeAdyacente)){
                continuarDFS(posVerticeAdyacente);
            }
        }
    }

    public boolean hayCaminoA(int posVertice){
        grafo.validarVertice(posVertice);
        return controlMarcados.estaMarcado(posVertice);
    }

    public Iterable<Integer>  elRecorrido(){
        return recorrido;
    }

    public boolean hayCaminoATodos(){
        return controlMarcados.estanTodosMarcados();
    }
}
