package com.example.application.dataProvider;


import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InternationalizationProvider {
    public Map<String, String> getCategoriesButtonString(){
        Map<String , String> strings = new HashMap<>();
        strings.put("Polski", "Kategorie");
        strings.put("English", "Categories");
        return strings;
    }
    public Map<String, String> getLastButtonStrings(){
        Map<String , String> strings = new HashMap<>();
        strings.put("Polski", "Najnowsze wiadomości");
        strings.put("English", "Last news");
        return strings;
    }
    public Map<String, String> getNewsLabelStrings(){
        Map<String , String> strings = new HashMap<>();
        strings.put("Polski", "Wiadomości dla kategorii");
        strings.put("English", "News for category");
        return strings;
    }
}
