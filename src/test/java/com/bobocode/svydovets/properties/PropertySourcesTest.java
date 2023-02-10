package com.bobocode.svydovets.properties;

import com.bobocode.svydovets.bring.properties.PropertySource;
import com.bobocode.svydovets.bring.properties.PropertySources;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PropertySourcesTest {

    @Test
    public void addLastTest() {
        PropertySources propertySources = new PropertySources();
        PropertySource<Map<String, Object>> propertySource1 = new SimplePropertySource("tst1");
        PropertySource<Map<String, Object>> propertySource2 = new SimplePropertySource("tst2");

        propertySources.addLast(propertySource1);
        propertySources.addLast(propertySource2);

        List<PropertySource<?>> actualPropertySources = propertySources.getPropertySources();
        assertEquals(2, actualPropertySources.size());
        assertEquals(propertySource1, actualPropertySources.get(0));
        assertEquals(propertySource2, actualPropertySources.get(1));
    }

    @Test
    public void getTest() {
        PropertySources propertySources = new PropertySources();
        PropertySource<Map<String, Object>> propertySource1 = new SimplePropertySource("test1");
        PropertySource<Map<String, Object>> propertySource2 = new SimplePropertySource("test2");

        propertySources.addLast(propertySource1);
        propertySources.addLast(propertySource2);

        PropertySource<?> actualPropertySource1 = propertySources.get("test1");
        PropertySource<?> actualPropertySource2 = propertySources.get("test2");
        PropertySource<?> actualPropertySource3 = propertySources.get("test3");

        assertEquals(propertySource1, actualPropertySource1);
        assertEquals(propertySource2, actualPropertySource2);
        assertNull(actualPropertySource3);
    }
}
