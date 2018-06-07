package br.com.goclip;

import br.com.goclip.mailStrategy.SenderFactory;
import br.com.goclip.mailStrategy.Sender;
import br.com.goclip.util.MailBuilder;
import com.github.racc.tscg.TypesafeConfigModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Response;

public class Main {

    public static void main(String[] args) {
        Config config = ConfigFactory.parseResources("config-variable-default.conf");
        Injector injector = Guice.createInjector(TypesafeConfigModule.fromConfigWithPackage(config, "br.com.goclip"));
        SenderFactory mailStrategyFactory = injector.getInstance(SenderFactory.class);
        Sender sender = mailStrategyFactory.createSender(SenderFactory.SenderType.valueOf(config.getString("mailgun.username").toUpperCase()));
        MultipartBody build = new MailBuilder()
                .from("jeovanebc@gmail.com")
                .to("jeovanebc@gmail.com")
                .subject("Teste")
                .text("vitinho show de bola")
                .html("<html><h1>O Luciano é bai...</h></html>")
                .attachment("/home/jeovane/clip-projects/mailnotification/pdf-test.pdf", MediaType.parse("application/pdf"))
                .build();
        Response send = sender.send(build);
        if (send.code() == 200) {
            System.out.println("deu bom");
        } else {
            System.out.println("deu ruim");
        }
    }
}
