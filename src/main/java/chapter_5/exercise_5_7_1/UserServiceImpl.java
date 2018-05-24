package chapter_5.exercise_5_7_1;

public class UserServiceImpl {

    private UserDao userDao;
    private SecurityService securityService;

    public UserServiceImpl(UserDao userDao, SecurityService securityService){
        this.userDao = userDao;
        this.securityService = securityService;
    }

    public void assignPassword(User user) throws Exception {
        String passwordMd5 = securityService.md5(user.getPassword());
        user.setPassword(passwordMd5);
        userDao.updateUser(user);
    }
}
