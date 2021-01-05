import java.util.ArrayList;

public class Publicacao {
    private String titulo;
    private String conferencia;
    private int anoPublic;
    private ArrayList<Colaborador> listaAutores = new ArrayList<>();
    private ArrayList<Projeto> listaProjetosAssociados = new ArrayList<>();

    public ArrayList<Colaborador> getListaAutores() {
        return listaAutores;
    }

    public ArrayList<Projeto> getListaProjetosAssociados() {
        return listaProjetosAssociados;
    }

    public Publicacao() {}

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

    public void adicionarAutor(Colaborador colab){
        listaAutores.add(colab);
    }

    public void adicionarProjeto(Projeto proj) {
        listaProjetosAssociados.add(proj);
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

        String projetos = "";
        if (getListaProjetosAssociados().isEmpty()) {
            projetos = " Não há projetos associados à publicação.";
        } else {
            for(Projeto proj : listaProjetosAssociados){
                projetos += "\n" + proj.getTitulo() + " (" + proj.getStatus() + ")";
            }
        }

        return "- Título da publicação: " + titulo + "\n- Conferência onde foi publicada: "
                + conferencia + "\n- Ano de publicação: " + anoPublic + "\n- Autores:" + autores
                + "\n- Projetos associados:" + projetos;
    }
}
