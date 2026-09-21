package payloads;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.github.javafaker.Faker;

import pojo.Address;
import pojo.Cart;
import pojo.CartProduct;
import pojo.Geolocation;
import pojo.Name;
import pojo.Product;
import pojo.User;

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
	
	//user
	/*
	 * 	private String email;
	private String username;
	private String password;
	private Name name;
	private Address address;
	private String phone;

	 */
	public static User userPayload()
	{
		//name
		String firstname = faker.name().firstName();
		String lastname = faker.name().lastName();
		Name name=new Name(firstname,lastname);
		
		//geolocation
		String latitude = faker.address().latitude();
		String longitude = faker.address().longitude();
		
		Geolocation geolocation=new Geolocation(latitude,longitude);
		
	//address
		String city = faker.address().cityName();
		String street = faker.address().streetName();
		int num = random.nextInt(100);
		String zipcode = faker.address().zipCode();
		Address address=new Address(city,street,num,zipcode,geolocation);
		
		//user
		String email=faker.internet().emailAddress();
		String username=faker.name().username();
		String password=faker.internet().password();
		String phonenumber = faker.phoneNumber().cellPhone();
		User user=new User(email,username,password,name,address,phonenumber);
		
		
		return user;
		
		
	}
	//cart 
	public static Cart cartPayload(int userId)
	{
		List<CartProduct>products=new ArrayList<>();
		int productId = random.nextInt(100);
		int quantity = random.nextInt(10)+1;
		CartProduct cartProduct=new CartProduct(productId,quantity);
		products.add(cartProduct);
		//Date date=new Date();
        //new Date()  ----> Returns date like  Wed Feb 19 13:17:45 IST 202
        // We need to convert this to "yyyy-MM-dd" format in String 
        
         SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);// Define output date format
         String date = outputFormat.format(new Date());//Converting to String
	
        return new Cart(userId, date, products);

	}
	
}
