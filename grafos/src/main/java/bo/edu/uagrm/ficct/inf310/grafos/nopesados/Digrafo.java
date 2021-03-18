package bo.edu.uagrm.ficct.inf310.grafos.nopesados;

import bo.edu.uagrm.ficct.inf310.grafos.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310.grafos.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310.grafos.excepciones.ExcepcionNroVerticesInvalido;

import java.util.List;

public class Digrafo extends Grafo{

    public Digrafo() {
    }

    public Digrafo(int nroDeVerticesInicial) throws ExcepcionNroVerticesInvalido {
        super(nroDeVerticesInicial);
    }

    @Override
    public void insertarArista(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaYaExiste {
        super.validarVertice(posVerticeOrigen);
        super.validarVertice(posVerticeDestino);
        if(super.existeAdyacencia(posVerticeOrigen, posVerticeDestino)){
            throw new ExcepcionAristaYaExiste();
        }
        List<Integer> adyacenciasDelOrigen = super.listaDeAdyacencias.get(posVerticeOrigen);
        adyacenciasDelOrigen.add(posVerticeDestino);
    }

    @Override
    public int gradoDeVertice(int posDeVertice) {
        throw new UnsupportedOperationException("No soportado en grafos dirigidos");
    }

    public int gradoDeSalida(int posDeVertice){
        return super.gradoDeVertice(posDeVertice);
    }

    public int gradoDeEntrada(int posDeVertice){
        super.validarVertice(posDeVertice);
        int cantidad = 0;
        for(List<Integer> adyacentesDeUnVertice : super.listaDeAdyacencias){
            for(Integer posAdyacente : adyacentesDeUnVertice){
                if(posAdyacente == posDeVertice){
                    cantidad++;
                }
            }
        }
        return cantidad;
    }
/*metodos para implementar*/
    @Override
    public int cantidadDeAristas() {
      return 0;
    }

    @Override
    public void eliminarArista(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaNoExiste{
        throw new ExcepcionAristaNoExiste();
    }

    /**
     * 6. Número de Islas Digrafo
     * @return
     */
    public  int cantDeIslas(){
        DFS dfs = new DFS(Digrafo.this,0);
        return cantDeIslas(0,0, dfs);
    }

    private  int cantDeIslas( int verticeDeProceso, int cantIslas,DFS dfs) {
        super.validarVertice(verticeDeProceso);
        if(cantIslas != 0) {
            dfs.continuarDFS(verticeDeProceso);
        }
        if(dfs.controlMarcados.estanTodosMarcados()){
            cantIslas ++;
            return cantIslas;
        }else{
            for (int i = 0; i < listaDeAdyacencias.size(); i++){
                    if (!dfs.controlMarcados.estaMarcado(i)) {
                        for (int j = 0; j < i; j++){
                            if(super.existeAdyacencia(i,j)) {
                                verticeDeProceso = i;
                                cantIslas = cantidadDeIslas(verticeDeProceso, cantIslas, dfs);
                            }
                        }
                        cantIslas ++;
                        verticeDeProceso = i;
                        cantIslas = cantidadDeIslas(verticeDeProceso, cantIslas, dfs);
                    }

            }
            return cantIslas;
        }
    }



}
