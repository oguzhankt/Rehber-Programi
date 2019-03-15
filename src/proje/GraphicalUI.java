// Bu sınıf görsel kullanıcı arayüzünü oluşturmak için methodlar barındırır

package proje;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class GraphicalUI {

    private static JFrame theFrame;
    public static JPanel background , printBackground, searchBackground, addPersonBackground, deletePersonBackground;
    public static JButton addButton, deleteButton;
    public static ArrayList<JLabel> textLabels;
    private static JLabel searchResult;
    public static JLabel deleteResult;
    public static JTextField searchField, getPhone, getName, deleteField;
    private static JScrollPane scrollPane;
    private static final Font font = new Font("Times New Roman",Font.PLAIN, 24);

    // Grafiksel arayüzü ve ana menüyü oluşturur
    public void setUpGui(){

        // Ana frame oluşturur
        theFrame = new JFrame("Rehber");
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BorderLayout layout = new BorderLayout();
        background = new JPanel(layout);
        background.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        background.setBackground(Color.DARK_GRAY);

        // Ana menü butonlarını dizmek için grid layout
        GridLayout grid = new GridLayout(6,1);
        grid.setVgap(10);

        JPanel buttonBox = new JPanel(grid);
        buttonBox.setBackground(Color.DARK_GRAY);

        JButton print = new JButton("Tüm Rehberi Yazdır");
        print.addActionListener(new PrintListener());
        print.setFont(font);
        buttonBox.add(print);

        JButton reversePrint = new JButton("Tüm Rehberi Ters Yazdır");
        reversePrint.addActionListener(new ReversePrintListener());
        reversePrint.setFont(font);
        buttonBox.add(reversePrint);

        JButton addPerson = new JButton("Kişi Ekleme");
        addPerson.addActionListener(new AddListener());
        addPerson.setFont(font);
        buttonBox.add(addPerson);

        JButton removePerson = new JButton("Kişi Çıkarma");
        removePerson.addActionListener(new RemoveListener());
        removePerson.setFont(font);
        buttonBox.add(removePerson);

        JButton searchPerson = new JButton("Kişi Arama");
        searchPerson.addActionListener(new SearchListener());
        searchPerson.setFont(font);
        buttonBox.add(searchPerson);

        JButton exit = new JButton("Kaydet ve Çık");
        exit.addActionListener(new ExitListener());
        exit.setFont(font);
        buttonBox.add(exit);

        background.add(BorderLayout.CENTER, buttonBox);
        theFrame.getContentPane().add(background);
        theFrame.setBounds(300, 350, 900, 900);
        theFrame.pack();
        theFrame.setVisible(true);

    }

    // Tüm kişileri düz ve tersten yazdırır
    public void printAll(String mode){

        textLabels = new ArrayList<>();
        background.setVisible(false);
        printBackground = new JPanel();
        JPanel printPanel =  new JPanel();
        printPanel.setBackground(Color.DARK_GRAY);

        GridLayout grid = new GridLayout(FileTools.linkListesi.getSize(),1);
        grid.setVgap(2);

        JPanel textBackground = new JPanel(grid);
        textBackground.setBorder(BorderFactory.createEmptyBorder(10,5,10,5));

        if(mode.equals("f")){
            FileTools.linkListesi.iterateForward();
        }else if(mode.equals("b")){
            FileTools.linkListesi.iterateBackward();
        }else{
            System.out.println("Method mode not valid");
        }


        for(int i=0 ; i< textLabels.size(); i++) {

            JLabel tempLabel;
            tempLabel = textLabels.get(i);
            textBackground.add(tempLabel);

        }
        printBackground.setLayout(new BorderLayout());
        printBackground.add(BorderLayout.CENTER, textBackground);

        JButton backMenu = new JButton("Geri");
        backMenu.addActionListener(new BackButtonListener());
        backMenu.setFont(font);
        printBackground.add(BorderLayout.NORTH, backMenu);
        printBackground.setBackground(Color.DARK_GRAY);

        scrollPane = new JScrollPane(printBackground, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        theFrame.getContentPane().add(scrollPane);

    }

    // Kişi aramak için arayüzü oluşturur
    public void searchName(){

        background.setVisible(false);
        searchBackground = new JPanel(new BorderLayout());
        searchBackground.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        searchBackground.setBackground(Color.DARK_GRAY);

        searchResult = new JLabel();

        JPanel searchBack = new JPanel();
        searchBack.setBackground(Color.WHITE);
        searchBack.add(searchResult);

        JPanel searchResultBackground = new JPanel();
        searchResultBackground.setBackground(Color.DARK_GRAY);
        searchResultBackground.add(BorderLayout.CENTER, searchBack);
        searchResultBackground.setBorder(BorderFactory.createEmptyBorder(100, 10,100,10));

        searchBackground.add(BorderLayout.CENTER, searchResultBackground);

        searchField = new JTextField();
        JPanel searchControl = new JPanel(new BorderLayout());
        searchControl.add(BorderLayout.NORTH, searchField);

        JButton searchButton = new JButton("Ara");
        searchButton.addActionListener(new SearchCommandListener());
        searchButton.setFont(font);
        searchControl.add(BorderLayout.SOUTH, searchButton);

        searchBackground.add(BorderLayout.SOUTH, searchControl);

        JButton backMenu = new JButton("Geri");
        backMenu.addActionListener(new BackButtonListener1());
        backMenu.setFont(font);
        searchBackground.add(BorderLayout.NORTH, backMenu);

        theFrame.getContentPane().add(searchBackground);

    }

    // Kişi ararken kullanılıcak methodları çalıştırır
    public void findSearchedName(){

        String command;
        command = searchField.getText();
        searchResult.setText(FileTools.linkListesi.toStringNodes(FileTools.linkListesi.findName(command)));

    }

    // Kişi eklemek için arayüz oluşturur
    public void addPerson(){

        background.setVisible(false);
        addPersonBackground = new JPanel(new BorderLayout());
        addPersonBackground.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        addPersonBackground.setBackground(Color.DARK_GRAY);

        JButton backButton = new JButton("Geri");
        backButton.setFont(font);
        backButton.addActionListener(new BackButtonListener2());
        addPersonBackground.add(BorderLayout.NORTH, backButton);

        GridLayout layout = new GridLayout(2,1);
        layout.setVgap(5);
        JPanel addControls = new JPanel(layout);

        getName = new JTextField();
        JLabel nameLabel = new JLabel("Ad-Soyad :");

        JPanel nameArea = new JPanel(new GridLayout(2,1));
        nameArea.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        nameArea.add(nameLabel);
        nameArea.add(getName);

        getPhone = new JTextField();
        JLabel phoneLabel = new JLabel("Telefon Numarası : (Birden fazla için virgülle ayırın)");

        JPanel phoneArea = new JPanel(new GridLayout(2,1));
        phoneArea.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        phoneArea.add(phoneLabel);
        phoneArea.add(getPhone);

        addControls.add(nameArea);
        addControls.add(phoneArea);
        addPersonBackground.add(BorderLayout.CENTER, addControls);


        addButton = new JButton("Kişiyi Ekle");
        addButton.setFont(font);
        addButton.addActionListener(new AddCommandListener());

        addPersonBackground.add(BorderLayout.SOUTH, addButton);

        theFrame.getContentPane().add(addPersonBackground);

    }

    // Kişi ekleme butonuna basılmasıyla gerçekleşi
    public void addGivenName(){

        String adSoyad, telNumStr;
        ArrayList<String> telNumaraları = new ArrayList<>();

        adSoyad = getName.getText();
        telNumStr = getPhone.getText();

        // For döngüsü için kullanılıcak virgüllerle ayrılmış toplam bilgi sayısı
        int tokenNumber;

        StringTokenizer st = new StringTokenizer(telNumStr, ",");
        tokenNumber = st.countTokens();

        for(int i=0; i<tokenNumber; i++){
            telNumaraları.add(st.nextToken());
        }

        Information infoToAdd = new Information(adSoyad, telNumaraları);
        FileTools.linkListesi.addAlpha(infoToAdd);

        getName.setText("Başarıyla Eklendi");
        getPhone.setText("");

    }

    // Kişileri silmek için arayüz oluşturur
    public void deletePerson(){

        background.setVisible(false);
        deletePersonBackground = new JPanel(new BorderLayout());
        deletePersonBackground.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        deletePersonBackground.setBackground(Color.DARK_GRAY);

        JButton backButton = new JButton("Geri");
        backButton.setFont(font);
        backButton.addActionListener(new BackButtonListener3());
        deletePersonBackground.add(BorderLayout.NORTH, backButton);

        deleteButton = new JButton("Kişiyi Sil");
        deleteButton.setFont(font);
        deleteButton.addActionListener(new DeleteCommandListener());

        deleteField = new JTextField();

        JPanel deleteArea = new JPanel(new GridLayout(2,1));
        deleteArea.add(deleteField);
        deleteArea.add(deleteButton);

        deleteResult = new JLabel();

        JPanel deleteBack = new JPanel();
        deleteBack.setBackground(Color.WHITE);
        deleteBack.add(deleteResult);

        JPanel deleteResultBackground = new JPanel();
        deleteResultBackground.setBackground(Color.DARK_GRAY);
        deleteResultBackground.add(BorderLayout.CENTER, deleteBack);
        deleteResultBackground.setBorder(BorderFactory.createEmptyBorder(100, 10,100,10));

        deletePersonBackground.add(BorderLayout.CENTER, deleteResultBackground);

        deletePersonBackground.add(BorderLayout.SOUTH, deleteArea);

        theFrame.getContentPane().add(deletePersonBackground);

    }

    // Kişileri silerken kullanılıcak methodları barındırır
    public void deleteGivenName(){

        String adSoyad = deleteField.getText();
        FileTools.linkListesi.findAndDelete(adSoyad);

    }

    // Bilgileri kaydeder ve programı kapatır
    public void saveAndExit(){

        FileTools.saveFile();
        System.exit(0);

    }

    public class PrintListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            printAll("f");
        }
    }

    public class ReversePrintListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            printAll("b");
        }
    }

    public class AddListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            addPerson();
        }
    }

    public class RemoveListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            deletePerson();
        }
    }

    public class SearchListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            searchName();
        }
    }

    public class ExitListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            saveAndExit();
        }
    }

    public class BackButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            background.setVisible(true);
            printBackground.setVisible(false);
            scrollPane.setVisible(false);
        }
    }

    public class BackButtonListener1 implements ActionListener{
        public void actionPerformed(ActionEvent e){
            background.setVisible(true);
            searchBackground.setVisible(false);
        }
    }

    public class BackButtonListener2 implements ActionListener{
        public void actionPerformed(ActionEvent e){
            background.setVisible(true);
            addPersonBackground.setVisible(false);
        }
    }

    public class BackButtonListener3 implements ActionListener{
        public void actionPerformed(ActionEvent e){
            background.setVisible(true);
            deletePersonBackground.setVisible(false);
        }
    }

    public class DeleteCommandListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            deleteGivenName();
        }
    }

    public class AddCommandListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            addGivenName();
        }
    }

    public class SearchCommandListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            findSearchedName();
        }
    }
}
