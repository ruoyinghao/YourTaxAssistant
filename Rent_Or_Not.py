
# _*_ coding:utf-8 _*_
#tax table version 2016, for the year of 2015
#filing status single only
#no carryover considered

import csv

mortgage = float(input("What's your mortgage interest paid?"))
realTax = float(input("What's your property tax and real estate tax paid?"))
depreciation = float(input("What's the depreciation expense of your property?"))
utilMain = float(input("What's the utility and maintenance expense of the property?"))
houseRate = float(input("What's the daily rental rate you want to charge?"))
partialAgi = float(input("What's your current AGI excluding rental Income?"))

perMin=int(input("Minimum personal day: "))
perMax=int(input("Maximum personal day: "))
rentMin=int(input("Minimum rent day: "))
rentMax=int(input("Maximum rent day: "))

taxableIncome=0
retainedIncome=0
taxRate=0;

with open('outcome.csv', 'w') as f:
    writer = csv.writer(f)
    writer.writerow(['rentDay', 'personalDay', 'retainedIncome'])

    for rentDay in range(rentMin,rentMax+1):
        for personalDay in range(perMin,perMax+1):
            if (rentDay+personalDay) <=365:
                if rentDay<=15:
                    taxableIncome=partialAgi-mortgage-realTax
                elif personalDay <= max(14, 0.1*rentDay):
                    allocateRate=rentDay/(personalDay+rentDay)
                    taxableIncome=partialAgi+rentDay*houseRate -allocateRate*(mortgage+realTax+depreciation+utilMain)-realTax*(1-allocateRate)
                else: #mixed use
                    allocateRate=rentDay/(personalDay+rentDay)
                    taxableIncome=partialAgi+max(0,rentDay*houseRate -allocateRate*(mortgage+realTax+depreciation+utilMain))-(1-allocateRate)*(mortgage+realTax)
                      
                
                if taxableIncome<=9225:
                    retainedIncome=taxableIncome*0.9
                elif taxableIncome<=37450:
                    retainedIncome=taxableIncome-922.5-(taxableIncome-9225)*0.85
                elif taxableIncome<=90750:
                    retainedIncome=taxableIncome-5156.25-(taxableIncome-37450)*0.75
                elif taxableIncome<=189300:
                    retainedIncome=taxableIncome-18481.25-(taxableIncome-90750)*0.72
                elif taxableIncome<=411500:
                    retainedIncome=taxableIncome-46075.25-(taxableIncome-189300)*0.67
                elif taxableIncome<=413200:
                    retainedIncome=taxableIncome-119401.25*(taxableIncome-411500)*0.65
                else:
                    retainedIncome=taxableIncome-119996.25*(taxableIncome-413200)*0.604
                
                
                writer.writerow([rentDay,personalDay,retainedIncome])
