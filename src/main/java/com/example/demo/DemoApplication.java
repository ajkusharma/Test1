package com.example.demo;

import com.example.demo.EntityLocker;

public class DemoApplication {

	public static void main(String[] args) {
		
//  Here's how we can use the EntityLocker class in our  application:

 EntityLocker locker = new EntityLocker();

// here protected code for entity with ID "entity1"
locker.executeProtectedCode("entity1", () -> {
    // do something with entity1
});

// given protected code for entity with ID 123
locker.executeProtectedCode(123, () -> {
    // do something with entity2
});
			
	}

}
