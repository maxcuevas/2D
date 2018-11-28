package game.Camera;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class ObstructionVisibilityTest {

  String string;

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void setVisibility() {

    string = "";

    assertThat(string).isEqualTo("");
  }
}