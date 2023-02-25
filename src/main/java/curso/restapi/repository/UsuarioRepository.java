package curso.restapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import curso.restapi.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
	
}
