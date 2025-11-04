package co.edu.unbosque.bogotacup.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.bogotacup.dto.EquipoDTO;
import co.edu.unbosque.bogotacup.dto.PartidoDTO;
import co.edu.unbosque.bogotacup.dto.UsuarioDTO;
import co.edu.unbosque.bogotacup.model.Equipo;
import co.edu.unbosque.bogotacup.model.Partido;
import co.edu.unbosque.bogotacup.model.Usuario;
import co.edu.unbosque.bogotacup.repository.EquipoRepository;
import co.edu.unbosque.bogotacup.repository.PartidoRepository;
import co.edu.unbosque.bogotacup.repository.UsuarioRepository;

@Service
public class EquipoService {

	@Autowired
	private EquipoRepository equipoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PartidoRepository partidoRepository;

	@Autowired
	private ModelMapper modelMapper;

	public long count() {
		return equipoRepository.count();
	}

	public int create(EquipoDTO data) {
		Equipo entity = modelMapper.map(data, Equipo.class);
		try {
			equipoRepository.save(entity);
			return 0;
		} catch (Exception e) {
			return 1;
		}
	}

	public int update(Integer id, EquipoDTO equipoDTO) {
		Optional<Equipo> existingEquipo = equipoRepository.findById(id);
		if (existingEquipo.isPresent()) {
			Equipo equipo = existingEquipo.get();
			equipo.setNombre(equipoDTO.getNombre());
			equipo.setDirectorTecnico(equipoDTO.getDirectorTecnico());
			equipo.setColorPrimario(equipoDTO.getColorPrimario());
			equipo.setColorSecundario(equipoDTO.getColorSecundario());
			equipoRepository.save(equipo);
			return 0;
		}
		return 1;
	}

	public int deleteById(Integer id) {
		Optional<Equipo> found = equipoRepository.findById(id);
		if (found.isPresent()) {
			equipoRepository.delete(found.get());
			return 0;
		}
		return 1;
	}

	public List<EquipoDTO> findAll() {
		List<Equipo> entityList = (List<Equipo>) equipoRepository.findAll();
		List<EquipoDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> {
			EquipoDTO dto = modelMapper.map(entity, EquipoDTO.class);
			dtoList.add(dto);
		});
		return dtoList;
	}

	public EquipoDTO findById(Integer id) {
		Optional<Equipo> equipo = equipoRepository.findById(id);
		return equipo.map(value -> modelMapper.map(value, EquipoDTO.class)).orElse(null);
	}

	public EquipoDTO findByNombre(String nombre) {
		Optional<Equipo> equipo = equipoRepository.findByNombre(nombre);
		return equipo.map(value -> modelMapper.map(value, EquipoDTO.class)).orElse(null);
	}

	public List<EquipoDTO> findByDirectorTecnico(String directorTecnico) {
		List<Equipo> equipos = equipoRepository.findByDirectorTecnico(directorTecnico);
		List<EquipoDTO> dtoList = new ArrayList<>();
		equipos.forEach(equipo -> dtoList.add(modelMapper.map(equipo, EquipoDTO.class)));
		return dtoList;
	}

	public int agregarUsuario(Integer equipoId, Integer usuarioId) {
		Optional<Equipo> equipoOpt = equipoRepository.findById(equipoId);
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);

		if (equipoOpt.isPresent() && usuarioOpt.isPresent()) {
			Equipo equipo = equipoOpt.get();
			Usuario usuario = usuarioOpt.get();

			equipo.agregarUsuario(usuario);
			equipoRepository.save(equipo);
			return 0;
		}
		return 1;
	}

	public int removerUsuario(Integer equipoId, Integer usuarioId) {
		Optional<Equipo> equipoOpt = equipoRepository.findById(equipoId);
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);

		if (equipoOpt.isPresent() && usuarioOpt.isPresent()) {
			Equipo equipo = equipoOpt.get();
			Usuario usuario = usuarioOpt.get();

			equipo.removerUsuario(usuario);
			equipoRepository.save(equipo);
			return 0;
		}
		return 1;
	}

	public List<UsuarioDTO> obtenerUsuariosDelEquipo(Integer equipoId) {
		Optional<Equipo> equipoOpt = equipoRepository.findById(equipoId);
		if (equipoOpt.isPresent()) {
			Equipo equipo = equipoOpt.get();
			return equipo.getUsuarios().stream().map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
					.collect(Collectors.toList());
		}
		return new ArrayList<>();
	}

	public int agregarPartido(Integer equipoId, Integer partidoId) {
		Optional<Equipo> equipoOpt = equipoRepository.findById(equipoId);
		Optional<Partido> partidoOpt = partidoRepository.findById(partidoId);
		if (equipoOpt.isPresent() && partidoOpt.isPresent()) {
			Equipo equipo = equipoOpt.get();
			Partido partido = partidoOpt.get();
			equipo.agregarPartido(partido);
			equipoRepository.save(equipo);
			return 0;
		}
		return 1;
	}

	public int removerPartido(Integer equipoId, Integer partidoId) {
		Optional<Equipo> equipoOpt = equipoRepository.findById(equipoId);
		Optional<Partido> partidoOpt = partidoRepository.findById(partidoId);
		if (equipoOpt.isPresent() && partidoOpt.isPresent()) {
			Equipo equipo = equipoOpt.get();
			Partido partido = partidoOpt.get();
			if (equipo.getPartidos() != null) {
				equipo.getPartidos().remove(partido);
			}
			if (partido.getEquipos() != null) {
				partido.getEquipos().remove(equipo);
			}
			equipoRepository.save(equipo);
			partidoRepository.save(partido);
			return 0;
		}
		return 1;
	}

	public List<PartidoDTO> obtenerPartidosDelEquipo(Integer equipoId) {
		Optional<Equipo> equipoOpt = equipoRepository.findById(equipoId);
		if (equipoOpt.isPresent()) {
			Equipo equipo = equipoOpt.get();
			if (equipo.getPartidos() != null) {
				return equipo.getPartidos().stream().map(partido -> modelMapper.map(partido, PartidoDTO.class))
						.collect(Collectors.toList());
			}
		}
		return new ArrayList<>();
	}

}
