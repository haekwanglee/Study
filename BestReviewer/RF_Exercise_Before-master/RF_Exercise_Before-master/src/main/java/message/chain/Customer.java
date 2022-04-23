package message.chain;

public class Customer {
    private final String name;
    private Address address;

    Customer(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
