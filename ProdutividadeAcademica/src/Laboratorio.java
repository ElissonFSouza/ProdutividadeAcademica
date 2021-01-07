import java.util.ArrayList;
import java.util.Collections;

public class Laboratorio {
    private static final ArrayList<Projeto> listaProjetos = new ArrayList<>();
    private static final ArrayList<Publicacao> listaPublicacoes = new ArrayList<>();
    private static final ArrayList<Orientacao> listaOrientacoes = new ArrayList<>();
    private static final ArrayList<Colaborador> listaColaboradores = new ArrayList<>();

    public static ArrayList<Projeto> getListaProjetos() {
        return listaProjetos;
    }

    public static ArrayList<Publicacao> getListaPublicacoes() {
        return listaPublicacoes;
    }

    public static ArrayList<Orientacao> getListaOrientacoes() {
        return listaOrientacoes;
    }

    public static ArrayList<Colaborador> getListaColaboradores() {
        return listaColaboradores;
    }

    public static void adicionarProjeto(Projeto proj) {
        listaProjetos.add(proj);
    }

    public static void adicionarPublicacao(Publicacao pub) {
        listaPublicacoes.add(pub);
        Collections.sort(listaPublicacoes);
    }

    public static void adicionarOrientacao(Orientacao ori, String nomeProf, String tituloProj) {
        listaOrientacoes.add(ori);
        for(Colaborador colab : listaColaboradores) {
            if (colab.getNome().equalsIgnoreCase(nomeProf)) {
                ((Professor) colab).adicionarOrientacao(ori);
                break;
            }
        }
        for (Projeto proj : listaProjetos) {
            if (proj.getTitulo().equalsIgnoreCase(tituloProj)) {
                proj.adicionarOrientacao(ori);
                break;
            }
        }

    }

    public static void adicionarColaborador(Colaborador colab) {
        listaColaboradores.add(colab);
    }

    public static int qtdProjetosElaboracao() { //Retorna a quantidade de projetos em elaboração
        int qtd = 0;
        for(Projeto proj : listaProjetos) {
            if (proj.getStatus().equals("Em elaboração")) {
                qtd++;
            }
        }
        return qtd;
    }

    public static int qtdProjetosAndamento() { //Retorna a quantidade de projetos em andamento
        int qtd = 0;
        for(Projeto proj : listaProjetos) {
            if (proj.getStatus().equals("Em andamento")) {
                qtd++;
            }
        }
        return qtd;
    }

    public static int qtdProjetosConcluidos() { //Retorna a quantidade de projetos concluídos
        int qtd = 0;
        for(Projeto proj : listaProjetos) {
            if (proj.getStatus().equals("Concluído")) {
                qtd++;
            }
        }
        return qtd;
    }

    public static boolean verificarSituacaoAluno(String titulo) {
        for (Projeto proj : listaProjetos) {
            if (proj.getTitulo().equalsIgnoreCase(titulo)) {
                return proj.verificarSituacaoAluno();
            }
        }
        return false;
    }

    public static boolean verificarProfessorProj(String titulo) { //Verifica se existe pelo menos 1 professor alocado ao projeto
        for (Projeto proj : listaProjetos) {
            if (proj.getTitulo().equalsIgnoreCase(titulo)) {
                return proj.verificarProfessor();
            }
        }
        return false;
    }

    public static boolean verificarDadosProj(String titulo) { //Verifica se todos os dados do projeto foram inseridos
        for (Projeto proj : listaProjetos) {
            if (proj.getTitulo().equalsIgnoreCase(titulo)) {
                if (proj.getAgenciaFinanciadora() != null && !proj.getAgenciaFinanciadora().isEmpty()
                        && proj.getObjetivo() != null && !proj.getObjetivo().isEmpty()
                        && proj.getDescricao() != null && !proj.getDescricao().isEmpty()) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    public static boolean verificarStatusProj(String titulo, String status) {     //Verifica se o status do projeto é igual ao status passado como parâmetro.
        for(Projeto proj : listaProjetos) {
            if (proj.getTitulo().equalsIgnoreCase(titulo)) {
                return proj.getStatus().equals(status);
            }
        }
        return false;
    }

    public static boolean verificarPubProjeto(String titulo) {
        for(Projeto proj : listaProjetos) {
            if (proj.getTitulo().equalsIgnoreCase(titulo)) {
                if (!proj.getListaPublicacoesAssociadas().isEmpty()) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    public static boolean verificarProjeto(String titulo) { //Verifica se existe um projeto com o nome digitado e retorna True ou False
        for(Projeto proj : listaProjetos) {
            if (proj.getTitulo().equalsIgnoreCase(titulo)) {
                return true;
            }
        }
        return false;
    }

    public static boolean verificarOcupacaoColab(String nome, String ocupacao) {
        for(Colaborador colab : listaColaboradores) {
            if (colab.getNome().equalsIgnoreCase(nome)) {
                if (colab.getOcupacao().equals(ocupacao)) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    public static String pesquisarProjeto(String titulo) { //Verifica se existe um projeto com o nome digitado e, caso exista, retorna uma String com os dados
        for(Projeto proj : listaProjetos) {
            if (proj.getTitulo().equalsIgnoreCase(titulo)) {
                return "\nProjeto encontrado.\n" + proj.imprimir();
            }
        }
        return "\nProjeto não encontrado.\n";
    }

    public static boolean verificarColaborador(String nome) { //Verifica se existe um colaborador com o nome digitado e retorna True ou False
        for(Colaborador colab : listaColaboradores) {
            if (colab.getNome().equalsIgnoreCase(nome)) {
                return true;
            }
        }
        return false;
    }

    public static String pesquisarColaborador(String nome) { //Verifica se existe um colaborador com o nome digitado e, caso exista, retorna uma String com os dados
        for(Colaborador colab : listaColaboradores) {
            if (colab.getNome().equalsIgnoreCase(nome)) {
                return "\nColaborador encontrado.\n" + colab.imprimir();
            }
        }
        return "\nColaborador não encontrado.\n";
    }

    public static boolean verificarPublicacao(String titulo) {     //Verifica se existe uma publicação com o título digitado e retorna True ou False
        for(Publicacao pub : listaPublicacoes) {
            if (pub.getTitulo().equalsIgnoreCase(titulo)) {
                return true;
            }
        }
        return false;
    }

    public static String pesquisarPublicacao(String titulo) { //Verifica se existe uma publicação com o nome digitado e, caso exista, retorna uma String com os dados
        for(Publicacao pub : listaPublicacoes) {
            if (pub.getTitulo().equalsIgnoreCase(titulo)) {
                return "\nPublicação encontrada.\n" + pub.imprimir();
            }
        }
        return "\nPublicação não encontrada.\n";
    }

    public static void associarColabProj(String titulo, String nome) { //Cadastra o colaborador na lista de participantes do projeto
        Colaborador colab2 = null;                                     //e inclui o projeto na lista de projetos em que o colaborador participa
        for(Colaborador colab : listaColaboradores) {
            if (colab.getNome().equalsIgnoreCase(nome)) {
                colab2 = colab;
                break;
            }
        }
        Projeto proj2 = null;
        for(Projeto proj : listaProjetos){
            if (proj.getTitulo().equalsIgnoreCase(titulo)){
                proj2 = proj;
                break;
            }
        }
        if (colab2 != null && proj2 != null) {
            colab2.adicionarProjeto(proj2);
            proj2.adicionarParticipante(colab2);
        }
    }

    public static void associarColabPub(String titulo, String nome) { //Cadastra o colaborador na lista de autores da publicação
        Colaborador colab2 = null;                                    //e inclui a publicação na lista de publicações do colaborador
        for(Colaborador colab : listaColaboradores) {
            if (colab.getNome().equalsIgnoreCase(nome)) {
                colab2 = colab;
                break;
            }
        }
        Publicacao pub2 = null;
        for(Publicacao pub : listaPublicacoes){
            if (pub.getTitulo().equalsIgnoreCase(titulo)){
                pub2 = pub;
                break;
            }
        }
        if (colab2 != null && pub2 != null) {
            colab2.adicionarPublicacao(pub2);
            pub2.adicionarAutor(colab2);
        }
    }

    public static void associarPubProj(String tituloProj, String tituloPub) { //Inclui a publicação na lista de publicações do projeto
        Publicacao pub2 = null;                                               //e vice-versa
        for(Publicacao pub : listaPublicacoes) {
            if (pub.getTitulo().equalsIgnoreCase(tituloPub)) {
                pub2 = pub;
                break;
            }
        }
        Projeto proj2 = null;
        for(Projeto proj : listaProjetos){
            if (proj.getTitulo().equalsIgnoreCase(tituloProj)) {
                proj2 = proj;
                break;
            }
        }
        if (pub2 != null && proj2 != null) {
            pub2.setProjetoAssociado(proj2);
            proj2.adicionarPublicacao(pub2);
        }
    }

    public static void alterarStatus(String titulo) {
        for(Projeto proj : listaProjetos) {
            if (proj.getTitulo().equalsIgnoreCase(titulo)) {
                if (proj.getStatus().equals("Em elaboração")) {
                    proj.setStatus("Em andamento");
                } else if (proj.getStatus().equals("Em andamento")) {
                    proj.setStatus("Concluído");
                }
                break;
            }
        }
    }
}
