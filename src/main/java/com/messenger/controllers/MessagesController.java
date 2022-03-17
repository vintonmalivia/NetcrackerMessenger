package com.messenger.controllers;
import com.messenger.repository.IDatabaseConversationDAO;
import com.messenger.repository.IDatabaseMessageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;
import java.util.UUID;
import static com.messenger.constants.controllers.Endpoints.MESSAGES;

@Controller
@RequestMapping(MESSAGES)
public class MessagesController
{
    private static abstract class Views {
        private static final String CONVERSATIONS_PATH = "conversations/";
        private static final String MESSAGES_HTML = "conversationMessages";
    }

    private static abstract class PathVariables {
        public static final String UUID = "uuid";
    }

    private static abstract class ModelAttributes {
        private static final String MESSAGES = "conversationMessages";
    }

    @Autowired // TODO: смотри подобные тудушки. Ставим над конструкторами, не над полями. + модификатор доступа должен быть явно указан
    private IDatabaseMessageDAO databaseMessageDAO; // TODO: Пора обернуть все репозитории в сервисы. https://alexkosarev.name/2018/07/27/n-tier-java-part1/
                                                    //  У нас обычно используются 3 уровня: контроллеры -> сервисы -> репозитории.
                                                    //  Самое быстрое объяснение: репозитории только берут данные, сервисы могут делать дополнительную бизнес-логику
                                                    //  над данными с репозиториев (DAO). Пример бизнес логики: некоторая промежуточная валидация
                                                    //  Контроллеры юзают сервисы. Именно здесь должен быть прикручен сервис (а не репозиторий, как это сделано сейчас)
                                                    //  Доп литература: многословное построение приложения. Слои репозитория, сервиса, контроллера
    @GetMapping
    public String getMessagesFromConversationByID(@PathVariable(PathVariables.UUID) UUID uuid, Model model)
    {
//        List<Conversation> conversations = ConversationManager.getInstance().getConversations();
//        Conversation resultConversation = conversations.stream().filter(conversation -> conversation.getId().equals(uuid)).findAny().orElse(null);
//        model.addAttribute(MESSAGES, resultConversation);
        model.addAttribute(ModelAttributes.MESSAGES, databaseMessageDAO.getMessages(uuid));
        return Views.CONVERSATIONS_PATH + Views.MESSAGES_HTML;
    }
}
