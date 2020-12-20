package br.com.fiap.api.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.com.fiap.api.dto.PessoaDTO;
import br.com.fiap.api.model.PessoaModel;
import br.com.fiap.api.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService service;

    public PessoaController(PessoaService service) {
		this.service = service;
	}
    
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<PessoaDTO> findAll() {
    	List<PessoaDTO> pessoas = new ArrayList<>(); 
    	
    	service.findAll().forEach(model -> pessoas.add(new PessoaDTO(model)));
    	
        return pessoas;
    }
    
    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public PessoaDTO findById(@PathVariable String id) {
        return new PessoaDTO(
        		service.findById(id)
        );
    }
    
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public PessoaDTO add(@RequestBody PessoaDTO dto) {
    	
        return new PessoaDTO(
        		service.add(new PessoaModel(dto)
        ));
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public PessoaDTO update(@PathVariable String id, @RequestBody PessoaDTO dto) {
        return new PessoaDTO(
        		service.update(id,new PessoaModel(dto))
        );
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void remove(@PathVariable String id) {
        service.remove(id);
    }
}
