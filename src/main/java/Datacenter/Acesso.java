package Datacenter;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Acesso")
public class Acesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Acesso")
    private Integer idAcesso;

    @Column(name = "data_acesso", nullable = false)
    private String data_acesso;

    @Column(name = "area_acessada", nullable = false)
    private String area_acessada;

    @Column(name = "tipo_movimento", nullable = false)
    private String tipo_movimento;

    @Column(name = "resultado", nullable = false)
    private String resultado;

    @Column(name = "motivo", nullable = false)
    private String motivo_negacao;

    @ManyToOne
    @JoinColumn(name = "id_Usuarios", nullable = false)
    private Usuarios usuario;

    public Acesso() {
    }

    public Acesso(Integer idAcesso, String data_acesso, String area_acessada, String tipo_movimento, String resultado, String motivo_negacao, Usuarios usuario) {
        this.idAcesso = idAcesso;
        this.data_acesso = data_acesso;
        this.area_acessada = area_acessada;
        this.tipo_movimento = tipo_movimento;
        this.resultado = resultado;
        this.motivo_negacao = motivo_negacao;
        this.usuario = usuario;
    }

    public Integer getIdAcesso() {
        return idAcesso;
    }

    public void setIdAcesso(Integer idAcesso) {
        this.idAcesso = idAcesso;
    }

    public String getData_acesso() {
        return data_acesso;
    }

    public void setData_acesso(String data_acesso) {
        this.data_acesso = data_acesso;
    }

    public String getArea_acessada() {
        return area_acessada;
    }

    public void setArea_acessada(String area_acessada) {
        this.area_acessada = area_acessada;
    }

    public String getTipo_movimento() {
        return tipo_movimento;
    }

    public void setTipo_movimento(String tipo_movimento) {
        this.tipo_movimento = tipo_movimento;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getMotivo_negacao() {
        return motivo_negacao;
    }

    public void setMotivo_negacao(String motivo_negacao) {
        this.motivo_negacao = motivo_negacao;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Acesso{" +
                "idAcesso=" + idAcesso +
                ", data_acesso='" + data_acesso + '\'' +
                ", area_acessada='" + area_acessada + '\'' +
                ", tipo_movimento='" + tipo_movimento + '\'' +
                ", resultado='" + resultado + '\'' +
                ", motivo_negacao='" + motivo_negacao + '\'' +
                ", usuario=" + usuario +
                '}';
    }
}
