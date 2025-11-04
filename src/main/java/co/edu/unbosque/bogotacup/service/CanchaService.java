package co.edu.unbosque.bogotacup.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.bogotacup.dto.CanchaDTO;
import co.edu.unbosque.bogotacup.model.Cancha;
import co.edu.unbosque.bogotacup.repository.CanchaRepository;

@Service
public class CanchaService {

    @Autowired
    private CanchaRepository canchaRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    public long count() {
        return canchaRepository.count();
    }

    public int create(CanchaDTO data) {
        Cancha entity = modelMapper.map(data, Cancha.class);
        try {
            canchaRepository.save(entity);
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }

    public int update(Integer id, CanchaDTO canchaDTO) {
        Optional<Cancha> existingCancha = canchaRepository.findById(id);
        if (existingCancha.isPresent()) {
            Cancha cancha = existingCancha.get();
            cancha.setNombre(canchaDTO.getNombre());
            cancha.setDireccion(canchaDTO.getDireccion());
            canchaRepository.save(cancha);
            return 0;
        }
        return 1;
    }

    public int deleteById(Integer id) {
        Optional<Cancha> found = canchaRepository.findById(id);
        if (found.isPresent()) {
            canchaRepository.delete(found.get());
            return 0;
        }
        return 1;
    }

    public List<CanchaDTO> findAll() {
        List<Cancha> entityList = (List<Cancha>) canchaRepository.findAll();
        List<CanchaDTO> dtoList = new ArrayList<>();
        entityList.forEach((entity) -> {
            CanchaDTO dto = modelMapper.map(entity, CanchaDTO.class);
            dtoList.add(dto);
        });
        return dtoList;
    }

    public CanchaDTO findById(Integer id) {
        Optional<Cancha> cancha = canchaRepository.findById(id);
        return cancha.map(value -> modelMapper.map(value, CanchaDTO.class)).orElse(null);
    }

    public CanchaDTO findByNombre(String nombre) {
        Optional<Cancha> cancha = canchaRepository.findByNombre(nombre);
        return cancha.map(value -> modelMapper.map(value, CanchaDTO.class)).orElse(null);
    }

    public List<CanchaDTO> findByDireccion(String direccion) {
        List<Cancha> canchas = canchaRepository.findByDireccion(direccion);
        List<CanchaDTO> dtoList = new ArrayList<>();
        canchas.forEach(cancha -> dtoList.add(modelMapper.map(cancha, CanchaDTO.class)));
        return dtoList;
    }
    
}
