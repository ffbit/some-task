package com.ffbit.yandex.report.mail;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.ffbit.yandex.report.mail.EmailValidator;

@RunWith(Parameterized.class)
public class EmailValidatorTest {
    private String email;
    private Boolean valid;
    private EmailValidator validator;
    
    @Parameters
    public static Collection<Object[]> init() {
        Object[][] parameters = {
                {"good@email.com", true},
                {"Good@email.Com", true},
                {"some.example1@gmail.com", true},
                {"bad@email@com", false},
                {"anoter.bad.email", false}
        };
        
        return Arrays.asList(parameters);
    }

    public EmailValidatorTest(String email, boolean valid) {
        this.email = email;
        this.valid = valid;
    }
    
    @Before
    public void setUp() {
        validator = new EmailValidator();
    }
    
    @Test
    public void testValidator() {
        assertEquals(valid, validator.validate(email));
    }
    
}
