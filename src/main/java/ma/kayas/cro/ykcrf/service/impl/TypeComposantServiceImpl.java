package ma.kayas.cro.ykcrf.service.impl;

import ma.kayas.cro.ykcrf.service.TypeComposantService;
import ma.kayas.cro.ykcrf.domain.TypeComposant;
import ma.kayas.cro.ykcrf.repository.TypeComposantRepository;
import ma.kayas.cro.ykcrf.service.dto.TypeComposantDTO;
import ma.kayas.cro.ykcrf.service.mapper.TypeComposantMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing TypeComposant.
 */
@Service
@Transactional
public class TypeComposantServiceImpl implements TypeComposantService {

    private final Logger log = LoggerFactory.getLogger(TypeComposantServiceImpl.class);

    private final TypeComposantRepository typeComposantRepository;

    private final TypeComposantMapper typeComposantMapper;

    public TypeComposantServiceImpl(TypeComposantRepository typeComposantRepository, TypeComposantMapper typeComposantMapper) {
        this.typeComposantRepository = typeComposantRepository;
        this.typeComposantMapper = typeComposantMapper;
    }

    /**
     * Save a typeComposant.
     *
     * @param typeComposantDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TypeComposantDTO save(TypeComposantDTO typeComposantDTO) {
        log.debug("Request to save TypeComposant : {}", typeComposantDTO);

        TypeComposant typeComposant = typeComposantMapper.toEntity(typeComposantDTO);
        typeComposant = typeComposantRepository.save(typeComposant);
        return typeComposantMapper.toDto(typeComposant);
    }

    /**
     * Get all the typeComposants.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TypeComposantDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TypeComposants");
        return typeComposantRepository.findAll(pageable)
            .map(typeComposantMapper::toDto);
    }


    /**
     * Get one typeComposant by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TypeComposantDTO> findOne(Long id) {
        log.debug("Request to get TypeComposant : {}", id);
        return typeComposantRepository.findById(id)
            .map(typeComposantMapper::toDto);
    }

    /**
     * Delete the typeComposant by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeComposant : {}", id);
        typeComposantRepository.deleteById(id);
    }
}
