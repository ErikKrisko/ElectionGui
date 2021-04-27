public class ElectionStat implements Comparable<ElectionStat> {
    private int id;
    private String surname;
    private String firstname;
    private String address;
    private String party;
    private String electoralArea;


    public ElectionStat(String line){

        //Parses the specific csv file. Takes one line of the file, splits it by " to seperate the address from the rest.
        //Then Splits it by , to split apart each other variable.
        //Sets the variables as the strings that were split up

        String[] lineStrings = line.split("\"");
        String s1 = lineStrings[0];
        String s3 = lineStrings[2];

        String[] firstString = s1.split(",");
        String[] secondString = s3.split(",");

        id = Integer.parseInt(firstString[0]);
        surname = firstString[1];
        firstname = firstString[2];
        address = lineStrings[1];
        party = secondString[1];
        electoralArea = secondString[2];

    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getParty() { return party; }
    public void setParty(String party) { this.party = party; }

    public String getElectoralArea() { return electoralArea; }
    public void setElectoralArea(String electoralArea) { this.electoralArea = electoralArea; }

    @Override
    public String toString() {
        return "ElectionStat{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", address='" + address + '\'' +
                ", party='" + party + '\'' +
                ", electoralArea='" + electoralArea + '\'' +
                '}';
    }

//I do not dare touch this
    @Override
    public int compareTo(ElectionStat o) {
        return 0;
    }
}
