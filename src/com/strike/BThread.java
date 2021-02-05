package com.strike;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BThread extends Thread{

    private Item item;
    private Scanner scan;
    private int count = 0;
    private int sum = 0;

    public BThread(Item item){
        this.item = item;
    }

    public void run(){
        // pętla się kończy jeśli ID z pliku będzie null lub jeśli wątek A przestanie działać
        while(item.ID != null && AThread.isDead == false){
            //funkcja getInstanceWeight zwraca wartość int więc bezpośrednio możemy to dodać do sumy (oznaczenie x+=y to inaczej x = x + y), w ten sposó liczymy
            //ogólną sumę wagi każdego towaru
            sum += item.getInstanceWeight();
            //liczymy ile razy pętla się wykonała, czyli ile już elementów zsumowaliśmy
            count++;
            //tu sprawdzamy czy aktualny element jest wielokrotnością liczby 100 (x % y == 1 to warunek który mowi czy x jest podzielne przez y z resztą 1)
            if(count % 100 == 0){
                System.out.println("zliczono "+count+ " wartości");
            }

        }
        //Jeżeli wątek A skończył działanie to wypisujemy po pętli while końcową sumę
        if(AThread.isDead == true){
            System.out.println("końcowa suma wynosi: "+sum);

        }
    }
}

