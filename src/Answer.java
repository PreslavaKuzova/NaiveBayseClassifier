public enum Answer {
    YES("y"),
    NO("n"),
    MISSING("?");

    private final String answer;

    Answer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {return answer; }

    public static Answer getAnswer(String value) {
        for(Answer variable : values())
            if(variable.getAnswer().equalsIgnoreCase(value)) return variable;
        return MISSING;
    }
}
