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

import co.edu.usbcali.banco.domain.TipoUsuario;
import co.edu.usbcali.banco.dto.TipoUsuarioDTO;
import co.edu.usbcali.banco.mapper.TipoUsuarioMapper;
import co.edu.usbcali.banco.service.TipoUsuarioService;

@RestController
@RequestMapping("/tipousuario")
public class TipoUsuarioRest {

	@Autowired
	private TipoUsuarioService tipoUsuarioService;

	@Autowired
	private TipoUsuarioMapper tipoUsuarioMapper;

	@GetMapping("/{id}")
	public TipoUsuarioDTO findById(@PathVariable("id") Long id) throws Exception {
		TipoUsuario tipoUsuario = tipoUsuarioService.findById(id);
		if (tipoUsuario == null) {
			return null;
		}
		TipoUsuarioDTO tipoUsuarioDTO = tipoUsuarioMapper.tipoUsuarioToTipoUsuarioDTO(tipoUsuario);
		return tipoUsuarioDTO;
	}

	@GetMapping("/")
	public List<TipoUsuarioDTO> findAll() throws Exception {
		return tipoUsuarioMapper.listTipoUsuarioToListTipoUsuarioDTO(tipoUsuarioService.findAll());
	}

	@PostMapping("/")
	public ResponseEntity<String> save(@RequestBody TipoUsuarioDTO tipoUsuarioDTO) throws Exception {
		try {
			TipoUsuario tipoUsuario = tipoUsuarioMapper.tipoUsuarioDTOToTipoUsuario(tipoUsuarioDTO);
			tipoUsuarioService.save(tipoUsuario);
			return ResponseEntity.ok().body("El tipoUsuario se grabo con exito");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/")
	public ResponseEntity<String> update(@RequestBody TipoUsuarioDTO tipoUsuarioDTO) throws Exception {
		try {
			TipoUsuario tipoUsuario = tipoUsuarioMapper.tipoUsuarioDTOToTipoUsuario(tipoUsuarioDTO);
			tipoUsuarioService.update(tipoUsuario);
			return ResponseEntity.ok().body("El tipoUsuario se modifico con exito");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id) throws Exception {
		try {
			TipoUsuario tipoUsuario = tipoUsuarioService.findById(id);
			tipoUsuarioService.delete(tipoUsuario);
			return ResponseEntity.ok().body("El tipoUsuario se borro con exito");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
