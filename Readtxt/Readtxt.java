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
    private String[] STxt;
    private final int NumberOfColumns = 4; 
    private static int TxtLength ; //Read TotalLength of the Txt 
    private int ReadTxtSize; //Current Length of the Txt
    private int[] completionTime;
    private int[] Sequence;
    private int[] processingTime;
    private int[] releaseDate;
    private int[] dueDate;
    private int[] deadline;
    private double[] revenue;
  
    public void setTxtLength(int TxtLength) {
        this.TxtLength = TxtLength;
    }
    
    public void setReadTxtSize(int ReadTxtSize) {
        this.ReadTxtSize = ReadTxtSize;
    }
  
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

    public void setDueDate(int[] dueDate) {
        this.dueDate = dueDate;
    }

    public void setDeadline(int[] deadline) {
        this.deadline = deadline;
    }

    public void setRevenue(double[] revenue) {
        this.revenue = revenue;
    }
    
    public void setReadTxtData(String fileName)
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
            STxt = TxtAll.split("\t|\n");
            
            //Total TxtLength 
            setTxtLength((STxt.length / NumberOfColumns)-1);
        }
    }
    
    private void SaveValueOfTxt(int TxtSize) {
        if(TxtSize > 0 && TxtSize <= TxtLength)
        {
          //Set Size
          int Numberindex = 4,due_dayindex = 5,processingTimeindex = 7;
          Number = new String[TxtSize];
          processingTime = new int[TxtSize];
          dueDate = new int[TxtSize];
          setReadTxtSize(TxtSize);

          //save value of Txt
          for (int i = 0; i < TxtSize; i++) {
              Number[i] = STxt[Numberindex];
              Numberindex += 4;
              dueDate[i] = Integer.parseInt(STxt[due_dayindex]);
              due_dayindex += 4;
              processingTime[i] = Integer.parseInt(STxt[processingTimeindex]);
              processingTimeindex += 4;
          }
        }else
        {
          System.out.print("Current Length is too long or too short.");
        }
    }
    
    public void output(){
      if(ReadTxtSize > 0)
      {
        for(int i = 0 ; i < Number.length;i++)
        {
          System.out.println("Number : " + Number[i] + "\t");
          System.out.println("processing time : " + processingTime[i] + "\t");
          System.out.println("due-date : " + dueDate[i] + "\t");
        }
      }
    }
    
    public static void main(String[] args) throws IOException{
      {
        Readtxt RT = new Readtxt();
        RT.setReadTxtData(".\\instances\\parallelMachine.txt");
        RT.ReadTxt();
        RT.SaveValueOfTxt(TxtLength);
        RT.output();
      }
    }
    
    public int getReadTxtSize() {
        return ReadTxtSize;
    }
    
    public int getTxtLength() {
        return TxtLength;
    }
    
    public int[] getcompletionTime() {
        return completionTime;
    }

    public int[] getSequence() {
        return Sequence;
    }

    public int[] getProcessingTime() {
        return processingTime;
    }

    public int[] getReleaseDate() {
        return releaseDate;
    }

    public int[] getDueDate() {
        return dueDate;
    }

    public int[] getDeadline() {
        return deadline;
    }

    public double[] getRevenue() {
        return revenue;
    }
    
    public String getTxtData()
    {
      return fileName;
    }
}
