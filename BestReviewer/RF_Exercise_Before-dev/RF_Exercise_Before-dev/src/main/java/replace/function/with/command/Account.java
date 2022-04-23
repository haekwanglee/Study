package replace.function.with.command;

public class Account {
    //...

    int gamma(int inputVal, int quantity, int yearToDate){
        return new Gamma(this, inputVal, quantity, yearToDate).invoke();

    }

    int delta(){
        return 20;
    }

}
