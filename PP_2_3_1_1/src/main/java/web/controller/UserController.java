package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.entity.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/") // запрос на /. Получает список всех пользователей, добавляет в Model и возвращает в "index" (список пользователей)
    public String index(Model model) {
        List<User> list = userService.getUsers();
        model.addAttribute("listUsers", list);
        return "index";
    }

    @GetMapping(value = "/show") // Получает идентификатор пользователя в качестве параметра запроса "id", извлекает его и добавляет в Модель с именем "user", возвращает в "show_user" (информация о конкретном пользователе)
    public String showUser (@RequestParam(value = "id") Long id, Model model) {
        model.addAttribute("user", userService.getSingleUserById(id));
        return "show";
    }

    @GetMapping("/add") // Добавляет нового пользователя в Модель как пустой объект "user" и возвращает в "add" (форма для добавления нового пользователя)
    public String addUser(Model model){
        model.addAttribute("user", new User());
        return "add";
    }

    @PostMapping() // Создает нового пользователя из формы добавления, принимает объект "user, создает его: добавляет и отправляет обратно на главную страницу
    public String createNewUser(@ModelAttribute("user") User user){
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/edit") // получает идентификатор, по нему извлекает пользователя, добавляет в Модель под именем "user", возвращает в "edit_user"? (форма для редактирования)
    public String edit(@RequestParam(value = "id") Long id, Model model) {
        model.addAttribute("user", userService.getSingleUserById(id));
        return "edit";
    }
    @PatchMapping("/save") // обновляет информацию о пользователе из формы редактирования. Принимает объект User, обновляет его и отпрвляет обратно на главную страницу
    public String updateUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/";
    }
    public String deleteUser(@RequestParam(value = "id") Long id, Model model) {
        userService.delete(id);
        return "redirect:/";
    }

    @GetMapping(value = "/")
    public String delete(@RequestParam(value = "id") Long id, Model model) {
        User user = userService.getSingleUserById(id);
        model.addAttribute("user", user);
        return "delete";
    }
//    @GetMapping("/")
//    public String index() {
//        return "index"; // Имя вашего HTML-файла, который отображает список пользователей
//    }
}

//    @GetMapping(value = "/delete") // удаляет пользователя по id и отправляет обратно на главную страницу
//    public String deleteUser (@RequestParam(value = "id") Long id, Model model) {
//        userService.delete(id);
//        return "delete";
//    }



