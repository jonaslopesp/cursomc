package br.com.jonaslopes.maisvendas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jonaslopes.maisvendas.domain.Cliente;
import br.com.jonaslopes.maisvendas.repositories.ClienteRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) throws ObjectNotFoundException {
		 Optional<Cliente> obj = repo.findById(id);
		 
  		 return obj.orElseThrow(() -> new ObjectNotFoundException(
  		 "Cliente não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
}
