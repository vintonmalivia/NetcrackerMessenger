package com.messenger.controllers;
import com.messenger.models.Conversation;
import com.messenger.repository.TextMessageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/messages")    // TODO (high): Стоит переименовать этот контроллер в conversation контроллер.
                                //  Или сделать отдельный контроллер для conversations. Нужно разделить логику
                                //  Тогда в этом @RequestMapping будет "/conversations", а в другом контроллере (для сообщений) - /conversation/{uuid}/messages
public class MessageController
{
    private final TextMessageDAO textMessageDAO;

    @Autowired
    public MessageController(TextMessageDAO textMessageDAO){
        this.textMessageDAO = textMessageDAO;
    }

    @GetMapping() // TODO (low): Обычно скобочки не ставим
    public String getConversations(Model model)
    {
        model.addAttribute("allConversations", textMessageDAO.getConversations());
        return "messages/getConversations"; // TODO (normal): сообщения -> получить все чаты. Это странно. Лучше сделать что-то
                                            //  типа messenger/conversations или просто conversations
    }

    @GetMapping("/{uuid}")
    public String getMessagesFromConversationByID(@PathVariable("uuid") UUID uuid, Model model)
    {
        model.addAttribute("conversationMessages", textMessageDAO.getConversationMessages(uuid));
        return "messages/getConversationMessages";  // TODO (normal): get в названии HTML страничек не пишут. Лучше сделать что-то типа
                                                    //  messenger/conversationMessages или просто conversations
    }

    @GetMapping("/new-conversation")    // TODO: (normal) Когда контроллер будет начинаться с @RequestMapping("/conversations"),
                                        //  то здесь просто можно оставить @GetMapping("/new"). Не принципиально, но обычно для создании
                                        //  сущности в контроллере используется простой эндпоинт "/new"
    public String getPageForCreateNewConversation(@ModelAttribute("newConversation") Conversation conversation)
    {
        return "messages/newConversation";
    }

    @PostMapping()
    public String createNewConversation(@ModelAttribute("newConversation") Conversation conversation)
    {
        textMessageDAO.saveNewConversation(conversation);
        return "redirect:/messages";
    }
}
