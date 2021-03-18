package bo.edu.uagrm.ficct.inf310.grafos.nopesados;

import java.util.ArrayList;
import java.util.List;

public class UtilsRecorrido {
    private List<Boolean> marcados;
    private   int nroVertices;

    public UtilsRecorrido(int nroVertice){
        this.nroVertices = nroVertice;
    }


    public void desmarcarTodos(){
        marcados = new ArrayList<>();
        for(int i = 0; i <this.nroVertices; i++){
            marcados.add(Boolean.FALSE);
        }
    }

    public void marcarVertice(int posVertice){
        marcados.set(posVertice, Boolean.TRUE);
    }


    public boolean estaMarcado(int posVertice){
        return marcados.get(posVertice);
    }


    public boolean estanTodosMarcados(){
        for(Boolean marcados : this.marcados){
            if(!marcados){
                return false;
            }
        }
        return true;
    }
}
