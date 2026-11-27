package Datacenter.Repository;

import Datacenter.Entities.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {

        Optional<Usuarios> findByLogin(String login);

        boolean existByLogin(String login);
}
