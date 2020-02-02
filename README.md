# SpringSKBProject
1.В данном тестовом приложении я реализовал регистрацию юзеров, последующее отправление их в БД. БД использовал PostgeSQL, но  в application.properties можно приконнектится к любой.


2.Так же осуществил взаимодействкие с другой системой, которая допусккает/блокирует вновь зарегистровавшихся юзеров. Для этого использовал сообщения из RabbitMQ.

3.С помощью метода com.skb.SpringSKBProject.services.UserService#sendProductMessage мы отсылаем сообщение в очередь.

4.Метод guru.springframework.listener.ProductMessageListener#receiveMessage всегда стоит и слушает. В данном тестовом я сделал, что при получении сообщения он допускает юзера в систему.

5.Сделал класс com.skb.SpringSKBProject.services.EmailService#EmailService для отпраки email, и поставил заглушку.

6.В сервисе com.skb.SpringSKBProject.services.UserService#getNotApprovedUsers сделал метод, который выводит юзеров, с которыми небыло еще не произведено ни одного действия (допущен/заблокирован), довольно удобно.
