package net.aschemann.maven.demos.analyzer.api;

// tag::interface[]
/**
 * Service interface for text analysis.
 * Implementations are discovered at runtime via {@link java.util.ServiceLoader}.
 */
public interface AnalyzerService {

    /**
     * Analyzes the given document and returns statistics.
     *
     * @param document the document to analyze
     * @return the analysis statistics
     */
    Statistics analyze(Document document);
}
// end::interface[]
