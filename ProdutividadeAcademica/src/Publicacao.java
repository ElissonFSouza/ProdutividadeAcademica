import java.util.ArrayList;

public class Publicacao {
    private String titulo;
    private String conferencia;
    private int anoPublic;
    private ArrayList<Colaborador> listaAutores = new ArrayList<>();
    private Projeto projetoAssociado = null;

    public ArrayList<Colaborador> getListaAutores() {
        return listaAutores;
    }

    public Publicacao() {
        this.projetoAssociado = new Projeto();
    }

    public Publicacao(String titulo, String conferencia, int anoPublic) {
        this.titulo = titulo;
        this.conferencia = conferencia;
        this.anoPublic = anoPublic;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConferencia() {
        return conferencia;
    }
    public void setConferencia(String conferencia) {
        this.conferencia = conferencia;
    }

    public int getAnoPublic() {
        return anoPublic;
    }
    public void setAnoPublic(int anoPublic) {
        this.anoPublic = anoPublic;
    }

    public Projeto getProjetoAssociado() {
        return projetoAssociado;
    }
    public void setProjetoAssociado(Projeto projetoAssociado) {
        this.projetoAssociado = projetoAssociado;
    }

    public void adicionarAutor(Colaborador colab){
        this.listaAutores.add(colab);
    }

    public String imprimir() {
        String autores = "";
        if (getListaAutores().isEmpty()) {
            autores = " Não há autores associados à publicação.";
        } else {
            for(Colaborador colab : listaAutores){
                autores += "\n" + colab.getNome() + " (" + colab.getOcupacao() + ")";
            }
        }

        String projeto = "";
        if (projetoAssociado == null) {
            projeto = "Não há projeto associado à publicação.";
        } else {
            projeto = projetoAssociado.getTitulo();
        }

        return "- Título da publicação: " + titulo + "\n- Conferência onde foi publicada: "
                + conferencia + "\n- Ano de publicação: " + anoPublic + "\n- Autores:" + autores
                + "\n- Projeto associado: " + projeto;
    }
}
