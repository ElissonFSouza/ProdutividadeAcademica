public class Orientacao {
    private Professor profOrientador = null;
    private Colaborador alunoOrientado = null;
    private Projeto projetoAssociado = null;

    public Orientacao() {
        this.projetoAssociado = new Projeto();
    }

    public Orientacao(Professor profOrientador, Colaborador alunoOrientado) {
        this.profOrientador = profOrientador;
        this.alunoOrientado = alunoOrientado;
    }

    public Orientacao(Professor profOrientador, Colaborador alunoOrientado, Projeto projetoAssociado) {
        this.profOrientador = profOrientador;
        this.alunoOrientado = alunoOrientado;
        this.projetoAssociado = projetoAssociado;
    }

    public Professor getProfOrientador() {
        return profOrientador;
    }
    public void setProfOrientador(Professor profOrientador) {
        this.profOrientador = profOrientador;
    }

    public Colaborador getAlunoOrientado() {
        return alunoOrientado;
    }
    public void setAlunoOrientado(Colaborador alunoOrientado) {
        this.alunoOrientado = alunoOrientado;
    }

    public Projeto getProjetoAssociado() {
        return projetoAssociado;
    }
    public void setProjetoAssociado(Projeto projetoAssociado) {
        this.projetoAssociado = projetoAssociado;
    }
}
