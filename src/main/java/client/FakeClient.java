package client;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FakeClient {
    private final Faker faker = new Faker();

    public Client getRandomClient() {
        return new Client(
                faker.internet().emailAddress(),
                faker.internet().password(10, 15),
                faker.name().fullName()
        );
    }

    public Client getRandomClientWithWrongPw() {
        return new Client(
                faker.internet().emailAddress(),
                faker.internet().password(4, 6),
                faker.name().fullName()
        );
    }
}