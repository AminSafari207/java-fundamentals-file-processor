package com.fileprocessor.service;

import com.fileprocessor.model.FileAnalysisResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public class FileAnalyzer {
    private static final List<String> keywords = List.of("error", "fail", "warning", "exception");
    private static final Set<String> ignoreWords = Set.of("the", "a", "an", "and", "or", "but", "of", "to", "in", "on");

    public FileAnalysisResult analyze(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        int lineCount = (int) lines.stream().filter(line -> !line.trim().isEmpty()).count();

        int wordCount = 0;
        Map<String, Integer> keywordCounts = new HashMap<>();
        Map<String, Integer> wordFrequency = new HashMap<>();

        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            String[] words = line.trim().split("\\s+");

            for (String word : words) {
                String clean = word.toLowerCase().replaceAll("[^a-z]", "");
                if (clean.isEmpty()) continue;

                wordCount++;

                if (keywords.contains(clean)) {
                    keywordCounts.put(clean, keywordCounts.getOrDefault(clean, 0) + 1);
                }

                if (!ignoreWords.contains(clean)) {
                    wordFrequency.put(clean, wordFrequency.getOrDefault(clean, 0) + 1);
                }
            }
        }

        List<String> topWords = wordFrequency.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        LocalDateTime lastModified = Files.getLastModifiedTime(path)
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        return new FileAnalysisResult(
                path.getFileName().toString(),
                lineCount,
                wordCount,
                keywordCounts,
                topWords,
                lastModified
        );
    }
}
