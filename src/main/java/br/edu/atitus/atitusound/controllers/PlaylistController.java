package br.edu.atitus.atitusound.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.atitus.atitusound.dtos.PlaylistDTO;
import br.edu.atitus.atitusound.entities.PlaylistEntity;
import br.edu.atitus.atitusound.services.GenericService;
import br.edu.atitus.atitusound.services.PlaylistService;

@RestController
@RequestMapping("/playlist")
public class PlaylistController extends GenericController<PlaylistEntity, PlaylistDTO> {
	
	private final PlaylistService playlistService;
	
	public PlaylistController(PlaylistService playlistService) {
		super();
		this.playlistService = playlistService;
	}

	protected PlaylistEntity convertDTO2Entety(PlaylistDTO dto) {
		PlaylistEntity entidade = new PlaylistEntity();
		BeanUtils.copyProperties(dto, entidade);
		return entidade;
	}

	@Override
	public GenericService<PlaylistEntity> getService() {
		return playlistService;
	}
	
}
