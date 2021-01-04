import java.util.ArrayList;

public class Publicacao {
    private String titulo;
    private String conferencia;
    private int anoPublic;
    private ArrayList<Colaborador> listaAutores = new ArrayList<>();

    public ArrayList<Colaborador> getListaAutores() {
        return listaAutores;
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

    public String imprimir() {
        String autores = "";
        if (getListaAutores().isEmpty()) {
            autores = " Não há autores associados à publicação.";
        } else {
            for(Colaborador colab : listaAutores){
                autores += "\n" + colab.getNome() + " (" + colab.getOcupacao() + ")";
            }
        }

        return "Título da publicação: " + titulo + "\nConferência onde foi publicada: "
                + conferencia + "\nAno de publicação: " + anoPublic + autores;
    }
}
