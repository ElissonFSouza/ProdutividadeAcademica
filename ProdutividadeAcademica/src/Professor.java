public class Professor extends Colaborador{
    private String orientacoes;

    public Professor() {
        super();
    }

    public Professor(String nome, String ocupacao, String orientacoes) {
        super(nome, ocupacao);
        this.orientacoes = orientacoes;
    }

    public String getOrientacoes() {
        return orientacoes;
    }
    public void setOrientacoes(String orientacoes) {
        this.orientacoes = orientacoes;
    }
}
