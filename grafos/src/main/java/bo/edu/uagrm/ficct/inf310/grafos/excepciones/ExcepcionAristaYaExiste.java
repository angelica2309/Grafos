package bo.edu.uagrm.ficct.inf310.grafos.excepciones;

public class ExcepcionAristaYaExiste extends Exception {
    public ExcepcionAristaYaExiste() {
        super("Arista Ya Existe");
    }

    public ExcepcionAristaYaExiste(String message) {
        super(message);
    }
}
