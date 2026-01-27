/**
 * CLI module for text analysis.
 * <p>
 * This module provides a command-line interface for the text analyzer.
 */
// tag::module-info[]
module net.aschemann.maven.demos.analyzer.cli {
    requires net.aschemann.maven.demos.analyzer.core;
    requires info.picocli;
    requires org.apache.logging.log4j;

    opens net.aschemann.maven.demos.analyzer.cli to info.picocli;
}
// end::module-info[]
