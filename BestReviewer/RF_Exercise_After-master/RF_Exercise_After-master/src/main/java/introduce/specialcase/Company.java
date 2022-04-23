package introduce.specialcase;

public class Company {
    private introduce.specialcase.Customer customer;

    public introduce.specialcase.Customer getCustomer() {

        return customer == null ? new UnknownCustomer() : customer;
    }

    public void setCustomer(introduce.specialcase.Customer customer) {
        this.customer = customer;
    }}
