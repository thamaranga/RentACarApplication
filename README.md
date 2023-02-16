# RentACarApplication

This is a sample project which demonstrates spring boot microservices architecture.
This project contains 5 rest services as customer-service, vehicle-service,rent-service,discovery-service , rent-cloud-config-service and api-gateway. rent-service is our main service.
From rent-service we are calling to customer-service and vehicle-service.  
rentcloudcommons  is not a services. It is a JAVA project which contains all the model classes which are used by rest services.
rent-cloud-config-server is the git config server. (Related git repository -https://github.com/thamaranga/RentACarApplicationConfigurations).
Since we have put property files which are match for prod env only, we have to give the active profie as prod while deploying the applications.
(In intelij idea need to provide this env variable. SPRING_PROFILES_ACTIVE=prod )
API gateway is the place all the routings are happened.(We should access all the services through API gateway only.)
Here inside rent-service we are calling to customer-service and vehicle-service without hardcoding their urls (Related mapping in rent-service is  @GetMapping("/rent/full/{id}")).
Here we have used a discovery service for finding customer-service and vehicle service.

