
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

//TODO: 새로운 연극의 장르를 추가하기 쉬우면서 장르의 변경도 용이하게 리팩토링하시오
public class StatementPrinter {

    private StringBuilder printResult = new StringBuilder();
    private int totalAmount = 0;
    private int thisAmount = 0;
    private int volumeCredits = 0;
    private NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
    private Play play;

    public String print(Invoice invoice, Map<String, Play> plays) {
        initPrint();

        printHeader(invoice);
        for (Performance perf : invoice.performances) {
            updateEachPerf(plays, perf);
            printLineForOrder(perf, thisAmount);
        }
        printFooter();

        return printResult.toString();
    }

    private void updateEachPerf(Map<String, Play> plays, Performance perf) {
        initThisAmount();
        initPlay(plays, perf);
        updateThisAmount(perf);
        updateVolumeCredits(perf);
        updateExtraCredits(perf);
        updateTotalAmount(thisAmount);
    }

    private void initPrint() {
        totalAmount = 0;
        thisAmount = 0;
        volumeCredits = 0;
    }

    private void initThisAmount() {
        thisAmount = 0;
    }

    private void updateThisAmount(Performance perf) {
        CalculatePlay calculatePlay = play.getCalculateAmount(play.type);
        thisAmount += calculatePlay.getAmount(perf);
    }

    private void initPlay(Map<String, Play> plays, Performance perf) {
        play = plays.get(perf.playID);
    }

    private void updateVolumeCredits(Performance perf) {
        CalculatePlay calculatePlay = play.getCalculateAmount(play.type);
        volumeCredits += calculatePlay.getVolumeCredit(perf);
    }

    private void updateExtraCredits(Performance perf) {
        CalculatePlay calculatePlay = play.getCalculateAmount(play.type);
        volumeCredits += calculatePlay.getExtraCredit(perf);
    }

    private void updateTotalAmount(int thisAmount) {
        totalAmount += thisAmount;
    }

    private void printHeader(Invoice invoice) {
        printResult.append(String.format("Statement for %s\n", invoice.customer));
    }

    private void printLineForOrder(Performance perf, int thisAmount) {
        printResult.append(getPrintOrder(perf, play, thisAmount));
    }

    private String getPrintOrder(Performance perf, Play play, int thisAmount) {
        String result = "";
        result += String.format("  %s: %s (%s seats)\n", play.name, frmt.format(thisAmount / 100), perf.audience);
        return result;
    }

    private void printFooter() {
        printResult.append(getFooter(totalAmount, volumeCredits));
    }

    private String getFooter(int totalAmount, int volumeCredits) {
        String result = "";
        result += String.format("Amount owed is %s\n", frmt.format(totalAmount / 100));
        result += String.format("You earned %s credits\n", volumeCredits);
        return result;
    }

}