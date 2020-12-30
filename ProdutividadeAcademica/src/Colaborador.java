public class Colaborador {
    private String nome;
    private String ocupacao;

    public Colaborador() {}

    public Colaborador(String nome, String ocupacao) {
        this.nome = nome;
        this.ocupacao = ocupacao;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getOcupacao() {
        return ocupacao;
    }
    public void setOcupacao(String ocupacao) {
        this.ocupacao = ocupacao;
    }

    public String imprimir() {
        return "Nome: " + nome + "\nOcupação: " + ocupacao + "\n";
    }
}
