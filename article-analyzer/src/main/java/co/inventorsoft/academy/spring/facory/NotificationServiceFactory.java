package co.inventorsoft.academy.spring.facory;

@Component
public class NotificationServiceFactory {

    @Autowired
    private EmailNotificationService emailNotificationService;

    @Autowired
    private SlackNotificationService slackNotificationService;

    public NotificationService getService(NotificationType type) {
        switch (type) {
            case EMAIL:
                return emailNotificationService;
            case SLACK:
                return slackNotificationService;
            default:
                throw new IllegalArgumentException("Unknown notification type: " + type);
        }
    }
}