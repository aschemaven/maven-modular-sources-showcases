/**
 * Core module for text analysis.
 * <p>
 * This module provides the analysis services and implementation.
 */
// tag::module-info[]
module net.aschemann.maven.demos.analyzer.core {
    requires transitive net.aschemann.maven.demos.analyzer.api; // <1>
    requires org.apache.logging.log4j; // <2>

    exports net.aschemann.maven.demos.analyzer.core.service; // <3>
    // Note: net.aschemann.maven.demos.analyzer.core.internal is NOT exported // <4>
}
// end::module-info[]
