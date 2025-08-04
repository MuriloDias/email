Aplicação producer tem configurações especificas para cada ambiente.
Configure o seguinte argumento dependendo do ambiente que estiver executando a aplicação

QAS ou DEV
-Dspring.profiles.active=test

PRD
-Dspring.profiles.active=prod
