public abstract class CalculatePlay {

    public static CalculatePlay createByType(String type) {

        switch (type) {
            case Play.TRAGEDY:
                return new CalculatePlayTragedy();
            case Play.COMEDY:
                return new CalculatePlayComedy();
            default:
                throw new Error("unknown type: ${play.type}");
        }
    }

    public abstract int getAmount(Performance perf);
    public abstract int getVolumeCredit(Performance perf);
    public abstract int getExtraCredit(Performance perf);
}
