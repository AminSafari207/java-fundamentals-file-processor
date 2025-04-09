# Java File Processor

This project is a multithreaded text file analyzer built with Java 21 and virtual threads (Project Loom). It scans a directory for `.txt` files, analyzes each file concurrently, and generates a summary report. Each file's analysis includes line counts, word counts, keyword hits, top words, and last modified timestamp.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technologies](#technologies)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [License](#license)

## Overview

Each `.txt` file is processed in parallel using virtual threads. The results include statistics like word and line counts, detected keywords (e.g., "error", "fail", "exception"), and the top 3 most frequent words (excluding common stop words).

## Features

- Analyzes `.txt` files in a given directory
- Processes each file in a separate virtual thread
- Extracts line and word count
- Counts keyword occurrences (e.g., error, fail, warning, exception)
- Detects top 3 most frequent non-common words
- Records last modified timestamp of each file
- Displays a full report of results

## Technologies

- Java 21
- Project Loom (Virtual Threads)
- Java NIO (`Path`, `Files`, `DirectoryStream`)
- Collections (`Map`, `List`, `Set`)
- Streams API

## Getting Started

### Prerequisites

- Java 21 or higher
- IntelliJ IDEA or any Java-compatible IDE

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/java-fundamentals-file-processor.git
   ```

2. Navigate to the project directory:
   ```bash
   cd java-fundamentals-file-processor
   ```

3. Create a `samples` folder in the root directory and add `.txt` files to it.

4. Compile the code:
   ```bash
   javac -d bin src/com/fileprocessor/**/*.java
   ```

5. Run the program:
   ```bash
   java -cp bin com.fileprocessor.Main
   ```

## Usage

When you run the app, it scans the `samples/` folder for `.txt` files. Each file is analyzed for:
- Line and word count
- Predefined keywords
- Top 3 most frequent meaningful words
- Last modified time

A summary is printed in the console.

## Project Structure

```
java-fundamentals-file-processor/
├── samples/                      # Place .txt files here
├── src/
│   └── com/
│       └── fileprocessor/
│           ├── Main.java
│           ├── model/
│           │   └── FileAnalysisResult.java
│           ├── service/
│           │   └── FileAnalyzer.java
├── README.md
```

## License

This project is licensed under the MIT License.
