package com.testIC.pattern.singleton;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConfigurationManagerTest {
    @Test
    public void shouldReturnSameInstance() {
        ConfigurationManager c1 = ConfigurationManager.getInstance();
        ConfigurationManager c2 = ConfigurationManager.getInstance();
        assertSame(c1, c2);
    }
    @Test
    void shouldAccessConfigurationValues() {
        ConfigurationManager config = ConfigurationManager.getInstance();
        assertEquals("Finance Application", config.getApplicationName());
    }
    @Test
    void multipleCallsReturnSameInstance() {
        ConfigurationManager c1 = ConfigurationManager.getInstance();
        ConfigurationManager c2 = ConfigurationManager.getInstance();
        ConfigurationManager c3 = ConfigurationManager.getInstance();
        assertSame(c1, c2);
        assertSame(c2, c3);
    }
    @Test
    void shouldReadConfigurationValues() {
        ConfigurationManager config = ConfigurationManager.getInstance();
        assertNotNull(config.getApplicationName());
    }
}