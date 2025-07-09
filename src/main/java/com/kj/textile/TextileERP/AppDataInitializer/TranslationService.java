package com.kj.textile.TextileERP.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TranslationService {

    // Cache to store loaded translations
    private final Map<String, Map<String, Object>> translationsByLang = new ConcurrentHashMap<>();

    /**
     * Load and cache translation file for the given language
     */
    private void loadTranslationFile(String lang) {
        try {
            if (!translationsByLang.containsKey(lang)) {
                ObjectMapper mapper = new ObjectMapper();
                String path = String.format("translations/%s.json", lang);
                InputStream is = getClass().getClassLoader().getResourceAsStream(path);
                if (is == null) throw new RuntimeException("Translation file not found for lang: " + lang);

                Map<String, Object> translations = mapper.readValue(is, new TypeReference<Map<String, Object>>() {});
                translationsByLang.put(lang, translations);
                System.out.println("Loaded translation for lang: " + lang);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load translation for lang: " + lang, e);
        }
    }

    /**
     * Get translation by path and language, e.g., "master.partyMaster.partyName", "mr"
     */
    public String getTranslation(String path, String lang) {
        loadTranslationFile(lang);
        Map<String, Object> translations = translationsByLang.get(lang);

        String[] keys = path.split("\\.");
        Object current = translations;

        for (String key : keys) {
            if (current instanceof Map<?, ?>) {
                current = ((Map<?, ?>) current).get(key);
            } else {
                return null;
            }
        }

        return current != null ? current.toString() : null;
    }
}
