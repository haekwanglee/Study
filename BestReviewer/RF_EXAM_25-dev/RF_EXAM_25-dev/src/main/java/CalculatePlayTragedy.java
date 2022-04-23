public class CalculatePlayTragedy extends CalculatePlay {
    @Override
    public int getAmount(Performance perf) {
        int thisAmount = 40000;
        if (perf.audience > 30) {
            thisAmount += 1000 * (perf.audience - 30);
        }
        return thisAmount;
    }

    @Override
    public int getVolumeCredit(Performance perf) {
        return (int) Math.max(perf.audience - 30, 0);
    }

    @Override
    public int getExtraCredit(Performance perf) {
        return 0;
    }
}
