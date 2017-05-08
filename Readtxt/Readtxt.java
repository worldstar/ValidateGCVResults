/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openga.applications.data;
import java.io.*;
/**
 *
 * @author Guo Yu-Cheng
 */
public class Readtxt {
  private String fileName;
  private String[] Number;  
  private int tempcompletionTime = 0;
  private int[] completionTime;
  private int[] Sequence;
  private int[] processingTime;
  private int[] releaseDate;
  private int[] dueDate;
  private int[] deadline;
  private double[] revenue;
  
  public void setcompletionTime(int[] completionTime) {
        this.completionTime = completionTime;
    }

    public void setSequence(int[] Sequence) {
        this.Sequence = Sequence;
    }

    public void setProcessingTime(int[] processingTime) {
        this.processingTime = processingTime;
    }

    public void setReleaseDate(int[] releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setDueDate(int[] setdueDate) {
        this.dueDate = setdueDate;
    }

    public void setDeadline(int[] deadline) {
        this.deadline = deadline;
    }

    public void setRevenue(double[] revenue) {
        this.revenue = revenue;
    }
    
    public void setTxtData(String fileName)
    {
      this.fileName = fileName;
    }
    
    private void ReadTxt() throws IOException{
        {
            //Read Txt
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String TxtAll = "", eachLine = "";
            while ((eachLine = br.readLine()) != null) {
                TxtAll += eachLine + "\n";
            }                     
            String[] STxt = TxtAll.split("\t|\n");
            
            //Set Size
            int Numberindex = 4,due_dayindex = 5,processingTimeindex = 7;
            int STxtlength = (STxt.length / 4)-1;
            Number = new String[STxtlength];
            processingTime = new int[STxtlength];
            dueDate = new int[STxtlength];
            
            //save value of Txt
            for (int i = 0; i < STxtlength; i++) {
                Number[i] = STxt[Numberindex];
                Numberindex += 4;
                dueDate[i] = Integer.parseInt(STxt[due_dayindex]);
                due_dayindex += 4;
                processingTime[i] = Integer.parseInt(STxt[processingTimeindex]);
                processingTimeindex += 4;
            };
    }
}
    
    public void output()
    {
      for(int i = 0 ; i < Number.length;i++)
      {
        System.out.println("Number : " + Number[i] + "\t");
        System.out.println("processing time : " + processingTime[i] + "\t");
        System.out.println("due-date : " + dueDate[i] + "\t");
      }
    }
    
    public static void main(String[] args) throws IOException{
      {
        Readtxt RT = new Readtxt();
        RT.setTxtData(".\\instances\\parallelMachine.txt");
        RT.ReadTxt();
        RT.output();
      }
    }
}
