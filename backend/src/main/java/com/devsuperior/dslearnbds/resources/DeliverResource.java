package com.devsuperior.dslearnbds.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dslearnbds.dto.DeliverRevisionDTO;
import com.devsuperior.dslearnbds.services.DeliverService;

@RestController
@RequestMapping(value = "/deliveries")
public class DeliverResource {

	@Autowired
	private DeliverService service;
	
	
	
	// pode colocar o preautorize na classe de servico se vc achar melhor, os dois tao certo
    @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")//vai fazer o pré processamento antes,se nao for, nem passa pro meto
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> saveRevision(@PathVariable Long id, @RequestBody DeliverRevisionDTO dto) {
		service.saveRevision(id, dto);
		return ResponseEntity.noContent().build();
	}
}
