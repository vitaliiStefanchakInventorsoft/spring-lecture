package co.inventorsoft.academy.homework.nofifier;

import co.inventorsoft.academy.homework.mapper.UserMapper;
import co.inventorsoft.academy.homework.model.Article;
import co.inventorsoft.academy.homework.model.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public abstract class NotificationService {
    @Autowired
    private UserMapper userMapper;

    private Gson gson;

    @Autowired
    public void setGson(Gson gson) {
        this.gson = gson;
    }

    public void notify(Article article) {
        System.out.println("Notifying users...");
        String jsonArticle = gson.toJson(article);
        List<User> users = userMapper.map(new File("users.json"));
        for (User user : users) {
            switch (user.getNotificationType()) {
                case EMAIL -> {
                    notifyUserByEmail(user, jsonArticle);
                }
                case SLACK -> {
                    notifyUserBySlack(user, jsonArticle);
                }
            }
        }
    }

    protected abstract void notifyUserByEmail(User user, String content);

    protected abstract void notifyUserBySlack(User user, String content);
}
