import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<FeatureBreakdown> breakdowns = new ArrayList<>();
    static List<Record> records = new ArrayList<>();

    static double democratsPercentage;
    static double republicansPercentage;

    public static void main(String[] args) throws FileNotFoundException {
        initValues();

        //write a method to calculate the probability to be a democrat
        //write a method to calculate the probability to be a republican
        //compare the results

        for (int i = 0; i < records.size(); i++) {
            System.out.println(assignToClassifier(records.get(i)));
        }
    }

    private static void initValues() throws FileNotFoundException {
        records = FileDevice.read("house-votes.data");

        democratsPercentage = records
                .stream()
                .filter(it -> it.className() == Classification.DEMOCRAT).count() * 1.0 / records.size();

        republicansPercentage = records
                .stream()
                .filter(it -> it.className() == Classification.REPUBLICAN).count() * 1.0 / records.size();

        for (Feature feature : Feature.values()) {
            breakdowns.add(new FeatureBreakdown(records, feature));
        }
    }

    private static Classification assignToClassifier(Record record) {
        double democrats = democratsPercentage;
        double republicans = republicansPercentage;

        for (Feature feature : Feature.values()) {
            FeatureBreakdown breakdown = breakdowns.get(feature.getPosition());
            if (record.answers().get(feature.getPosition()) == Answer.YES) {
                democrats *= breakdown.getPercentageDemocratsYes();
                republicans *= breakdown.getPercentageRepublicansYes();
            } else if (record.answers().get(feature.getPosition()) == Answer.NO) {
                democrats *= breakdown.getPercentageDemocratsNo();
                republicans *= breakdown.getPercentageRepublicansNo();
            }
        }

        if (democrats > republicans) {
            return Classification.DEMOCRAT;
        }

        return Classification.REPUBLICAN;
    }
}
