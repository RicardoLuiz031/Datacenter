package Datacenter;

import jakarta.persistence.*; // Troque para javax.persistence.* se usar versão antiga do JPA
import java.time.LocalDateTime;

@Entity
@Table(name = "Usuarios")
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Usuarios")
    private Integer idUsuarios;

    @Column(name = "tipo_usuario", nullable = false)
    private String tipoUsuarios; // Valores: "Administrador", "Tecnico" ou "Visitante"

    @Column(name = "nome", nullable = false, length = 200)
    private String nome;

    @Column(name = "cpf", nullable = false, length = 255)
    private String cpf; // Salvar já criptografado com AES-256

    @Column(name = "telefone", nullable = false, length = 255)
    private String telefone; // Salvar já criptografado com AES-256

    @Column(name = "email", nullable = false, length = 255)
    private String email; // Salvar já criptografado com AES-256

    @Column(name = "login", nullable = false, unique = true, length = 100)
    private String login;

    @Column(name = "senha", nullable = false, length = 255)
    private String senha; // Salvar apenas o Hash (ex: BCrypt)

    @Column(name = "tentativas_falhas")
    private Integer tentativasFalhas = 0;

    @Column(name = "bloqueado_ate")
    private LocalDateTime bloqueadoAte;

    // 1. Construtor vazio OBRIGATÓRIO para o JPA funcionar
    public Usuarios() {
    }

    // 2. Construtor com parâmetros para você usar no código
    public Usuarios(String tipoUsuarios, String nome, String cpf, String telefone, String email, String login, String senha) {
        this.tipoUsuarios = tipoUsuarios;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.tentativasFalhas = 0;
    }

    // Getters e Setters
    public Integer getIdUsuarios() {
        return idUsuarios;
    }

    public void setIdUsuarios(Integer idUsuarios) {
        this.idUsuarios = idUsuarios;
    }

    public String getTipoUsuarios() {
        return tipoUsuarios;
    }

    public void setTipoUsuarios(String tipoUsuarios) {
        this.tipoUsuarios = tipoUsuarios;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getTentativasFalhas() {
        return tentativasFalhas;
    }

    public void setTentativasFalhas(Integer tentativasFalhas) {
        this.tentativasFalhas = tentativasFalhas;
    }

    public LocalDateTime getBloqueadoAte() {
        return bloqueadoAte;
    }

    public void setBloqueadoAte(LocalDateTime bloqueadoAte) {
        this.bloqueadoAte = bloqueadoAte;
    }

    @Override
    public String toString() {
        return "Usuarios{" +
                "idUsuarios=" + idUsuarios +
                ", tipoUsuarios='" + tipoUsuarios + '\'' +
                ", nome='" + nome + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}