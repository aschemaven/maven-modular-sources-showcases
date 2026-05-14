package net.aschemann.maven.demos.analyzer.core.service;

import net.aschemann.maven.demos.analyzer.api.AnalyzerService;
import net.aschemann.maven.demos.analyzer.api.Document;
import net.aschemann.maven.demos.analyzer.api.Statistics;
// tag::import-internal[]
import net.aschemann.maven.demos.analyzer.core.internal.TextNormalizer;
// end::import-internal[]
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

// tag::class[]
/**
 * Default implementation of {@link AnalyzerService}.
 * Provides comprehensive text analysis with word frequency tracking.
 */
public class DefaultTextAnalyzer implements AnalyzerService { // <1>

    private static final Logger LOG = LogManager.getLogger(DefaultTextAnalyzer.class);

    private static final int DEFAULT_TOP_WORDS_LIMIT = 10;

    private final int topWordsLimit;

    public DefaultTextAnalyzer() {
        this(DEFAULT_TOP_WORDS_LIMIT);
    }

    public DefaultTextAnalyzer(int topWordsLimit) {
        this.topWordsLimit = topWordsLimit;
    }

    @Override // <2>
    public Statistics analyze(Document document) {
        LOG.info("Analyzing document: {}", document.path());

        String content = document.content();
        String[] lines = content.split("\\R");
        long lineCount = lines.length;

        // tag::use-normalizer[]
        String[] words = TextNormalizer.tokenize(content);
        // end::use-normalizer[]
        long wordCount = words.length;

        long characterCount = content.length();
        long characterCountWithoutSpaces = content.chars()
                .filter(c -> !Character.isWhitespace(c))
                .count();

        Map<String, Long> wordFrequencies = Arrays.stream(words)
                .filter(word -> !word.isEmpty())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        List<Map.Entry<String, Long>> topWords = wordFrequencies.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(topWordsLimit)
                .toList();

        LOG.debug("Analysis complete: {} lines, {} words, {} characters",
                lineCount, wordCount, characterCount);

        return new Statistics(
                document,
                lineCount,
                wordCount,
                characterCount,
                characterCountWithoutSpaces,
                wordFrequencies,
                topWords
        );
    }
// end::class[]

}
