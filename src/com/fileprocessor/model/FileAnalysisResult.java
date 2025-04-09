package com.fileprocessor.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class FileAnalysisResult {
    private final String fileName;
    private final int lineCount;
    private final int wordCount;
    private final Map<String, Integer> keywordCounts;
    private final List<String> topWords;
    private final LocalDateTime lastModified;

    public FileAnalysisResult(
            String fileName,
            int lineCount,
            int wordCount,
            Map<String, Integer> keywordCounts,
            List<String> topWords,
            LocalDateTime lastModified) {
        this.fileName = fileName;
        this.lineCount = lineCount;
        this.wordCount = wordCount;
        this.keywordCounts = keywordCounts;
        this.topWords = topWords;
        this.lastModified = lastModified;
    }

    public String getFileName() {
        return fileName;
    }

    public int getLineCount() {
        return lineCount;
    }

    public int getWordCount() {
        return wordCount;
    }

    public Map<String, Integer> getKeywordCounts() {
        return keywordCounts;
    }

    public List<String> getTopWords() {
        return topWords;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    @Override
    public String toString() {
        return String.format(
                "File: %s\nLines: %d | Words: %d | Last Modified: %s\nTop Words: %s\nKeywords: %s\n",
                fileName,
                lineCount,
                wordCount,
                lastModified,
                topWords,
                keywordCounts
        );
    }
}
