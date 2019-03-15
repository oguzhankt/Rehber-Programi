// Bu classta dosyayla ilgili methodlar vardır

package proje;

import java.io.PrintWriter;
import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileTools {

    private static Scanner dosya;
    private static ArrayList<String> telNumbers, stringsToSave;
    public static DoublyLinkedList linkListesi = new DoublyLinkedList();
    private static ArrayList<Information> info = new ArrayList<>();

    // Dosyayı okuyup müşteri bilgilerini alan method
    public void readFile(){

        this.initializeFile();
        this.getInformation();
        this.setLinkedList();

    }

    // Dosyayı okumaya hazır hale getirir
    private void initializeFile(){

        try{
            dosya = new Scanner(new FileInputStream("rehber.txt"));
        }
        catch(FileNotFoundException e){
            System.out.println("Dosya bulunamadı");
            System.exit(0);
        }

    }

    // Dosyadan asıl kişi bilgilerini alır
    private void getInformation(){

        // For döngüsü için kullanılıcak virgüllerle ayrılmış toplam bilgi sayısı
        int tokenNumber;
        String adSoyad = null;
        String satır, data;
        StringTokenizer st;

        // Asıl kişi bilgilerini alır ve her biri için bir class oluşturur
        do{

            satır = dosya.nextLine();
            st = new StringTokenizer(satır, ",");
            telNumbers = new ArrayList<>();
            // For döngüsünün ne kadar çalışıcağını belirlemek için
            tokenNumber = st.countTokens();

            Information temp;

            for(int i=0; i<tokenNumber; i++) {
                data = st.nextToken();

                if(i==0){
                    adSoyad = data;
                }
                else{
                    telNumbers.add(data);
                }

            }

            temp = new Information(adSoyad, telNumbers);
            info.add(temp);

        }while(dosya.hasNextLine());

        for(int i=0 ; i< info.size(); i++){
            System.out.println(info.get(i).toString());
        }

    }

    // Dosyadan alınan bilgilerle linked listi kurar
    public void setLinkedList(){

        for(int i=0 ; i< info.size(); i++){
            linkListesi.addAlpha(info.get(i));
        }

    }

    // Programdan çıkmadan önce dosyaya kişileri kaydeder
    public static void saveFile(){

        stringsToSave = new ArrayList<>();

        try{
            PrintWriter writer = new PrintWriter("sakla.txt", "UTF-8");

            stringsToSave = linkListesi.toArrayList();

            for (String aStringToSave : stringsToSave) {
                writer.println(aStringToSave);
            }

            writer.close();
        } catch (Exception e) {
            System.exit(0);
        }

    }

}
