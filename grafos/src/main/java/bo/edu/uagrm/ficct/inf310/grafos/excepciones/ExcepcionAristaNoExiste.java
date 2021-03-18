package bo.edu.uagrm.ficct.inf310.grafos.excepciones;

public class ExcepcionAristaNoExiste extends Exception {
    public ExcepcionAristaNoExiste() {
        super("Arista No Existe");
    }

    public ExcepcionAristaNoExiste(String message) {
        super(message);
    }
}
