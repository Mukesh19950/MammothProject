package com.telliant.pageObjects;

import com.github.javafaker.Faker;
import com.telliant.core.web.WebDriverRoot;

public class Bonus extends WebDriverRoot{
	public void navigateToBonus() {
		waitForElementVisible("bonustab");
		clickElement("bonustab");
	}
	public void bonusMin() {
		Faker faker =new Faker();
		int number = faker.number().numberBetween(1, 5);
		String min=Integer.toString(number);
		type("bonusMintxtbox", min);
	}
	public void bonusMax() {
		Faker faker =new Faker();
		int number = faker.number().numberBetween(1, 9);
		String max=Integer.toString(number);
		type("bonusMaxtxtbox", max);
	}
	public void bonusAmount() {
		Faker faker =new Faker();
		int number = faker.number().numberBetween(100, 500);
		String amount=Integer.toString(number);
		type("bonusAmounttxtbox", amount);
	}
	public void badReviews() {
		Faker faker =new Faker();
		int number = faker.number().numberBetween(1, 5);
		String amount=Integer.toString(number);
		type("badReviewstxtbox", amount);
	}
	public void collisions() {
		Faker faker =new Faker();
		int number = faker.number().numberBetween(1, 5);
		String amount=Integer.toString(number);
		type("collisionstxtbox", amount);
	}
	public void badReviewDedication() {
		Faker faker =new Faker();
		int number = faker.number().numberBetween(1, 5);
		String amount=Integer.toString(number);
		type("badreviewDedication", amount);
	}
	public void collisionsDedication() {
		Faker faker =new Faker();
		int number = faker.number().numberBetween(1, 5);
		String amount=Integer.toString(number);
		type("collisionsDedication", amount);
	}

}
