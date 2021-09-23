/* ALPHA Test HiMCM Summer Job Selection 11.14.20*/


import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

  //Data Fields
  private String name = "";
  private int age = 0;
  private double prefHourlyWage = 0;
  private int minHrsWeekly = 0;
  private int maxHrsWeekly = 0;
  private int manual = 0;
  private ArrayList<Integer> optSkillList;
  private ArrayList<Integer> reqSkillList;
  private double distance = 0;

  // Constructor for user's inputed key.
  public Main(String name, int age, double prefHourlyWage, int minHrsWeekly, int maxHrsWeekly, int manual, ArrayList<Integer> optSkillList, ArrayList<Integer> reqSkillList, double distance) {
    this.name = name;
    this.age = age;
    this.prefHourlyWage = prefHourlyWage;
    this.minHrsWeekly = minHrsWeekly;
    this.maxHrsWeekly = maxHrsWeekly;
    this.manual = manual;
    this.optSkillList = optSkillList;
    this.reqSkillList = reqSkillList;
    this.distance = distance;
  }

  public String toString() {
    return this.name;
  }
  
  //Calculates Affinity points of a job for a particular student
  public double getAffinityPoints(Main job) {
    double points = 0;
    
    if (this.age < job.age) {
      return 0;
    }

    // Weighted default value for wage is 5 points.      
    points += 5 * (job.prefHourlyWage/this.prefHourlyWage);
    

    if(this.minHrsWeekly >= job.minHrsWeekly) {
      points += 5;
    }

    if(this.maxHrsWeekly <= job.maxHrsWeekly) {
      points += 5;
    }
    
    if(this.manual != job.manual && this.manual != 3) {
      return 0;
    }
    
    //Bonus affinity points attributed for each suggested skill that the person has
    for (int i = 0; i < job.optSkillList.size(); i++) {
      for (int j = 0; j < this.optSkillList.size(); j++) {
        if (job.optSkillList.get(i) == this.optSkillList.get(j))
          points += 2.5;  
      }
    }

    //If a user doesn't have one of the required skills for the job, affinity is set to 0 and will eventually be removed from the list
    int counter = 0;
    for (int i = 0; i < job.reqSkillList.size(); i++) {
      for (int j = 0; j < this.optSkillList.size(); j++) {
        if (job.reqSkillList.get(i) == this.optSkillList.get(j)) {
          points += 2.5;
          counter++; 
        }
      }
    }
    
    if (counter != job.reqSkillList.size()) {
      return 0;
    }
    
    // Calculations based on distance.
    if (this.distance >= job.distance) {
      points += 3.5 * (this.distance/job.distance);
    }else
      return 0;
    
    return points;
  }
  
  //Sorts ArrayList of jobs from greatest to least affinity points
  public static void sortList(Main student1, ArrayList<Main> jobList) {
    
    //Removes jobs with affinity 0;
    for (int i = 0; i < jobList.size(); i++){
      if (student1.getAffinityPoints(jobList.get(i)) == 0) {
        jobList.remove(i);
        i--;
      }
    }
    
    for (int i = 0; i < jobList.size() - 1; i++) {
      for (int j = i + 1; j < jobList.size(); j++) {
        if(student1.getAffinityPoints(jobList.get(i)) <= student1.getAffinityPoints(jobList.get(j))) {
          jobList.add(i,jobList.get(j));
          jobList.remove(j + 1);
        }   
      }
    }
  }

  public static void main(String[] args) {

    /*
    Scanner input = new Scanner(System.in);
    
    System.out.print("Enter your name: ");
    String name = input.nextLine();
    System.out.println();

    System.out.print("Input your age: ");
    int age = input.nextInt();
    System.out.println();

    System.out.print("Input your desired minimum hourly wage: ");
    double prefHourlyWage = input.nextDouble();
    System.out.println();

    System.out.print("Input the minimum amount of hours you are willing to work per week: ");
    int minHrsWeekly = input.nextInt();
    System.out.println();

    System.out.print("Input the maximum amount of hours you are willing to work per week: ");
    int maxHrsWeekly = input.nextInt();
    System.out.println();

    //Blue-collar labor refers to manual labor and white-collar labor refers to non-manual labor
    System.out.print("Enter the number that applies best to your preference: \n [1] Blue-collar labor\n [2] White-collar labor\n [3] Fine with either\n");
    int manual = input.nextInt();
    System.out.println();

    //Skills list
    System.out.print("Enter all the numbers that apply to your skills, input 0 once done: \n [1] Mathematics proficiency\n [2] Science proficiency\n [3] Language Arts proficiency\n [4] Multilingual\n [5] Artistic knowledge\n [6] Hospitality\n [7] Communication skills\n [8] Leadership\n [9] Swimming proficiency\n [10] Cooking proficiency\n [11] CPR certified\n [12] First Aid certified\n [13] Technology proficiency\n");
    
    int skills = -1;
    ArrayList<Integer> optSkillList = new ArrayList<Integer>();
    
    while(skills != 0) {
        skills = input.nextInt();
        optSkillList.add(skills);
    }
    System.out.println();
    
    System.out.print("Enter the maximum distance you are willing to travel to work in (miles): ");
    
    double distance = input.nextDouble();
    System.out.println();

    ArrayList<Integer> reqSkillList = new ArrayList<Integer>();

    Main student1 = new Main(name, age, prefHourlyWage, minHrsWeekly, maxHrsWeekly, manual, optSkillList, reqSkillList, distance);
    */

    //Test Student initialized
    Main student1 = new Main("Omar", 17, 8.50, 20, 40, 3, new ArrayList<Integer>(Arrays.asList(1,2,4,0)), new ArrayList<Integer>(Arrays.asList()), 15);

    // Initializes the jobs to be searched through (Test Cases)
    Main mathTutor = new Main("Kumon",16, 8.56, 20, 40, 2, new ArrayList<Integer>(Arrays.asList(4,6,7,8)), new ArrayList<Integer>(Arrays.asList(1)),10.0);

    Main lifeguard = new Main("Hallandale Beach", 16, 10, 40, 56, 1, new ArrayList<Integer>(Arrays.asList(4,7)), new ArrayList<Integer>(Arrays.asList(9,11)), 26.0);
    
    Main restaurantCashier = new Main("Chipotle",16, 9.25, 30, 40, 1, new ArrayList<Integer>(Arrays.asList(1,4,6,7)), new ArrayList<Integer>(Arrays.asList(0)), 13.0);
    
    Main salesAssociate = new Main("Aeropostale",16, 9, 30, 40, 1, new ArrayList<Integer>(Arrays.asList(4,6,8)), new ArrayList<Integer>(Arrays.asList(7)), 16.0);
    
    Main childcare = new Main("YMCA", 18, 12.25, 20, 40, 1, new ArrayList<Integer>(Arrays.asList(6,7,8)), new ArrayList<Integer>(Arrays.asList(11,12)), 7.0);

    Main libraryClerk = new Main("Broward County Public Library", 16, 10.0, 10, 20, 2, new ArrayList<Integer>(Arrays.asList(4,6)), new ArrayList<Integer>(Arrays.asList(7,13)), 9.0);

    Main cook = new Main("McDonald's", 16, 8.55, 20, 40, 1, new ArrayList<Integer>(Arrays.asList(4,7,10)), new ArrayList<Integer>(Arrays.asList()), 5.0);

    Main artGuide = new Main("Miami Art Museum", 18, 11.75, 15, 40, 1, new ArrayList<Integer>(Arrays.asList(4,6)), new ArrayList<Integer>(Arrays.asList(5,7)), 21.0);

    Main stocker = new Main("Target", 16, 8.55, 20, 40, 1, new ArrayList<Integer>(Arrays.asList(4,7)), new ArrayList<Integer>(Arrays.asList()), 8.0);

    Main server = new Main("Chili's", 18, 9, 30, 40, 1, new ArrayList<Integer>(Arrays.asList(4,6)), new ArrayList<Integer>(Arrays.asList(7)), 14.0);

    Main ticketClerk = new Main("Cinemark", 16, 8.25, 15, 40, 2, new ArrayList<Integer>(Arrays.asList(4,6)), new ArrayList<Integer>(Arrays.asList(7,13)), 24.0);

    ArrayList<Main> jobList = new ArrayList<Main>(Arrays.asList(mathTutor,lifeguard,restaurantCashier,salesAssociate, childcare, libraryClerk, cook, artGuide, stocker, server, ticketClerk));
    
    // Calculates affinity points for each job in same order initialized in (For testing purposes) - Will be hidden in final product
    System.out.println();

    /* (For Testing Purposes)
    System.out.println(student1.getAffinityPoints(mathTutor));
    System.out.println(student1.getAffinityPoints(lifeguard));
    System.out.println(student1.getAffinityPoints(restaurantCashier));
    System.out.println(student1.getAffinityPoints(salesAssociate));
    System.out.println(student1.getAffinityPoints(childcare));
    System.out.println(student1.getAffinityPoints(libraryClerk));
    System.out.println(student1.getAffinityPoints(cook));
    System.out.println(student1.getAffinityPoints(artGuide));
    System.out.println(student1.getAffinityPoints(stocker));
    System.out.println(student1.getAffinityPoints(server));
    System.out.println(student1.getAffinityPoints(ticketClerk));
    */

    System.out.println();
    sortList(student1, jobList);
    
    // Prints test cases in ordered from greatest to least affinity points (assuming they weren't removed)
    System.out.println(jobList);
  }
}