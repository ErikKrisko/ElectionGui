import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

public class GUI extends JFrame implements ActionListener {

    String[] partyStrings = {"Fine Gael","Green Party/Comhaontas Glas","Non party","Sinn Féin","Comhar Críostaí/The Christian Solidarity Party",
            "People Before Profit Alliance", "The Labour Party","Fianna Fáil","The Workers' Party"};

    //Constituency Chooser
    JComboBox constituencyBox = new JComboBox(partyStrings);
    JComboBox addParty = new JComboBox(partyStrings);
    JLabel constituency = new JLabel("Party: ");

    JLabel  file = new JLabel("File:");
    JLabel  info = new JLabel("Info:");

    //Labels for ADD text fields
    JLabel surname = new JLabel("Surname:");
    JLabel firstname = new JLabel("Firstname:");
    JLabel address = new JLabel("Address:");
    JLabel party = new JLabel("Party:");
    JLabel earea = new JLabel("E Area:");

    //Label for DELETE by ID
    JLabel id = new JLabel("ID:");

    //Buttons
    JButton browse = new JButton("Browse");
    JButton run = new JButton("Run");
    JButton constituencyB = new JButton("Find");
    JButton add = new JButton("Add");
    JButton save = new JButton("Save");
    JButton delete = new JButton("Delete");

    //TextFields
    JTextField fileField = new JTextField();
    JTextField addSurname = new JTextField();
    JTextField addFirstname = new JTextField();
    JTextField addAddress = new JTextField();
    JTextField addEArea = new JTextField();
    JTextField deleteID = new JTextField();

    //Info Text Area
    TextArea infoField = new TextArea();

    ElectionParser ep;
    File fileObject;

    public GUI() {

        this.setLayout(null);

        file.setBounds(50, 40, 200, 25);
        fileField.setBounds(100, 40, 220, 25);
        browse.setBounds(340, 40, 80, 25);
        run.setBounds(430, 40, 80, 25);

        constituency.setBounds(50, 80, 220, 25);
        constituencyB.setBounds(340, 80, 80, 25);
        constituencyBox.setBounds(100, 80, 220, 25);

        info.setBounds(50, 160, 40, 25);
        infoField.setBounds(100, 160, 440, 250);

        addSurname.setBounds(50, 450, 70, 25);surname.setBounds(50,430,70,25);
        addFirstname.setBounds(130, 450, 70, 25);firstname.setBounds(130,430,70,25);
        addAddress.setBounds(210, 450, 70, 25);address.setBounds(210,430,70,25);
        addParty.setBounds(290, 450, 100, 25);party.setBounds(290,430,70,25);
        addEArea.setBounds(400, 450, 70, 25);earea.setBounds(400,430,70,25);
        add.setBounds(480, 450, 70, 25);

        save.setBounds(480 ,500, 70, 25);
        delete.setBounds(480,550,70,25);
        deleteID.setBounds(400, 550, 70, 25);id.setBounds(400,530,70,25);

        this.add(constituency);
        this.add(constituencyB);
        this.add(constituencyBox);
        this.add(file);
        this.add(info);
        this.add(fileField);
        this.add(infoField);
        this.add(browse);
        this.add(run);
        this.add(save);
        this.add(delete);
        this.add(deleteID);deleteID.setToolTipText("Enter ID to delete!");
        this.add(id);

        this.add(addSurname);addSurname.setToolTipText("Surname");
        this.add(addFirstname);addFirstname.setToolTipText("Firstname");
        this.add(addAddress);addAddress.setToolTipText("Address");
        this.add(addParty);addParty.setToolTipText("Party");
        this.add(addEArea);addEArea.setToolTipText("Area");
        this.add(add);

        this.add(surname);
        this.add(firstname);
        this.add(address);
        this.add(party);
        this.add(earea);

        delete.addActionListener(this);
        save.addActionListener(this);
        add.addActionListener(this);
        constituencyB.addActionListener(this);
        browse.addActionListener(this);
        run.addActionListener(this);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e)
    {
        //This checks which button is clicked. Could easily be a bunch of else if statements but I dont think this way causes any real issues.
        Object target = e.getSource();
        if(target == browse ) {
            //Opens file explorer and sets name of the file into the file text field
            openFile();
            fileField.setText(fileObject.getName());
            ep = new ElectionParser(fileObject);
        }
        if(fileField.getText().equals("")) {
            //When button is clicked without choosing a file
            infoField.setText("Please Browse for a csv file");
        }
        if(target == run) {
            infoField.setText(ep.outputData());
        }
        if(target == constituencyB){
            //Takes info from Combo Box and sends it to method to print out specific candidates
            infoField.setText(ep.outputDataByParty(constituencyBox.getSelectedItem()));
        }
        if(target == add){
            //Check if any of the boxes are empty, if they arent a new string will be created and pushed into a method which creates an object into the array list
            if(!addSurname.getText().equals("") && !addFirstname.getText().equals("") && !addAddress.getText().equals("") && !addParty.getSelectedItem().equals("") && !addEArea.getText().equals("")){
                String s = addSurname.getText() + "," + addFirstname.getText() +",\"" + addAddress.getText() + "\"," + addParty.getSelectedItem() + "," + addEArea.getText();
                ep.addData(s);
            }
        }
        if(target == save){
            //Runs the method in ElectionStat to save data into the csv file
            ep.saveData();
        }
        if(target == delete){
            //Send the ID in the ID text field to delete an object in the array list
            int id = Integer.parseInt(deleteID.getText());
            ep.deleteData(id);
        }
    }

    //File Explorer
    private void openFile() {
        //  Open file chooser directed at resources folder
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File((".\\")));
        //  File selection
        int result = fileChooser.showOpenDialog(new JFrame());
        if (result == JFileChooser.APPROVE_OPTION)
            fileObject = fileChooser.getSelectedFile();

    }

}



