package org.example;

import org.springframework.stereotype.Service;

@Service
public class BookService {


	public String print(){
		System.out.println("#########################");
		return "print";
	}

}
