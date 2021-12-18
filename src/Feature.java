public enum Feature {
    YES("y"),
    NO("n"),
    MISSING("?");

    private final String answer;

    Feature(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {return answer; }

    public static Feature getFeature(String value) {
        for(Feature variable : values())
            if(variable.getAnswer().equalsIgnoreCase(value)) return variable;
        return MISSING;
    }
}
