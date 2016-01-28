package ru.bigcheese.jsalon.ee.dao.entity;

import javax.persistence.*;

/**
 * Created by BigCheese on 03.08.15.
 */
@Entity
@Table(name = "posts")
@NamedQueries({
    @NamedQuery(name = PostEntity.FIND_BY_NAME, query = "SELECT p FROM PostEntity p WHERE p.name = ?1"),
    @NamedQuery(name = PostEntity.EXISTS_BY_NAME, query = "SELECT p.name FROM PostEntity p WHERE p.name = ?1")
})
public class PostEntity extends BaseEntity {

    public static final String FIND_BY_NAME =   "Post.findByName";
    public static final String EXISTS_BY_NAME = "Post.existsByName";

    private String name;

    public PostEntity() {}

    public PostEntity(Long id, String name) {
        super.setId(id);
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POSTS_SEQ")
    @SequenceGenerator(name = "POSTS_SEQ", sequenceName = "posts_id_seq", allocationSize = 1)
    public Long getId() {
        return super.getId();
    }

    @Column(nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
