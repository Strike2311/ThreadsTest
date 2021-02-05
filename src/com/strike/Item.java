package com.strike;

public class Item {
    //te zmienne statyczne żeby oba wątki miały dostę do tych samcyh danych
    //bez static wątek A miałby dostęp do tworzonych klas, a wątek B do pustych klas bo sam nie podaje im danych
    public static String ID = "";
    public static int weight = 0;
    public static boolean nextItem = false; //ta zmienna posłóży nam za diode sygnalizacyjną

    //funkcja 'tworząca' instancje, tak naprawde dla jednej instancji podmienia wartości przez co można uznać że to nowa instancja
    public synchronized void createInstance(String ID, int weight){
        //jeśli dioda jest zapaona to nie robimy nowej instancji
        while(nextItem == true){
            try{
                wait();
            }catch(InterruptedException e){

            }
        }
        //jeśli dioda zgaśnie to znowu ją zapalamy i ustawiamy wartości klasy na kolejny wiersz z pliku, dane z pliku przekazujemy w argumencie a pobieramy je z pliku w AThread
        nextItem = true;
        this.ID = ID;
        this.weight = weight;
        //powiadamiamy wątki że operacja wykonana
        notifyAll();
    }

    //funkcja dla wątku B, która podaje mu wage aktualnej instancji, funkcja zwraca int
    public synchronized int getInstanceWeight(){
        //sprawdzamy czy dioda jest zgaszona, dodatkowy warunek to sprawdzenie czy wątek A się już skończył, wtedy wypisujemy wartość sumy końcowej
        //znaki && oznaczają, że while działa dopóki oba warunki są prawdą, jeśli jeden z nich nie jest prawdziwy, czyli jeśli dioda jest włączona, albo jeśli wątek A przestał działać
       while(nextItem == false && AThread.isDead == false){
           try{
               wait();
           }catch(InterruptedException e){

           }
       }
       //skoro dioda się świeciła to wyłączamy ją, powiadamiamy o tym inne wątki i zwracamy wartość wagi aktualnego towaru
        nextItem = false;
        notifyAll();
        return weight;
    }


}