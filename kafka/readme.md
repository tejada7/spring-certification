## Creating SSL certificates with keystore and trustore

1. Creating a RSA key
```shell
openssl genrsa -out ca.key
```
2. Creating a certificate out of the previous key. Mind the 3650 = ten-year-validity certificate
```shell
echo -e "FR\nIle de France\nParis\nZenika\nLabs\nlocalhost\nfavio.tejada@zenika.com" | openssl req -new -x509 -key ca.key -days 3650 -out ca.crt
```

3. Creating a truststore containing the certificate
```shell
echo -e 'password\npassword\nyes' | keytool -keystore kafka.truststore.jks -alias CARoot -import -file ca.crt
```

4. Creating a ten-year-valid keystore
```shell
echo -e 'password\npassword\nFavio Tejada\nLabs\nZenika\nParis\nIle de France\nFR\nyes' | keytool -keystore kafka.keystore.jks -alias localhost -validity 365 -genkey -keyalg RSA
```

5. Creating an unsigned certificate from keystore created in previous step
```shell
echo -e 'password' | keytool -keystore kafka.keystore.jks -alias localhost -certreq -file kafka.unsigned.crt
```

6. Signing certificate with original certificate created in step 2
```shell
openssl x509 -req -CA ca.crt -CAkey ca.key -in kafka.unsigned.crt -out kafka.signed.crt -days 365 -CAcreateserial
```

7. Adding original certificate to keystore
```shell
echo -e 'password\nyes' | keytool -keystore kafka.keystore.jks -alias CARoot -import -file ca.crt
```

8. Adding signed certificate to keystore
```shell
echo 'password' | keytool -keystore kafka.keystore.jks -alias localhost -import -file kafka.signed.crt
```

9. Creating Kafka client config file
```shell
echo "bootstrap.servers=localhost:9093
security.protocol=SSL
ssl.truststore.location=kafka.truststore.jks
ssl.truststore.password=password
ssl.keystore.location=kafka.keystore.jks
ssl.keystore.password=password
ssl.key.password=password
ssl.endpoint.identification.algorithm=
">client-ssl-host.properties
```

Script available [here](src/main/resources/scripts/generate-ssl-certificates.sh).
