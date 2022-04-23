import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MockitoMockTest{

    @Test
    void createAndStubMock(){
        Person p = mock(Person.class);

        when(p.getFirstName()).thenReturn("mocked result");
        when(p.getLastName()).thenReturn("real result?");

        assertAll("Mock Test",
                ()->assertEquals("mocked result", p.getFirstName()),
                ()->assertEquals("real result?", p.getLastName())
        );
    }

    @Test
    void testCallRealMethod(){
        Person p = mock(Person.class);

        doCallRealMethod().when(p).isAdult(37);
        assertEquals(true, p.isAdult(37));
    }

    @Mock    Person Johnson;
    @Mock    Person Toms;

    @Test
    void mockAnotationTest(){
        MockitoAnnotations.openMocks(this);

        Johnson.setAge(37);
        verify(Johnson, atLeastOnce()).setAge(anyInt());

        when(Toms.getFullName()).thenReturn("Toms Jonadan");
        assertEquals("Toms Jonadan", Toms.getFullName());

        HelloWorld hworld = mock(HelloWorld.class);
        hworld.isGreater(34,56);
        verify(hworld, atLeastOnce()).isGreater(eq(34),eq(56));
    }

    @Test
    void createAndStubSpy(){
        Person p = spy(new Person("최", "고다"));

        when(p.getFirstName()).thenReturn("mocked result");

        assertAll("Mock Test",
                ()->assertEquals("mocked result", p.getFirstName()),
                ()->assertEquals("고다", p.getLastName())
        );

    }

    @Test
    void createAndStubMock2(){
        Person p = mock(Person.class);

        when(p.getFirstName()).thenReturn("firstname mocked result");
        doReturn("fullname mocked result").when(p).getFullName();

        assertEquals("firstname mocked result", p.getFirstName());
        assertEquals("fullname mocked result", p.getFullName());
    }

    @Test
    void createAndStubSpy2(){
        Person p = spy(new Person("김", "인류"));

        when(p.getFirstName()).thenReturn("mocked result");
        doReturn("spy mock").when(p).getFirstName();
        assertEquals("spy mock", p.getFirstName());

    }

    @Test
    void createAndStubMock3(){
        Person p = mock(Person.class);

        when(p.getFirstName())
                .thenReturn("mocked result1")
                .thenReturn("mocked result");

        assertEquals("mocked result1", p.getFirstName());
        assertEquals("mocked result", p.getFirstName());
    }


    @Test
    void matcherMockitoTest(){

        Person  p = mock(Person.class);

        doThrow(IllegalArgumentException.class).when(p).setAge(33);
        assertThrows(IllegalArgumentException.class, ()->p.setAge(33));

    }


    @Test
    void matcherMockitoTest2(){
        Person  p = mock(Person.class);

        doThrow(IllegalArgumentException.class).when(p).setAge(anyInt());
        assertThrows(IllegalArgumentException.class, ()->p.setAge(1));

    }
}

class ThrowingExceptionTest{

    @Test
    void testThrowingException_thenThrow(){
        Person p = mock(Person.class);

        when(p.getFirstName()).thenThrow(IllegalArgumentException.class);
        assertThrows(IllegalArgumentException.class, ()->p.getFirstName());
    }

    @Test
    void testThrowingException_thenThrow2(){
        Person p = mock(Person.class);

        when(p.getFirstName())
                .thenReturn("mocked result")
                .thenThrow(IllegalArgumentException.class);

        assertEquals("mocked result", p.getFirstName());
        assertThrows(IllegalArgumentException.class, ()->p.getFirstName());
    }

}

class mockitoVerifyingTest{

    @Test
    void verifyingNoInteractionWithSpecificMethod(){
        Person p = mock(Person.class);

        p.getFirstName();
        p.getFirstName();

        verify(p, times(2)).getFirstName();
    }
}

class inorderMockTest{

    @Test
    void testInorderForMultipleMocks(){
        Person first = mock(Person.class);
        Person second = mock(Person.class);

        first.setAge(33);
        second.getFirstName();
        first.getFullName();

        InOrder inOrder = inOrder(first,second);

        inOrder.verify(first).setAge(anyInt());
        inOrder.verify(second).getFirstName();
        inOrder.verify(first).getFullName();

    }
}

class mock4HelloWorld{

    @Test
    public void spyHelloworld() throws Exception{
        HelloWorld spyHello = spy(HelloWorld.class);
        assertEquals("HelloWorld", spyHello.getClassName());
    }


    @Test
    public void mockHelloworld() throws Exception{
        HelloWorld mockHello = mock(HelloWorld.class);
        when(mockHello.getClassName()).thenReturn("HelloWorld");
        assertEquals("HelloWorld", mockHello.getClassName());
    }

    @Mock
    Random mockRandom;
    @InjectMocks
    HelloWorld mockHello;

    @Test
    public void testHelloRollDice(){
        MockitoAnnotations.openMocks(this);
        when(mockRandom.nextInt(anyInt())).thenReturn(45);

        assertEquals(46, mockHello.rollDice());
        verify(mockRandom, atLeastOnce()).nextInt(anyInt());
    }

}
