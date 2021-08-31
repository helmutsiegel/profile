# profile
### - java version: coretto-1.8.0_282 
### - app server: payara-5.2021.3
### - node version: v14.16.1
### - npm version: 6.14.12
### - angular cli version: 12.0.0 
### - install angular cli: ```npm install -g @angular/cli```

#To enable secure admin on server: 
####./asadmin start-domain
####./asadmin change-admin-password
####./asadmin enable-secure-admin
####./asadmin stop-domain
####./asadmin start-domain

#To add library to payara:
####./asadmin add-library postgresql-42.2.20.jar 

## Add proxy config to angular:

Create the following file "src/proxy.conf.json", then add the following configuration: {

```
"/{yourPathPattern}/*": {
  "target": "{yourHost}",
  "secure": false } 
}
```
Then add ```--proxy-config proxy.conf.json``` to start in scripts in package.json \
for example:  ```"start":"ng serve --proxy-config proxy.conf.json"```

