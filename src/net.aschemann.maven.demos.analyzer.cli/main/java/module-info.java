/**
 * CLI module for text analysis.
 * <p>
 * This module provides a command-line interface for the text analyzer.
 */
// tag::module-info[]
module net.aschemann.maven.demos.analyzer.cli {
    requires net.aschemann.maven.demos.analyzer.api; // <1>
    requires info.picocli;
    requires org.apache.logging.log4j;

    uses net.aschemann.maven.demos.analyzer.api.AnalyzerService; // <2>

    opens net.aschemann.maven.demos.analyzer.cli to info.picocli;
}
// end::module-info[]
