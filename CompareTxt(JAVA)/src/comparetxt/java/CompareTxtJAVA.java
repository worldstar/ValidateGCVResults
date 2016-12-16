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
            String NewTxtPath = "@../../File/jpgFilterList.txt";
            //CompareTxt Path
            String TxtComparejpgTotal1Path = "@../../File/jpgFilterList.txt";
            String NewTxtPath2 = "@../../File/CompareTxt1.txt";
            String NewTxtPath3 = "@../../File/ComparejpgTotal1.txt";
            //CompareTxt2 Path
            String NewTxtPath4 = "@../../File/CompareTxt2.txt";
            String NewTxtPath5 = "@../../File/ComparejpgTotal2.txt";
            String SynonymPath = "@../../File/Synonym word";

            //Create a new txt(true = no-cover && false = cover)
            FileWriter sw = new FileWriter(NewTxtPath, false);

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
            for (int i = 0; i < STxt.length / 5; i++) {
                name1[i] = STxt[name1index];
                name1index += 5;
            }
            String[] jpg = new String[(STxt.length / 5)];
            int jpgindex = 1;
            for (int i = 0; i < STxt.length / 5; i++) {
                jpg[i] = STxt[jpgindex];
                jpgindex += 5;
            }
            String[] local = new String[(STxt.length / 5)];
            int localindex = 2;
            for (int i = 0; i < STxt.length / 5; i++) {
                local[i] = STxt[localindex];
                localindex += 5;
            }
            String[] name2 = new String[(STxt.length / 5)];
            int name2index = 3;
            for (int i = 0; i < STxt.length / 5; i++) {
                name2[i] = STxt[name2index];
                name2index += 5;
            }

            //Filter jpg
            String[] Filterjpg = new String[jpg.length];
            boolean chk = false;
            int count = 0;
            for (int i = 0; i < jpg.length; i++) {
                chk = false;
                for (int j = 0; j < Filterjpg.length; j++) {
                    if (jpg[i].equals(Filterjpg[j])) {
                        chk = true;
                    }
                }
                if (chk == false) {
                    Filterjpg[count] = jpg[i];
                    sw.write(jpg[i].trim() + "\n");
                    count++;
                }
            }

            sw.close();

            System.out.println("NoRepeatJpg Finish.");
            System.out.println("------------------------------");
            System.out.println("CompareTxt Starting...");

            //Create a new txt(true = no-cover && false = cover)
            FileWriter sw2 = new FileWriter(NewTxtPath2, false);
            FileWriter sw3 = new FileWriter(NewTxtPath3, false);

            //Read TxtComparejpgTotal1Path
            FileReader fr2 = new FileReader(TxtComparejpgTotal1Path);
            BufferedReader br2 = new BufferedReader(fr2);
            String TxtAll2 = "", eachLine2 = "";
            while ((eachLine2 = br2.readLine()) != null) {
                TxtAll2 += eachLine2 + "\n";
            }

            //Split TxtAll2
            String[] SjpgTotal1 = TxtAll2.split(",|\n");

            //Count success or unsuccess
            int success = 0;
            int unsuccess = 0;

            int[] jpgsuccess = new int[SjpgTotal1.length];
            int[] jpgunsuccess = new int[SjpgTotal1.length];
            
            int jpgsuccesstotal = 0;
            int jpgunsuccesstotal = 0;

            //Compare name1 and name2
            for (int i = 0; i < name1.length; i++) {
                if (name1[i].trim().equals(name2[i].trim())) {
                    sw2.write(name1[i].trim() + ",");
                    sw2.write(jpg[i].trim() + ",");
                    sw2.write(local[i].trim() + ",");
                    sw2.write(name2[i].trim() + ",");
                    sw2.write("True");
                    sw2.write("\n");
                    sw2.write("\n");

                    //jpgsuccess
                    for (int j = 0; j < SjpgTotal1.length; j++) {
                        if (jpg[i].trim().equals(SjpgTotal1[j].trim())) {
                            jpgsuccess[j]++;
                        }
                    }

                    success += 1;
                } else {
                    sw2.write(name1[i].trim() + ",");
                    sw2.write(jpg[i].trim() + ",");
                    sw2.write(local[i].trim() + ",");
                    sw2.write(name2[i].trim() + ",");
                    sw2.write("False");
                    sw2.write("\n");
                    sw2.write("\n");

                    //jpgunsuccess
                    for (int j = 0; j < SjpgTotal1.length; j++) {
                        if (jpg[i].trim().equals(SjpgTotal1[j].trim())) {
                            jpgunsuccess[j]++;
                        }
                    }

                    unsuccess += 1;
                }
            }

            //sw3
            for (int i = 0; i < (SjpgTotal1.length - 1); i++) {
                sw3.write(SjpgTotal1[i] + ",");
                sw3.write("total:" + (jpgsuccess[i] + jpgunsuccess[i]) + ",");
                sw3.write("success total:" + (jpgsuccess[i]) + ",");
                sw3.write("unsuccess total:" + (jpgunsuccess[i]) + "\n");
                sw3.write("\n");
                jpgsuccesstotal += jpgsuccess[i];
                jpgunsuccesstotal += jpgunsuccess[i];
            }
            sw3.write("total:" + (jpgsuccesstotal + jpgunsuccesstotal) + ",");
            sw3.write("success total:" + (jpgsuccesstotal) + ",");
            sw3.write("unsuccess total:" + (jpgunsuccesstotal) + "\n");

            //sw2
            sw2.write("total:" + (success + unsuccess) + "\n");
            sw2.write("success total:" + (success) + "\n");
            sw2.write("unsuccess total:" + (unsuccess) + "\n");

            sw2.close();
            sw3.close();
            System.out.println("total" + (success + unsuccess));
            System.out.println("success total:" + (success));
            System.out.println("unsuccess total:" + (unsuccess));

            System.out.println("CompareTxt Finish.");
            System.out.println("------------------------------");
            System.out.println("CompareTxt2 Starting...");

            //Create a new txt(true = no-cover && false = cover)
            FileWriter sw4 = new FileWriter(NewTxtPath4, false);
            FileWriter sw5 = new FileWriter(NewTxtPath5, false);

            //Read TxtComparejpgTotal1Path
            FileReader fr3 = new FileReader(TxtComparejpgTotal1Path);
            BufferedReader br3 = new BufferedReader(fr3);

            String TxtAll3 = "", eachLine3 = "";
            while ((eachLine3 = br3.readLine()) != null) {
                TxtAll3 += eachLine3 + "\n";
            }
            
            //Split TxtAll3 
            String[] SjpgTotal2 = TxtAll3.split(",|\n");

            //Count success2 or unsuccess2
            int success2 = 0;
            int unsuccess2 = 0;

            int[] jpgsuccess2 = new int[SjpgTotal2.length];
            int[] jpgunsuccess2 = new int[SjpgTotal2.length];

            //Read file of the Synonym 
            java.io.File[] SynonymWordCollection = new java.io.File(SynonymPath).listFiles();

            //Compare name1 and name2 of the Synonym 
            for (int i = 0; i < name1.length; i++) {
                if (name1[i].trim().equals(name2[i].trim())) {
                    sw4.write(name1[i].trim() + ",");
                    sw4.write(jpg[i].trim() + ",");
                    sw4.write(local[i].trim() + ",");
                    sw4.write(name2[i].trim() + ",");
                    sw4.write("True");
                    sw4.write("\n");
                    sw4.write("\n");

                    //jpg success
                    for (int j = 0; j < SjpgTotal2.length; j++) {
                        if (jpg[i].trim().equals(SjpgTotal2[j].trim())) {
                            jpgsuccess2[j]++;
                        }
                    }

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
                                    sw4.write(name1[i].trim() + ",");
                                    sw4.write(jpg[i].trim() + ",");
                                    sw4.write(local[i].trim() + ",");
                                    sw4.write(name2[i].trim() + ",");
                                    sw4.write("True");
                                    sw4.write("\n");
                                    sw4.write("\n");

                                    //jpg success
                                    for (int l = 0; l < SjpgTotal2.length; l++) {
                                        if (jpg[i].trim().equals(SjpgTotal2[l].trim())) {
                                            jpgsuccess2[l]++;
                                        }
                                    }

                                    success2 += 1;
                                    insuccess = true;
                                }
                            }
                            if (insuccess.equals(false)) {
                                sw4.write(name1[i].trim() + ",");
                                sw4.write(jpg[i].trim() + ",");
                                sw4.write(local[i].trim() + ",");
                                sw4.write(name2[i].trim() + ",");
                                sw4.write("False");
                                sw4.write("\n");
                                sw4.write("\n");

                                //jpg unsuccess
                                for (int l = 0; l < SjpgTotal2.length; l++) {
                                    if (jpg[i].trim().equals(SjpgTotal2[l].trim())) {
                                        jpgunsuccess2[l]++;
                                    }
                                }

                                unsuccess2 += 1;
                            }
                        }
                    }
                }
            }

            //Count success2 or unsuccess2
            int jpgsuccesstotal2 = 0;
            int jpgunsuccesstotal2 = 0;

            for (int i = 0; i < (SjpgTotal2.length - 1); i++) {
                sw5.write(SjpgTotal2[i] + ",");
                sw5.write("total:" + (jpgsuccess2[i] + jpgunsuccess2[i]) + ",");
                sw5.write("success total:" + (jpgsuccess2[i]) + ",");
                sw5.write("unsuccess total:" + (jpgunsuccess2[i] + "\n"));
                sw5.write("\n");
                jpgsuccesstotal2 += jpgsuccess2[i];
                jpgunsuccesstotal2 += jpgunsuccess2[i];
            }
            sw5.write("total:" + (jpgsuccesstotal2 + jpgunsuccesstotal2) + ",");
            sw5.write("success total:" + (jpgsuccesstotal2) + ",");
            sw5.write("unsuccess total:" + (jpgunsuccesstotal2) + "\n");

            //success2 or unsuccess2
            sw4.write("total:" + (success2 + unsuccess2) + "\n");
            sw4.write("success total:" + (success2) + "\n");
            sw4.write("unsuccess total:" + (unsuccess2) + "\n");

            sw4.close();
            sw5.close();
            System.out.println("total:" + (success2 + unsuccess2));
            System.out.println("success total:" + (success2));
            System.out.println("unsuccess total:" + (unsuccess2));

            System.out.println("CompareTxt2 Finish.");
            System.out.println("------------------------------");
        }
    }
}
