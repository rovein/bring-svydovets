package com.bobocode.svydovets.autowiring.constructor;

import com.bobocode.svydovets.bring.context.AnnotationApplicationContext;
import com.bobocode.svydovets.bring.exception.BeanException;
import com.bobocode.svydovets.autowiring.constructor.success.FooDependency;
import com.bobocode.svydovets.autowiring.constructor.success.Service;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("AutoSvydovets constructor tests")
public class AutoSvydovetsConstructorTest {

    private AnnotationApplicationContext applicationContext;

    @Test
    @Order(1)
    @DisplayName("Bean dependencies is correctly set by the constructor-injection")
    void createBeanWithConstructorBasedDependencies() {
        applicationContext = new AnnotationApplicationContext("com.bobocode.svydovets.autowiring.constructor.success");
        Service constructorInjectionBean = applicationContext.getBean(Service.class);
        assertNotNull(constructorInjectionBean);
        assertEquals("FooBar", constructorInjectionBean.getMessage());
    }

    @Test
    @Order(2)
    @DisplayName("BeanException should be thrown if there is no field for constructor param")
    void createBeanWithConstructorBasedDependenciesWithNoField() {
        BeanException beanException = assertThrows(BeanException.class, () ->
                new AnnotationApplicationContext("com.bobocode.svydovets.autowiring.constructor.nofield"));
        assertThat(beanException.getMessage(), containsString("No field found for constructor-passed parameter type "));
        assertThat(beanException.getMessage(), containsString(FooDependency.class.getName()));
    }
}
