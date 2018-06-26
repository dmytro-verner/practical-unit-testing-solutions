package chapter_6.exercise_6_15_2;

import org.junit.Before;
import org.junit.Test;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class UserServiceImplTests {

    private UserServiceImpl userService;
    private SecurityService securityService;
    private UserDao userDao;
    private User user;

    @Before
    public void setUp(){
        securityService = mock(SecurityService.class);
        userDao = mock(UserDao.class);
        user = mock(User.class);

        userService = new UserServiceImpl(userDao, securityService);
    }

    @Test
    public void shouldNotProceedFurtherWhenUsersGetPasswordThrowsException() {
        when(user.getPassword()).thenThrow(Exception.class);

        catchException(() -> userService.assignPassword(user));

        assertNotNull("Should have thrown exception of type Exception on calling getPassword", caughtException());
        verify(user, times(1)).getPassword();
        verifyNoMoreInteractions(user);
        verifyZeroInteractions(userDao);
    }

    @Test
    public void shouldNotProceedFurtherWhenSecurityServiceThrowsException() throws Exception {
        when(user.getPassword()).thenAnswer(i -> anyString());
        when(securityService.md5(anyString())).thenThrow(Exception.class);

        catchException(() -> securityService.md5(anyString()));

        assertNotNull("Should have thrown exception of type Exception on calling md5()", caughtException());
        verifyZeroInteractions(user);
        verifyZeroInteractions(userDao);
    }

    @Test
    public void shouldNotProceedFurtherWhenUsersSetPasswordThrowsException() throws Exception {
        when(user.getPassword()).thenAnswer(i -> anyString());
        when(securityService.md5(anyString())).thenAnswer(i -> anyString());
        doThrow(Exception.class).when(user).setPassword(anyString());

        catchException(() -> userService.assignPassword(user));

        assertNotNull("Should have thrown exception of type Exception on calling setPassword", caughtException());
        verify(user).getPassword();
        verify(securityService).md5(anyString());
        verify(user).setPassword(anyString());
        verifyZeroInteractions(userDao);
    }

    @Test
    public void shouldNotUpdateUserDaoWhenExceptionIsThrownOnUpdateUser() throws Exception {
        when(user.getPassword()).thenAnswer(i -> anyString());
        when(securityService.md5(anyString())).thenAnswer(i -> anyString());
        doThrow(Exception.class).when(userDao).updateUser(any());

        catchException(() -> userDao.updateUser(any()));

        assertNotNull(caughtException());
        verify(userDao, times(1)).updateUser(any());
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenNullIsPassedAsParameter() {
        catchException(() -> userService.assignPassword(null));

        assertTrue(caughtException() instanceof NullPointerException);
        verifyZeroInteractions(user);
        verifyZeroInteractions(securityService);
        verifyZeroInteractions(userDao);
    }
}
