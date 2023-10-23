package newbankg.webtransactionservice.models;


import lombok.Data;

@Data
public class Client {
    private long customerIdentifier;
    private String FirstName;
    private String LastName;
}
