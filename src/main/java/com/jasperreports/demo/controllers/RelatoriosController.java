package com.jasperreports.demo.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jasperreports.demo.dtos.ParametrosDTO;
import com.jasperreports.demo.services.RelatorioService;

@RestController
@RequestMapping("/relatorio")
public class RelatoriosController {
	
	@Autowired
	private RelatorioService relatorioService;
	
	@PostMapping("/db")
	public ResponseEntity<byte[]> relatorioDb(@RequestBody ParametrosDTO dto) throws Exception {	
		
		byte[] relatorio = relatorioService.exportRelatorioDb(dto);
		return ResponseEntity.ok()
				.header("Content-Disposition", "attachment; filename=relatorio_employees_"+LocalDate.now()+".pdf")
				.body(relatorio);
		
	}
	
	@PostMapping("/json")
	public ResponseEntity<byte[]> relatorioJson(@RequestBody ParametrosDTO dto) throws Exception {	
		
		byte[] relatorio = relatorioService.exportRelatorioJson(dto);
		return ResponseEntity.ok()
				.header("Content-Disposition", "attachment; filename=relatorio_employees_"+LocalDate.now()+".pdf")
				.body(relatorio);
		
	}

}