import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileDevice {
    public static final String REGEX = ",";

    public static List<Record> read(String inputDirectory) throws FileNotFoundException {
        List<Record> records = new ArrayList<>();

        InputStream stream = new FileInputStream(inputDirectory);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {

            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(REGEX);

                records.add(
                        new Record(
                                Classification.getClassification(values[0]),
                                Arrays.asList(
                                        Feature.getFeature(values[1]),
                                        Feature.getFeature(values[2]),
                                        Feature.getFeature(values[3]),
                                        Feature.getFeature(values[4]),
                                        Feature.getFeature(values[5]),
                                        Feature.getFeature(values[6]),
                                        Feature.getFeature(values[7]),
                                        Feature.getFeature(values[8]),
                                        Feature.getFeature(values[9]),
                                        Feature.getFeature(values[10]),
                                        Feature.getFeature(values[11]),
                                        Feature.getFeature(values[12]),
                                        Feature.getFeature(values[13]),
                                        Feature.getFeature(values[14]),
                                        Feature.getFeature(values[15]),
                                        Feature.getFeature(values[16]))
                        )
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return records;
    }
}
