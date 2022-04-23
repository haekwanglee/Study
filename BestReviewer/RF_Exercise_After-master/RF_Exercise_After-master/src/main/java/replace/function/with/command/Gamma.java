package replace.function.with.command;

//refactoring 2
// account.gamma() 에서 refactor - replace method with on method object
// refactor - Move inner class to upper level
class Gamma {
    private final Account account;
    private int inputVal;
    private int quantity;
    private int yearToDate;
    private int importantValue1;
    private int importantValue2;

    public Gamma(Account account, int inputVal, int quantity, int yearToDate) {
        this.account = account;
        this.inputVal = inputVal;
        this.quantity = quantity;
        this.yearToDate = yearToDate;
    }

    public int invoke() {
        importantValue1 = (inputVal * quantity) + account.delta();
        importantValue2 = (inputVal * yearToDate) + 100;

        doImportantThing();

        int importantValue3 = importantValue2 * 7;
        //and so on

        return importantValue3  - 2* importantValue1;
    }

    protected void doImportantThing() {
        if((yearToDate - importantValue1) > 100){
            importantValue2 -= 20;
        }
    }
}

// refactoring 1 - gamma class 생성, execute 메소드 생성, account 매개변수로 변경
// refactor - show context actions, create method 이용
//public class Gamma {
//    private final Account account;
//    private int inputVal;
//    private int quantity;
//    private int yearToDate;
//
//    public Gamma(Account account, int inputVal, int quantity, int yearToDate) {
//        this.account = account;
//        this.inputVal = inputVal;
//        this.quantity = quantity;
//        this.yearToDate = yearToDate;
//    }
//
//    public int execute() {
//        int importantValue1 = (inputVal * quantity) + account.delta();
//        int importantValue2 = (inputVal * yearToDate) + 100;
//
//        if((yearToDate - importantValue1) > 100){
//            importantValue2 -= 20;
//        }
//
//        int importantValue3 = importantValue2 * 7;
//        //and so on
//
//        return importantValue3  - 2*importantValue1;
//    }
//}
