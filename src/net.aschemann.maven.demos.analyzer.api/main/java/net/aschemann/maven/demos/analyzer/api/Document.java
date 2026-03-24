package net.aschemann.maven.demos.analyzer.api;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

/**
 * Represents a text document to be analyzed.
 */
public record Document(Path path, String content) {

    public Document {
        Objects.requireNonNull(path, "path must not be null");
        Objects.requireNonNull(content, "content must not be null");
    }

    // tag::factory[]
    /**
     * Reads a document from the given path using UTF-8 encoding.
     *
     * @param path the path to the file
     * @return a new Document instance
     * @throws IOException if the file cannot be read
     */
    public static Document fromPath(Path path) throws IOException {
        return fromPath(path, StandardCharsets.UTF_8);
    }

    /**
     * Reads a document from the given path using the specified charset.
     *
     * @param path the path to the file
     * @param charset the charset to use for reading
     * @return a new Document instance
     * @throws IOException if the file cannot be read
     */
    public static Document fromPath(Path path, Charset charset) throws IOException {
        if (!Files.exists(path)) {
            throw new IOException("File not found: " + path);
        }
        if (!Files.isRegularFile(path)) {
            throw new IOException("Not a regular file: " + path);
        }
        String content = Files.readString(path, charset);
        return new Document(path, content);
    }
    // end::factory[]

    /**
     * Creates a document with the given content and a synthetic path.
     *
     * @param content the document content
     * @return a new Document instance
     */
    public static Document ofContent(String content) {
        return new Document(Path.of("unnamed"), content);
    }
}
