package ru.bigcheese.jsalon.ee.dao.entity;

import javax.persistence.*;

/**
 * Created by BigCheese on 03.08.15.
 */
@Entity
@Table(name = "discounts")
@NamedQueries({
    @NamedQuery(name = DiscountEntity.FIND_BY_NAME, query = "SELECT d FROM DiscountEntity d WHERE d.name = ?1"),
    @NamedQuery(name = DiscountEntity.EXISTS_BY_NAME, query = "SELECT d.name FROM DiscountEntity d WHERE d.name = ?1")
})
public class DiscountEntity extends BaseEntity {

    public static final String FIND_BY_NAME =   "Discount.findByName";
    public static final String EXISTS_BY_NAME = "Discount.existsByName";

    private String name;
    private Integer value;

    public DiscountEntity() {}

    public DiscountEntity(Long id, String name, Integer value) {
        super.setId(id);
        this.name = name;
        this.value = value;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DISCOUNTS_SEQ")
    @SequenceGenerator(name = "DISCOUNTS_SEQ", sequenceName = "discounts_id_seq", allocationSize = 1)
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
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
