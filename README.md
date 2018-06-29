# spring-boot-ssl-authentication

Exemplo de microserviços construídos com springboot que utilizam certificados ssl como mecanismo de autenticação.

Existem dois projetos neste repositório:
* __spring-boot-ssl-authentication-server:__ Pode ser acessado através da URL https://localhost:8443/, porém, a intenção é que 
o mesmo seja acessado programaticamente por um outro serviço client.
* __spring-boot-ssl-authentication-client:__ Pode ser acessado através da URL http://localhost:8080/. O mesmo realiza uma requisição 
no microserviço server, que passa por autenticação e então devolve sua resposta.

## Para gerar seus próprios certificados
Nos fontes dos projetos já existem os certificados para o devido funcionamento dos mesmos, mas para gerar seus próprios certificados pode-se 
utilizar o seguinte procedimento:
 
* #### Gerando keystore dos serviços
1. __Server:__
`keytool -genkeypair -alias server -keyalg RSA -dname "CN=localhost,OU=myorg,O=myorg,L=mycity,S=mystate,C=br" 
-keypass keystore-pass -keystore server-keystore.jks -storepass keystore-pass`

2. __Client:__
`keytool -genkeypair -alias client -keyalg RSA -dname "CN=client-test,OU=myorg,O=myorg,L=mycity,S=mystate,C=br" -keypass keystore-pass 
-keystore client-keystore.jks -storepass keystore-pass`

* #### Extraindo chave pública dos keystores gerados
1. __Server:__
`keytool -exportcert -alias server -file server-public.cer -keystore server-keystore.jks -storepass keystore-pass`

2. __Client:__
`keytool -exportcert -alias client -file client-public.cer -keystore client-keystore.jks -storepass keystore-pass`

* #### Importando chave pública do client no truststore do server:
1. `keytool -importcert -keystore server-truststore.jks -alias client -file client-public.cer -storepass truststore-pass`

* #### Importando chave pública do server no truststore do client:
1. `keytool -importcert -keystore client-truststore.jks -alias server -file server-public.cer -storepass keystore-pass`

###### Caso as chaves públicas não sejam importadas nos truststores, ao consumir o serviço do server o client receberá o seguinte erro:
`javax.net.ssl.SSLHandshakeException: Received fatal alert: bad_certificate`

## Acessar o server através do browser
Para acessar o endereço `https://localhost:8443` diretamente pelo browser é necessário extrair um `.p12` do 
keystore do client para então importa-lo no browser:
`keytool -importkeystore -srckeystore client-keystore.jks -destkeystore client-test.p12 -deststoretype PKCS12`
