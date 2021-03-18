package bo.edu.uagrm.ficct.inf310.grafos.excepciones;

public class ExcepcionNroVerticesInvalido extends Exception {
    public ExcepcionNroVerticesInvalido() {
        super("Nro de vertices inválido");
    }

    public ExcepcionNroVerticesInvalido(String message) {
        super(message);
    }
}
