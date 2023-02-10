package com.bobocode.svydovets.autowiring.configuration;

import com.bobocode.svydovets.bring.annotations.AutoSvydovets;
import com.bobocode.svydovets.bring.annotations.Bean;
import com.bobocode.svydovets.bring.annotations.Configuration;
import com.bobocode.svydovets.bring.annotations.Primary;

@Configuration
public class TestConfig {

    @AutoSvydovets
    private AutoSvydovetsDependency autoSvydovetsDependency;

    @Bean
    @Primary
    public FooService fooSecondaryService() {
        FooService fooService = new FooService();
        fooService.setMessage("FooPrimary");
        return fooService;
    }

    @Bean
    public FooService fooService() {
        FooService fooService = new FooService();
        fooService.setMessage("Foo");
        return fooService;
    }

    @Bean
    public FooBarService fooBarService() {
        FooBarService fooBarService = new FooBarService(fooService());
        fooBarService.setMessage("Bar");
        return fooBarService;
    }

    @Bean
    public AutoSvydovetsClientBean autoSvydovetsClientBean() {
        return new AutoSvydovetsClientBean(autoSvydovetsDependency);
    }


}
