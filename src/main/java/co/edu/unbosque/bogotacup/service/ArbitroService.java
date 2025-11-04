package co.edu.unbosque.bogotacup.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.bogotacup.dto.ArbitroDTO;
import co.edu.unbosque.bogotacup.model.Arbitro;
import co.edu.unbosque.bogotacup.repository.ArbitroRepository;

@Service
public class ArbitroService {

    @Autowired
    private ArbitroRepository arbitroRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    public long count() {
        return arbitroRepository.count();
    }

    public int create(ArbitroDTO data) {
        Arbitro entity = modelMapper.map(data, Arbitro.class);
        try {
            arbitroRepository.save(entity);
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }

    public int update(Integer id, ArbitroDTO arbitroDTO) {
        Optional<Arbitro> existingArbitro = arbitroRepository.findById(id);
        if (existingArbitro.isPresent()) {
            Arbitro arbitro = existingArbitro.get();
            arbitro.setNombre(arbitroDTO.getNombre());
            arbitro.setApellido(arbitroDTO.getApellido());
            arbitroRepository.save(arbitro);
            return 0;
        }
        return 1;
    }

    public int deleteById(Integer id) {
        Optional<Arbitro> found = arbitroRepository.findById(id);
        if (found.isPresent()) {
            arbitroRepository.delete(found.get());
            return 0;
        }
        return 1;
    }

    public List<ArbitroDTO> findAll() {
        List<Arbitro> entityList = (List<Arbitro>) arbitroRepository.findAll();
        List<ArbitroDTO> dtoList = new ArrayList<>();
        entityList.forEach((entity) -> {
            ArbitroDTO dto = modelMapper.map(entity, ArbitroDTO.class);
            dtoList.add(dto);
        });
        return dtoList;
    }

    public ArbitroDTO findById(Integer id) {
        Optional<Arbitro> arbitro = arbitroRepository.findById(id);
        return arbitro.map(value -> modelMapper.map(value, ArbitroDTO.class)).orElse(null);
    }

    public List<ArbitroDTO> findByNombre(String nombre) {
        List<Arbitro> arbitros = arbitroRepository.findByNombre(nombre);
        List<ArbitroDTO> dtoList = new ArrayList<>();
        arbitros.forEach(arbitro -> dtoList.add(modelMapper.map(arbitro, ArbitroDTO.class)));
        return dtoList;
    }

    public List<ArbitroDTO> findByApellido(String apellido) {
        List<Arbitro> arbitros = arbitroRepository.findByApellido(apellido);
        List<ArbitroDTO> dtoList = new ArrayList<>();
        arbitros.forEach(arbitro -> dtoList.add(modelMapper.map(arbitro, ArbitroDTO.class)));
        return dtoList;
    }
    
}
