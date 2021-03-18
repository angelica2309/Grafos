package bo.edu.uagrm.ficct.inf310.grafos.pesados;

import bo.edu.uagrm.ficct.inf310.grafos.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310.grafos.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310.grafos.excepciones.ExcepcionNroVerticesInvalido;

import java.util.ArrayList;
import java.util.List;

public class GrafoPesado {

    protected List<List<AdyacenteConPeso>> listaDeAdyacencias;

    public GrafoPesado() {
        this.listaDeAdyacencias = new ArrayList<>();

    }

    public GrafoPesado(int nroDeVerticesInicial) throws ExcepcionNroVerticesInvalido {
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
            List<AdyacenteConPeso> adyacentesDeUnVertice = this.listaDeAdyacencias.get(i);
            for(AdyacenteConPeso posAdyacente : adyacentesDeUnVertice){
                if(i == posAdyacente.getIndiceVertice()){
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

    public void insertarArista(int posVerticeOrigen, int posVerticeDestino, double costo) throws ExcepcionAristaYaExiste{
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        if(this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)){
            throw new ExcepcionAristaYaExiste();
        }
        List<AdyacenteConPeso> adyacenciasDelOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        adyacenciasDelOrigen.add(new AdyacenteConPeso(posVerticeDestino, costo));
        if(posVerticeOrigen != posVerticeDestino){
            List<AdyacenteConPeso> adyacenciasDelDestino = this.listaDeAdyacencias.get(posVerticeDestino);
            adyacenciasDelDestino.add(new AdyacenteConPeso(posVerticeOrigen, costo));
        }
    }

    public  boolean existeAdyacencia(int posVerticeOrigen, int posVerticeDestino){
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        List<AdyacenteConPeso> adyacenciasDelOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        AdyacenteConPeso destino = new AdyacenteConPeso(posVerticeDestino);
        return adyacenciasDelOrigen.contains(destino);
    }

    public void eliminarVertice(int posVerticeAEliminar){
        validarVertice(posVerticeAEliminar);
        this.listaDeAdyacencias.remove(posVerticeAEliminar);
        for (List<AdyacenteConPeso> adyacentesDeUnVertice : this.listaDeAdyacencias){
            AdyacenteConPeso adyacenteConPeso = new AdyacenteConPeso(posVerticeAEliminar);
            int posicionDeVerticeEnAdy = adyacentesDeUnVertice.indexOf(posVerticeAEliminar);
            if(posicionDeVerticeEnAdy >= 0){
                adyacentesDeUnVertice.remove(posicionDeVerticeEnAdy);
            }
            for(int i= 0; i < adyacentesDeUnVertice.size(); i++){
                AdyacenteConPeso posicionAdyacente = adyacentesDeUnVertice.get(i);
                if(posicionAdyacente.getIndiceVertice() > posVerticeAEliminar){
                    posicionAdyacente.setIndiceVertice(posicionAdyacente.getIndiceVertice() - 1 );
                    //adyacentesDeUnVertice.set(i,posicionAdyacente - 1);
                }
            }
        }
    }

    public int gradoDeVertice(int posDeVertice){
        validarVertice(posDeVertice);
        List<AdyacenteConPeso> adyacenciasDelVertice = this.listaDeAdyacencias.get(posDeVertice);
        return adyacenciasDelVertice.size();
    }

    public Iterable<Integer> adyacentesDeVertice(int posDeVertice){
        validarVertice(posDeVertice);
        List<AdyacenteConPeso> adyacenciasDelVertice = this.listaDeAdyacencias.get(posDeVertice);
        List<Integer> adyacentesDelVertice = new ArrayList<>();
        for(AdyacenteConPeso adyacente : adyacenciasDelVertice){
            adyacentesDelVertice.add(adyacente.getIndiceVertice());
        }
        Iterable<Integer> it = adyacentesDelVertice;
        return it;
    }

    /*hacer nosotros*/
    public void eliminarArista(int posVerticeOrigen, int posVerticeDestino ) throws ExcepcionAristaNoExiste {
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        if(!this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)){
            throw new ExcepcionAristaNoExiste();
        }
        List<AdyacenteConPeso> adyacenciasDelOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        int posicion = adyacenciasDelOrigen.indexOf(posVerticeDestino);
        adyacenciasDelOrigen.remove(posicion);
        if(posVerticeOrigen != posVerticeDestino){
            List<AdyacenteConPeso> adyacenciasDelDestino = this.listaDeAdyacencias.get(posVerticeOrigen);
            posicion = adyacenciasDelDestino.indexOf(posVerticeDestino);
            adyacenciasDelDestino.remove(posicion);
        }
    }

}
