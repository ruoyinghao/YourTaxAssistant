import java.util.*;

public class HouseType {
	public static void main(String[] args) {
		double taxableIncome;
		double carryforward=0;
		double itemize;
		double deductible;
		double allocateRate;
		Scanner console = new Scanner(System.in);
		
		System.out.println("Hi, Let me help you determine your rentals");
		
		System.out.println("How many days in this year did you rent the house?");
		double rentDays=console.nextInt();//the days in a year that the residence is rented
		
		System.out.println("How many days in this year did your household use this house personally");
		double personalDays=console.nextInt();//days in a year that the residence is for personal use
		
		System.out.println("What's the daily rate of rentals");
		double rentDailyRate=console.nextDouble();
		
		System.out.println("What's the mortgage interest paid");
		double interest=console.nextDouble();
		System.out.println("What's the real estate taxes paid");
		double realTax=console.nextDouble();
		System.out.println("What's the depreciation expense");
		double depreciation=console.nextDouble();
		System.out.println("What's the utility expense");
		double utility=console.nextDouble();
		System.out.println("What's the maintenance expense");
		double maintenance=console.nextDouble();
		
		if(rentDays<=14){
			System.out.println("This property is primaily personal use.");
			taxableIncome=0;
			itemize=interest+realTax;
			deductible=0;
			
		}
		else if(personalDays<=Math.max(14,0.1*rentDays)){
			System.out.println("This property is primaily rental use.");
			
			allocateRate=rentDays/(rentDays+personalDays);
			itemize=realTax*(1-allocateRate);
			deductible=(interest+realTax+maintenance+utility+depreciation)*allocateRate;
			taxableIncome=rentDailyRate*rentDays-deductible;
		}
		else{
			System.out.println("This property is rental/personal mix use.");
			allocateRate=rentDays/(rentDays+personalDays);
			taxableIncome=rentDailyRate*rentDays-(interest+realTax)*allocateRate;
			System.out.println("allocate rate "+allocateRate);
			itemize=(interest+realTax)*(1-allocateRate);
			if(taxableIncome<0){//if negative after interest tax
				carryforward=Math.abs(taxableIncome);
				deductible=rentDailyRate*rentDays;
				
				taxableIncome=0;
			}
			else{//if positive after interest tax
				
				taxableIncome=taxableIncome-(utility+maintenance)*allocateRate;
				if(taxableIncome<0){//if negative after expense
					carryforward=carryforward+Math.abs(taxableIncome);
					deductible=rentDailyRate*rentDays;
					taxableIncome=0;
				}
				else{//if positive after depreciation
					taxableIncome=taxableIncome-depreciation*allocateRate;
					if(taxableIncome<0){
						carryforward=carryforward+Math.abs(taxableIncome);
						deductible=rentDailyRate*rentDays;
						taxableIncome=0;
					}
					else{//if positive after everything is done
						//taxable income is taxable income
						deductible=(realTax+interest+depreciation+utility+maintenance)*allocateRate;
					}
				}
			}
				
		}
		System.out.println("Taxable income is "+taxableIncome);
		System.out.println("itemize deduction allowed is "+itemize);
		System.out.println("deduction for AGI is "+deductible);
		System.out.println("deduction carry forward is "+carryforward);
}
	
	
	

}
