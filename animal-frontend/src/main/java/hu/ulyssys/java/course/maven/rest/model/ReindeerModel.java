package hu.ulyssys.java.course.maven.rest.model;

public class ReindeerModel {
    private Long id;
    private String name;
    private Integer legsNumber;
    private Integer hornNumber;
    private Long farmerID;

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

    public Integer getHornNumber() {
        return hornNumber;
    }

    public void setHornNumber(Integer hornNumber) {
        this.hornNumber = hornNumber;
    }

    public Long getFarmerID() {
        return farmerID;
    }

    public void setFarmerID(Long farmerID) {
        this.farmerID = farmerID;
    }
}
