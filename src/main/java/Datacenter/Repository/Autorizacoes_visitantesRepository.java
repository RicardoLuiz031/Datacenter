package Datacenter.Repository;

import Datacenter.Entities.Autorizacoes_visitantes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface Autorizacoes_visitantesRepository extends JpaRepository<Autorizacoes_visitantes, Integer> {

    @Query("""

    SELECT av FROM Autorizacoes_visitantes av
    WHERE av.usuario.id_Usuarios = :idUsuario
    AND av.Ativa = true
    AND av.Data_inicio <= :hoje
    AND av.Data_validade >= :agora
""")

    Optional<Autorizacoes_visitantes> buscarAutorizacaoValida(
            @Param("idUsuario") Integer idUsuario,
            @Param("hoje") LocalDate hoje,
            @Param("agora") LocalDateTime agora
    );

        List<Autorizacoes_visitantes> findByUsuarioId_Usuarios(Integer idUsuario);
}
