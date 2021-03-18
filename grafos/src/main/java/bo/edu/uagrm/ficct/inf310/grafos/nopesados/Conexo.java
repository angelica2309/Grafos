package bo.edu.uagrm.ficct.inf310.grafos.nopesados;

public class Conexo {
    private DFS dfsGrafo;
    public Conexo(Grafo unGrafo){
        dfsGrafo = new DFS(unGrafo, 0);
    }

    public boolean esConexo(){
        return dfsGrafo.hayCaminoATodos();
    }


}
