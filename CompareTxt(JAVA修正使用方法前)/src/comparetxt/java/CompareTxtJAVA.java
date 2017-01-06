/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comparetxt.java;

import java.io.FileWriter;//write the Txt
import java.io.FileReader;//Read the Txt
import java.io.BufferedReader;//Read Temporary
import java.io.IOException;//Detection
import java.util.*;
import java.io.*;

/**
 *
 * @author Guo Yu-Cheng
 */
public class CompareTxtJAVA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        {
            // TODO code application logic here
            CompareTxtJAVA CT = new CompareTxtJAVA();
            System.out.println("CompareTxtJAVA Starting...");

            CT.CompareTxt();

            System.out.println("CompareTxtJAVA Finish.");
        }
    }
    
    private void CompareTxt() throws IOException {
        {
            System.out.println("NoRepeatJpg Starting...");

            //NoRepeatJpg Path
            String TxtCaltechPath = "@../../File/Caltech101Results.txt";
            //CompareTxt Path
            String NewTxtPath2 = "@../../File/CompareTxt1.txt";
            //CompareTxt2 Path
            String NewTxtPath4 = "@../../File/CompareTxt2.txt";
            String SynonymPath = "@../../File/Synonym word";
            
            //Read Caltech101Results
            FileReader fr = new FileReader(TxtCaltechPath);
            BufferedReader br = new BufferedReader(fr);
            String TxtAll = "", eachLine = "";
            while ((eachLine = br.readLine()) != null) {
                TxtAll += eachLine + "\n";
            }
            
            //Split TxtAll 
            String[] STxt = TxtAll.split(",|\n");
            
            //The STxt are saved to different arrays.
            String[] name1 = new String[(STxt.length / 5)];
            int name1index = 0;
            String[] jpg = new String[(STxt.length / 5)];
            int jpgindex = 1;
            String[] local = new String[(STxt.length / 5)];
            int localindex = 2;
            String[] name2 = new String[(STxt.length / 5)];
            int name2index = 3;
            for (int i = 0; i < STxt.length / 5; i++) {
                name1[i] = STxt[name1index];
                name1index += 5;
                jpg[i] = STxt[jpgindex];
                jpgindex += 5;
                local[i] = STxt[localindex];
                localindex += 5;
                name2[i] = STxt[name2index];
                name2index += 5;
            }

            System.out.println("NoRepeatJpg Finish.");
            System.out.println("------------------------------");
            System.out.println("CompareTxt Starting...");

            //Create a new txt(true = no-cover && false = cover)
            FileWriter sw2 = new FileWriter(NewTxtPath2, false);

            //Count success or unsuccess
            int success = 0;
            int unsuccess = 0;

            //Compare name1 and name2
            for (int i = 0; i < name1.length; i++) {
                if (name1[i].trim().equals(name2[i].trim())) {
                    sw2.write(name1[i].trim() + "," + jpg[i].trim() + "," + local[i].trim() + ","
                             + name2[i].trim() + "," + "True" + "\n" + "\n");

                    success += 1;
                } else {
                    sw2.write(name1[i].trim() + "," + jpg[i].trim() + "," + local[i].trim() + ","
                             + name2[i].trim() + "," + "False" + "\n" + "\n");

                    unsuccess += 1;
                }
            }
            //sw2
            sw2.write("total:" + (success + unsuccess) + "\n"
                     + "success total:" + (success) + "\n"
                     + "unsuccess total:" + (unsuccess) + "\n");

            sw2.close();
            System.out.println("total" + (success + unsuccess));
            System.out.println("success total:" + (success));
            System.out.println("unsuccess total:" + (unsuccess));

            System.out.println("CompareTxt Finish.");
            System.out.println("------------------------------");
            System.out.println("CompareTxt2 Starting...");

            //Create a new txt(true = no-cover && false = cover)
            FileWriter sw4 = new FileWriter(NewTxtPath4, false);

            //Count success2 or unsuccess2
            int success2 = 0;
            int unsuccess2 = 0;
            
            //Read file of the Synonym 
            java.io.File[] SynonymWordCollection = new java.io.File(SynonymPath).listFiles();

            //Compare name1 and name2 of the Synonym 
            for (int i = 0; i < name1.length; i++) {
                if (name1[i].trim().equals(name2[i].trim())) {
                    sw4.write(name1[i].trim() + "," + jpg[i].trim() + "," + local[i].trim() + ","
                             + name2[i].trim() + "," + "True" + "\n" + "\n");

                    success2 += 1;
                } else {
                    for (int j = 0; j < SynonymWordCollection.length; j++) {
                        Boolean insuccess = false;
                        insuccess = false;
                        if ((name1[i].trim() + ".txt").equals(SynonymWordCollection[j].getName())) {

                            //Read (SynonymPath + SynonymWordCollection[j].getName())
                            FileReader fr4 = new FileReader(SynonymPath +"/" +  SynonymWordCollection[j].getName());
                            BufferedReader br4 = new BufferedReader(fr4);
                            String TxtAll4 = "", eachLine4 = "";
                            while ((eachLine4 = br4.readLine()) != null) {
                                TxtAll4 += eachLine4 + "\n";
                            }
                            //Split TxtAll4
                            String[] SSynonymWordTxt = TxtAll4.split("\n");

                            for (int SSWTcount = 0; SSWTcount < SSynonymWordTxt.length; SSWTcount++) {
                                if (name2[i].trim().equals(SSynonymWordTxt[SSWTcount].trim())) {
                                    sw4.write(name1[i].trim() + "," + jpg[i].trim() + ","
                                             + local[i].trim() + "," + name2[i].trim() + ","
                                             + "True" + "\n" + "\n");

                                    success2 += 1;
                                    insuccess = true;
                                }
                            }
                            if (insuccess.equals(false)) {
                                sw4.write(name1[i].trim() + "," + jpg[i].trim() + ","
                                         + local[i].trim() + "," + name2[i].trim() + ","
                                         + "False" + "\n" + "\n");

                                unsuccess2 += 1;
                            }
                        }
                    }
                }
            }
            
            //success2 or unsuccess2
            sw4.write("total:" + (success2 + unsuccess2) + "\n"
                     + "success total:" + (success2) + "\n"
                     + "unsuccess total:" + (unsuccess2) + "\n");

            sw4.close();
            System.out.println("total:" + (success2 + unsuccess2));
            System.out.println("success total:" + (success2));
            System.out.println("unsuccess total:" + (unsuccess2));

            System.out.println("CompareTxt2 Finish.");
            System.out.println("------------------------------");
        }
    }
}
