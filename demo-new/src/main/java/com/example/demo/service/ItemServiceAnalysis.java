package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Item;

@Service
public class ItemServiceAnalysis {

    private final ItemService itemService;

    public ItemServiceAnalysis(ItemService itemService) {
        this.itemService = itemService;
    }

    // Returns only items where the value contains "demo"
    public List<Item> getAllItemsWithDemo() {
        List<Item> allItems = itemService.getAllItems(); // get from service

        return allItems.stream()
                .filter(item -> item.value() != null && item.value().contains("demo"))
                .toList();
    }
}
