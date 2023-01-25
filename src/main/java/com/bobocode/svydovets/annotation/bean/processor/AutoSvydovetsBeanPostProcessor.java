package com.bobocode.svydovets.annotation.bean.processor;

import com.bobocode.svydovets.annotation.annotations.AutoSvydovets;
import com.bobocode.svydovets.annotation.annotations.Qualifier;
import com.bobocode.svydovets.annotation.bean.factory.BeanFactory;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Implementation of the {@link BeanPostProcessor} interface. Executes processing of
 * {@link AutoSvydovets} marked fields in beans. Such fields will be injected as a
 * dependency, retrieved from the context.
 * <p>
 * If no unique bean is present - processor will try to inject bean based on
 * {@link Qualifier} value.
 *
 * @see BeanPostProcessor
 * @see AutoSvydovets
 * @see Qualifier
 */
public class AutoSvydovetsBeanPostProcessor implements BeanPostProcessor {

    private final BeanFactory beanFactory;

    public AutoSvydovetsBeanPostProcessor(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public void processBeans(Map<String, Object> rootContext) {
        for (var entry : rootContext.entrySet()) {
            Object beanObject = entry.getValue();
            Class<?> beanType = beanObject.getClass();
            for (var field : beanType.getDeclaredFields()) {
                if (field.isAnnotationPresent(AutoSvydovets.class)) {
                    var dependency = getDependencyForField(field);
                    initField(beanObject, field, dependency);
                }
            }
        }
    }

    private Object getDependencyForField(Field field) {
        if (field.isAnnotationPresent(Qualifier.class)) {
            String qualifierValue = field.getAnnotation(Qualifier.class).value();
            return beanFactory.getBean(qualifierValue, field.getType());
        } else {
            return beanFactory.getBean(field.getType());
        }
    }

}
