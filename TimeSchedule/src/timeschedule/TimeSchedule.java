/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timeschedule;

import java.util.*;

/**
 *
 * @author Guo Yu-Cheng
 */
public class TimeSchedule {

    private int completionTime = 0;
    private ArrayList<Integer> Sequence;
    private ArrayList<Integer> processingTime;
    private ArrayList<Integer> releaseDate;

//  private double [] r;       //  release date.
//  private double [] p;       //  processing time
//  private double [] d;       //  due-date
//  private double [] d_bar;   //  deadline
//  private double [] e;       //  revenue
//  private double [] w;       //  weight
//  private double [][] s;     //  setup times
//  private int size;          //  instance lengh
    /**
     * @param args the command line arguments
     */
    public void setSequence(ArrayList<Integer> Sequence) {
        this.Sequence = Sequence;
    }

    public void setProcessingTime(ArrayList<Integer> processingTime) {
        this.processingTime = processingTime;
    }

    public void setReleaseDate(ArrayList<Integer> releaseDate) {
        this.releaseDate = releaseDate;
    }

    public static void main(String[] args) {
        //create Sequence,processingTime,ri
        TimeSchedule ts = new TimeSchedule();
        ts.setSequence(new ArrayList(Arrays.asList(1, 2, 3, 4, 5)));
        ts.setReleaseDate(new ArrayList(Arrays.asList(1, 3, 7, 5, 9))); //Goods arrived Date.
        ts.setProcessingTime(new ArrayList(Arrays.asList(2, 4, 8, 6, 10)));

        //result
        ts.Sorting();
    }

    private int Sorting() {

        //print startSequence
        System.out.print("Sequence : " + "\t");
        for (int i = 0; i < Sequence.size(); i++) {
            System.out.print(Sequence.get(i) + "\t");
        }
        System.out.println();

        //Processing the completion time
        System.out.print("Completion: " + "\t");

        for (int i = 0; i < Sequence.size(); i++) {
            int index = Sequence.get(i) - 1;
            if (completionTime > releaseDate.get(index)) 
            {
                completionTime += processingTime.get(index);
            } 
            else 
            {
                completionTime += processingTime.get(index) + (releaseDate.get(index) - completionTime);
            }

            System.out.print(completionTime + "\t");
        }

        return completionTime;
    }

}
