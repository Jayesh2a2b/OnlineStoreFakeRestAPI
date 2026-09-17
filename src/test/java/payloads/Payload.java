package payloads;

import java.util.Random;

import com.github.javafaker.Faker;

import pojo.Product;

public class Payload {
	private static final Faker faker = new Faker();
	private static final String categories[] = { "electronics", "furniture", "clothing", "books", "beauty" };
	private static final Random random = new Random();

	public static Product productPayload() {
		String name = faker.commerce().productName();
		Double price = Double.parseDouble(faker.commerce().price());
		String discription = faker.lorem().sentence();
		String imageUrl = "https://i.pravatar.cc/100";
		String category=categories[random.nextInt(categories.length)];
		new Product( name,price,discription,imageUrl, category);
		return new  Product(name,price,discription,imageUrl, category);
		

	}
}
