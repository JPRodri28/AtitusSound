package br.edu.atitus.atitusound.serviceimpl;

import org.springframework.stereotype.Service;

import br.edu.atitus.atitusound.entities.UserEntity;
import br.edu.atitus.atitusound.repositories.GenericRepository;
import br.edu.atitus.atitusound.repositories.UserRepository;
import br.edu.atitus.atitusound.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	private final UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	@Override
	public GenericRepository<UserEntity> getRespository() {
		return userRepository;
	}
	
	@Override
	public void validateSave(UserEntity entidade) throws Exception {
		UserService.super.validateSave(entidade);
		if (entidade.getUsername() == null || entidade.getUsername().isEmpty())
			throw new Exception ("Username inválido!");
		if (entidade.getPassword() == null || entidade.getPassword().isEmpty())
			throw new Exception ("Password inválido!");
	}
	

}
