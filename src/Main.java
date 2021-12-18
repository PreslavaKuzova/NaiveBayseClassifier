import java.io.FileNotFoundException;
import java.util.List;

//Feature handicappedInfants, yes/no
//Feature waterProjectCostSharing, yes/no
//Feature adoptionOfTheBudgetResolution,
//Feature physicianFeeFreeze,
//Feature elSalvadorAid,
//Feature religiousGroupsInSchools,
//Feature antiSatelliteTestBan,
//Feature aidToNicaraguanContras,
//Feature mxMissile,
//Feature immigration,
//Feature synfuelsCorporationCutback,
//Feature educationSpending,
//Feature superfundRightToSue,
//Feature crime,
//Feature dutyFreeExports,
//Feature exportAdministrationActSouthAfrica



public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        List<Record> records = FileDevice.read("house-votes.data");

        for (Record record: records) {

            System.out.println(record.toString());
        }
    }
}
