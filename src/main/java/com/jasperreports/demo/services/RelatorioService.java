package com.jasperreports.demo.services;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.sql.DataSource;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jasperreports.demo.dtos.EmployeeDTO;
import com.jasperreports.demo.dtos.ParametrosDTO;
import com.jasperreports.demo.models.Employee;
import com.jasperreports.demo.repositories.EmployeeRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class RelatorioService {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	public byte[] exportRelatorioDb(ParametrosDTO dto) throws SQLException, JRException {
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("data_contratacao", dto.getDataContratacao());
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
				
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/relatorio_employees.jasper");			
		Connection con = this.dataSource.getConnection();
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, con);
		con.close();
		return JasperExportManager.exportReportToPdf(jasperPrint);		
	}
	
	public byte[] exportRelatorioJson(ParametrosDTO dto) throws SQLException, JRException, ParseException {
		
		List<Employee> listEmployee = new ArrayList<Employee>();
		List<EmployeeDTO> listEmployeeDTOs = new ArrayList<EmployeeDTO>();
		
		if(dto.getDataContratacao() == null) {
			listEmployee = employeeRepository.findAll();
		}else {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy"); 
			Date data = formato.parse(dto.getDataContratacao());
			listEmployee = employeeRepository.findByHireDate(data);
		}
		
		listEmployee.forEach(employee -> {
			listEmployeeDTOs.add(modelMapper.map(employee, EmployeeDTO.class));
		});
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
		
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/relatorio_employees_json.jasper");			
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,
				new JRBeanCollectionDataSource(listEmployeeDTOs));
		
		return JasperExportManager.exportReportToPdf(jasperPrint);	
	}
}
