public class CalculatePlayComedy extends CalculatePlay {
    @Override
    public int getAmount(Performance perf) {
        int thisAmount = 30000;
        if (perf.audience > 20) {
            thisAmount += 10000 + 500 * (perf.audience - 20);
        }
        thisAmount += 300 * perf.audience;
        return thisAmount;
    }

    @Override
    public int getVolumeCredit(Performance perf) {
        return (int) Math.max(perf.audience - 30, 0);
    }

    @Override
    public int getExtraCredit(Performance perf) {
        return (int) Math.floor(perf.audience / 5);
    }
}
