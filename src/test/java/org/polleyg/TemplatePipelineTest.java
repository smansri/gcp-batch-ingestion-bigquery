package org.polleyg;

import com.google.api.services.bigquery.model.TableRow;
import org.apache.beam.sdk.transforms.DoFnTester;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;


public class TemplatePipelineTest {

    private static DoFnTester<String, TableRow> fnTester;

    @BeforeClass
    public static void init() {
        fnTester = DoFnTester.of(new TemplatePipeline.WikiParDo());
    }

    @AfterClass
    public static void destroy() {
        fnTester = null;
    }

    @Test
    public void test_parse_CSV_format_successfully_with_tablerow() throws Exception {

        List<String> input = new ArrayList<>();

        input.add("toto,8,tata");

        List<TableRow> output = fnTester.processBundle(input);

        Assert.assertThat(output, is(not(empty())));

        Assert.assertThat(output.get(0).get("title"), is(equalTo("toto")));
        Assert.assertThat(output.get(0).get("id"), is(equalTo("8")));
        Assert.assertThat(output.get(0).get("language"), is(equalTo("tata")));
    }

    @Test
    public void test_parse_header_return_empty_list() throws Exception {

        List<String> input = new ArrayList<>();

        input.add("title,id,language");

        List<TableRow> output = fnTester.processBundle(input);

        Assert.assertThat(output, is(empty()));
    }
}
