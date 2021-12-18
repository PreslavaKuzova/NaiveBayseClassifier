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
                                        Answer.getAnswer(values[1]),
                                        Answer.getAnswer(values[2]),
                                        Answer.getAnswer(values[3]),
                                        Answer.getAnswer(values[4]),
                                        Answer.getAnswer(values[5]),
                                        Answer.getAnswer(values[6]),
                                        Answer.getAnswer(values[7]),
                                        Answer.getAnswer(values[8]),
                                        Answer.getAnswer(values[9]),
                                        Answer.getAnswer(values[10]),
                                        Answer.getAnswer(values[11]),
                                        Answer.getAnswer(values[12]),
                                        Answer.getAnswer(values[13]),
                                        Answer.getAnswer(values[14]),
                                        Answer.getAnswer(values[15]),
                                        Answer.getAnswer(values[16]))
                        )
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return records;
    }
}
