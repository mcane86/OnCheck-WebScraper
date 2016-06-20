/**
 * Created by stevejaminson on 6/14/16.
 */
public class InspectionObject {

    private String entity;
    private String address;
    private String city;
    private String zipCode;
    private String County;
    private String inspectionType;
    private String date;
    private String violations;

    public InspectionObject(){}

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCounty() {
        return County;
    }

    public void setCounty(String county) {
        County = county;
    }

    public String getInspectionType() {
        return inspectionType;
    }

    public void setInspectionType(String inspectionType) {
        this.inspectionType = inspectionType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getViolations() {
        return violations;
    }

    public void setViolations(String violations) {
        this.violations = violations;
    }

    public String getAll(){
        String result = "";

        result = "Entity: " + this.getEntity() + "\n" + "Adress: " + this.getAddress() + "\n" + "City: " + this.getCity() + "\n" +
                "ZipCode: " + this.getZipCode() + "\n" + "County: " + this.getCounty() + "\n" + "Inspection Type : " + this.getInspectionType() + "\n" +
                "Date: " + this.getDate() + "\n" + "Violations: " + this.getViolations() + "\n\n";

        return result;
    }
}
