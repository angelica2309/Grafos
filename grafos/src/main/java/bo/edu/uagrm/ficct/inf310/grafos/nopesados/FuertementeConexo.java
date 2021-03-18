package bo.edu.uagrm.ficct.inf310.grafos.nopesados;

public class FuertementeConexo {
    private  Conexo grafoConexo;
    private boolean esFuertementeConexo;

    public FuertementeConexo(Digrafo unDigrafo){
        esFuertementeConexo = true;
        for(int i = 0; i < unDigrafo.cantidadDeVertices() && esFuertementeConexo; i++){
            DFS dfs = new DFS(unDigrafo, i);
            esFuertementeConexo = dfs.hayCaminoATodos();
        }
    }

    public boolean esFuertementeConexo(){
        return this.esFuertementeConexo;
    }
}
