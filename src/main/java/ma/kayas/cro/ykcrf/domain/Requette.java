package ma.kayas.cro.ykcrf.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import ma.kayas.cro.ykcrf.domain.enumeration.EtatRequette;

/**
 * A Requette.
 */
@Entity
@Table(name = "requette")
public class Requette implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "etat")
    private EtatRequette etat;

    @OneToOne    @JoinColumn(unique = true)
    private ComposantValeur composantValeur;

    @OneToOne    @JoinColumn(unique = true)
    private ValidationComposant validationComposant;

    @OneToMany(mappedBy = "requette")
    private Set<CommentaireRequette> commentaireRequettes = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EtatRequette getEtat() {
        return etat;
    }

    public Requette etat(EtatRequette etat) {
        this.etat = etat;
        return this;
    }

    public void setEtat(EtatRequette etat) {
        this.etat = etat;
    }

    public ComposantValeur getComposantValeur() {
        return composantValeur;
    }

    public Requette composantValeur(ComposantValeur composantValeur) {
        this.composantValeur = composantValeur;
        return this;
    }

    public void setComposantValeur(ComposantValeur composantValeur) {
        this.composantValeur = composantValeur;
    }

    public ValidationComposant getValidationComposant() {
        return validationComposant;
    }

    public Requette validationComposant(ValidationComposant validationComposant) {
        this.validationComposant = validationComposant;
        return this;
    }

    public void setValidationComposant(ValidationComposant validationComposant) {
        this.validationComposant = validationComposant;
    }

    public Set<CommentaireRequette> getCommentaireRequettes() {
        return commentaireRequettes;
    }

    public Requette commentaireRequettes(Set<CommentaireRequette> commentaireRequettes) {
        this.commentaireRequettes = commentaireRequettes;
        return this;
    }

    public Requette addCommentaireRequette(CommentaireRequette commentaireRequette) {
        this.commentaireRequettes.add(commentaireRequette);
        commentaireRequette.setRequette(this);
        return this;
    }

    public Requette removeCommentaireRequette(CommentaireRequette commentaireRequette) {
        this.commentaireRequettes.remove(commentaireRequette);
        commentaireRequette.setRequette(null);
        return this;
    }

    public void setCommentaireRequettes(Set<CommentaireRequette> commentaireRequettes) {
        this.commentaireRequettes = commentaireRequettes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Requette requette = (Requette) o;
        if (requette.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), requette.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Requette{" +
            "id=" + getId() +
            ", etat='" + getEtat() + "'" +
            "}";
    }
}
