package com.strike;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AThread extends Thread {
    private Item item;
    private Scanner scan;
    private int count = 0;
    //zmienna isDead ma za zadanie sprawdzenie czy wątek 'żyje' tak aby móc w odpowiednim momencie zakończyć wątek B, dodałem ja bo w sytuacji jeśli wątek A napotakł koniec pliku
    // to wątek B dalej działał przez co nie wypisywał sumy końcowej, możliwe że jest na to lepszy sposó ale ja wymyśliłem to i nie chciało mi sie szukać już
    public static boolean isDead = false;
    public AThread(Item item){
        this.item = item;
        try {
            scan = new Scanner(new File(System.getProperty("user.home") + "/Desktop/Towary.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        //pętla while działa dopóki w pliku są nowe wiersze, jeśli napotkamy koniec pliku pętla się nie wykona
        while(scan.hasNext()){
            //funkcja createInstance nie zwraca wartości więc wykonujemy ją 'samodzielnie', czyli bez przypisania jej do wartości
            item.createInstance(scan.next(), Integer.parseInt(scan.next()));
            //licznik przejść pętli, mówi ile już instancji zostało stworzonych
            count++;
            //wypisanie na konsole informacji o utworzeniu 200,400,600... klas
            if(count % 200 == 0){
                System.out.println("utworzono "+count+ " obiektów");
            }
        }
        //jeśli pętla while się przerwie, to znaczy że plik się skończył, więc zanim zakończy się funkcja run ustawiam zmienną isDead na true, tak aby wątek B wiedział o tym
        //że również może się zakończyć
        isDead = true;

    }

}
