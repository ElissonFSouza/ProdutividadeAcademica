import java.util.ArrayList;

public class Laboratorio {
    private static final ArrayList<Projeto> listaProjetos = new ArrayList<>();
    private static final ArrayList<Publicacao> listaPublicacoes = new ArrayList<>();
    private static final ArrayList<Colaborador> listaColaboradores = new ArrayList<>();

    public static ArrayList<Projeto> getListaProjetos() {
        return listaProjetos;
    }

    public static ArrayList<Publicacao> getListaPublicacoes() {
        return listaPublicacoes;
    }

    public static ArrayList<Colaborador> getListaColaboradores() {
        return listaColaboradores;
    }

    public static void adicionarProjeto(Projeto proj) {
        listaProjetos.add(proj);
    }

    public static void adicionarPublicacao(Publicacao pub) {
        listaPublicacoes.add(pub);
    }

    public static void adicionarColaborador(Colaborador colab) {
        listaColaboradores.add(colab);
    }

    /*public static String listarProjetos() { //Imprime os dados de todos os projetos cadastrados
        String saida = "";
        int i = 1;
        for(Projeto proj : listaProjetos){
            saida += "\n- Projeto nº " + (i++) + "\n";
            saida += proj.imprimir();
        }
        return saida;
    }*/

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
                } else {
                    break;
                }
            }
        }
        return false;
    }

    public static boolean verificarStatusProj(String titulo) { //Verifica se o status do projeto é "Em elaboração".
        for(Projeto proj : listaProjetos) {
            if (proj.getTitulo().equalsIgnoreCase(titulo)) {
                return proj.getStatus().equals("Em elaboração");
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

    public static boolean verificarPublicaao(String titulo) { //Verifica se existe uma publicação com o título digitado e retorna True ou False
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

    public static void associarProj(String titulo, String nome){ //Cadastra o colaborador na lista de participantes do projeto
        Colaborador colab2 = null;                           //e inclui o projeto na lista de projetos em que o colaborador participa
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

    public static void associarPub(String titulo, String nome){ //Cadastra o colaborador na lista de autores da publicação
        Colaborador colab2 = null;                              //e inclui a publicação na lista de publicações do colaborador
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
