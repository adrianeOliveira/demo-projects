package br.com.adrianerodrigues.junitdemo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class MockitoTests {

    @Mock
    private UserRepository userRepository;

    @Test
    void findUserByIdTest() {
        var user = new User(1L, "Adriane");

        when(userRepository.findById(1L)).thenReturn(user);

        User userResult = userRepository.findById(1L);

        assertEquals(user.getId(), userResult.getId());
        verify(userRepository).findById(1L);
    }
}
