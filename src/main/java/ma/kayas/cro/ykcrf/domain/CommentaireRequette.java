package ma.kayas.cro.ykcrf.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A CommentaireRequette.
 */
@Entity
@Table(name = "commentaire_requette")
public class CommentaireRequette implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "commentaire")
    private String commentaire;

    @ManyToOne
    @JsonIgnoreProperties("commentaireRequettes")
    private Requette requette;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public CommentaireRequette commentaire(String commentaire) {
        this.commentaire = commentaire;
        return this;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Requette getRequette() {
        return requette;
    }

    public CommentaireRequette requette(Requette requette) {
        this.requette = requette;
        return this;
    }

    public void setRequette(Requette requette) {
        this.requette = requette;
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
        CommentaireRequette commentaireRequette = (CommentaireRequette) o;
        if (commentaireRequette.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), commentaireRequette.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CommentaireRequette{" +
            "id=" + getId() +
            ", commentaire='" + getCommentaire() + "'" +
            "}";
    }
}
