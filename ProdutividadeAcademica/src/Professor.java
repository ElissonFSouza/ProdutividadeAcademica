import java.util.ArrayList;

public class Professor extends Colaborador{
    private ArrayList<Orientacao> listaMinhasOrientacaoes = new ArrayList<>();

    public Professor() {
        super();
    }

    public Professor(String nome, String email, String ocupacao) {
        super(nome, email, ocupacao);
    }

    public Professor(String nome, String ocupacao, String email, ArrayList<Projeto> listaMeusProjetos, ArrayList<Publicacao> listaMinhasPublicacoes) {
        super(nome, ocupacao, email, listaMeusProjetos, listaMinhasPublicacoes);
    }
}
