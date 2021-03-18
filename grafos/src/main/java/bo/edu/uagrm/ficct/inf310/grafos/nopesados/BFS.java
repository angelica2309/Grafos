package bo.edu.uagrm.ficct.inf310.grafos.nopesados;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {
    private List<Boolean> marcados;
    private List<Integer> recorrido;
    private Grafo grafo;
    public  BFS(Grafo unGrafo, int posVerticePartida){
        this.grafo = unGrafo;
        grafo.validarVertice(posVerticePartida);
        desmarcarTodos();
        ejecutarBFS(posVerticePartida);
    }

    private void desmarcarTodos(){
        marcados = new ArrayList<>();
        recorrido = new ArrayList<>();
        for(int i = 0; i < grafo.cantidadDeVertices(); i++){
            marcados.add(Boolean.FALSE);
        }
    }

    private void marcarVertice(int posVertice){
        marcados.set(posVertice, Boolean.TRUE);
    }

    private void  ejecutarBFS(int posVertice){
        Queue<Integer> cola = new LinkedList<>();
        cola.offer(posVertice);
        marcarVertice(posVertice);
        do{
            int posVerticeEnTurno = cola.poll();
            recorrido.add(posVerticeEnTurno);
            Iterable<Integer> adyacenteEnTuro = grafo.adyacentesDeVertice(posVerticeEnTurno);
            for(Integer posVerticeAdyacente : adyacenteEnTuro){
                if(!estaMarcado(posVerticeAdyacente)){
                    cola.add(posVerticeAdyacente);
                    marcarVertice(posVerticeAdyacente);
                }
            }
        }while (!cola.isEmpty());
    }

    private boolean estaMarcado(int posVertice){
        return marcados.get(posVertice);
    }
    public boolean hayCaminoA(int posVertice){
        grafo.validarVertice(posVertice);
        return estaMarcado(posVertice);
    }

    public Iterable<Integer>  elRecorrido(){
        return recorrido;
    }
}
