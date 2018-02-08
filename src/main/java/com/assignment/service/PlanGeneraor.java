package com.assignment.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Service;

import com.assignment.models.PlanOutput;
import com.assignment.models.PlanRequest;
import com.assignment.models.PlanResponse;

@Service
public class PlanGeneraor {

	public PlanResponse generate(PlanRequest planInput) {
		double remainingOutstandingPrincipal = Double.parseDouble(planInput.getLoanAmount());
		PlanResponse planResponse = new PlanResponse();
		List<PlanOutput> plans = new ArrayList<PlanOutput>();
		double monthlyPayment = calculateMonthlyPayment(Integer.parseInt(planInput.getLoanAmount()), planInput.getDuration(),
				Double.parseDouble(planInput.getNominalRate()));

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		
		for (int i=0; i< planInput.getDuration(); i++) {
			PlanOutput planOutput = new PlanOutput();
			planOutput.setInitialOutstandingPrincipal(remainingOutstandingPrincipal);
			planOutput.setBorrowerPaymentAmount(Precision.round(monthlyPayment, 2));
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(planInput.getStartDate());
			calendar.add(Calendar.MONTH, + i);
		
			planOutput.setDate(df.format(calendar.getTime()));
			double interest = calculateInterest(remainingOutstandingPrincipal, Double.parseDouble(planInput.getNominalRate()));
			planOutput.setInterest(Precision.round(interest, 2));
			double principal = planOutput.getBorrowerPaymentAmount() - planOutput.getInterest();
			planOutput.setPrincipal(Precision.round(principal, 2));
			remainingOutstandingPrincipal -= planOutput.getPrincipal();
			remainingOutstandingPrincipal = Precision.round(remainingOutstandingPrincipal, 2);
			if (remainingOutstandingPrincipal < 0) {
				remainingOutstandingPrincipal = 0;
			}
			planOutput.setRemainingOutstandingPrincipal(remainingOutstandingPrincipal);
			
			plans.add(planOutput);
			planResponse.setPlans(plans);
		}
		return planResponse;
	}

	public double calculateMonthlyPayment(double loanAmount, int termInMonths, double nominalRate) {

		nominalRate /= 100.0;

		double monthlyRate = nominalRate / 12.0;

		return (loanAmount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -termInMonths));

	}
	
	public double calculateInterest(double loanAmount, double nominalRate) {
		
		double interest = (nominalRate * loanAmount * 30) / (360 * 100);
		
		return interest;
	}
}
