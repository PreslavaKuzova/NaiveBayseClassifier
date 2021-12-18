public enum Classification {
    DEMOCRAT("democrat"),
    REPUBLICAN("republican"),
    UNKNOWN("unknown");

    private final String classVariable;

    Classification(String answer) {
        this.classVariable = answer;
    }

    public String getClassVariable() {
        return classVariable;
    }

    public static Classification getClassification(String value) {
        for(Classification variable : values())
            if(variable.getClassVariable().equalsIgnoreCase(value)) return variable;
        return UNKNOWN;
    }
}
