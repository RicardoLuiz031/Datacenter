package Datacenter.Repository;

import Datacenter.Entities.Acesso;
import Datacenter.Dto.LogAcessoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AcessoRepository extends JpaRepository<Acesso, Integer> {

    @Query("""
           SELECT new Datacenter.dto.LogAcessoDTO(
               a.idAcesso,
               a.dataAcesso,
               a.area_acessada,
               a.tipo_movimento,
               a.resultado,
               a.motivo_negacao,
               u.nome,
               u.login,
               u.tipo_usuario
           )
           FROM Acesso a
           JOIN a.usuario u
           WHERE (:inicio IS NULL OR a.dataAcesso >= :inicio)
             AND (:fim IS NULL OR a.dataAcesso <= :fim)
             AND (:idUsuario IS NULL OR u.idUsuarios = :idUsuario)
             AND (:area IS NULL OR a.area_acessada = :area)
           ORDER BY a.dataAcesso DESC
           """)
    List<LogAcessoDto> buscarLogsFiltrados(
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim,
            @Param("idUsuario") Integer idUsuario,
            @Param("area") String area
    );
}
