package com.fileprocessor;

import com.fileprocessor.model.FileAnalysisResult;
import com.fileprocessor.service.FileAnalyzer;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Path folder = Paths.get("samples"); // your folder with .txt files

        if (!Files.exists(folder) || !Files.isDirectory(folder)) {
            System.out.println("Folder not found: " + folder.toAbsolutePath());
            return;
        }

        FileAnalyzer analyzer = new FileAnalyzer();
        List<FileAnalysisResult> results = Collections.synchronizedList(new ArrayList<>());
        List<Thread> threads = new ArrayList<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(folder, "*.txt")) {
            for (Path file : stream) {
                Thread thread = Thread.ofVirtual().start(() -> {
                    try {
                        FileAnalysisResult result = analyzer.analyze(file);
                        results.add(result);
                    } catch (IOException e) {
                        System.err.println("Failed to analyze " + file.getFileName() + ": " + e.getMessage());
                    }
                });
                threads.add(thread);
            }
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("\n=== ANALYSIS RESULTS ===");
        results.forEach(result -> {
            System.out.println("-".repeat(60));
            System.out.println(result);
        });

        System.out.println("\nTotal files analyzed: " + results.size());
    }
}
