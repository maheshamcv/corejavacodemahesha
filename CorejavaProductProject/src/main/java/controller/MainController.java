package controller;

import java.util.Scanner;

import Service.Impl;

import model.Product;

public class MainController {

	public static void main(String[] args) {
		System.out.println("Welcome to Yashomahi Online shopping");
		Impl impl=new Impl();
		Product product=new Product();
		Scanner sc=new Scanner(System.in);
	
		System.out.println("Enter your choice");
		System.out.println("1---> for addProduct");
		System.out.println("2---> for buyProduct");
		
		int a=sc.nextInt();
		if(a==1) {
		impl.adddata(product);
		}
		else if(a==2) {
			impl.DisplayandBuy(product);
		}
		else {
			MainController.main(args);
		}
	}

}
