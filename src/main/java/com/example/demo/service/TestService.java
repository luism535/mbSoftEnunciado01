package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface TestService {
	public ResponseEntity<?> esPrioritaio(String codigo);
	public ResponseEntity<?> verificar(String codigo);
	public ResponseEntity<?> ordenar(List<String> codigos);
	public ResponseEntity<?> unir(List<String> lista1, List<String> lista2);
	public ResponseEntity<?> interseccion(List<String> lista1, List<String> lista2);
	
}
