Per far funzionare in modo permanente il microfono è necessario abilitare https

Utilizzare questo tool per generare una chiave 

keytool -genkey -alias mkyong -keyalg RSA -keystore /Users/$USER/mykey.key"


Ed inserire poi questa configurazione all'interno del file server.xml

 <Connector port="8443" SSLEnabled="true"
maxThreads="150" minSpareThreads="25" maxSpareThreads="75"
enableLookups="true" disableUploadTimeout="true"
acceptCount="100" debug="0" scheme="https" secure="true"
clientAuth="false" sslProtocol="TLS"
keystoreFile="/Users/$USER/mykey.key"
keystorePass="123456" />


