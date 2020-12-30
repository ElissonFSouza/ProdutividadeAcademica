import java.lang.reflect.Array;
import java.util.ArrayList;

public class Laboratorio {
    private static ArrayList<Projeto> listaProjetos = new ArrayList<>();
    private static ArrayList<Publicacao> listaPublicacoes = new ArrayList<>();
    private static ArrayList<Colaborador> listaColaboradores = new ArrayList<>();

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

    public static String listarProjetos(){
        String saida = "";
        int i = 1;
        for(Projeto proj : listaProjetos){
            saida += "\n- Projeto nº " + (i++) + "\n";
            saida += proj.imprimir();
        }
        return saida;
    }

    public static String pesquisarProjeto(String titulo){
        for(Projeto proj : listaProjetos) {
            if (proj.getTitulo().equalsIgnoreCase(titulo)) {
                return "Projeto encontrado.\nDados do projeto:\n" + proj.imprimir();
            }
        }
        return "Projeto não encontrado.\n";
    }

    public static String pesquisarColaborador(String nome){
        for(Colaborador colab : listaColaboradores) {
            if (colab.getNome().equalsIgnoreCase(nome)) {
                return "Colaborador encontrado.\nDados do colaborador:\n" + colab.imprimir();
            }
        }
        return "Colaborador não encontrado.\n";
    }

}
