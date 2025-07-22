package com.example.service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.service.ItemService;
import com.example.demo.service.ItemServiceAnalysis;
import com.example.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ItemServiceAnalysisTest {
	private ItemService itemService;
	private ItemServiceAnalysis itemServiceAnalysis;

    @BeforeEach
    void setUp() {
        itemService = new ItemService();
        itemServiceAnalysis = new ItemServiceAnalysis();
    }
    
    @Test
    void testGetDemoItem() {
    	Item item1 = itemService.createItem("Item1");
    	Item item2 = itemService.createItem("Item2");
    	Item item3 = itemService.createItem("Item3");
    	Item item4 = itemService.createItem("demo");
    	
    	List<Item> data = new ArrayList<Item>();
    	data.add(item1);
    	data.add(item2);
    	data.add(item3);
    	data.add(item4);
    	
    	List<Item> result = itemServiceAnalysis.getAllItemsWithDemo(data);
    	assertTrue(result.isEmpty());
    	for(Item item : result) {
    		assertEquals(item.value(), "demo");
    	}
    }
    
    

}
