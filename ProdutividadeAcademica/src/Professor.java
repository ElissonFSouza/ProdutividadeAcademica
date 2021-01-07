import java.util.ArrayList;

public class Professor extends Colaborador{
    private ArrayList<Orientacao> listaMinhasOrientacoes = new ArrayList<>();     //Lista das orientações do colaborador professor

    public Professor() {
        super();
    }

    public Professor(String nome, String email, String ocupacao) {
        super(nome, email, ocupacao);
    }

    public Professor(String nome, String ocupacao, String email, ArrayList<Projeto> listaMeusProjetos, ArrayList<Publicacao> listaMinhasPublicacoes) {
        super(nome, ocupacao, email, listaMeusProjetos, listaMinhasPublicacoes);
    }

    public Professor(String nome, String email, String ocupacao, Orientacao ori) {
        super(nome, email, ocupacao);
        this.listaMinhasOrientacoes.add(ori);
    }

    public ArrayList<Orientacao> getListaMinhasOrientacoes() {
        return listaMinhasOrientacoes;
    }
    public void setListaMinhasOrientacoes(ArrayList<Orientacao> listaMinhasOrientacoes) {
        this.listaMinhasOrientacoes = listaMinhasOrientacoes;
    }

    public void adicionarOrientacao(Orientacao ori) {
        listaMinhasOrientacoes.add(ori);
    }

    @Override
    public String imprimir() {
        String orientacoes = "";
        if (getListaMinhasOrientacoes().isEmpty()) {
            orientacoes = " Este colaborador não possui orientações.";
        } else {
            for(Orientacao ori : listaMinhasOrientacoes) {
                if (ori.getTituloProj() != null) {
                    orientacoes += "\n  " + ori.getNomeColab() + " (" + ori.getTituloProj() + ")";
                } else {
                    orientacoes += "\n  " + ori.getNomeColab();
                }

            }
        }
        return super.imprimir() + "\n- Orientações:" + orientacoes;
    }
}
