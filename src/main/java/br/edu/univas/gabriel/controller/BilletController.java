package br.edu.univas.gabriel.controller;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.univas.gabriel.dto.BilletDto;
import br.edu.univas.gabriel.service.BilletService;

@RestController
@RequestMapping("/billets")
public class BilletController {
	
	@Autowired
	private BilletService service;
	
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public void createMessage(@RequestBody @Valid BilletDto dto) throws ParseException {
		service.create(dto);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateMessage(@RequestBody @Valid BilletDto dto, @PathVariable String id) {
		service.update(id, dto);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteMesage(@PathVariable String id) {
		service.delete(id);
	}
	
	@GetMapping()
	public List<BilletDto> getAllBillets() {
		return service.getAllBillet();
	}
	
	@GetMapping("/{id}")
	public BilletDto getBilletById(@PathVariable String id) {
		return service.getBilletById(id);
	}
}