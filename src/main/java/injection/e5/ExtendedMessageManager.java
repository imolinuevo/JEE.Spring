package injection.e5;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:META-INF/application.properties")
public class ExtendedMessageManager {
	
	@Autowired
    private Environment environment;

    private ExtendedMessageService messageService1;

    private ExtendedMessageService messageService2;

    @PostConstruct
    public void postConstruct() {
        messageService1.setPrefix(environment.getProperty("e5.prefix"));
    }

    // Se inyecta un objeto de la clase indicada
    // @Autowired(required = true) por defecto
    // @Qualifier("messageService") Se inyecta un objeto del tipo indicado
    @Autowired
    public void setMessageService1(ExtendedMessageService messageService1) {
        this.messageService1 = messageService1;
    }

    // Es una instancia diferente a la anterior por ser "prototype"
    @Autowired
    public void setMessageService2(ExtendedMessageService messageService2) {
        this.messageService2 = messageService2;
    }

    public void addMessage1(String key, String message) {
        messageService1.add(key, message);
    }

    public String findKey1(String message) {
        return messageService1.key(message);
    }

    public String findMessage1(String key) {
        return messageService1.message(key);
    }

    public String findMessage2(String key) {
        return messageService2.message(key);
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("liberando...");
    }

}
