/**
 * Core module for text analysis.
 * <p>
 * This module provides the domain model and analysis services.
 */
// tag::module-info[]
module net.aschemann.maven.demos.analyzer.core {
    requires org.apache.logging.log4j; // <1>

    exports net.aschemann.maven.demos.analyzer.core.model; // <2>
    exports net.aschemann.maven.demos.analyzer.core.service; // <2>
}
// end::module-info[]
