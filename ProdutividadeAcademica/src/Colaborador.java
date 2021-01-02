import java.util.ArrayList;

public class Colaborador {
    private String nome;
    private String ocupacao;
    private String email;
    private ArrayList<Projeto> listaMeusProjetos = new ArrayList<>(); //lista dos projetos do colaborador

    public ArrayList<Projeto> getListaMeusProjetos() {
        return listaMeusProjetos;
    }

    public Colaborador() {}

    public Colaborador(String nome, String email, String ocupacao) {
        this.nome = nome;
        this.email = email;
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

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void adicionarProjeto(Projeto proj){
        listaMeusProjetos.add(proj);
    }

    public boolean verificarSituacao() {
        int qtd = 0;
        for(Projeto proj : listaMeusProjetos){
            if (ocupacao.equals("Aluno") && proj.getStatus().equals("Em andamento")) {
                qtd += 1;
            }
        }
        return qtd < 2;
    }

    public String imprimir() {
        String projetos = "";

        if (getListaMeusProjetos().isEmpty()) {
            projetos = "Este colaborador não participa de nenhum projeto.";
        } else {
            for(Projeto proj : listaMeusProjetos){
                projetos += "\n" + proj.getTitulo() + " (" + proj.getStatus() + ")";
            }
        }

        return "Nome: " + nome + "\nEmail: " + email + "\nOcupação: " + ocupacao + "\nProjetos em que participa:" + projetos;
    }
}
