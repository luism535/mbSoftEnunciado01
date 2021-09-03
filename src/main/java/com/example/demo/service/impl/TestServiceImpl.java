package com.example.demo.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.common.Common;
import com.example.demo.model.Response;
import com.example.demo.service.TestService;

@Service
public class TestServiceImpl implements TestService {
	
	private String regex = "([A-Z]{3})-([0-9]{5})-([0-9]{1})";
	private String priorityCode = "PW";
	
	@Autowired
	private Common cm;
	

	@Override
	public ResponseEntity<?> esPrioritaio(String codigo) {	
		Response res = new Response();
		
		try {
			if(Pattern.matches(regex, codigo)) {
				res.setMessage(String.valueOf(priorityCode.indexOf(codigo.charAt(0)) != -1));
				return new ResponseEntity<>(res, HttpStatus.OK);
			}else {
				res.setMessage("Codigo no posee un formato valido (XXX-NNNNN-N)");
				return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
			}
			
		}catch (Exception ex) {
			res.setMessage("Ha ocurrido un error inesperado: " + ex.getMessage());
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@Override
	public ResponseEntity<?> verificar(String codigo) {
		Response res = new Response();
		
		try {
			if(Pattern.matches(regex, codigo)) {
				String[] codes = codigo.split("-");
				Integer calculateDigit = cm.sumDigits(Integer.parseInt(codes[1]));
				
				res.setMessage(String.valueOf(calculateDigit == Integer.parseInt(codes[2])));
				return new ResponseEntity<>(res, HttpStatus.OK);
			}else {
				res.setMessage("Codigo no posee un formato valido (XXX-NNNNN-N)");
				return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
			}
			
		}catch (Exception ex) {
			res.setMessage("Ha ocurrido un error inesperado: " + ex.getMessage());
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> ordenar(List<String> codigos) {
		Response res = new Response();
		
		try {
			
			if(codigos.size() <= 0) {
				res.setMessage("La lista esta vacia...");
				return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
			}
			
			for(String code : codigos) {
				if(!Pattern.matches(regex, code)) {
					res.setMessage("Uno o mas codigos no cumplen con el formato valido");
					return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
				}
			}
			
			Collections.sort(codigos, new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return o1.split("-")[0].compareTo(o2.split("-")[0]);
				}
			});
			
			return new ResponseEntity<>(codigos, HttpStatus.OK);
		}catch (Exception ex) {
			res.setMessage("Ha ocurrido un error inesperado: " + ex.getMessage());
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}

	@Override
	public ResponseEntity<?> unir(List<String> lista1, List<String> lista2) {
		Response res = new Response();
		
		try {
			if(lista1.size() == 0 || lista2.size() == 0) {
				res.setMessage("Una o ambas listas estan vacias");
				return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
			}
			
			List<String> result = Stream.concat(lista1.stream(), lista2.stream()).collect(Collectors.toList());
			
			for(String code : result) {
				if(!Pattern.matches(regex, code)) {
					res.setMessage("Uno o mas codigos de las listas no cumplen con el formato valido ");
					return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
				}
			}
			
			return new ResponseEntity<>(result, HttpStatus.OK);
			
		}catch (Exception ex) {
			res.setMessage("Ha ocurrido un error inesperado: " + ex.getMessage());
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> interseccion(List<String> lista1, List<String> lista2) {
		Response res = new Response();
		
		try {
			if(lista1.size() == 0 || lista2.size() == 0) {
				res.setMessage("Una o ambas listas estan vacias");
				return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
			}
			
			List<String> result = cm.getDuplicados(cm.unirListas(lista1, lista2));
			return new ResponseEntity<>(result, HttpStatus.OK);
			
		}catch (Exception ex) {
			res.setMessage("Ha ocurrido un error inesperado: " + ex.getMessage());
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
