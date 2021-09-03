package com.example.demo.common;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

@Service
public class Common {
	
	public Integer sumDigits(Integer code) {
		int resultado = 0;
		
		while(code > 0) {
			resultado += code % 10;
			code = code/10;
		}
		
		if(resultado > 9) {
			resultado = sumDigits(resultado);
		}
		
		return resultado;
	}
	
	public List<String> unirListas(List<String> lista1, List<String> lista2){
		return Stream.concat(lista1.stream(), lista2.stream()).collect(Collectors.toList());
	}
	
	public List<String> getDuplicados(List<String> codigos){
		return  codigos.stream().filter(c -> Collections.frequency(codigos, c) > 1)
		.distinct()
		.collect(Collectors.toList());
	}

}
