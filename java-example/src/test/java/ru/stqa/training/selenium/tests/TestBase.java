package ru.stqa.training.selenium.tests;

import org.junit.Before;
import ru.stqa.training.selenium.app.Application;

public class TestBase {

    public static ThreadLocal<Application> tlApp = new ThreadLocal<>(); //явные ожидания
    public Application app;

    @Before
    public void start() {
        //если драйвер уже есть, то выходим
        if (tlApp.get() != null) {
            app = tlApp.get();
            return;
        }
        //иначе создаем новый
        app = new Application();
        tlApp.set(app);
        //останов драйвера в конце теста
        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> { app.quit(); app = null; }));
    }

}
