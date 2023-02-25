package curso.restapi.contoller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import curso.restapi.model.Usuario;
import curso.restapi.repository.UsuarioRepository;

@RestController /*Arquitetura Rest*/
@RequestMapping(value = "/usuario")
public class IndexController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	/*Serviço Restful*/
	
	/*@GetMapping(value = "/", produces= "application/json")
	public ResponseEntity init(@RequestParam (value = "nome", defaultValue = "Nome não informado") String nome, @RequestParam(value = "salario") Long salario) {
		
		System.out.println("Parametro sendo recebido: " + nome);
		return new ResponseEntity("Olá Rest Spring Boot seu nome é: " + nome + " salario é: " + salario,HttpStatus.OK);
	}
	*/
	
	/*Serviço Restful*/
	@GetMapping(value = "/teste", produces= "application/json")
	public ResponseEntity<List<Usuario>> initUsuario() {
		
		Usuario usuario = new Usuario();
		usuario.setId(50L);
		usuario.setNome("Alessandro");
		usuario.setLogin("alessandropedrosoti@gmail.com");
		usuario.setSenha("admin");
		
		Usuario usuario1 = new Usuario();
		usuario1.setId(51L);
		usuario1.setNome("Eduardo Pedroso");
		usuario1.setLogin("eduardopedroso@gmail.com");
		usuario1.setSenha("admin");
		
		List<Usuario> listUsuarios = new ArrayList<Usuario>();
		listUsuarios.add(usuario);
		listUsuarios.add(usuario1);
		
		
		System.out.println("Parametro sendo recebido: ");
		
		/*return ResponseEntity.ok(usuario);*/
		return new ResponseEntity<List<Usuario>>(listUsuarios,HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}/codigoVenda/{venda}", produces= "application/json")
	public ResponseEntity<Usuario> relatorio(@PathVariable (value = "id") Long id, @PathVariable(value="venda") Long venda) {
		
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		
		return new ResponseEntity<Usuario>(usuario.get(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}", produces= "application/json")
	public ResponseEntity<Usuario> initUsuarioJPA(@PathVariable (value = "id") Long id) {
		
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		
		
		return new ResponseEntity<Usuario>(usuario.get(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/", produces= "application/json")
	public ResponseEntity<List<Usuario>> consultaUsuario() {
		
		List<Usuario> usuarioLista = (List<Usuario>) usuarioRepository.findAll();
		
		return new ResponseEntity<List<Usuario>>(usuarioLista,HttpStatus.OK);
	}
	
	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario){
		
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		
		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
	}
	
	@PutMapping(value = "/", produces = "application/json")
	public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario){
		
		
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		
		
		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
	}
	
	
	@DeleteMapping(value = "/{id}", produces = "application/text")
	public ResponseEntity<String> delete(@PathVariable("id") Long id){
		
		usuarioRepository.deleteById(id);
		
		return new ResponseEntity<String>("Ok Dados excluidos!", HttpStatus.OK);
		
	}
	
}
