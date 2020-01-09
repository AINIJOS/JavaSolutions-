import java.io.*;

import java.util.Scanner;

import java.util.Random;

import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;




public class PizzaOrder
{
	public static void main(String [] args){		
		Scanner keyboard = new Scanner(System.in);	
		boolean discount = false;     				
		char choice;                  		
		String input;                 				   
		String[] orders = new String[10];	
		int numOfOrders = 0;				

		
		System.out.println("====================================");
		System.out.println("Welcome to \"Eat&Chat\" Pizza Order!");
		System.out.println("====================================");
		
		System.out.print("Today is: ");
		printCurrentDate();	
		System.out.println();
		System.out.print("> Is it your BIRTHDAY? (10% discount available on presenting ID)  (Y/N):  ");
		input = keyboard.nextLine();


      	char bd=input.charAt(0);	
      	if (bd == 'Y' || bd=='y'){
      		discount = true;
      	}

		orders[numOfOrders++] = orderPizza();	
		previewOrder(orders);	


		
		boolean rep = true;
		while (rep){



			printMenu();	

			input = keyboard.nextLine();
			choice = input.charAt(0);

			switch(choice){

				
				case '1': 
					confirmOrder(orders, discount);			
					break;

				
				case '2':					
					orders[numOfOrders++] = orderPizza();	
					previewOrder(orders);					
					break;

			
				case '3': 
					System.out.println("Good bye!");
					System.exit(0);							

				default: 
					System.exit(0);
			
			}
		}
	}

	public static void printMenu(){		
		System.out.println("------------MENU-------------");
		System.out.println("1 - Complete");
		System.out.println("2 - Add another order");
		System.out.println("3 - Exit"); 
		System.out.print("> Choose one of the above (Enter a number): ");
	}

	
	public static String orderPizza(){ 
		Scanner keyboard = new Scanner(System.in);
		String input;                 	
		char choice;                  	
		int size;                   	 
		int cost = 0;          			
		int numberOfToppings = 0;     	
		String toppings = "Cheese";  	
		String result = "";				
		final int toppingCost = 200;	

		
		System.out.println("-----------------------------");
		System.out.println("Pizza Size (cm)      Cost");
		System.out.println("       20            1000 T");
		System.out.println("       30            1500 T");
		System.out.println("       40            2000 T");
		System.out.println("What size pizza would you like?"); 
		System.out.print("> 20, 30, or 40 (enter the number only): ");
		size = keyboard.nextInt();


		if (size==20){
			cost = 1000;
		}
		else if (size==30){
			cost = 1500;
			
		}
		else if (size==40) {
			cost=2000;
			
		}
		    
		
		keyboard.nextLine(); 
		                
	
		System.out.println("-----------------------------");              
		System.out.println("All pizzas come with cheese."); 
		System.out.println("Additional toppings are 200T each," + " choose from:");
		System.out.println("- Meat, Sausage, Vegetables, Mushroom");

		System.out.print("> Do you want Meat?  (Y/N):  ");
		input = keyboard.nextLine();
		choice = input.charAt(0);
		if (choice == 'Y' || choice == 'y')
		{
			numberOfToppings += 1;
			toppings = toppings + " + Meat";
		}
		System.out.print("> Do you want Sausage?  (Y/N):  ");
		input = keyboard.nextLine();
		choice = input.charAt(0);
		if (choice == 'Y' || choice == 'y')
		{
			numberOfToppings += 1;
			toppings = toppings + " + Sausage";
		}
		System.out.print("> Do you want Vegetables?  (Y/N):  ");
		input = keyboard.nextLine();
		choice = input.charAt(0);
		if (choice == 'Y' || choice == 'y')
		{
			numberOfToppings += 1;
			toppings = toppings + " + Vegetables";
		}
		System.out.print("> Do you want Mushroom?  (Y/N):  ");
		input = keyboard.nextLine();
		choice = input.charAt(0);
		if (choice == 'Y' || choice == 'y')
		{
			numberOfToppings += 1;
			toppings = toppings + " + Mushroom";
		}


		cost+=(numberOfToppings*toppingCost);

		
		result += size + "cm pizza, " + toppings;
	
		result += ":" + cost;
		return result;
   	}

	
	public static void previewOrder(String[] orders){
		System.out.println("-----------------------------");
		System.out.println("Your order: ");


		for (int i=0;i<orders.length;i++){
			String num=String.valueOf(i+1);
            if (orders[i] != null){
                System.out.println(num + ") " + orders[i]);
            }
        }
		


	
		System.out.println("Total: " + getTotalCost(orders) + " T");
	}


	public static int getTotalCost(String[] orders){
		int price = 0;
        for (int i=0;i<orders.length;i++){
            if (orders[i] != null){
                price += Integer.parseInt(orders[i].split(":")[1]);
            }
        }
			
		return price;
	
}
	
	public static void confirmOrder(String[] orders, boolean discount){
		final int DISCOUNT_AMOUNT = 10;	

		
		System.out.println("#############################################");
		previewOrder(orders);

		
		int cost = getTotalCost(orders);



		if(discount){
			System.out.println("-----------------------------");
			System.out.println("TOTAL with DISCOUNT (on presenting ID only!):");
			int disc=(int) (cost*DISCOUNT_AMOUNT)/100;
			System.out.println(cost-disc + " T");

		}
		
		System.out.println("-----------------------------");
		System.out.println("Your order will be ready for pickup in 30 minutes.");

		System.out.print("Date: ");
		printCurrentDate();				

		System.out.print("\tTime: ");
		printCurrentTime();				
		System.out.println();

		System.out.println("Order ID: " + generateCode());	
	}

	public static void printCurrentDate(){
	
	LocalDate currDate= LocalDate.now();
	DateTimeFormatter form= DateTimeFormatter.ofPattern("dd.MM.YYYY");
	System.out.print(form.format(currDate));
	} 

	public static void printCurrentTime(){
	LocalTime currTime=LocalTime.now();
    System.out.print(currTime.getHour()+ ":" +currTime.getMinute());


	}

	public static String generateCode(){
		
		String resRandom2="";
		Random random1 = new Random();	
		int resRandom = (int)(Math.random()*9000)+1000;
		resRandom2=Integer.toString(resRandom);
		
    return	resRandom2;
	}
}