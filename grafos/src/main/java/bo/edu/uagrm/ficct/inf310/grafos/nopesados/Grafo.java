package bo.edu.uagrm.ficct.inf310.grafos.nopesados;

import bo.edu.uagrm.ficct.inf310.grafos.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310.grafos.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310.grafos.excepciones.ExcepcionNroVerticesInvalido;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Grafo {

    protected List<List<Integer>> listaDeAdyacencias;

    public Grafo() {
        this.listaDeAdyacencias = new ArrayList<>();

    }

    public Grafo(int nroDeVerticesInicial) throws ExcepcionNroVerticesInvalido {
        if(nroDeVerticesInicial < 0){
            throw new ExcepcionNroVerticesInvalido();
        }
        this.listaDeAdyacencias = new ArrayList<>();
        for (int i = 0; i < nroDeVerticesInicial; i++){
            this.listaDeAdyacencias.add(new ArrayList<>());
        }
    }

    public void insertarVertice(){
        this.listaDeAdyacencias.add(new ArrayList<>());
    }

    public int cantidadDeAristas(){
        int cantidadArtistas = 0;
        int cantLazos = 0;
        for(int i = 0; i < this.listaDeAdyacencias.size(); i++){
            List<Integer> adyacentesDeUnVertice = this.listaDeAdyacencias.get(i);
            for(Integer posAdyacente : adyacentesDeUnVertice){
                if(i == posAdyacente){
                    cantLazos++;
                }else {
                    cantidadArtistas++;
                }
            }
        }
        cantidadArtistas = (cantidadArtistas / 2 ) + cantLazos;
        return cantidadArtistas;
    }

    public int cantidadDeVertices(){
        return listaDeAdyacencias.size();
    }

    protected void validarVertice(int posicionDeVertice){
        if(posicionDeVertice < 0 ||
                posicionDeVertice >= cantidadDeVertices()){
            throw  new IllegalArgumentException("El vértice" + posicionDeVertice + "no pertenece al grafo");
        }
    }

    public void insertarArista(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaYaExiste{
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        if(this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)){
            throw new ExcepcionAristaYaExiste();
        }
        List<Integer> adyacenciasDelOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        adyacenciasDelOrigen.add(posVerticeDestino);
        if(posVerticeOrigen != posVerticeDestino){
            List<Integer> adyacenciasDelDestino = this.listaDeAdyacencias.get(posVerticeDestino);
            adyacenciasDelDestino.add(posVerticeOrigen);
        }
    }

    public  boolean existeAdyacencia(int posVerticeOrigen, int posVerticeDestino){
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        List<Integer> adyacenciasDelOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        return adyacenciasDelOrigen.contains(posVerticeDestino);
    }

    public void eliminarVertice(int posVerticeAEliminar){
        validarVertice(posVerticeAEliminar);
        this.listaDeAdyacencias.remove(posVerticeAEliminar);
        for (List<Integer> adyacentesDeUnVertice : this.listaDeAdyacencias){
            int posicionDeVerticeEnAdy = adyacentesDeUnVertice.indexOf(posVerticeAEliminar);
            if(posicionDeVerticeEnAdy >= 0){
                adyacentesDeUnVertice.remove(posicionDeVerticeEnAdy);
            }
            for(int i= 0; i < adyacentesDeUnVertice.size(); i++){
                int posicionAdyacente = adyacentesDeUnVertice.get(i);
                if(posicionAdyacente > posVerticeAEliminar){
                    adyacentesDeUnVertice.set(i,posicionAdyacente - 1);
                }
            }
        }
    }

    public int gradoDeVertice(int posDeVertice){
        validarVertice(posDeVertice);
        List<Integer> adyacenciasDelVertice = this.listaDeAdyacencias.get(posDeVertice);
        return adyacenciasDelVertice.size();
    }

    public Iterable<Integer> adyacentesDeVertice(int posDeVertice){
        validarVertice(posDeVertice);
        List<Integer> adyacenciasDelVertice = this.listaDeAdyacencias.get(posDeVertice);
        Iterable<Integer> it = adyacenciasDelVertice;
        return it;
    }

    /*hacer nosotros*/
    public void eliminarArista(int posVerticeOrigen, int posVerticeDestino ) throws ExcepcionAristaNoExiste {
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        if(!this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)){
            throw new ExcepcionAristaNoExiste();
        }
        List<Integer> adyacenciasDelOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        int posicion = adyacenciasDelOrigen.indexOf(posVerticeDestino);
        adyacenciasDelOrigen.remove(posicion);
        if(posVerticeOrigen != posVerticeDestino){
            List<Integer> adyacenciasDelDestino = this.listaDeAdyacencias.get(posVerticeOrigen);
            posicion = adyacenciasDelDestino.indexOf(posVerticeDestino);
            adyacenciasDelDestino.remove(posicion);
        }
    }

    /**
     * 5. Número de Islas
     * @return
     */
    public int cantidadDeIslas(){
        DFS dfs = new DFS(Grafo.this,0);
        return cantidadDeIslas( 0, 0, dfs);
    }

    protected int cantidadDeIslas(int verticeDeProceso, int cantIslas, DFS dfs){
      validarVertice(verticeDeProceso);
                if(cantIslas != 0){
                dfs.continuarDFS(verticeDeProceso);
                }
        cantIslas++;
        if(dfs.controlMarcados.estanTodosMarcados()){
            return cantIslas;
        }else{
                for (int i = 0; i < listaDeAdyacencias.size(); i++){
                    if(!dfs.controlMarcados.estaMarcado(i)){
                      verticeDeProceso = i;
                      cantIslas = cantidadDeIslas(verticeDeProceso, cantIslas,dfs);
                    }
                }
                return cantIslas;
        }
    }



}
