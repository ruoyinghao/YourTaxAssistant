	function recordNumber()
	{
		var rentDays=document.getElementById(rentDays).value
		var personalDays=document.getElementById(personalDays).value
		var rentDailyRate=document.getElementById(rentDailyRate).value
		var interest=document.getElementById(interest).value
		var realTax=document.getElementById(realTax).value
		var utilMain=document.getElementById(utilMain).value
		var depreciation=document.getElementById(depreciation).value
		
		var interestItem
		var realTaxItem
		var interestDed
		var realTaxDed
		var utilMainDed
		var depreDed
		
		var taxableIncome
		var carryforward=0
		var itemize
		var deductible
		var allocateRate
		
		if(rentDays=14)
		{
			document.getElementById(propType).value=Primary Personal
			taxableIncome=0
			itemize=eval(interest)+eval(realTax)
			interestItem=interest
			realTaxItem=realTax
			
		}
		else if(personalDays=Math.max(14,0.1rentDays))
		{
			document.getElementById(propType).value=Primary Rental
			allocateRate=rentDays(eval(rentDays)+eval(personalDays))
			
			itemize=Math.round(realTax(1-allocateRate))
			
			realTaxItem=itemize
			interestItem=0
			interestDed=Math.round(interestallocateRate)
			
			realTaxDed=Math.round(realTaxallocateRate)
			
			utilMainDed=Math.round(utilMainallocateRate)
			depreDed=Math.round(depreciationallocateRate)
			
			taxableIncome=eval(rentDailyRaterentDays)-(interestDed+realTaxDed+utilMainDed+depreDed)

		}
		else
		{
			document.getElementById(propType).value=RentalPersonal Mixed Use
			allocateRate=rentDays(eval(rentDays)+eval(personalDays))
			taxableIncome=rentDailyRaterentDays-Math.round((interest+realTax)allocateRate)
			
			realTaxItem=Math.round(realTax(1-allocateRate))
			interestItem=Math.round(interest(1-allocateRate))
			itemize=realTaxItem+interestItem
			if(taxableIncome0)
				{if negative after interest tax
					carryforward=Math.abs(taxableIncome)

					taxableIncome=0
				}
				else
					{if positive after interest tax
						taxableIncome=taxableIncome-Math.round(utilMainallocateRate)
						if(taxableIncome0)
							{if negative after expense
								carryforward=carryforward+Math.abs(taxableIncome)
								
								taxableIncome=0
							}
							else
								{if positive after depreciation
									taxableIncome=Math.round(taxableIncome-depreciationallocateRate)
									if(taxableIncome0)
									{
										carryforward=carryforward+Math.abs(taxableIncome)
										
										taxableIncome=0
									}
									
								}
							}

						}
						document.getElementById(taxInc).value=taxableIncome
						document.getElementById(ite).value=itemize
						document.getElementById(carr).value=carryforward
						document.getElementById(mortIt).value=interestItem
						document.getElementById(rtIt).value=realTaxItem
					}