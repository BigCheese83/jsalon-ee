package ru.bigcheese.jsalon.ee.dao.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by BigCheese on 04.08.15.
 */
@Entity
@Table(name = "services")
@NamedQueries({
    @NamedQuery(name = ServiceEntity.FIND_BY_NAME, query = "SELECT s FROM ServiceEntity s WHERE s.name = ?1"),
    @NamedQuery(name = ServiceEntity.EXISTS_BY_NAME, query = "SELECT s.name FROM ServiceEntity s WHERE s.name = ?1"),
    @NamedQuery(name = ServiceEntity.FILTER_BY_NAME, query = "SELECT new ru.bigcheese.jsalon.core.model.ModelTO(s.id, s.name) FROM ServiceEntity s WHERE LOWER(s.name) LIKE ?1 ESCAPE '!'")
})
public class ServiceEntity extends BaseEntity {

    public static final String FIND_BY_NAME =   "Service.findByName";
    public static final String EXISTS_BY_NAME = "Service.existsByName";
    public static final String FILTER_BY_NAME = "Service.filterByName";

    private String name;
    private BigDecimal cost;
    private Integer duration;
    private String description;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERVICES_SEQ")
    @SequenceGenerator(name = "SERVICES_SEQ", sequenceName = "services_id_seq", allocationSize = 1)
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

    @Column(nullable = false)
    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Column(nullable = false)
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
