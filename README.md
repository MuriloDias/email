# Gerenciamento de Email
API para gerenciamento de envio de email usando mensageria, aplicação dividida em 3 partes:
- Lib com classes comuns e de uso dos outros modulos, conversores, entidades.
- Producer, responsável por expor uma API para receber um JSON com o email a ser enviado, interpretá-lo, realizar a persistencia no DB e enviá-lo para Mensageria (RabbtiMQ no caso).
- Consumer, responsável por consumir os itens da fila, e realizar a comunicação com AWS SES por SMTP, realizar o envio e atualizar status do email no DB.

## Tecnologias
- Java 21
- Spring 3.3.12
- Maven 3.9.10
- RabbitMQ 4.1.1

## JSON utilizando o seguinte padrão
### URL http://localhost:8080/email/enviar
	{
	  "aplicacaoQueEnviou": "Nome da Aplicaçao que enviou o email",
	  "operacaoDaAplicacao": "Nome da Operação da Aplicação que realizou o disparo",
	  "usuarioResponsavel": "Nome do usuário que fez a operaçao que resultou no disparo do email",
	  "remetente": "no-reply@dominio.com.br",
	  "destinatarios": [
	    "email@dominio.com.br"
	  ],
	  "assunto": "Teste com anexo",
	  "conteudoHtml": "Olá!Segue o anexo solicitado.",
	  "anexos": [
	    {
	      "nomeArquivo": "exemplo.txt",
	      "tipoConteudo": "text/plain",
	      "conteudoBase64": "SGVsbG8sIGVzc2UgZXVtIGFyYXVpdm8gZGUgdGVzdGUu" 
	    }
	  ]
	}

## Os anexos podem ter os seguintes tipo de conteudo
	### DOCUMENTOS
	- PDF				application/pdf
	- Word (.docx)			application/vnd.openxmlformats-officedocument.wordprocessingml.document
	- Excel (.xlsx)			application/vnd.openxmlformats-officedocument.spreadsheetml.sheet
	- Texto simples			text/plain
	- CSV				text/csv
	### IMAGENS
	- JPG/JPEG		  	image/jpeg
	- PNG				image/png
	- GIF				image/gif
	- SVG	i			image/svg+xml
	### ARQUIVOS COMPACTADOS
	- ZIP				application/zip
	- RAR				application/vnd.rar
	- 7Z				application/x-7z-compressed[
	### AUDIOS
	- MP3				audio/mpeg
	- MP4				video/mp4
	- OGG				audio/ogg

