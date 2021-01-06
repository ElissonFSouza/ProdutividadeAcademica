import java.util.ArrayList;
import java.util.Collections;

public class Colaborador {
    private String nome;
    private String ocupacao;
    private String email;
    private ArrayList<Projeto> listaMeusProjetos = new ArrayList<>();    //lista dos projetos do colaborador
    private ArrayList<Publicacao> listaMinhasPublicacoes = new ArrayList<>();    //lista das publicações do colaborador

    public ArrayList<Projeto> getListaMeusProjetos() {
        return listaMeusProjetos;
    }

    public ArrayList<Publicacao> getListaMinhasPublicacoes() {
        return listaMinhasPublicacoes;
    }

    public Colaborador() {}

    public Colaborador(String nome, String email, String ocupacao) {
        this.nome = nome;
        this.email = email;
        this.ocupacao = ocupacao;
    }

    public Colaborador(String nome, String ocupacao, String email, ArrayList<Projeto> listaMeusProjetos, ArrayList<Publicacao> listaMinhasPublicacoes) {
        this.nome = nome;
        this.ocupacao = ocupacao;
        this.email = email;
        this.listaMeusProjetos = listaMeusProjetos;
        this.listaMinhasPublicacoes = listaMinhasPublicacoes;
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

    public void adicionarPublicacao(Publicacao pub){
        listaMinhasPublicacoes.add(pub);
        Collections.sort(listaMinhasPublicacoes);
    }

    public boolean verificarSituacao() {     //Retorna a quantidade de projetos em andamento que o aluno participa
        int qtd = 0;
        for(Projeto proj : listaMeusProjetos){
            if (ocupacao.equals("Aluno") && proj.getStatus().equals("Em andamento")) {
                qtd += 1;
            }
        }
        return qtd < 2;
    }

    public String imprimir() {
        String publicacoes = "";
        if (getListaMinhasPublicacoes().isEmpty()) {
            publicacoes = " Este colaborador não é autor de nenhuma publicação.";
        } else {
            for(Publicacao pub : listaMinhasPublicacoes){
                publicacoes += "\n" + pub.getTitulo() + " (" + pub.getAnoPublic() + ")";
            }
        }

        String projetos = "";
        if (getListaMeusProjetos().isEmpty()) {
            projetos = " Este colaborador não participa de nenhum projeto.";
        } else {
            for(Projeto proj : listaMeusProjetos){
                projetos += "\n" + proj.getTitulo() + " (" + proj.getStatus() + ")";
            }
        }

        return "- Nome: " + nome + "\n- Email: " + email + "\n- Ocupação: " + ocupacao
                + "\n- Projetos em que participa:" + projetos + "\n- Publicações:" + publicacoes;
    }
}
