# SpringSKBProject
В данном тестовом приложении я реализовал регистрацию юзеров, последующее отправление их в БД. БД использовал PostgeSQL, но  в application.properties можно приконнектится к любой. 
Так же осуществил взаимодействкие с другой системой, которая допусккает/блокирует вновь зарегистровавшихся юзеров. Для этого использовал сообщения из RabbitMQ. 
С помощью метода com.skb.SpringSKBProject.services.UserService#sendProductMessage мы отсылаем сообщение в очередь.
Метод guru.springframework.listener.ProductMessageListener#receiveMessage всегда стоит и слушает. В данном тестовом я сделал, что при получении сообщения он допускает юзера в систему.
Сделал класс com.skb.SpringSKBProject.services.EmailService#EmailService для отпраки email, и поставил заглушку
В сервисе com.skb.SpringSKBProject.services.UserService#getNotApprovedUsers сделал метод, который выводит юзеров, с которыми небыло еще не произведено ни одного действия (допущен/заблокирован), довольно удобно
