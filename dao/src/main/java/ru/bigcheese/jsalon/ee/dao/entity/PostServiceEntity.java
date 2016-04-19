package ru.bigcheese.jsalon.ee.dao.entity;

import javax.persistence.*;

/**
 * Created by BigCheese on 18.02.16.
 */
@Entity
@Table(name = "posts_services")
public class PostServiceEntity extends BaseEntity {

    private PostEntity post;
    private ServiceEntity service;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POSTS_SERVICES_SEQ")
    @SequenceGenerator(name = "POSTS_SERVICES_SEQ", sequenceName = "posts_services_id_seq", allocationSize = 1)
    public Long getId() {
        return super.getId();
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    public PostEntity getPost() {
        return post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", nullable = false)
    public ServiceEntity getService() {
        return service;
    }

    public void setService(ServiceEntity service) {
        this.service = service;
    }
}
