public class Orientacao {
    private String nomeProf;
    private String nomeColab;
    private String tituloProj;

    public Orientacao() {
    }

    public Orientacao(String nomeProf, String nomeColab, String tituloProj) {
        this.nomeProf = nomeProf;
        this.nomeColab = nomeColab;
        this.tituloProj = tituloProj;
    }

    public String getNomeProf() {
        return nomeProf;
    }
    public void setNomeProf(String nomeProf) {
        this.nomeProf = nomeProf;
    }

    public String getNomeColab() {
        return nomeColab;
    }
    public void setNomeColab(String nomeColab) {
        this.nomeColab = nomeColab;
    }

    public String getTituloProj() {
        return tituloProj;
    }
    public void setTituloProj(String tituloProj) {
        this.tituloProj = tituloProj;
    }
}
