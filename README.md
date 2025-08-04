# Gerenciamento de Email
API para gerenciamento de envio de email usando mensageria, aplicação dividida em 3 partes:
- Lib com classes comuns e de uso dos outros modulos, conversores, entidades.
- Producer, responsável por expor uma API para receber um JSON com o email a ser enviado, interpretá-lo, realizar a persistencia no DB e enviá-lo para Mensageria (RabbtiMQ no caso).
- Consumer, responsável por consumir os itens da fila, e realizar a comunicação com AWS SES por SMTP, realizar o envio e atualizar status do email no DB.

## Tecnologias
- Java 21
- Spring 3.3.12
- RabbitMQ
