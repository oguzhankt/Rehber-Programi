// Bu sınıf double linked list veri yapısını ve ilgili methodları bulundurur

package proje;

import javax.swing.*;
import java.util.*;

public class DoublyLinkedList {

    private Node head;
    private Node tail;
    private int size;
    private int result;

    public DoublyLinkedList() {
        size = 0;
    }

    public class Node {
        Information info;
        Node next;
        Node prev;

        public Node(Information info, Node prev, Node next) {
            this.info = info;
            this.next = next;
            this.prev = prev;
        }

        public Node() {
            this.info = null;
            this.next = null;
            this.prev = null;
        }
    }

    public int getSize(){
        return size;
    }

    // Linked listin boş olup olmadığını döndürür
    private boolean isEmpty(){
        return size == 0;
    }

    // Listeye bilgileri alfabetik olarak ekler
    public void addAlpha(Information info){

        Node tmp = head;
        Node newNode = new Node();
        newNode.info = info;

        if(this.isEmpty()){
            this.addFirst(info);
        }else{

            if (this.size >=2) {
                this.insertNode(info);
            }else{

                if(tmp.info.getAdSoyad().compareTo(info.getAdSoyad()) > 0){

                    newNode.next = head;
                    head.prev = newNode;
                    head = newNode;
                    size++;

                }else if(tmp.info.getAdSoyad().compareTo(info.getAdSoyad()) < 0){

                    newNode.prev = tail;
                    tail.next = newNode;
                    tail = newNode;
                    size++;

                }

            }

        }

    }

    // Listenin başına bilgiyi ekler
    private void addFirst(Information info) {

        Node tmp = new Node(info, head, null);

        if(head != null ){
            head.prev = tmp;
        }

        head = tmp;
        tmp.next = tail;

        if(tail == null){
            tail = tmp;
        }

        size++;
        System.out.println("adding: "+ info);

    }

    // Listenin arasına bilgiyi ekler. addAlpha() için yardımcı method
    private Node insertNode(Information info){

        Node newNode = new Node();
        newNode.info = info;

        if (info.getAdSoyad().compareTo(head.info.getAdSoyad()) < 0) {
            newNode.next = head;
            head.prev = newNode;
            return newNode;
        }

        Node current = head;
        Node previous = null;


        result = current.next.info.getAdSoyad().compareTo(info.getAdSoyad());

        while (current.next != null){
            previous = current;
            current = current.next;


            if(result > 0){
                break;
            }

            if(current.next != null){
                result = current.next.info.getAdSoyad().compareTo(info.getAdSoyad());
            }

        }

        if (previous == null) {
            head = newNode;
        } else {
            // re-link previous node
            previous.next = newNode;
            newNode.prev = previous;
        }

        if (current != null) {
            // re-link next node
            current.prev = newNode;
            newNode.next = current;
        }

        System.out.println("adding.." + info.getAdSoyad());
        size++;
        return head;

    }

    // Node ları baştan sona doğru kullanıcıya yazdırır
    public void iterateForward(){

        System.out.println("iterating forward..");
        Node tmp = head;

        while(tmp != null){
            GraphicalUI.textLabels.add(new JLabel(toStringNodes(tmp)));
            tmp = tmp.next;
        }

    }

    // Node ları ters bir şekilde yazdırır
    public void iterateBackward(){

        System.out.println("iterating backward..");
        Node tmp = tail;

        while(tmp != null){
            GraphicalUI.textLabels.add(new JLabel(toStringNodes(tmp)));
            tmp = tmp.prev;
        }

    }

    // Baştaki node u siler
    private Information removeFirst() {

        Node tmp = head;
        head = head.next;
        head.prev = null;
        size--;
        System.out.println("deleted: "+tmp.info);
        return tmp.info;

    }

    // Sondaki node u siler
    private Information removeLast() {

        Node tmp = tail;
        tail = tail.prev;
        tail.next = null;
        size--;
        System.out.println("deleted: "+tmp.info);
        return tmp.info;

    }

    // Belli bir bilgiyi listede bulur ve node u döndürür
    public Node findName(String name){

        Node tmp = head;
        while(tmp != null){
            if(tmp.info.getAdSoyad().equals(name)){
                return tmp;
            }
            tmp = tmp.next;
        }

        return tmp;
    }

    // Belli bir isim-soyisim e ait node u bulur ve kaldırır
    public void findAndDelete(String adSoyad){

        Node nodeToRemove;
        nodeToRemove = findName(adSoyad);

        if(nodeToRemove == null){
            GraphicalUI.deleteResult.setText("İsim Bulunamadı");
        }else if(nodeToRemove == head){
            removeFirst();
            GraphicalUI.deleteResult.setText("Başarıyla Silindi");
        }else if(nodeToRemove == tail){
            removeLast();
            GraphicalUI.deleteResult.setText("Başarıyla Silindi");
        }else{
            nodeToRemove.prev.next = nodeToRemove.next;
            nodeToRemove.next.prev = nodeToRemove.prev;
            GraphicalUI.deleteResult.setText("Başarıyla Silindi");
        }
    }

    public String toStringNodes(Node n){

        if(n == null){
            return "Bulunamadı";
        }
        return n.info.toString();

    }

    public ArrayList<String> toArrayList(){

        ArrayList<String> aList = new ArrayList<>();
        String element;
        System.out.println("iterating forward..");
        Node tmp = head;
        while(tmp != null){
            element = tmp.info.getAdSoyad();

            for(int i=0 ; i < tmp.info.getTelNumberSize(); i++){
                element = element + "," + tmp.info.getTelNums().get(i);
            }
            aList.add(element);
            tmp = tmp.next;
        }

        return aList;

    }
}
