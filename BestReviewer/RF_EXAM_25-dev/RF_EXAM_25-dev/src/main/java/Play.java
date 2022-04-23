public class Play {
    public static final String TRAGEDY = "tragedy";
    public static final String COMEDY = "comedy";

    protected String name;
    protected String type;
    protected CalculatePlay calculatePlay;

    public Play(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public CalculatePlay getCalculateAmount(String type) {
        try {
           this.calculatePlay = CalculatePlay.createByType(type);
        } catch (Exception e) {
            throw e;
        }
        return calculatePlay;
    }

}