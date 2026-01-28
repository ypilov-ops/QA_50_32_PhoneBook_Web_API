package utils;

import dto.User;
import net.datafaker.Faker;

public class UserFactory {
    static Faker faker = new Faker();

    public static User positiveUser(){
        User user = new User(faker.internet().emailAddress(),
                "D@NT3A");
        return user;
    }

    public static User negativeUserWrongEmail(){
        User user = new User
                (faker.internet().domainName(),"D@NT3A");
        return user;
    }

    public static User negativeUserWrongPassword(){
        User user = new User
                (faker.internet().emailAddress(),"dante");

    }
}
