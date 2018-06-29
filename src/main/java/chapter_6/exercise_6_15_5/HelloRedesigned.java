package chapter_6.exercise_6_15_5;


public class HelloRedesigned {

    private TimeProvider timeProvider;

    public HelloRedesigned(TimeProvider timeProvider) {
        this.timeProvider = timeProvider;
    }

    public String sayHello() {
        if(timeProvider.isMorning()) {
            return DayTimeTypeMessage.MORNING;
        }
        else {
            return DayTimeTypeMessage.AFTERNOON;
        }
    }
}
