import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Main {
    static final int N_FOLD_CROSS_VALIDATION_VALUE = 10;

    static List<FeatureBreakdown> breakdowns = new ArrayList<>();
    static List<Record> allRecords = new ArrayList<>();
    static List<List<Record>> nFoldCrossValidationRecords = new ArrayList<>();

    static double democratsPercentage;
    static double republicansPercentage;

    public static void main(String[] args) throws FileNotFoundException {
        allRecords = FileDevice.read("house-votes.data");
        Collections.shuffle(allRecords);
        divideIntoNTestGroups();
        calculateAccuracy();
    }

    private static void calculateAccuracy() {
        double modelAccuracy = 0.0;

        for (int i = 0; i < N_FOLD_CROSS_VALIDATION_VALUE; i++) {
            // Only use N - 1 lists of BreastCancerData out of all N
            // Create a list that holds off the data of these N - 1 lists (train set)
            // Base of these N - 1 list values, we calculate:
            // - the total percentage of DEMOCRATS (democratsPercentage) and REPUBLICANS(republicansPercentage)
            // - a list of FeatureBreakdown (each holds information about the percentage of positiveDemocrats, negativeDemocrats,
            //   positiveRepublicans, negativeRepublicans)
            initCurrentTestValues(i);

            System.out.println("Test No " + (i + 1));

            int correctPredictions = 0;
            for (Record record: nFoldCrossValidationRecords.get(i)) {
                Classification prediction = assignToClassifier(record);
                if(prediction == record.className()) {
                    correctPredictions++;
                }
            }
            double currentIterationAccuracy = correctPredictions * 1.0 / nFoldCrossValidationRecords.get(i).size();
            modelAccuracy += currentIterationAccuracy;
            System.out.println("Current test accuracy: " + currentIterationAccuracy);
        }

        System.out.println("Model accuracy: " + modelAccuracy / N_FOLD_CROSS_VALIDATION_VALUE);
    }

    private static void initCurrentTestValues(int indexOfCurrentTestGroup) {
        democratsPercentage = nFoldCrossValidationRecords
                .stream()
                .filter(it -> nFoldCrossValidationRecords.indexOf(it) != indexOfCurrentTestGroup)
                .flatMap(Collection::stream)
                .filter(it -> it.className() == Classification.DEMOCRAT).count() * 1.0 / allRecords.size();

        republicansPercentage = nFoldCrossValidationRecords
                .stream()
                .filter(it -> nFoldCrossValidationRecords.indexOf(it) != indexOfCurrentTestGroup)
                .flatMap(Collection::stream)
                .filter(it -> it.className() == Classification.REPUBLICAN).count() * 1.0 / allRecords.size();

        // Each FeatureBreakdown holds information about the percentage of positiveDemocrats, negativeDemocrats,
        // positiveRepublicans, negativeRepublicans, at the same position the feature is
        // E.g. Feature HANDICAPPED_INFANT(0) -> FeatureBreakdown.get(0) will be the breakdown of HANDICAPPED_INFANT
        for (Feature feature : Feature.values()) {
            breakdowns.add(new FeatureBreakdown(allRecords, feature));
        }
    }

    private static void divideIntoNTestGroups() {
        int chunk = allRecords.size() / N_FOLD_CROSS_VALIDATION_VALUE;

        for (int i = 0; i < allRecords.size(); i += chunk) {
            nFoldCrossValidationRecords
                    .add(allRecords.subList(i, Math.min(i + chunk, allRecords.size())));
        }

    }

    // P(Democrat|record) = (P(feature1Value|Democrat)...P(featureNValue|Democrat))/P(record)
    // P(Republican|record) = (P(feature1Value|Republican)...P(featureNValue|Republican))/P(record)
    // Since P(record) is common, we don't have to calculate it, and we can ignore it
    private static Classification assignToClassifier(Record record) {
        double democrats = democratsPercentage;
        double republicans = republicansPercentage;

        for (Feature feature : Feature.values()) {
            FeatureBreakdown breakdown = breakdowns.get(feature.getPosition());
            // Depending on the answer of the given Feature we extract the value of the given FeatureBreakdown
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
