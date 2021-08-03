package hu.ulyssys.java.course.maven.entity;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractAnimal implements AnimalTypeAware {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "legs_number")
    private Integer legsNumber;

    @JoinColumn(name = "farmer_id")
    @ManyToOne
    private Farmer farmer;

    public AbstractAnimal() {
    }

    public AbstractAnimal(Long id, String name, Integer legsNumber) {
        this.id = id;
        this.name = name;
        this.legsNumber = legsNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLegsNumber() {
        return legsNumber;
    }

    public void setLegsNumber(Integer legsNumber) {
        this.legsNumber = legsNumber;
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }
}
