public class Publicacao {
    private String titulo;
    private String conferencia;
    private int ano;
    private String projetoAssociado;

    public Publicacao() {}

    public Publicacao(String titulo, String conferencia, int ano, String projetoAssociado) {
        this.titulo = titulo;
        this.conferencia = conferencia;
        this.ano = ano;
        this.projetoAssociado = projetoAssociado;
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

    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getProjetoAssociado() {
        return projetoAssociado;
    }
    public void setProjetoAssociado(String projetoAssociado) {
        this.projetoAssociado = projetoAssociado;
    }
}
