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

import co.edu.usbcali.banco.domain.Cliente;
import co.edu.usbcali.banco.dto.ClienteDTO;
import co.edu.usbcali.banco.mapper.ClienteMapper;
import co.edu.usbcali.banco.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteRest {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ClienteMapper clienteMapper;
	
	@GetMapping("/{id}")
	public ClienteDTO findById(@PathVariable("id") Long id)throws Exception {
		Cliente cliente=clienteService.findById(id);
		if(cliente==null) {
			return null;
		}
		ClienteDTO clienteDTO=clienteMapper.clienteToClienteDTO(cliente);
		return clienteDTO;
	}
	
	@GetMapping("/")
	public List<ClienteDTO> findAll()throws Exception{
		return clienteMapper.listClienteToListClienteDTO(clienteService.findAll());
	}
	
	@PostMapping("/")
	public ResponseEntity<String> save(@RequestBody ClienteDTO clienteDTO)throws Exception{
		try {
			Cliente cliente=clienteMapper.clienteDTOToCliente(clienteDTO);
			clienteService.save(cliente);
			return ResponseEntity.ok().body("El cliente se grabo con exito");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/")
	public ResponseEntity<String> update(@RequestBody ClienteDTO clienteDTO)throws Exception{
		try {
			Cliente cliente=clienteMapper.clienteDTOToCliente(clienteDTO);
			clienteService.update(cliente);
			return ResponseEntity.ok().body("El cliente se modifico con exito");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id)throws Exception{
		try {
			Cliente cliente=clienteService.findById(id);
			clienteService.delete(cliente);
			return ResponseEntity.ok().body("El cliente se borro con exito");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	

}
