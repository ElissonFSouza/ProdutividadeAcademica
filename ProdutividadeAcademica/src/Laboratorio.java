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

    public static boolean verificarProjeto(String titulo) {
        for(Projeto proj : listaProjetos) {
            if (proj.getTitulo().equalsIgnoreCase(titulo)) {
                return true;
            }
        }
        return false;
    }

    public static String pesquisarProjeto(String titulo) {
        for(Projeto proj : listaProjetos) {
            if (proj.getTitulo().equalsIgnoreCase(titulo)) {
                return "\nProjeto encontrado.\n" + proj.imprimir();
            }
        }
        return "\nProjeto não encontrado.\n";
    }

    public static boolean verificarColaborador(String nome) {
        for(Colaborador colab : listaColaboradores) {
            if (colab.getNome().equalsIgnoreCase(nome)) {
                return true;
            }
        }
        return false;
    }

    public static String pesquisarColaborador(String nome) {
        for(Colaborador colab : listaColaboradores) {
            if (colab.getNome().equalsIgnoreCase(nome)) {
                return "\nColaborador encontrado.\nDados do colaborador:\n" + colab.imprimir();
            }
        }
        return "\nColaborador não encontrado.\n";
    }

    public static boolean associar(String titulo, String nome){
        for(Projeto proj : listaProjetos){
            if(proj.getTitulo().equalsIgnoreCase(titulo)){
                proj.adicionarColaborador(retornarColaborador(nome));
                return true;
            }
        }
        return false;
    }

    public static Colaborador retornarColaborador(String nome){
        for(Colaborador colab : listaColaboradores) {
            if (colab.getNome().equalsIgnoreCase(nome)) {
                return colab;
            }
        }
        return null;
    }

}
