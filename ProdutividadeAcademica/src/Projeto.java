import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Projeto {
    private String titulo;
    private Date dataInicio;
    private Date dataFim;
    private String agenciaFinanciadora;
    private float valorFinanciado;
    private String objetivo;
    private String descricao;
    private String status;
    private final ArrayList<Colaborador> listaParticipantes = new ArrayList<>(); //Lista dos participantes do projeto
    private final ArrayList<Publicacao> listaPublicacoesAssociadas = new ArrayList<>(); //Lista das publicações associadas ao projeto
    private final ArrayList<Orientacao> listaOrientacoesAssociadas = new ArrayList<>(); //Lista das orientações associadas ao projeto

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public ArrayList<Colaborador> getListaParticipantes() {
        return listaParticipantes;
    }

    public ArrayList<Publicacao> getListaPublicacoesAssociadas() {
        return listaPublicacoesAssociadas;
    }

    public ArrayList<Orientacao> getListaOrientacoesAssociadas() {
        return listaOrientacoesAssociadas;
    }

    public Projeto(){}

    public Projeto(String titulo, Date dataInicio, Date dataFim,
                   String agenciaFinanciadora, float valorFinanciado,
                   String objetivo, String descricao, String status) {
        this.titulo = titulo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.agenciaFinanciadora = agenciaFinanciadora;
        this.valorFinanciado = valorFinanciado;
        this.objetivo = objetivo;
        this.descricao = descricao;
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getAgenciaFinanciadora() {
        return agenciaFinanciadora;
    }
    public void setAgenciaFinanciadora(String agenciaFinanciadora) {
        this.agenciaFinanciadora = agenciaFinanciadora;
    }

    public float getValorFinanciado() {
        return valorFinanciado;
    }
    public void setValorFinanciado(float valorFinanciado) {
        this.valorFinanciado = valorFinanciado;
    }

    public String getObjetivo() {
        return objetivo;
    }
    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public void adicionarParticipante(Colaborador colab){
        listaParticipantes.add(colab);
    }

    public void adicionarPublicacao(Publicacao pub) {
        listaPublicacoesAssociadas.add(pub);
        Collections.sort(listaPublicacoesAssociadas);
    }

    public void adicionarOrientacao(Orientacao ori) {
        listaOrientacoesAssociadas.add(ori);
    }

    public boolean verificarProfessor() { // verifica se existe algum professor entre os participantes
        for(Colaborador colab : listaParticipantes){
            if(colab.getOcupacao().equals("Professor")){
                return true;
            }
        }
        return false;
    }

    public boolean verificarSituacaoAluno() {
        for(Colaborador colab : listaParticipantes){
            if (!colab.verificarSituacao()) {
                return false;
            }
        }
        return true;
    }

    public String imprimir() {
        String participantes = "";
        if (getListaParticipantes().isEmpty()) {
            participantes = " Não há colaboradores alocados ao projeto.";
        } else {
            for(Colaborador colab : listaParticipantes) {
                participantes += "\n  " + colab.getNome() + " (" + colab.getOcupacao() + ")";
            }
        }

        String publicacoes = "";
        if (getListaPublicacoesAssociadas().isEmpty()) {
            publicacoes = " Não há publicações associadas ao projeto.";
        } else {
            for(Publicacao pub : listaPublicacoesAssociadas) {
                publicacoes += "\n  " + pub.getTitulo() + " (" + pub.getAnoPublic() + ")";
            }
        }

        String orientacoes = "";
        if (getListaOrientacoesAssociadas().isEmpty()) {
            orientacoes = " Não há orientações associadas ao projeto.";
        } else {
                for(Orientacao ori : listaOrientacoesAssociadas) {
                if (ori.getTituloProj() != null) {
                    orientacoes += "\n  " + ori.getNomeProf() + "/" + ori.getNomeColab() + " (" + ori.getTituloProj() + ")";
                } else {
                    orientacoes += "\n  " + ori.getNomeProf() + "/" + ori.getNomeColab();
                }

            }
        }

        return "- Título do projeto: " + titulo + "\n- Data de início: " + sdf.format(dataInicio)
                + "\n- Data de término: " + sdf.format(dataFim) + "\n- Agência financiadora: " + agenciaFinanciadora + "\n- Valor financiado: "
                + String.format("R$ %.2f", valorFinanciado) + "\n- Objetivo: " + objetivo + "\n- Descrição: " + descricao
                + "\n- Status: " + status + "\n- Participantes:" + participantes + "\n- Publicações:" + publicacoes + "\n- Orientações:" + orientacoes;
    }
}

