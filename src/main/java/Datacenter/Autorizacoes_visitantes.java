package Datacenter;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Autorizacoes_visitantes")
public class Autorizacoes_visitantes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Autorizacoes_visitantes")
    private Integer idAutorizacoes_visitantes;

    @Column(name = "Data_inicio", nullable = false)
    private String Data_inicio;

    @Column(name = "Data_validade", nullable = false)
    private String Data_validade;

    @Column(name = "Ativa", nullable = false)
    private String Ativa;

    @ManyToOne
    @JoinColumn(name = "id_Usuarios", nullable = false)
    private Usuarios usuario;

    public Autorizacoes_visitantes() {
    }

    public Autorizacoes_visitantes(Integer idAutorizacoes_visitantes, String data_inicio, String data_validade, String ativa, Usuarios usuario) {
        this.idAutorizacoes_visitantes = idAutorizacoes_visitantes;
        Data_inicio = data_inicio;
        Data_validade = data_validade;
        Ativa = ativa;
        this.usuario = usuario;
    }

    public Integer getIdAutorizacoes_visitantes() {
        return idAutorizacoes_visitantes;
    }

    public void setIdAutorizacoes_visitantes(Integer idAutorizacoes_visitantes) {
        this.idAutorizacoes_visitantes = idAutorizacoes_visitantes;
    }

    public String getData_inicio() {
        return Data_inicio;
    }

    public void setData_inicio(String data_inicio) {
        Data_inicio = data_inicio;
    }

    public String getData_validade() {
        return Data_validade;
    }

    public void setData_validade(String data_validade) {
        Data_validade = data_validade;
    }

    public String getAtiva() {
        return Ativa;
    }

    public void setAtiva(String ativa) {
        Ativa = ativa;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Autorizacoes_visitantes{" +
                "idAutorizacoes_visitantes=" + idAutorizacoes_visitantes +
                ", Data_inicio='" + Data_inicio + '\'' +
                ", Data_validade='" + Data_validade + '\'' +
                ", Ativa='" + Ativa + '\'' +
                ", usuario=" + usuario +
                '}';
    }
}
