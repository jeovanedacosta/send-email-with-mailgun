package br.com.goclip.mailStrategy;

import com.google.inject.Inject;

public class SenderFactory {
    public enum SenderType {API, SMTP}
    private final APIStrategy apiStrategy;
    private final SMTPStrategy smtpStrategy;

    @Inject
    public SenderFactory(APIStrategy apiStrategy,
                         SMTPStrategy smtpStrategy) {
        this.apiStrategy = apiStrategy;
        this.smtpStrategy = smtpStrategy;
    }

    public Sender createSender(SenderType type) {
        if (type == SenderType.API) {
            return apiStrategy;
        } else {
            return smtpStrategy;
        }
    }
}