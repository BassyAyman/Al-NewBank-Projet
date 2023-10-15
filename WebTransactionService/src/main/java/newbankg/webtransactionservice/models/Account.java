package newbankg.webtransactionservice.models;

public class Account {

    private String name;
    private String lastname;
    private long id;

    public Account(String name, String lastname, long id) {
        this.name = name;
        this.lastname = lastname;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public long getId() {
        return id;
    }
}
