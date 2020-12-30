import java.util.ArrayList;

public class Projeto {
    private String titulo;
    private String dataInicio;
    private String dataFim;
    private String agenciaFinanciadora;
    private float valorFinanciado;
    private String objetivo;
    private String descricao;
    private String status;
    private ArrayList<Colaborador> listaParticipantes = new ArrayList<>();

    public ArrayList<Colaborador> getListaParticipantes() {
        return listaParticipantes;
    }

    public Projeto(){}

    public Projeto(String titulo, String dataInicio, String dataFim,
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

    public String getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }
    public void setDataFim(String dataFim) {
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

    public String imprimir() {
        return "Título do Projeto: " + titulo + "\nStatus: " + status;
    }
}

