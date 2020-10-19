package com.example.demo1;

import com.example.demo1.web.rest.NoteResourceTest;
import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class NativeExampleResourceIT extends NoteResourceTest {

    // Execute the same tests but in native mode.
}