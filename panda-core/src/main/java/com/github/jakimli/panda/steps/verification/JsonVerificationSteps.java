package com.github.jakimli.panda.steps.verification;

import com.github.jakimli.panda.domain.Variables;
import com.github.jakimli.panda.domain.verification.JsonVerificationContext;
import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import static java.lang.Double.parseDouble;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class JsonVerificationSteps {
    @Autowired
    JsonVerificationContext verifier;

    @Autowired
    Variables variables;

    @Then("^verify: '([^\"]*)'='([^\"]*)'$")
    public void verifyEqualsLiteral(String path, final String expected) throws Throwable {
        verifier.verify(path, actual -> assertThat(actual, is(expected)));
    }

    @Then("^verify: '([^\"]*)'!='([^\"]*)'$")
    public void verifyNotEqualsLiteral(String path, final String expected) throws Throwable {
        verifier.verify(path, actual -> assertThat(actual, not(expected)));
    }

    @Then("^verify: '([^\"]*)'=\"([^\"]*)\"$")
    public void verifyEqualsString(String path, String expected) throws Throwable {
        final String interpret = variables.interpret(expected);
        verifier.verify(path, actual -> assertThat(actual, is(interpret)));
    }

    @Then("^verify: '([^\"]*)'!=\"([^\"]*)\"$")
    public void verifyNotEqualsString(String path, String expected) throws Throwable {
        final String interpret = variables.interpret(expected);
        verifier.verify(path, actual -> assertThat(actual, not(interpret)));
    }

    @Then("^verify: '([^\"]*)' contains: '([^\"]*)'$")
    public void verifyContainsLiteral(String path, String contained) throws Throwable {
        verifier.verify(path, actual -> assertThat(String.valueOf(actual), containsString(contained)));
    }

    @Then("^verify: '([^\"]*)' contains: \"([^\"]*)\"$")
    public void verifyContainsString(String path, String contained) throws Throwable {
        final String interpret = variables.interpret(contained);
        verifier.verify(path, actual -> assertThat(String.valueOf(actual), containsString(interpret)));
    }

    @Then("^verify: '([^\"]*)'=(\\d+)$")
    public void verifyEqualsInteger(String path, final int expected) throws Throwable {
        verifier.verify(path, actual -> assertThat(actual, is(expected)));
    }

    @Then("^verify: '([^\"]*)'!=(\\d+)$")
    public void verifyNotEqualsInteger(String path, final int expected) throws Throwable {
        verifier.verify(path, actual -> assertThat(actual, not(expected)));
    }

    @Then("^verify: '([^\"]*)'>(\\d+)$")
    public void verifyGreaterThanInteger(String path, final int expected) throws Throwable {
        verifier.verify(path, actual -> assertThat((int) actual, greaterThan(expected)));
    }

    @Then("^verify: '([^\"]*)'=(\\d+\\.\\d+)$")
    public void verifyEqualsDouble(String path, final String expected) throws Throwable {
        verifier.verify(path, actual -> assertThat(actual, is(parseDouble(expected))));
    }

    @Then("^verify: '([^\"]*)'!=(\\d+\\.\\d+)$")
    public void verifyNotEqualsDouble(String path, final String expected) throws Throwable {
        verifier.verify(path, actual -> assertThat(actual, not(parseDouble(expected))));
    }

    @Then("^verify: '([^\"]*)'>(\\d+\\.\\d+)$")
    public void verifyGreaterThanDouble(String path, final String expected) throws Throwable {
        verifier.verify(path, actual -> assertThat((double) actual, greaterThan(parseDouble(expected))));
    }
}
