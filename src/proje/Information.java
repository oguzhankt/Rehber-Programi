// Double linked listteki her bir node a atanıcak olan bilgi sınıfı

package proje;

import java.util.ArrayList;

public class Information {

    private String adSoyad;
    private ArrayList<String> telNums;
    private int telNumberSize;

    public int getTelNumberSize() {
        return telNumberSize;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public ArrayList<String> getTelNums() {
        return telNums;
    }

    public void setTelNums(ArrayList<String> telNumbers) {
        this.telNums = telNumbers;
        this.telNumberSize = telNumbers.size();
    }

    public Information() {
        this.adSoyad = null;
        this.telNums = null;

    }

    public Information(String adSoyad, ArrayList<String> telNumbers) {
        this.adSoyad = adSoyad;
        this.telNums = telNumbers;
        this.telNumberSize = telNumbers.size();
    }

    public String toString(){
        return this.adSoyad + this.getTelNums().toString();
    }

}
