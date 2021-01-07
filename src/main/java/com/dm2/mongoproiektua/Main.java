/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dm2.mongoproiektua;

import java.util.*;

/**
 *
 * @author Joseba
 */
public class Main {

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        
        String erabakia;
        int aukera = 453;
        int id;
        while (aukera != 0) {
            System.out.println("+-------------------------------------------------------------------+");
            System.out.println("|                   Ikasleen datuak                                 |");
            System.out.println("+-------------------------------------------------------------------+");
            System.out.println("|1.-Ikasle guztien datuak                                            |");
            System.out.println("|2.-Ikasle baten datuak                                              |");
            System.out.println("|3.-Ikasle berri bat sartu                                           |");
            System.out.println("|4.-Ikasle bat eguneratu                                             |");
            System.out.println("|5.-Ikasle bat ezabatu                                               |");
            System.out.println("|6.-Ikasle gaindituak(atal bat gaindituta)                           |");
            System.out.println("|7.-Ikasleak alfabetikoki                                            |");
            System.out.println("|8.-Notak ordenatu                                                  |");
            System.out.println("|0.-Irten                                                           |");
            System.out.println("+-------------------------------------------------------------------+");
            System.out.println("Sartu zenbaki bat:");

            try {
                aukera = in.nextInt();
                switch (aukera) {
                    case 0:
                        System.out.println("Agur!");
                        break;
                    case 1:
                        Model.bistaratuGuztiak();
                        break;
                    case 2:

                        System.out.println("Sartu ikaslearen id-a: ");
                        id = in.nextInt();
                        Model.ikasleBakarra(id);
                        break;
                    case 3:
                        double an,
                         qn,
                         hn;
                        String izena;

                        System.out.println("Ikasle berri bat sortzen...");
                        System.out.println("Sartu ikaslearen izena: ");
                        izena = in.next();
                        System.out.println("Sartu ikaslearen azterketako nota: ");
                        an = in.nextDouble();
                        System.out.println("Sartu ikaslearen quizeko nota: ");
                        qn = in.nextDouble();
                        System.out.println("Sartu ikaslearen etxekolanen nota: ");
                        hn = in.nextDouble();

                        Model.ikasleBerria(izena, an, qn, hn);
                        break;
                    case 4:
                        double nota;
                        System.out.println("Sartu ikaslearen id-a: ");
                        id = in.nextInt();
                        System.out.println("Azterketa/Quiz/EtxekoLanak ?: ");
                        erabakia = in.next();
                        erabakia.toLowerCase();
                        System.out.println("Sartu nota berria: ");
                        nota = in.nextDouble();
                        Model.notakAldatu(id,erabakia,nota);
                        break;
                    case 5:
                        System.out.println("Sartu ikaslearen id-a: ");
                        id = in.nextInt();
                        Model.deleteOne(id);
                        break;
                    case 6:
                        //Atal bat gaindituta dutenak, bestea ez dut lortu
                        Model.gaindituak();
                        break;
                    case 7:
                        //Ikasleak alfabetikoki ordenatzen ditu
                        Model.ikasleakAlfabetikoki();
                        break;
                    case 8:
                        //Notarik altuenak dituzten ikasleak bistaratzen ditu.
                        Model.notakOrdenatu();
                        break;
                    default:
                        System.out.println("Aukera hori ez dago erabilgarri...");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Sartu zenbaki bat mesedez...");
                in.nextLine();
            }
        }
    }
}
