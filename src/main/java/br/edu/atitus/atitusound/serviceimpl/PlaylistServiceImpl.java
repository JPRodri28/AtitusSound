package br.edu.atitus.atitusound.serviceimpl;

import org.springframework.stereotype.Service;


import br.edu.atitus.atitusound.entities.PlaylistEntity;
import br.edu.atitus.atitusound.repositories.GenericRepository;
import br.edu.atitus.atitusound.repositories.PlaylistRepository;
import br.edu.atitus.atitusound.services.PlaylistService;

@Service
public class PlaylistServiceImpl implements PlaylistService{
	private final PlaylistRepository playlistRepository;
	
	public PlaylistServiceImpl(PlaylistRepository playlistRepository) {
		super();
		this.playlistRepository = playlistRepository;
	}
	@Override
	public GenericRepository<PlaylistEntity> getRespository() {
		return playlistRepository;
	}
}

