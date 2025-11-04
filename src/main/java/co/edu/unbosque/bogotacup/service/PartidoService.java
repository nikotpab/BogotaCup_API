package co.edu.unbosque.bogotacup.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.bogotacup.dto.EquipoDTO;
import co.edu.unbosque.bogotacup.dto.PartidoDTO;
import co.edu.unbosque.bogotacup.model.Arbitro;
import co.edu.unbosque.bogotacup.model.Cancha;
import co.edu.unbosque.bogotacup.model.Partido;
import co.edu.unbosque.bogotacup.repository.ArbitroRepository;
import co.edu.unbosque.bogotacup.repository.CanchaRepository;
import co.edu.unbosque.bogotacup.repository.PartidoRepository;
import co.edu.unbosque.bogotacup.repository.EquipoRepository;

@Service
public class PartidoService {

    @Autowired
    private PartidoRepository partidoRepository;

    @Autowired
    private ArbitroRepository arbitroRepository;

    @Autowired
    private CanchaRepository canchaRepository;

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public long count() {
        return partidoRepository.count();
    }

    private Partido mapFromDTO(PartidoDTO dto) {
        Arbitro arbitro = arbitroRepository.findById(dto.getIdArbitro())
                .orElseThrow(() -> new RuntimeException("Árbitro no encontrado: " + dto.getIdArbitro()));

        Cancha cancha = canchaRepository.findById(dto.getIdCancha())
                .orElseThrow(() -> new RuntimeException("Cancha no encontrada: " + dto.getIdCancha()));

        Partido partido = new Partido();
        partido.setFecha(dto.getFecha());
        partido.setEstadioPartido(dto.getEstadioPartido());
        partido.setGolesVisitante(dto.getGolesVisitante());
        partido.setGolesLocal(dto.getGolesLocal());
        partido.setTiempoExtra(dto.getTiempoExtra());
        partido.setPenalesVisitante(dto.getPenalesVisitante());
        partido.setPenalesLocal(dto.getPenalesLocal());
        partido.setArbitro(arbitro);
        partido.setCancha(cancha);

        return partido;
    }

    private PartidoDTO mapToDTO(Partido entity) {
        PartidoDTO dto = modelMapper.map(entity, PartidoDTO.class);
        if (entity.getArbitro() != null) {
            dto.setIdArbitro(entity.getArbitro().getIdArbitro());
        }
        if (entity.getCancha() != null) {
            dto.setIdCancha(entity.getCancha().getIdCancha());
        }
        return dto;
    }

    public int create(PartidoDTO data) {
        try {
            Partido entity = mapFromDTO(data);
            partidoRepository.save(entity);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    public int update(Integer id, PartidoDTO partidoDTO) {
        Optional<Partido> existingPartido = partidoRepository.findById(id);
        if (existingPartido.isPresent()) {
            try {
                Partido partido = mapFromDTO(partidoDTO);
                partido.setIdPartido(id);
                partidoRepository.save(partido);
                return 0;
            } catch (Exception e) {
                e.printStackTrace();
                return 1;
            }
        }
        return 1;
    }

    public int deleteById(Integer id) {
        Optional<Partido> found = partidoRepository.findById(id);
        if (found.isPresent()) {
            partidoRepository.delete(found.get());
            return 0;
        }
        return 1;
    }

    public List<PartidoDTO> findAll() {
        List<Partido> entityList = partidoRepository.findAll();
        return entityList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public PartidoDTO findById(Integer id) {
        Optional<Partido> partido = partidoRepository.findById(id);
        return partido.map(this::mapToDTO).orElse(null);
    }

    public List<PartidoDTO> findByFecha(LocalDate fecha) {
        List<Partido> partidos = partidoRepository.findByFecha(fecha);
        return partidos.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<PartidoDTO> findByEstadioPartido(String estadio) {
        List<Partido> partidos = partidoRepository.findByEstadioPartido(estadio);
        return partidos.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public int agregarEquipo(Integer partidoId, Integer equipoId) {
        return 1;
    }

    public int removerEquipo(Integer partidoId, Integer equipoId) {
        return 1;
    }

    public List<EquipoDTO> obtenerEquiposDelPartido(Integer partidoId) {
        return List.of();
    }
}