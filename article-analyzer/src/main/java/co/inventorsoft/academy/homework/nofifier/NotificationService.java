package co.inventorsoft.academy.homework.nofifier;

import co.inventorsoft.academy.homework.model.Article;
import co.inventorsoft.academy.homework.model.User;


public interface NotificationService {

/*    public void notify(Article article) {
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
    }*/

    void notify(User user, Article article);

    String getSenderIdentifier();

}
