import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class ElectionParser {
    //Global Variables
    ArrayList<ElectionStat> list = new ArrayList<>();
    File file;

    Scanner sc;
    String lineTitle = "";
    String lineHeaders = "";

    public ElectionParser(File file){

        //Take the 2 first lines of the csv file , title and headers and set them as Strings. I use title but not the headers. I save them so i can print the back into the csv when saving changes.
        //After the first 2 lines it runs through the rest of the lines and throws them into the array list as objects ElectionStat.
        try {
            this.file = file;
            sc = new Scanner(file, StandardCharsets.ISO_8859_1);
            if(sc.hasNextLine()){
                lineTitle = sc.nextLine();
                String[] title = lineTitle.split(",");
                lineTitle = title[0];
            }
            if(sc.hasNextLine()){
                lineHeaders = sc.nextLine();
            }

            while(sc.hasNextLine()){
                String s = sc.nextLine();
                if(s.charAt(0) != ','){ //Checks for bs lines
                    list.add(new ElectionStat(s));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Goes through the objects within the arraylist and uses the getters and bundles them all into a big string of data. I also use the previosly mentioned title string.
    //This method is requested to return a String value so its set as the InfoField text.
    public String outputData(){
        String p = "";
        p += lineTitle + "\n\n";

        for(ElectionStat es : list){
            p += "ID: " + es.getId() + "\n"
                    + "Surname: " + es.getSurname() + "\n"
                    + "Firstname: " + es.getFirstname() + "\n"
                    + "Address: " + es.getAddress() + "\n"
                    + "Party: " + es.getParty() + "\n"
                    + "Electoral Area: " + es.getElectoralArea() + "\n\n\n";
        }
        return p;
    }

    //Goes through the objects within the arraylist and finds specific objects depending on the party chosen and uses the getters and bundles them all into a big string of data. I also use the previosly mentioned title string.
    //This method is requested to return a String value so its set as the InfoField text.
    public String outputDataByParty(Object selectedItem){
        String p = "";
        p += lineTitle + "\n\n";

        for(ElectionStat es : list){
            if(es.getParty().equals(selectedItem)){
                p += "ID: " + es.getId() + "\n"
                        + "Surname: " + es.getSurname() + "\n"
                        + "Firstname: " + es.getFirstname() + "\n"
                        + "Address: " + es.getAddress() + "\n"
                        + "Party: " + es.getParty() + "\n"
                        + "Electoral Area: " + es.getElectoralArea() + "\n\n\n";
            }
        }
        return p;
    }

    //Recieves a String. That string gets added an ID which is just the length of the array list + 1.
    //This can be broken when deleting an object in the middle, it then sets it as an already existing ID.
    //I believe this could have been fixed by getID of the last object by using list.size() - 1, but Im getting really burned out.
    public void addData(String s){
        int id = list.size() + 1;
        String string = id + "," + s;
        ElectionStat e = new ElectionStat(string);
        list.add(e);
    }

    //Goes trough all the objects in the array list(included added and excludes deleted) and updates the csv file.
    //I add back the title and headers into the file.
    public void saveData(){
        try{
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.ISO_8859_1));
            String info = lineTitle + ",,,,,\n" + lineHeaders + "\n";

            for(ElectionStat es : list){
                info += es.getId() + "," +
                        es.getSurname() + "," +
                        es.getFirstname() + "," +
                        "\"" + es.getAddress() + "\"," +
                        es.getParty() + "," +
                        es.getElectoralArea() + "\n";
            }
            pw.print(info);
            pw.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    //Has an int pushed into from one of the textfields. Compares all the ids within the array list and deletes whichever match.
    public void deleteData(int id){
        list.removeIf(es -> es.getId() == id);
    }



}


