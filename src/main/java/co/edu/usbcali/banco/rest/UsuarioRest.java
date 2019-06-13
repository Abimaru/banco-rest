package co.edu.usbcali.banco.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.banco.domain.Usuario;
import co.edu.usbcali.banco.dto.UsuarioDTO;
import co.edu.usbcali.banco.mapper.UsuarioMapper;
import co.edu.usbcali.banco.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioRest {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioMapper usuarioMapper;
	
	@GetMapping("/{usuario}")
	public UsuarioDTO findById(@PathVariable("usuario") String usuUsuario)throws Exception {
		Usuario usuario=usuarioService.findByUsuario(usuUsuario);
		if(usuario==null) {
			return null;
		}
		UsuarioDTO usuarioDTO=usuarioMapper.usuarioToUsuarioDTO(usuario);
		return usuarioDTO;
	}
	
	@GetMapping("/")
	public List<UsuarioDTO> findAll()throws Exception{
		return usuarioMapper.listUsuarioToListUsuarioDTO(usuarioService.findAll());
	}
	
	@PostMapping("/")
	public ResponseEntity<String> save(@RequestBody UsuarioDTO usuarioDTO)throws Exception{
		try {
			Usuario usuario=usuarioMapper.usuarioDTOToUsuario(usuarioDTO);
			usuarioService.save(usuario);
			return ResponseEntity.ok().body("El usuario se grabo con exito");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/")
	public ResponseEntity<String> update(@RequestBody UsuarioDTO usuarioDTO)throws Exception{
		try {
			Usuario usuario=usuarioMapper.usuarioDTOToUsuario(usuarioDTO);
			usuarioService.update(usuario);
			return ResponseEntity.ok().body("El usuario se modifico con exito");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{usuario}")
	public ResponseEntity<String> delete(@PathVariable("usuario") String usuUsuario)throws Exception{
		try {
			Usuario usuario=usuarioService.findByUsuario(usuUsuario);
			usuarioService.delete(usuario);
			return ResponseEntity.ok().body("El usuario se borro con exito");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	

}
