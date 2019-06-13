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

import co.edu.usbcali.banco.domain.TipoDocumento;
import co.edu.usbcali.banco.dto.TipoDocumentoDTO;
import co.edu.usbcali.banco.mapper.TipoDocumentoMapper;
import co.edu.usbcali.banco.service.TipoDocumentoService;

@RestController
@RequestMapping("/tipodocumento")
public class TipoDocumentoRest {
	@Autowired
	private TipoDocumentoService tipoDocumentoService;

	@Autowired
	private TipoDocumentoMapper tipoDocumentoMapper;

	@GetMapping("/{id}")
	public TipoDocumentoDTO findById(@PathVariable("id") Long id) throws Exception {
		TipoDocumento tipoDocumento = tipoDocumentoService.findById(id);
		if (tipoDocumento == null) {
			return null;
		}
		TipoDocumentoDTO tipoDocumentoDTO = tipoDocumentoMapper.tipoDocumentoToTipoDocumentoDTO(tipoDocumento);
		return tipoDocumentoDTO;
	}

	@GetMapping("/")
	public List<TipoDocumentoDTO> findAll() throws Exception {
		return tipoDocumentoMapper.listTipoDocumentoToListTipoDocumentoDTO(tipoDocumentoService.findAll());
	}

	@PostMapping("/")
	public ResponseEntity<String> save(@RequestBody TipoDocumentoDTO tipoDocumentoDTO) throws Exception {
		try {
			TipoDocumento tipoDocumento = tipoDocumentoMapper.tipoDocumentoDTOToTipoDocumento(tipoDocumentoDTO);
			tipoDocumentoService.save(tipoDocumento);
			return ResponseEntity.ok().body("El tipoDocumento se grabo con exito");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/")
	public ResponseEntity<String> update(@RequestBody TipoDocumentoDTO tipoDocumentoDTO) throws Exception {
		try {
			TipoDocumento tipoDocumento = tipoDocumentoMapper.tipoDocumentoDTOToTipoDocumento(tipoDocumentoDTO);
			tipoDocumentoService.update(tipoDocumento);
			return ResponseEntity.ok().body("El tipoDocumento se modifico con exito");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id) throws Exception {
		try {
			TipoDocumento tipoDocumento = tipoDocumentoService.findById(id);
			tipoDocumentoService.delete(tipoDocumento);
			return ResponseEntity.ok().body("El tipoDocumento se borro con exito");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
