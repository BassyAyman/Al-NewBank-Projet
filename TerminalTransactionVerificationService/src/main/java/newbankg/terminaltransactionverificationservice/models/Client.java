package newbankg.terminaltransactionverificationservice.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Client {
    @Id
    private long customerIdentifier;
    @Column(name = "first_name")
    private String FirstName;
    @Column(name = "last_name")
    private String LastName;

    public Client(){}
}
