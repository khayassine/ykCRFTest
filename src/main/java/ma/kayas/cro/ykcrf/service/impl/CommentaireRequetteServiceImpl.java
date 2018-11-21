package ma.kayas.cro.ykcrf.service.impl;

import ma.kayas.cro.ykcrf.service.CommentaireRequetteService;
import ma.kayas.cro.ykcrf.domain.CommentaireRequette;
import ma.kayas.cro.ykcrf.repository.CommentaireRequetteRepository;
import ma.kayas.cro.ykcrf.service.dto.CommentaireRequetteDTO;
import ma.kayas.cro.ykcrf.service.mapper.CommentaireRequetteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing CommentaireRequette.
 */
@Service
@Transactional
public class CommentaireRequetteServiceImpl implements CommentaireRequetteService {

    private final Logger log = LoggerFactory.getLogger(CommentaireRequetteServiceImpl.class);

    private final CommentaireRequetteRepository commentaireRequetteRepository;

    private final CommentaireRequetteMapper commentaireRequetteMapper;

    public CommentaireRequetteServiceImpl(CommentaireRequetteRepository commentaireRequetteRepository, CommentaireRequetteMapper commentaireRequetteMapper) {
        this.commentaireRequetteRepository = commentaireRequetteRepository;
        this.commentaireRequetteMapper = commentaireRequetteMapper;
    }

    /**
     * Save a commentaireRequette.
     *
     * @param commentaireRequetteDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CommentaireRequetteDTO save(CommentaireRequetteDTO commentaireRequetteDTO) {
        log.debug("Request to save CommentaireRequette : {}", commentaireRequetteDTO);

        CommentaireRequette commentaireRequette = commentaireRequetteMapper.toEntity(commentaireRequetteDTO);
        commentaireRequette = commentaireRequetteRepository.save(commentaireRequette);
        return commentaireRequetteMapper.toDto(commentaireRequette);
    }

    /**
     * Get all the commentaireRequettes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CommentaireRequetteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CommentaireRequettes");
        return commentaireRequetteRepository.findAll(pageable)
            .map(commentaireRequetteMapper::toDto);
    }


    /**
     * Get one commentaireRequette by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CommentaireRequetteDTO> findOne(Long id) {
        log.debug("Request to get CommentaireRequette : {}", id);
        return commentaireRequetteRepository.findById(id)
            .map(commentaireRequetteMapper::toDto);
    }

    /**
     * Delete the commentaireRequette by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CommentaireRequette : {}", id);
        commentaireRequetteRepository.deleteById(id);
    }
}
