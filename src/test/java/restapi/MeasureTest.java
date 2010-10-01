package restapi;

import org.junit.runner.RunWith;
import restapi.model.Measure;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class MeasureTest {

    @Test
    public void shouldSupportName() {
        Measure measure = new Measure("abc");
        assertThat(measure.getName(), equalTo("abc"));
    }
}
