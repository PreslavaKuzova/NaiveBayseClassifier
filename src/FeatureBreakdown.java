import java.util.List;

public class FeatureBreakdown {
    final private Feature feature;
    final private double percentageDemocratsYes;
    final private double percentageDemocratsNo;
    final private double percentageRepublicansYes;
    final private double percentageRepublicansNo;

    public FeatureBreakdown(List<Record> records, Feature feature) {
        this.feature = feature;

        int positiveDemocrats = 0;
        int negativeDemocrats = 0;
        int positiveRepublicans = 0;
        int negativeRepublicans = 0;

        long allPositive = records
                .stream()
                .map(it -> it.answers().get(feature.getPosition()))
                .filter(it -> it == Answer.YES)
                .count();

        long allNegative = records
                .stream()
                .map(it -> it.answers().get(feature.getPosition()))
                .filter(it -> it == Answer.NO)
                .count();


        for (Record record : records) {
            if (record.answers().get(feature.getPosition()) == Answer.YES) {
                if (record.className() == Classification.DEMOCRAT) {
                    positiveDemocrats++;
                } else if (record.className() == Classification.REPUBLICAN) {
                    positiveRepublicans++;
                }
            } else if (record.answers().get(feature.getPosition()) == Answer.NO) {
                if (record.className() == Classification.DEMOCRAT) {
                    negativeDemocrats++;
                } else if (record.className() == Classification.REPUBLICAN) {
                    negativeRepublicans++;
                }
            }
        }

        percentageDemocratsYes = positiveDemocrats * 1.0 / allPositive;
        percentageDemocratsNo = negativeDemocrats * 1.0 / allNegative;
        percentageRepublicansYes = positiveRepublicans * 1.0 / allPositive;
        percentageRepublicansNo = negativeRepublicans * 1.0 / allNegative;
    }

    public Feature getFeature() {
        return feature;
    }

    public double getPercentageDemocratsYes() {
        return percentageDemocratsYes;
    }

    public double getPercentageDemocratsNo() {
        return percentageDemocratsNo;
    }

    public double getPercentageRepublicansYes() {
        return percentageRepublicansYes;
    }

    public double getPercentageRepublicansNo() {
        return percentageRepublicansNo;
    }
}