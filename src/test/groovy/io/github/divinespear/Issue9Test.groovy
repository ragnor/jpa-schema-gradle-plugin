package io.github.divinespear

import static org.hamcrest.Matchers.*
import static org.junit.Assert.*

import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test;

class Issue9Test {
    
    JpaSchemaGenerateTask getTask() {
        def project = ProjectBuilder.builder().build()
        project.apply plugin: "jpa-schema-generate"
        
        assertThat(project.tasks.generateSchema, instanceOf(JpaSchemaGenerateTask))
        return project.tasks.generateSchema;
    }

    @Test
    void shouldOverrideCreateIndex() {
        def from = "CREATE INDEX INDEX_SYSTEM_CURRENCY_RATE_VERSION DESC ON SYSTEM_CURRENCY_RATE (VERSION DESC);"
        def expected = """CREATE INDEX INDEX_SYSTEM_CURRENCY_RATE_VERSION\r
\tON SYSTEM_CURRENCY_RATE (VERSION DESC);"""
        def task = getTask()
        assertThat(task.format(from), is(expected))
    }

}