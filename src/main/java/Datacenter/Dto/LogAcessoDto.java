package Datacenter.Dto;

import java.time.LocalDateTime;

public record LogAcessoDto(
        Integer idAcesso,
        LocalDateTime dataAcesso,
        String areaAcessada,
        String tipoMovimento,
        String resultado,
        String motivoNegacao,
        String nomeUsuario,
        String loginUsuario,
        String tipoUsuario
) {}
