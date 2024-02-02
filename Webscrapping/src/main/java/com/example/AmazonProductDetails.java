package com.example;

import java.util.Scanner;
import com.microsoft.playwright.*;

public class AmazonProductDetails {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Please enter a product: ");
        String userInput = input.nextLine();
        int numberOfProducts;
        // Ensure that the number of products is between 1 and 10
        do {
            System.out.println("Enter the number of products (between 1 and 10):");
            numberOfProducts = input.nextInt();
        } while (numberOfProducts < 1 || numberOfProducts > 10);

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://www.amazon.com");
        page.fill("input[name='field-keywords']", userInput);
        page.press("input[name='field-keywords']", "Enter");
        StringBuilder emailBody = new StringBuilder("Amazon Product Details:\n");
        for (int i = 1; i <= numberOfProducts; i++) {
            page.locator(String.format("(//span[contains(@class,'a-size-base-plus a-color-base')])[%d]", i)).click();
            emailBody.append("Product ").append(i).append(" : ").append(page.locator("id=productTitle").textContent()).append("\n");
            emailBody.append("Price = ").append(page.locator("(//span[@class='aok-offscreen'])[1]").textContent()).append("\n");
            page.goBack();
        }

        playwright.close();

        // Call the email sending function
        EmailSender.sendEmail(emailBody.toString());
    }
}