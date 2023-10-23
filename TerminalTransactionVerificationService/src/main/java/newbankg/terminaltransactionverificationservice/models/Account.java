package newbankg.terminaltransactionverificationservice.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.ToString;

@ToString
@Entity
public class Account {

    @Column
    private String name;

    @Column
    private String lastname;

    @Id
    private long id;

    public Account(String name, String lastname, long id) {
        this.name = name;
        this.lastname = lastname;
        this.id = id;
    }

    public Account() {
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
