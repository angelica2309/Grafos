package bo.edu.uagrm.ficct.inf310.grafos.nopesados;

public class UtilsGrafos {
    public static boolean esConexo(Grafo unGrafo) {
        DFS dfsGrafo = new DFS(unGrafo, 0);
        return dfsGrafo.hayCaminoATodos();
    }

}
