package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.RequestCode;
import com.example.demo.service.TestService;

@RestController
@RequestMapping("/api")
public class MainController {

	@Autowired
	private TestService service;
	
	@GetMapping("/esPrioritario/{code}")
	public ResponseEntity<?> esPrioritario(@PathVariable String code){
		return service.esPrioritaio(code);
	}
	
	@GetMapping("/verificar/{code}")
	public ResponseEntity<?> verificar(@PathVariable String code){
		return service.verificar(code);
	}
	
	@PostMapping("/ordenar")
	public ResponseEntity<?> ordenar(@RequestBody RequestCode codigos){
		return service.ordenar(codigos.getCodigos());
	}
	
	@PostMapping("/unir")
	public ResponseEntity<?> unir(@RequestBody RequestCode codigos){
		return service.unir(codigos.getLista1(), codigos.getLista2());
	}
	
	@PostMapping("/inter")
	public ResponseEntity<?> inter(@RequestBody RequestCode codigos){
		return service.interseccion(codigos.getLista1(), codigos.getLista2());
	}
}
