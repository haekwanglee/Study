package replace.function.with.command;

public class Account {
    //...

    int gamma(int inputVal, int quantity, int yearToDate){
        //refactoring 1
//        return new Gamma(this,inputVal, quantity, yearToDate).execute();

        return new Gamma(this, inputVal, quantity, yearToDate).invoke();

    }

    int delta(){
        return 20;
    }

}
