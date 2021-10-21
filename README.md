### HATEOAS. 
* Hypermedia as the Engine of Application State ( HATEOAS)
* is a constraint of the REST application architecture that distinguishes it from other network application architectures. With HATEOAS, a client interacts with a network application whose application servers provide information dynamically through hypermedia.


### Generate Swagger Code
````
gradle clean generateSwaggerCode
````

### Executing unit tests
You can run the 
```
gradle clean test 
```
command to execute our tests. This will generate the unit test reports at spring-jpa-gradle/build/reports/tests/test/index.html

```
gradle clean build 
```
will generate report coverage at spring-jpa-gradle/build/jacoco/test/html/index.html

### Generating the public/private keys
```
You can use JDK's keytool to create a key store and generate public/private keys, as shown in the following code snippet:

$ keytool -genkey -alias "jwt-sign-key" -keyalg RSA -keystore jwt-keystore.jks -keysize 4096

Enter keystore password:

Re-enter new password:

What is your first and last name?

[Unknown]:  Modern API Development

What is the name of your organizational unit?

[Unknown]:  Org Unit

What is the name of your organization?

[Unknown]:  Packt

What is the name of your City or Locality?

[Unknown]:  City

What is the name of your State or Province?

[Unknown]:  State

What is the two-letter country code for this unit?

[Unknown]:  IN

Is CN=Modern API Development, OU=Org Unit, O=Packt, L=City,                               ST=State, C=IN correct?

[no]:  yes

Generating 4,096 bit RSA key pair and self-signed certificate (SHA384withRSA) with a validity of 90 days

for: CN=Modern API Development, OU=Org Unit, O=Packt,              L=City, ST=State, C=IN

The generated key store should be placed under the src/main/resources directory. These keys are valid only for 90 days from the time they got generated.
```


* CI stands for continuous integration, which means build > test > merge in a code repository. 
* CD stands for continuous delivery and/or continuous deployment, both of which may be used interchangeably. 
* Continuous delivery is a process where code is automatically tested and released (read and uploaded) to an artifact repository or container registry. Then, it can be picked and deployed to a production environment. 
* Continuous deployment is one step ahead of continuous delivery in the pipeline, and code is deployed to the production environment once the previous steps have been successful. 
* Products that don't release their code for public access use this approach, such as Facebook and Twitter. 
* On the other hand, products/services that is available publicly, such as the Spring Framework and Java, use continuous delivery pipelines.


JUnit: https://junit.org/
AssertJ: https://assertj.github.io/doc/
Hamcrest: http://hamcrest.org/
Mockito: https://site.mockito.org/

### Docker is now up and running. Now, we can pull and start the local Docker registry by using the following command:
```
docker run -d -p 5000:5000 -e REGISTRY_STORAGE_DELETE_ENABLED=true --restart=always --name registry registry:2
```

### Build Docker image
```
gradle clean bootBuildImage
```

### Run Image in a container
```
docker run -t 172.18.96.1:5000/spring-jpa-graddle:0.0.1-SNAPSHOT -p 8080:8080 172.18.96.1:5000/spring-jpa-graddle:0.0.1-SNAPSHOT
```
You can learn more about Spring Boot, Docker, and Kubernetes and their configuration at https://github.com/dsyer/kubernetes-intro.