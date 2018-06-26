package chapter_6.exercise_6_15_3;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class UserListTests {

    private UserList userList = new UserList();

    @Test
    public void emptyListIsReturnedIfNoUsersAreAdded() {
        assertThat(userList.getUsers()).isEmpty();
    }

    @Test
    public void oneUserIsReturnedInListWhenOneWasAddedToList() {
        User user1 = mock(User.class);

        userList.addUser(user1);

        assertThat(userList.getUsers()).hasSize(1).contains(user1);
    }

    @Test
    public void multipleUsersAreReturnedInListWhenTheyWereAddedToList() {
        User user1 = mock(User.class);
        User user2 = mock(User.class);

        userList.addUser(user1);
        userList.addUser(user2);

        assertThat(userList.getUsers()).hasSize(2).contains(user1, user2);
    }
}
