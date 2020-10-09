package com.MeuProjetoSpring.Meu.projeto.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MeuProjetoSpring.Meu.projeto.model.Categoria;
import com.MeuProjetoSpring.Meu.projeto.repository.ProjetoRepository;

@RestController
@RequestMapping("/categorias")
@CrossOrigin("*")
public class ProjetoController {

	@Autowired
	private ProjetoRepository repository;

	@GetMapping
	public ResponseEntity<List<Categoria>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> GetById(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/descricao/{categorias}")
		public ResponseEntity<List<Categoria>> GetByCategoria(@PathVariable String categorias){
		return ResponseEntity.ok(repository.findAllByCategoriaContainingIgnoreCase(categorias));
	}
	
	
	@PostMapping
	public ResponseEntity<Categoria> post(@RequestBody Categoria categorias){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categorias));
	}
	
	@PutMapping
	public ResponseEntity<Categoria> put(@RequestBody Categoria categorias){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(categorias));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
	
}
