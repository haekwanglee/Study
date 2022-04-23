package message.chain;

public class Address {
    private String zipCode;

    public Address(String zipCode) {
        this.zipCode = zipCode;
    }

    String getZipCode() {
        return zipCode;
    }
}
